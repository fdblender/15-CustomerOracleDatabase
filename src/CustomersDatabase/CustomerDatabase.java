package CustomersDatabase;
import DatabaseUtil.DBUtil;

public class CustomerDatabase {		
	
	public CustomerDatabase() {
		// TODO Auto-generated constructor stub
	}
	
	public static Customer getCustomer(String lastname, String firstname) {
		String columns[];
		String sql;
		Customer customer = new Customer();

		sql = "select customerID, title, firstname, lastname, streetaddress, cities.city, "
				+ "states.state, trunc(zipcode,2) from customers "
				+ "inner join cities on cities.cityid = customers.cityid " 
				+ "inner join states on states.stateid = customers.stateid "
				+ "where lastname= '"+lastname+"' "
				+"and firstname= '"+firstname+"'";
		
		//System.out.println("getCustomer sql: "+sql);
		
		columns = DBUtil.executeGet(sql);
		
		customer.setCustomerId(columns[0]);
		customer.setTitle(columns[1]);			
		customer.setFirstname(columns[2]);
		customer.setLastname(columns[3]);
		customer.setAddress(columns[4]);
		customer.setCity(columns[5]);
		customer.setState(columns[6]);
		customer.setZip(columns[7]);		
		return customer;
	}
	
	public static boolean updateAddress(String customerID, String addr, String city, 
			String state, String zip) {
		String sql;
		String columns[];
		String cityID, stateid;		
		
		if (!addr.isEmpty()) {
			sql = "update customers set streetaddress = '"+addr 
					+ "' where customerid = "+customerID;
			//System.out.println("ExecuteUpdate sql: "+sql);
			if (DBUtil.executeUpdate(sql) >0) {
				System.out.println("Update successful: "+addr);
			};
			
		}
		if (!city.isEmpty()) {
			//System.out.println("city is not empty");
			sql = "select cityid from cities where city = '"+city+"'";
						
			// get the city id
			columns = DBUtil.executeGet(sql);			
			
			// if it found the city, then update the city id
			if (!columns[0].isEmpty()) {
				cityID = columns[0];				
				//System.out.println("columns city: "+cityID);
				
				// update the city id
				sql = "update customers set cityid ="+cityID
						+ " where customerid=" + customerID;
				//System.out.println("city update: "+sql);
				if (DBUtil.executeUpdate(sql) > 0) {
					System.out.println("City Update successful: "+cityID);
				};
			} else {
				System.out.println("Invalid city - unable to update the city");
			}
		}
		if (!state.isEmpty()) {
			//System.out.println("city is not empty");
			sql = "select stateid from states where state = '"+state+"'";
						
			// get the state id
			columns = DBUtil.executeGet(sql);			
			
			// if it found the state, then update the state id
			if (!columns[0].isEmpty()) {
				stateid = columns[0];				
				//System.out.println("columns city: "+stateid);
				
				// update the city id
				sql = "update customers set stateid ="+stateid
						+ " where customerid=" + customerID;
				System.out.println("State update: "+sql);
				if (DBUtil.executeUpdate(sql) > 0) {
					System.out.println("State Update successful: "+stateid);
				};
			} else {
				System.out.println("Invalid state - unable to update the state");
			}
			
		}
		if (!zip.isEmpty()) {			
			sql = "update customers set zipcode = '"+zip 
					+ "' where customerid = "+customerID;
			//System.out.println("ExecuteUpdate sql: "+sql);
			if (DBUtil.executeUpdate(sql) >0) {
				System.out.println("Zip Code Update successful: "+zip);
			};
		}		
		
		return true;
	}
	

	public static String toString(Customer customer) {
		String customerStr;
		
		// TO DO
		String position = "not implemented yet";
		// get position based on the customer ID - requires a join
		
		customerStr = "Customer Number: "+customer.getCustomerId() + "\n"
				+ customer.getTitle() + " "+customer.getFirstname()+" "+customer.getLastname() + "\n"
				+ customer.getAddress() + "\n"
				+ customer.getCity()+", "+customer.getState()+", "+customer.getZip()+ "\n"
				+ position;			
		
		
		return( customerStr);
	}
			

}
