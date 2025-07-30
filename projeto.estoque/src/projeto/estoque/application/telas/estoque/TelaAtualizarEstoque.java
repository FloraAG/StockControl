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
import projeto.estoque.application.telas.administracao.TelaAtualizarUsuario;

public class TelaAtualizarEstoque {

	static Estoque item = new Estoque();

	public static StackPane criarTelaAtualizacao(StackPane spSecundaria, List<Estoque> listaEstoque, FileEstoque fileEstoque, Usuario usuario) {
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
		/*-----ToolTip-----*/
		Tooltip ttEstoque = new Tooltip("Defina conforme deseja classificar e declarar uso dos itens do estoque.");
		/*-----Labels-----*/
		Label lblSelecao = new Label("Selecione o item:");
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
		Label lblSituacaoAtualizacao = new Label();
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
		/*-----Menu-----*/
		MenuButton mbtSelecao = new MenuButton();
		mbtSelecao.setPrefWidth(width * 0.2);
		MenuButton mbtPericulosidade = new MenuButton();
		MenuButton mbtPericulosidadeTipo = new MenuButton();
		CheckMenuItem cmiPerigoNenhum = new CheckMenuItem("Não Aplicável");
		CheckMenuItem cmiPerigoFisico = new CheckMenuItem("Físico");
		CheckMenuItem cmiPerigoSaude = new CheckMenuItem("Saúde");
		CheckMenuItem cmiPerigoAmbiental = new CheckMenuItem("Ambiental");
		mbtPericulosidade.getItems().addAll(cmiPerigoNenhum, cmiPerigoFisico, cmiPerigoSaude, cmiPerigoAmbiental);
		for(Estoque estoqueAux : listaEstoque) {
			if(estoqueAux.getIdEstoque() != null) {
				MenuItem miEstoque = new MenuItem(estoqueAux.getNomeProduto());
				miEstoque.setOnAction(_ -> {mbtSelecao.setText(estoqueAux.getNomeProduto());});
				mbtSelecao.getItems().add(miEstoque);
			}
		}
		/*-----Botões-----*/
		Button btConfimar = new Button("Confirmar");
		Button btAtualizar = new Button("Atualizar Item");
		Button btDeletar = new Button("Deletar Item");
		/*-----Boxes------*/
		HBox hBoxMenu = new HBox(5);
		HBox hBoxNomeProduto = new HBox(5);
		HBox hBoxCategoria = new HBox(5);
		HBox hBoxPericulosidade = new HBox(3);
		HBox hBoxPericulosidadeOutros = new HBox(5);
		HBox hBoxLocalUnidadeMedida = new HBox(5);
		HBox hBoxQuantidade = new HBox(5);
		HBox hBoxBotoes = new HBox(width * 0.33);
		HBox hBoxGeral = new HBox(10);
		VBox vBoxPericulosidade = new VBox(10);
		VBox vBoxTelaAtualizacaoI = new VBox(height * 0.045);
		VBox vBoxTelaAtualizacaoII = new VBox(height * 0.045);
		/*-----Retângulo Periculosidade-----*/
		Rectangle retPericulosidade = new Rectangle(width * 0.655 , height * 0.23);
		/*-----StackPane Periculosidade-----*/
		StackPane spPericulosidade = new StackPane();

		/*----------Inserindo Estilos----------*/
		/*-----Retângulo Periculosidade-----*/
		retPericulosidade.getStyleClass().addAll("fundo-ret-geral", "ret-green");
		/*-----Labels-----*/
		lblSelecao.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblNomeProduto.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblCategoria.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblPericulosidadeDefinicao.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblPericulosidadeDefinicao.setStyle("-fx-underline: true;");
		lblPericulosidade.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblPericulosidadeTipo.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblPericulosidadeOutros.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblLocalizacao.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblUnidadeMedida.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblEstoqueMinimo.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblEstoque.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		lblSituacaoAtualizacao.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
		/*-----Fields-----*/
		tfNomeProduto.getStyleClass().addAll("fonte-geral", "fundo-obj-geral", "borda");
		tfNomeProduto.setPrefWidth(width * 0.5);
		tfCategoria.getStyleClass().addAll("fonte-geral", "fundo-obj-geral", "borda");
		tfPericulosidadeOutros.getStyleClass().addAll("fonte-geral", "fundo-obj-geral", "borda");
		tfPericulosidadeOutros.setPrefWidth(width * 0.58);
		tfLocalizacao.getStyleClass().addAll("fonte-geral", "fundo-obj-geral", "borda");
		tfLocalizacao.setPrefWidth(width * 0.2);
		tfUnidadeMedida.getStyleClass().addAll("fonte-geral", "fundo-obj-geral", "borda");
		tfUnidadeMedida.setStyle("-fx-underline: true;");
		tfUnidadeMedida.setPrefWidth(width * 0.2);
		tfEstoqueMinimo.getStyleClass().addAll("fonte-geral", "fundo-obj-geral", "borda");
		tfEstoqueMinimo.setStyle("-fx-underline: true;");
		tfEstoqueMinimo.setPrefWidth(width * 0.2);
		tfEstoque.getStyleClass().addAll("fonte-geral", "fundo-obj-geral", "borda");
		tfEstoque.setStyle("-fx-underline: true;");
		tfEstoque.setPrefWidth(width * 0.2);
		/*-----Menu-----*/
		mbtSelecao.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral", "borda", "white");
		mbtSelecao.setStyle("-fx-padding: 0 0 0 0");
		for(MenuItem mItem : mbtSelecao.getItems()) {
			mItem.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral");
			mItem.setStyle("-fx-padding: 5px 20px;");
		}
		mbtPericulosidade.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral", "borda", "white-full");
		mbtPericulosidade.setStyle("-fx-padding: 0 0 0 0");
		mbtPericulosidade.setPrefWidth(width * 0.16);
		mbtPericulosidadeTipo.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral", "borda", "white-full");
		mbtPericulosidadeTipo.setStyle("-fx-padding: 0 0 0 0");
		mbtPericulosidadeTipo.setPrefWidth(width * 0.21);
		/*-----Botões-----*/
		btConfimar.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral", "borda", "greenII");
		btDeletar.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral", "borda", "greenII");
		btAtualizar.getStyleClass().addAll("fonte-geral", "bold", "fundo-obj-geral", "borda", "greenII");

		/*----------Inserindo Actions----------*/
		spSecundaria.setOnMouseClicked(_ -> {
			lblSituacaoAtualizacao.setText("");
		});
		TextField[] listaFields = {tfNomeProduto, tfCategoria, tfPericulosidadeOutros, tfLocalizacao, tfUnidadeMedida, tfEstoqueMinimo,
				tfEstoque};
		for(TextField tfAux : listaFields) {
			tfAux.setOnAction(_ -> {
				lblSituacaoAtualizacao.setText("");
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
		btConfimar.setOnAction(_ -> {
			item = Consulta.retornarEstoque(listaEstoque, mbtSelecao.getText());
			vBoxTelaAtualizacaoII.setVisible(true);
			tfNomeProduto.setText(item.getNomeProduto());
			tfCategoria.setText(item.getCategoria());
			tfLocalizacao.setText(item.getLocalizacao());
			tfUnidadeMedida.setText(item.getUnidadeMedida());
			tfEstoqueMinimo.setText(String.valueOf(item.getQuantidadeMinima()));
			tfEstoque.setText(String.valueOf(item.getQuantidadeAtual()));
			atualizarSelecaoPericulosidadeGeral(item, mbtPericulosidade);
			atualizarSelecaoPericulosidadeEspecifica(item, tfPericulosidadeOutros, mbtPericulosidadeTipo);
		});
		btDeletar.setOnAction(_ -> {
			item.setIdEstoque(null);
			fileEstoque.alterarPlanilha(listaEstoque);
			listaEstoque.remove(item);
			criarTelaAtualizacao(spSecundaria, listaEstoque, fileEstoque, usuario);
		});
		btAtualizar.setOnAction(_ -> {
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			String data = LocalDateTime.now().format(formato);
			if(tfNomeProduto.getText().isBlank() || tfCategoria.getText().isBlank() || mbtPericulosidade.getText().isBlank() ||
					tfLocalizacao.getText().isBlank() || tfUnidadeMedida.getText().isBlank() || tfEstoqueMinimo.getText().isBlank()
					|| tfEstoque.getText().isBlank()) {
				lblSituacaoAtualizacao.setText("Campo(s) Vazio(s)");
			} else if(!tfNomeProduto.getText().equals(item.getNomeProduto()) &&
					Consulta.consultarItemExiste(listaEstoque, tfNomeProduto.getText().toUpperCase())) {
				lblSituacaoAtualizacao.setText("Item já cadastrado!");
			} else if(!mbtPericulosidade.getText().contains("Não Aplicável") && !mbtPericulosidadeTipo.getText()
					.contains("Item(ns) Selecionado(s)")) {
				lblSituacaoAtualizacao.setText("Selecione ao menos uma categoria de periculosidade");
			} else if(DadosInseridos.corrigirDouble(tfEstoqueMinimo.getText()) == 0) {
				lblSituacaoAtualizacao.setText("Número Inválido no campo \"Estoque Mínimo\" ");
			} else if(DadosInseridos.corrigirDouble(tfEstoque.getText()) == 0) {
				lblSituacaoAtualizacao.setText("Número Inválido no campo \"Estoque\" ");
			} else {
				listaEstoque.remove(item);
				item = new Estoque();
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
				DadosInseridos.retornarIDEstoque(listaEstoque, item);

				/*----Ordenando a Lista----*/
				List<Estoque> listaAux = new ArrayList<>();
				for(Estoque itemAux : listaEstoque) {
					if(itemAux.getIdEstoque() == null) {
						listaAux.add(itemAux);
						listaEstoque.remove(itemAux);
					}
				}

				for(Estoque itemAux : listaEstoque) {
					listaAux.add(itemAux);
				}

				fileEstoque.alterarPlanilha(listaEstoque);

				criarTelaAtualizacao(spSecundaria, listaEstoque, fileEstoque, usuario);
			}
		});

		/*----------Finalizando a Tela----------*/
		hBoxMenu.getChildren().addAll(lblSelecao, mbtSelecao, btConfimar);
		hBoxMenu.setAlignment(Pos.CENTER);
		hBoxNomeProduto.getChildren().addAll(lblNomeProduto, tfNomeProduto);
		hBoxCategoria.getChildren().addAll(lblCategoria, tfCategoria);
		hBoxPericulosidade.getChildren().addAll(lblPericulosidade, mbtPericulosidade, lblPericulosidadeTipo, mbtPericulosidadeTipo);
		hBoxPericulosidadeOutros.getChildren().addAll(lblPericulosidadeOutros, tfPericulosidadeOutros);
		hBoxLocalUnidadeMedida.getChildren().addAll(lblLocalizacao, tfLocalizacao, lblUnidadeMedida, tfUnidadeMedida);
		hBoxQuantidade.getChildren().addAll(lblEstoqueMinimo, tfEstoqueMinimo, lblEstoque, tfEstoque);
		hBoxBotoes.getChildren().addAll(btDeletar, btAtualizar);
		hBoxBotoes.setAlignment(Pos.CENTER);
		vBoxPericulosidade.getChildren().addAll(lblPericulosidadeDefinicao, hBoxPericulosidade, hBoxPericulosidadeOutros);
		vBoxPericulosidade.setAlignment(Pos.CENTER);
		spPericulosidade.getChildren().addAll(retPericulosidade, vBoxPericulosidade);
		vBoxTelaAtualizacaoI.getChildren().addAll(hBoxMenu, vBoxTelaAtualizacaoII);
		vBoxTelaAtualizacaoI.setAlignment(Pos.CENTER);
		vBoxTelaAtualizacaoII.getChildren().addAll(hBoxNomeProduto, hBoxCategoria, spPericulosidade, hBoxLocalUnidadeMedida,
				hBoxQuantidade, lblSituacaoAtualizacao, hBoxBotoes);
		vBoxTelaAtualizacaoII.setAlignment(Pos.CENTER);
		vBoxTelaAtualizacaoII.setVisible(false);
		hBoxGeral.getChildren().addAll(ivFundo, vBoxTelaAtualizacaoI);
		hBoxGeral.setAlignment(Pos.CENTER_LEFT);
		spSecundaria.getChildren().clear();
		spSecundaria.getChildren().addAll(limitesRetangulo, hBoxGeral);

		return spSecundaria;
	}

	private static void atualizarSelecaoPericulosidadeGeral(Estoque itemAux, MenuButton mbtPericulosidade) {
		for(MenuItem miAux : mbtPericulosidade.getItems()) {
			if(miAux.getText().equals("Ambiental") && item.getPericulosidadeGeral().contains("Ambiental")) {
				((CheckMenuItem) miAux).setSelected(true);
			}
			if(miAux.getText().equals("Físico") && item.getPericulosidadeGeral().contains("Físico")) {
				((CheckMenuItem) miAux).setSelected(true);
			}
			if(miAux.getText().equals("Saúde") && item.getPericulosidadeGeral().contains("Saúde")) {
				((CheckMenuItem) miAux).setSelected(true);
			}
			if(miAux.getText().equals("Não Aplicável") && item.getPericulosidadeGeral().contains("Não Aplicável")) {
				((CheckMenuItem) miAux).setSelected(true);
			}
		}
		mbtPericulosidade.fire();
	}

	private static void atualizarSelecaoPericulosidadeEspecifica(Estoque itemAux, TextField tfPericulosidade,
			MenuButton mbtPericulosidade) {
		for(MenuItem miAux : mbtPericulosidade.getItems()) {
			if(conferirPericulosidadeEspecifica(miAux.getText(), item.getPericulosidadeEspecifica())){
				((CheckMenuItem) miAux).setSelected(true);
			}
		}
		if(!item.getPericulosidadeEspecifica().isEmpty() &&
				!item.getPericulosidadeEspecifica().getLast().equals(mbtPericulosidade.getItems().getLast().getText())) {
			tfPericulosidade.setText(item.getPericulosidadeEspecifica().getLast());
		}
		mbtPericulosidade.fire();
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

	private static boolean conferirPericulosidadeEspecifica(String texto, List<String> lista) {
		for(String conteudo : lista) {
			if(texto.equals(conteudo)) {
				return true;
			}
		}
		return false;
	}
}
