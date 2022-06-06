package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.PessoaDao;
import model.entities.Departamento;
import model.entities.Pessoa;

/*
 *Essa classe tem o objetivo de fazer a ponte entre o banco e a interface do sistema 
 */

public class PessoaDaoJDBC implements PessoaDao{

	
	private Connection conn;
	
	public PessoaDaoJDBC (Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Pessoa obj) {
		

	}

	@Override
	public void update(Pessoa obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Pessoa findById(Integer id) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement("select p.*, d.descricao "
					+"from pessoa p inner join departamento d "
					+"on p.iddepartamento = d.id where p.id = ?");
				
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			/*Esse IF abaixo é para saber se o EXECUTEQUERY retornou resposta,
			* pois se for nulo, o IF precisa tratar.
			*/
			if(rs.next()) {
				Departamento dp = new Departamento();
				dp.setId(rs.getInt("iddepartamento"));
				dp.setDescricao(rs.getString("descricao"));
				Pessoa obj = new Pessoa();
				obj.setId(rs.getInt("id"));
				obj.setNome(rs.getString("nome"));
				obj.setEmail(rs.getString("email"));
				obj.setNascimento(rs.getDate("nascimento"));
				obj.setSalariobase(rs.getDouble("salariobase"));
				obj.setDepartamento(dp);	
				
				return obj;
			}

			return null;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
			
		}finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
		
		
		
	}

	@Override
	public List<Pessoa> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
