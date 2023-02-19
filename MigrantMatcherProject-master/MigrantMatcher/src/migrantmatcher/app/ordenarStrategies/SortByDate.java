package migrantmatcher.app.ordenarStrategies;

import java.util.Comparator;

import migrantmatcher.app.domain.Ajuda;

/**
 * Classe usada para comparar duas ajudas em termos de registos temporais
 * 
 * @author Rui Martins 56283
 * @author Paulo Bolinhas 56300
 */
public class SortByDate implements Comparator<Ajuda>{
	// Used for sorting in ascending order of
	// roll number
	public int compare(Ajuda a, Ajuda b)
	{
		return (int) (a.getTime() - b.getTime());
	}
}
