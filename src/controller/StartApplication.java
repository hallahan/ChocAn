package controller;
import model.*;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class StartApplication {
	public static void main(String[] args) {
		setNativeLookAndFeel();
		login();
	}

	private static void login() {
		String id = JOptionPane.showInputDialog(null, "Enter your Provider ID Number:", "Enter ID", JOptionPane.PLAIN_MESSAGE);
		if (id != null)
			System.out.println(id);
		else
			System.out.println("id is null");
		
		JOptionPane.showMessageDialog(null, "24343", "Error retrieving member table:", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void setNativeLookAndFeel() {
		//Set look and feel to native platform.
		//"com.sun.java.swing.plaf.windows.WindowsLookAndFeel"
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			System.out.println("Error setting native look and feel: " + e);
		}
	}
}
