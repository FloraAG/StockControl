package projeto.estoque.application.dados.metodos;

import org.mindrot.jbcrypt.BCrypt;

public class CriptografiaSenha {
	public static String criptografarSenha(String senhaInserida) {
		return new String(BCrypt.hashpw(senhaInserida, BCrypt.gensalt()));
	}

	public static boolean verificarSenhas(String senhaInserida, String senhaCriptografada) {
		return BCrypt.checkpw(senhaInserida, senhaCriptografada);
	}
}
