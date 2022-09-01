package com.mtrx;

/**
 * Gets information from the Persons, Items, and Stores csv files
 *  and turns them into a list of objects
 * @author amelgoza and ppande
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileParser {
	/**
	 * Parses information from Items.csv into a list of Item
	 * 
	 * @return
	 */
	
	
	
	public static List<Item> parseItemsFile() {
		List<Item> result = new ArrayList<Item>();
		File f = new File("data/Items.csv");
		Scanner s;
		try {
			s = new Scanner(f);
		} catch (FileNotFoundException fnfe) {
			throw new RuntimeException(fnfe);
		}
		s.nextLine();
		while (s.hasNext()) {
			String line = s.nextLine();
			if (!line.trim().isEmpty()) {
				// Create Item object placeholder
				Item p = null;
				String tokens[] = line.split(",");
				// Set token values
				String code = tokens[0];
				String type = tokens[1];
				String name = tokens[2];
				double basePrice = 0;
				// To make sure gift cards don't have a value
				// Sets token[3] to a token named basePrice
				if (tokens.length > 3) {
					basePrice = Double.parseDouble(tokens[3]);
				}
				// Creates new objects based on type of item
				if (type.equals("N")) {
					p = new Product(code, type, name, basePrice);
					result.add(p);
				}
				if (type.equals("U")) {
					p = new Product(code, type, name, (basePrice * .8));
					result.add(p);
				}
				if (type.equals("G")) {// need price for gift card
					p = new GiftCard(code, type, name);
					result.add(p);
				}
				if (type.equals("S")) {
					p = new Service(code, type, name, basePrice);
					result.add(p);
				}
				if (type.equals("R")) {
					p = new RentalService(code, type, name, basePrice);
					result.add(p);
				}
				if (type.equals("P")) {
					p = new Subscription(code, type, name, basePrice);
					result.add(p);
				}
			}
		}
		s.close();
		return result;
	}

	/**
	 * Parses the Stores.csv file into a list of Store
	 * 
	 * @return
	 */
	public static List<Store> parseStoresFile() {
		List<Store> result = new ArrayList<Store>();
		// Create a list of persons to deal with manager
		List<Person> personList = parsePersonsFile();

		File f = new File("data/Stores.csv");
		Scanner s;
		try {
			s = new Scanner(f);
		} catch (FileNotFoundException fnfe) {
			throw new RuntimeException(fnfe);
		}
		s.nextLine();
		// Create a new person object to compare
		Person manager = null;
		while (s.hasNext()) {
			String line = s.nextLine();
			if (!line.trim().isEmpty()) {
				Store st = null;
				String tokens[] = line.split(",");
				String storeCode = tokens[0];
				String managerCode = tokens[1];
				// Iterate through list to find Manager info
				for (Person pe : personList) {
					if (managerCode.equals(pe.getPersonCode())) {
						manager = pe;
					}
				}
				// Set token values
				String street = tokens[2];
				String city = tokens[3];
				String state = tokens[4];
				String zip = tokens[5];
				String country = tokens[6];
				// Create Address object for store
				List<Sale> saleList = new ArrayList<Sale>();

				Address ad = new Address(street, city, state, zip, country);
				st = new Store(storeCode, manager, ad, saleList);
				result.add(st);
			}
		}
		s.close();
		return result;
	}

	/**
	 * Parses the Persons.csv file into a list of Person
	 * 
	 * @return
	 */
	public static List<Person> parsePersonsFile() {
		List<Person> result = new ArrayList<Person>();
		File f = new File("data/Persons.csv");
		Scanner s;
		try {
			s = new Scanner(f);
		} catch (FileNotFoundException fnfe) {
			throw new RuntimeException(fnfe);
		}
		s.nextLine();
		while (s.hasNext()) {
			String line = s.nextLine();
			if (!line.trim().isEmpty()) {
				ArrayList<String> emails = new ArrayList<String>();
				Person p = null;
				Address add = null;
				// Token to hold data from file
				String tokens[] = line.split(",", -1);
				// Setting token values
				String personCode = tokens[0];
				String type = tokens[1];
				String firstName = tokens[3];
				String lastName = tokens[2];
				String street = tokens[4];
				String city = tokens[5];
				String state = tokens[6];
				String zip = tokens[7];
				String country = tokens[8];
				// Loop adds emails to an arrayList
				for (int i = 9; i < tokens.length; i++) {
					emails.add(tokens[i]);
				}
				// Creates new address object and then creates the person object
				add = new Address(street, city, state, zip, country);
				List<Sale> saleList = new ArrayList<Sale>();

				if (type.equals("C")) {
					p = new Customer(personCode, type, lastName, firstName, emails, add);
				}
				if (type.equals("G")) {
					p = new GoldCustomer(personCode, type, lastName, firstName, emails, add);
				}
				if (type.equals("S")) {
					p = new SilverCustomer(personCode, type, lastName, firstName, emails, add);
				}
				if (type.equals("E")) {
					p = new Employee(personCode, type, lastName, firstName, emails, add, saleList);
				}

				result.add(p);
			}
		}
		s.close();
		return result;

	}

	public static List<Sale> parseSalesFile() {
		List<Sale> result = new ArrayList<Sale>();

		List<Person> personList = parsePersonsFile();
		List<Store> storeList = parseStoresFile();
		List<Item> itemList = parseItemsFile();

		File f = new File("data/Sales.csv");
		Scanner s;
		try {
			s = new Scanner(f);
		} catch (FileNotFoundException fnfe) {
			throw new RuntimeException(fnfe);
		}
		s.nextLine();

		while (s.hasNext()) {
			String line = s.nextLine();
			if (!line.trim().isEmpty()) {

				// list of items for sales with multiple items
				List<Item> itm = new ArrayList<Item>();
				Person customerToken = null;
				Employee employeeToken = null;
				Store storeToken = null;

				String tokens[] = line.split(",");
				String saleCode = tokens[0];
				String storeCode = tokens[1];
				String customerCode = tokens[2];
				String salesPersonCode = tokens[3];

				// checks for all items after the sales person code
				for (int i = 4; i < tokens.length; i++) {
					for (Item it : itemList) {
						if (tokens[i].equals(it.getItemCode())) {
							// checks type of item and then adds the specific item to item list
							if ((it.getItemType().equals("N") || it.getItemType().equals("U"))) {
								int itemQuantity = Integer.parseInt(tokens[i + 1]);
								// create a new item
								// and then add the item to the item list
								Item a = new Product((Product) it, itemQuantity);
								itm.add(a);
							}
							if (it.getItemType().equals("G")) {
								int itemQuantity = 1;
								double price = Double.parseDouble(tokens[i + 1]);
								Item b = new GiftCard((GiftCard) it, itemQuantity, price);
								itm.add(b);
							}
							if (it.getItemType().equals("S")) {
								String empCode = tokens[i + 1];
								Integer numHours = Integer.parseInt(tokens[i + 2]);
								Item r = new Service((Service) it, empCode, numHours);
								itm.add(r);
							}
							if (it.getItemType().equals("R")) {
								String beginDate = tokens[i + 1];
								String endDate = tokens[i + 2];
								Item t = new RentalService((RentalService) it, beginDate, endDate);
								itm.add(t);
							}

							if (it.getItemType().equals("P")) {
								String beginMonth = tokens[i + 1];
								String endMonth = tokens[i + 2];
								Item e = new Subscription((Subscription) it, beginMonth, endMonth);
								itm.add(e);
							}
						}
					}
				}
				// create store
				for (Store at : storeList) {
					if (at.getStoreCode().equals(storeCode)) {
						storeToken = at;
					}
				}

				// list of employees
				List<Employee> empList = new ArrayList<Employee>();

				// create customer
				for (Person pe : personList) {
					if (customerCode.equals(pe.getPersonCode())) {
						// Regular customer
						if (pe.getCustomerType().equals("C")) {
							customerToken = new Customer(pe.getPersonCode(), pe.getCustomerType(), pe.getLastName(),
									pe.getFirstName(), pe.getEmailAddress(), pe.getAddress());
						}
						// Silver customer
						if (pe.getCustomerType().equals("S")) {
							customerToken = new SilverCustomer(pe.getPersonCode(), pe.getCustomerType(),
									pe.getLastName(), pe.getFirstName(), pe.getEmailAddress(), pe.getAddress());
						}
						// Gold customer
						if (pe.getCustomerType().equals("G")) {
							customerToken = new GoldCustomer(pe.getPersonCode(), pe.getCustomerType(), pe.getLastName(),
									pe.getFirstName(), pe.getEmailAddress(), pe.getAddress());
						}
						// Employee
						if (pe.getCustomerType().equals("E")) {
							Employee e = (Employee) pe;
							customerToken = new Employee(e.getPersonCode(), e.getCustomerType(), e.getLastName(),
									e.getFirstName(), e.getEmailAddress(), e.getAddress());

						}

					}

					// adds employees to a list, makes sure there is no duplicate
					if (pe.getCustomerType().equals("E") && !(empList.contains(pe))) {
						empList.add((Employee) pe);
					}

				}
				// create employee
				for (Employee e : empList) {
					if (salesPersonCode.equals(e.getPersonCode())) {
						employeeToken = new Employee(e.getPersonCode(), e.getCustomerType(), e.getLastName(),
								e.getFirstName(), e.getEmailAddress(), e.getAddress(), e.getTotalSales());
					}
				}
				// set sale and add sale to list associated with employee and store
				Sale jkd = new Sale(saleCode, storeToken, (Person) customerToken, employeeToken, itm);
				employeeToken.addSale(jkd);
				storeToken.addStoreSale(jkd);

				result.add(jkd);

			}

		}
		s.close();
		return result;
	}

}
