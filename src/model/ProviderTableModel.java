package model;

import javax.swing.table.*;

import controller.Application;

import java.util.Vector;

public class ProviderTableModel extends AbstractTableModel implements TableModel {

	public ProviderTableModel() {
		db = SQLiteInterface.singleton();
		providers = db.retrieveProviderTable();
	}
	
	public int getColumnCount() {
		if (Application.isManagerMode()==true)
			return 7;
		return 6;
	}

	public int getRowCount() {
		return providers.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Provider row = providers.elementAt(rowIndex);
		if (Application.isManagerMode()==true) {
			switch (columnIndex) {
				case 0: return row.provider_id;
				case 1: return row.name;
				case 2: 
					ProviderType pt = db.retrieveProviderType(row.providertype_id);
					return pt.name;
//					return row.providertype_id;
				case 3: return row.address;
				case 4: return row.city;
				case 5: return row.state;
				case 6: return row.zip;
				default: return null;
			}
		} else {
			switch (columnIndex) {
				case 0: return row.name;
				case 1: return db.retrieveProviderType(row.providertype_id).name;
				case 2: return row.address;
				case 3: return row.city;
				case 4: return row.state;
				case 5: return row.zip;
				default: return null;
			}
		}
	}

	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}
	
	public String getColumnName(int col) {
		String[] columns = new String [] {
                "Provider ID", "Name", "Provider Type", "Address", 
                "City", "State", "Zip"
        };
		if (Application.isManagerMode() == true)
			return columns[col];
		return columns[col+1]; //If we are not in manager mode, we don't want to show the provider id to any unprivileged provider...
	}
	
	public void search(String searchKey) {
		providers = db.retrieveProviderTable(searchKey);
	}
	
	private Vector<Provider> providers;
	public SQLiteInterface db;
}
