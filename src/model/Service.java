package model;

import java.text.*;

import javax.swing.JOptionPane;

public class Service {
	private static String EMPTY = "";
	
	public int	service_id		= 0;
	public String	name		= EMPTY;
	public String	fee			= EMPTY;

	
	public void print() {
		System.out.println("-------SERVICE ROW-------");
		System.out.println(service_id);
		System.out.println(name);
		System.out.println(fee);
	}
	
	/* This insures that the input is saved as a 
	 * properly formatted currency amount according 
	 * to the locale the application is set to.
	 */
	public boolean setFee(String inFee) {
		double d;
		NumberFormat nf;
		
		try {
			if (inFee.charAt(0) == '$') {
				d = Double.parseDouble(inFee.substring(1));
			} else {
				d = Double.parseDouble(inFee);
			}
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "Your service fee must be formatted as a monetary amount (XXX.XX).", "Error setting service fee: ", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		try {
			nf = NumberFormat.getCurrencyInstance(java.util.Locale.US);
			this.fee = nf.format(d);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR FORMATTING INTO US CURRENCY NUMBER FORMAT: Your service fee must be formatted as a monetary amount (XXX.XX).", "Error setting service fee: ", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	public String toString() {
		return (name + " - " + fee) ;
	}
}
