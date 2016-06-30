package SqlQueryUility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import DatabaseUtil.DBUtil;

public class SqlQueryUtility {

	public static void displayResults(ArrayList<HashMap<String, String>> list) {
		HashMap<String, String> firstrow = list.get(0);
		Set<String> columns = firstrow.keySet();
		// display column names
		for (String col : columns) {
			// row.get(col);
			System.out.print(col+"\t");
		}
		System.out.println("");
		// display all the records
		for (int a = 0; a < list.size(); a++) {
			HashMap<String, String> row = (HashMap<String, String>) list.get(a);

			for (String col : columns) {
				// row.get(col);
				System.out.print(row.get(col)+"\t");
			}
			System.out.println("");
		}

	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String sql, username, password;
		boolean found;
		boolean update = false;
		ArrayList<HashMap<String, String>> resultList;

		System.out.println("\nEnter your SQL query to insert or update records or (Q) to quit>");
		System.out.println("User Name>");
		username = scan.nextLine();
		System.out.println("Password>");
		password = scan.nextLine();

		// establish connection
		// if no connection then display error message and exit program
		if (!DBUtil.getConnection(username, password)) {
			System.out.print("Error: no connection. Exiting the SQL Query Utility.");
			System.exit(1);
		}

		while (true) {
			System.out.println("\nSQL>");
			sql = scan.nextLine();
			if (sql.toLowerCase().equals("q")) {
				System.out.print("Exiting SQL Query Utility.");
				break;
			}

			else {
				// parse the string to determine if insert or update

				// if select statement
				if (!update) {
					resultList = DBUtil.execGet(sql);
					// display records 
					displayResults(resultList);
					// or write them to a file					

				} else {

					if (DBUtil.executeUpdate(sql) > 0) {
						System.out.println("The record was updated");
					}
				}
			}

		}
	}
}
