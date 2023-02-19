package migrantmatcher.app.facade.session;

import migrantmatcher.app.domain.catalogos.CatalogoMigrante;
import migrantmatcher.app.facade.controllers.ProcuraAjudaHandler;
import migrantmatcher.app.facade.dto.MigranteDTO;

/**
 * Classe que representa a sessao de um Migrante
 * 
 * @author Rui Martins 56283
 * @author Paulo Bolinhas 56300
 */
public class SessionMigrante extends Session {
	
	private MigranteDTO currentMigrDTO;
	private CatalogoMigrante catMigr;
	
	/**
	 * construtor da sessao migr
	 * 
	 * @param currentVol - voluntario corrente
	 * @param catVol     - catalogo voluntarios
	 */
	public SessionMigrante(MigranteDTO currentMigr, CatalogoMigrante catMigr) {
		super();
		this.currentMigrDTO = currentMigr;
		this.catMigr = catMigr;
	}
	
	public ProcuraAjudaHandler getProcuraAjudaHandler() {
		return new ProcuraAjudaHandler(this.currentMigrDTO.getContact(),this.catMigr);
	}

	public MigranteDTO getMigrDTO() {
		return this.currentMigrDTO;
	}
	
	public int getNumPessoas() {
		return this.currentMigrDTO.getNumPessoas();
	}
	
	
}
