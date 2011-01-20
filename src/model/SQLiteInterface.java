package model;
import java.sql.*;
import javax.swing.JOptionPane;

public class SQLiteInterface {
	private String driverName;
	private String dbName;
	private String urlPrefix;
	
	private Driver driver;
	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	
	public SQLiteInterface() { 
		this("org.sqlite.JDBC", "ChocAnon.db", "jdbc:sqlite:"); 
	}
	
	public SQLiteInterface(String driverName, String dbName, String urlPrefix) {
		this.driverName = driverName;
		this.dbName 	= dbName;
		this.urlPrefix	= urlPrefix;
		this.connect();
	}
	
	
	private void connect() {
		try {
			driver = (Driver)Class.forName(driverName).newInstance();
			DriverManager.registerDriver(driver);
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.toString(), "Error loading database driver:", JOptionPane.ERROR_MESSAGE);
		}

		try {
			String url = urlPrefix + dbName;
			con = DriverManager.getConnection(url);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString(), "Error creating connection:", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void execute(String query) {
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.toString(), "Error creating or running statement:", JOptionPane.ERROR_MESSAGE);
			try {
				con.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.toString(), "Could not close connection:", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private void testFetchResults() {
	       try
	        {
	            String abbrev, name;
	            while(rs.next())
	            {
	                abbrev = rs.getString("abbrev");
	                name = rs.getString("name");
	                System.out.println(abbrev + "  |  " + name);
	            }
	        }
	        catch(Exception e)
	        {
	            System.out.println("Error processing results: " + e.toString());
	            try
	            {
	                rs.close();
	                stmt.close();
	                con.close();
	            }
	            catch(Exception ex)
	            {
	            }
	        }
	}
	
	public void testFetchStates() {
		this.execute("SELECT * FROM state;");
		this.testFetchResults();
	}
	
}
