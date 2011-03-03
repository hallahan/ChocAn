package reports;
import model.*;
import java.util.Vector;
import javax.swing.JOptionPane;

public class MemberReport extends Report {
	//char width of table columns
	private static final int ID_LEN 		= 11;
	private static final int TIMESTAMP_LEN 	= 19;
	private static final int DATE_LEN 		= 13;
	private static final int PROV_LEN		= 45;
	private static final int SERV_LEN		= 45;
	private static final int FEE_LEN		= 10;
	
	private Member m;
	private ServiceInstanceTableModel t;
	private Vector<String> lines;
	
	
	public MemberReport(Member m, ServiceInstanceTableModel t) {
		this.m = m;
		this.t = t;
		lines = new Vector<String>();
		
		createReport();
		JOptionPane.showMessageDialog(null, "The Member Report has been written \nto the main directory of the application as:\n\n" + fileName);
	}
	
	private void createHeader() {
		
	}
	private void createTable() {
		
	}
	private void createReport() {
		createHeader();
		createTable();
		printFile(lines);
	}
}
