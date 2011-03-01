package reports;
import model.*;
import java.util.Vector;
import java.io.*;
import javax.swing.JOptionPane;

public class ProviderServiceDirectory {
	private static final String FILE_NAME_PREFIX = "ProviderServicesDirectory";
	private static final String FILE_NAME_SUFFIX = ".txt";
	
	private Vector<Service> allServices;
	private SQLiteInterface db;
	
	public ProviderServiceDirectory() {
		db = SQLiteInterface.singleton();
		allServices = db.retrieveServiceTableSorted("name", true);
		String fileName = FILE_NAME_PREFIX + DateAndTime.getCurrentDate() + FILE_NAME_SUFFIX;
		printFile(fileName);
		JOptionPane.showMessageDialog(null, "The Provider Service Directory has been written \nto the main directory of the application as:\n\n" + fileName);
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
    		out.println("SERVICE ID  NAME                                       FEE");
    		out.println("----------  ----------------------------------------  ----------"); //10 40 10 chars for each field
    		
    		String id, name, fee;
    		String space = " ";
    		String spaces;
    		
    		int pad = 0;
    		
    		for (Service each : allServices) {
    			id = stringPadded(String.valueOf(each.service_id), 10);
    			name = stringPadded(each.name, 40);
    			fee = stringPadded(each.fee, 10);
    			
    			out.println(id + " " + name + " " + fee); 
    		}
    		
    		out.flush();
    		
    	} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "FileWriter Error: unable to write to file!", "I/O Exception", JOptionPane.ERROR_MESSAGE);
		} finally {
    		if(out != null)
    			out.close();
    	}
    }
	
	private static String stringPadded(String str, int totalLength) {
		int len = str.length();
		if (len >= totalLength) {
			return str;
		}
		
		int padLen = totalLength - len;
		String pad = "";
		for (int i=0; i <= padLen; ++i) {
			pad += " ";
		}
		
		return str + pad;
	}
	
	public static void main (String[] args) {
		new ProviderServiceDirectory();
	}
}
