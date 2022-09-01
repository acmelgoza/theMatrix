package com.mtrx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This is a collection of utility methods that define a general API for
 * interacting with the database supporting this application.
 *
 */
public class SalesData {
	/**
	 * Removes all sales records from the database.
	 */
	public static void removeAllSales() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseConnection.URL, DatabaseConnection.USERNAME,
					DatabaseConnection.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		// create a query
		String query = "delete from SaleItem where saleItemId != -1;";
		String query2 = "delete from Sale where saleId != -1;";
		PreparedStatement prep = null;
		PreparedStatement prep2 = null;
		try {
			prep = conn.prepareStatement(query);
			prep2 = conn.prepareStatement(query2);
			// execute
			prep.executeUpdate();
			prep2.executeUpdate();
			// close
			prep.close();
			prep2.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Removes the single sales record associated with the given
	 * <code>saleCode</code>
	 * 
	 * @param saleCode
	 */
	public static void removeSale(String saleCode) {
		Connection conn = null;
		int id = getSaleId(saleCode);
		try {
			conn = DriverManager.getConnection(DatabaseConnection.URL, DatabaseConnection.USERNAME,
					DatabaseConnection.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		// create a query
		String query = "delete from SaleItem where saleId = ?;";
		String query2 = "delete from Sale where saleId = ?;";
		PreparedStatement prep = null;
		PreparedStatement prep2 = null;

		try {
			prep = conn.prepareStatement(query);
			prep2 = conn.prepareStatement(query2);

			prep.setInt(1, id);
			prep2.setInt(1, id);
			// execute
			prep.executeUpdate();
			prep2.executeUpdate();

			// close
			prep.close();
			prep2.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Clears all tables of the database of all records.
	 */
	public static void clearDatabase() {
		removeAllPersons();
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseConnection.URL, DatabaseConnection.USERNAME,
					DatabaseConnection.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		// create a query
		String query = "delete from Address where addressId != -1;\n";
		PreparedStatement prep = null;
		try {
			prep = conn.prepareStatement(query);

			// execute
			prep.executeUpdate();
			// close
			prep.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Removes all email records from the database.
	 */
	public static void removeAllEmails() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseConnection.URL, DatabaseConnection.USERNAME,
					DatabaseConnection.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		// 3. create a query
		String query = "delete from Email where emailId != -1;";
		PreparedStatement prep = null;
		try {
			prep = conn.prepareStatement(query);
			// execute
			prep.executeUpdate();
			// process results

			prep.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Removes all sales items records from the database.
	 */
	public static void removeAllSalesItems() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseConnection.URL, DatabaseConnection.USERNAME,
					DatabaseConnection.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		// 3. create a query
		String query = "delete from SaleItem where saleItemId != -1;";
		PreparedStatement prep = null;
		try {
			prep = conn.prepareStatement(query);
			// execute
			prep.executeUpdate();
			// process results

			prep.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Removes all store records from the database.
	 */
	public static void removeAllStores() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseConnection.URL, DatabaseConnection.USERNAME,
					DatabaseConnection.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		// 3. create a query
		String query = "delete from SaleItem where saleItemId != -1;";
		String query2 = "delete from Sale where saleId != -1;";
		String query3 = "delete from Store where !=-1;";
		PreparedStatement prep = null;
		PreparedStatement prep2 = null;
		PreparedStatement prep3 = null;

		try {
			prep = conn.prepareStatement(query);
			prep2 = conn.prepareStatement(query2);
			prep3 = conn.prepareStatement(query3);
			// execute
			prep.executeUpdate();
			// process results

			prep.close();
			prep2.close();
			prep3.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Removes all persons records from the database.
	 */
	public static void removeAllPersons() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseConnection.URL, DatabaseConnection.USERNAME,
					DatabaseConnection.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		// 3. create a query
		String query = "delete from Email where emailId > 0;\r\n";
		String query2 = "delete from SaleItem where saleItemId > 0;\r\n";
		String query3 = "delete from Item where itemId > 0;\r\n";
		String query4 = "delete from Sale where saleId > 0;\r\n";
		String query5 = "delete from Store where storeId > 0;\r\n";
		String query6 = "delete from Person where personId > 0;";
		PreparedStatement prep = null;
		PreparedStatement prep2 = null;
		PreparedStatement prep3 = null;
		PreparedStatement prep4 = null;
		PreparedStatement prep5 = null;
		PreparedStatement prep6 = null;

		try {
			prep = conn.prepareStatement(query);
			prep2 = conn.prepareStatement(query2);
			prep3 = conn.prepareStatement(query3);
			prep4 = conn.prepareStatement(query4);
			prep5 = conn.prepareStatement(query5);
			prep6 = conn.prepareStatement(query6);

			// execute
			prep.executeUpdate();
			prep2.executeUpdate();
			prep3.executeUpdate();
			prep4.executeUpdate();
			prep5.executeUpdate();
			prep6.executeUpdate();

			// process results

			prep.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Removes all address records from the database.
	 */
	public static void removeAllAddresses() {
		clearDatabase();
	}

	/**
	 * Method to add an address record to the database with the provided data.
	 * Returns the generated key.
	 * 
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 */
	private static int addAddress(String street, String city, String state, String zip, String country) {
		int addressId = 0;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseConnection.URL, DatabaseConnection.USERNAME,
					DatabaseConnection.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		// 3. create a query
		String query = "insert into Address(street, city, state, country, zipcode) values ( ?, ?, ?, ?, ?);";
		String query2 = "select addressId from Address where street = ? and city = ? and state = ? and country = ? and zipcode = ?;";
		PreparedStatement prep = null;
		PreparedStatement prep2 = null;
		ResultSet rs = null;
		try {
			prep = conn.prepareStatement(query);
			prep2 = conn.prepareStatement(query2);
			prep.setString(1, street);
			prep.setString(2, city);
			prep.setString(3, state);
			prep.setString(4, country);
			prep.setString(5, zip);

			prep2.setString(1, street);
			prep2.setString(2, city);
			prep2.setString(3, state);
			prep2.setString(4, country);
			prep2.setString(5, zip);

			prep.executeUpdate();

			// execute
			rs = prep2.executeQuery();
			while (rs.next()) {
				addressId = rs.getInt("addressId");
			}

			rs.close();
			prep.close();
			prep2.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return addressId;
	}

	/**
	 * Gets personId using personCode
	 * 
	 * @param personCode
	 * @return
	 */
	public static int getPersonId(String personCode) {
		int id = 0;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseConnection.URL, DatabaseConnection.USERNAME,
					DatabaseConnection.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		// 3. create a query
		String query = "Select personId from Person where personCode = ?;";
		PreparedStatement prep = null;
		ResultSet rs = null;
		try {
			prep = conn.prepareStatement(query);
			prep.setString(1, personCode);
			// execute
			rs = prep.executeQuery();
			// process results
			while (rs.next()) {
				id = rs.getInt("personId");
			}
			rs.close();
			prep.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return id;
	}

	/**
	 * Gets storeId using storeCode
	 * 
	 * @param storeCode
	 * @return
	 */
	public static int getStoreId(String storeCode) {
		int id = 0;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseConnection.URL, DatabaseConnection.USERNAME,
					DatabaseConnection.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		// 3. create a query
		String query = "Select storeId from Store where storeCode = ?;";
		PreparedStatement prep = null;
		ResultSet rs = null;
		try {
			prep = conn.prepareStatement(query);
			prep.setString(1, storeCode);
			// execute
			rs = prep.executeQuery();
			// process results
			while (rs.next()) {
				id = rs.getInt("storeId");
			}
			rs.close();
			prep.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return id;
	}

	/**
	 * Gets itemId using itemCode
	 * 
	 * @param itemCode
	 * @return
	 */
	public static int getItemId(String itemCode) {
		int id = 0;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseConnection.URL, DatabaseConnection.USERNAME,
					DatabaseConnection.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		// 3. create a query
		String query = "Select itemId from Item where itemCode = ?;";
		PreparedStatement prep = null;
		ResultSet rs = null;
		try {
			prep = conn.prepareStatement(query);
			prep.setString(1, itemCode);
			// execute
			rs = prep.executeQuery();
			// process results
			while (rs.next()) {
				id = rs.getInt("itemId");
			}
			rs.close();
			prep.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return id;
	}

	/**
	 * Gets saleId using saleCode
	 * 
	 * @param saleCode
	 * @return
	 */
	public static int getSaleId(String saleCode) {
		int id = 0;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseConnection.URL, DatabaseConnection.USERNAME,
					DatabaseConnection.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		// 3. create a query
		String query = "Select saleId from Sale where saleCode = ?;";
		PreparedStatement prep = null;
		ResultSet rs = null;
		try {
			prep = conn.prepareStatement(query);
			prep.setString(1, saleCode);
			// execute
			rs = prep.executeQuery();
			// process results
			while (rs.next()) {
				id = rs.getInt("saleId");
			}
			rs.close();
			prep.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return id;
	}

	/**
	 * Returns a specific Address using an addressId
	 * 
	 * @param addressId
	 * @return
	 */
	public static int getAddressId(String street, String city, String state, String zip, String country) {
		int add = 0;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseConnection.URL, DatabaseConnection.USERNAME,
					DatabaseConnection.PASSWORD);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		// 3. create a query
		String query = "select a.addressId from Address a where a.street =  ? and a.city =? and a.state = ? and a.zipcode = ? and"
				+ " a.country = ?;";
		PreparedStatement prep = null;
		ResultSet rs = null;
		try {
			prep = conn.prepareStatement(query);
			// execute
			// set strings
			prep.setString(1, street);
			prep.setString(2, city);
			prep.setString(3, state);
			prep.setString(4, zip);
			prep.setString(5, country);
			rs = prep.executeQuery();
			// process results
			while (rs.next()) {

				add = rs.getInt("addressId");
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
	 * Method to add a person record to the database with the provided data. The
	 * <code>type</code> value depending on the type (employee or type of customer).
	 * 
	 * @param personCode
	 * @param type
	 * @param firstName
	 * @param lastName
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 */
	public static void addPerson(String personCode, String type, String firstName, String lastName, String street,
			String city, String state, String zip, String country) {
		int addressId = 0;
		addressId = getAddressId(street, city, state, zip, country);
		if (addressId == 0) {
			addressId = addAddress(street, city, state, zip, country);
		}
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseConnection.URL, DatabaseConnection.USERNAME,
					DatabaseConnection.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		// check if address is in database, if not create new address with personId

		// 3. create a query
		String query = "insert into Person(firstName, lastName, personCode, personType, addressId) values (?, ?, ?, ?, ?)";
		PreparedStatement prep = null;
		try {
			prep = conn.prepareStatement(query);
			prep.setString(1, firstName);
			prep.setString(2, lastName);
			prep.setString(3, personCode);
			prep.setString(4, type);
			prep.setInt(5, addressId);

			// execute
			prep.executeUpdate();

			prep.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * Adds an email record corresponding person record corresponding to the
	 * provided <code>personCode</code>
	 * 
	 * @param personCode
	 * @param email
	 */
	public static void addEmail(String personCode, String email) {
		int personId = getPersonId(personCode);
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseConnection.URL, DatabaseConnection.USERNAME,
					DatabaseConnection.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		// 3. create a query
		String query = "insert into Email(personId, address) values (?,?);";
		PreparedStatement prep = null;
		try {

			prep = conn.prepareStatement(query);
			prep.setInt(1, personId);
			prep.setString(2, email);
			// execute
			prep.executeUpdate();

			prep.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Adds a store record to the database managed by the person identified by the
	 * given code.
	 * 
	 * @param storeCode
	 * @param managerCode
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 */
	public static void addStore(String storeCode, String managerCode, String street, String city, String state,
			String zip, String country) {

		int addressId = getAddressId(street, city, state, zip, country);
		if (addressId == 0) {
			addressId = addAddress(street, city, state, zip, country);
		}
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseConnection.URL, DatabaseConnection.USERNAME,
					DatabaseConnection.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		// 3. create a query
		String query = "insert into Store(storeCode, managerCode, addressId) values (?,?,?);";
		PreparedStatement prep = null;
		try {
			prep = conn.prepareStatement(query);
			prep.setString(1, storeCode);
			prep.setString(2, managerCode);
			prep.setInt(3, addressId);
			// execute
			prep.executeUpdate();

			prep.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Adds a sales item (product, service, subscription) record to the database
	 * with the given <code>name</code> and <code>basePrice</code>. The type of item
	 * is specified by the <code>type</code> which may be one of "N", "U", "G", "S",
	 * "R" or "P". These correspond to new products, used products, gift cards,
	 * services, rental and subscriptions.
	 * 
	 * @param itemCode
	 * @param type
	 * @param name
	 * @param basePrice
	 */
	public static void addItem(String itemCode, String type, String name, Double basePrice) {
		Connection conn = null;
		if (basePrice == null) {
			basePrice = 0.0;
		}
		try {
			conn = DriverManager.getConnection(DatabaseConnection.URL, DatabaseConnection.USERNAME,
					DatabaseConnection.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		// 3. create a query
		String query = "insert into Item(itemCode, itemName, itemType, price) values(?,?,?,?);";
		PreparedStatement prep = null;
		try {
			prep = conn.prepareStatement(query);
			prep.setString(1, itemCode);
			prep.setString(2, name);
			prep.setString(3, type);
			prep.setDouble(4, basePrice);
			// execute
			prep.executeUpdate();

			prep.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Adds a sales record to the database with the given data.
	 * 
	 * @param saleCode
	 * @param storeCode
	 * @param customerCode
	 * @param salesPersonCode
	 */
	public static void addSale(String saleCode, String storeCode, String customerCode, String salesPersonCode) {
		int salesPersonId = getPersonId(salesPersonCode);
		int customerId = getPersonId(customerCode);

		int storeId = getStoreId(storeCode);
		if (storeId == 0) {

		}
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseConnection.URL, DatabaseConnection.USERNAME,
					DatabaseConnection.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		// 3. create a query
		String query = "insert into Sale(saleCode, salesPersonId, customerId, storeId) values (?,?,?,?);";
		PreparedStatement prep = null;
		try {
			prep = conn.prepareStatement(query);
			prep.setString(1, saleCode);
			prep.setInt(2, salesPersonId);
			prep.setInt(3, customerId);
			prep.setInt(4, storeId);
			// execute
			prep.executeUpdate();

			prep.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Adds a particular product (new or used, identified by <code>itemCode</code>)
	 * to a particular sale record (identified by <code>saleCode</code>) with the
	 * specified quantity.
	 * 
	 * @param saleCode}
	 * 
	 * 
	 *                  /** Adds a particular product (new or used, identified by
	 *                  <code>itemCode</code>) to a particular sale record
	 *                  (identified by <code>saleCode</code>) with the specified
	 *                  quantity.
	 * 
	 * @param saleCode
	 * @param itemCode
	 * @param quantity
	 */
	public static void addProductToSale(String saleCode, String itemCode, int quantity) {
		int saleId = getSaleId(saleCode);
		int itemId = getItemId(itemCode);
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseConnection.URL, DatabaseConnection.USERNAME,
					DatabaseConnection.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		// 3. create a query
		String query = "insert into SaleItem(saleId, itemId, itemQuantity) values(?,?,?);" + "";
		PreparedStatement prep = null;
		try {
			prep = conn.prepareStatement(query);
			prep.setInt(1, saleId);
			prep.setInt(2, itemId);
			prep.setInt(3, quantity);

			// execute
			prep.executeUpdate();

			prep.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Adds a particular gift card (identified by <code>itemCode</code>) to a
	 * particular sale record (identified by <code>saleCode</code>) in the specified
	 * amount.
	 * 
	 * @param saleCode
	 * @param itemCode
	 * @param amount
	 */
	public static void addGiftCardToSale(String saleCode, String itemCode, double amount) {
		int saleId = getSaleId(saleCode);
		int itemId = getItemId(itemCode);
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseConnection.URL, DatabaseConnection.USERNAME,
					DatabaseConnection.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		// 3. create a query
		String query = "insert into SaleItem(saleId, itemId, giftCardAmount) values(?,?,?);";
		PreparedStatement prep = null;
		try {
			prep = conn.prepareStatement(query);
			prep.setInt(1, saleId);
			prep.setInt(2, itemId);
			prep.setDouble(3, amount);
			// execute
			prep.executeUpdate();

			prep.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Adds a particular service (identified by <code>itemCode</code>) to a
	 * particular sale record (identified by <code>saleCode</code>) which will be
	 * performed by the given employee for the specified number of hours.
	 * 
	 * @param saleCode
	 * @param itemCode
	 * @param employeeCode
	 * @param billedHours
	 */
	public static void addServiceToSale(String saleCode, String itemCode, String employeeCode, double billedHours) {
		int saleId = getSaleId(saleCode);
		int itemId = getItemId(itemCode);
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseConnection.URL, DatabaseConnection.USERNAME,
					DatabaseConnection.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		// 3. create a query
		String query = "insert into SaleItem(saleId, itemId, empCode, hours) values(?,?,?,?);";
		PreparedStatement prep = null;
		try {
			prep = conn.prepareStatement(query);
			prep.setInt(1, saleId);
			prep.setInt(2, itemId);
			prep.setString(3, employeeCode);
			prep.setInt(4, (int) (billedHours)); // CHECK
			// execute
			prep.executeUpdate();

			prep.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Adds a particular subscription (identified by <code>itemCode</code>) to a
	 * particular sale record (identified by <code>saleCode</code>) which is
	 * effective from the <code>startDate</code> to the <code>endDate</code>
	 * inclusive of both dates.
	 * 
	 * @param saleCode
	 * @param itemCode
	 * @param startDate
	 * @param endDate
	 */
	public static void addSubscriptionToSale(String saleCode, String itemCode, String startDate, String endDate) {
		int saleId = getSaleId(saleCode);
		int itemId = getItemId(itemCode);
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseConnection.URL, DatabaseConnection.USERNAME,
					DatabaseConnection.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		// 3. create a query
		String query = "insert into SaleItem(saleId, itemId, beginDate, endDate) values(?,?,?,?);";
		PreparedStatement prep = null;
		try {
			prep = conn.prepareStatement(query);
			prep.setInt(1, saleId);
			prep.setInt(2, itemId);
			prep.setString(3, startDate);
			prep.setString(4, endDate);
			// execute
			prep.executeUpdate();

			prep.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}