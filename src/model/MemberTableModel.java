package model;

import javax.swing.table.*;
import java.util.Vector;

public class MemberTableModel extends AbstractTableModel implements TableModel {

	private static final long serialVersionUID = 1L;

	public int getColumnCount() {
		return 9;
	}

	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}

	public Vector<Member> members;
}
