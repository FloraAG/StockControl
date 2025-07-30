package projeto.estoque.application.telas.administracao;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import projeto.estoque.application.excel.metodos.file.usuario.FileUsuario;
import projeto.estoque.application.objetos.ListaObjetos;
import projeto.estoque.application.telas.LabelsColorChange;
import projeto.estoque.application.telas.TelaHome;

public class TelaPrincipalAdministracao {

	public static StackPane criarTelaPrincipalAdministracao(StackPane spPrincipal, StackPane spSecundaria, ListaObjetos listaGeral, FileUsuario fileUsuario) {

		//Geral
		Rectangle limitesRetangulo = (Rectangle)spPrincipal.getChildren().get(0);
		double width = limitesRetangulo.getWidth();
		double height = limitesRetangulo.getHeight();

		//Image
		Image iconeHome = new Image(TelaConsultaUsuarios.class.getResourceAsStream("/images/home.png"));
		ImageView ivIcone = new ImageView(iconeHome);
		ivIcone.setFitWidth(width * 0.145);
		ivIcone.setFitHeight(height * 0.062);

		//Labels
		Label lblHome = new Label("", ivIcone);
		Label lblCadastro = new Label("Cadastro");
		Label lblConsulta = new Label("Consulta");
		Label lblAlteracao = new Label("Atualização de Dados");

		//VBox Labels
		VBox vBoxLabels = new VBox();
		vBoxLabels.setAlignment(Pos.CENTER);

		//Inserindo estilos
		lblHome.getStyleClass().addAll("fonte-geral", "bold", "tela-principal", "fundo-obj-geral", "greenI");
		lblHome.setPrefWidth(width * 0.95);
		lblHome.setPrefHeight(height * 0.23);
		lblCadastro.getStyleClass().addAll("fonte-geral", "bold", "tela-principal", "fundo-obj-geral", "greenI");
		lblCadastro.setPrefWidth(width * 0.95);
		lblCadastro.setPrefHeight(height * 0.23);
		lblConsulta.getStyleClass().addAll("fonte-geral", "bold", "tela-principal", "fundo-obj-geral", "greenI");
		lblConsulta.setPrefWidth(width * 0.95);
		lblConsulta.setPrefHeight(height * 0.23);
		lblAlteracao.getStyleClass().addAll("fonte-geral", "bold", "tela-principal", "fundo-obj-geral", "greenI");
		lblAlteracao.setPrefWidth(width * 0.95);
		lblAlteracao.setPrefHeight(height * 0.23);

		//Inserindo Eventos
		Label[] vetorLabels = {lblHome, lblCadastro, lblConsulta,lblAlteracao};
		for(Label label : vetorLabels) {
			label.setStyle("-fx-cursor: hand;");
		}
		lblHome.setOnMouseClicked(_ ->{
			for(Label label : vetorLabels) {
				label.getStyleClass().removeAll("borda", "white");
				label.getStyleClass().add("greenI");
			}
			LabelsColorChange.trocarCorLabel(lblHome);

			TelaHome.criarTelaHome(spSecundaria, listaGeral.getListaEstoque());
		});

		lblCadastro.setOnMouseClicked(_ ->{
			for(Label label : vetorLabels) {
				label.getStyleClass().removeAll("borda", "white");
				label.getStyleClass().add("greenI");
			}
			LabelsColorChange.trocarCorLabel(lblCadastro);

			TelaCadastroUsuario.criarTelaCadastro(spSecundaria, listaGeral.getListaUsuario(), fileUsuario);
		});

		lblConsulta.setOnMouseClicked(_ ->{
			for(Label label : vetorLabels) {
				label.getStyleClass().removeAll("borda", "white");
				label.getStyleClass().add("greenI");
			}
			LabelsColorChange.trocarCorLabel(lblConsulta);

			TelaConsultaUsuarios.criarTelaConsultaUsuarios(spSecundaria, listaGeral.getListaUsuario());
		});

		lblAlteracao.setOnMouseClicked(_ ->{
			for(Label label : vetorLabels) {
				label.getStyleClass().removeAll("borda","white");
				label.getStyleClass().add("greenI");
			}
			LabelsColorChange.trocarCorLabel(lblAlteracao);

			TelaAtualizarUsuario.criarTelaAtualizacao(spSecundaria, listaGeral.getListaUsuario(), fileUsuario);
		});

		//Terminando a StackPane
		vBoxLabels.getChildren().addAll(lblHome, lblCadastro, lblConsulta, lblAlteracao);
		spPrincipal.getChildren().add(vBoxLabels);

		return spPrincipal;
	}
}
