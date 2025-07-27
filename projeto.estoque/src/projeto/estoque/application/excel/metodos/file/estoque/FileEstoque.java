package projeto.estoque.application.excel.metodos.file.estoque;

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
import projeto.estoque.application.objetos.Estoque;

public class FileEstoque implements FileCRUD<Estoque>{

	@Override
	public List<Estoque> transferirFileToList(XSSFWorkbook workbook, List<Estoque> lista) {
		//Variáveis Excel
		final XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow linhaAux;
		//Transferência
		for(int i = 2; i <= sheet.getLastRowNum(); i++) {
			linhaAux = sheet.getRow(i);
			if(linhaAux != null) {

				final Estoque itemAux = new Estoque();

				// Coluna 0 - ID
				XSSFCell cell = linhaAux.getCell(0);
				if(cell != null && cell.getCellType() == CellType.STRING) {
					itemAux.setIdEstoque(cell.getStringCellValue());
				} else {
					sheet.removeRow(linhaAux);
					if(i < sheet.getLastRowNum()) {
						sheet.shiftRows(i + 1, sheet.getLastRowNum() + 1, -1);
						i -= 1;
					}
				}

				// Coluna 1 - Nome Produto
				cell = linhaAux.getCell(1);
				if(cell != null && cell.getCellType() == CellType.STRING) {
					itemAux.setNomeProduto(cell.getStringCellValue());
				}

				// Coluna 2 - Categoria
				cell = linhaAux.getCell(2);
				if(cell != null && cell.getCellType() == CellType.STRING) {
					itemAux.setCategoria(cell.getStringCellValue());
				}

				// Coluna 3 - Periculosidade Geral
				cell = linhaAux.getCell(3);
				if(cell != null) {
					final String[] periculosidade = cell.getStringCellValue().split("/n");
					itemAux.addPericulosidadeGeral(periculosidade);
				}

				// Coluna 4 - Periculosidade Especifica
				cell = linhaAux.getCell(4);
				if(cell != null) {
					final String[] periculosidade = cell.getStringCellValue().split("/n");
					itemAux.addPericulosidadeEspecifica(periculosidade);
				}

				// Coluna 5 - Localização
				cell = linhaAux.getCell(5);
				if(cell != null && cell.getCellType() == CellType.STRING) {
					itemAux.setLocalizacao(cell.getStringCellValue());
				}

				// Coluna 6 - Unidade de Medida
				cell = linhaAux.getCell(6);
				if(cell != null && cell.getCellType() == CellType.STRING) {
					itemAux.setUnidadeMedida(cell.getStringCellValue());
				}

				// Coluna 7 - Quantidade Minima
				cell = linhaAux.getCell(7);
				if(cell != null && cell.getCellType() == CellType.NUMERIC) {
					itemAux.setQuantidadeMinima(cell.getNumericCellValue());
				}

				// Coluna 8 - Quantidade Atual
				cell = linhaAux.getCell(8);
				if(cell != null && cell.getCellType() == CellType.NUMERIC) {
					itemAux.setQuantidadeAtual(cell.getNumericCellValue());
				}

				// Coluna 9 - Data Alteração
				cell = linhaAux.getCell(9);
				if(cell != null && cell.getCellType() == CellType.STRING) {
					itemAux.setDataAlteracao(cell.getStringCellValue());
				}

				// Coluna 10 - Nome Usuário
				cell = linhaAux.getCell(10);
				if(cell != null && cell.getCellType() == CellType.STRING) {
					itemAux.setNomeUsuario(cell.getStringCellValue());
				}

				if(itemAux.getNomeProduto() != null) {
					lista.add(itemAux);
				}
			}

		}

		/*-----Encerrando o procedimento-----*/
		try (FileOutputStream os = new FileOutputStream("planilhas/Estoque.xlsx")) {
			workbook.write(os);
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileProtected.protegerFile(new File("planilhas/Estoque.xlsx"));

		return lista;
	}

	@Override
	public XSSFWorkbook criarWorkBook(XSSFWorkbook workbook) {
		/*-----Variáveis-----*/
		XSSFSheet sheet = workbook.createSheet("Estoque");
		XSSFRow linha = sheet.createRow(0);
		XSSFCell cell;
		CellStyle estiloHeader = FileLayout.criarEstiloHeader(workbook.createCellStyle(), workbook);
		CellStyle estiloHeaderTitulos = FileLayout.criarEstiloHeaderTitulos(workbook.createCellStyle(), workbook);

		/*-----Criando Header Geral-----*/
		cell = linha.createCell(0);
		cell.setCellValue("Estoque");
		cell.setCellStyle(estiloHeader);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 10));

		/*-----Criando Header Títulos---*/
		linha = sheet.createRow(1);
		// Coluna 0
		cell = linha.createCell(0);
		cell.setCellValue("ID Produto");
		//Coluna 1
		cell = linha.createCell(1);
		cell.setCellValue("Identificação");
		//Coluna 2
		cell = linha.createCell(2);
		cell.setCellValue("Categoria");
		//Coluna 3
		cell = linha.createCell(3);
		cell.setCellValue("Tipo de Periculosidade");
		//Coluna 4
		cell = linha.createCell(4);
		cell.setCellValue("Classificação de Periculosidade");
		//Coluna 5
		cell = linha.createCell(5);
		cell.setCellValue("Localização");
		//Coluna 6
		cell = linha.createCell(6);
		cell.setCellValue("Unidade de Medida");
		//Coluna 7
		cell = linha.createCell(7);
		cell.setCellValue("Quantidade Mínima");
		//Coluna 8
		cell = linha.createCell(8);
		cell.setCellValue("Quantidade Atual");
		//Coluna 9
		cell = linha.createCell(9);
		cell.setCellValue("Última Alteração");
		//Coluna 10
		cell = linha.createCell(10);
		cell.setCellValue("Usuário");
		//Alterando o estilo da célula
		for(int i = 0; i < 11; i++) {
			linha.getCell(i).setCellStyle(estiloHeaderTitulos);
		}

		/*-----Alterando largura da coluna-----*/
		for(int i = 0; i < 11; i++) {
			sheet.autoSizeColumn(i);
		}

		try (FileOutputStream os = new FileOutputStream("planilhas/Estoque.xlsx")) {
			workbook.write(os);
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return workbook;
	}

	@Override
	public Estoque consultarUnicoItem(List<Estoque> lista, String produto) {
		Estoque estoqueAux = null;

		for(Estoque estoque : lista) {
			if(estoque.getNomeUsuario().equals(produto)) {
				estoqueAux = estoque;
				break;
			}
		}

		return estoqueAux;
	}

	@Override
	public List<Estoque> alterarItemLista(List<Estoque> list, Estoque item, String nome) {
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getIdEstoque().equals(nome)) {
				list.add(i, item);
			}
		}
		return list;
	}

	@Override
	public void alterarPlanilha( List<Estoque> list) {
		try {
			//Variáveis Execel
			final FileInputStream fis = new FileInputStream("planilhas/Estoque.xlsx");
			XSSFWorkbook workbook = (XSSFWorkbook) WorkbookFactory.create(fis, "rkIX)-Tw");
			XSSFSheet sheet = workbook.getSheetAt(0);
			XSSFRow linha;
			XSSFCell cell;
			CellStyle estilo = FileLayout.criarEstiloConteudos(workbook.createCellStyle(), workbook);
			//Variáveis Não Excel
			List<String> periculosidade;
			String valorConcatenado;

			//Tranferir valores List to Excel
			for(int i = 0; i < list.size(); i++) {
				linha = sheet.createRow(i + 2);

				if(i == list.size() - 1) {
					estilo.setBorderBottom(BorderStyle.THICK);
				}

				//Coluna 0
				cell = linha.createCell(0);
				cell.setCellValue(list.get(i).getIdEstoque());

				//Coluna 1
				cell = linha.createCell(1);
				cell.setCellValue(list.get(i).getNomeProduto());

				//Coluna 2
				cell = linha.createCell(2);
				cell.setCellValue(list.get(i).getCategoria());

				//Coluna 3
				periculosidade = list.get(i).getPericulosidadeGeral();
				valorConcatenado = String.join("/n", periculosidade);
				cell = linha.createCell(3);
				cell.setCellValue(valorConcatenado);

				//Coluna 4
				periculosidade = list.get(i).getPericulosidadeEspecifica();
				valorConcatenado = String.join("/n", periculosidade);
				cell = linha.createCell(4);
				cell.setCellValue(valorConcatenado);

				//Coluna 5
				cell = linha.createCell(5);
				cell.setCellValue(list.get(i).getLocalizacao());

				//Coluna 6
				cell = linha.createCell(6);
				cell.setCellValue(list.get(i).getUnidadeMedida());

				//Coluna 7
				cell = linha.createCell(7);
				cell.setCellValue(list.get(i).getQuantidadeMinima());

				//Coluna 8
				cell = linha.createCell(8);
				cell.setCellValue(list.get(i).getQuantidadeAtual());

				//Coluna 9
				cell = linha.createCell(9);
				cell.setCellValue(list.get(i).getDataAlteracao());

				//Coluna 10
				cell = linha.createCell(10);
				cell.setCellValue(list.get(i).getNomeUsuario());

				//Alterando o estilo da célula
				for(int j = 0; j < 11; j++) {
					linha.getCell(j).setCellStyle(estilo);
				}
			}

			/*-----Alterando largura da coluna-----*/
			for(int i = 0; i < 11; i++) {
				sheet.autoSizeColumn(i);
			}

			/*-----Encerrando o procedimento-----*/
			try (FileOutputStream os = new FileOutputStream("planilhas/Estoque.xlsx")) {
				workbook.write(os);
				workbook.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (EncryptedDocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileProtected.protegerFile(new File("planilhas/Estoque.xlsx"));
	}
}
