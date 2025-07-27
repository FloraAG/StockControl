package projeto.estoque.application.objetos;

import java.util.ArrayList;
import java.util.List;

public class ListaObjetos {
	private  List<Usuario> listaUsuario = new ArrayList<>();
	private  List<Estoque> listaEstoque = new ArrayList<>();
	private  List<Logs> listaLogs = new ArrayList<>();

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public List<Estoque> getListaEstoque() {
		return listaEstoque;
	}

	public void setListaEstoque(List<Estoque> listaEstoque) {
		this.listaEstoque = listaEstoque;
	}

	public List<Logs> getListaLogs() {
		return listaLogs;
	}

	public void setListaLogs(List<Logs> listaLogs) {
		this.listaLogs = listaLogs;
	}
}
