package model;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/*	
 * Utility class that handles parsing date/time strings
 * and getting current date/time from the system.
 */

public class DateAndTime {
    
    public static String getCurrentDate() {
    	DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
    	Date date = new Date();
    	return df.format(date);
    }
    
    public static String getCurrentTimestamp() {
    	DateFormat df = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
    	Date date = new Date();
    	return df.format(date);
    }
    
    //insures that the date is stored as a properly formatted string
    public static String formatDate(String input) {
    	Date date = null;
    	DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
    	
    	try {
			date = df.parse(input);
			
		} catch (ParseException e) {
			try {
				DateFormat df2 = new SimpleDateFormat("MMddyyyy");
				date = df2.parse(input);
			} catch (ParseException e1) {
				try {
					DateFormat df3 = new SimpleDateFormat("MM/dd/yyyy");
					date = df3.parse(input);
				} catch (ParseException e2) {
					javax.swing.JOptionPane.showMessageDialog(null, "You must format your input date as either:\n\tMM-dd-yyyy\n\tMMddyyyy\n\tMM/dd/yyyy");
				}
			}
		}
    	
		if (date != null) {
			return df.format(date);
		}
    	return null;
    }
    
    //insures that the timestamp is stored as a properly formatted string
    public static String formatTimestamp(String input) {
    	Date timestamp = null;
    	DateFormat df = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
    	
    	try {
			timestamp = df.parse(input);
			
		} catch (ParseException e) {
			try {
				DateFormat df2 = new SimpleDateFormat("MMddyyyy HHmmss");
				timestamp = df2.parse(input);
			} catch (ParseException e1) {
				try {
					DateFormat df3 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
					timestamp = df3.parse(input);
				} catch (ParseException e2) {
					javax.swing.JOptionPane.showMessageDialog(null, "You must format your input date as either:\n\tMM-dd-yyyy HH:mm:ss\n\tMMddyyyy HHmmss\n\tMM/dd/yyyy HH:mm:ss");
				}
			}
		}
    	
		if (timestamp != null) {
			return df.format(timestamp);
		}
    	return null;
    }
    
	public static void main(String[] args) {
		System.out.println(getCurrentDate());
		System.out.println(getCurrentTimestamp());
		System.out.println(formatDate("07/04/1986"));
		System.out.println(formatDate("07041986"));
		System.out.println(formatTimestamp("07041986 121212"));
	}
}
