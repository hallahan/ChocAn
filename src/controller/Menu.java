package controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import reports.*;

public class Menu extends JMenuBar implements ActionListener {
	public Menu() {
		super();
		Application.windows().menu = this;
		setup();
	}

	private void setup() {
		JMenu 		file, search, add, logout;
		JMenuItem 	psd, ms, ps, am, ap, apt, as, ll;
		
		file	= new JMenu("File");
		search	= new JMenu("Search");
		add		= new JMenu("Add");
		logout	= new JMenu("Logout");
		
		add(file);
		add(search);
		add(add);
		add(logout);
		
		psd = new JMenuItem("Provider Services Directory");
		ms 	= new JMenuItem("Member Search");
		ps 	= new JMenuItem("Provider Search");
		am 	= new JMenuItem("Add Member");
		ap 	= new JMenuItem("Add Provider");
		apt = new JMenuItem("Add Provider Type");
		as 	= new JMenuItem("Add Service");
		ll 	= new JMenuItem("Logout / Login");
		
		psd.addActionListener(this);
		ms.addActionListener(this);
		ps.addActionListener(this);
		am.addActionListener(this);
		ap.addActionListener(this);
		apt.addActionListener(this);
		as.addActionListener(this);
		ll.addActionListener(this);
		
		file.add(psd);
		search.add(ms);
		search.add(ps);
		add.add(am);
		add.add(ap);
		add.add(apt);
		add.add(as);
		logout.add(ll);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		JMenuItem source = (JMenuItem)e.getSource();
		String itemName = source.getText();
		
		if (itemName.equals("Member Search") == true) {
			memberSearch();
		} else if (itemName.equals("Provider Search") == true) {
			providerSearch();
		} else if (itemName.equals("Add Member") == true) {
			addMember();
		} else if (itemName.equals("Add Provider") == true) {
			addProvider();
		} else if (itemName.equals("Add Provider Type") == true) {
			addProviderType();
		} else if (itemName.equals("Add Service") == true) {
			addService();
		}  else if (itemName.equals("Logout / Login") == true) {
			logoutLogin();
		} else if (itemName.equals("Write Provider Service Directory") == true) {
			providerServiceDirectory();
		}
	}
	
	private void providerServiceDirectory() {
		new ProviderServiceDirectory();
	}
	
	private void memberSearch() {
		Application.windows().closeAllWindows();
		new MemberSearch();
	}
	
	private void providerSearch() {	
		Application.windows().closeAllWindows();
		new ProviderSearch();
	}
	
	private void addMember() {
		new AddMember();
	}
	
	private void addProvider() {
		new AddOrEditProvider();
	}
	
	private void addProviderType() {
		new AddProviderType();
	}
	
	private void addService() {
		new AddService();
	}
	
	private void logoutLogin() {
		Application.windows().closeAllWindows();
		new Login();
	}
}
