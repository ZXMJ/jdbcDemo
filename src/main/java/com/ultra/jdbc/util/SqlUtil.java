package com.ultra.jdbc.util;

public class SqlUtil {

	public static String DEPART_INSERT_SQL = "INSERT INTO DEPART (DEPART_NAME) VALUES (?)";
	public static String DEPART_UPDATE_SQL = "UPDATE DEPART SET DEPART_NAME = ? WHERE DEPART_ID = ?";
	public static String DEPART_DELETE_SQL = "DELETE FROM DEPART WHERE DEPART_ID = ?";
	public static String DEPART_SELECT_ALL_SQL = "SELECT DEPART_ID ID, DEPART_NAME NAME FROM DEPART";
	public static String DEPART_SELECT_SQL = DEPART_SELECT_ALL_SQL + " WHERE DEPART_ID = ?";
}
