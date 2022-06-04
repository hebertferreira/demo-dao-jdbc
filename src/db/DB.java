package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {
	
	/*===========================*/
	/*METODO PARA ABRIR A CONEXÃO*/
	/*===========================*/
	
	private static Connection conn = null;

	public static Connection getConnection() {
		
	//Objetico 01 -> testar os dados de acesso estão OK
		if(conn == null) {
			try {

				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props); // Variável CONN recebe os dados da URL do PROPS

			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		
		return conn;
	}
	
	public static void closeConnection() {
		if(conn!=null) {
			try {
				
				conn.close();
			}catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	/* METODO PARA LER OS DADOS DE ACESSO AO BANCO */
	private static Properties loadProperties() {
		
		try(FileInputStream fs = new FileInputStream("db.properties")){
			
			Properties props = new Properties();
			props.load(fs); //Load do PROPS irá ler a variável tipo InputStream FS que guarda os objetos  dentro do PROPS.
			return props;
			
		}catch (IOException e) {
			
			throw new DbException(e.getMessage());

		}
	}
	
	public static void closeStatement(Statement st) {
		if(st !=null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	public static void closeResultSet(ResultSet rs) {
		if(rs !=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
}
