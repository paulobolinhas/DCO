package migrantmatcher.app.facade.dto;

/**
 * Fachada para o Voluntario
 * 
 * @author Rui Martins 56283
 * @author Paulo Bolinhas 56300
 */
public class VoluntarioDTO {

	private String contact;

	public VoluntarioDTO(String contact) {
		this.contact = contact;
	}

	public String toString() {
		return this.contact;
	}

	public String getContact() {
		return this.contact;
	}
}
