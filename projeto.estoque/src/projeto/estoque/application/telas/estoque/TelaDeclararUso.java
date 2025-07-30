package projeto.estoque.application.telas.estoque;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import projeto.estoque.application.dados.metodos.Consulta;
import projeto.estoque.application.dados.metodos.DadosInseridos;
import projeto.estoque.application.excel.metodos.file.estoque.FileEstoque;
import projeto.estoque.application.excel.metodos.file.logs.FileLogs;
import projeto.estoque.application.objetos.Estoque;
import projeto.estoque.application.objetos.Logs;
import projeto.estoque.application.objetos.Usuario;
import projeto.estoque.application.telas.administracao.TelaAtualizarUsuario;

public class TelaDeclararUso {

	static Estoque item = new Estoque();

	public  static StackPane criarTelaDeclararUso(StackPane spSecundaria, List<Estoque> listaEstoque, List<Logs> listaLogs,
			FileEstoque fileEstoque, FileLogs fileLogs, Usuario usuario) {
		/*----------Variáveis----------*/
		/*-----Gerais-----*/
		Rectangle limitesRetangulo = (Rectangle)spSecundaria.getChildren().get(0);
		double width = limitesRetangulo.getWidth();
		double height = limitesRetangulo.getHeight();
		/*-----Imagem-----*/
		Image imagem = new Image(TelaAtualizarUsuario.class.getResourceAsStream("/images/fundo-secundario-I.png"));
		ImageView ivFundo = new ImageView(imagem);
		ivFundo.setFitWidth(width * 0.255);
		ivFundo.setFitHeight(height * 0.33);
		/*-----ToolTip-----*/
		Tooltip ttDeclararUso = new Tooltip("Atente-se para a unidade de medida e faça a conversão se necesário. P.ex.:\n"
				+ "Unidade de Medida ----- Quantidade Utilizada \n"
				+ "litros ----- 10ml -> 0.01L\n"
				+ "frasco(1L) ----- 60ml -> 0.06L\n"
				+ "caixa(50uni) ----- 12uni -> 0.24 da caixa");
		/*-----Labels-----*/
		Label lblSelecao = new Label("Selecione o usuário:");
		Label lblIDProdutoI = new Label("ID Item:");
		Label lblNomeProdutoI = new Label("Identificador:");
		Label lblCategoriaI = new Label("Categoria: ");
		Label lblLocalizacaoI = new Label("Localização:");
		Label lblUnidadeMedidaI = new Label("Unidade de Medida:");
		Label lblEstoqueI = new Label("Estoque Atual:");
		Label lblIDProdutoII = new Label();
		Label lblNomeProdutoII = new Label();
		Label lblCategoriaII = new Label();
		Label lblLocalizacaoII = new Label();
		Label lblUnidadeMedidaII = new Label();
		Label lblEstoqueII = new Label();
		Label lblDeclararUso = new Label("Quantidade Utilizada:");
		lblDeclararUso.setTooltip(ttDeclararUso);
		Label lblSituacaoDeclararUso = new Label();
		/*-----Fields-----*/
		TextField tfDeclararUso = new TextField();
		tfDeclararUso.setTooltip(ttDeclararUso);
		/*-----Menu-----*/
		MenuButton mbtSelecao = new MenuButton();
		mbtSelecao.setPrefWidth(width * 0.2);
		for(Estoque estoqueAux : listaEstoque) {
			if(estoqueAux.getIdEstoque() != null) {
				MenuItem miEstoque = new MenuItem(estoqueAux.getNomeProduto());
				miEstoque.setOnAction(_ -> {mbtSelecao.setText(estoqueAux.getNomeProduto());});
				mbtSelecao.getItems().add(miEstoque);
			}
		}
		/*-----Botões-----*/
		Button btConfimar = new Button("Confirmar");
		Button btDeclararUso = new Button("Declarar Quantidade Utilizada");
		/*-----Boxes------*/
		HBox hBoxMenu = new HBox(5);
		HBox hBoxIDNomeProduto = new HBox(5);
		HBox hBoxCategoria = new HBox(5);
		HBox hBoxLocalUnidadeMedida = new HBox(5);
		HBox hBoxQuantidade = new HBox(5);
		HBox hBoxQuantidadeUtilizada = new HBox(5);
		HBox hBoxBotoes = new HBox(width * 0.33);
		HBox hBoxGeral = new HBox(10);
		VBox vBoxTelaAtualizacaoI = new VBox(30);
		VBox vBoxTelaAtualizacaoII = new VBox(20);

		/*----------Inserindo Estilos----------*/
		/*-----Labels-----*/
		lblSelecao.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblIDProdutoI.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblNomeProdutoI.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblCategoriaI.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblLocalizacaoI.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblUnidadeMedidaI.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblEstoqueI.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblIDProdutoII.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblNomeProdutoII.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblCategoriaII.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblLocalizacaoII.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblUnidadeMedidaII.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblEstoqueII.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblDeclararUso.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblDeclararUso.setStyle("-fx-underline: true;");
		lblSituacaoDeclararUso.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		/*-----Fields-----*/
		ttDeclararUso.getStyleClass().addAll("fundo-obj-geral", "white-full", "borda", "tooltip");
		tfDeclararUso.getStyleClass().addAll("fonte-geral", "fundo-obj-geral", "borda");
		/*-----Menu-----*/
		mbtSelecao.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral", "borda", "white");
		mbtSelecao.setStyle("-fx-padding: 0 0 0 0");
		for(MenuItem mItem : mbtSelecao.getItems()) {
			mItem.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
			mItem.setStyle("-fx-padding: 5px 20px;");
		}
		/*-----Botões-----*/
		btConfimar.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral", "borda", "greenII");
		btDeclararUso.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral", "borda", "greenII");

		/*----------Inserindo Actions----------*/
		spSecundaria.setOnMouseClicked(_ -> {
			lblSituacaoDeclararUso.setText("");
		});
		tfDeclararUso.setOnAction(_ -> {
			lblSituacaoDeclararUso.setText("");
		});
		btConfimar.setOnAction(_ -> {
			item = Consulta.retornarEstoque(listaEstoque, mbtSelecao.getText());
			vBoxTelaAtualizacaoII.setVisible(true);
			lblIDProdutoII.setText(item.getIdEstoque());
			lblNomeProdutoII.setText(item.getNomeProduto());
			lblCategoriaII.setText(item.getCategoria());
			lblLocalizacaoII.setText(item.getLocalizacao());
			lblUnidadeMedidaII.setText(item.getUnidadeMedida());
			lblEstoqueII.setText(String.valueOf(item.getQuantidadeAtual()));
		});
		tfDeclararUso.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.ENTER) {
				btDeclararUso.fire();
			}
		});
		btDeclararUso.setOnAction(_ -> {
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			String data = LocalDateTime.now().format(formato);
			if(DadosInseridos.corrigirDouble(tfDeclararUso.getText()) == 0) {
				lblSituacaoDeclararUso.setText("Valor inserido inválido!");
			} else if(DadosInseridos.corrigirDouble(tfDeclararUso.getText()) > item.getQuantidadeAtual()) {
				lblSituacaoDeclararUso.setText("Valor inserido inválido - verifique a disponibilidade do item!");
			} else {
				Alert alerta = new Alert(AlertType.CONFIRMATION, "Deseja declarar o uso de " + tfDeclararUso.getText() + " "
						+ lblUnidadeMedidaII.getText() + "?");
				alerta.getDialogPane().lookupButton(ButtonType.OK).getStyleClass()
				.addAll("fonte-geral", "bold", "fundo-obj-geral", "borda", "greenII");
				alerta.showAndWait().ifPresent(response -> {
					if(response == ButtonType.OK) {
						Logs logs = new Logs();
						logs.setIdData(data);
						logs.setIdProduto(item.getIdEstoque());
						logs.setNomeProduto(item.getNomeProduto());
						logs.setNomeUsuario(usuario.getNomeUsuario());
						logs.setQuantidadeUtilizada(DadosInseridos.corrigirDouble(tfDeclararUso.getText()));

						item.setQuantidadeAtual(item.getQuantidadeAtual() - DadosInseridos.corrigirDouble(tfDeclararUso.getText()));

						listaLogs.add(logs);

						fileLogs.alterarPlanilha(listaLogs);
						fileEstoque.alterarPlanilha(listaEstoque);
						criarTelaDeclararUso(spSecundaria, listaEstoque, listaLogs, fileEstoque, fileLogs, usuario);
					}
				});
			}
		});

		/*----------Finalizando a Tela----------*/
		hBoxMenu.getChildren().addAll(lblSelecao, mbtSelecao, btConfimar);
		hBoxMenu.setAlignment(Pos.CENTER);
		hBoxIDNomeProduto.getChildren().addAll(lblIDProdutoI, lblIDProdutoII, lblNomeProdutoI, lblNomeProdutoII);
		hBoxCategoria.getChildren().addAll(lblCategoriaI, lblCategoriaII);
		hBoxLocalUnidadeMedida.getChildren().addAll(lblLocalizacaoI, lblLocalizacaoII, lblUnidadeMedidaI, lblUnidadeMedidaII);
		hBoxQuantidade.getChildren().addAll(lblEstoqueI, lblEstoqueII);
		hBoxQuantidadeUtilizada.getChildren().addAll(lblDeclararUso, tfDeclararUso);
		hBoxBotoes.getChildren().addAll(btDeclararUso);
		hBoxBotoes.setAlignment(Pos.CENTER);
		vBoxTelaAtualizacaoI.getChildren().addAll(hBoxMenu, vBoxTelaAtualizacaoII);
		vBoxTelaAtualizacaoI.setAlignment(Pos.CENTER);
		vBoxTelaAtualizacaoII.getChildren().addAll(hBoxIDNomeProduto, hBoxCategoria, hBoxLocalUnidadeMedida,
				hBoxQuantidade, lblSituacaoDeclararUso, hBoxQuantidadeUtilizada, hBoxBotoes);
		vBoxTelaAtualizacaoII.setAlignment(Pos.CENTER);
		vBoxTelaAtualizacaoII.setVisible(false);
		hBoxGeral.getChildren().addAll(ivFundo, vBoxTelaAtualizacaoI);
		hBoxGeral.setAlignment(Pos.CENTER_LEFT);
		spSecundaria.getChildren().clear();
		spSecundaria.getChildren().addAll(limitesRetangulo, hBoxGeral);

		return spSecundaria;
	}
}
