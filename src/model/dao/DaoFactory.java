package model.dao;

import model.dao.impl.PessoaDaoJDBC;

/*
 * A classe DAOFACTORY tem o objetivo de instanciar uma implementação,
 * através de um metodo tem referencia a classe DAO 
 */

public class DaoFactory {
	
	public static PessoaDao createPessoaDao() {
		
		return new PessoaDaoJDBC();
	}
	

}
