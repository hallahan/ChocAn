package model;

import javax.swing.table.*;

import controller.Application;

import java.util.Vector;

/* 
 * member - 	Instance ID, Billing Timestamp, Date Provided, Service Name, Provider Name, Service Name, Fee
 * provider -   Instance ID, Billing Timestamp, Date Provided, First Name, Last Name, Service Name, Fee
 */
public class ServiceInstanceTableModel extends AbstractTableModel implements TableModel{
	
	public ServiceInstanceTableModel(int id, boolean forMember) {
		this.forMember = forMember;
		db = SQLiteInterface.singleton();
		this.id = id;
		
		if (forMember == true) {
			serviceInstances = db.retrieveServiceInstanceTableForMemberSorted(id, "date_provided", true);
		} else {
			serviceInstances = db.retrieveServiceInstanceTableForProviderSorted(id, "time_stamp", true);
		}
		
	}
	
	//only call this method in MemberInformation
	//DO NOT CALL IN PROVIDER INFORMATION!!!!
	public void allProvidersSelected(boolean selected) {
		
		if (selected == false) {
			serviceInstances = db.retrieveServiceInstanceTableForMemberAndProviderSorted(Application.selectedMemberId, Application.appOperatorProviderId, "date_provided", true);
		} else {
			serviceInstances = db.retrieveServiceInstanceTableForMemberSorted(Application.selectedMemberId, "date_provided", true);
		}
		fireTableDataChanged();
	}
	
	public int getColumnCount() {
		if (forMember == true) {
			return 6;
		} else
			return 7;
	}

	public int getRowCount() {
		if (serviceInstances != null)
			return serviceInstances.size();
		return 0;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		ServiceInstance row = serviceInstances.elementAt(rowIndex);
		Service ser = db.retrieveService(row.service_id);
		
		if (forMember == true) {
			Provider p = db.retrieveProvider(row.provider_id);
			switch(columnIndex) {
				case 0: return row.instance_id;
				case 1: return row.getTime_stamp();
				case 2: return row.getDate_provided();
				case 3: return p.name;
				case 4: return ser.name;
				case 5: return ser.fee;
				default: return null;
			}
			
		} else {
			Member m = db.retrieveMember(row.member_id);
			switch(columnIndex) {
				case 0: return row.instance_id;
				case 1: return row.getTime_stamp();
				case 2: return row.getDate_provided();
				case 3: return m.first;
				case 4: return m.last;
				case 5: return ser.name;
				case 6: return ser.fee;
				default: return null;
			}
			
		}
	}

	public ServiceInstance getRow(int row) {
		
		if (serviceInstances.size() <= row || row < 0){
			return new ServiceInstance();
		} else {
			return serviceInstances.elementAt(row);
		}
		
	}
	
	public String getColumnName(int col) {
		String[] memCols = new String [] {
				"Instance ID", "Billing Timestamp", "Date Provided", 
				"Provider Name", "Service Name", "Fee"   
			};
		
		String[] provCols = new String [] {
				"Instance ID", "Billing Timestamp", "Date Provided", 
				"First Name", "Last Name", "Service Name", "Fee"
            };
		
		if (forMember == true) {
			return memCols[col];
		} else {
			return provCols[col];
		}
	}

	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}
	
	public void pastWeek() {
		if (forMember == true) {
			serviceInstances = db.retrieveServiceInstanceTableForMemberSortedPastWeek(id, "date_provided", true);
		} else {
			serviceInstances = db.retrieveServiceInstanceTableForProviderSortedPastWeek(id, "time_stamp", true);
		}
		fireTableDataChanged();
	}
	
	public void entireHistory() {
		if (forMember == true) {
			serviceInstances = db.retrieveServiceInstanceTableForMemberSorted(id, "date_provided", true);
		} else {
			serviceInstances = db.retrieveServiceInstanceTableForProviderSorted(id, "time_stamp", true);
		}
		fireTableDataChanged();
	}
	
	private boolean forMember;  //true if it is Member Information, false if it is for Provider Information
	private Vector<ServiceInstance> serviceInstances;
	private int id;
	public SQLiteInterface db;
}
