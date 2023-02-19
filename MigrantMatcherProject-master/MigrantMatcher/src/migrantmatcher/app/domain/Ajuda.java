package migrantmatcher.app.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import migrantmatcher.app.facade.dto.MigranteDTO;
import migrantmatcher.app.facade.dto.VoluntarioDTO;

/**
 * Classe que representa uma Ajuda
 * 
 * @author Paulo Bolinhas fc56300
 * @author Rui Martins fc56283
 *
 */
public class Ajuda {

	public int ID;
	protected VoluntarioDTO voluntarioDono;
	protected MigranteDTO migranteAtual; // mudar para tipo Migrante
	private long date;

	/**
	 * construtor de uma ajuda
	 * 
	 * @param dono          - voluntarioDTO
	 * @param migranteAtual - migrante atual
	 */
	public Ajuda(VoluntarioDTO dono) {
		this.voluntarioDono = dono;
		this.date = System.currentTimeMillis();
	}

	/**
	 * funcao que transforma uma data numa string
	 * 
	 * @return sdf.format(resultdate) - formato string desejado da data
	 */
	public String dateToString() {
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss:SSS");
		Date resultdate = new Date(this.date);
		return sdf.format(resultdate);
	}

	public String toString() {
		if (this.migranteAtual == null) {
			return "ID: " + this.ID + " | Dono: " + this.voluntarioDono + " | Migrante Atual: Disponivel" + " | Data: "
					+ this.dateToString() + "\n";
		} else {
			return "ID: " + this.ID + " | Dono: " + this.voluntarioDono + " | Migrante Atual: "
					+ this.migranteAtual.getName() + " | Data: " + this.dateToString() + "\n";
		}
	}

	public long getTime() {
		return this.date;
	}

	public void setMigrante(MigranteDTO m) {
		this.migranteAtual = m;
	}

	public void setID(int n) {
		this.ID = n;
	}

	public int getID() {
		return this.ID;
	}

	public VoluntarioDTO getDono() {
		return this.voluntarioDono;
	}

}