package projeto.estoque.application.objetos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Estoque {
	private String idEstoque;
	private String nomeProduto;
	private String categoria;
	private List<String> periculosidadeGeral = new ArrayList<>();
	private List<String> periculosidadeEspecifica = new ArrayList<>();
	private String localizacao;
	private String unidadeMedida;
	private double quantidadeMinima;
	private double quantidadeAtual;
	private String dataAlteracao;
	private String nomeUsuario;


	public String getIdEstoque() {
		return idEstoque;
	}

	public void setIdEstoque(String idEstoque) {
		this.idEstoque = idEstoque;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public List<String> getPericulosidadeGeral() {
		return periculosidadeGeral;
	}

	public void setPericulosidadeGeral(List<String> periculosidadeGeral) {
		this.periculosidadeGeral = periculosidadeGeral;
	}

	public List<String> getPericulosidadeEspecifica() {
		return periculosidadeEspecifica;
	}

	public void setPericulosidadeEspecifica(List<String> periculosidadeEspecifica) {
		this.periculosidadeEspecifica = periculosidadeEspecifica;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public double getQuantidadeMinima() {
		return quantidadeMinima;
	}

	public void setQuantidadeMinima(double quantidadeMinima) {
		this.quantidadeMinima = quantidadeMinima;
	}

	public double getQuantidadeAtual() {
		return quantidadeAtual;
	}

	public void setQuantidadeAtual(double quantidadeAtual) {
		this.quantidadeAtual = quantidadeAtual;
	}

	public String getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(String dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public void addPericulosidadeGeral(String... niveis) {
		periculosidadeGeral.addAll(Arrays.asList(niveis));
	}

	public void addPericulosidadeEspecifica(String... niveis) {
		periculosidadeEspecifica.addAll(Arrays.asList(niveis));
	}
}
