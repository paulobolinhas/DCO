package migrantmatcher.app.domain.observers;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um objeto observavel se o objeto a extender
 * 
 * @author Rui Martins 56283
 * @author Paulo Bolinhas 56300
 */
public class Observable<T extends IEvent> {
	
	private List<Observer<T>> observers = new ArrayList<>();
	
	protected void notifyAllObservers(T event) {
		for (Observer<T> o : observers) {
			o.receiveEvent(event);
		}
	}
	
	public void addObserver(Observer<T> o) {
		observers.add(o);
	}
}
