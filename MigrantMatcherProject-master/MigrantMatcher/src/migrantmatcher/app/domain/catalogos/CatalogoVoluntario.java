package migrantmatcher.app.domain.catalogos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import migrantmatcher.app.domain.Voluntario;
import migrantmatcher.app.facade.dto.VoluntarioDTO;

/**
 * 
 * Catalogo de voluntarios
 * 
 * @author Paulo Bolinhas fc56300
 * @author Rui Martins fc56283
 *
 */
public class CatalogoVoluntario {

	private List<Voluntario> catVoluntario;
	private static CatalogoVoluntario INSTANCE;

	/*
	 * construtor do catalogo de voluntario, que e um singleton
	 */
	private CatalogoVoluntario() {
		this.catVoluntario = new ArrayList<Voluntario>();
	}

	/*
	 * funcao que retorna a instancia da classe caso ela exista, caso contrario,
	 * cria uma
	 */
	public static CatalogoVoluntario getInstance() { // SINGLETON
		if (INSTANCE == null) {
			INSTANCE = new CatalogoVoluntario();
		}
		return INSTANCE;
	}

	/*
	 * funcao que adiciona um voluntario ao catalogo de voluntarios
	 *
	 */
	public void addVoluntario(String contact) {
		this.catVoluntario.add(new Voluntario(contact));
	}

	/**
	 * funcao que retorna o catalogo de voluntarios
	 * 
	 * @param contact - contacto do voluntario
	 * @return this.catVoluntario.stream().filter(voluntario ->
	 *         contact.equals(voluntario.toString())) .collect(Collectors.toList())
	 *         - catalogo de voluntarios
	 */
	private List<Voluntario> getVoluntarioList(String contact) {

		return this.catVoluntario.stream().filter(voluntario -> contact.equals(voluntario.toString()))
				.collect(Collectors.toList());
	}

	/**
	 * funcao que retorna o voluntario correspondente
	 * 
	 * @param contact - contacto do voluntario
	 * @return this.getVoluntarioList(contact).get(0) - voluntario
	 */
	public Voluntario getVoluntario(String contact) {

		return this.getVoluntarioList(contact).get(0);
	}

	/**
	 * funcao que retorna um optional voluntario DTO
	 * 
	 * @param contact - contacto do voluntario
	 * @return Optional.of(v) if v!= null, v = this.getVoluntario(contact).toDTO()
	 */
	public Optional<VoluntarioDTO> getOptVoluntario(String contact) {
		VoluntarioDTO v = this.getVoluntario(contact).toDTO();

		if (v == null)
			return Optional.empty();

		return Optional.of(v);
	}

	/**
	 * funcao que verifica que existe um voluntario com contacto no catalogo
	 * 
	 * @param contact - contacto do voluntario
	 * @return true if exists
	 */
	public Boolean existsVol(String contact) {

		for (Voluntario v : this.catVoluntario) {
			if (contact.equals(v.getContact()))
				return true;
		}

		return false;
	}

}