package migrantmatcher.app.ordenarStrategies;

import java.util.List;

import migrantmatcher.app.domain.Ajuda;
import migrantmatcher.app.domain.catalogos.CatalogoAjuda;

/**
 * Classe que implementa uma estrategia de ordenação de ajudas
 * Podendo ordenar o catalogo inteiro ou so de algumas ajudas possiveis
 * 
 * @author Rui Martins 56283
 * @author Paulo Bolinhas 56300
 */
public class SortByAlojItem implements ISortAjudaStrategy {

	@Override
	public void ordAjudas(CatalogoAjuda catAjuda) {

		catAjuda.concatLists(catAjuda);

	}

	@Override
	public List<Ajuda> ordAjudasPossiveis(CatalogoAjuda catAjuda, String regiao) {
		return catAjuda.getAjudasPossiveis(regiao);
	}

}
