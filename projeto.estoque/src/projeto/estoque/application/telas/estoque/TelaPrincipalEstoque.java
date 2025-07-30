package projeto.estoque.application.telas.estoque;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import projeto.estoque.application.objetos.ListaFiles;
import projeto.estoque.application.objetos.ListaObjetos;
import projeto.estoque.application.objetos.Usuario;
import projeto.estoque.application.telas.LabelsColorChange;
import projeto.estoque.application.telas.TelaHome;
import projeto.estoque.application.telas.administracao.TelaConsultaUsuarios;

public class TelaPrincipalEstoque {
	public static StackPane criarTelaPrincipalEstoque(StackPane spPrincipal, StackPane spSecundaria, ListaObjetos listaGeral,
			ListaFiles listaFiles, Usuario usuario) {

		//Geral
		Rectangle limitesRetangulo = (Rectangle)spPrincipal.getChildren().get(0);
		double width = limitesRetangulo.getWidth();
		double height = limitesRetangulo.getHeight();

		//Image
		Image iconeHome = new Image(TelaConsultaUsuarios.class.getResourceAsStream("/images/home.png"));
		ImageView ivIcone = new ImageView(iconeHome);
		ivIcone.setFitWidth(width * 0.145);
		ivIcone.setFitHeight(height * 0.06);

		//Labels
		Label lblHome = new Label("", ivIcone);
		Label lblCadastro = new Label("Cadastro - Estoque");
		Label lblConsulta = new Label("Consulta - Estoque");
		Label lblAlteracao = new Label("Atualização dos Itens");
		Label lblDeclararItens = new Label("Declarar Uso");
		Label lblLogs = new Label("Resgistro de Uso");

		//VBox Labels
		VBox vBoxLabels = new VBox();
		vBoxLabels.setAlignment(Pos.CENTER);

		//Inserindo estilos
		lblHome.getStyleClass().addAll("fonte-geral", "bold", "tela-principal", "fundo-obj-geral", "greenI");
		lblCadastro.getStyleClass().addAll("fonte-geral", "bold", "tela-principal", "fundo-obj-geral", "greenI");
		lblConsulta.getStyleClass().addAll("fonte-geral", "bold", "tela-principal", "fundo-obj-geral", "greenI");
		lblAlteracao.getStyleClass().addAll("fonte-geral", "bold", "tela-principal", "fundo-obj-geral", "greenI");
		lblDeclararItens.getStyleClass().addAll("fonte-geral", "bold", "tela-principal", "fundo-obj-geral", "greenI");
		lblLogs.getStyleClass().addAll("fonte-geral", "bold", "tela-principal", "fundo-obj-geral", "greenI");

		//Definindo Tamanho das Labels Principais
		if(usuario.isPermAdministrador()) {
			lblHome.setPrefWidth(width * 0.95);
			lblHome.setPrefHeight(height * 0.15);
			lblCadastro.setPrefWidth(width * 0.95);
			lblCadastro.setPrefHeight(height * 0.15);
			lblConsulta.setPrefWidth(width * 0.95);
			lblConsulta.setPrefHeight(height * 0.15);
			lblAlteracao.setPrefWidth(width * 0.95);
			lblAlteracao.setPrefHeight(height * 0.15);
			lblDeclararItens.setPrefWidth(width * 0.95);
			lblDeclararItens.setPrefHeight(height *0.15);
			lblLogs.setPrefWidth(width * 0.95);
			lblLogs.setPrefHeight(height * 0.15);
			vBoxLabels.getChildren().addAll(lblHome, lblCadastro, lblConsulta, lblAlteracao, lblDeclararItens, lblLogs);
		} else {
			lblHome.setPrefWidth(width * 0.95);
			lblHome.setPrefHeight(height * 0.24);
			lblConsulta.setPrefWidth(width * 0.95);
			lblConsulta.setPrefHeight(height * 0.24);
			lblDeclararItens.setPrefWidth(width * 0.95);
			lblDeclararItens.setPrefHeight(height * 0.24);
			lblLogs.setPrefWidth(width * 0.95);
			lblLogs.setPrefHeight(height * 0.24);
			vBoxLabels.getChildren().addAll(lblHome, lblConsulta, lblDeclararItens, lblLogs);
		}

		//Inserindo Eventos
		Label[] vetorLabels = {lblHome, lblCadastro, lblConsulta, lblAlteracao, lblDeclararItens, lblLogs};
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

			TelaCadastroEstoque.criarTelaCadastroEstoque(spSecundaria, listaGeral.getListaEstoque(), listaFiles.getfEstoque(), usuario);
		});

		lblConsulta.setOnMouseClicked(_ ->{
			for(Label label : vetorLabels) {
				label.getStyleClass().removeAll("borda", "white");
				label.getStyleClass().add("greenI");
			}
			LabelsColorChange.trocarCorLabel(lblConsulta);

			TelaConsultaEstoque.criarTelaConsulta(spSecundaria, listaGeral.getListaEstoque());
		});

		lblAlteracao.setOnMouseClicked(_ -> {
			for(Label label : vetorLabels) {
				label.getStyleClass().removeAll("borda","white");
				label.getStyleClass().add("greenI");
			}
			LabelsColorChange.trocarCorLabel(lblAlteracao);

			TelaAtualizarEstoque.criarTelaAtualizacao(spSecundaria, listaGeral.getListaEstoque(), listaFiles.getfEstoque(), usuario);
		});

		lblDeclararItens.setOnMouseClicked(_ -> {
			for(Label label : vetorLabels) {
				label.getStyleClass().removeAll("borda","white");
				label.getStyleClass().add("greenI");
			}
			LabelsColorChange.trocarCorLabel(lblDeclararItens);

			TelaDeclararUso.criarTelaDeclararUso(spSecundaria, listaGeral.getListaEstoque(), listaGeral.getListaLogs(),
					listaFiles.getfEstoque(), listaFiles.getfLogs(), usuario);

		});

		lblLogs.setOnMouseClicked(_ -> {
			for(Label label : vetorLabels) {
				label.getStyleClass().removeAll("borda","white");
				label.getStyleClass().add("greenI");
			}
			LabelsColorChange.trocarCorLabel(lblLogs);

			TelaConsultaLogs.criarTelaConsultaLogs(spSecundaria, listaGeral.getListaLogs(), listaFiles.getfLogs(), usuario);
		});

		//Terminando a StackPane
		spPrincipal.getChildren().clear();
		spPrincipal.getChildren().addAll(limitesRetangulo, vBoxLabels);

		return spPrincipal;
	}
}
