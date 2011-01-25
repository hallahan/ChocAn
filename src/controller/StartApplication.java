package controller;
import model.*;
import javax.swing.JOptionPane;

public class StartApplication {
	public static void main(String[] args) {
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
}
