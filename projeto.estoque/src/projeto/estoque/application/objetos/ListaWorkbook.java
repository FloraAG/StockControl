package projeto.estoque.application.objetos;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ListaWorkbook {
	private XSSFWorkbook workbookUsuario;
	private XSSFWorkbook workbookEstoque;
	private XSSFWorkbook workbookLogs;

	public XSSFWorkbook getWorkbookUsuario() {
		return workbookUsuario;
	}

	public void setWorkbookUsuario(XSSFWorkbook workbookUsuario) {
		this.workbookUsuario = workbookUsuario;
	}

	public XSSFWorkbook getWorkbookEstoque() {
		return workbookEstoque;
	}

	public void setWorkbookEstoque(XSSFWorkbook workbookEstoque) {
		this.workbookEstoque = workbookEstoque;
	}

	public XSSFWorkbook getWorkbookLogs() {
		return workbookLogs;
	}

	public void setWorkbookLogs(XSSFWorkbook workbookLogs) {
		this.workbookLogs = workbookLogs;
	}
}
