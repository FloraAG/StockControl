module projeto.estoque {
	requires transitive javafx.graphics;
	requires transitive javafx.controls;
	requires javafx.fxml;
	requires org.apache.poi.poi;
	requires transitive org.apache.poi.ooxml;
	requires jbcrypt;

	opens projeto.estoque.application.objetos to javafx.base;
	opens projeto.estoque.application.telas to javafx.fxml;

	exports projeto.estoque.application.excel.metodos.file.estoque;
	exports projeto.estoque.application.excel.metodos.file.logs;
	exports projeto.estoque.application.excel.metodos.file.usuario;
	exports projeto.estoque.application;
	exports projeto.estoque.application.objetos;
	exports projeto.estoque.application.telas;
}