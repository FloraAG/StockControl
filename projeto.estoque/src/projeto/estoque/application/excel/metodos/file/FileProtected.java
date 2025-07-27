package projeto.estoque.application.excel.metodos.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionMode;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class FileProtected {

	public static void protegerFile(File planilha) {
		try(POIFSFileSystem fs = new POIFSFileSystem()){
			final EncryptionInfo info = new EncryptionInfo(EncryptionMode.agile);
			final Encryptor encryptor = info.getEncryptor();
			encryptor.confirmPassword("Definir a senha");

			try (OPCPackage opc = OPCPackage.open(planilha, PackageAccess.READ_WRITE);
					OutputStream os = encryptor.getDataStream(fs)) {
				opc.save(os);
			} catch (InvalidFormatException | GeneralSecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try (FileOutputStream fos = new FileOutputStream(planilha)) {
				fs.writeFilesystem(fos);
			}

		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
