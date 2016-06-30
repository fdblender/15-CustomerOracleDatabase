package DatabaseUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DBUtil {
	private static Connection conn = null;

	public DBUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public static void getConnection() {
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
	
	// overload method to open a connection with username and password
	public static boolean getConnection(String username, String password) {
		String connStr;
		try {
		if( conn == null) {			
			Class.forName("oracle.jdbc.driver.OracleDriver");
		// 	con = DriverManager.getConnection("jdbc:oracle:thin:sys as
		// sysdba/oracle@localhost:1521:orcl");
			
			connStr = "jdbc:oracle:thin:"+username+"/"+password+"@localhost:1521:orcl";
			
			conn = DriverManager.getConnection(connStr);
			return true;
		}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return false;
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<HashMap<String, String>> execGet(String sql) {
		try {			
			ResultSet rs;   // result set of query
			PreparedStatement pstmt = null;			
			String fields[] = new String[20];	
			ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			
			getConnection();
			if (conn == null) {
				System.out.println("con is null");
			}
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			// convert result set to array list
			ResultSetMetaData md = rs.getMetaData();
			int columns = md.getColumnCount();
			//ArrayList list = new ArrayList(50);			  
			while (rs.next()){
			     HashMap<String,String> row = new HashMap<String,String>(columns);
			     for(int i=1; i<=columns; ++i){           
			       row.put(md.getColumnName(i),rs.getString(i));			       
			     }
			     list.add(row);
			}

			rs.close();
			pstmt.close();
			return list;					
								
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
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
