package migrantmatcher.app.ordenarStrategies;

import java.util.List;

import migrantmatcher.app.domain.Ajuda;
import migrantmatcher.app.domain.catalogos.CatalogoAjuda;

/**
 * Interface que representa uma estrategia de ordenação de ajudas
 * Podendo ordenar o catalogo inteiro ou so de algumas ajudas possiveis
 * 
 * @author Rui Martins 56283
 * @author Paulo Bolinhas 56300
 */
public interface ISortAjudaStrategy {
	
	public void ordAjudas(CatalogoAjuda catAjuda);
	
	
	public List<Ajuda> ordAjudasPossiveis(CatalogoAjuda catAjuda, String regiao);
}
