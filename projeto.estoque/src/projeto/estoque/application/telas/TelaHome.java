package projeto.estoque.application.telas;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
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
import projeto.estoque.application.objetos.Estoque;

public class TelaHome {
	public static StackPane criarTelaHome(StackPane spSecundaria, List<Estoque> listaEstoque) {

		//Geral
		Rectangle limitesRetangulo = (Rectangle)spSecundaria.getChildren().get(0);
		double width = limitesRetangulo.getWidth();
		double height = limitesRetangulo.getHeight() ;

		//Ícones
		Image icAtencaoI = new Image(TelaHome.class.getResourceAsStream("/images/exclamation-yellow.png"));
		Image icAtencaoII = new Image(TelaHome.class.getResourceAsStream("/images/exclamation-red.png"));
		ImageView ivAtencaoI = new ImageView(icAtencaoI);
		ImageView ivAtencaoII = new ImageView(icAtencaoII);
		ivAtencaoI.setFitWidth(width * 0.02);
		ivAtencaoI.setFitHeight(height * 0.03);
		ivAtencaoII.setFitWidth(width * 0.02);
		ivAtencaoII.setFitHeight(height * 0.03);

		//Labels
		Label lblAtencaoI = new Label("", ivAtencaoI);
		Label lblAtencaoII = new Label("", ivAtencaoII);
		Label lblEstoqueI = new Label("Estoque em defasagem");
		Label lblEstoqueII = new Label("Estoque defasado");
		lblEstoqueI.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblEstoqueII.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");

		//HBox
		HBox hBoxEstoqueI = new HBox(5);
		HBox hBoxEstoqueII = new HBox(5);
		hBoxEstoqueI.getChildren().addAll(lblAtencaoI, lblEstoqueI);
		hBoxEstoqueII.getChildren().addAll(lblAtencaoII, lblEstoqueII);
		hBoxEstoqueI.setAlignment(Pos.CENTER);
		hBoxEstoqueII.setAlignment(Pos.CENTER);

		//Tables
		ObservableList<Estoque> olEstoqueI = FXCollections.observableArrayList(listaEstoque);
		ObservableList<Estoque> olEstoqueII = FXCollections.observableArrayList(listaEstoque);
		FilteredList<Estoque> flEstoqueI = new FilteredList<>(olEstoqueI);
		FilteredList<Estoque> flEstoqueII = new FilteredList<>(olEstoqueII);
		flEstoqueI.setPredicate(e -> {
			return e.getIdEstoque() != null &&
					e.getQuantidadeAtual() <= (e.getQuantidadeMinima() * 2) && e.getQuantidadeAtual() > e.getQuantidadeMinima();
		});

		flEstoqueII.setPredicate(e -> {
			return e.getIdEstoque() != null &&
					e.getQuantidadeAtual() <= e.getQuantidadeMinima();
		});
		TableView<Estoque> tvEstoqueI = new TableView<>(flEstoqueI);
		TableView<Estoque> tvEstoqueII = new TableView<>(flEstoqueII);
		adicionarColunas(tvEstoqueI);
		adicionarColunas(tvEstoqueII);
		tvEstoqueI.refresh();
		tvEstoqueII.refresh();

		//ScrollPane
		ScrollPane scpEstoqueI = new ScrollPane(tvEstoqueI);
		ScrollPane scpEstoqueII = new ScrollPane(tvEstoqueII);
		scpEstoqueI.setMaxWidth(width * 0.92);
		scpEstoqueII.setMaxWidth(width * 0.92);
		scpEstoqueI.setMaxHeight(height * 0.38);
		scpEstoqueII.setMaxHeight(height * 0.38);

		//VBox
		VBox vBoxScrollPane = new VBox(10);
		vBoxScrollPane.getChildren().addAll(hBoxEstoqueI, scpEstoqueI, hBoxEstoqueII, scpEstoqueII);
		vBoxScrollPane.setAlignment(Pos.CENTER);

		//Terminando a StackPane
		spSecundaria.getChildren().clear();
		spSecundaria.getChildren().addAll(limitesRetangulo, vBoxScrollPane);

		return spSecundaria;
	}

	private static void adicionarColunas(TableView<Estoque> tvCriada) {
		//ID
		TableColumn<Estoque, String> columnID = new TableColumn<>("ID Produto");
		columnID.setCellValueFactory(new PropertyValueFactory<>("idEstoque"));
		columnID.setPrefWidth(columnID.getText().length() * 10);
		//Nome Produto
		TableColumn<Estoque, String> columnNome = new TableColumn<>("Identificador");
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
		columnNome.setPrefWidth(columnNome.getText().length() * 20);
		//Categoria
		TableColumn<Estoque, String> columnCategoria = new TableColumn<>("Categoria");
		columnCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
		columnCategoria.setPrefWidth(columnCategoria.getText().length() * 15);
		//Unidade de Medida
		TableColumn<Estoque, String> columnUnidadeMedida = new TableColumn<>("Unidade de Medida");
		columnUnidadeMedida.setCellValueFactory(new PropertyValueFactory<>("unidadeMedida"));
		columnUnidadeMedida.setPrefWidth(columnUnidadeMedida.getText().length() * 10);
		//Localização
		TableColumn<Estoque, String> columnLocalizacao = new TableColumn<>("Localização");
		columnLocalizacao.setCellValueFactory(new PropertyValueFactory<>("localizacao"));
		columnLocalizacao.setPrefWidth(columnLocalizacao.getText().length() * 15);
		//Quantidade Minima
		TableColumn<Estoque, Double> columnQuantMinima = new TableColumn<>("Estoque Mínimo");
		columnQuantMinima.setCellValueFactory(new PropertyValueFactory<>("quantidadeMinima"));
		columnQuantMinima.setCellFactory(_ -> new TableCell<Estoque, Double>(){
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
		columnQuantMinima.setPrefWidth(columnQuantMinima.getText().length() * 10);
		//Quantidade Atual
		TableColumn<Estoque, Double> columnQuantAtual = new TableColumn<>("Estoque Atual");
		columnQuantAtual.setCellValueFactory(new PropertyValueFactory<>("quantidadeAtual"));
		columnQuantAtual.setCellFactory(_ -> new TableCell<Estoque, Double>(){
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
		columnQuantAtual.setPrefWidth(columnQuantAtual.getText().length() * 10);

		List<TableColumn<Estoque, ?>> listaColunas = new ArrayList<>();
		listaColunas.add(columnID);
		listaColunas.add(columnNome);
		listaColunas.add(columnUnidadeMedida);
		listaColunas.add(columnQuantMinima);
		listaColunas.add(columnQuantAtual);
		listaColunas.add(columnCategoria);
		listaColunas.add(columnLocalizacao);

		tvCriada.getColumns().addAll(listaColunas);
		tvCriada.getStyleClass().clear();
		tvCriada.getStyleClass().add("table-view");
	}
}
