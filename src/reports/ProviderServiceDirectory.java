package reports;
import model.*;
import java.util.Vector;
import javax.swing.JOptionPane;

public class ProviderServiceDirectory extends Report{
	//length of a string with the padding added for a cell in text table
	private static final int ID_LEN   = 10;
	private static final int NAME_LEN = 45;
	private static final int FEE_LEN  = 10;
	
	
	private Vector<Service> allServices;
	private Vector<String> lines;
	
	public ProviderServiceDirectory() {
		allServices = db.retrieveServiceTableSorted("name", true);
		lines = new Vector<String>();
		
		createReport();
		JOptionPane.showMessageDialog(null, "The Provider Service Directory has been written \nto the main directory of the application as:\n\n" + fileName);
	}
	
	private void createHeader() {
		lines.add("Provider Services Directory");
		lines.add("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		lines.add(" ");
		lines.add("SERVICE ID  NAME                                           FEE");
		lines.add("----------  ---------------------------------------------  ----------");
	}
	
	private void createTable() {
		String line, id, name, fee;
		for(Service each : allServices) {
			id = stringPadded(String.valueOf(each.service_id), ID_LEN);
			name = stringPadded(each.name, NAME_LEN);
			fee = stringPadded(each.fee, FEE_LEN);
			line = id + " " + name + " " + fee;
			lines.add(line);
		}
	}
	
	private void createReport() {
		createHeader();
		createTable();
		printFile(lines);
	}


	
	public static void main (String[] args) {
		new ProviderServiceDirectory();
	}
}
