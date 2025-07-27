package projeto.estoque.application.excel.metodos.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import projeto.estoque.application.objetos.ListaFiles;
import projeto.estoque.application.objetos.ListaWorkbook;

public class FileOpen {

	public static void criarFile(ListaFiles listaFiles) {
		final File diretorio = new File("planilhas");
		if(!diretorio.exists()) {
			diretorio.mkdirs();
		}

		if(diretorio.listFiles() == null || diretorio.listFiles().length < 3) {
			if(!new File(diretorio, "Usuários.xlsx").exists()) {
				listaFiles.getfUsuario().criarWorkBook(new XSSFWorkbook());
				FileProtected.protegerFile(new File(diretorio, "Usuários.xlsx"));
			}
			if(!new File(diretorio, "Estoque.xlsx").exists()) {
				listaFiles.getfEstoque().criarWorkBook(new XSSFWorkbook());
				FileProtected.protegerFile(new File(diretorio, "Estoque.xlsx"));
			}
			if(!new File(diretorio, "Logs.xlsx").exists()) {
				listaFiles.getfLogs().criarWorkBook(new XSSFWorkbook());
				FileProtected.protegerFile(new File(diretorio, "Logs.xlsx"));
			}
		}
	}

	public static ListaWorkbook abrirFile(ListaWorkbook listaWorkbook) {
		try {
			final FileInputStream fis1 = new FileInputStream("planilhas/Usuários.xlsx");
			listaWorkbook.setWorkbookUsuario((XSSFWorkbook) WorkbookFactory.create(fis1, "Definir a senha"));
			final FileInputStream fis2 = new FileInputStream("planilhas/Estoque.xlsx");
			listaWorkbook.setWorkbookEstoque((XSSFWorkbook) WorkbookFactory.create(fis2, "Definir a senha"));
			final FileInputStream fis3 = new FileInputStream("planilhas/Logs.xlsx");
			listaWorkbook.setWorkbookLogs((XSSFWorkbook) WorkbookFactory.create(fis3, "Definir a senha"));

			fis1.close();
			fis2.close();
			fis3.close();
		} catch (final IOException | EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listaWorkbook;
	}

}
