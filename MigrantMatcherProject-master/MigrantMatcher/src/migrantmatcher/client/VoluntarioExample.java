package migrantmatcher.client;

import java.util.Optional;
import java.util.Scanner;

import migrantmatcher.app.facade.MigrantMatcherSystem;
import migrantmatcher.app.facade.controllers.RegistaAjudaHandler;
import migrantmatcher.app.facade.session.SessionVoluntario;

/**
 * Main que representa o caso de uso Regista Ajuda realizado por um Voluntario
 * 
 * @author Rui Martins 56283
 * @author Paulo Bolinhas 56300
 */
public class VoluntarioExample {

	private static MigrantMatcherSystem mmSys = new MigrantMatcherSystem();

	/**
	 * main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Optional<SessionVoluntario> currentSession = mmSys.identificaVoluntario("25");
		Scanner sc = new Scanner(System.in);

		currentSession.ifPresent(s -> {

			RegistaAjudaHandler raHandler = s.getRegistaAjudaHandler();
			System.out.println("--- MIGRANTMATCHER ---\n\nQue tipo de ajuda pretender oferecer?");

			String ajudaRead = sc.next();

			if (ajudaRead.equals("item")) {

				mmSys.getCatAjuda().createItem(s.getVolDTO(), raHandler.indicaDescItem(sc));

			} else if (ajudaRead.equals("alojamento")) {

				int numPessoas = raHandler.indicaNumPessoas(sc);
				mmSys.printCatRegioes();
				mmSys.getCatAjuda().createAlojamento(s.getVolDTO(), numPessoas, raHandler.indicaRegiao(sc));

			} else {
				System.out.println("\nAjudas possiveis: item e alojamento");
				System.exit(0);
			}

			mmSys.sendSMS();

			if (mmSys.getPrivateCode().equals(raHandler.indicaCodigo(sc)))
				System.out.println("\nAjuda Registada com sucesso!!");
			else
				System.out.println("\nCodigo Errado");
		});
		
			mmSys.printCatAjudas();

		sc.close();
	}

}
