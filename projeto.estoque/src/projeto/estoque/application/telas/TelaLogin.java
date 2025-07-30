package projeto.estoque.application.telas;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import projeto.estoque.application.dados.metodos.CriptografiaSenha;
import projeto.estoque.application.excel.metodos.file.FileOpen;
import projeto.estoque.application.excel.metodos.file.estoque.FileEstoque;
import projeto.estoque.application.excel.metodos.file.logs.FileLogs;
import projeto.estoque.application.excel.metodos.file.usuario.FileUsuario;
import projeto.estoque.application.objetos.ListaFiles;
import projeto.estoque.application.objetos.ListaObjetos;
import projeto.estoque.application.objetos.ListaWorkbook;
import projeto.estoque.application.objetos.Usuario;

public class TelaLogin {

	public static StackPane criarTelaLogin(StackPane spInicial, Rectangle2D limiteTela) {

		/*----------Variáveis----------*/
		/*-----Geral-----*/
		final double width = limiteTela.getWidth();
		final double height = limiteTela.getHeight();
		/*-----Listas/Excel-----*/
		ListaWorkbook listaWorkbook = new ListaWorkbook();
		listaWorkbook.setWorkbookUsuario(new XSSFWorkbook());
		listaWorkbook.setWorkbookEstoque(new XSSFWorkbook());
		listaWorkbook.setWorkbookLogs(new XSSFWorkbook());
		ListaFiles listaFiles = new ListaFiles();
		listaFiles.setfUsuario(new FileUsuario());
		listaFiles.setfEstoque(new FileEstoque());
		listaFiles.setfLogs(new FileLogs());
		FileOpen.criarFile(listaFiles);
		FileOpen.abrirFile(listaWorkbook);
		ListaObjetos listaGeral = new ListaObjetos();
		listaGeral.setListaUsuario(listaFiles.getfUsuario().transferirFileToList(
				listaWorkbook.getWorkbookUsuario(), listaGeral.getListaUsuario()));
		listaGeral.setListaEstoque(listaFiles.getfEstoque().transferirFileToList(
				listaWorkbook.getWorkbookEstoque(), listaGeral.getListaEstoque()));
		listaGeral.setListaLogs(listaFiles.getfLogs().transferirFileToList(
				listaWorkbook.getWorkbookLogs(), listaGeral.getListaLogs()));
		/*----Tela-----*/
		MetodoTrocaTela mtt = new MetodoTrocaTela();
		//Rectangle Login
		final Rectangle retLogin = new Rectangle(width * 0.25, (height * 0.30));
		retLogin.getStyleClass().addAll("fundo-ret-geral", "ret-green");
		//Label
		final Label lblUsuario = new Label("Usuário:");
		final Label lblSenha = new Label("Senha:");
		final Label lblSituacao = new Label();
		lblUsuario.getStyleClass().addAll("fonte-geral", "bold");
		lblSenha.getStyleClass().addAll("fonte-geral", "bold");
		lblSituacao.getStyleClass().addAll("fonte-geral", "bold");
		//TextField
		final TextField txtUsuario = new TextField();
		txtUsuario.getStyleClass().addAll("fonte-geral", "fundo-obj-geral", "borda");
		txtUsuario.setPrefWidth(retLogin.getWidth() * 0.60);
		//PasswordField
		final PasswordField pfSenha = new PasswordField();
		pfSenha.getStyleClass().addAll("fonte-geral", "fundo-obj-geral", "borda");
		pfSenha.setPrefWidth(retLogin.getWidth() * 0.60);
		pfSenha.setTranslateX(+5);
		//Button
		final Button btLogin = new Button("Entrar");
		btLogin.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral", "borda", "greenII");
		//HBox
		final HBox hBoxUsuario = new HBox(10);
		final HBox hBoxSenha = new HBox(10);
		hBoxUsuario.getChildren().addAll(lblUsuario, txtUsuario);
		hBoxUsuario.setAlignment(Pos.CENTER);
		hBoxSenha.getChildren().addAll(lblSenha, pfSenha);
		hBoxSenha.setAlignment(Pos.CENTER);
		//VBox
		final VBox vBoxLogin = new VBox(retLogin.getHeight() * 0.08);
		vBoxLogin.getChildren().addAll(hBoxUsuario, hBoxSenha, lblSituacao, btLogin);
		vBoxLogin.setAlignment(Pos.CENTER);

		/*----------Colocando Ações------*/
		txtUsuario.setOnKeyPressed(e ->{
			if(e.getCode() == KeyCode.ENTER) {
				btLogin.fire();
			}
		});
		pfSenha.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.ENTER) {
				btLogin.fire();
			}
		});
		btLogin.setOnAction(_ -> {
			spInicial.setCursor(Cursor.WAIT);
			btLogin.setCursor(Cursor.WAIT);
			lblSituacao.setText("");

			Usuario usuarioAux = listaFiles.getfUsuario().consultarUnicoItem(listaGeral.getListaUsuario(), txtUsuario.getText().toUpperCase());

			if(usuarioAux != null) {
				if(CriptografiaSenha.verificarSenhas(pfSenha.getText(), usuarioAux.getSenhaUsuario())) {
					mtt.iniciar(spInicial);
					mtt.mudarTela();
					TelaPrincipal.criarTelaPrincipal(spInicial, listaGeral, listaWorkbook, listaFiles, limiteTela, usuarioAux);
					spInicial.setCursor(Cursor.DEFAULT);
					btLogin.setCursor(Cursor.DEFAULT);
				} else {
					lblSituacao.setText("Senha Inválida!");
					spInicial.setCursor(Cursor.DEFAULT);
					btLogin.setCursor(Cursor.DEFAULT);
				}
			} else {
				lblSituacao.setText("Usuário Inválido!");
				spInicial.setCursor(Cursor.DEFAULT);
				btLogin.setCursor(Cursor.DEFAULT);
			}
		});

		/*---------Finalizando a StackPane----------*/
		spInicial.getChildren().addAll(retLogin, vBoxLogin);

		return spInicial;
	}
}
