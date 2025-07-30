package projeto.estoque.application.telas;


import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import projeto.estoque.application.objetos.ListaFiles;
import projeto.estoque.application.objetos.ListaObjetos;
import projeto.estoque.application.objetos.ListaWorkbook;
import projeto.estoque.application.objetos.Usuario;
import projeto.estoque.application.telas.administracao.TelaPrincipalAdministracao;
import projeto.estoque.application.telas.estoque.TelaPrincipalEstoque;

public class TelaPrincipal {

	static boolean telaAdministracao;
	static StackPane spPrincipal = new StackPane();
	static StackPane spSecundaria = new StackPane();

	public static StackPane criarTelaPrincipal(StackPane spAnterior, ListaObjetos listaGeral, ListaWorkbook listaWorkbook, ListaFiles listaFiles, Rectangle2D limitesTela, Usuario usuarioAux) {

		/*----------Variáveis----------*/
		/*-----Geral-----*/
		double width = limitesTela.getWidth();
		double height = limitesTela.getHeight();
		/*-----Cumprimento e Sair-----*/
		Label lblCumprimento = new Label("Olá, " + usuarioAux.getNomeUsuario());
		Label lblSair = new Label("Sair");
		Label lblAdmEstoque = new Label();
		HBox hBoxCumprimento = new HBox(5);
		/*-----Tela Principal-----*/
		Rectangle retPrincipal = new Rectangle(width * 0.22, height * 0.85);
		Rectangle retSecundario = new Rectangle(width * 0.75, height * 0.85);
		HBox hBoxStackPanes = new HBox(10);
		VBox vBoxPrincipal = new VBox();
		hBoxCumprimento.setMaxWidth(retSecundario.getWidth());

		/*----------Inserindo Styles----------*/
		/*-----HBox-----*/
		hBoxCumprimento.getStyleClass().addAll("fundo-obj-geral", "borda");
		hBoxCumprimento.setStyle("-fx-background-color: rgba(255,255,255,0.85);");
		/*----Retângulos----*/
		retPrincipal.getStyleClass().addAll("fundo-ret-geral", "ret-green");
		retSecundario.getStyleClass().addAll("fundo-ret-geral", "ret-white");
		/*-----Labels-----*/
		lblCumprimento.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblSair.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblSair.setCursor(Cursor.HAND);
		lblAdmEstoque.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral", "white");
		lblAdmEstoque.setCursor(Cursor.HAND);

		/*-----Definindo o Padrão da tela-----*/
		if(usuarioAux.getNomeUsuario().equals("ADMINISTRADOR") || !usuarioAux.isPermAdministrador()) {
			hBoxCumprimento.getChildren().addAll(lblCumprimento, lblSair);
		} else {
			hBoxCumprimento.getChildren().addAll(lblCumprimento, lblAdmEstoque, lblSair);
		}

		if(telaAdministracao || usuarioAux.getNomeUsuario().equals("ADMINISTRADOR")) {
			lblAdmEstoque.setText("Estoque");
			spPrincipal.getChildren().clear();
			spSecundaria.getChildren().clear();
			spPrincipal.getChildren().add(retPrincipal);
			spSecundaria.getChildren().add(retSecundario);
			TelaPrincipalAdministracao.criarTelaPrincipalAdministracao(spPrincipal, spSecundaria, listaGeral, listaFiles.getfUsuario());
			hBoxStackPanes.getChildren().addAll(spPrincipal, spSecundaria);
		} else {
			lblAdmEstoque.setText("Administração");
			spPrincipal.getChildren().clear();
			spSecundaria.getChildren().clear();
			spPrincipal.getChildren().add(retPrincipal);
			spSecundaria.getChildren().add(retSecundario);
			TelaPrincipalEstoque.criarTelaPrincipalEstoque(spPrincipal, spSecundaria, listaGeral, listaFiles, usuarioAux);
			hBoxStackPanes.getChildren().addAll(spPrincipal, spSecundaria);
		}

		/*----------Inserindo Evento Label Cumprimento----------*/
		if(!usuarioAux.getNomeUsuario().equals("ADMINISTRADOR")) {
			lblCumprimento.setCursor(Cursor.HAND);
			lblCumprimento.setOnMousePressed(_ ->{
				spSecundaria.getChildren().clear();
				spSecundaria.getChildren().add(retSecundario);
				TelaPerfil.criarAreaPerfil(spSecundaria, listaGeral.getListaUsuario(), listaFiles.getfUsuario(), usuarioAux);
			});
		}

		/*----------Inserindo Evento Label Sair----------*/
		lblSair.setOnMousePressed(_ ->{
			spAnterior.getChildren().clear();
			TelaLogin.criarTelaLogin(spAnterior, limitesTela);
			telaAdministracao = false;
		});

		/*----------Inserindo Evento Label AdmEstoque----------*/
		lblAdmEstoque.setOnMousePressed(_ ->{
			telaAdministracao = !telaAdministracao;
			spAnterior.getChildren().clear();
			spPrincipal.getChildren().clear();
			spSecundaria.getChildren().clear();
			criarTelaPrincipal(spAnterior, listaGeral, listaWorkbook, listaFiles, limitesTela, usuarioAux);
		});

		/*----------Definindo as HBox e VBox----------*/
		/*-----HBox Cumprimento-----*/
		hBoxCumprimento.setAlignment(Pos.CENTER_RIGHT);
		/*-----HBox StackPanes-----*/
		hBoxStackPanes.setAlignment(Pos.CENTER);
		/*-----VBox Principal-----*/
		vBoxPrincipal.getChildren().addAll(hBoxCumprimento, hBoxStackPanes);
		vBoxPrincipal.setTranslateY(height * 0.02);
		hBoxCumprimento.setTranslateX(retSecundario.getWidth() * 0.32);;
		/*----------StackPane----------*/
		spAnterior.getChildren().addAll(vBoxPrincipal);

		return spAnterior;
	}
}
