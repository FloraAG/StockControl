package projeto.estoque.application.telas;

import javafx.scene.layout.StackPane;

public class MetodoTrocaTela {

	private StackPane spPrincipal;

	public void iniciar(StackPane spPrincipal) {
		this.spPrincipal = spPrincipal;
	}

	public void mudarTela() {
		spPrincipal.getChildren().clear();
	}
}
