package Connections;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class connectionn {
	Connection conn;
	DataSource ds;
	public DataSource dataconn() throws NamingException {
		Context context = new InitialContext();
		DataSource ds;
		ds = (DataSource)context.lookup("java:comp/env/jdbc/cardvalidation");
		
		return ds; 
	}
	public Connection getConnection() throws SQLException, NamingException {
		Connection conn=dataconn().getConnection();
		return conn;
	}
}
