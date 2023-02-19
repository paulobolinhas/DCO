package migrantmatcher.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import migrantmatcher.app.facade.MigrantMatcherSystem;
import migrantmatcher.app.facade.session.SessionMigrante;

class ProcuraAjudaTests {

	private static MigrantMatcherSystem mmSys = new MigrantMatcherSystem();

	@Test
	void registaMigranteTest() {
		Optional<SessionMigrante> currentSession = mmSys.registaMigrante("Paulo", "7", 3);

		currentSession.ifPresent(s -> {

			assertEquals("Paulo", s.getMigrDTO().getName());
			assertEquals("7", s.getMigrDTO().getContact());
			assertEquals(3, s.getMigrDTO().getNumPessoas());

		});
	}

	@Test
	void indicaMembroTest() {
		Optional<SessionMigrante> currentSession = mmSys.registaMigrante("Paulo", "7", 3);

		currentSession.ifPresent(s -> {

			s.getProcuraAjudaHandler().indicaMembro("Rui");

			assertEquals(true, mmSys.getCatMigr().getMigrante("7").getFam().contains("Rui"));

		});
	}

	@Test
	void ajudasPossiveisTest() {
		Optional<SessionMigrante> currentSession = mmSys.registaMigrante("Paulo", "7", 3);

		currentSession.ifPresent(s -> {

			mmSys.getAjudasPossives("Lisboa", new Scanner(System.in));

			assertEquals(true, mmSys.getCatAjuda().getAjudasPossiveis("Lisboa") != null);

		});
	}

	@Test
	void indicaAjudaTest() {
		Optional<SessionMigrante> currentSession = mmSys.registaMigrante("Paulo", "7", 3);

		currentSession.ifPresent(s -> {

			s.getProcuraAjudaHandler().indicaAjuda(mmSys.getCatAjuda().getAjudaByID(2));

			assertEquals(true, mmSys.getCatAjuda().getAjudaByID(2) != null);

		});
	}

	void confirmaAjudasTests() {
		Optional<SessionMigrante> currentSession = mmSys.registaMigrante("Paulo", "7", 3);

		currentSession.ifPresent(s -> {

			s.getProcuraAjudaHandler().confirmarAjudas(s.getMigrDTO());

			assertEquals(true, s.getProcuraAjudaHandler().getCurrentAjudas() != null);

		});
	}

}
