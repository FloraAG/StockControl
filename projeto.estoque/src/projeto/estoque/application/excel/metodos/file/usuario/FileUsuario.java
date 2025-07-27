package projeto.estoque.application.excel.metodos.file.usuario;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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

import projeto.estoque.application.dados.metodos.CriptografiaSenha;
import projeto.estoque.application.excel.metodos.file.FileCRUD;
import projeto.estoque.application.excel.metodos.file.FileLayout;
import projeto.estoque.application.excel.metodos.file.FileProtected;
import projeto.estoque.application.objetos.Usuario;

public class FileUsuario implements FileCRUD<Usuario> {

	@Override
	public List<Usuario> transferirFileToList(XSSFWorkbook workbook, List<Usuario> lista) {
		//Variáveis Excel
		final XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow linhaAux;
		XSSFCell cell;

		//Transferência
		for(int i = 2; i <= sheet.getLastRowNum(); i++) {
			linhaAux = sheet.getRow(i);

			if(linhaAux != null) {

				Usuario usuarioAux = new Usuario();

				//Coluna 0 - ID
				cell = linhaAux.getCell(0);
				if(cell != null && cell.getCellType() == CellType.STRING) {
					usuarioAux.setIdUsuario(cell.getStringCellValue());
				} else{
					cell = linhaAux.getCell(9);
					if(cell != null && !cell.getStringCellValue().isBlank()){
						DateTimeFormatter formatoCompleto = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
						LocalDate dataSaida = LocalDate.parse(cell.getStringCellValue(), formatoCompleto);
						LocalDate dataAtual = LocalDate.now();
						if (ChronoUnit.YEARS.between(dataSaida, dataAtual) >= 2) {
							sheet.removeRow(linhaAux);
							if(i != sheet.getLastRowNum() && i > 2) {
								sheet.shiftRows(i + 1, sheet.getLastRowNum(), -1);
								i -= 1;
							}
						}
					}
				}

				// Coluna 1 - Nome
				cell = linhaAux.getCell(1);
				if(cell != null && cell.getCellType() == CellType.STRING) {
					usuarioAux.setNomeUsuario(cell.getStringCellValue());
				}

				// Coluna 2 - Senha
				cell = linhaAux.getCell(2);
				if(cell != null && cell.getCellType() == CellType.STRING) {
					usuarioAux.setSenhaUsuario(cell.getStringCellValue());
				}

				// Coluna 3 - Orientação
				cell = linhaAux.getCell(3);
				if(cell != null && cell.getCellType() == CellType.STRING) {
					usuarioAux.setOrientacao(cell.getStringCellValue());
				}

				// Coluna 4 - Telefone
				cell = linhaAux.getCell(4);
				if(cell != null && cell.getCellType() == CellType.STRING) {
					usuarioAux.setTelefone(cell.getStringCellValue());
				}

				// Coluna 5 - Email
				cell = linhaAux.getCell(5);
				if(cell != null && cell.getCellType() == CellType.STRING) {
					usuarioAux.setEmail(cell.getStringCellValue());
				}

				// Coluna 6 - Perm Admin
				cell = linhaAux.getCell(6);
				if(cell != null) {
					usuarioAux.setPermAdministrador(cell.getBooleanCellValue());
				}

				// Coluna 7 - Perm Usuário
				cell = linhaAux.getCell(7);
				if(cell != null) {
					usuarioAux.setPermUsuario(cell.getBooleanCellValue());
				}

				// Coluna 8 - Data Cadastro
				cell = linhaAux.getCell(8);
				if(cell != null && cell.getCellType() == CellType.STRING) {
					usuarioAux.setDataEntrada(cell.getStringCellValue());
				}

				// Coluna 9 - Data Saida
				cell = linhaAux.getCell(9);
				if(cell != null && cell.getCellType() == CellType.STRING) {
					usuarioAux.setDataSaida(cell.getStringCellValue());
				}

				if(usuarioAux.getNomeUsuario() != null) {
					lista.add(usuarioAux);
				}
			}
		}

		/*-----Encerrando o procedimento-----*/
		try (FileOutputStream os = new FileOutputStream("planilhas/Usuários.xlsx")) {
			workbook.write(os);
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileProtected.protegerFile(new File("planilhas/Usuários.xlsx"));

		return lista;
	}

	@Override
	public XSSFWorkbook criarWorkBook(XSSFWorkbook workbook) {
		/*-----Variáveis-----*/
		XSSFSheet sheet = workbook.createSheet("Usuários");
		XSSFRow linha = sheet.createRow(0);
		XSSFCell cell;
		CellStyle estiloHeader = FileLayout.criarEstiloHeader(workbook.createCellStyle(), workbook);
		CellStyle estiloHeaderTitulos = FileLayout.criarEstiloHeaderTitulos(workbook.createCellStyle(), workbook);
		CellStyle estiloConteudo = FileLayout.criarEstiloConteudos(workbook.createCellStyle(), workbook);

		/*-----Criando Header Geral-----*/
		cell = linha.createCell(0);
		cell.setCellValue("Usuários Cadastrados");
		cell.setCellStyle(estiloHeader);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 9));

		/*-----Criando Header Títulos---*/
		linha = sheet.createRow(1);
		// Coluna 0
		cell = linha.createCell(0);
		cell.setCellValue("ID");
		//Coluna 1
		cell = linha.createCell(1);
		cell.setCellValue("Usuário");
		//Coluna 2
		cell = linha.createCell(2);
		cell.setCellValue("Senha");
		//Coluna 3
		cell = linha.createCell(3);
		cell.setCellValue("Orientação");
		//Coluna 4
		cell = linha.createCell(4);
		cell.setCellValue("Telefone");
		//Coluna 5
		cell = linha.createCell(5);
		cell.setCellValue("E-mail");
		//Coluna 6
		cell = linha.createCell(6);
		cell.setCellValue("Permissão de Administrador");
		//Coluna 7
		cell = linha.createCell(7);
		cell.setCellValue("Permissão de Usuário");
		//Coluna 8
		cell = linha.createCell(8);
		cell.setCellValue("Entrada");
		//Coluna 9
		cell = linha.createCell(9);
		cell.setCellValue("Saída");
		//Alterando o estilo da célula
		for(int i = 0; i < 10; i++) {
			linha.getCell(i).setCellStyle(estiloHeaderTitulos);
		}

		/*-----Criando Primeira Linha Conteudo-----*/
		linha = sheet.createRow(2);
		// Coluna 0
		cell = linha.createCell(0);
		cell.setCellValue("0");
		//Coluna 1
		cell = linha.createCell(1);
		cell.setCellValue("ADMINISTRADOR");
		//Coluna 2
		cell = linha.createCell(2);
		cell.setCellValue(CriptografiaSenha.criptografarSenha("Definir a senha"));
		//Coluna 3
		cell = linha.createCell(3);
		cell.setBlank();
		//Coluna 4
		cell = linha.createCell(4);
		cell.setBlank();
		//Coluna 5
		cell = linha.createCell(5);
		cell.setBlank();
		//Coluna 6
		cell = linha.createCell(6);
		cell.setCellValue(true);
		//Coluna 7
		cell = linha.createCell(7);
		cell.setCellValue(true);
		//Coluna 8
		cell = linha.createCell(8);
		cell.setBlank();
		//Coluna 9
		cell = linha.createCell(9);
		cell.setBlank();
		//Alterando o estilo da célula
		for(int i = 0; i < 10; i++) {
			linha.getCell(i).setCellStyle(estiloConteudo);
		}

		/*-----Alterando largura da coluna-----*/
		for(int i = 0; i < 10; i++) {
			sheet.autoSizeColumn(i);
		}

		try (FileOutputStream fos = new FileOutputStream("planilhas/Usuários.xlsx")) {
			workbook.write(fos);
			fos.close();
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return workbook;
	}

	@Override
	public Usuario consultarUnicoItem(List<Usuario> lista, String nome) {
		Usuario usuarioAux = null;

		for(int i = 0; i < lista.size(); i++) {
			if(lista.get(i).getIdUsuario() != null && lista.get(i).getNomeUsuario().equals(nome)) {
				usuarioAux = lista.get(i);
				break;
			}
		}
		return usuarioAux;
	}

	@Override
	public List<Usuario> alterarItemLista(List<Usuario> list, Usuario usuario, String id) {

		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getIdUsuario() != null && list.get(i).getIdUsuario().equals(id)) {
				list.add(i, usuario);
			}
		}

		return list;
	}

	@Override
	public void alterarPlanilha(List<Usuario> list) {
		try {
			//Variáveis Execel
			final FileInputStream fis = new FileInputStream("planilhas/Usuários.xlsx");
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
				cell.setCellValue(list.get(i).getIdUsuario());

				//Coluna 1
				cell = linha.createCell(1);
				cell.setCellValue(list.get(i).getNomeUsuario());

				//Coluna 2
				cell = linha.createCell(2);
				cell.setCellValue(list.get(i).getSenhaUsuario());

				//Coluna 3
				cell = linha.createCell(3);
				cell.setCellValue(list.get(i).getOrientacao());

				//Coluna 4
				cell = linha.createCell(4);
				cell.setCellValue(list.get(i).getTelefone());

				//Coluna 5
				cell = linha.createCell(5);
				cell.setCellValue(list.get(i).getEmail());

				//Coluna 6
				cell = linha.createCell(6);
				cell.setCellValue(list.get(i).isPermAdministrador());

				//Coluna 7
				cell = linha.createCell(7);
				cell.setCellValue(list.get(i).isPermUsuario());

				//Coluna 8
				cell = linha.createCell(8);
				cell.setCellValue(list.get(i).getDataEntrada());

				//Coluna 9
				cell = linha.createCell(9);
				cell.setCellValue(list.get(i).getDataSaida());

				//Alterando o estilo da célula
				for(int j = 0; j < 10; j++) {
					linha.getCell(j).setCellStyle(estilo);
				}
			}

			/*-----Alterando largura da coluna-----*/
			for(int i = 0; i < 10; i++) {
				sheet.autoSizeColumn(i);
			}

			/*-----Encerrando o procedimento-----*/
			try (FileOutputStream os = new FileOutputStream("planilhas/Usuários.xlsx")) {
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
		FileProtected.protegerFile(new File("planilhas/Usuários.xlsx"));

	}
}
