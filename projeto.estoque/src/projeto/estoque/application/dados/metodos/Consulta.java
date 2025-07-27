package projeto.estoque.application.dados.metodos;

import java.util.List;

import projeto.estoque.application.objetos.Estoque;
import projeto.estoque.application.objetos.Usuario;

public class Consulta {
	public static boolean consultarUsuarioExiste(List<Usuario> lista, String nomeInserido){

		for(int i = 0; i < lista.size(); i++) {
			if(lista.get(i).getIdUsuario() != null && lista.get(i).getNomeUsuario().equals(nomeInserido)) {
				return true;
			}
		}

		return false;
	}

	public static boolean consultarItemExiste(List<Estoque> lista, String nomeInserido){

		for(int i = 0; i < lista.size(); i++) {
			if(lista.get(i).getIdEstoque() != null && lista.get(i).getNomeProduto().equals(nomeInserido)) {
				return true;
			}
		}

		return false;
	}

	public static Usuario retornarUsuario(List<Usuario> lista, String nomeInserido){

		Usuario usuario = new Usuario();

		for(Usuario usuarioAux : lista) {
			if(usuarioAux.getNomeUsuario().equals(nomeInserido)) {
				usuario = usuarioAux;
			}
		}

		return usuario;
	}

	public static Estoque retornarEstoque(List<Estoque> lista, String nomeInserido){

		Estoque item = new Estoque();

		for(Estoque itemAux : lista) {
			if(itemAux.getNomeProduto().equals(nomeInserido)) {
				item = itemAux;
				return item;
			}
		}
		return null;
	}
}
