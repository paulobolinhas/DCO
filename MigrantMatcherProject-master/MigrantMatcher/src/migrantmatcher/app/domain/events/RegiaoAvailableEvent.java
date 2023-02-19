package migrantmatcher.app.domain.events;

import migrantmatcher.app.domain.observers.IEvent;

/**
 * Classe que representa um evento para quando já existem ajudas disponiveis numa regiao
 * 
 * @author Paulo Bolinhas fc56300
 * @author Rui Martins fc56283
 *
 */
public class RegiaoAvailableEvent implements IEvent {

	private String regiao;

	public RegiaoAvailableEvent(String regiao) {
		this.regiao = regiao;
	}

	public String getName() {
		return this.regiao;
	}
}