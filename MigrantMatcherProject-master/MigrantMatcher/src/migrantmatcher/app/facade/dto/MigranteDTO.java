package migrantmatcher.app.facade.dto;

/**
 * Fachada para o Migrante
 * 
 * @author Rui Martins 56283
 * @author Paulo Bolinhas 56300
 */
public class MigranteDTO {

	private String name;
	private String contact;
	private int numPessoas;

	public MigranteDTO(String name, String contact, int numPessoas) {
		this.name = name;
		this.contact = contact;
		this.numPessoas = numPessoas;
	}

	public String toString() {
		return "Nome: " + this.name + " Contacto: " + this.contact + "\n";
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getContact() {
		return this.contact;
	}
	
	public int getNumPessoas() {
		return this.numPessoas;
	}

}
