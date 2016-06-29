package DatabaseUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class DBUtil {
	private static Connection conn = null;

	public DBUtil() {
		// TODO Auto-generated constructor stub
	}
	
	private static void getConnection() {
		try {
		if( conn == null) {			
			Class.forName("oracle.jdbc.driver.OracleDriver");
		// 	con = DriverManager.getConnection("jdbc:oracle:thin:sys as
		// sysdba/oracle@localhost:1521:orcl");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:ora1/ora1@localhost:1521:orcl");
		}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}			
	}	
	
	public static String[] executeGet(String sql) {
		try {			
			ResultSet rs;   // result set of query
			PreparedStatement pstmt = null;			
			String fields[] = new String[20];			
			
			getConnection();
			if (conn == null) {
				System.out.println("con is null");
			}
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				for (int col=1; col<=rs.getMetaData().getColumnCount(); col++) {
					fields[col-1] = rs.getString(col);
				}
			}
			rs.close();
			pstmt.close();
			return fields;					
								
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return null;
	}
	
	public static int executeUpdate(String sql) {
		int result = 0;
		try {			
			
			PreparedStatement pstmt = null;			
			String fields[] = new String[20];				
			
			getConnection();
			if (conn == null) {
				System.out.println("con is null");
			}
			//System.out.println("DBUtil.executeUpdate "+sql);
			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeUpdate();
			//System.out.println("DBUtil.executeUpdate "+result);
						
			pstmt.close();
								
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return result;
	}

}
