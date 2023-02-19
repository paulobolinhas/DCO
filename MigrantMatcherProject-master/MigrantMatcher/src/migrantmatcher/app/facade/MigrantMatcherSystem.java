package migrantmatcher.app.facade;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.pidgeonsmssender.sdk.PidgeonSMSSender;

import migrantmatcher.app.domain.Ajuda;
import migrantmatcher.app.domain.Alojamento;
import migrantmatcher.app.domain.Item;
import migrantmatcher.app.domain.Voluntario;
import migrantmatcher.app.domain.catalogos.CatalogoAjuda;
import migrantmatcher.app.domain.catalogos.CatalogoMigrante;
import migrantmatcher.app.domain.catalogos.CatalogoRegiao;
import migrantmatcher.app.domain.catalogos.CatalogoVoluntario;
import migrantmatcher.app.domain.events.RegiaoAvailableEvent;
import migrantmatcher.app.domain.factory.SortStrategyFactory;
import migrantmatcher.app.domain.observers.Observer;
import migrantmatcher.app.facade.dto.MigranteDTO;
import migrantmatcher.app.facade.dto.VoluntarioDTO;
import migrantmatcher.app.facade.session.SessionMigrante;
import migrantmatcher.app.facade.session.SessionVoluntario;
import migrantmatcher.app.ordenarStrategies.ISortAjudaStrategy;

/**
 * Classe que representa o sistema Migrant Matcher
 * 
 * @author Rui Martins 56283
 * @author Paulo Bolinhas 56300
 */
public class MigrantMatcherSystem implements Observer<RegiaoAvailableEvent> {

	private CatalogoVoluntario catVoluntario;
	private CatalogoAjuda catAjuda;
	private CatalogoRegiao catRegiao;
	private CatalogoMigrante catMigrante;
	private ISortAjudaStrategy ordStrategy;
	private PidgeonSMSSender smsService;
	private String codeToSend;

	/**
	 * construtor do sistema que contem todos os catalogos e ainda voluntarios,
	 * regioes e ajuda já pre-definidas
	 */
	public MigrantMatcherSystem() {

		this.catVoluntario = CatalogoVoluntario.getInstance();
		this.catRegiao = CatalogoRegiao.getInstance();
		this.catAjuda = CatalogoAjuda.getInstance();
		this.catMigrante = CatalogoMigrante.getInstance();
		this.smsService = new PidgeonSMSSender();

//		this.sensores.add();

		SortStrategyFactory f = SortStrategyFactory.getInstance();
		this.ordStrategy = f.getOrdStrategy();

		this.catVoluntario.addVoluntario("1");
		this.catVoluntario.addVoluntario("2");

		this.catRegiao.addRegiao("Lisboa");
		this.catRegiao.addRegiao("Porto");
		this.catRegiao.addRegiao("Portalegre");
		this.catRegiao.addRegiao("Estremoz");

		this.catAjuda.createItem(new Voluntario("3").toDTO(), "meias");
		this.catAjuda.createAlojamento(new Voluntario("4").toDTO(), 2, this.catRegiao.getRegiao("Lisboa"));
		this.catAjuda.createAlojamento(new Voluntario("5").toDTO(), 3, this.catRegiao.getRegiao("Lisboa"));
		this.catAjuda.createAlojamento(new Voluntario("6").toDTO(), 6, this.catRegiao.getRegiao("Lisboa"));
		this.catAjuda.createItem(new Voluntario("7").toDTO(), "t-shirt");
		this.catAjuda.createAlojamento(new Voluntario("8").toDTO(), 4, this.catRegiao.getRegiao("Porto"));

	}

	/*
	 * Cria uma sessao para um voluntario, que pode ou não existir. Se não
	 * existir, é criado
	 * 
	 * @param name - nome do voluntario
	 * 
	 * @return uma sessao opcional
	 */
	public Optional<SessionVoluntario> identificaVoluntario(String contact) {

		if (!this.catVoluntario.existsVol(contact)) {
			this.catVoluntario.addVoluntario(contact);
		}

		Optional<VoluntarioDTO> optVol = this.catVoluntario.getOptVoluntario(contact);

		return optVol.map(v -> new SessionVoluntario(v, catRegiao));
	}

	/*
	 * Cria uma sessao para um migrante, que pode ou não existir. Se não existir,
	 * é criado
	 * 
	 * @param name - nome do migrante
	 * 
	 * @param contact - contacto do migrante
	 * 
	 * @param numPessoas - numero de pessoas da familia do migrante/cabeca de casal
	 * 
	 * @return uma sessao opcional
	 */
	public Optional<SessionMigrante> registaMigrante(String name, String contact, int numPessoas) {

		if (!this.catMigrante.existsMigrante(contact)) {
			this.catMigrante.addMigrante(name, contact, numPessoas);
		}

		Optional<MigranteDTO> optMigr = this.catMigrante.getOptMigrante(contact, numPessoas);

		return optMigr.map(m -> new SessionMigrante(m, this.catMigrante));
	}

	public CatalogoAjuda getCatAjuda() {
		return this.catAjuda;
	}

	public CatalogoRegiao getCatRegiao() {
		return this.catRegiao;
	}

	public CatalogoMigrante getCatMigr() {
		return this.catMigrante;
	}

	public void printCatRegioes() {
		System.out.println(this.catRegiao.toString());
	}

	public void printCatAjudas() {
		this.ordStrategy.ordAjudas(catAjuda);
		System.out.println(this.catAjuda.toString());
	}

	public void getAjudasPossives(String regiao, Scanner sc) {
		List<Ajuda> result = this.ordStrategy.ordAjudasPossiveis(this.catAjuda, regiao);

		if (!this.containsAloj(result)) {
			System.out.println(
					"--- Nao existem alojamentos nesta regiao. Prentende ser notificado quando houver? Ainda pode escolher qualquer Item. Digite sim --- " );
			if (sc.next().equals("sim"))
				this.pretendeNotificao(regiao);
		} else {
			System.out.println("--- Ajudas disponiveis para a regiao que escolheu ---");
		}
		
		for (Ajuda a : result) {
			if (this.catAjuda.isAloj(a))
				System.out.println("\nAjuda: Alojamento\nID: " + a.getID() + " | Dono: " + a.getDono().toString()
						+ " | Regiao: " + ((Alojamento) a).regiaoToString() + " | Numero de pessoas que alberga: "
						+ ((Alojamento) a).getNumPessoas());
			else
				System.out.println("\nAjuda: Item\nID: " + a.getID() + " | Dono: " + a.getDono().toString()
						+ " | Descricao: " + ((Item) a).getDescricao());
		}
	}

	public Boolean containsAloj(List<Ajuda> listAjuda) {
		Boolean result = false;
		for (Ajuda a : listAjuda) {
			if (this.catAjuda.isAloj(a))
				return true;
		}

		return result;
	}

	public void sendSMS() {
		this.codeToSend = generateRandomCode(3);
		smsService.send("Codigo de verificao", codeToSend);
	}

	public String getPrivateCode() {
		return this.codeToSend;
	}

	private String generateRandomCode(int l) {
		String AlphaNumericStr = "0123456789";
		StringBuilder s = new StringBuilder(l);

		for (int i = 0; i < l; i++) {
			int ch = (int) (AlphaNumericStr.length() * Math.random());
			s.append(AlphaNumericStr.charAt(ch));
		}

		return s.toString();
	}

	@Override
	public void receiveEvent(RegiaoAvailableEvent e) {
		this.smsService.send("Atualizacao de Ajudas", "Um Alojamento na regiao que pediu (" + e.getName() + ") ja esta disponivel ");

	}

	public void pretendeNotificao(String regiao) {
		this.catAjuda.addToNotify(regiao);
		this.catAjuda.addObserver(this);
	}
}
