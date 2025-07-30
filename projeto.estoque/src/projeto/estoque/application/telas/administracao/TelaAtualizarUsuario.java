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
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
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

public class TelaAtualizarUsuario {

	static Usuario usuario = new Usuario();
	public static StackPane criarTelaAtualizacao(StackPane spSecundaria, List<Usuario> listaUsuario, FileUsuario fUsuario) {

		/*----------Variáveis----------*/
		/*-----Gerais-----*/
		Rectangle limitesRetangulo = (Rectangle)spSecundaria.getChildren().get(0);
		double width = limitesRetangulo.getWidth();
		double height = limitesRetangulo.getHeight();
		/*-----Imagem-----*/
		Image imagem = new Image(TelaAtualizarUsuario.class.getResourceAsStream("/images/fundo-secundario-III.png"));
		ImageView ivFundo = new ImageView(imagem);
		ivFundo.setFitWidth(width * 0.25);
		ivFundo.setFitHeight(height * 0.33);
		/*-----Labels-----*/
		Label lblSelecao = new Label("Selecione o usuário:");
		Label lblNomeUsuario = new Label("Usuário:");
		Label lblSenhaI = new Label("Senha:");
		Label lblSenhaII = new Label("Confirmar Senha:");
		Label lblOrientacao = new Label("Orientador(a):");
		Label lblTelefone = new Label("Telefone:");
		Label lblEmail = new Label("E-mail:");
		Label lblPermisoes = new Label("Permissões do Usuário");
		Label lblSituacaoAtualizacao = new Label();
		/*-----Fields e Boxes-----*/
		TextField tfNomeUsuario = new TextField();
		PasswordField pfSenhaI = new PasswordField();
		PasswordField pfSenhaII = new PasswordField();
		TextField tfOrientacao = new TextField();
		TextField tfTelefone = new TextField();
		TextField tfEmail = new TextField();
		CheckBox cbAdministrador = new CheckBox("Permissão de Administrador");
		CheckBox cbUsuario = new CheckBox("Permissão de Usuário");
		/*-----Menu-----*/
		MenuButton mbtSelecao = new MenuButton();
		mbtSelecao.setPrefWidth(width * 0.2);
		for(Usuario usuarioAux : listaUsuario) {
			if(usuarioAux.getIdUsuario() != null && !usuarioAux.getIdUsuario().equals("0")) {
				MenuItem miUsuario = new MenuItem(usuarioAux.getNomeUsuario());
				miUsuario.setOnAction(_ -> {mbtSelecao.setText(usuarioAux.getNomeUsuario());});
				mbtSelecao.getItems().add(miUsuario);
			}
		}
		/*-----Botões-----*/
		Button btConfimar = new Button("Confirmar");
		Button btAtualizar = new Button("Atualizar Dados");
		Button btDeletar = new Button("Deletar Usuário");
		/*-----HBoxes-----*/
		HBox hBoxGeral = new HBox(10);
		HBox hBoxMenu = new HBox(5);
		HBox hBoxUsuario = new HBox(5);
		HBox hBoxSenha = new HBox(5);
		HBox hBoxOrientacao = new HBox(5);
		HBox hBoxTelefone = new HBox(5);
		HBox hBoxEmail = new HBox(5);
		HBox hBoxPermissao = new HBox(50);
		HBox hBoxBotoes = new HBox(width * 0.33);
		/*-----VBox-----*/
		VBox vBoxTelaAtualizacaoI = new VBox(height * 0.045);
		VBox vBoxTelaAtualizacaoII = new VBox(height * 0.045);

		/*----------Inserindo Estilos----------*/
		/*-----Labels-----*/
		lblSelecao.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblNomeUsuario.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblSenhaI.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblSenhaII.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblOrientacao.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblTelefone.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblEmail.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblPermisoes.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblSituacaoAtualizacao.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
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
		/*-----Menu-----*/
		mbtSelecao.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral", "borda", "white");
		mbtSelecao.setStyle("-fx-padding: 0 0 0 0");
		for(MenuItem mItem : mbtSelecao.getItems()) {
			mItem.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral", "white");
			mItem.setStyle("-fx-padding: 5px 20px;");
		}
		/*-----Botões-----*/
		btConfimar.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral", "borda", "greenII");
		btDeletar.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral", "borda", "greenII");
		btAtualizar.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral", "borda", "greenII");

		/*----------Inserindo Actions----------*/
		spSecundaria.setOnMouseClicked(_ -> {
			lblSituacaoAtualizacao.setText("");
		});
		TextField[] listaFields = {tfNomeUsuario, pfSenhaI, pfSenhaII, tfOrientacao, tfTelefone, tfEmail};
		for(TextField textField : listaFields) {
			textField.setOnMouseClicked(_ -> {
				lblSituacaoAtualizacao.setText("");
			});
		}
		btConfimar.setOnAction(_ -> {
			usuario = Consulta.retornarUsuario(listaUsuario, mbtSelecao.getText());
			vBoxTelaAtualizacaoII.setVisible(true);
			tfNomeUsuario.setText(usuario.getNomeUsuario());
			tfOrientacao.setText(usuario.getOrientacao());
			tfTelefone.setText(usuario.getTelefone());
			tfEmail.setText(usuario.getEmail());
			cbAdministrador.setSelected(usuario.isPermAdministrador());
			cbUsuario.setSelected(usuario.isPermUsuario());
		});
		btDeletar.setOnAction(_ ->{
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			String data = LocalDateTime.now().format(formato);
			usuario.setIdUsuario(null);
			usuario.setDataSaida(data);
			fUsuario.alterarPlanilha(listaUsuario);
			criarTelaAtualizacao(spSecundaria, listaUsuario, fUsuario);
		});
		btAtualizar.setOnAction(_ ->{
			if(tfNomeUsuario.getText().isBlank() ||	tfTelefone.getText().isBlank() || tfEmail.getText().isBlank() ||
					!cbUsuario.isSelected()) {
				lblSituacaoAtualizacao.setText("Campo(s) vazio(s)!");
			} else if(!usuario.getNomeUsuario().equals(tfNomeUsuario.getText().toUpperCase()) &&
					Consulta.consultarUsuarioExiste(listaUsuario, tfNomeUsuario.getText().toUpperCase())) {
				lblSituacaoAtualizacao.setText("Usuário já existe!");
			} else if(!pfSenhaI.getText().isBlank() && (!pfSenhaI.getText().equals(pfSenhaII.getText()) ||
					pfSenhaI.getText().length() < 6)) {
				lblSituacaoAtualizacao.setText("Senha inválida ou não coincidem");
			} else if(DadosInseridos.corrigeTelefone(tfTelefone.getText()) == null) {
				lblSituacaoAtualizacao.setText("Telefone inválido!");
			} else if(!DadosInseridos.checarEmailValido(tfEmail.getText())) {
				lblSituacaoAtualizacao.setText("E-mail inválido!");
			} else {
				if(!usuario.getOrientacao().equals(tfOrientacao.getText())) {
					usuario.setIdUsuario(DadosInseridos.retornarIDUsuario(listaUsuario, tfOrientacao.getText()));
				}
				if(!pfSenhaI.getText().isBlank()) {
					usuario.setSenhaUsuario(CriptografiaSenha.criptografarSenha(pfSenhaI.getText()));
				}
				usuario.setNomeUsuario(tfNomeUsuario.getText().toUpperCase());
				usuario.setOrientacao(tfOrientacao.getText());
				usuario.setTelefone(DadosInseridos.corrigeTelefone(tfTelefone.getText()));
				usuario.setEmail(tfEmail.getText());
				usuario.setPermAdministrador(cbAdministrador.isSelected());
				usuario.setPermUsuario(true);

				/*----Ordenando a Lista----*/
				List<Usuario> listaAux = new ArrayList<>();
				for(Usuario usuario : listaUsuario) {
					if(usuario.getIdUsuario() == null) {
						listaAux.add(usuario);
						listaUsuario.remove(usuario);
					}
				}
				listaUsuario.sort(Comparator.comparing(Usuario::getIdUsuario).thenComparing(Usuario::getNomeUsuario));
				for(Usuario usuario : listaUsuario) {
					listaAux.add(usuario);
				}
				fUsuario.alterarPlanilha(listaAux);

				criarTelaAtualizacao(spSecundaria, listaUsuario, fUsuario);
			}
		});

		/*----------Finalizando a Tela----------*/
		hBoxMenu.getChildren().addAll(lblSelecao, mbtSelecao, btConfimar);
		hBoxMenu.setAlignment(Pos.CENTER);
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
		hBoxBotoes.getChildren().addAll(btDeletar, btAtualizar);
		hBoxBotoes.setAlignment(Pos.CENTER);
		vBoxTelaAtualizacaoI.getChildren().addAll(hBoxMenu, vBoxTelaAtualizacaoII);
		vBoxTelaAtualizacaoI.setAlignment(Pos.CENTER);
		vBoxTelaAtualizacaoII.getChildren().addAll(hBoxUsuario, hBoxSenha, hBoxOrientacao, hBoxTelefone, hBoxEmail,
				lblPermisoes, hBoxPermissao, lblSituacaoAtualizacao, hBoxBotoes);
		vBoxTelaAtualizacaoII.setAlignment(Pos.CENTER);
		vBoxTelaAtualizacaoII.setVisible(false);
		hBoxGeral.getChildren().addAll(ivFundo, vBoxTelaAtualizacaoI);
		hBoxGeral.setAlignment(Pos.CENTER_LEFT);
		spSecundaria.getChildren().clear();
		spSecundaria.getChildren().addAll(limitesRetangulo, hBoxGeral);

		return spSecundaria;
	}
}
