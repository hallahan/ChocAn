package controller;
import model.*;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.*;

public class Application {
	private static final String PASSWORD = "12steps";
	private static final int MANAGER_PROVIDER_ID = 3;
	
	public static void main(String[] args) {
		setNimbusLookAndFeel();
		login();
	}

	private static void login() {
		new Login();
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
	
	//The singleton instance of this class provides access to the master
	//fields pointing to the application's windows.
	public static Application windows() {
		if (windows == null) {
			windows = new Application();
		}
		return windows;
	}
	

	public static boolean isManagerMode() {
		return managerMode;
	}

	public static boolean setManagerMode(String pass) {
		if (pass.equals(PASSWORD)) {
			managerMode = true;
			appOperatorProviderId = MANAGER_PROVIDER_ID;
			return true;
		}
		else
			return false;
	}
	
	//singleton instance variable for the application windows
	private static Application windows;
	
	// class fields
	private static boolean managerMode = false;
	public static int appOperatorProviderId=0;
	public static int selectedMemberId=0;
	
	
	
	public static int getAppOperatorProviderId() {
		return appOperatorProviderId;
	}

	// application windows
	public MemberSearch memberSearch = null;
	public ProviderSearch providerSearch = null;
	public MemberInformation memberInformation = null;
	public ProviderInformation providerInformation = null;
	public AddOrEditServiceInstance addOrEditServiceInstance = null;
}
