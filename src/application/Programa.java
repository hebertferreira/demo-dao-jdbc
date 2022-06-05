package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.PessoaDao;
import model.entities.Departamento;
import model.entities.Pessoa;

public class Programa {

	public static void main(String[] args) {

		Departamento obj = new Departamento(1,"Brinquedos");
		System.out.println(obj);
		
		Pessoa vendedor = new Pessoa(1, "Joseph", "joseph@nomus.com.br", new Date(), 6499.99, obj);
		
		System.out.println(vendedor);
		
		/*Abaixo, PESSOADAO irá chamar a DAOFACTORY que tem referencia a INTERFACE que é a PESSOADAO 
		*permitindo fazer uma injeção de dependencia */
		
		PessoaDao pessoaDao = DaoFactory.createPessoaDao(); 
	}

}
