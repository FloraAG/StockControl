package projeto.estoque.application.excel.metodos.file;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileLayout {
	public static CellStyle criarEstiloHeader(CellStyle cellStyle, XSSFWorkbook workbook) {
		//Cor
		cellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		//Fonte
		XSSFFont fonte = workbook.createFont();
		fonte.setFontName("Times New Roman");
		fonte.setBold(true);
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setVerticalAlignment(org.apache.poi.ss.usermodel.VerticalAlignment.CENTER);
		cellStyle.setFont(fonte);
		//Bordas
		cellStyle.setBorderBottom(BorderStyle.THICK);
		cellStyle.setBorderTop(BorderStyle.THICK);

		return cellStyle;
	}

	public static CellStyle criarEstiloHeaderTitulos(CellStyle cellStyle, XSSFWorkbook workbook) {
		//Cor
		cellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		//Fonte
		XSSFFont fonte = workbook.createFont();
		fonte.setFontName("Times New Roman");
		fonte.setBold(true);
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setVerticalAlignment(org.apache.poi.ss.usermodel.VerticalAlignment.CENTER);
		cellStyle.setFont(fonte);
		//Bordas
		cellStyle.setBorderBottom(BorderStyle.THICK);
		cellStyle.setBorderTop(BorderStyle.THICK);

		return cellStyle;
	}

	public static CellStyle criarEstiloConteudos(CellStyle cellStyle, XSSFWorkbook workbook) {
		//Fonte
		XSSFFont fonte = workbook.createFont();
		fonte.setFontName("Times New Roman");
		cellStyle.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
		cellStyle.setVerticalAlignment(org.apache.poi.ss.usermodel.VerticalAlignment.CENTER);
		cellStyle.setFont(fonte);
		//Bordas
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderTop(BorderStyle.THIN);
		return cellStyle;
	}
}
