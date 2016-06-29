
/* Create a JDBC application called CustomerApp 
 * which contains a class called Customer (and other classes as needed). 
 * The application prompts the user for a customer last name 
 * and returns the matching record, 
 * displaying it on the screen like an address label
 */
package CustomersDatabase;

import java.util.Scanner;

public class CustomerApp {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String resp, firstname, lastname;
		boolean found;
		Customer customer;
		String customerStr;
		String customerID, addr, city, state, zip;		
		
		while (true) {
			System.out.println("\nPress (1) to search for a customer or press (2) to edit the customer's address");
			System.out.println("  or press (Q) to quit");
			resp = scan.nextLine();
			if (resp.equals("1")) {
				System.out.println("Enter the customer's last name: ");
				lastname = scan.nextLine();
				System.out.println("Enter the customer's first name: ");
				firstname = scan.nextLine();
				customer = CustomerDatabase.getCustomer(lastname, firstname);				
				System.out.println(CustomerDatabase.toString(customer));
				
				
			} else if (resp.equals("2")) {
				customerID="";
				addr = "";
				city = "";
				state = "";
				zip = "";
				System.out.println("Enter the customer ID: ");
				customerID = scan.nextLine();
				System.out.println("Enter the new street address: ");
				addr = scan.nextLine();
				System.out.println("Enter the new city: ");
				city = scan.nextLine();				
				System.out.println("Enter the new state: ");
				state = scan.nextLine();
				System.out.println("Enter the new zip: ");
				zip = scan.nextLine();
				
				found = CustomerDatabase.updateAddress(customerID, addr, city, state, zip);
				if (found) {
					System.out.println("The record was updated");
				}
				
			} else {
				System.out.print("Exiting customer database application.");
				break;
			}
			
			
		}

	}

}
