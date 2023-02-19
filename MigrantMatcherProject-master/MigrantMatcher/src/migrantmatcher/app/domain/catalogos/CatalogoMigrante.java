package migrantmatcher.app.domain.catalogos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import migrantmatcher.app.domain.Migrante;
import migrantmatcher.app.facade.dto.MigranteDTO;

/**
 * 
 * Catalogo de migrantes
 * 
 * @author Paulo Bolinhas fc56300
 * @author Rui Martins fc56283
 *
 */
public class CatalogoMigrante {

	private List<Migrante> catMigrante;
	private static CatalogoMigrante INSTANCE;

	/*
	 * construtor do catalogo de Migrante, que e um singleton
	 */
	private CatalogoMigrante() {
		this.catMigrante = new ArrayList<Migrante>();
	}

	/*
	 * funcao que retorna a instancia da classe caso ela exista, caso contrario,
	 * cria uma
	 */
	public static CatalogoMigrante getInstance() { // SINGLETON
		if (INSTANCE == null) {
			INSTANCE = new CatalogoMigrante();
		}
		return INSTANCE;
	}

	/*
	 * funcao que adiciona um Migrante ao catalogo de Migrantes
	 *
	 */
	public void addMigrante(String nome, String contacto, int numPessoas) {
		this.catMigrante.add(new Migrante(nome, contacto, numPessoas));
	}

	/**
	 * funcao que retorna o catalogo de Migrantes
	 * 
	 * @param contact - contacto do Migrante
	 * @return this.catMigrante.stream().filter(Migrante ->
	 *         contact.equals(Migrante.toString())) .collect(Collectors.toList()) -
	 *         catalogo de Migrantes
	 */
	private List<Migrante> getMigranteList(String contacto) {

		return this.catMigrante.stream().filter(Migrante -> contacto.equals(Migrante.getContacto()))
				.collect(Collectors.toList());
	}

	/**
	 * funcao que retorna o Migrante correspondente
	 * 
	 * @param contact - contacto do Migrante
	 * @return this.getMigranteList(contact).get(0) - Migrante
	 */
	public Migrante getMigrante(String contacto) {

		return this.getMigranteList(contacto).get(0);
	}

	/**
	 * funcao que retorna um optional Migrante DTO
	 * 
	 * @param contact - contacto do Migrante
	 * @return Optional.of(v) if v!= null, v = this.getMigrante(contact).toDTO()
	 */
	public Optional<MigranteDTO> getOptMigrante(String contacto, int numPessoas) {
		MigranteDTO m = this.getMigrante(contacto).toDTO(numPessoas);

		if (m == null)
			return Optional.empty();

		return Optional.of(m);
	}

	/**
	 * funcao que verifica se existe Migrante com contacto no catalogo
	 * 
	 * @param contact - contacto do Migrante
	 * @return true if exists
	 */
	public Boolean existsMigrante(String contacto) {

		for (Migrante m : this.catMigrante) {
			if (contacto.equals(m.getContacto()))
				return true;
		}

		return false;
	}

}
