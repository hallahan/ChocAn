package reports;
import model.*;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Vector;
import javax.swing.JOptionPane;

public class SummaryReport extends Report {
	//char width of table columns
	private static final int PROV_LEN		= 40;
	private static final int NUM_LEN		= 25;
	private static final int FEE_LEN		= 10;
	
	private Vector<String> lines;
	private Vector<Integer> providerIDs;
	private int totalConsult;
	private double totalFeeEveryone = 0;
	
	public SummaryReport() {
		lines = new Vector<String>();
		
		createReport();
		JOptionPane.showMessageDialog(null, "The Summary Report has been written \nto the main directory of the application as:\n\n" + fileName);
	}
	private void createReport() {
		createHeader();
		createTable();
		createFooter();
		printFile(lines);
	}
	private void createHeader() {
		lines.add("Summary Report");
		lines.add("~~~~~~~~~~~~~~");
		
		String weekLine = "Week of: " + db.weekAgo() + " to " + db.now();
		lines.add(weekLine);
		lines.add(" ");
		lines.add("PROVIDER NAME                            NUMBER OF CONSULTATIONS   TOTAL FEES");
		lines.add("---------------------------------------- ------------------------- ----------");
	}
	private void createTable() {
		providerIDs = db.retrieveProviderIDsForPastWeek();
		for (Integer each : providerIDs) {
			String line = "";
			String name;
			int numConsult = 0;
			double totalFees = 0;
			
			name = db.retrieveProvider(each).name;
			Vector<ServiceInstance> serviceInstancesForProvider = db.retrieveServiceInstanceTableForProviderSortedPastWeek(each, "time_stamp", true);
			numConsult = serviceInstancesForProvider.size();
			totalConsult += numConsult;
			for (ServiceInstance si : serviceInstancesForProvider) {
				Service s = db.retrieveService(si.service_id);
				double fee = currencyStringToDouble(s.fee);
				totalFees += fee;
			}
			totalFeeEveryone += totalFees;
			String totalCurr = doubleToCurrencyString(totalFees);
			line = stringPadded(name, PROV_LEN) + stringPadded(String.valueOf(numConsult), NUM_LEN) + stringPadded(totalCurr, FEE_LEN);
			lines.add(line);
		}
		lines.add(" ");
	}
	private void createFooter() {
		String p = "Total Number of Providers     = ";
		String c = "Total Number of Consultations = ";
		String f = "Overall Fee                   = ";
		
		p += String.valueOf(providerIDs.size());
		c += String.valueOf(totalConsult);
		f += doubleToCurrencyString(totalFeeEveryone);
		lines.add(p);
		lines.add(c);
		lines.add(f);
	}
	
	public static void main(String[] args) {
		new SummaryReport();
	}
	
	public double currencyStringToDouble(String curr) {
		NumberFormat nf = NumberFormat.getCurrencyInstance(java.util.Locale.US);
		Number n = null;
		try {
			n = nf.parse(curr);
		} catch (ParseException e) {
			System.out.println("unable to parse currency");
			e.printStackTrace();
		}
		if (n != null) {
			double d = n.doubleValue();
			return d;
		}
		return 0;
	}
	
	public String doubleToCurrencyString(double d) {
		NumberFormat nf =  NumberFormat.getCurrencyInstance(java.util.Locale.US);
		String totalFees = nf.format(d);
		return totalFees;
	}
}
