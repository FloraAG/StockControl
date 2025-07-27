package projeto.estoque.application.objetos;

import projeto.estoque.application.excel.metodos.file.estoque.FileEstoque;
import projeto.estoque.application.excel.metodos.file.logs.FileLogs;
import projeto.estoque.application.excel.metodos.file.usuario.FileUsuario;

public class ListaFiles {
	private FileUsuario fUsuario;
	private FileEstoque fEstoque;
	private FileLogs fLogs;

	public FileUsuario getfUsuario() {
		return fUsuario;
	}

	public void setfUsuario(FileUsuario fUsuario) {
		this.fUsuario = fUsuario;
	}

	public FileEstoque getfEstoque() {
		return fEstoque;
	}

	public void setfEstoque(FileEstoque fEstoque) {
		this.fEstoque = fEstoque;
	}

	public FileLogs getfLogs() {
		return fLogs;
	}

	public void setfLogs(FileLogs fLogs) {
		this.fLogs = fLogs;
	}

}
