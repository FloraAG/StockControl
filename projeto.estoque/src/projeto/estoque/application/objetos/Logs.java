package projeto.estoque.application.objetos;

public class Logs {
	private String idData;
	private String idProduto;
	private String nomeProduto;
	private double quantidadeUtilizada;
	private String nomeUsuario;

	public String getIdData() {
		return idData;
	}

	public void setIdData(String idData) {
		this.idData = idData;
	}

	public String getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(String idProduto) {
		this.idProduto = idProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public double getQuantidadeUtilizada() {
		return quantidadeUtilizada;
	}

	public void setQuantidadeUtilizada(double quantidadeUtilizada) {
		this.quantidadeUtilizada = quantidadeUtilizada;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
}
