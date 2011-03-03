package model;
import java.sql.*;
import javax.swing.JOptionPane;
import java.util.Vector;


public class SQLiteInterface {
	static final int VECTOR_ALLOC_SIZE = 50;
	
	/* This is the one instance that exists throughout the entire life-cycle
	 * of the program.  The can be retrieved at any time using the static
	 * singleton method.  This is to be used to prevent many instances of
	 * the same database hanging around.
	 */
	private static SQLiteInterface singletonInstance = null;
	
	
	private String driverName;
	private String dbName;
	private String urlPrefix;
	
	private Driver driver;
	private Connection con=null;
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
	
	//method that retrieves the singleton instance
	public static SQLiteInterface singleton() {
		if (singletonInstance == null) {
			singletonInstance = new SQLiteInterface();
		}
		return singletonInstance;
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
	
	private void execute(String query) {
		try {
			if (con==null)
				this.connect();
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error executing SQL statement: " + ex.toString(), "Error creating or running statement:", JOptionPane.ERROR_MESSAGE);
			try {
				con.close();
				con=null;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.toString(), "Could not close connection:", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	//Call this method to delete as well.
	private void update(String query) {
		try {
			if (con==null)
				this.connect();
			stmt = con.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error executing SQL update: " + ex.toString(), "Error creating or running update:", JOptionPane.ERROR_MESSAGE);
			try {
				con.close();
				con=null;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.toString(), "Could not close connection:", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	

	//~~~~~~~~~~~~~~~~~~~~~~~~~~~OPERATIONS ON MEMBER TABLE~~~~~~~~~~~~~~~~~~~~~~~~
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
				row.active_status = rs.getInt(2);
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
				con=null;
			} catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Error closing result set, statement and connection: " + e.toString(), "Error Fetching Member Results: ", JOptionPane.ERROR_MESSAGE);
			}
		} 
		return results;
	}
	
	public Member retrieveMember(int member_id) {
		String query = "SELECT * FROM member m WHERE m.member_id=" + member_id + ";";
		this.execute(query);
		return this.fetchMemberResults().firstElement();
	}
	public Vector<Member> retrieveMemberTable() {
		String query = "SELECT * FROM member;";
		this.execute(query);
		return this.fetchMemberResults();
	}
	public Vector<Member> retrieveMemberTableSorted(String field, boolean ascending) {
		String query;
		if (ascending == true)
			query = "SELECT * FROM member ORDER BY " + field + ";";
		else
			query = "SELECT * FROM member ORDER BY " + field + " DESC;";
		this.execute(query);
		return this.fetchMemberResults();		
	}
	public Vector<Member> retrieveMemberTable(String searchKey) {
		String query =	"SELECT * FROM member m WHERE m.last LIKE '%" + searchKey + 
						"%' OR m.first LIKE '%" + searchKey +
						"%' OR m.middle LIKE '%" + searchKey +
						"%' OR m.address LIKE '%" + searchKey +
						"%' OR m.city LIKE '%" + searchKey +
						"%' OR m.state LIKE '%" + searchKey +
						"%' OR m.zip LIKE '%" + searchKey +
						"%' OR m.member_id ='" + searchKey + "';";
		this.execute(query);
		return this.fetchMemberResults();
	}
	public Vector<Member> retrieveMemberTableSorted(String searchKey, String sortField, boolean ascending) {
		String query;
		if (ascending == true) {
			query =	"SELECT * FROM member m WHERE m.last LIKE '%" + searchKey + 
						"%' OR m.first LIKE '%" + searchKey +
						"%' OR m.middle LIKE '%" + searchKey +
						"%' OR m.address LIKE '%" + searchKey +
						"%' OR m.city LIKE '%" + searchKey +
						"%' OR m.state LIKE '%" + searchKey +
						"%' OR m.zip LIKE '%" + searchKey +
						"%' OR m.member_id = '" + searchKey +
						"' ORDER BY " + sortField + ";";
		} else {
			query =	"SELECT * FROM member m WHERE m.last LIKE '%" + searchKey + 
						"%' OR m.first LIKE '%" + searchKey +
						"%' OR m.middle LIKE '%" + searchKey +
						"%' OR m.address LIKE '%" + searchKey +
						"%' OR m.city LIKE '%" + searchKey +
						"%' OR m.state LIKE '%" + searchKey +
						"%' OR m.zip LIKE '%" + searchKey +
						"%' OR m.member_id = '" + searchKey +
						"' ORDER BY " + sortField + " DESC;";
		}
		this.execute(query);
		return this.fetchMemberResults();
	}
	public Vector<Member> retrieveMemberTableActive() {
		String query = "SELECT * FROM member m WHERE m.active_status=1;";
		this.execute(query);
		return this.fetchMemberResults();
	}
	public Vector<Member> retrieveMemberTableActiveSorted(String sortField, boolean ascending) {
		String query;
		if (ascending == true) {
			query = "SELECT * FROM member m WHERE m.active_status=1 ORDER BY " + sortField + ";";
		} else {
			query = "SELECT * FROM member m WHERE m.active_status=1 ORDER BY " + sortField + " DESC;";
		}
		this.execute(query);
		return this.fetchMemberResults();
	}
	public Vector<Member> retrieveMemberTableInactive() {
		String query = "SELECT * FROM member m WHERE m.active_status=0;";
		this.execute(query);
		return this.fetchMemberResults();
	}
	public Vector<Member> retrieveMemberTableInactiveSorted(String sortField, boolean ascending) {
		String query;
		if (ascending == true) {
			query = "SELECT * FROM member m WHERE m.active_status=0 ORDER BY " + sortField + ";";
		} else {
			query = "SELECT * FROM member m WHERE m.active_status=0 ORDER BY " + sortField + " DESC;";
		}
		this.execute(query);
		return this.fetchMemberResults();
	}
	
	//The member_id field of the Member instance provided is not read.
	//A new member_id is automatically assigned by SQLite.
	public void addMember(Member nm) {
		String query =	"INSERT INTO member VALUES (null, " +
						nm.active_status + ", '" +
						nm.first + "', '" +
						nm.middle + "', '" +
						nm.last + "', '" +
						nm.address + "', '" +
						nm.city + "', '" +
						nm.state + "', '" +
						nm.zip + "');";
		this.update(query);
	}
	public void updateMember(Member um) {
		String query =	"UPDATE member SET " +
						"active_status=" + um.active_status + ", " +
						"first='" + um.first + "', " +
						"middle='" + um.middle + "', " +
						"last='" + um.last + "', " +
						"address='" +um.address + "', " +
						"city='" + um.city + "', " +
						"state='" + um.state + "', " +
						"zip='" + um.zip + 
						"' WHERE member.member_id =" +
						um.member_id + ";";
		this.update(query);
	}
	public void deleteMember(int member_id) {
		String query = 	"DELETE FROM member WHERE member.member_id=" + member_id + ";";
		this.update(query);
	}
	
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~OPERATIONS ON PROVIDER TABLE~~~~~~~~~~~~~~~~~~~~~~
	private Vector<Provider> fetchProviderResults () {
		Vector<Provider> results = null;
		Provider row = null;
		
		try {
			while (rs.next()) {
				if (results == null)
					results = new Vector<Provider>(VECTOR_ALLOC_SIZE);
				row = new Provider();
				
				//fills in Provider row with values from RowSet rs
				row.provider_id = rs.getInt(1);
				row.name = rs.getString(2);
				row.providertype_id = rs.getInt(3);
				row.address = rs.getString(4);
				row.city = rs.getString(5);
				row.state = rs.getString(6);
				row.zip = rs.getString(7);
				
				results.add(row);
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error Fetching Provider Results: " + e.toString(), "Error Fetching Provider Results: ", JOptionPane.ERROR_MESSAGE);
			try {
				rs.close();
				stmt.close();
				con.close();
				con=null;
			} catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Error closing result set, statement and connection: " + e.toString(), "Error Fetching Provider Results: ", JOptionPane.ERROR_MESSAGE);
			}
		} 
		return results;
	}

	public Provider retrieveProvider(int provider_id) {
		String query = "SELECT * FROM provider p WHERE p.provider_id=" + provider_id + ";";
		this.execute(query);
//		return this.fetchProviderResults().firstElement();
		
		// Prevents null pointer exception when there is no corresponding provider
		Vector<Provider> vp = fetchProviderResults();
		if (vp != null) {
			return vp.firstElement();
		} else {
			return new Provider();
		}
	}
	public Vector<Provider> retrieveProviderTable() {
		String query = "SELECT * FROM provider;";
		this.execute(query);
		return this.fetchProviderResults();
	}
	public Vector<Provider> retrieveProviderTableSorted(String sortField, boolean ascending) {
		String query;
		if (ascending == true ) {
			query = "SELECT * FROM provider ORDER BY " + sortField + ";";
		} else {
			query = "SELECT * FROM provider ORDER BY " + sortField + " DESC;";
		}
		this.execute(query);
		return this.fetchProviderResults();
	}
	public Vector<Provider> retrieveProviderTable(String searchKey) {
		String query =	"SELECT * FROM provider p WHERE p.name LIKE '%" + searchKey +
						"%'  OR p.address LIKE '%" + searchKey +
						"%' OR p.city LIKE '%" + searchKey + 
						"%' OR p.state LIKE '%" + searchKey +
						"%' OR p.zip LIKE '%" + searchKey + 
						"%' OR p.provider_id = '" + searchKey + "';";
		this.execute(query);
		return this.fetchProviderResults();
	}
	public Vector<Provider> retrieveProviderTableSorted(String searchKey, String sortField, boolean ascending) {
		String query;
		if (ascending == true) {
			query =	"SELECT * FROM provider p WHERE p.name LIKE '%" + searchKey +
						"%'  OR p.address LIKE '%" + searchKey +
						"%' OR p.city LIKE '%" + searchKey + 
						"%' OR p.state LIKE '%" + searchKey +
						"%' OR p.zip LIKE '%" + searchKey + 
						"%' OR p.provider_id = '" + searchKey +
						"' ORDER BY " + sortField + ";";
		} else {
			query =	"SELECT * FROM provider p WHERE p.name LIKE '%" + searchKey +
						"%'  OR p.address LIKE '%" + searchKey +
						"%' OR p.city LIKE '%" + searchKey + 
						"%' OR p.state LIKE '%" + searchKey +
						"%' OR p.zip LIKE '%" + searchKey + 
						"%' OR p.provider_id = '" + searchKey + 
						"' ORDER BY " + sortField + " DESC;";
		}
		this.execute(query);
		return this.fetchProviderResults();
	}
	
	public void addProvider(Provider np) {
		String query =	"INSERT INTO provider VALUES (null, '" +
						np.name + "', " +
						np.providertype_id + ", '" +
						np.address + "', '" +
						np.city + "', '" +
						np.state + "', '" +
						np.zip + "');";
		this.update(query);			
	}
	public void updateProvider(Provider up) {
		String query =	"UPDATE provider SET " + 
						"name='" + up.name + "', " +
						"providertype_id=" + up.providertype_id + ", " +
						"address='" + up.address + "', " +
						"city='" + up.city + "', " +
						"state='" + up.state + "', " +
						"zip='" + up.zip +
						"' WHERE provider.provider_id =" +
						up.provider_id + ";";
		this.update(query);
	}
	public void deleteProvider(int provider_id) {
		String query = "DELETE FROM provider WHERE provider.provider_id=" + provider_id + ";";
		this.update(query);
	}
	
	
	//~~~~~~~~~~~~~~~~~~~~~OPERATIONS ON PROVIDERTYPE TABLE~~~~~~~~~~~~~~~~~~~~~~~~
	private Vector<ProviderType> fetchProviderTypeResults () {
		Vector<ProviderType> results = null;
		ProviderType row = null;
		
		try {
			while (rs.next()) {
				if (results == null)
					results = new Vector<ProviderType>(VECTOR_ALLOC_SIZE);
				row = new ProviderType();
				
				//fills in Member row with values from RowSet rs
				row.providertype_id = rs.getInt(1);
				row.name = rs.getString(2);
				row.desc = rs.getString(3);
				
				results.add(row);
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error Fetching ProviderType Results: " + e.toString(), "Error Fetching ProviderType Results: ", JOptionPane.ERROR_MESSAGE);
			try {
				rs.close();
				stmt.close();
				con.close();
				con=null;
			} catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Error closing result set, statement and connection: " + e.toString(), "Error Fetching ProviderType Results: ", JOptionPane.ERROR_MESSAGE);
			}
		} 
		return results;
	}
	
	public ProviderType retrieveProviderType(int providertype_id) {
		String query = "SELECT * FROM providertype pt WHERE pt.providertype_id=" + providertype_id + ";";
		this.execute(query);
		
		//prevents null pointer exception when there is not a provider type set
		Vector<ProviderType> vpt = fetchProviderTypeResults();
		if (vpt != null) {
			return vpt.firstElement();
		} else {
			return new ProviderType();
		}
	}
	public Vector<ProviderType> retrieveProviderTypeTable() {
		String query = "SELECT * FROM providertype;";
		this.execute(query);
		return this.fetchProviderTypeResults();
	}
	public Vector<ProviderType> retrieveProviderTypeTableSorted(String sortField, boolean ascending) {
		String query;
		if (ascending == true) {
			query = "SELECT * FROM providertype ORDER BY " + sortField + ";";
		} else {
			query = "SELECT * FROM providertype ORDER BY " + sortField + " DESC;";
		}
		this.execute(query);
		return this.fetchProviderTypeResults();
	}
	public Vector<ProviderType> retrieveProviderTypeTable(String searchKey) {
		String query =	"SELECT * FROM providertype pt WHERE pt.name LIKE '%" + searchKey +
						"%' OR pt.desc LIKE '%" + searchKey + "%' OR pt.providertype_id = '" + searchKey + "';";
		this.execute(query);
		return this.fetchProviderTypeResults();
	}
	public Vector<ProviderType> retrieveProviderTypeTableSorted(String searchKey, String sortField, boolean ascending) {
		String query;
		if (ascending == true) {
			query = "SELECT * FROM providertype pt WHERE pt.name LIKE '%" + searchKey +
					"%' OR pt.desc LIKE '%" + searchKey + "%' OR pt.providertype_id = '" + searchKey + "' ORDER BY " + sortField + ";";
		} else {
			query = "SELECT * FROM providertype pt WHERE pt.name LIKE '%" + searchKey +
					"%' OR pt.desc LIKE '%" + searchKey + "%' OR pt.providertype_id = '" + searchKey + "' ORDER BY " + sortField + " DESC;";			
		}
		this.execute(query);
		return this.fetchProviderTypeResults();
	}
	public void addProviderType(ProviderType npt) {
		String query =	"INSERT INTO providertype VALUES (null, '" +
						npt.name + "', '" +
						npt.desc + "');";
		this.update(query);
	}
	public void updateProviderType(ProviderType upt) {
		String query =	"UPDATE providertype SET " +
						"name='" + upt.name + "', " +
						"desc='" + upt.desc +
						"' WHERE providertype.providertype_id = " +
						upt.providertype_id + ";";
		this.update(query);
	}
	public void deleteProviderType(int providertype_id) {
		String query = "DELETE FROM providertype pt WHERE pt.providertype_id=" + providertype_id + ";";
		this.update(query);
	}
	
	
	//~~~~~~~~~~~~~~~~~~~~OPERATIONS OF SERVICE TABLE~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	private Vector<Service> fetchServiceResults () {
		Vector<Service> results = null;
		Service row = null;
		
		try {
			while (rs.next()) {
				if (results == null)
					results = new Vector<Service>(VECTOR_ALLOC_SIZE);
				row = new Service();
				
				//fills in Member row with values from RowSet rs
				row.service_id = rs.getInt(1);
				row.name = rs.getString(2);
				row.fee = rs.getString(3);
				
				results.add(row);
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error Fetching Service Results: " + e.toString(), "Error Fetching Service Results: ", JOptionPane.ERROR_MESSAGE);
			try {
				rs.close();
				stmt.close();
				con.close();
				con=null;
			} catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Error closing result set, statement and connection: " + e.toString(), "Error Fetching Service Results: ", JOptionPane.ERROR_MESSAGE);
			}
		} 
		return results;
	}
	
	public Service retrieveService(int service_id) {
		String query = "SELECT * FROM service s WHERE s.service_id=" + service_id +";";
		this.execute(query);
//		return this.fetchServiceResults().firstElement();
		
		//prevents null pointer exception
		Vector<Service> s = fetchServiceResults();
		if (s != null) {
			return s.firstElement();
		} else {
			return new Service();
		}
		
	}
	public Vector<Service> retrieveServiceTable() {
		String query = "SELECT * FROM service;";
		this.execute(query);
		return this.fetchServiceResults();
	}
	public Vector<Service> retrieveServiceTableSorted(String searchField, boolean ascending) {
		String query;
		if (ascending == true) {
			query = "SELECT * FROM service ORDER BY " + searchField + ";";
		} else {
			query = "SELECT * FROM service ORDER BY " + searchField + " DESC;";
		}
		this.execute(query);
		return this.fetchServiceResults();
	}
	public Vector<Service> retrieveServiceTableSorted(String searchKey, String searchField, boolean ascending) {
		String query;
		if (ascending == true) {
			query = "SELECT * FROM service s WHERE s.name LIKE '%" + searchKey + 
					"%' OR s.fee LIKE '%" + searchKey + "%' ORDER BY " + searchField + ";";
		} else {
			query = "SELECT * FROM service s WHERE s.name LIKE '%" + searchKey + 
					"%' OR s.fee LIKE '%" + searchKey + "%' ORDER BY " + searchField + " DESC;";
		}
		this.execute(query);
		return fetchServiceResults();
	}
	
	public void addService(Service s) {
		String query = "INSERT INTO service VALUES (null, '" +
						s.name + "', '" +
						s.fee + "');";
		this.update(query);
	}
	public void updateService(Service s) {
		String query = "UPDATE service SET " +
						"name='" + s.name + "', " +
						"fee='" + s.fee +
						"' WHERE service.service_id = " +
						s.service_id + ";";
//		System.out.println(query);
		this.update(query);
	}
	public void deleteService(int service_id) {
		String query = "DELETE FROM service WHERE service_id = " + service_id + ";";
		System.out.println("delete service query: " + query);
		this.update(query);
	}
	
	
	//~~~~~~~~~~~~~~~~~~~~OPERATIONS OF SERVICE INSTANCE TABLE~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	private Vector<ServiceInstance> fetchServiceInstanceResults () {
		Vector<ServiceInstance> results = null;
		ServiceInstance row = null;
		
		try {
			while (rs.next()) {
				if (results == null)
					results = new Vector<ServiceInstance>(VECTOR_ALLOC_SIZE);
				row = new ServiceInstance();
				
				//fills in Member row with values from RowSet rs
				row.instance_id = rs.getInt(1);
				row.member_id = rs.getInt(2);
				row.service_id = rs.getInt(3);
				row.provider_id = rs.getInt(4);
				row.setDate_providedDB(rs.getString(5));
				row.setTime_stampDB(rs.getString(6));
				row.comments = rs.getString(7);
				
				results.add(row);
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error Fetching ServiceInstance Results: " + e.toString(), "Error Fetching ServiceInstance Results: ", JOptionPane.ERROR_MESSAGE);
			try {
				rs.close();
				stmt.close();
				con.close();
				con=null;
			} catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Error closing result set, statement and connection: " + e.toString(), "Error Fetching ServiceInstance Results: ", JOptionPane.ERROR_MESSAGE);
			}
		} 
		return results;
	}
	
	public ServiceInstance retrieveServiceInstance(int instance_id) {
		String query = "SELECT * FROM serviceinstance si WHERE si.instance_id=" + instance_id + ";";
		this.execute(query);
//		return this.fetchServiceInstanceResults().firstElement();
		
		//prevents null pointer exception
		Vector<ServiceInstance> si = fetchServiceInstanceResults();
		if (si != null) {
			return si.firstElement();
		} else {
			return new ServiceInstance();
		}
	}
	public Vector<ServiceInstance> retrieveServiceInstanceTable() {
		String query = "SELECT * FROM serviceinstance;";
		this.execute(query);
		return this.fetchServiceInstanceResults();
	}
	public Vector<ServiceInstance> retrieveServiceInstanceTableSorted(String sortField, boolean ascending) {
		String query;
		if (ascending == true) {
			query = "SELECT * FROM serviceinstance ORDER BY " + sortField + ";";
		} else {
			query = "SELECT * FROM serviceinstance ORDER BY " + sortField + " DESC;";
		}
		this.execute(query);
		return this.fetchServiceInstanceResults();
	}
	public Vector<ServiceInstance> retrieveServiceInstanceTableForMember(int member_id) {
		String query = "SELECT * FROM serviceinstance si WHERE si.member_id=" +
						member_id + ";";
		this.execute(query);
		return fetchServiceInstanceResults();
	}
	public Vector<ServiceInstance> retrieveServiceInstanceTableForMemberSorted(int member_id, String sortField, boolean ascending) {
		String query;
		if (ascending == true) {
			query = "SELECT * FROM serviceinstance si WHERE si.member_id=" + member_id + " ORDER BY " + sortField + ";";
		} else {
			query = "SELECT * FROM serviceinstance si WHERE si.member_id=" + member_id + " ORDER BY " + sortField + " DESC;";
		}
		this.execute(query);
		return fetchServiceInstanceResults();
	}
	public Vector<ServiceInstance> retrieveServiceInstanceTableForMemberSortedPastWeek(int member_id, String sortField, boolean ascending) {
		String query;
		if (ascending == true) {
			query = "SELECT * FROM serviceinstance si WHERE si.member_id=" + member_id + " AND " + sortField + " BETWEEN date('now', '-7 day') AND date('now') ORDER BY " + sortField + ";";
		} else {
			query = "SELECT * FROM serviceinstance si WHERE si.member_id=" + member_id + " AND " + sortField + " BETWEEN date('now', '-7 day') AND date('now') ORDER BY " + sortField + " DESC;";
		}
		this.execute(query);
		return fetchServiceInstanceResults();
	}
	public Vector<ServiceInstance> retrieveServiceInstanceTableForMemberSortedTimespan(int member_id, String sortField, boolean ascending, String from, String to) {
		String query;
		if (ascending == true) {
			query = "SELECT * FROM serviceinstance si WHERE si.member_id=" + member_id + " AND " + sortField + " BETWEEN '" + from + "' AND '" + to + "' ORDER BY " + sortField + ";";
		} else {
			query = "SELECT * FROM serviceinstance si WHERE si.member_id=" + member_id + " AND " + sortField + " BETWEEN '" + from + "' AND '" + to + "' ORDER BY " + sortField + " DESC;";
		}
		this.execute(query);
		return fetchServiceInstanceResults();
	}
	public Vector<ServiceInstance> retrieveServiceInstanceTableForMemberAndProviderSorted(int member_id, int provider_id, String sortField, boolean ascending) {
		String query;
		if (ascending == true) {
			query = "SELECT * FROM serviceinstance si WHERE si.member_id=" + member_id + " AND si.provider_id=" + provider_id + " ORDER BY " + sortField + ";";
		} else {
			query = "SELECT * FROM serviceinstance si WHERE si.member_id=" + member_id + " AND si.provider_id=" + provider_id + " ORDER BY " + sortField + " DESC;";
		}
		this.execute(query);
		return fetchServiceInstanceResults();
	}
	public Vector<ServiceInstance> retrieveServiceInstanceTableForMemberAndProviderSortedPastWeek(int member_id, int provider_id, String sortField, boolean ascending) {
		String query;
		if (ascending == true) {
			query = "SELECT * FROM serviceinstance si WHERE si.member_id=" + member_id + " AND si.provider_id=" + provider_id + " AND " + sortField +  " BETWEEN date('now', '-7 day') AND date('now') ORDER BY " + sortField + ";";
		} else {
			query = "SELECT * FROM serviceinstance si WHERE si.member_id=" + member_id + " AND si.provider_id=" + provider_id + " AND " + sortField +  " BETWEEN date('now', '-7 day') AND date('now') ORDER BY " + sortField + " DESC;";
		}
		this.execute(query);
		return fetchServiceInstanceResults();
	}
	public Vector<ServiceInstance> retrieveServiceInstanceTableForMemberAndProviderSortedTimespan(int member_id, int provider_id, String sortField, boolean ascending, String from, String to) {
		String query;
		if (ascending == true) {
			query = "SELECT * FROM serviceinstance si WHERE si.member_id=" + member_id + " AND si.provider_id=" + provider_id + " AND " + sortField + " BETWEEN '" + from + "' AND '" + to + "' ORDER BY " + sortField + ";";
		} else {
			query = "SELECT * FROM serviceinstance si WHERE si.member_id=" + member_id + " AND si.provider_id=" + provider_id + " AND " + sortField + " BETWEEN '" + from + "' AND '" + to + "' ORDER BY " + sortField + " DESC;";
		}
		this.execute(query);
		return fetchServiceInstanceResults();
	}
	public Vector<ServiceInstance> retrieveServiceInstanceTableForService(int service_id) {
		String query = "SELECT * FROM serviceinstance si WHERE si.service_id=" +
						service_id + ";";
		this.execute(query);
		return fetchServiceInstanceResults();
	}
	public Vector<ServiceInstance> retrieveServiceInstanceTableForServiceSorted(int service_id, String sortField, boolean ascending) {
		String query;
		if (ascending == true) {
			query = "SELECT * FROM serviceinstance si WHERE si.service_id=" + service_id + " ORDER BY " + sortField + ";";
		} else {
			query = "SELECT * FROM serviceinstance si WHERE si.service_id=" + service_id + " ORDER BY " + sortField + " DESC;";
		}
		this.execute(query);
		return fetchServiceInstanceResults();
	}
	public Vector<ServiceInstance> retrieveServiceInstanceTableForProvider(int provider_id) {
		String query = "SELECT * FROM serviceinstance si WHERE si.provider_id=" +
						provider_id + ";";
		this.execute(query);
		return fetchServiceInstanceResults();
	}
	public Vector<ServiceInstance> retrieveServiceInstanceTableForProviderSorted(int provider_id, String sortField, boolean ascending) {
		String query;
		if (ascending == true) {
			query = "SELECT * FROM serviceinstance si WHERE si.provider_id=" + provider_id + " ORDER BY " + sortField + ";";
		} else {
			query = "SELECT * FROM serviceinstance si WHERE si.provider_id=" + provider_id + " ORDER BY " + sortField + " DESC;";
		}
		this.execute(query);
		return fetchServiceInstanceResults();
	}
	public Vector<ServiceInstance> retrieveServiceInstanceTableForProviderSortedPastWeek(int provider_id, String sortField, boolean ascending) {
		String query;
		if (ascending == true) {
			query = "SELECT * FROM serviceinstance si WHERE si.provider_id=" + provider_id + " AND " + sortField + " BETWEEN date('now', '-7 day') AND date('now') ORDER BY " + sortField + ";";
		} else {
			query = "SELECT * FROM serviceinstance si WHERE si.provider_id=" + provider_id + " AND " + sortField + " BETWEEN date('now', '-7 day') AND date('now') ORDER BY " + sortField + " DESC;";
		}
		this.execute(query);
		return fetchServiceInstanceResults();		
	}
	public Vector<ServiceInstance> retrieveServiceInstanceTableForProviderSortedTimespan(int provider_id, String sortField, boolean ascending, String from, String to) {
		String query;
		if (ascending == true) {
			query = "SELECT * FROM serviceinstance si WHERE si.provider_id=" + provider_id + " AND " + sortField + " BETWEEN '" + from + "' AND '" + to + "' ORDER BY " + sortField + ";";
		} else {
			query = "SELECT * FROM serviceinstance si WHERE si.provider_id=" + provider_id + " AND " + sortField + " BETWEEN '" + from + "' AND '" + to + "' ORDER BY " + sortField + " DESC;";
		}
		this.execute(query);
		return fetchServiceInstanceResults();	
	}
	public Vector<ServiceInstance> retrieveServiceInstanceTableForDateProvided(String dateProvided) {
		String query = "SELECT * FROM serviceinstance si WHERE si.date_provided='" +
						dateProvided + "';";
		this.execute(query);
		return fetchServiceInstanceResults();
	}
	public Vector<ServiceInstance> retrieveServiceInstanceTableForDateProvidedSorted(String dateProvided, String sortField, boolean ascending) {
		String query;
		if (ascending) {
			query = "SELECT * FROM serviceinstance si WHERE si.date_provided='" + dateProvided + "' ORDER BY " + sortField + ";";
		} else {
			query = "SELECT * FROM serviceinstance si WHERE si.date_provided='" + dateProvided + "' ORDER BY " + sortField + " DESC;";
		}
		this.execute(query);
		return fetchServiceInstanceResults();
	}
	public Vector<ServiceInstance> retrieveServiceInstanceTableForTimeStamp(String timeStamp) {
		String query = "SELECT * FROM serviceinstance si WHERE si.time_stamp='" + timeStamp + "';";
		this.execute(query);
		return fetchServiceInstanceResults();
	}

	public void addServiceInstance(ServiceInstance s) {
		String query = "INSERT INTO serviceinstance VALUES (null, " +
						s.member_id + ", " +
						s.service_id + ", " +
						s.provider_id + ", '" +
						s.getDate_providedDB() + "', '" +
						s.getTime_stampDB() + "', '" +
						s.comments + "');";
		this.update(query);
	}
	public void updateServiceInstance(ServiceInstance s) {
		String query = "UPDATE serviceinstance SET " +
						"member_id=" + s.member_id + ", " +
						"service_id=" + s.service_id + ", " +
						"provider_id=" +s.provider_id + ", " +
						"date_provided='" + s.getDate_providedDB() + "', " +
						"time_stamp='" +s.getTime_stampDB() + "', " +
						"comments='" +s.comments +
						"' WHERE serviceinstance.instance_id = " +
						s.instance_id + ";";
		this.update(query);
	}
	public void deleteServiceInstance(int instance_id) {
		String query = "DELETE FROM serviceinstance WHERE instance_id = " + instance_id + ";";
		this.update(query);
	}
	
	//~~~~~~~~~~~~~~~~~~~~OPERATIONS OF STATE TABLE~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//	public States retrieveStates() {
//		int i=0;
//		States s = new States();
//		this.execute("SELECT * FROM state;");
//		
//		try {
//			while (rs.next()) {
//				s.abbrev[i] = rs.getString("abbrev");
//				s.name[i] = rs.getString("name");
//				++i;
//			}
//		} catch(Exception e) {
//			System.out.println("Error fetching states: " + e.toString());
//            try
//            {
//                rs.close();
//                stmt.close();
//                con.close();
//            }
//            catch(Exception ex)
//            {
//            }
//		}
//		return s;
//	}
	
	
	
	
	// TEST GARBAGE
	
//	private void testFetchResults() {
//	       try
//	        {
//	            String abbrev, name;
//	            while(rs.next())
//	            {
//	                abbrev = rs.getString("abbrev");
//	                name = rs.getString("name");
//	                System.out.println(abbrev + "  |  " + name);
//	            }
//	        }
//	        catch(Exception e)
//	        {
//	            System.out.println("Error processing results: " + e.toString());
//	            try
//	            {
//	                rs.close();
//	                stmt.close();
//	                con.close();
//	            }
//	            catch(Exception ex)
//	            {
//	            }
//	        }
//	}
//	public void testFetchStates() {
//		this.execute("SELECT * FROM state WHERE state.abbrev='AK';");
//		this.testFetchResults();
//	}

}
