package reports;
import model.*;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Vector;
import javax.swing.JOptionPane;

public class ProviderReport extends Report {
	//char width of table columns
	private static final int ID_LEN 		= 11;
	private static final int TIMESTAMP_LEN 	= 21;
	private static final int DATE_LEN 		= 13;
	private static final int FIRST_LEN		= 25;
	private static final int LAST_LEN		= 25;
	private static final int SERV_LEN		= 45;
	private static final int FEE_LEN		= 10;
	
	private static int len(int col) {
		switch (col) {
			case 0: return ID_LEN;
			case 1: return TIMESTAMP_LEN;
			case 2: return DATE_LEN;
			case 3: return FIRST_LEN;
			case 4: return LAST_LEN;
			case 5: return SERV_LEN;
			case 6: return FEE_LEN;
			default: return -1;
		}
	}
	
	private Provider p;
	private ServiceInstanceTableModel t;
	private Vector<String> lines;
	private int c;	//number of columns
	private int r;	//number of rows
	
	
	public ProviderReport(Provider p, ServiceInstanceTableModel t) {
		super(p.name);
		this.p = p;
		this.t = t;
		lines = new Vector<String>();
		c = t.getColumnCount();
		r = t.getRowCount();
		
		createReport();
		JOptionPane.showMessageDialog(null, "The Provider Report has been written \nto the main directory of the application as:\n\n" + fileName);
	}
	
	private void createHeader() {
		lines.add("Provider Service Report");
		lines.add("~~~~~~~~~~~~~~~~~~~~~");
		lines.add("Provider ID: " + String.valueOf(p.provider_id));
		lines.add(" ");
		
		lines.add(p.name);
		lines.add(p.address);
		lines.add(p.city + ", " + p.state + " " + p.zip);
		lines.add(" ");
		
		int colNum = t.getColumnCount();
		String colLine = "";
		for (int i=0; i < colNum; ++i) {
			colLine += stringPadded(t.getColumnName(i), len(i));
		}
		
		String colDash = "";
		int nameLen = 0;
		for (int j=0; j < colNum; ++j) {
			for (int k=0; k < len(j); ++k) {
				colDash += "-";
			}
			colDash += " ";
		}
		
		lines.add(colLine);
		lines.add(colDash);
	}
	private void createTable() {
		for (int i=0; i < r; ++i) {
			String line = "";
			for (int j=0; j < c; ++j) {
				Object val = t.getValueAt(i, j);
				String cellVal = String.valueOf(val);
				line += stringPadded(cellVal, len(j));
			}
			lines.add(line);
		}
	}
	
	private void computeTotalFee() {
		double total = 0;
		for (int i=0; i < r; ++i) {
			String str = (String)t.getValueAt(i, 6);
			NumberFormat nf = NumberFormat.getCurrencyInstance(java.util.Locale.US);
			Number n = null;
			try {
				n = nf.parse(str);
			} catch (ParseException e) {
				System.out.println("unable to parse currency");
				e.printStackTrace();
			}
			if (n != null) {
				double d = n.doubleValue();
				total += d;
			}
		}
		
		NumberFormat nf =  NumberFormat.getCurrencyInstance(java.util.Locale.US);
		String totalFees = nf.format(total);
		lines.add(" ");
		lines.add("Total Fees: " + totalFees);
	}
	
	private void createReport() {
		createHeader();
		createTable();
		computeTotalFee();
		printFile(lines);
	}
	
	
}

