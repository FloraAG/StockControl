package projeto.estoque.application.telas.estoque;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import projeto.estoque.application.objetos.Estoque;

public class TelaConsultaEstoque {
	public static StackPane criarTelaConsulta(StackPane spSecundaria, List<Estoque> listaEstoque) {
		/*----------Variáveis----------*/
		/*-----Gerais-----*/
		Rectangle limitesRetangulo = (Rectangle)spSecundaria.getChildren().get(0);
		double width = limitesRetangulo.getWidth();
		double height = limitesRetangulo.getHeight();
		/*-----Labels-----*/
		Label lblTitulo = new Label("Estoque");
		lblTitulo.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral", "borda", "greenI");
		/*-----Table-----*/
		ObservableList<Estoque> olEstoque = FXCollections.observableArrayList(listaEstoque);
		TableView<Estoque> tvEstoque = new TableView<>(olEstoque);
		adicionarColunas(tvEstoque);
		/*-----ScrollPane-----*/
		ScrollPane scpEstoque = new ScrollPane(tvEstoque);
		scpEstoque.setMaxWidth(width * 0.975);
		scpEstoque.setMaxHeight(height * 0.98);
		/*-----VBox-----*/
		VBox vBoxTelaConsulta = new VBox(20);
		vBoxTelaConsulta.getChildren().addAll(lblTitulo, scpEstoque);
		vBoxTelaConsulta.setAlignment(Pos.CENTER);

		/*----------Terminando a Tela----------*/
		spSecundaria.getChildren().clear();
		spSecundaria.getChildren().addAll(limitesRetangulo, vBoxTelaConsulta);

		return spSecundaria;
	}

	private static void adicionarColunas(TableView<Estoque> tvCriada) {
		//ID
		TableColumn<Estoque, String> columnID = new TableColumn<>("ID Estoque");
		columnID.setCellValueFactory(new PropertyValueFactory<>("idEstoque"));
		columnID.setPrefWidth(columnID.getText().length() * 10);
		//Nome Produto
		TableColumn<Estoque, String> columnNome = new TableColumn<>("Identificador");
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
		columnNome.setPrefWidth(columnNome.getText().length() * 20);
		//Categoria
		TableColumn<Estoque, String> columnCategoria = new TableColumn<>("Categoria");
		columnCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
		columnCategoria.setPrefWidth(columnCategoria.getText().length() * 12);
		//Periculosidade
		TableColumn<Estoque, String> columnPericulosidade = new TableColumn<>("Periculosidade");
		columnPericulosidade.setPrefWidth(columnPericulosidade.getText().length() * 40);
		//Periculosidade Tipo
		TableColumn<Estoque, ArrayList<String>> columnPericulosidadeTipo = new TableColumn<>("Tipo");
		columnPericulosidadeTipo.setPrefWidth(columnPericulosidadeTipo.getText().length() * 20);
		columnPericulosidadeTipo.setCellValueFactory(new PropertyValueFactory<>("periculosidadeGeral"));
		columnPericulosidadeTipo.setCellFactory(column -> new TableCell<Estoque, ArrayList<String>>(){
			@Override
			protected void updateItem(ArrayList<String> item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText(null);
				} else {
					setText(item.toString().replace("[", "").replace("]", ""));
				}
			}
		});
		//Periculosidade Categoria
		TableColumn<Estoque, ArrayList<String>> columnPericulosidadeCategoria = new TableColumn<>("Classificação");
		columnPericulosidadeCategoria.setPrefWidth(columnPericulosidadeCategoria.getText().length() * 18);
		columnPericulosidadeCategoria.setCellValueFactory(new PropertyValueFactory<>("periculosidadeEspecifica"));
		columnPericulosidade.getColumns().add(columnPericulosidadeTipo);
		columnPericulosidade.getColumns().add(columnPericulosidadeCategoria);
		columnPericulosidadeCategoria.setCellFactory(column -> new TableCell<Estoque, ArrayList<String>>(){
			@Override
			protected void updateItem(ArrayList<String> item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText(null);
				} else {
					setText(item.toString(). replace("[", "").replace("]", ""));
				}
			}
		});
		//Localização
		TableColumn<Estoque, String> columnLocalizacao = new TableColumn<>("Localização");
		columnLocalizacao.setCellValueFactory(new PropertyValueFactory<>("localizacao"));
		columnLocalizacao.setPrefWidth(columnLocalizacao.getText().length() * 15);
		//Unidade de Medida
		TableColumn<Estoque, String> columnUnidadeMedida = new TableColumn<>("Unidade de Medida");
		columnUnidadeMedida.setCellValueFactory(new PropertyValueFactory<>("unidadeMedida"));
		columnUnidadeMedida.setPrefWidth(columnUnidadeMedida.getText().length() * 10);
		//Quantidade Mínima
		TableColumn<Estoque, Double> columnQuantidadeMinima = new TableColumn<>("Estoque Mínimo");
		columnQuantidadeMinima.setCellValueFactory(new PropertyValueFactory<>("quantidadeMinima"));
		columnQuantidadeMinima.setPrefWidth(columnQuantidadeMinima.getText().length() * 10);
		columnQuantidadeMinima.setCellFactory(numero -> new TableCell<Estoque, Double>(){
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
		//Quantidade Atual
		TableColumn<Estoque, Double> columnQuantidadeAtual = new TableColumn<>("Estoque Atual");
		columnQuantidadeAtual.setCellValueFactory(new PropertyValueFactory<>("quantidadeAtual"));
		columnQuantidadeAtual.setPrefWidth(columnQuantidadeAtual.getText().length() * 10);
		columnQuantidadeAtual.setCellFactory(numero -> new TableCell<Estoque, Double>(){
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
		//Data Alteração
		Label lblDataAlteração = new Label("Última Alteração");
		Tooltip tooltip = new Tooltip("Realizada por usuário com permissão de administrador.");
		tooltip.getStyleClass().addAll("fundo-obj-geral", "white-full", "borda", "tooltip");
		lblDataAlteração.setTooltip(tooltip);
		lblDataAlteração.setStyle("-fx-underline: true;" + "-fx-cursor: hand;");
		TableColumn<Estoque, String> columnDataAlteracao = new TableColumn<>();
		columnDataAlteracao.setGraphic(lblDataAlteração);
		columnDataAlteracao.setCellValueFactory(new PropertyValueFactory<>("dataAlteracao"));
		columnDataAlteracao.setPrefWidth(lblDataAlteração.getText().length() * 10);
		//Usuario
		TableColumn<Estoque, String> columnUsuario = new TableColumn<>("Usuario");
		columnUsuario.setCellValueFactory(new PropertyValueFactory<>("nomeUsuario"));
		columnUsuario.setPrefWidth(columnUsuario.getText().length() * 15);

		List<TableColumn<Estoque, ?>> listaColunas = new ArrayList<>();
		listaColunas.add(columnID);
		listaColunas.add(columnNome);
		listaColunas.add(columnCategoria);
		listaColunas.add(columnPericulosidade);
		listaColunas.add(columnLocalizacao);
		listaColunas.add(columnUnidadeMedida);
		listaColunas.add(columnQuantidadeMinima);
		listaColunas.add(columnQuantidadeAtual);
		listaColunas.add(columnDataAlteracao);
		listaColunas.add(columnUsuario);

		tvCriada.getColumns().addAll(listaColunas);
		tvCriada.getStyleClass().clear();
		tvCriada.getStyleClass().add("table-view");
	}
}
