package util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class DbConnection {

	public static Connection getConnection(){
		Connection conn = null;
		
		try {
			Context context = new InitialContext();
			DataSource dSource = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
			conn = dSource.getConnection();
		} catch (Exception e) {
			System.out.println("Conn Error: " +e);
		} 
		return conn;
	}
	
	public static void CloseConn(Connection conn, PreparedStatement pstmt, ResultSet rs){
		
		try {
			if(rs != null){rs.close();}
			if(pstmt != null){pstmt.close();}
			if(conn != null){ conn.close();}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
}
