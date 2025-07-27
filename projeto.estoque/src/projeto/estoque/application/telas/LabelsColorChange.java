package projeto.estoque.application.telas;

import javafx.scene.control.Label;

public class LabelsColorChange {
	public static Label trocarCorLabel(Label lblOriginal) {
		lblOriginal.getStyleClass().addAll("borda", "white");
		return lblOriginal;
	}
}
