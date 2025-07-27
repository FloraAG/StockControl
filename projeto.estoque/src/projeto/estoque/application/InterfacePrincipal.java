package projeto.estoque.application;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import projeto.estoque.application.telas.MetodoTrocaTela;
import projeto.estoque.application.telas.TelaLogin;

public class InterfacePrincipal extends Application {

	Rectangle2D limitesTela = Screen.getPrimary().getVisualBounds();

	@Override
	public void start(Stage palcoPrincipal) throws Exception {
		StackPane spPrincipal = new StackPane();
		MetodoTrocaTela mtt = new MetodoTrocaTela();
		mtt.iniciar(spPrincipal);
		spPrincipal.getStyleClass().add("fundo-geral");

		TelaLogin.criarTelaLogin(spPrincipal, limitesTela);

		final Scene cenaPrincipal = new Scene(spPrincipal, limitesTela.getWidth(), limitesTela.getHeight());

		cenaPrincipal.getStylesheets().add("data:text/css," + String.format(".fonte-geral{"
				+ "	-fx-font-family: 'Times New Roman';"
				+ "	-fx-font-size: %.2fpx;"
				+ "	-fx-text-fill: black;"
				+ "	-fx-alignment: center;"
				+ "}", limitesTela.getWidth() * 0.0117));
		cenaPrincipal.getStylesheets().add("data:text/css," + String.format(".tooltip{"
				+ "-fx-font-family: 'Times New Roman';"
				+ "-fx-font-size: %.2fpx;"
				+ "-fx-text-fill: black;"
				+ "-fx-text-alignment: center;"
				+ "-fx-show-delay: 50ms;"
				+ "-fx-show-duration: 10000ms;"
				+ "}", limitesTela.getWidth() * 0.0117));
		cenaPrincipal.getStylesheets().add("data:text/css," + String.format(".fonte-geral.tela-principal{"
				+ "	-fx-font-size: %.2fpx;"
				+ "}", limitesTela.getWidth() * 0.0183));
		cenaPrincipal.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
		palcoPrincipal.setScene(cenaPrincipal);
		palcoPrincipal.setTitle("StockControl");
		palcoPrincipal.show();
	}

}
