package com.mtrx;

/**
 * Information to connect to database
 * 
 * @author amelgoza and ppande
 *
 */
public class DatabaseConnection {
	public static final String PARAMETERS = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	public static final String USERNAME = "amelgoza";
	public static final String PASSWORD = "o7xcihQa";
	public static final String URL = "jdbc:mysql://cse.unl.edu/" + USERNAME + PARAMETERS;
}
