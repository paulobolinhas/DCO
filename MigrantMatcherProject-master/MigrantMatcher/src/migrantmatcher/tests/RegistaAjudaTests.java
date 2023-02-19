package migrantmatcher.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import migrantmatcher.app.facade.MigrantMatcherSystem;
import migrantmatcher.app.facade.session.SessionVoluntario;

class RegistaAjudaTests {

	private static MigrantMatcherSystem mmSys = new MigrantMatcherSystem();

	@Test
	void identificaVoluntarioTest() {
		Optional<SessionVoluntario> currentSession = mmSys.identificaVoluntario("25");

		currentSession.ifPresent(s -> {

			assertEquals("25", s.getVolDTO().getContact());
		});
	}

	@Test
	void itemCreatedTest() {

		Optional<SessionVoluntario> currentSession = mmSys.identificaVoluntario("25");

		currentSession.ifPresent(s -> {

			mmSys.getCatAjuda().createItem(s.getVolDTO(), "sapatos");

			assertEquals(true, mmSys.getCatAjuda().toString().contains("Dono: " + s.getVolDTO().toString()));
			assertEquals(true, mmSys.getCatAjuda().toString().contains("Descricao: sapatos"));
		});
	}

	@Test
	void alojCreatedTest() {

		Optional<SessionVoluntario> currentSession = mmSys.identificaVoluntario("25");

		currentSession.ifPresent(s -> {

			// int numPessoas = 3; //equivalente a indicaNumPessoas(3);
			mmSys.getCatAjuda().createAlojamento(s.getVolDTO(), 3, mmSys.getCatRegiao().getRegiao("Porto"));

			assertEquals(true, mmSys.getCatAjuda().toString().contains("Dono: " + s.getVolDTO().toString()));
			assertEquals(true, mmSys.getCatAjuda().toString().contains("NumPessoas: 3"));
			assertEquals(true, mmSys.getCatAjuda().toString().contains("Descricao: sapatos"));
		});
	}

}
