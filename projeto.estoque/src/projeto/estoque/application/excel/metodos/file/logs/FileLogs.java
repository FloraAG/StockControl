package projeto.estoque.application.excel.metodos.file.logs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import projeto.estoque.application.excel.metodos.file.FileCRUD;
import projeto.estoque.application.excel.metodos.file.FileLayout;
import projeto.estoque.application.excel.metodos.file.FileProtected;
import projeto.estoque.application.objetos.Logs;

public class FileLogs implements FileCRUD<Logs>{

	@Override
	public List<Logs> transferirFileToList(XSSFWorkbook workbook, List<Logs> lista) {
		//Variáveis Excel
		final XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow linhaAux;
		//Transferência
		for(int i = 2; i <= sheet.getLastRowNum(); i++) {
			linhaAux = sheet.getRow(i);
			if(linhaAux != null) {

				final Logs logAux = new Logs();

				// Coluna 0 - ID
				XSSFCell cell = linhaAux.getCell(0);
				if(cell != null && cell.getCellType() == CellType.STRING) {
					logAux.setIdData(cell.getStringCellValue());
				} else {
					sheet.removeRow(linhaAux);
					if(i < sheet.getLastRowNum()) {
						sheet.shiftRows(i + 1, sheet.getLastRowNum() + 1, -1);
						i -= 1;
					}
				}

				// Coluna 1 - ID Produto
				cell = linhaAux.getCell(1);
				if(cell != null && cell.getCellType() == CellType.STRING) {
					logAux.setIdProduto(cell.getStringCellValue());
				}

				// Coluna 2 - Nome Produto
				cell = linhaAux.getCell(2);
				if(cell != null && cell.getCellType() == CellType.STRING) {
					logAux.setNomeProduto(cell.getStringCellValue());
				}

				// Coluna 3 - Quantidade Utilizada
				cell = linhaAux.getCell(3);
				if(cell != null && cell.getCellType() == CellType.NUMERIC) {
					logAux.setQuantidadeUtilizada(cell.getNumericCellValue());
				}

				// Coluna 4 - Nome Usuario
				cell = linhaAux.getCell(4);
				if(cell != null && cell.getCellType() == CellType.STRING) {
					logAux.setNomeUsuario(cell.getStringCellValue());
				}
				lista.add(logAux);
			}

		}

		/*-----Encerrando o procedimento-----*/
		try (FileOutputStream os = new FileOutputStream("planilhas/Logs.xlsx")) {
			workbook.write(os);
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileProtected.protegerFile(new File("planilhas/Logs.xlsx"));

		return lista;
	}

	@Override
	public XSSFWorkbook criarWorkBook(XSSFWorkbook workbook) {
		/*-----Variáveis-----*/
		XSSFSheet sheet = workbook.createSheet("Logs");
		XSSFRow linha = sheet.createRow(0);
		XSSFCell cell;
		CellStyle estiloHeader = FileLayout.criarEstiloHeader(workbook.createCellStyle(), workbook);
		CellStyle estiloHeaderTitulos = FileLayout.criarEstiloHeaderTitulos(workbook.createCellStyle(), workbook);

		/*-----Criando Header Geral-----*/
		cell = linha.createCell(0);
		cell.setCellValue("Logs");
		cell.setCellStyle(estiloHeader);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));

		/*-----Criando Header Títulos---*/
		linha = sheet.createRow(1);
		// Coluna 0
		cell = linha.createCell(0);
		cell.setCellValue("Data Alteração");
		//Coluna 1
		cell = linha.createCell(1);
		cell.setCellValue("ID Produto");
		//Coluna 2
		cell = linha.createCell(2);
		cell.setCellValue("Identificação");
		//Coluna 3
		cell = linha.createCell(3);
		cell.setCellValue("Quantidade Utilizada");
		//Coluna 4
		cell = linha.createCell(4);
		cell.setCellValue("Usuário");
		//Alterando o estilo da célula
		for(int i = 0; i < 5; i++) {
			linha.getCell(i).setCellStyle(estiloHeaderTitulos);
		}

		/*-----Alterando largura da coluna-----*/
		for(int i = 0; i < 5; i++) {
			sheet.autoSizeColumn(i);
		}

		try (FileOutputStream os = new FileOutputStream("planilhas/Logs.xlsx")) {
			workbook.write(os);
			os.close();
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return workbook;
	}

	@Override
	public Logs consultarUnicoItem(List<Logs> lista, String identificador) {
		Logs logAux = null;

		for(Logs logs : lista) {
			if(logs.getIdData().equals(identificador)) {
				logAux =  logs;
				break;
			}
		}

		return logAux;
	}

	@Override
	public List<Logs> alterarItemLista(List<Logs> list, Logs log, String id) {

		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getIdData().equals(id)) {
				list.add(i, log);
			}
		}

		return list;
	}

	@Override
	public void alterarPlanilha(List<Logs> list) {
		try {
			//Variáveis Execel
			final FileInputStream fis = new FileInputStream("planilhas/Logs.xlsx");
			XSSFWorkbook workbook = (XSSFWorkbook) WorkbookFactory.create(fis, "Definir a senha");
			XSSFSheet sheet = workbook.getSheetAt(0);
			XSSFRow linha;
			XSSFCell cell;
			CellStyle estilo = FileLayout.criarEstiloConteudos(workbook.createCellStyle(), workbook);

			//Tranferir valores List to Excel
			for(int i = 0; i < list.size(); i++) {
				linha = sheet.createRow(i + 2);

				if(i == list.size() - 1) {
					estilo.setBorderBottom(BorderStyle.THICK);
				}

				//Coluna 0
				cell = linha.createCell(0);
				cell.setCellValue(list.get(i).getIdData());

				//Coluna 1
				cell = linha.createCell(1);
				cell.setCellValue(list.get(i).getIdProduto());

				//Coluna 2
				cell = linha.createCell(2);
				cell.setCellValue(list.get(i).getNomeProduto());

				//Coluna 3
				cell = linha.createCell(3);
				cell.setCellValue(list.get(i).getQuantidadeUtilizada());

				//Coluna 4
				cell = linha.createCell(4);
				cell.setCellValue(list.get(i).getNomeUsuario());

				//Alterando o estilo da célula
				for(int j = 0; j < 5; j++) {
					linha.getCell(j).setCellStyle(estilo);
				}
			}

			/*-----Alterando largura da coluna-----*/
			for(int i = 0; i < 5; i++) {
				sheet.autoSizeColumn(i);
			}

			/*-----Encerrando o procedimento-----*/
			try (FileOutputStream os = new FileOutputStream("planilhas/Logs.xlsx")) {
				workbook.write(os);
				os.close();
				workbook.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (EncryptedDocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileProtected.protegerFile(new File("planilhas/Logs.xlsx"));

	}

}
