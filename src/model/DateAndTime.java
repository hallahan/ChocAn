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
    
    private static boolean timestampInputCheck(String in) {
    	int len = in.length();
    	
    	if (len != 19) {
    		if (len !=15)
    			return false;
    	}
    	
    	if ( (len==19) && (in.charAt(2) != in.charAt(5)) ) return false;
    	
    	String month, day, year, hour, minute, second;
    	int m, d, h, min, s;
    	
    	if (len==15) {
    		month = in.substring(0,2);
    		day   = in.substring(2,4);
    		year  = in.substring(4,8);
    		
    		hour  = in.substring(9,11);
    		minute= in.substring(11,13);
    		second= in.substring(13,15);
    		
    	} else if (len==19) {
    		month = in.substring(0,2);
    		day   = in.substring(3,5);
    		year  = in.substring(6,10);
    		
    		hour  = in.substring(11,13);
    		minute= in.substring(14,16);
    		second= in.substring(17,19);
    		
    	} else {
    		return false;
    	}
    	
    	try {
			m = Integer.valueOf(month);
			d = Integer.valueOf(day);
			Integer.valueOf(year);
			
			h = Integer.valueOf(hour);
			min = Integer.valueOf(minute);
			s = Integer.valueOf(second);
			
		} catch (Exception e) {
			return false;
		}
		
		if (m > 12 || m < 1) return false;
		if (d > 31 || d < 1) return false;
		if (h > 23 || h < 0) return false;
		if (min > 59 || min < 0) return false;
		if (s > 59 || s < 0) return false;
		
    	return true;    	
    }
    
    //insures that the timestamp is stored as a properly formatted string
    public static String formatTimestamp(String input) {
    	boolean valid =timestampInputCheck(input);
    	if (valid == false) {
    		javax.swing.JOptionPane.showMessageDialog(null, "BOGUS TIMESTAMP: You must format your input date as either:\n\tMM-dd-yyyy HH:mm:ss\n\tMMddyyyy HHmmss\n\tMM/dd/yyyy HH:mm:ss");
    		return null;
    	}
    	
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
		
//		testDate();
		testTimestamp();
	}
	
	public static void testDate() {
		String bogus, badMonth, badDate, badYear, badSeparator;
		String good1, good2, good3, good4;
		String bogusR, badMonthR, badDateR, badYearR, badSepR;
		String good1R, good2R, good3R, good4R;
		
		bogus		= "sadf";
		badMonth	= "13-12-2011";
		badDate		= "07-44-2011";
		badYear		= "07-04-E089";
		badSeparator= "07-04/2011";
		good1		= "07-04-2011";
		good2		= "12-12-1956";
		good3		= "01-23-2020";
		good4		= "12121212";
		
		bogusR		= formatDate(bogus);
		badMonthR	= formatDate(badMonth);
		badDateR	= formatDate(badDate);
		badYearR	= formatDate(badYear);
		badSepR		= formatDate(badSeparator);
		good1R		= formatDate(good1);
		good2R		= formatDate(good2);
		good3R		= formatDate(good3);
		good4R		= formatDate(good4);
			
		System.out.println(bogusR);
		System.out.println(badMonthR);
		System.out.println(badDateR);
		System.out.println(badYearR);
		System.out.println(badSepR);
		System.out.println(good1R);
		System.out.println(good2R);
		System.out.println(good3R);
		System.out.println(good4R);
	}
	
	public static void testTimestamp() {
		String bogus, badMonth, badDate, badYear, badSeparator;
		String good1, good2, good3, good4;
		String bogusR, badMonthR, badDateR, badYearR, badSepR;
		String good1R, good2R, good3R, good4R;
		String bad1, bad2, bad3, bad4;
		String bad1R, bad2R, bad3R, bad4R;
		
		bogus		= "sadf";
		badMonth	= "13-12-2011 12:32:44";
		badDate		= "07-44-2011 12:32:44";
		badYear		= "07-04-E089 12:32:44";
		badSeparator= "07-04/2011 12:32:44";
		good1		= "07-04-2011 12:32:44";
		good2		= "12-12-1956 15:22:11";
		good3		= "01-23-2020 04:04:23";
		good4		= "12121212 142112";
		bad1		= "07-04-2011 12:32:77";
		bad2		= "12-12-1956 25:22:11";
		bad3		= "01-23-2020 04:04-23";
		bad4		= "12121212 1423112";
		
//		bogusR		= formatTimestamp(bogus);
//		badMonthR	= formatTimestamp(badMonth);
//		badDateR	= formatTimestamp(badDate);
//		badYearR	= formatTimestamp(badYear);
//		badSepR		= formatTimestamp(badSeparator);
//		good1R		= formatTimestamp(good1);
//		good2R		= formatTimestamp(good2);
//		good3R		= formatTimestamp(good3);
//		good4R		= formatTimestamp(good4);
		bad1R	= formatTimestamp(bad1);
		bad2R	= formatTimestamp(bad2);
		bad3R	= formatTimestamp(bad3);
		bad4R	= formatTimestamp(bad4);
			
//		System.out.println(bogusR);
//		System.out.println(badMonthR);
//		System.out.println(badDateR);
//		System.out.println(badYearR);
//		System.out.println(badSepR);
//		System.out.println(good1R);
//		System.out.println(good2R);
//		System.out.println(good3R);
//		System.out.println(good4R);
		System.out.println(bad1R);
		System.out.println(bad2R);
		System.out.println(bad3R);
		System.out.println(bad4R);
	}
}
