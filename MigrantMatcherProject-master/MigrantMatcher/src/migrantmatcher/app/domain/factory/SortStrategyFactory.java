package migrantmatcher.app.domain.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import migrantmatcher.app.ordenarStrategies.ISortAjudaStrategy;
import migrantmatcher.app.ordenarStrategies.SortByCrescDate;

/**
 * Factory de estrategias de ordenacao
 * 
 * @author Paulo Bolinhas fc56300
 * @author Rui Martins fc56283
 *
 */
public class SortStrategyFactory {

	private static SortStrategyFactory INSTANCE = null;

	protected SortStrategyFactory() {
		// EMPTY ON PURPOSE
	}

	public static SortStrategyFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new SortStrategyFactory();
		}
		return INSTANCE;
	}

	/**
	 * funcao que usa reflection para obter o nome da estrategia em tempo de
	 * execucao
	 * 
	 * @return (OrdAjudaStrategy) construtor.newInstance() - strategy constructor ou
	 *         o default caso haja excecao - new DateCres()
	 */
	public ISortAjudaStrategy getOrdStrategy() {
		Properties p = new Properties();
		try {

			p.load(new FileInputStream(new File("defaults.properties")));
			String className = p.getProperty("ord_ajuda_strategy");
			
			@SuppressWarnings("unchecked")
			Class<ISortAjudaStrategy> klass = (Class<ISortAjudaStrategy>) Class.forName(className);
			Constructor<ISortAjudaStrategy> construtor = klass.getConstructor();
			return (ISortAjudaStrategy) construtor.newInstance();

		} catch (IOException | ClassNotFoundException | NoSuchMethodException | SecurityException
				| InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			return new SortByCrescDate();
		}

	}
}