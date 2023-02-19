package migrantmatcher.app.facade.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import migrantmatcher.app.domain.Ajuda;
import migrantmatcher.app.domain.Migrante;
import migrantmatcher.app.domain.catalogos.CatalogoMigrante;
import migrantmatcher.app.facade.dto.MigranteDTO;

/**
 * Handler para o caso de uso Procurar Ajuda
 * 
 * @author Rui Martins 56283
 * @author Paulo Bolinhas 56300
 */
public class ProcuraAjudaHandler {

	private CatalogoMigrante catMigr;
	private Migrante currentMigrant;
	private List<Ajuda> currentAjudas;

	public ProcuraAjudaHandler(String contactDTO, CatalogoMigrante catMigr) {
		this.catMigr = catMigr;
		this.currentMigrant = this.catMigr.getMigrante(contactDTO);
		this.currentAjudas = new ArrayList<>();
	}

	public List<Ajuda> getCurrentAjudas() {
		return this.currentAjudas;
	}

	public void indicaMembro(String name) {
		this.currentMigrant.addMembroFam(name);
	}

	public String indicaRegiao(Scanner sc) {
		return sc.next();
	}

	public void indicaAjuda(Ajuda ajuda) {
		this.currentAjudas.add(ajuda);
	}

	public void confirmarAjudas(MigranteDTO migrDTO) {
		for (Ajuda a : this.currentAjudas)
			a.setMigrante(migrDTO);
	}
}
