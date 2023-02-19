package migrantmatcher.app.domain;

import java.util.ArrayList;
import java.util.List;

import migrantmatcher.app.facade.dto.MigranteDTO;

/**
 * Classe que representa um Migrante
 * 
 * @author Paulo Bolinhas fc56300
 * @author Rui Martins fc56283
 *
 */
public class Migrante {

	private String nome;
	private String contacto;
	private int numPessoas;
	private List<String> familia;

	/**
	 * construtor de um migrante
	 * 
	 * @param nome       - nome do migrante
	 * @param contacto   - contacto do migrante
	 * @param numPessoas - numero de pessoas
	 */
	public Migrante(String nome, String contacto, int numPessoas) {
		this.nome = nome;
		this.contacto = contacto;
		this.numPessoas = numPessoas;

		if (this.numPessoas > 0)
			this.familia = new ArrayList<String>();

	}

	public void addMembroFam(String membro) {
		this.familia.add(membro);
	}

	public List<String> getFam() {
		return this.familia;
	}

	/**
	 * funcao get do contacto do migranteDTO
	 * 
	 * @return this.numTelefone - contacto do migranteDTO
	 */
	public String getContacto() {
		return this.contacto;
	}

	/**
	 * funcao que torna um migrante em migranteDTO
	 * 
	 * @return new MigranteDTO(this.contact) - migranteDTO
	 */
	public MigranteDTO toDTO(int numPessoas) {
		return new MigranteDTO(this.nome, this.contacto, numPessoas);
	}

	public int getNumPessoas() {
		return this.getNumPessoas();
	}

}