package model;

import javax.swing.JOptionPane;
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
			mode = Mode.ENTIRE_HISTORY;
			allProvidersChecked = true;
			serviceInstances = db.retrieveServiceInstanceTableForMemberSorted(id, "date_provided", true);
		} else {
			serviceInstances = db.retrieveServiceInstanceTableForProviderSorted(id, "time_stamp", true);
		}
		
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
				case 3: 
					if (p == null) 	return "";
					return p.name;
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
				case 3: 
					if (m == null) return "";
					return m.first;
				case 4: 
					if (m == null) return "";
					return m.last;
				case 5: return ser.name;
				case 6: return ser.fee;
				default: return null;
			}
		}
	}

	public ServiceInstance getRow(int row) {
		if (serviceInstances == null || serviceInstances.size() <= row || row < 0){
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
			if (allProvidersChecked) {
				serviceInstances = db.retrieveServiceInstanceTableForMemberSortedPastWeek(id, "date_provided", true);
			} else {
				serviceInstances = db.retrieveServiceInstanceTableForMemberAndProviderSortedPastWeek(id, Application.appOperatorProviderId, "date_provided", true);
			}
			
		} else {
			serviceInstances = db.retrieveServiceInstanceTableForProviderSortedPastWeek(id, "time_stamp", true);
		}
		mode = Mode.PAST_WEEK;
		fireTableDataChanged();
	}
	public void timespan(String f, String t) {
		from = f;
		to = t;
		if (forMember == true) {
			if (allProvidersChecked) {
				serviceInstances = db.retrieveServiceInstanceTableForMemberSortedTimespan(id, "date_provided", true, from, to);
			} else {
				serviceInstances = db.retrieveServiceInstanceTableForMemberAndProviderSortedTimespan(id, Application.appOperatorProviderId, "date_provided", true, from, to);
			}
			
		} else {
			from += " 00:00:00";
			to   += " 23:59:59";
			serviceInstances = db.retrieveServiceInstanceTableForProviderSortedTimespan(id, "time_stamp", true, from, to);
		}
		if (serviceInstances == null) {
			JOptionPane.showMessageDialog(null, "No matching Service Instances for the time frame:\n" + from + " - " + to);
		} else {
			mode = Mode.TIMESPAN;
			fireTableDataChanged();
		}
	}
	
	public void entireHistory() {
		if (forMember == true) {
			if (allProvidersChecked) {
				serviceInstances = db.retrieveServiceInstanceTableForMemberSorted(id, "date_provided", true);
			} else {
				serviceInstances = db.retrieveServiceInstanceTableForMemberAndProviderSorted(id, Application.appOperatorProviderId, "date_provided", true);
			}
			
		} else {
			serviceInstances = db.retrieveServiceInstanceTableForProviderSorted(id, "time_stamp", true);
		}
		mode = Mode.ENTIRE_HISTORY;
		fireTableDataChanged();
	}
	
	

	public boolean isAllProvidersChecked() {
		return allProvidersChecked;
	}

	public void setAllProvidersChecked(boolean selected) {
		allProvidersChecked = selected;

		if (mode == Mode.TIMESPAN) {
			timespan(from, to);
		} else if (mode == Mode.PAST_WEEK) {
			pastWeek();
		} else {
			entireHistory();
		}
		
	}



	private boolean forMember;  //true if it is Member Information, false if it is for Provider Information
	private Vector<ServiceInstance> serviceInstances;
	private int id;
	private boolean allProvidersChecked;
	String from, to;
	public SQLiteInterface db;
	
	enum Mode {TIMESPAN, PAST_WEEK, ENTIRE_HISTORY};
	Mode mode;
}
