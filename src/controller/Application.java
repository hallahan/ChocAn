package controller;
import model.*;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.*;

public class Application {
	
	public static void main(String[] args) {
		setNimbusLookAndFeel();
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
	
	public static void setNimbusLookAndFeel() {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch(Exception e) {
			System.out.println("Error setting Numbus Look and Feel: " + e);
			setNativeLookAndFeel();
		}
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
	
	//singleton instance variable for the application windows
	private static Application windows;
	
	public static Application windows() {
		if (windows == null) {
			windows = new Application();
		}
		return windows;
	}
	
	public MemberSearch memberSearch = null;
}
