import java.sql.*;
import java.util.ArrayList;


public class Operations {

	public static void createTableEvent()	{
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			
			String sql = "CREATE TABLE IF NOT EXISTS Event " +
					"(Name 		TEXT PRIMARY KEY," +
					" Place     TEXT , " + 
					" Date      TEXT " +
					");";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Table created successfully");
	}
	public static void createTablePlace()	{
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS Place " +
					"(PName 		TEXT PRIMARY KEY," +
					" City     		TEXT ," + 
					" Country  		TEXT " +
					")"; 
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Table created successfully");
	}
	public static void insertIntoTable(Place place)	{
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			// java.sql.Date sqlDate = new java.sql.Date(event.getDate().getTime());

			stmt = c.createStatement();
			String sql = 	"INSERT " + 
							"INTO Place (PName,City,Country) " +
							"VALUES ('" + place.getName() + "', '" + place.getCity() + "', '" + place.getCountry() +
							"');";
			stmt.executeUpdate(sql);

			stmt.close();
			c.commit();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Record created successfully");
	}
	public static void insertIntoTable(Event event)	{
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			// java.sql.Date sqlDate = new java.sql.Date(event.getDate().getTime());

			stmt = c.createStatement();
			String sql = 	"INSERT " + 
							"INTO Event (Name,Place,Date) " +
							"VALUES ('" + event.getName() + "', '" + event.getPlace() + "', '" + event.getDate() +
							"');";
			stmt.executeUpdate(sql);

			stmt.close();
			c.commit();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Record created successfully");
	}

	public static String[][] selectAll()	{
		Connection c = null;
		Statement stmt = null;
		String[][] results = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			// int length = selectFromTable(new Event("","",""), "all").length;
			ResultSet rs;
			stmt = c.createStatement();
			rs = stmt.executeQuery( "SELECT * " +
									"FROM Event " +
									"INNER JOIN Place " +
									"ON Event.Place = Place.PName " +
									"ORDER BY Date DESC;" );
			ResultSet count;
			count = stmt.executeQuery( "SELECT COUNT (*) AS count1 " +
									   "FROM Event;");
			int length = 0;
			while ( count.next() )	{
				length = count.getInt("count1");
			}
			System.out.println(length);
			results = new String[length][5];
			int i = 0;
			while ( rs.next() ) {
				results[i][0] =	rs.getString("Name");
				System.out.println(results[1][0]);
				results[i][1] = rs.getString("Place");
				results[i][2] = rs.getString("Date");
				results[i][3] = rs.getString("City");
				results[i][4] =	rs.getString("Country");
				i++;
			}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Operation done successfully");
		return results;
	}
	
	public static String[] selectItems(String table, String column)	{
		Connection c = null;
		Statement stmt = null;
		String[] results = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			ResultSet rs;
			stmt = c.createStatement();
			String sql = String.format("SELECT %s FROM %s;", column, table);
			rs = stmt.executeQuery(sql);
			ArrayList<String> res = new ArrayList<String>();
			while (rs.next())	{
				res.add(rs.getString("PName"));
			}
			results = new String[res.size()];
			for (int i = 0; i < res.size(); i++)	{
				results[i] = res.get(i);
			}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Operation done successfully");
		return results;
	}
		
	public static void deleteDb()	{
		Connection c = null;
		Statement stmt = null;
		try	{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			c.setAutoCommit(false);
			System.out.println("Opened db successfully");
			stmt = c.createStatement();
			String sql = "DROP TABLE IF EXISTS Event;";
			stmt.executeUpdate(sql);
			sql = "DROP TABLE IF EXISTS Place;";
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Tables deleted successfully");
	}
	public static void deleteFromTable(String table, String record)	{
		Connection c = null;
		Statement stmt = null;
		try	{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			c.setAutoCommit(false);
			System.out.println("Opened db successfully");
			stmt = c.createStatement();
			String sql = "DELETE FROM " + table
						 + " WHERE Name='" + record + "';";
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Row deleted successfully");
	}

}

