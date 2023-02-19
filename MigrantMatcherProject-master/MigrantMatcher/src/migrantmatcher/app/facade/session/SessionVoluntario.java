package migrantmatcher.app.facade.session;

import migrantmatcher.app.domain.catalogos.CatalogoRegiao;
import migrantmatcher.app.facade.controllers.RegistaAjudaHandler;
import migrantmatcher.app.facade.dto.VoluntarioDTO;

/**
 * Classe que representa a sessao de um Voluntario
 * 
 * @author Rui Martins 56283
 * @author Paulo Bolinhas 56300
 */
public class SessionVoluntario extends Session {

	private VoluntarioDTO currentVolDTO;

	/**
	 * construtor da sessao vol
	 * 
	 * @param currentVol - voluntario corrente
	 * @param catVol     - catalogo voluntarios
	 */
	public SessionVoluntario(VoluntarioDTO currentVol, CatalogoRegiao catRegiao) {
		super();
		this.currentVolDTO = currentVol;
		super.catRegiao = catRegiao;
	}

	public RegistaAjudaHandler getRegistaAjudaHandler() { //se o caso de uso nao necessitar de login o getHandler fica no sistema										
		return new RegistaAjudaHandler(catRegiao);				 
	}

	public VoluntarioDTO getVolDTO() {
		return this.currentVolDTO;
	}

	
}
