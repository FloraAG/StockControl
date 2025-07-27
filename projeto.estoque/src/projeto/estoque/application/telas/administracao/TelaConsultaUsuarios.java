package projeto.estoque.application.telas.administracao;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import projeto.estoque.application.objetos.Usuario;

public class TelaConsultaUsuarios {
	public static StackPane criarTelaConsultaUsuarios(StackPane spSecundaria, List<Usuario> listaUsuario) {
		/*----------Variáveis----------*/
		/*-----Gerais-----*/
		Rectangle limitesRetangulo = (Rectangle)spSecundaria.getChildren().get(0);
		double width = limitesRetangulo.getWidth();
		double height = limitesRetangulo.getHeight();
		/*-----Labels-----*/
		Label lblTitulo = new Label("Usuários Cadastrados");
		lblTitulo.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral", "borda", "greenI");
		/*-----Table-----*/
		ObservableList<Usuario> olUsuario = FXCollections.observableArrayList(listaUsuario);
		FilteredList<Usuario> flUsuario = new FilteredList<>(olUsuario);
		flUsuario.setPredicate(e -> {
			return e.getIdUsuario() != null && !e.getIdUsuario().equals("0");
		});
		TableView<Usuario> tvUsuario = new TableView<>(flUsuario);
		adicionarColunas(tvUsuario);
		/*-----ScrollPane-----*/
		ScrollPane scpUsuario = new ScrollPane(tvUsuario);
		scpUsuario.setMaxWidth(width * 0.92);
		scpUsuario.setMaxHeight(height * 0.92);
		/*-----VBox-----*/
		VBox vBoxTelaConsulta = new VBox(20);
		vBoxTelaConsulta.getChildren().addAll(lblTitulo, scpUsuario);
		vBoxTelaConsulta.setAlignment(Pos.CENTER);

		/*----------Terminando a Tela----------*/
		spSecundaria.getChildren().clear();
		spSecundaria.getChildren().addAll(limitesRetangulo, vBoxTelaConsulta);

		return spSecundaria;
	}

	private static void adicionarColunas(TableView<Usuario> tvCriada) {
		//ID
		TableColumn<Usuario, String> columnID = new TableColumn<>("ID Usuario");
		columnID.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
		columnID.setPrefWidth(columnID.getText().length() * 10);
		//Nome Usuario
		TableColumn<Usuario, String> columnNome = new TableColumn<>("Usuário");
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nomeUsuario"));
		columnNome.setPrefWidth(columnNome.getText().length() * 20);
		//Orientação
		TableColumn<Usuario, String> columnOrientacao = new TableColumn<>("Orientador(a)");
		columnOrientacao.setCellValueFactory(new PropertyValueFactory<>("orientacao"));
		columnOrientacao.setPrefWidth(columnOrientacao.getText().length() * 20);
		//Telefone
		TableColumn<Usuario, String> columnTelefone = new TableColumn<>("Telefone");
		columnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		columnTelefone.setPrefWidth(columnTelefone.getText().length() * 10);
		//E-mail
		TableColumn<Usuario, String> columnEmail = new TableColumn<>("E-mail");
		columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		columnEmail.setPrefWidth(columnEmail.getText().length() * 30);
		//Data de Entrada
		TableColumn<Usuario, String> columnDataEntrada = new TableColumn<>("Entrada");
		columnDataEntrada.setCellValueFactory(new PropertyValueFactory<>("dataEntrada"));
		columnDataEntrada.setPrefWidth(columnDataEntrada.getText().length() * 30);

		List<TableColumn<Usuario, ?>> listaColunas = new ArrayList<>();
		listaColunas.add(columnID);
		listaColunas.add(columnNome);
		listaColunas.add(columnOrientacao);
		listaColunas.add(columnTelefone);
		listaColunas.add(columnEmail);
		listaColunas.add(columnDataEntrada);

		tvCriada.getColumns().addAll(listaColunas);
		tvCriada.getStyleClass().clear();
		tvCriada.getStyleClass().add("table-view");
	}
}
