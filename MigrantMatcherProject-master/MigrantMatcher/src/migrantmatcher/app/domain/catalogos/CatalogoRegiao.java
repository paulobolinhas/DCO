package migrantmatcher.app.domain.catalogos;

import java.util.ArrayList;
import java.util.List;

import migrantmatcher.app.domain.Regiao;

/**
 * 
 * Catalogo de regioes
 * 
 * @author Paulo Bolinhas fc56300
 * @author Rui Martins fc56283
 *
 */
public class CatalogoRegiao {

	private List<Regiao> catRegiao;
	private static CatalogoRegiao INSTANCE;

	/*
	 * construtor do catalogo de regioes, que e um singleton
	 */
	private CatalogoRegiao() {
		this.catRegiao = new ArrayList<Regiao>();
	}

	/*
	 * funcao que retorna a instancia da classe caso ela exista, caso contrario,
	 * cria uma
	 */
	public static CatalogoRegiao getInstance() { // SINGLETON
		if (INSTANCE == null) {
			INSTANCE = new CatalogoRegiao();
		}
		return INSTANCE;
	}

	/**
	 * funcao que adiciona uma regiao ao catalogo de regioes
	 * 
	 * @param name - nome da regiao
	 */
	public void addRegiao(String name) {
		if (!this.existsRegiao(name))
			this.catRegiao.add(new Regiao(name));
	}

	/**
	 * funcao que ve se a regiao existe
	 * 
	 * @param name nome da regiao
	 * @return true if exists
	 */
	private Boolean existsRegiao(String name) {

		for (Regiao r : this.catRegiao) {
			if (name.equals(r.toString()))
				return true;
		}

		return false;
	}

	/**
	 * funcao que devolve a regiao correspondente do catalogo de regioes
	 * 
	 * @param name nome da regiao
	 * @return this.catRegiao.stream().filter(r ->
	 *         name.equals(r.toString())).findAny().orElse(null) - respetiva regiao
	 */
	public Regiao getRegiao(String name) {
		return this.catRegiao.stream().filter(r -> name.equals(r.toString())).findAny().orElse(null);
	}

	/**
	 * funcao que da print ao catalogo de regioes
	 */
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("\nCatalogo Regioes:\n");

		for (Regiao r : this.catRegiao) {
			if (this.catRegiao.get(this.catRegiao.size() - 1) != r)
				sb.append(r.toString() + " | ");
			else
				sb.append(r.toString() + "\n");
		}

		return sb.toString();

	}

}