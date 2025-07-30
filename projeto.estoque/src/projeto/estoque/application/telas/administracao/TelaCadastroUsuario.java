package projeto.estoque.application.telas.administracao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import projeto.estoque.application.dados.metodos.Consulta;
import projeto.estoque.application.dados.metodos.CriptografiaSenha;
import projeto.estoque.application.dados.metodos.DadosInseridos;
import projeto.estoque.application.excel.metodos.file.usuario.FileUsuario;
import projeto.estoque.application.objetos.Usuario;

public class TelaCadastroUsuario {

	public static StackPane criarTelaCadastro(StackPane spSecundaria, List<Usuario> listaUsuario, FileUsuario fileUsuario) {

		/*----------Variáveis----------*/
		/*-----Gerais-----*/
		Rectangle limitesRetangulo = (Rectangle)spSecundaria.getChildren().get(0);
		double width = limitesRetangulo.getWidth();
		double height = limitesRetangulo.getHeight();
		/*-----Imagem-----*/
		Image imagem = new Image(TelaCadastroUsuario.class.getResourceAsStream("/images/fundo-secundario-II.png"));
		ImageView ivFundo = new ImageView(imagem);
		ivFundo.setFitWidth(width* 0.25);
		ivFundo.setFitHeight(height * 0.33);
		/*-----Labels-----*/
		Label lblNomeUsuario = new Label("Usuário:");
		Label lblSenhaI = new Label("Senha:");
		Label lblSenhaII = new Label("Confirmar Senha:");
		Label lblOrientacao = new Label("Orientador(a):");
		Label lblTelefone = new Label("Telefone:");
		Label lblEmail = new Label("E-mail:");
		Label lblPermisoes = new Label("Permissões do Usuário");
		Label lblSituacaoCadastro = new Label();
		/*-----Fields e Boxes-----*/
		TextField tfNomeUsuario = new TextField();
		PasswordField pfSenhaI = new PasswordField();
		PasswordField pfSenhaII = new PasswordField();
		TextField tfOrientacao = new TextField();
		TextField tfTelefone = new TextField();
		TextField tfEmail = new TextField();
		CheckBox cbAdministrador = new CheckBox("Permissão de Administrador");
		CheckBox cbUsuario = new CheckBox("Permissão de Usuário");
		/*-----Botões-----*/
		Button btLimpar = new Button("Limpar Campos");
		Button btCadastrar = new Button("Finalizar Cadastro");
		/*-----HBoxes-----*/
		HBox hBoxGeral = new HBox(10);
		HBox hBoxUsuario = new HBox(5);
		HBox hBoxSenha = new HBox(5);
		HBox hBoxOrientacao = new HBox(5);
		HBox hBoxTelefone = new HBox(5);
		HBox hBoxEmail = new HBox(5);
		HBox hBoxPermissao = new HBox(50);
		HBox hBoxBotoes = new HBox(width * 0.33);
		/*-----VBox-----*/
		VBox vBoxTelaCadastro = new VBox(30);

		/*----------Inserindo Estilos----------*/
		/*-----Labels-----*/
		lblNomeUsuario.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblSenhaI.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblSenhaII.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblOrientacao.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblTelefone.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblEmail.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblPermisoes.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblSituacaoCadastro.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		/*-----Fields e Boxes-----*/
		tfNomeUsuario.getStyleClass().addAll("fonte-geral", "fundo-obj-geral", "borda");
		pfSenhaI.getStyleClass().addAll("fonte-geral", "fundo-obj-geral", "borda");
		pfSenhaI.setTooltip(new Tooltip("A senha deverá conter no mínimo seis caracteres!"));
		pfSenhaII.getStyleClass().addAll("fonte-geral", "fundo-obj-geral", "borda");
		pfSenhaII.setTooltip(new Tooltip("A senha deverá conter no mínimo seis caracteres!"));
		tfOrientacao.getStyleClass().addAll("fonte-geral", "fundo-obj-geral", "borda");
		tfOrientacao.setPrefWidth(width * 0.5);
		tfTelefone.getStyleClass().addAll("fonte-geral", "fundo-obj-geral", "borda");
		tfEmail.getStyleClass().addAll("fonte-geral", "fundo-obj-geral", "borda");
		tfEmail.setPrefWidth(width * 0.5);
		cbAdministrador.getStyleClass().addAll("fonte-geral", "check-box");
		cbUsuario.getStyleClass().addAll("fonte-geral", "check-box");
		cbAdministrador.setOnAction(_ -> {
			if(cbAdministrador.isSelected()) {
				cbUsuario.setSelected(true);
			} else {
				cbUsuario.setSelected(false);
			}
		});
		/*-----Botões-----*/
		btLimpar.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral", "borda", "greenII");
		btCadastrar.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral", "borda", "greenII");

		/*----------Inserindo Actions----------*/
		spSecundaria.setOnMouseClicked(_ -> {
			lblSituacaoCadastro.setText("");
		});
		TextField[] listaFields = {tfNomeUsuario, pfSenhaI, pfSenhaII, tfOrientacao, tfTelefone, tfEmail};
		for(TextField textField : listaFields) {
			textField.setOnMouseClicked(_ -> {
				lblSituacaoCadastro.setText("");
			});
		}
		btLimpar.setOnAction(_ -> {
			tfNomeUsuario.setText("");
			pfSenhaI.setText("");
			pfSenhaII.setText("");
			tfOrientacao.setText("");
			tfTelefone.setText("");
			tfEmail.setText("");
			cbAdministrador.setSelected(false);
			cbUsuario.setSelected(false);
			lblSituacaoCadastro.setText("Campos Limpos!");
		});
		btCadastrar.setOnAction(_ ->{
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			String data = LocalDateTime.now().format(formato);
			Usuario usuarioCriado = new Usuario();

			if(tfNomeUsuario.getText().isBlank() || pfSenhaI.getText().isBlank() || pfSenhaII.getText().isBlank() ||
					tfTelefone.getText().isBlank() || tfEmail.getText().isBlank() || !cbUsuario.isSelected()) {
				lblSituacaoCadastro.setText("Campo(s) vazio(s)!");
			} else if(Consulta.consultarUsuarioExiste(listaUsuario, tfNomeUsuario.getText().toUpperCase())) {
				lblSituacaoCadastro.setText("Usuário já existe!");
			} else if(!pfSenhaI.getText().equals(pfSenhaII.getText()) || pfSenhaI.getText().length() < 6) {
				lblSituacaoCadastro.setText("Senha inválida ou não coincidem");
			} else if(DadosInseridos.corrigeTelefone(tfTelefone.getText()) == null) {
				lblSituacaoCadastro.setText("Telefone inválido!");
			} else if(!DadosInseridos.checarEmailValido(tfEmail.getText())) {
				lblSituacaoCadastro.setText("E-mail inválido!");
			} else {
				usuarioCriado.setIdUsuario(DadosInseridos.retornarIDUsuario(listaUsuario, tfOrientacao.getText()));
				usuarioCriado.setNomeUsuario(tfNomeUsuario.getText().toUpperCase());
				usuarioCriado.setSenhaUsuario(CriptografiaSenha.criptografarSenha(pfSenhaI.getText()));
				usuarioCriado.setOrientacao(tfOrientacao.getText());
				usuarioCriado.setTelefone(DadosInseridos.corrigeTelefone(tfTelefone.getText()));
				usuarioCriado.setEmail(tfEmail.getText());
				usuarioCriado.setPermAdministrador(cbAdministrador.isSelected());
				usuarioCriado.setPermUsuario(true);
				usuarioCriado.setDataEntrada(data);

				/*----Ordenando a Lista----*/
				List<Usuario> listaAux = new ArrayList<>();
				for(Usuario usuario : listaUsuario) {
					if(usuario.getIdUsuario() == null) {
						listaAux.add(usuario);
						listaUsuario.remove(usuario);
					}
				}
				listaUsuario.add(usuarioCriado);
				listaUsuario.sort(Comparator.comparing(Usuario::getIdUsuario).thenComparing(Usuario::getNomeUsuario));
				for(Usuario usuario : listaUsuario) {
					listaAux.add(usuario);
				}
				fileUsuario.alterarPlanilha(listaAux);

				tfNomeUsuario.setText("");
				pfSenhaI.setText("");
				pfSenhaII.setText("");
				tfOrientacao.setText("");
				tfTelefone.setText("");
				tfEmail.setText("");
				cbAdministrador.setSelected(false);
				cbUsuario.setSelected(false);

				lblSituacaoCadastro.setText("Usuário cadastrado com sucesso!");
			}

		});

		/*----------Finalizando a Tela----------*/
		hBoxUsuario.getChildren().addAll(lblNomeUsuario, tfNomeUsuario);
		hBoxUsuario.setAlignment(Pos.CENTER_LEFT);
		hBoxSenha.getChildren().addAll(lblSenhaI, pfSenhaI, lblSenhaII, pfSenhaII);
		hBoxSenha.setAlignment(Pos.CENTER_LEFT);
		hBoxOrientacao.getChildren().addAll(lblOrientacao, tfOrientacao);
		hBoxOrientacao.setAlignment(Pos.CENTER_LEFT);
		hBoxTelefone.getChildren().addAll(lblTelefone, tfTelefone);
		hBoxTelefone.setAlignment(Pos.CENTER_LEFT);
		hBoxEmail.getChildren().addAll(lblEmail, tfEmail);
		hBoxEmail.setAlignment(Pos.CENTER_LEFT);
		hBoxPermissao.getChildren().addAll(cbAdministrador, cbUsuario);
		hBoxPermissao.setAlignment(Pos.CENTER);
		hBoxBotoes.getChildren().addAll(btLimpar, btCadastrar);
		hBoxBotoes.setAlignment(Pos.CENTER);
		vBoxTelaCadastro.getChildren().addAll(hBoxUsuario, hBoxSenha, hBoxOrientacao, hBoxTelefone, hBoxEmail,
				lblPermisoes, hBoxPermissao, lblSituacaoCadastro, hBoxBotoes);
		vBoxTelaCadastro.setAlignment(Pos.CENTER);
		hBoxGeral.getChildren().addAll(ivFundo, vBoxTelaCadastro);
		hBoxGeral.setAlignment(Pos.CENTER_LEFT);
		spSecundaria.getChildren().clear();
		spSecundaria.getChildren().addAll(limitesRetangulo, hBoxGeral);

		return spSecundaria;
	}

}
