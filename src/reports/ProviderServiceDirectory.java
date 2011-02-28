package reports;
import model.*;
import java.util.Vector;
import java.io.*;
import javax.swing.JOptionPane;

public class ProviderServiceDirectory {
	private static final String FILE_NAME_PREFIX = "ProviderServicesDirectory ";
	private static final String FILE_NAME_SUFFIX = ".txt";
	
	private Vector<Service> allServices;
	private SQLiteInterface db;
	
	public ProviderServiceDirectory() {
		db = SQLiteInterface.singleton();
		allServices = db.retrieveServiceTableSorted("name", true);
		String fileName = FILE_NAME_PREFIX + DateAndTime.getCurrentDate() + FILE_NAME_SUFFIX;
		printFile(fileName);
	}
	
	public void printFile(String fileName) {
    	PrintWriter out = null;
    	FileOutputStream fos;
    	OutputStreamWriter osw; 
    	try {
    		fos = new FileOutputStream(fileName, false);
    		osw = new OutputStreamWriter(fos,"UTF-8");
    		out = new PrintWriter(osw, false);	//false so we don't automatically flush
    		
    		out.println("Provider Services Directory");
    		out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    		out.println(" ");
    		out.println("SERVICE ID  NAME                            FEE");
    		out.println("----------  ------------------------------  ----------"); //10 30 10 chars for each field
    		
    		String id, name, fee;
    		String space = " ";
    		String spaces;
    		
    		int pad = 0;
    		
    		for (Service each : allServices) {
    			id = String.valueOf(each.service_id);
    			name = each.name;
    			fee = each.fee;
    			
    			
    			out.println(id + "    " + name + "    " + fee);
    			
    			
    		}
    		
    		out.flush();
    		
    	} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "FileWriter Error: unable to write to file!", "I/O Exception", JOptionPane.ERROR_MESSAGE);
		} finally {
    		if(out != null)
    			out.close();
    	}
    }
	
	public static void main (String[] args) {
		new ProviderServiceDirectory();
	}
}
