package projeto.estoque.application.objetos;

public class Usuario {
	private String idUsuario;
	private String nomeUsuario;
	private String senhaUsuario;
	private String orientacao;
	private String telefone;
	private String email;
	private boolean permAdministrador;
	private boolean permUsuario;
	private String dataEntrada;
	private String dataSaida;

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenhaUsuario() {
		return senhaUsuario;
	}

	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}

	public String getOrientacao() {
		return orientacao;
	}

	public void setOrientacao(String orientacao) {
		this.orientacao = orientacao;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isPermAdministrador() {
		return permAdministrador;
	}

	public void setPermAdministrador(boolean permAdministrador) {
		this.permAdministrador = permAdministrador;
	}

	public boolean isPermUsuario() {
		return permUsuario;
	}

	public void setPermUsuario(boolean permUsuario) {
		this.permUsuario = permUsuario;
	}

	public String getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public String getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(String dataSaida) {
		this.dataSaida = dataSaida;
	}
}
