package reports;
import model.*;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Vector;
import javax.swing.JOptionPane;

public class MemberReport extends Report {
	//char width of table columns
	private static final int ID_LEN 		= 11;
	private static final int TIMESTAMP_LEN 	= 21;
	private static final int DATE_LEN 		= 13;
	private static final int PROV_LEN		= 45;
	private static final int SERV_LEN		= 45;
	private static final int FEE_LEN		= 10;
	
	private static int len(int col) {
		switch (col) {
			case 0: return ID_LEN;
			case 1: return TIMESTAMP_LEN;
			case 2: return DATE_LEN;
			case 3: return PROV_LEN;
			case 4: return SERV_LEN;
			case 5: return FEE_LEN;
			default: return -1;
		}
	}
	
	private Member m;
	private ServiceInstanceTableModel t;
	private Vector<String> lines;
	private int c;	//number of columns
	private int r;	//number of rows
	
	
	public MemberReport(Member m, ServiceInstanceTableModel t) {
		super(m.first+m.last);
		this.m = m;
		this.t = t;
		lines = new Vector<String>();
		c = t.getColumnCount();
		r = t.getRowCount();
		
		createReport();
		JOptionPane.showMessageDialog(null, "The Member Report has been written \nto the main directory of the application as:\n\n" + fileName);
	}
	
	private void createHeader() {
		lines.add("Member Service Report");
		lines.add("~~~~~~~~~~~~~~~~~~~~~");
		lines.add("Member ID: " + String.valueOf(m.member_id));
		lines.add(" ");
		
		lines.add(m.first + " " + m.middle + " " + m.last);
		lines.add(m.address);
		lines.add(m.city + ", " + m.state + " " + m.zip);
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
			String str = (String)t.getValueAt(i, 5);
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
