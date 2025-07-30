package projeto.estoque.application.telas;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
import projeto.estoque.application.telas.administracao.TelaCadastroUsuario;

public class TelaPerfil {
	public static StackPane criarAreaPerfil(StackPane spSecundaria, List<Usuario> listaUsuario, FileUsuario fileUsuario, Usuario usuario) {
		//Geral
		Rectangle limitesRetangulo = (Rectangle)spSecundaria.getChildren().get(0);
		double width = limitesRetangulo.getWidth();
		double height = limitesRetangulo.getHeight();

		//Imagem
		Image imagem = new Image(TelaCadastroUsuario.class.getResourceAsStream("/images/fundo-secundario-IV.png"));
		ImageView ivFundo = new ImageView(imagem);
		ivFundo.setFitWidth(width * 0.2);
		ivFundo.setFitHeight(height * 0.26);

		//Labels
		Label lblNomeUsuario = new Label("Usuário:");
		Label lblSenhaI = new Label("Senha:");
		Label lblSenhaII = new Label("Confirmar a Senha:");
		Label lblOrientacaoI = new Label("Orientador(a):");
		Label lblOrientacaoII = new Label(usuario.getOrientacao());
		Label lblTelefone = new Label("Telefone:");
		Label lblEmail = new Label("E-mail:");
		Label lblSituacaoPerfil = new Label();

		//Fields
		TextField tfNomeUsuario = new TextField(usuario.getNomeUsuario());
		PasswordField pfSenhaI = new PasswordField();
		PasswordField pfSenhaII = new PasswordField();
		TextField tfTelefone = new TextField(usuario.getTelefone());
		TextField tfEmail = new TextField(usuario.getEmail());

		//Button
		Button btAtualizar = new Button("Atualizar");

		//HBox
		HBox hBoxUsuario = new HBox(10);
		HBox hBoxSenha = new HBox(10);
		HBox hBoxOrientacao = new HBox(10);
		HBox hBoxTelefone = new HBox(10);
		HBox hBoxEmail = new HBox(10);
		HBox hBoxGeral = new HBox(10);
		hBoxUsuario.getChildren().addAll(lblNomeUsuario, tfNomeUsuario);
		hBoxSenha.getChildren().addAll(lblSenhaI, pfSenhaI, lblSenhaII, pfSenhaII);
		hBoxOrientacao.getChildren().addAll(lblOrientacaoI, lblOrientacaoII);
		hBoxTelefone.getChildren().addAll(lblTelefone, tfTelefone);
		hBoxEmail.getChildren().addAll(lblEmail, tfEmail);

		//VBox
		VBox vBoxTelaPerfil = new VBox(15);
		vBoxTelaPerfil.getChildren().addAll(hBoxUsuario, hBoxSenha, hBoxOrientacao, hBoxTelefone, hBoxEmail, btAtualizar);
		vBoxTelaPerfil.setAlignment(Pos.CENTER);

		//Inserindo Estilos
		lblNomeUsuario.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblSenhaI.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblSenhaII.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblOrientacaoI.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblOrientacaoII.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblTelefone.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblEmail.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblSituacaoPerfil.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		tfNomeUsuario.getStyleClass().addAll("fonte-geral", "fundo-obj-geral", "borda");
		pfSenhaI.getStyleClass().addAll("fonte-geral", "fundo-obj-geral", "borda");
		pfSenhaII.getStyleClass().addAll("fonte-geral", "fundo-obj-geral", "borda");
		tfTelefone.getStyleClass().addAll("fonte-geral", "fundo-obj-geral", "borda");
		tfEmail.getStyleClass().addAll("fonte-geral", "fundo-obj-geral", "borda");
		tfEmail.setPrefWidth(width * 0.4);
		btAtualizar.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral", "borda", "greenII");

		//Terminando a StackPane
		hBoxGeral.getChildren().addAll(ivFundo, vBoxTelaPerfil);
		hBoxGeral.setAlignment(Pos.CENTER);
		spSecundaria.getChildren().clear();
		spSecundaria.getChildren().addAll(limitesRetangulo, hBoxGeral);

		//Inserindo Action
		btAtualizar.setOnAction(_ -> {

			if(tfNomeUsuario.getText().isBlank() || tfTelefone.getText().isBlank() || tfEmail.getText().isBlank()) {
				lblSituacaoPerfil.setText("Campo(s) vazio(s)!");
			} else if(!usuario.getNomeUsuario().equals(tfNomeUsuario.getText().toUpperCase()) &&
					Consulta.consultarUsuarioExiste(listaUsuario, tfNomeUsuario.getText().toUpperCase())) {
				lblSituacaoPerfil.setText("Usuário já existe!");
			} else if(!pfSenhaI.getText().isBlank() && (!pfSenhaI.getText().equals(pfSenhaII.getText()) ||
					pfSenhaI.getText().length() < 6)) {
				lblSituacaoPerfil.setText("Senha inválida ou não coincidem");
			} else if(DadosInseridos.corrigeTelefone(tfTelefone.getText()) == null) {
				lblSituacaoPerfil.setText("Telefone inválido!");
			} else if(!DadosInseridos.checarEmailValido(tfEmail.getText())) {
				lblSituacaoPerfil.setText("E-mail inválido!");
			} else {
				if(!pfSenhaI.getText().isBlank()) {
					usuario.setSenhaUsuario(CriptografiaSenha.criptografarSenha(pfSenhaI.getText()));
				}
				usuario.setNomeUsuario(tfNomeUsuario.getText().toUpperCase());
				usuario.setTelefone(DadosInseridos.corrigeTelefone(tfTelefone.getText()));
				usuario.setEmail(tfEmail.getText());

				/*----Ordenando a Lista----*/
				List<Usuario> listaAux = new ArrayList<>();
				for(Usuario usuarioAux : listaUsuario) {
					if(usuarioAux.getIdUsuario() == null) {
						listaAux.add(usuarioAux);
						listaUsuario.remove(usuarioAux);
					}
				}
				listaUsuario.add(usuario);
				listaUsuario.sort(Comparator.comparing(Usuario::getIdUsuario).thenComparing(Usuario::getNomeUsuario));
				for(Usuario usuarioAux : listaUsuario) {
					listaAux.add(usuarioAux);
				}
				fileUsuario.alterarPlanilha(listaAux);
			}
		});

		return spSecundaria;

	}
}
