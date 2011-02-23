package controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JMenuBar implements ActionListener {
	public Menu() {
		super();
		Application.windows().menu = this;
		setup();
	}

	private void setup() {
		JMenu 		search, add, logout;
		JMenuItem 	ms, ps, am, ap, apt, as, ll;
		
		search	= new JMenu("Search");
		add		= new JMenu("Add");
		logout	= new JMenu("Logout");
		
		add(search);
		add(add);
		add(logout);
		
		ms 	= new JMenuItem("Member Search");
		ps 	= new JMenuItem("Provider Search");
		am 	= new JMenuItem("Add Member");
		ap 	= new JMenuItem("Add Provider");
		apt = new JMenuItem("Add Provider Type");
		as 	= new JMenuItem("Add Service");
		ll 	= new JMenuItem("Logout / Login");
		
		ms.addActionListener(this);
		ps.addActionListener(this);
		am.addActionListener(this);
		ap.addActionListener(this);
		apt.addActionListener(this);
		as.addActionListener(this);
		ll.addActionListener(this);
		
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
		}
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
