package migrantmatcher.app.domain;

import migrantmatcher.app.facade.dto.VoluntarioDTO;

/**
 * Classe que representa um Alojamento (sbtipo do tipo Ajuda)
 * 
 * @author Paulo Bolinhas fc56300
 * @author Rui Martins fc56283
 *
 */
public class Alojamento extends Ajuda {

	private int numPessoas;
	private Regiao regiao;

	/**
	 * construtor de um alojamento
	 * 
	 * @param dono       - voluntarioDTO
	 * @param numPessoas - numero de pessoas para o alojamento
	 * @param regiao     - regiao do alojamento
	 */
	public Alojamento(VoluntarioDTO dono, int numPessoas, Regiao r) {
		super(dono);
		this.numPessoas = numPessoas;
		this.regiao = r;
	}

	/**
	 * funcao toString de um alojamento
	 */
	public String toString() {
		return super.toString() + "Ajuda: Alojamento\nRegiao: " + this.regiao + "\nNumPessoas: " + this.numPessoas
				+ "\nVoluntario Dono: " + super.voluntarioDono.toString() + "\n";
	}

	public String regiaoToString() {
		return this.regiao.toString();
	}

	public Boolean hasMigr() {
		return this.migranteAtual != null;
	}

	public int getNumPessoas() {
		return this.numPessoas;
	}

}