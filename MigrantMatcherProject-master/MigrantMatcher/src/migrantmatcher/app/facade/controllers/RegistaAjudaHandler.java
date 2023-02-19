package migrantmatcher.app.facade.controllers;

import java.util.Scanner;

import migrantmatcher.app.domain.Regiao;
import migrantmatcher.app.domain.catalogos.CatalogoRegiao;

/**
 * Handler para o caso de uso Regista Ajuda
 * 
 * @author Rui Martins 56283
 * @author Paulo Bolinhas 56300
 */
public class RegistaAjudaHandler { 

	private CatalogoRegiao catRegiao;

	public RegistaAjudaHandler(CatalogoRegiao catRegiao) {
		this.catRegiao = catRegiao;
	}

	public int indicaNumPessoas(Scanner sc) {
		System.out.println("\nIndique o numero de pessoas que o alojamento alberga: ");
		return sc.nextInt();
	}

	public Regiao indicaRegiao(Scanner sc) {
		System.out.println("\nIndique a regiao onde se localiza o alojamento:");
		String ajudaRead = sc.next();
		return this.catRegiao.getRegiao(ajudaRead);
	}

	public String indicaDescItem(Scanner sc) {
		System.out.println("\nAdicione descricao do item:");
		return sc.next();
	}

	public String indicaCodigo(Scanner sc) {
		System.out.println("\nConfirme o codigo de verificao: ");
		return sc.next();
	}

}
