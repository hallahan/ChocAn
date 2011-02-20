package model;

import javax.swing.table.*;
import java.util.Vector;

public class MemberTableModel extends AbstractTableModel implements TableModel {
	private static final long serialVersionUID = 1L;

	public MemberTableModel() {
		db = SQLiteInterface.singleton();
		members = db.retrieveMemberTable();
	}
	
	public int getColumnCount() {
		return 9;
	}

	public int getRowCount() {
		return members.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Member row = members.elementAt(rowIndex);
		switch (columnIndex) {
			case 0: return row.member_id;
			case 1:
				if (row.active_status == 1)
					return "Active";
				else
					return "Inactive";
			case 2: return row.first;
			case 3: return row.middle;
			case 4: return row.last;
			case 5: return row.address;
			case 6: return row.city;
			case 7: return row.state;
			case 8: return row.zip;
			default: return null;
		}
	}

	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}
	
	public String getColumnName(int col) {
		String[] columns = new String [] {
                "ID", "Status", "First", "Middle", "Last", 
                "Address", "City", "State", "Zip"};
		return columns[col];
	}
	
	public void search(String searchKey) {
		members = db.retrieveMemberTable(searchKey);
		fireTableDataChanged();
	}
	
	public Member getMember(int row) {
		return members.elementAt(row);
	}
	private Vector<Member> members;
	public SQLiteInterface db;
}
