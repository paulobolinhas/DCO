package migrantmatcher.client;

import java.util.Optional;
import java.util.Scanner;

import migrantmatcher.app.domain.Voluntario;
import migrantmatcher.app.facade.MigrantMatcherSystem;
import migrantmatcher.app.facade.controllers.ProcuraAjudaHandler;
import migrantmatcher.app.facade.session.SessionMigrante;

/**
 * Main que representa o caso de uso Procura Ajuda realizado por um Migrante
 * 
 * @author Rui Martins 56283
 * @author Paulo Bolinhas 56300
 */
public class MigranteExample {

	private static MigrantMatcherSystem mmSys = new MigrantMatcherSystem();

	/**
	 * main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int numPessoas = 0;

		System.out.println("--- MIGRANTMATCHER ---\n\nPretende registar-se individualmente ou a sua familia?");
		String registro = sc.next();

		if (registro.equals("familia")) {

			System.out.println("\n--- Indique o numero de pessoas da sua familia: ---");
			numPessoas = sc.nextInt();

		} else if (!registro.equals("individualmente")) {

			System.out.println("--- Registros possiveis: individualmente e familia\n ---");
			System.exit(0);

		}

		System.out.println("\n--- Insira os seus dados ---");
		System.out.println("Indique o seu nome:");
		String name = sc.next();
		System.out.println("Indique o seu contacto:");
		String contact = sc.next();

		Optional<SessionMigrante> currentSession = mmSys.registaMigrante(name, contact, numPessoas);

		currentSession.ifPresent(s -> {

			ProcuraAjudaHandler paHandler = s.getProcuraAjudaHandler();
			if (registro.equals("familia")) {
				int i = 0;
				String currentMembroFam;
				System.out.println("\n--- Insira os dados da sua familia ---");
				while (i < s.getNumPessoas()) {
					System.out.println("\nInsira o nome do membro " + (i + 1) + " da sua familia");
					currentMembroFam = sc.next();
					paHandler.indicaMembro(currentMembroFam);
					i++;
				}
			}

			System.out.println("\n--- Vai se poder mover para as seguintes regioes ---");
			mmSys.printCatRegioes();

			System.out.println("Escolha agora a regiao:");
			String regiaoEscolhida = paHandler.indicaRegiao(sc);

			
			mmSys.getAjudasPossives(regiaoEscolhida, sc);

			Boolean need = true;

			while (need) {
				System.out.println("\n--- Se quiser uma ajuda, selecione o respectivo ID, senao insira '-1' ---");

				int possivelAjuda = sc.nextInt();

				if (possivelAjuda == -1) {
					need = false;
				} else {
					paHandler.indicaAjuda(mmSys.getCatAjuda().getAjudaByID(possivelAjuda));
				}
			}

			System.out.println("\n--- Prentende confirmar as ajudas? Digite sim ---");
			if (sc.next().equals("sim"))
				paHandler.confirmarAjudas(s.getMigrDTO());
			else
				System.out.println("Registado que nao quer ser notificado\n");

			mmSys.getCatAjuda().createAlojamento(new Voluntario("7").toDTO(), 9,mmSys.getCatRegiao().getRegiao("Portalegre"));
			mmSys.getCatAjuda().createAlojamento(new Voluntario("9").toDTO(), 9,mmSys.getCatRegiao().getRegiao("Portalegre"));
			mmSys.getCatAjuda().createAlojamento(new Voluntario("10").toDTO(), 2,mmSys.getCatRegiao().getRegiao("Estremoz"));


		});

		sc.close();
	}

}
