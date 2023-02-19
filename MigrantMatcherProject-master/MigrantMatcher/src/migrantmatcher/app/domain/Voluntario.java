package migrantmatcher.app.domain;

import migrantmatcher.app.facade.dto.VoluntarioDTO;

/**
 * Classe que representa um Voluntario
 * 
 * @author Paulo Bolinhas fc56300
 * @author Rui Martins fc56283
 *
 */
public class Voluntario {

	private String contact;

	/**
	 * construtor do voluntario
	 * 
	 * @param contact - contacto do voluntario
	 */
	public Voluntario(String contact) {
		this.contact = contact;
	}

	/**
	 * funcao toString de um voluntario
	 */
	public String toString() {
		return this.contact;
	}

	/**
	 * funcao get do contacto do voluntario
	 * 
	 * @return this.contact - contacto do voluntario
	 */
	public String getContact() {
		return this.contact;
	}

	/**
	 * funcao que torna um voluntario em voluntarioDTO
	 * 
	 * @return new VoluntarioDTO(this.contact) - voluntarioDTO
	 */
	public VoluntarioDTO toDTO() {
		return new VoluntarioDTO(this.contact);
	}

}