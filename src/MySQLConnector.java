import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class MySQLConnector {
	//Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts", "root", "jtionnshou@4152");
	private static String url = "jdbc:mysql://localhost:3306/accounts";
    private static String user = "root";
    private static String pass = "jtionnshou@4152";
	
	
	public static void main(String[] args) throws SQLException {
		//System.out.println(mySQLConnection("skin"));
		System.out.println(login("jhu17", "jinsong1281567", "patient"));
		
		//accountTest("jhu17","jinsong1281567");
		//registerTest("jh22", "1154");
		String str = "";
		String st2 = "'ll do my best to assist you. What do you need help with?' )'";
		
		//insertSearch("jhu17", Search.replaceSingleQuote(str), Search.replaceSingleQuote(st2));
		
		//System.out.println(searchResult("1"));
		
		//deleteSearch("8");
		
		//System.out.println(userZipCode("jhu17"));
		
		//List<String> dList = doctorList("jhu17", "1. Who are");
		//System.out.println(dList.get(0) + " " + dList.get(1));
	}
	
	//Medical database
	public static String mySQLConnection(String userInput) throws SQLException {
		// String sqlCommand2 = "Select primary_name, info_link_data00 from mytable";

		String res = "";

		String condition = "";
		String url = "";
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/symptoms", "root", "jtionnshou@4152");

		Statement stmt = con.createStatement();

		String sqlCommand = "Select primary_name, info_link_data00 from mytable where primary_name like '%" + userInput
				+ "%';";

		ResultSet output = stmt.executeQuery(sqlCommand);

		while (output.next()) {
			condition = output.getString("PRIMARY_NAME");
			url = output.getString("INFO_LINK_DATA00");

			// System.out.println(condition + " " + url);
			res += condition + "                                                  " + url + "\n";
		}
		//System.out.println(res);
		con.close();
		return res;

	}

	public static void accountTest(String username, String password) throws SQLException 
	{
		String userName = "";
		String psd = "";
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts?useSSL=false", "root", "jtionnshou@4152");

		Statement stmt = con.createStatement();

		String sqlCommand = "Select username, password from login";

		ResultSet output = stmt.executeQuery(sqlCommand);
		
		while (output.next()) {
			userName = output.getString("username");
			psd = output.getString("password");
			
			System.out.println(userName + "                  " + psd);
			
			/**if(userName.equals(username) && psd.equals(password)) 
			{
				System.out.println(userName + "                  " + psd);
			}
			**/
		}
		con.close();
	}
	
	public static void registerTest(String username, String password) throws SQLException 
	{
		Boolean exist = false;
		String name = "";
		String signUp = "insert into login (username, password) values('" + username + "' , '"  + password + "' )";
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts", "root", "jtionnshou@4152");

		Statement stmt = con.createStatement();

		String sqlCommand = "Select username from login";

		ResultSet output = stmt.executeQuery(sqlCommand);

		while (output.next()) {
			name = output.getString("username");

			if(name.equals(username)) 
			{
				exist = true;
			}
		}
		
		if(exist) 
		{
			JOptionPane.showMessageDialog(null, "Username has already existed", "Message", JOptionPane.PLAIN_MESSAGE);
		}else {
			stmt.execute(signUp);
			JOptionPane.showMessageDialog(null, "Your account have created", "Message", JOptionPane.PLAIN_MESSAGE);
		}
		con.close();
	}
	
	public static boolean login(String username, String password, String portal) throws SQLException {
		String name = "";
		String psd = "";
		Connection con = DriverManager.getConnection(url, user, pass);

		Statement stmt = con.createStatement();

		String sqlCommand = "";
		
		if(portal.equalsIgnoreCase("patient")) {
			sqlCommand ="Select username, password from login";
		}else {
			sqlCommand ="Select username, password from studentLogin";
		}
		
		ResultSet output = stmt.executeQuery(sqlCommand);

		while (output.next()) {
			name = output.getString("username");
			psd = output.getString("password");

			if(name.equals(username) && psd.equals(password)) 
			{
				return true;
			}
		}
		con.close();
		return false;
	}
	
	public static boolean register(String username, String password, String zipCode, String portal) throws SQLException 
	{
		Boolean exist = false;
		String name = "";
		int zip = Integer.parseInt(zipCode); 
		
		String signUp = "";
		Connection con = DriverManager.getConnection(url, user, pass);
		
		Statement stmt = con.createStatement();

		String sqlCommand = "";
		
		if(portal.equalsIgnoreCase("patient")) {
			sqlCommand = "Select username from login"; 
			signUp = "insert into login (username, password, zip) values('" + username + "' , '"  + password + "', '"  + zip + "' )";
		}else {
			sqlCommand = "Select username from studentLogin";
			signUp = "insert into studentLogin (username, password, zip) values('" + username + "' , '"  + password + "', '"  + zip + "' )";
		}

		ResultSet output = stmt.executeQuery(sqlCommand);

		while (output.next()) {
			name = output.getString("username");

			if(name.equals(username)) 
			{
				exist = true;
			}
		}
		
		if(exist) 
		{
			JOptionPane.showMessageDialog(null, "Username has already existed", "Message", JOptionPane.PLAIN_MESSAGE);
			con.close();
			return false;
		}else {
			stmt.execute(signUp);
			JOptionPane.showMessageDialog(null, "Your account have created", "Message", JOptionPane.PLAIN_MESSAGE);
			con.close();
			return true;
		}
	}
	
	
	public static boolean usernameCheck(String username) throws SQLException 
	{
		Boolean exist = false;
		String name = "";
		Connection con = DriverManager.getConnection(url, user, pass);
		
		Statement stmt = con.createStatement();

		String sqlCommand = "Select username from login";

		ResultSet output = stmt.executeQuery(sqlCommand);

		while (output.next()) {
			name = output.getString("username");

			if(name.equals(username)) 
			{
				exist = true;
			}
		}
		
		if(exist) 
		{
			return true;
		}else {
			return false;
		}
	}
	
	
	public static List<String> searchList(String username, String portal) throws SQLException {

		String ID = "";
		String keyword = "";
		List<String> history = new ArrayList<String>();
		
		Connection con = DriverManager.getConnection(url, user, pass);
		
		Statement stmt = con.createStatement();

		String sqlCommand = "Select * from record where username = '" + username + "' ";
		
		if(portal.equalsIgnoreCase("patient")) {
			sqlCommand = "Select * from record where username = '" + username + "' ";
		}else {
			sqlCommand = "Select * from sRecord where username = '" + username + "' ";
		}

		ResultSet output = stmt.executeQuery(sqlCommand);

		while (output.next()) {
			keyword = output.getString("keyword");
			history.add(keyword);
		}
		con.close();
		return history;
	}
	
	public static List<String> doctorList(String username, String keyword) throws SQLException {
		String d1 = "";
		String d2 = "";
		List<String> doctors = new ArrayList<String>();
		
		Connection con = DriverManager.getConnection(url, user, pass);
		
		Statement stmt = con.createStatement();

		String sqlCommand = "Select * from record where username = '" + username + "' and keyword = '" + keyword + "'";
		
		ResultSet output = stmt.executeQuery(sqlCommand);

		while (output.next()) {
			d1 = output.getString("doctor1");
			d2 = output.getString("doctor2");
			
			doctors.add(d1);
			doctors.add(d2);
		}
		con.close();
		return doctors;
	}
	
	public static String searchResult(String username, String keyword, String portal) throws SQLException{
		String search = "";
		String outcome = "";
		String res = "";
		
		Connection con = DriverManager.getConnection(url, user, pass);
		
		Statement stmt = con.createStatement();

		String sqlCommand = "";
		
		if(portal.equalsIgnoreCase("patient")) {
			 sqlCommand = "Select * from record where username = '" + username + "' and keyword = '" + keyword + "'";
		}else {
			sqlCommand = "Select * from sRecord where username = '" + username + "' and keyword = '" + keyword + "'";
		}

		ResultSet output = stmt.executeQuery(sqlCommand);

		while (output.next()) {
			search = output.getString("Search");
			outcome = output.getString("Outcome");
			
			res = "Question: \n" +search + "\n\nOutcome: \n" + outcome;
		}
		con.close();
		return res;
	}
	
	public static String searchGPTResp(String keyword, String username, String portal) throws SQLException{
		String outcome = "";
		String res = "";
		
		Connection con = DriverManager.getConnection(url, user, pass);
		
		Statement stmt = con.createStatement();

		String sqlCommand = "";
		
		if(portal.equalsIgnoreCase("patient")) {
			sqlCommand = "Select * from record where username = '" + username + "' and keyword = '" + keyword + "'";
		}else {
			sqlCommand = "Select * from sRecord where username = '" + username + "' and keyword = '" + keyword + "'";
		}

		ResultSet output = stmt.executeQuery(sqlCommand);

		while (output.next()) {
			outcome = output.getString("Outcome");
			
			res = outcome;
		}
		con.close();
		return res;
	}
	
	public static void insertSearch(String username, String input, String output, String key, String d1, String d2, String portal) throws SQLException {
		Connection con = DriverManager.getConnection(url, user, pass);
		
		Statement stmt = con.createStatement();

		String sqlCommand = "";
		
		if(portal.equalsIgnoreCase("patient")) {
			sqlCommand = "insert into accounts.record (username, search, outcome, keyword, doctor1, doctor2) "
					+ "values ('"+ username +"', '"+ input +"', '"+ output +"' , '"+ key +"' , '"+ d1 +"' , '"+ d2 +"' )";
		}else {
			sqlCommand = "insert into accounts.sRecord (username, search, outcome, keyword) "
					+ "values ('"+ username +"', '"+ input +"', '"+ output +"' , '"+ key +"')";
		}
		
		stmt.execute(sqlCommand);
		
		con.close();

	}
	
	public static void deleteSearch(String username, String key, String portal) throws SQLException {		
		Connection con = DriverManager.getConnection(url, user, pass);
		Statement stmt = con.createStatement();
		
		String sqlCommand = "delete from record where keyword = '" + key + "' and username = '" + username + "'";
		
		if(portal.equalsIgnoreCase("patient")) {
			sqlCommand = "delete from record where keyword = '" + key + "' and username = '" + username + "'";
		}else {
			sqlCommand = "delete from sRecord where keyword = '" + key + "' and username = '" + username + "'";
		}
		
		stmt.execute(sqlCommand);
		
		con.close();
	}
	
	public static String userZipCode(String username) throws SQLException {
		String zip = "";
		Connection con = DriverManager.getConnection(url, user, pass);
		
		Statement stmt = con.createStatement();

		String sqlCommand = "Select zip from login where username = '" + username + "' ";

		ResultSet output = stmt.executeQuery(sqlCommand);

		while (output.next()) {
			zip = String.valueOf(output.getInt("zip"));
		}
		con.close();
		
		return zip;
	}

}
