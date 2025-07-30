package projeto.estoque.application.telas.estoque;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import projeto.estoque.application.dados.metodos.Consulta;
import projeto.estoque.application.dados.metodos.DadosInseridos;
import projeto.estoque.application.excel.metodos.file.estoque.FileEstoque;
import projeto.estoque.application.objetos.Estoque;
import projeto.estoque.application.objetos.Usuario;
import projeto.estoque.application.telas.administracao.TelaCadastroUsuario;

public class TelaCadastroEstoque {
	public static StackPane criarTelaCadastroEstoque(StackPane spSecundaria, List<Estoque> listaEstoque, FileEstoque fileEstoque, Usuario usuario) {

		/*----------Variáveis----------*/
		/*-----Gerais-----*/
		Rectangle limitesRetangulo = (Rectangle)spSecundaria.getChildren().get(0);
		double width = limitesRetangulo.getWidth() * 0.98;
		double height = limitesRetangulo.getHeight();
		/*-----Imagem-----*/
		Image imagem = new Image(TelaCadastroUsuario.class.getResourceAsStream("/images/fundo-secundario-II.png"));
		ImageView ivFundo = new ImageView(imagem);
		ivFundo.setFitWidth(width * 0.25);
		ivFundo.setFitHeight(height * 0.33);
		/*-----ToolTip-----*/
		Tooltip ttEstoque = new Tooltip("Defina conforme deseja classificar e declarar uso dos itens do estoque.");
		/*-----Labels-----*/
		Label lblNomeProduto = new Label("Identificador:");
		Label lblCategoria = new Label("Categoria: ");
		Label lblPericulosidadeDefinicao = new Label("PERICULOSIDADE");
		Label lblPericulosidade = new Label("Perigo:");
		Label lblPericulosidadeTipo = new Label("Classificação de Periculosidade:");
		Label lblPericulosidadeOutros = new Label ("Outros:");
		Label lblLocalizacao = new Label("Localização:");
		Label lblUnidadeMedida = new Label("Unidade de Medida:");
		lblUnidadeMedida.setTooltip(ttEstoque);
		Label lblEstoqueMinimo = new Label("Estoque Mínimo:");
		lblEstoqueMinimo.setTooltip(ttEstoque);
		Label lblEstoque = new Label("Estoque Atual:");
		lblEstoque.setTooltip(ttEstoque);
		Label lblSituacaoCadastro = new Label();
		/*-----Fields-----*/
		TextField tfNomeProduto = new TextField();
		TextField tfCategoria = new TextField();
		tfCategoria.setTranslateX(+18);
		TextField tfPericulosidadeOutros = new TextField();
		TextField tfLocalizacao = new TextField();
		TextField tfUnidadeMedida = new TextField();
		tfUnidadeMedida.setTooltip(ttEstoque);
		TextField tfEstoqueMinimo = new TextField();
		tfEstoqueMinimo.setTooltip(ttEstoque);
		TextField tfEstoque = new TextField();
		tfEstoque.setTooltip(ttEstoque);
		/*-----Menus-----*/
		MenuButton mbtPericulosidade = new MenuButton();
		MenuButton mbtPericulosidadeTipo = new MenuButton();
		CheckMenuItem cmiPerigoNenhum = new CheckMenuItem("Não Aplicável");
		CheckMenuItem cmiPerigoFisico = new CheckMenuItem("Físico");
		CheckMenuItem cmiPerigoSaude = new CheckMenuItem("Saúde");
		CheckMenuItem cmiPerigoAmbiental = new CheckMenuItem("Ambiental");
		mbtPericulosidade.getItems().addAll(cmiPerigoNenhum, cmiPerigoFisico, cmiPerigoSaude, cmiPerigoAmbiental);
		/*-----Botões-----*/
		Button btLimpar = new Button("Limpar Campos");
		Button btCadastrar = new Button("Cadastrar Item");
		/*-----Boxes------*/
		HBox hBoxNomeProduto = new HBox(5);
		HBox hBoxCategoria = new HBox(5);
		HBox hBoxPericulosidade = new HBox(3);
		HBox hBoxPericulosidadeOutros = new HBox(5);
		HBox hBoxLocalUnidadeMedida = new HBox(5);
		HBox hBoxQuantidade = new HBox(5);
		HBox hBoxBotoes = new HBox(width/3);
		HBox hBoxGeral = new HBox(10);
		VBox vBoxPericulosidade = new VBox(10);
		VBox vBoxTelaCadastro = new VBox(20);
		/*-----Retângulo Periculosidade-----*/
		Rectangle retPericulosidade = new Rectangle(width * 0.655, height * 0.23);
		/*-----StackPane Periculosidade-----*/
		StackPane spPericulosidade = new StackPane();

		/*----------Inserindo Estilos----------*/
		/*-----Retângulo Periculosidade-----*/
		retPericulosidade.getStyleClass().addAll("fundo-ret-geral", "ret-green");
		/*-----Labels-----*/
		lblNomeProduto.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblCategoria.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblPericulosidadeDefinicao.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblPericulosidadeDefinicao.setStyle("-fx-underline: true;");
		lblPericulosidade.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblPericulosidadeTipo.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblPericulosidadeOutros.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblLocalizacao.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblUnidadeMedida.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblUnidadeMedida.setStyle("-fx-underline: true;");
		lblEstoqueMinimo.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblEstoqueMinimo.setStyle("-fx-underline: true;");
		lblEstoque.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblEstoque.setStyle("-fx-underline: true;");
		lblSituacaoCadastro.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		/*-----Fields-----*/
		tfNomeProduto.getStyleClass().addAll("fonte-geral", "fundo-obj-geral", "borda");
		tfNomeProduto.setPrefWidth(width * 0.5);
		tfCategoria.getStyleClass().addAll("fonte-geral", "fundo-obj-geral", "borda");
		tfCategoria.setPrefWidth(width * 0.2);
		tfPericulosidadeOutros.getStyleClass().addAll("fonte-geral", "fundo-obj-geral", "borda");
		tfPericulosidadeOutros.setPrefWidth(width * 0.58);
		tfLocalizacao.getStyleClass().addAll("fonte-geral", "fundo-obj-geral", "borda");
		tfLocalizacao.setPrefWidth(width * 0.2);
		ttEstoque.getStyleClass().addAll("fundo-obj-geral", "white-full", "borda", "tooltip");
		tfUnidadeMedida.getStyleClass().addAll("fonte-geral", "fundo-obj-geral", "borda");
		tfUnidadeMedida.setPrefWidth(width * 0.2);
		tfEstoqueMinimo.getStyleClass().addAll("fonte-geral", "fundo-obj-geral", "borda");
		tfEstoqueMinimo.setPrefWidth(width * 0.2);
		tfEstoque.getStyleClass().addAll("fonte-geral", "fundo-obj-geral", "borda");
		tfEstoque.setPrefWidth(width * 0.2);
		/*-----Menu-----*/
		mbtPericulosidade.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral", "borda", "white-full");
		mbtPericulosidade.setStyle("-fx-padding: 0 0 0 0");
		mbtPericulosidade.setPrefWidth(width * 0.16);
		mbtPericulosidadeTipo.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral", "borda", "white-full");
		mbtPericulosidadeTipo.setStyle("-fx-padding: 0 0 0 0");
		mbtPericulosidadeTipo.setPrefWidth(width * 0.21);
		/*-----Botões-----*/
		btLimpar.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral", "borda", "greenII");
		btCadastrar.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral", "borda", "greenII");

		/*----------Inserindo Actions----------*/
		TextField[] listaFields = {tfNomeProduto, tfCategoria, tfPericulosidadeOutros, tfLocalizacao, tfUnidadeMedida, tfEstoqueMinimo,
				tfEstoque};
		for(TextField tfAux : listaFields) {
			tfAux.setOnAction(_ -> {
				lblSituacaoCadastro.setText("");
			});
		}
		for(MenuItem cmiAux : mbtPericulosidade.getItems()) {
			cmiAux.setOnAction(_ ->{
				mbtPericulosidadeTipo.getItems().clear();
				mbtPericulosidade.fire();
			});
		}
		mbtPericulosidade.setOnAction(_ ->{
			List<String> listaTexto = new ArrayList<>();

			if(cmiPerigoNenhum.isSelected()) {
				listaTexto.add("Não Aplicável");
				cmiPerigoFisico.setSelected(false);
				cmiPerigoSaude.setSelected(false);
				cmiPerigoAmbiental.setSelected(false);
			} else {
				listaTexto.remove("Não Aplicável");
			}
			if(cmiPerigoFisico.isSelected()) {
				listaTexto.add("Físico");
				adicionarPerigosFisicos(mbtPericulosidadeTipo);
			} else {
				listaTexto.remove("Físico");
			}
			if(cmiPerigoSaude.isSelected()) {
				listaTexto.add("Saúde");
				adicionarPerigosSaude(mbtPericulosidadeTipo);
			} else {
				listaTexto.remove("Saúde");
			}
			if(cmiPerigoAmbiental.isSelected()) {
				listaTexto.add("Ambiental");
				adicionarPerigosAmbientais(mbtPericulosidadeTipo);
			} else {
				listaTexto.remove("Ambiental");
			}

			for(MenuItem cmiAux : mbtPericulosidadeTipo.getItems()) {
				cmiAux.getStyleClass().addAll("fonte-geral", "bold");
				cmiAux.setStyle("-fx-padding: 5px 15px;");

				cmiAux.setOnAction(_ -> {
					mbtPericulosidadeTipo.fire();
				});
			}

			Collections.sort(listaTexto);
			String texto = listaTexto.toString();
			texto = texto.replace("[", "");
			texto = texto.replace("]", "");
			mbtPericulosidade.setText(texto);
		});
		mbtPericulosidadeTipo.setOnAction(_ -> {
			boolean selecionado = false;
			for(MenuItem miAux : mbtPericulosidadeTipo.getItems()) {
				if(((CheckMenuItem) miAux).isSelected()) {
					selecionado = true;
				}
			}
			if(selecionado) {
				mbtPericulosidadeTipo.setText("Item(ns) Selecionado(s)");
			} else {
				mbtPericulosidadeTipo.setText("");
			}
		});
		btLimpar.setOnAction(_ ->{
			tfNomeProduto.setText("");
			tfCategoria.setText("");
			tfPericulosidadeOutros.setText("");
			tfLocalizacao.setText("");
			tfUnidadeMedida.setText("");
			tfEstoqueMinimo.setText("");
			tfEstoque.setText("");
			mbtPericulosidade.setText("");
			limparMenuButton(mbtPericulosidade);
			mbtPericulosidadeTipo.setText("");
			limparMenuButton(mbtPericulosidadeTipo);

			lblSituacaoCadastro.setText("Campos Limpos");
		});
		btCadastrar.setOnAction(_ -> {
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			String data = LocalDateTime.now().format(formato);
			Estoque item = new Estoque();

			if(tfNomeProduto.getText().isBlank() || tfCategoria.getText().isBlank() || mbtPericulosidade.getText().isBlank() ||
					tfLocalizacao.getText().isBlank() || tfUnidadeMedida.getText().isBlank() || tfEstoqueMinimo.getText().isBlank()
					|| tfEstoque.getText().isBlank()) {
				lblSituacaoCadastro.setText("Campo(s) Vazio(s)");
			} else if(Consulta.consultarItemExiste(listaEstoque, tfNomeProduto.getText().toUpperCase())) {
				lblSituacaoCadastro.setText("Item já cadastrado!");
			} else if(!mbtPericulosidade.getText().contains("Não Aplicável") && !mbtPericulosidadeTipo.getText()
					.contains("Item(ns) Selecionado(s)")) {
				lblSituacaoCadastro.setText("Selecione ao menos uma categoria de periculosidade");
			} else if(DadosInseridos.corrigirDouble(tfEstoqueMinimo.getText()) == 0) {
				lblSituacaoCadastro.setText("Número Inválido no campo \"Estoque Mínimo\" ");
			} else if(DadosInseridos.corrigirDouble(tfEstoque.getText()) == 0) {
				lblSituacaoCadastro.setText("Número Inválido no campo \"Estoque\" ");
			} else {
				item.setNomeProduto(tfNomeProduto.getText().toUpperCase());
				item.setCategoria(tfCategoria.getText());
				String[] periculosidade = mbtPericulosidade.getText().split(",");
				item.addPericulosidadeGeral(periculosidade);
				if(mbtPericulosidadeTipo.getText().isBlank()) {
					item.addPericulosidadeEspecifica(tfPericulosidadeOutros.getText());
				} else {
					periculosidade = null;
					for(MenuItem miAux : mbtPericulosidadeTipo.getItems()) {
						if(((CheckMenuItem) miAux).isSelected()) {
							periculosidade = miAux.getText().split(",");
						}
					}
					item.addPericulosidadeEspecifica(periculosidade);
					if(!tfPericulosidadeOutros.getText().isBlank()) {
						item.addPericulosidadeEspecifica(tfPericulosidadeOutros.getText());
					}
				}
				item.setLocalizacao(tfLocalizacao.getText());
				item.setUnidadeMedida(tfUnidadeMedida.getText());
				item.setQuantidadeMinima(DadosInseridos.corrigirDouble(tfEstoqueMinimo.getText()));
				item.setQuantidadeAtual(DadosInseridos.corrigirDouble(tfEstoque.getText()));
				item.setDataAlteracao(data);
				item.setNomeUsuario(usuario.getNomeUsuario());

				/*----Ordenando a Lista----*/
				List<Estoque> listaAux = new ArrayList<>();
				for(Estoque itemAux : listaEstoque) {
					if(itemAux.getIdEstoque() == null) {
						listaAux.add(itemAux);
						listaEstoque.remove(itemAux);
					}
				}

				DadosInseridos.retornarIDEstoque(listaEstoque, item);

				for(Estoque itemAux : listaEstoque) {
					listaAux.add(itemAux);
				}

				fileEstoque.alterarPlanilha(listaEstoque);

				tfNomeProduto.setText("");
				tfCategoria.setText("");
				tfPericulosidadeOutros.setText("");
				tfLocalizacao.setText("");
				tfUnidadeMedida.setText("");
				tfEstoqueMinimo.setText("");
				tfEstoque.setText("");
				mbtPericulosidade.setText("");
				limparMenuButton(mbtPericulosidade);
				mbtPericulosidadeTipo.setText("");
				limparMenuButton(mbtPericulosidadeTipo);

				lblSituacaoCadastro.setText("Item cadastrado com sucesso!");
			}
		});

		/*----------Finalizando a Tela----------*/
		hBoxNomeProduto.getChildren().addAll(lblNomeProduto, tfNomeProduto);
		hBoxCategoria.getChildren().addAll(lblCategoria, tfCategoria);
		hBoxPericulosidade.getChildren().addAll(lblPericulosidade, mbtPericulosidade, lblPericulosidadeTipo, mbtPericulosidadeTipo);
		hBoxPericulosidade.setAlignment(Pos.CENTER_LEFT);
		hBoxPericulosidadeOutros.getChildren().addAll(lblPericulosidadeOutros, tfPericulosidadeOutros);
		hBoxPericulosidadeOutros.setAlignment(Pos.CENTER_LEFT);
		hBoxLocalUnidadeMedida.getChildren().addAll(lblLocalizacao, tfLocalizacao, lblUnidadeMedida, tfUnidadeMedida);
		hBoxQuantidade.getChildren().addAll(lblEstoqueMinimo, tfEstoqueMinimo, lblEstoque, tfEstoque);
		hBoxBotoes.getChildren().addAll(btLimpar, btCadastrar);
		hBoxBotoes.setAlignment(Pos.CENTER);
		vBoxPericulosidade.getChildren().addAll(lblPericulosidadeDefinicao, hBoxPericulosidade, hBoxPericulosidadeOutros);
		vBoxPericulosidade.setAlignment(Pos.CENTER);
		spPericulosidade.getChildren().addAll(retPericulosidade, vBoxPericulosidade);
		vBoxTelaCadastro.getChildren().addAll(hBoxNomeProduto, hBoxCategoria, spPericulosidade, hBoxLocalUnidadeMedida,
				hBoxQuantidade, lblSituacaoCadastro, hBoxBotoes);
		vBoxTelaCadastro.setAlignment(Pos.CENTER);
		hBoxGeral.getChildren().addAll(ivFundo, vBoxTelaCadastro);
		hBoxGeral.setAlignment(Pos.CENTER_LEFT);
		spSecundaria.getChildren().clear();
		spSecundaria.getChildren().addAll(limitesRetangulo, hBoxGeral);

		return spSecundaria;
	}

	private static void adicionarPerigosFisicos(MenuButton mbtOrigial) {
		CheckMenuItem cmiI = new CheckMenuItem("2.1 Explosivos");
		CheckMenuItem cmiII = new CheckMenuItem("2.2 Gases Inflamáveis");
		CheckMenuItem cmiIII = new CheckMenuItem("2.3 Aerosóis Inflamáveis");
		CheckMenuItem cmiIV = new CheckMenuItem("2.4 Gases Comburentes");
		CheckMenuItem cmiV = new CheckMenuItem("2.5 Gases sob Pressão");
		CheckMenuItem cmiVI = new CheckMenuItem("2.6 Líquidos Inflamáveis");
		CheckMenuItem cmiVII = new CheckMenuItem("2.7 Sólidos Inflamáveis");
		CheckMenuItem cmiVIII = new CheckMenuItem("2.8 Substâncias e Misturas Auto-Reativas");
		CheckMenuItem cmiIX = new CheckMenuItem("2.9 Líquidos Pirofóricos");
		CheckMenuItem cmiX = new CheckMenuItem("2.10 Sólidos Pirofóricos");
		CheckMenuItem cmiXI = new CheckMenuItem("2.11 Substâncias e Misturas Suscetíveis de "
				+ "Auto-Aquecimento");
		CheckMenuItem cmiXII = new CheckMenuItem("2.12 Substâncias e Misturas que, em contato com "
				+ "água, liberam Gases Inflamáveis");
		CheckMenuItem cmiXIII = new CheckMenuItem("2.13 Líquidos Comburentes");
		CheckMenuItem cmiXIV = new CheckMenuItem("2.14 Sólidos Comburentes");
		CheckMenuItem cmiXV = new CheckMenuItem("2.15 Peróxidos Orgânicos");
		CheckMenuItem cmiXVI = new CheckMenuItem("2.16 Corrosivos para Metais");
		CheckMenuItem cmiXVII = new CheckMenuItem("2.17 Explosivos Dessensibilizados");

		CheckMenuItem[] listaCheckMenu = {cmiI, cmiII, cmiIII, cmiIV, cmiV, cmiVI, cmiVII, cmiVIII, cmiIX, cmiX, cmiXI, cmiXII, cmiXIII,
				cmiXIV, cmiXV, cmiXVI, cmiXVII};

		mbtOrigial.getItems().addAll(listaCheckMenu);
	}

	private static void adicionarPerigosSaude(MenuButton mbtOriginal) {
		CheckMenuItem cmiI = new CheckMenuItem("3.1 Toxicidade Aguda");
		CheckMenuItem cmiII = new CheckMenuItem("3.2 Corrosão/Irritação Cultânea");
		CheckMenuItem cmiIII = new CheckMenuItem("3.3 Lesões oculares graves / irritação ocular");
		CheckMenuItem cmiIV = new CheckMenuItem("3.4 Sensibilização respiratória ou cutânea)");
		CheckMenuItem cmiV = new CheckMenuItem("3.5 Mutagenicidade em células germinativas");
		CheckMenuItem cmiVI = new CheckMenuItem("3.6 Carcinogenicidade");
		CheckMenuItem cmiVII = new CheckMenuItem("3.7 Toxicidade Reprodutiva");
		CheckMenuItem cmiVIII = new CheckMenuItem("3.8 Toxicidade para órgãos-alvo específicos de exposição única");
		CheckMenuItem cmiIX = new CheckMenuItem("3.9 Toxicidade para órgãos-alvo específicos de exposição repetida");
		CheckMenuItem cmiX = new CheckMenuItem("3.10 Perigo de Aspiração");

		CheckMenuItem[] listaCheckMenu = {cmiI, cmiII, cmiIII, cmiIV, cmiV, cmiVI, cmiVII, cmiVIII, cmiIX, cmiX};
		mbtOriginal.getItems().addAll(listaCheckMenu);
	}

	private static void adicionarPerigosAmbientais(MenuButton mbtOriginal) {
		CheckMenuItem cmiI = new CheckMenuItem("4.1 Perigoso para o ambiente aquático");
		CheckMenuItem cmiII = new CheckMenuItem("4.2 Perigoso para a camada de ozônio");

		mbtOriginal.getItems().add(cmiI);
		mbtOriginal.getItems().add(cmiII);
	}

	private static void limparMenuButton(MenuButton mbtOriginal) {
		for(MenuItem miAux : mbtOriginal.getItems()) {
			((CheckMenuItem) miAux).setSelected(false);
		}
	}
}
