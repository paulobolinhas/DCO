package migrantmatcher.app.domain.catalogos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import migrantmatcher.app.domain.Ajuda;
import migrantmatcher.app.domain.Alojamento;
import migrantmatcher.app.domain.Item;
import migrantmatcher.app.domain.Regiao;
import migrantmatcher.app.domain.events.RegiaoAvailableEvent;
import migrantmatcher.app.domain.observers.Observable;
import migrantmatcher.app.facade.dto.VoluntarioDTO;

/**
 * 
 * Catalogo de ajudas
 * 
 * @author Paulo Bolinhas fc56300
 * @author Rui Martins fc56283
 *
 */
public class CatalogoAjuda extends Observable<RegiaoAvailableEvent> { //Observable

	private List<Ajuda> catAjuda = new ArrayList<Ajuda>();
	private static CatalogoAjuda INSTANCE;
	private int counter = 0;
	private List<Item> items = new ArrayList<>();
	private List<Alojamento> alojamentos = new ArrayList<>();
	private List<String> regioesToNotify;
	
	/*
	 * construtor do catalogo de ajuda, que e um singleton
	 */
	private CatalogoAjuda() {
		this.catAjuda = new ArrayList<Ajuda>();
		this.regioesToNotify = new ArrayList<String>();
	}

	/*
	 * funcao que retorna a instancia da classe caso ela exista, caso contrario,
	 * cria uma
	 */
	public static CatalogoAjuda getInstance() { // SINGLETON
		if (INSTANCE == null) {
			INSTANCE = new CatalogoAjuda();
		}
		return INSTANCE;
	}
	
	/**
	 * funcao que da add as regioes para notificar quando esta nao esta contida na
	 * lista destas
	 * 
	 * @param regiao - Regiao
	 */
	public void addToNotify(String regiao) {
		if (!this.regioesToNotify.contains(regiao))
			this.regioesToNotify.add(regiao);
	}
	
	/**
	 * funcao que remove as regioes dispararam notificacao
	 * 
	 * @param regiao
	 */
	public void removeFromNotify(String regiao) {
		this.regioesToNotify.remove(regiao);
	}

	/**
	 * funcao que cria um item
	 * 
	 * @param volDTO - VoluntarioDTO
	 * @param desc   - descricao
	 */
	public void createItem(VoluntarioDTO volDTO, String desc) {
		Ajuda item = new Item(volDTO, desc);
		item.setID(counter++);
		this.items.add((Item) item);
		this.addAjuda(item);
	}
	
	/**
	 * funcao que cria um alojamento
	 * 
	 * @param volDTO     - VoluntarioDTO
	 * @param numPessoas - Numero de pessoas
	 * @param r          - Regiao
	 */
	public void createAlojamento(VoluntarioDTO volDTO, int numPessoas, Regiao r) {
		
		Ajuda aloj = new Alojamento(volDTO, numPessoas, r);
		aloj.setID(counter++);
		
		if (this.regioesToNotify.contains(r.toString())) {
			this.notifyAllObservers(new RegiaoAvailableEvent(r.toString()));
			this.regioesToNotify.remove(r.toString());
		}
				
		this.alojamentos.add((Alojamento) aloj);
		this.addAjuda(aloj);

	}
	
	/**
	 * funcao que da add a uma ajuda ao catalogo
	 * 
	 * @param ajuda - Ajuda
	 */
	public void addAjuda(Ajuda ajuda) {
		this.catAjuda.add(ajuda);
	}
	
	/**
	 * funcao que converte o catalogo de ajudas numa lista de ajudas
	 * 
	 * @param catAjuda - Catalogo de Ajudas
	 * @return result - Lista de ajudas
	 */
	public List<Ajuda> catalogoToList(CatalogoAjuda catAjuda) {
		List<Ajuda> result = new ArrayList<>();
		for (int i = 0; i < catAjuda.getSize(); i++) {
			result.add(i, this.catAjuda.get(i));
		}

		return result;
	}

	public boolean isAloj(Ajuda a) {
		return a.toString().contains("Ajuda: Alojamento");
	}

	public boolean isItem(Ajuda a) {
		return a.toString().contains("Ajuda: Item");
	}

	public void concatLists(CatalogoAjuda catAjuda) {
		this.catAjuda = Stream.concat(this.alojamentos.stream(), this.items.stream()).collect(Collectors.toList());
	}

	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("\nCatalogo Ajudas:\n\n");

		for (Ajuda a : this.catAjuda) {
			sb.append(a.toString() + "\n");
		}

		return sb.toString();
	}

	public int getSize() {
		return this.catAjuda.size();
	}
	
	/**
	 * funcao que da get das ajudas possiveis para cada regiao
	 * 
	 * @param regiao - Regiao
	 * @return Stream.concat(result.stream(),
	 *         this.items.stream()).collect(Collectors.toList()) - lista com as
	 *         ajudas possiveis da respectiva regiao
	 */
	public List<Ajuda> getAjudasPossiveis(String regiao) {

		List<Ajuda> result = new ArrayList<>();

		for (Alojamento a : this.alojamentos) {
			if (regiao.equals(a.regiaoToString()) && !a.hasMigr())
				result.add(a);
		}

		return Stream.concat(result.stream(), this.items.stream()).collect(Collectors.toList());
	}
	
	/**
	 * funcao que retorna a ajuda pelo respectivo ID
	 * 
	 * @param ID - ID da ajuda
	 * @return current - Ajuda
	 */
	public Ajuda getAjudaByID(int ID) {
		Ajuda current;
		for (int i = 0; i < this.getSize(); i++) {
			current = this.catAjuda.get(i);
			if (current.getID() == ID) {
				if (isAloj(current)) {
					this.regioesToNotify.remove(((Alojamento)current).regiaoToString());
				}
				return current;
			}
		}
		return null;
	}
}
