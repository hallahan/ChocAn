package controller;

import model.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

public class EditService extends JFrame {
	//model
	private SQLiteInterface db;
	private Service s;
	
	//font to be used in this window
	Font font;
	
	//Labels
	private JLabel nameLabel, feeLabel;
	
	
	//Text Fields
	private JTextField nameTF, feeTF;
	
	//Buttons
	private JButton okButton, cancelButton, deleteButton;
	
	public EditService(Service s) {
		//initialize model
		db = SQLiteInterface.singleton();
		this.s = s;
		
		//get content pane and set layout to null
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		
		//setup font
		Font font = new Font("Helvetica", Font.PLAIN, 14);
		
		//setup labels
		nameLabel = new JLabel();
		nameLabel.setText("Service Name:");
		nameLabel.setBounds(10, 10, 150, 20);
		nameLabel.setFont(font);
		contentPane.add(nameLabel);
		
		feeLabel = new JLabel();
		feeLabel.setText("Service Fee:");
		feeLabel.setBounds(10, 60, 150, 20);
		feeLabel.setFont(font);
		contentPane.add(feeLabel);
		
//		dollarLabel = new JLabel();
//		dollarLabel.setText("$");
//		dollarLabel.setBounds(10, 80, 10, 25);
//		dollarLabel.setFont(font);
//		contentPane.add(dollarLabel);

		
		//setup text fields
		nameTF = new JTextField();
		nameTF.setText(s.name);
		nameTF.setBounds(20,30,375,25);
		contentPane.add(nameTF);
		
		feeTF = new JTextField();
		feeTF.setText(s.fee);
		feeTF.setBounds(20,80,375,25);
		contentPane.add(feeTF);
		
		
		//setup buttons
		cancelButton = new JButton();
		cancelButton.setText("Delete Service");
		cancelButton.setBounds(10,120,130,30);
		contentPane.add(cancelButton);
		cancelButton.addActionListener(
				//anonymous inner class
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						deleteButtonActionPerformed(event);
					}
				}
		
		);
		
		cancelButton = new JButton();
		cancelButton.setText("Cancel");
		cancelButton.setBounds(190,120,100,30);
		contentPane.add(cancelButton);
		cancelButton.addActionListener(
				//anonymous inner class
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						cancelButtonActionPerformed(event);
					}
				}
		
		);
		
		okButton = new JButton();
		okButton.setText("OK");
		okButton.setBounds(290,120,100,30);
		contentPane.add(okButton);
		okButton.addActionListener(
				//anonymous inner class
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						okButtonActionPerformed(event);
					}
				}
		
		);
		getRootPane().setDefaultButton(okButton);
		
		//set properties of AddService window
		setTitle("Edit Service");
		setSize(405,180);
		setVisible(true);
		setResizable(false);
	}

	private void okButtonActionPerformed(ActionEvent event) {
		s.name = nameTF.getText();
		s.setFee(feeTF.getText());
		
		s.print();
		db.updateService(s);
	
		if (Application.windows().addOrEditServiceInstance != null) {
			Application.windows().addOrEditServiceInstance.updateList();
		}
		
		this.dispose();
	}
	
	private void cancelButtonActionPerformed(ActionEvent event) {
		this.dispose();
	}
	
	private void deleteButtonActionPerformed(ActionEvent event) {
		db.deleteService(s.service_id);
		
		if (Application.windows().addOrEditServiceInstance != null) {
			Application.windows().addOrEditServiceInstance.updateList();
		}
		
		dispose();
	}
	public static void main(String[] args) {
		Application.setNativeLookAndFeel();
		Service ser = SQLiteInterface.singleton().retrieveService(2);
		EditService addService = new EditService(ser);
		addService.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}