package migrantmatcher.app.domain;

/**
 * Classe que representa uma Regiao
 * 
 * @author Paulo Bolinhas fc56300
 * @author Rui Martins fc56283
 *
 */
public class Regiao {

	private String name;

	/**
	 * construtor da Regiao
	 * 
	 * @param name - nome da regiao
	 */
	public Regiao(String name) {
		this.name = name;
	}

	/**
	 * funcao toString de uma regiao
	 */
	public String toString() {
		return this.name;
	}

}