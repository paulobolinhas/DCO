package migrantmatcher.app.domain.observers;

/**
 * Interface que representa um objeto observavel se o objeto a implementar
 * 
 * @author Rui Martins 56283
 * @author Paulo Bolinhas 56300
 */
public interface Observer<T extends IEvent> {
	public void receiveEvent(T e);
}

