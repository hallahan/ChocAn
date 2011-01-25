package model;
import java.sql.*;
import javax.swing.JOptionPane;
import java.util.Vector;


public class SQLiteInterface {
	static final int VECTOR_ALLOC_SIZE = 50;
	
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
			JOptionPane.showMessageDialog(null, "Error executing SQL statement: " + ex.toString(), "Error creating or running statement:", JOptionPane.ERROR_MESSAGE);
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
	private Vector<Member> fetchMemberResults() {
		Vector<Member> results = null;
		Member row = null;
		
		try {
			while (rs.next()) {
				if (results == null)
					results = new Vector<Member>(VECTOR_ALLOC_SIZE);
				row = new Member();
				
				//fills in Member row with values from RowSet rs
				row.member_id = rs.getInt(1);
				if (rs.getInt(2) == 1)
					row.active_status = true;
				else
					row.active_status = false;
				row.first = rs.getString(3);
				row.middle = rs.getString(4);
				row.last = rs.getString(5);
				row.address = rs.getString(6);
				row.city = rs.getString(7);
				row.state = rs.getString(8);
				row.zip = rs.getString(9);
				
				results.add(row);
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error Fetching Member Results: " + e.toString(), "Error Fetching Member Results: ", JOptionPane.ERROR_MESSAGE);
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Error closing result set, statement and connection: " + e.toString(), "Error Fetching Member Results: ", JOptionPane.ERROR_MESSAGE);
			}
		} 
		return results;
	}
	
	
	public Vector<Member> retrieveMemberTable() {
		String query = "SELECT * FROM member;";
		this.execute(query);
		return this.fetchMemberResults();
	}
	public Vector<Member> retrieveMemberTable(String searchKey) {
		String query =	"SELECT * FROM member m WHERE m.last='" + searchKey + 
						"' OR m.first='" + searchKey +
						"' OR m.middle='" + searchKey +
						"' OR m.address='" + searchKey +
						"' OR m.city='" + searchKey +
						"' OR m.state='" + searchKey +
						"' OR m.zip='" + searchKey +
						"';";
		this.execute(query);
		return this.fetchMemberResults();
	}
	public Vector<Member> retrieveMemberTable(Integer member_id) {
		String query = "SELECT * FROM member m WHERE m.member_id=" + member_id + ";";
		this.execute(query);
		return this.fetchMemberResults();
	}
	
	
	
	
	
	
	
	
	public void testFetchStates() {
		this.execute("SELECT * FROM state WHERE state.abbrev='AK';");
		this.testFetchResults();
	}

}
