package projeto.estoque.application.dados.metodos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import projeto.estoque.application.objetos.Estoque;
import projeto.estoque.application.objetos.Usuario;

public class DadosInseridos {
	/*-----Correção de Dados-----*/
	public static String corrigeTelefone(String telefone) {
		telefone = telefone.replaceAll("[^\\d]", "");

		if (!telefone.startsWith("0")) {
			telefone = "0" + telefone;
		}

		if (telefone.length() < 11 || telefone.length() >  12 || (telefone.length() == 12 && telefone.charAt(3) != '9')) {
			return telefone = null;
		} else if (telefone.length() == 12) {
			return telefone.replaceAll("(\\d{3})(\\d{5})(\\d{4})", "($1) $2-$3");
		} else {
			return telefone.replaceAll("(\\d{3})(\\d{4})(\\d{4})", "($1) $2-$3");
		}
	}

	private static String corrigirIniciaisOritentacao(String nomeCompleto) {

		String nomeSemTitulos = nomeCompleto.replaceAll("(?i)\\b(prof|profa|dr|dra)\\b\\.?\\s*", "");
		String[] partes = nomeSemTitulos.trim().split("\\s+");

		StringBuilder iniciais = new StringBuilder();

		for (String parte : partes) {
			if (!parte.matches("(?i)\\b(d[aeo]s?|e)\\b") && !parte.isEmpty()) {
				iniciais.append(Character.toUpperCase(parte.charAt(0)));
			}
		}

		return iniciais.toString();
	}

	private static String corrigirIniciaisEstoque(Estoque item) {

		String[] partesPerigo = item.getPericulosidadeGeral().toString().trim().split("\\s+");
		String partesUnidadeMedida = item.getUnidadeMedida();
		StringBuilder iniciais = new StringBuilder();

		for (String parte : partesPerigo) {
			iniciais.append(Character.toUpperCase(parte.charAt(1)));
		}

		iniciais.append(Character.toUpperCase(partesUnidadeMedida.charAt(0)));
		iniciais.append(Character.toUpperCase(partesUnidadeMedida.charAt(1)));

		return iniciais.toString();
	}

	public static double corrigirDouble(String texto) {
		texto = texto.replace(",", ".");

		if(checarNumeroValido(texto)) {
			return Double.parseDouble(texto);
		} else {
			return 0;
		}
	}

	public static List<Estoque> corrigirIDEstoque(List<Estoque> listaEstoque, String id){
		List<Estoque> listaAuxiliar = new ArrayList<>();

		for(Estoque itemAux : listaEstoque) {
			if(itemAux.getIdEstoque() != null && itemAux.getIdEstoque().startsWith(id)) {
				listaAuxiliar.add(itemAux);
			}
		}

		id = id.replaceAll("\\d", "");

		for(int i = 0; i < listaAuxiliar.size() ; i++) {
			if(i < 10) {
				listaAuxiliar.get(i).setIdEstoque(id + 0 + (i+1));
			} else {
				listaAuxiliar.get(i).setIdEstoque(id + (i+1));
			}
		}

		return listaEstoque;
	}

	/*-----Checagem de Dados-----*/
	public static boolean checarEmailValido(String email) {
		String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

		if(email.matches(regex)) {
			return true;
		}

		return false;
	}

	private static boolean checarNumeroValido(String texto) {
		if(texto.matches("\\d.*")) {
			return true;
		} else {
			return false;
		}
	}

	/*-----Retornar ID-----*/
	public static String retornarIDUsuario(List<Usuario> listaUsuario, String orientacao) {
		String id;
		String iniciais = corrigirIniciaisOritentacao(orientacao);
		List<String> iniciaisID = new ArrayList<>();

		if(!iniciais.isBlank()) {
			for(Usuario usuario : listaUsuario) {
				if(usuario.getIdUsuario() != null && usuario.getIdUsuario().startsWith(iniciais)) {
					iniciaisID.add(usuario.getIdUsuario());
					iniciaisID.sort(Comparator.naturalOrder());
				}
			};
		}

		int numero = 0;

		if(!iniciaisID.isEmpty()) {
			numero = Integer.parseInt(iniciaisID.getLast().replaceAll("[^\\d]", ""));
		}

		numero += 1;

		if(numero < 10) {
			id = iniciais + "0" + numero;
		} else {
			id = iniciais + numero;
		}

		return id;
	}

	public static List<Estoque> retornarIDEstoque(List<Estoque> listaEstoque, Estoque item) {
		String id;
		id = corrigirIniciaisEstoque(item);
		List<String> iniciaisID = new ArrayList<>();

		for(Estoque itemAux : listaEstoque) {
			if(itemAux.getIdEstoque() != null && itemAux.getIdEstoque().startsWith(id)) {
				iniciaisID.add(itemAux.getIdEstoque());
				iniciaisID.sort(Comparator.naturalOrder());
			}
		};

		int numero = 0;

		if(!iniciaisID.isEmpty()) {
			numero = Integer.parseInt(iniciaisID.getLast().replaceAll("[^\\d]", ""));
		}

		numero += 1;

		if(numero < 10) {
			id = id + "0" + numero;
		} else {
			id = id + numero;
		}


		item.setIdEstoque(id);
		listaEstoque.add(item);
		listaEstoque.sort(Comparator.comparing(Estoque::getIdEstoque));

		listaEstoque = corrigirIDEstoque(listaEstoque, id);

		return listaEstoque;
	}
}
