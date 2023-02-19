package migrantmatcher.app.domain;

import migrantmatcher.app.facade.dto.VoluntarioDTO;

/**
 * Classe que representa um item (subtipo do tipo Ajuda)
 * 
 * @author Paulo Bolinhas fc56300
 * @author Rui Martins fc56283
 *
 */
public class Item extends Ajuda {

	private String descricao;

	/**
	 * construtor de um item
	 * 
	 * @param dono - voluntarioDTO
	 * @param d    - descricao do item
	 */
	public Item(VoluntarioDTO dono, String d) {
		super(dono);
		this.descricao = d;
	}

	/**
	 * funcao toString de um item
	 */
	public String toString() {
		return super.toString() + "Ajuda: Item\nDescricao: " + this.descricao + "\nVoluntario Dono: "
				+ super.voluntarioDono.toString() + "\n";
	}

	public String getDescricao() {
		return this.descricao;
	}

}