package projeto.estoque.application.telas.estoque;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import projeto.estoque.application.excel.metodos.file.logs.FileLogs;
import projeto.estoque.application.objetos.Logs;
import projeto.estoque.application.objetos.Usuario;

public class TelaConsultaLogs {
	public static StackPane criarTelaConsultaLogs(StackPane spSecundaria, List<Logs> listaLogs, FileLogs fileLogs, Usuario usuario) {

		//Geral
		Rectangle limitesRetangulo = (Rectangle)spSecundaria.getChildren().get(0);
		double width = limitesRetangulo.getWidth();
		double height = limitesRetangulo.getHeight();

		//Ícones
		Image icAtencaoI = new Image(TelaConsultaLogs.class.getResourceAsStream("/images/menu.png"));
		Image icAtencaoII = new Image(TelaConsultaLogs.class.getResourceAsStream("/images/user.png"));
		ImageView ivAtencaoI = new ImageView(icAtencaoI);
		ImageView ivAtencaoII = new ImageView(icAtencaoII);
		ivAtencaoI.setFitWidth(width * 0.02);
		ivAtencaoI.setFitHeight(height * 0.03);
		ivAtencaoII.setFitWidth(width * 0.02);
		ivAtencaoII.setFitHeight(height* 0.03);

		//Labels
		Label lblConsultaI = new Label("", ivAtencaoI);
		Label lblConsultaII = new Label("", ivAtencaoII);
		Label lblLogsI = new Label("Consumo Geral");
		Label lblLogsII = new Label("Consumo de " + usuario.getNomeUsuario());
		lblLogsI.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblLogsII.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");

		//Menu
		Label lblSelecao = new Label("Selecione o período que deseja excluir:");
		lblSelecao.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		MenuButton mbtSelecao = new MenuButton();
		mbtSelecao.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral", "borda", "white");
		mbtSelecao.setStyle("-fx-padding: 0 0 0 0");
		mbtSelecao.setPrefWidth(width * 0.2);
		DateTimeFormatter formatoOriginal = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		DateTimeFormatter formatoAlterado = DateTimeFormatter.ofPattern("MM/yyyy");
		String ultimaData = null;
		for(Logs logAux : listaLogs) {
			if(logAux.getIdData() != null) {
				String dataAux = LocalDateTime.parse(logAux.getIdData(), formatoOriginal).format(formatoAlterado);
				if(!dataAux.equals(ultimaData)) {
					MenuItem miAux = new MenuItem(dataAux);
					miAux.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
					miAux.setStyle("-fx-padding: 5px 20px;");
					miAux.setOnAction(e -> mbtSelecao.setText(dataAux));
					mbtSelecao.getItems().add(miAux);
					ultimaData = dataAux;
				}
			}
		}
		Button btDeletar = new Button("Deletar Registros");
		btDeletar.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral", "borda", "greenII");
		btDeletar.setOnAction(e -> {
			String data = mbtSelecao.getText();
			for(Logs logs : listaLogs) {
				if(logs.getIdData() != null &&
						LocalDateTime.parse(logs.getIdData(), formatoOriginal).format(formatoAlterado).equals(data)) {
					logs.setIdData(null);
				}
			}
			fileLogs.alterarPlanilha(listaLogs);
			listaLogs.removeIf(log -> log.getIdData() == null);
			criarTelaConsultaLogs(spSecundaria, listaLogs, fileLogs, usuario);
		});

		//HBox
		HBox hboxMenu = new HBox(5);
		HBox hBoxEstoqueI = new HBox(5);
		HBox hBoxEstoqueII = new HBox(5);
		hboxMenu.getChildren().addAll(lblSelecao, mbtSelecao, btDeletar);
		hBoxEstoqueI.getChildren().addAll(lblConsultaI, lblLogsI);
		hBoxEstoqueII.getChildren().addAll(lblConsultaII, lblLogsII);
		hboxMenu.setAlignment(Pos.CENTER);
		hBoxEstoqueI.setAlignment(Pos.CENTER);
		hBoxEstoqueII.setAlignment(Pos.CENTER);

		//Tables
		ObservableList<Logs> olLogs = FXCollections.observableArrayList(listaLogs);
		FilteredList<Logs> flLogsI = new FilteredList<>(olLogs);
		FilteredList<Logs> flLogsII = new FilteredList<>(olLogs);
		flLogsI.setPredicate(e -> {
			return e.getIdData() != null;
		});
		flLogsII.setPredicate(e -> {
			return e.getIdData() != null && e.getNomeUsuario().equals(usuario.getNomeUsuario());
		});
		TableView<Logs> tvLogsI = new TableView<>(flLogsI);
		TableView<Logs> tvLogsII = new TableView<>(flLogsII);
		adicionarColunas(tvLogsI);
		adicionarColunas(tvLogsII);

		//ScrollPane
		ScrollPane scpLogsI = new ScrollPane(tvLogsI);
		ScrollPane scpLogsII = new ScrollPane(tvLogsII);

		//VBox
		VBox vBoxScrollPane = new VBox(10);
		vBoxScrollPane.setAlignment(Pos.CENTER);

		//Terminando a StackPane
		if(usuario.isPermAdministrador()) {
			scpLogsI.setMaxWidth(width * 0.75);
			scpLogsII.setMaxWidth(width * 0.75);
			scpLogsI.setMaxHeight(height * 0.38);
			scpLogsII.setMaxHeight(height * 0.38);
			vBoxScrollPane.getChildren().addAll(hboxMenu, hBoxEstoqueI, scpLogsI, hBoxEstoqueII, scpLogsII);
		} else {
			scpLogsII.setMaxWidth(width * 0.75);
			scpLogsII.setMaxHeight(height * 0.98);
			vBoxScrollPane.getChildren().addAll(hBoxEstoqueII, scpLogsII);
		}

		spSecundaria.getChildren().clear();
		spSecundaria.getChildren().addAll(limitesRetangulo, vBoxScrollPane);

		return spSecundaria;
	}

	private static void adicionarColunas(TableView<Logs> tvCriada) {
		//ID Logs
		TableColumn<Logs, String> columnIDLogs = new TableColumn<>("Data");
		columnIDLogs.setCellValueFactory(new PropertyValueFactory<>("idData"));
		columnIDLogs.setPrefWidth(columnIDLogs.getText().length() * 40);
		//ID Produto
		TableColumn<Logs, String> columnIDProduto = new TableColumn<>("ID Produto");
		columnIDProduto.setCellValueFactory(new PropertyValueFactory<>("idProduto"));
		columnIDProduto.setPrefWidth(columnIDProduto.getText().length() * 10);
		//Nome Produto
		TableColumn<Logs, String> columnNomeProduto = new TableColumn<>("Identificador");
		columnNomeProduto.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
		columnNomeProduto.setPrefWidth(columnNomeProduto.getText().length() * 20);
		//Quantidade Utilizada
		TableColumn<Logs, Double> columnQuantidadeUtilizada = new TableColumn<>("Quantidade Utilizada");
		columnQuantidadeUtilizada.setCellValueFactory(new PropertyValueFactory<>("quantidadeUtilizada"));
		columnQuantidadeUtilizada.setPrefWidth(columnQuantidadeUtilizada.getText().length() * 10);
		columnQuantidadeUtilizada.setCellFactory(numero -> new TableCell<Logs, Double>(){
			@Override
			protected void updateItem(Double item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText("");
				} else {
					setText(String.format("%.1f", item).replace(",", "."));
				}
			}
		});
		//Usuario
		TableColumn<Logs, String> columnUsuario = new TableColumn<>("Usuário");
		columnUsuario.setCellValueFactory(new PropertyValueFactory<>("nomeUsuario"));
		columnUsuario.setPrefWidth(columnUsuario.getText().length() * 15);

		List<TableColumn<Logs, ?>> listaColunas = new ArrayList<>();
		listaColunas.add(columnIDLogs);
		listaColunas.add(columnIDProduto);
		listaColunas.add(columnNomeProduto);
		listaColunas.add(columnQuantidadeUtilizada);
		listaColunas.add(columnUsuario);

		tvCriada.getColumns().addAll(listaColunas);
		tvCriada.getStyleClass().clear();
		tvCriada.getStyleClass().add("table-view");
	}
}
