package projeto.estoque.application.excel.metodos.file;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public interface FileCRUD<T> {
	public abstract XSSFWorkbook criarWorkBook(XSSFWorkbook workbook);
	public abstract List<T> transferirFileToList(XSSFWorkbook workbook, List<T> lista);
	public abstract T consultarUnicoItem(List<T> lista, String identificador);
	public abstract List<T> alterarItemLista(List<T> list, T item, String id);
	public abstract void alterarPlanilha(List<T> list);
}
