package projeto.estoque.application;

public class Principal extends InterfacePrincipal {

	public static void main(String[] args) {
		System.setProperty("org.apache.logging.log4j.simplelog.StatusLogger.level", "OFF");
		System.setProperty("jdk.gtk.verbose", "false");
		System.setProperty("jdk.gtk.version", "2");
		InterfacePrincipal.launch(args);
	}
}
