package com.mtrx;

/**
 * This class loads everything from the database
 * into the classes
 * @author amelgoza and ppande
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoadData {

	/**
	 * Returns a specific personCode using a personId
	 * 
	 * @param personId
	 * @return
	 */
	public static String getPersonCodeFromId(int personId) {
		String code = null;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseConnection.URL, DatabaseConnection.USERNAME,
					DatabaseConnection.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		// 3. create a query
		String query = "Select personCode from Person where personId = ?;";
		PreparedStatement prep = null;
		ResultSet rs = null;
		try {
			prep = conn.prepareStatement(query);
			prep.setInt(1, personId);
			// execute
			rs = prep.executeQuery();
			// process results
			while (rs.next()) {
				code = rs.getString("personCode");
			}
			rs.close();
			prep.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return code;
	}

	/**
	 * Returns a specific storeCode using storeId
	 * 
	 * @param storeId
	 * @return
	 */
	public static String getStoreCodeFromStoreId(int storeId) {
		String code = null;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseConnection.URL, DatabaseConnection.USERNAME,
					DatabaseConnection.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		// 3. create a query
		String query = "Select storeCode from Store where storeId = ?;";
		PreparedStatement prep = null;
		ResultSet rs = null;
		try {
			prep = conn.prepareStatement(query);
			prep.setInt(1, storeId);
			// execute
			rs = prep.executeQuery();

			// process results
			while (rs.next()) {
				code = rs.getString("storeCode");
			}
			rs.close();
			prep.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return code;
	}

	/**
	 * Gets personCode using personId Works whenever personId is referenced
	 * 
	 * @param personId
	 * @return
	 */
	public static Person getPersonFromId(int personId) {
		Person p = null;
		ArrayList<String> emails = new ArrayList<String>();
		Address add = null;
		// 1. load JDBC driver
		// 2. create a connection
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseConnection.URL, DatabaseConnection.USERNAME,
					DatabaseConnection.PASSWORD);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		// 3. create a query
		String query = "select firstName, lastName, personCode, personType, addressId from Person where personId = ?;";
		PreparedStatement prep = null;
		ResultSet rs = null;
		try {
			prep = conn.prepareStatement(query);
			prep.setInt(1, personId);
			// execute
			rs = prep.executeQuery();
			// process results
			while (rs.next()) {
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String personCode = rs.getString("personCode");
				String type = rs.getString("personType");
				int addressId = rs.getInt("addressId");
				// get Address
				// get emails
				add = getAddressFromId(addressId);
				emails = getEmail(personId);
				// create person
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
					p = new Employee(personCode, type, lastName, firstName, emails, add);
				}
			}
			rs.close();
			prep.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return p;
	}

	/**
	 * Gets a list of items from a sale using saleId
	 * 
	 * @param saleId
	 * @return
	 */
	public static ArrayList<Item> getSalesItems(int saleId) {
		ArrayList<Item> itm = new ArrayList<Item>();
		ArrayList<Item> itemList = getAllItems();
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseConnection.URL, DatabaseConnection.USERNAME,
					DatabaseConnection.PASSWORD);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		// 3. create a query
		String query = "Select s.*, i.* from Item i " + "join SaleItem s on s.itemId = i.itemId "
				+ "join Sale st on st.saleId = s.saleID " + "where s.saleId = ?;";
		PreparedStatement prep = null;
		ResultSet rs = null;
		try {
			prep = conn.prepareStatement(query);
			prep.setInt(1, saleId);
			// execute
			rs = prep.executeQuery();
			// process results
			while (rs.next()) {
				String itemCode = rs.getString("i.itemCode");

				// Compares itemCode to the list of items
				for (Item it : itemList) {
					if (itemCode.equals(it.getItemCode())) {
						// checks type of item and then adds the specific item to item list
						if ((it.getItemType().equals("N") || it.getItemType().equals("U"))) {
							int itemQuantity = rs.getInt("s.itemQuantity");
							// create a new item
							// and then add the item to the item list
							Item a = new Product((Product) it, itemQuantity);
							itm.add(a);
						}
						if (it.getItemType().equals("G")) {
							int itemQuantity = 1;
							double price = rs.getDouble("s.giftCardAmount");
							Item b = new GiftCard((GiftCard) it, itemQuantity, price);
							itm.add(b);
						}
						if (it.getItemType().equals("S")) {
							String empCode = rs.getString("s.empCode");
							Integer numHours = (rs.getInt("s.hours"));
							Item r = new Service((Service) it, empCode, numHours);
							itm.add(r);
						}
						if (it.getItemType().equals("R")) {
							String beginDate = rs.getString("s.beginDate");
							String endDate = rs.getString("s.endDate");
							Item t = new RentalService((RentalService) it, beginDate, endDate);
							itm.add(t);
						}

						if (it.getItemType().equals("P")) {
							String beginMonth = rs.getString("s.beginMonth");
							String endMonth = rs.getString("s.endMonth");
							Item e = new Subscription((Subscription) it, beginMonth, endMonth);
							itm.add(e);
						}
					}

				}
			}
			rs.close();
			prep.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return itm;
	}

	/**
	 * Returns an Employee using personId
	 * 
	 * @param personId
	 * @return
	 */
	public static Employee getEmployeeFromId(int personId) {
		Employee p = null;
		ArrayList<String> emails = new ArrayList<String>();
		Address add = null;
		// 1. load JDBC driver
		// 2. create a connection
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseConnection.URL, DatabaseConnection.USERNAME,
					DatabaseConnection.PASSWORD);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		// 3. create a query
		String query = "select firstName, lastName, personCode, personType, addressId from Person where personId = ?;";
		PreparedStatement prep = null;
		ResultSet rs = null;
		try {
			prep = conn.prepareStatement(query);
			prep.setInt(1, personId);
			// execute
			rs = prep.executeQuery();

			// process results

			while (rs.next()) {
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String personCode = rs.getString("personCode");
				String type = rs.getString("personType");
				int addressId = rs.getInt("addressId");
				// read in address
				// get emails
				add = getAddressFromId(addressId);
				emails = getEmail(personId);

				if (type.equals("E")) {
					p = new Employee(personCode, type, lastName, firstName, emails, add);
				} else {
					return null;
				}
			}
			rs.close();
			prep.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return p;
	}

	/**
	 * Returns a Store using storeId
	 * 
	 * @param id
	 * @return
	 */
	public static Store getStoreFromId(int id) {
		Store store = null;
		ArrayList<Sale> sales = new ArrayList<Sale>();
		Connection conn = null;
		ArrayList<Person> managerCheck = getAllPeople();
		Person manager = null;
		Address add = null;

		try {
			conn = DriverManager.getConnection(DatabaseConnection.URL, DatabaseConnection.USERNAME,
					DatabaseConnection.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		// 3. create a query
		String query = "select * from Store where storeId = ?;";
		PreparedStatement prep = null;
		ResultSet rs = null;
		try {
			prep = conn.prepareStatement(query);
			prep.setInt(1, id);
			// execute
			rs = prep.executeQuery();
			// process results
			while (rs.next()) {
				// add to store
				String storeCode = rs.getString("storeCode");
				String managerCode = rs.getString("managerCode");
				int addressId = rs.getInt("addressId");

				// get address
				add = getAddressFromId(addressId);
				// get manager by checking personList and comparing personCode and managerCode
				for (Person pe : managerCheck) {
					if (managerCode.equals(pe.getPersonCode())) {
						manager = pe;
					}
				}
				// create store and add to list
				store = new Store(storeCode, manager, add, sales);
			}
			rs.close();
			prep.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return store;
	}

	/**
	 * Gets list of emails from a specific person using personId
	 * 
	 * @param personId
	 * @return
	 */
	public static ArrayList<String> getEmail(int personId) {
		ArrayList<String> emails = new ArrayList<String>();
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DatabaseConnection.URL, DatabaseConnection.USERNAME,
					DatabaseConnection.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		// 3. create a query
		String query = "select address from Email where personId = ?;";
		PreparedStatement prep = null;
		ResultSet rs = null;
		try {
			prep = conn.prepareStatement(query);
			prep.setInt(1, personId);
			// execute
			rs = prep.executeQuery();
			// process results
			while (rs.next()) {
				// get email and add to list
				String address = rs.getString("address");
				emails.add(address);
			}
			rs.close();
			prep.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return emails;
	}

	/**
	 * Returns a specific Address using an addressId
	 * 
	 * @param addressId
	 * @return
	 */
	public static Address getAddressFromId(int addressId) {
		Address add = null;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseConnection.URL, DatabaseConnection.USERNAME,
					DatabaseConnection.PASSWORD);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		// 3. create a query
		String query = "select a.street, a.city, a.state, a.zipcode, a.country from Address a where a.addressId = ?;";
		PreparedStatement prep = null;
		ResultSet rs = null;
		try {
			prep = conn.prepareStatement(query);
			// execute
			// set int
			prep.setInt(1, addressId);
			rs = prep.executeQuery();
			// process results
			while (rs.next()) {
				String street = rs.getString("street");
				String city = rs.getString("city");
				String state = rs.getString("state");
				String zip = rs.getString("zipcode");
				String country = rs.getString("country");
				// create new address
				add = new Address(street, city, state, zip, country);
			}
			rs.close();
			prep.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return add;
	}

	/**
	 * Returns a list of all people from the database
	 * 
	 * @return
	 */
	public static ArrayList<Person> getAllPeople() {
		ArrayList<Person> result = new ArrayList<Person>();
		Person p = null;
		ArrayList<String> emails = new ArrayList<String>();

		Address add = null;

		// 1. load JDBC driver

		// 2. create a connection

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DatabaseConnection.URL, DatabaseConnection.USERNAME,
					DatabaseConnection.PASSWORD);

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		// 3. create a query

		String query = "select personId, firstName, lastName, personCode, personType, addressId from Person;";

		PreparedStatement prep = null;
		ResultSet rs = null;
		try {
			prep = conn.prepareStatement(query);
			// execute
			rs = prep.executeQuery();

			// process results

			while (rs.next()) {
				int personId = rs.getInt("personId");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String personCode = rs.getString("personCode");
				String type = rs.getString("personType");
				int addressId = rs.getInt("addressId");

				// read in address
				// get emails
				add = getAddressFromId(addressId);
				emails = getEmail(personId);

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
					p = new Employee(personCode, type, lastName, firstName, emails, add);
				}
				result.add(p);
			}
			rs.close();
			prep.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return result;
	}

	/**
	 * Returns a list of all stores from the database
	 * 
	 * @return
	 */
	public static ArrayList<Store> getStores() {
		ArrayList<Store> result = new ArrayList<Store>();
		ArrayList<Person> managerCheck = getAllPeople();
		Store str = null;
		Person manager = null;
		Address add = null;
		// 1. load JDBC driver
		// 2. create a connection
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseConnection.URL, DatabaseConnection.USERNAME,
					DatabaseConnection.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		// 3. create a query
		String query = "select storeCode, managerCode, addressId from Store;";
		PreparedStatement prep = null;
		ResultSet rs = null;
		try {
			prep = conn.prepareStatement(query);
			// execute
			rs = prep.executeQuery();

			// process results
			while (rs.next()) {
				String storeCode = rs.getString("storeCode");
				String managerCode = rs.getString("managerCode");
				int addressId = rs.getInt("addressId");
				// get address
				add = getAddressFromId(addressId);
				// get manager by checking personList and comparing personCode and managerCode
				for (Person pe : managerCheck) {
					if (managerCode.equals(pe.getPersonCode())) {
						manager = pe;
					}
				}
				// create store and add to list
				ArrayList<Sale> sales = new ArrayList<Sale>();
				str = new Store(storeCode, manager, add, sales);
				result.add(str);
			}
			rs.close();
			prep.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * Returns a list of all Items from the database
	 * 
	 * @return
	 */
	public static ArrayList<Item> getAllItems() {
		ArrayList<Item> result = new ArrayList<Item>();
		Item p = null;
		// 1. load JDBC driver
		// 2. create a connection
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseConnection.URL, DatabaseConnection.USERNAME,
					DatabaseConnection.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		// 3. create a query
		String query = "Select itemID, itemCode, itemName, itemType, price from Item;";
		PreparedStatement prep = null;
		ResultSet rs = null;
		try {
			prep = conn.prepareStatement(query);
			// execute
			rs = prep.executeQuery();
			// process results
			while (rs.next()) {
				String code = rs.getString("itemCode");
				String type = rs.getString("itemType");
				String name = rs.getString("itemName");
				double basePrice = rs.getDouble("price");
				if (type.equals("N")) {
					p = new Product(code, type, name, basePrice);
					result.add(p);
				}
				if (type.equals("U")) {
					p = new Product(code, type, name, (basePrice * .8));
					result.add(p);
				}
				if (type.equals("G")) {
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
			rs.close();
			prep.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * Returns a list of all Sales from the database
	 * 
	 * @return
	 */
	public static ArrayList<Sale> getAllSales() {
		ArrayList<Sale> result = new ArrayList<Sale>();
		// put everything in sale list
		// SaleList<Sale> finalList = new SaleList<Sale>();

		ArrayList<Person> personList = getAllPeople();
		ArrayList<Store> storeList = getStores();
		Person customerToken = null;
		Employee employeeToken = null;
		Store storeToken = null;
		List<Item> itm = new ArrayList<Item>();
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseConnection.URL, DatabaseConnection.USERNAME,
					DatabaseConnection.PASSWORD);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		String query = "select * from Sale";
		PreparedStatement prep = null;
		ResultSet rs = null;
		try {
			prep = conn.prepareStatement(query);
			// execute
			rs = prep.executeQuery();
			// process results
			while (rs.next()) {

				int saleId = rs.getInt("saleId");
				String saleCode = rs.getString("saleCode");
				int storeId = rs.getInt("storeId");
				int customerId = rs.getInt("customerId");
				int salePersonId = rs.getInt("salesPersonId");

				String customerCode = getPersonCodeFromId(customerId);
				String salesPersonCode = getPersonCodeFromId(salePersonId);
				String storeCode = getStoreCodeFromStoreId(storeId);

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
				for (Store at : storeList) {
					if (at.getStoreCode().equals(storeCode)) {
						storeToken = at;
					}
				}

				// check itemslist with salesitem from database add item to item list
				itm = getSalesItems(saleId);
				// set sale and add sale to list associated with employee and store
				Sale jkd = new Sale(saleCode, storeToken, (Person) customerToken, employeeToken, itm);
				employeeToken.addSale(jkd);
				storeToken.addStoreSale(jkd);
				result.add(jkd);

			}
			rs.close();
			prep.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return result;
	}

}