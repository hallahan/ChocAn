package model;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

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
    	boolean valid = dateInputCheck(input);
    	if (valid == false) {
    		javax.swing.JOptionPane.showMessageDialog(null, "BOGUS DATE INPUT: You must format your input date as either:\n\tMM-dd-yyyy\n\tMMddyyyy\n\tMM/dd/yyyy");
    		return null;
    	}
    	
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
//			System.out.println(df.format(date));
			return df.format(date);
		}
		JOptionPane.showMessageDialog(null, "Improper date formatting (XX-XX-XXXX)");
    	return null;
    }
    
    private static boolean dateInputCheck(String in) {
    	int len = in.length();
    	
    	if (len != 10) {
    		if (len !=8)
    			return false;
    	}
    	
    	if ( (len==10) && (in.charAt(2) != in.charAt(5)) ) return false;
    	
    	String month, day, year;
    	int m, d;
    	
    	if (len==8) {
    		month = in.substring(0,2);
    		day   = in.substring(2,4);
    		year  = in.substring(4,8);
    	} else if (len==10) {
    		month = in.substring(0,2);
    		day   = in.substring(3,5);
    		year  = in.substring(6,10);
    	} else {
    		return false;
    	}
    	
    	try {
			m = Integer.valueOf(month);
			d = Integer.valueOf(day);
			Integer.valueOf(year);
		} catch (Exception e) {
			return false;
		}
		
		if (m > 12 || m < 1) return false;
		if (d > 31 || d < 1) return false;
		
    	return true;
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
//		System.out.println(getCurrentDate());
//		System.out.println(getCurrentTimestamp());
//		System.out.println(formatDate("07/04/1986"));
//		System.out.println(formatDate("07041986"));
//		System.out.println(formatTimestamp("07041986 121212"));
		
		testDate();
	}
	
	public static void testDate() {
		String bogus, badMonth, badDate, badYear, badSeparator;
		String good1, good2, good3;
		String bogusR, badMonthR, badDateR, badYearR, badSepR;
		String good1R, good2R, good3R;
		
		bogus		= "sadf";
		badMonth	= "13-12-2011";
		badDate		= "07-44-2011";
		badYear		= "07-04-E089";
		badSeparator= "07-04/2011";
		good1		= "07-04-2011";
		good2		= "12-12-1956";
		good3		= "01-23-2020";
		
//		bogusR		= formatDate(bogus);
//		badMonthR	= formatDate(badMonth);
//		badDateR	= formatDate(badDate);
//		badYearR	= formatDate(badYear);
//		badSepR		= formatDate(badSeparator);
		good1R		= formatDate(good1);
		good2R		= formatDate(good2);
		good3R		= formatDate(good3);
			
//		System.out.println(bogusR);
//		System.out.println(badMonthR);
//		System.out.println(badDateR);
//		System.out.println(badYearR);
//		System.out.println(badSepR);
		System.out.println(good1R);
		System.out.println(good2R);
		System.out.println(good3R);
	}
}
