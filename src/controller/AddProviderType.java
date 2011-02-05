package controller;

import model.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

public class AddProviderType extends JFrame {
	//model
	private SQLiteInterface db;
	private ProviderType pt;
	
	//font to be used in this window
	Font font;
	
	//Labels
	private JLabel nameLabel, descriptionLabel;
	
	//Text Fields
	private JTextField nameTF, descriptionTF;
	
	//Buttons
	private JButton okButton, cancelButton;
	
	public AddProviderType() {
		//initialize model
		db = SQLiteInterface.singleton();
		pt = new ProviderType();
		
		//get content pane and set layout to null
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		
		//setup font
		Font font = new Font("Helvetica", Font.PLAIN, 14);
		
		//setup labels
		nameLabel = new JLabel();
		nameLabel.setText("Provider Type:");
		nameLabel.setBounds(10, 10, 150, 20);
		nameLabel.setFont(font);
		contentPane.add(nameLabel);
		
		descriptionLabel = new JLabel();
		descriptionLabel.setText("Description:");
		descriptionLabel.setBounds(10, 60, 150, 20);
		descriptionLabel.setFont(font);
		contentPane.add(descriptionLabel);

		
		//setup text fields
		nameTF = new JTextField();
		nameTF.setBounds(5,30,375,25);
		contentPane.add(nameTF);
		
		descriptionTF = new JTextField();
		descriptionTF.setBounds(5,80,375,25);
		contentPane.add(descriptionTF);
		
		
		//setup buttons		
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
		
		setTitle("Add Provider Type");
		setSize(400,180);
		setVisible(true);
		setResizable(false);
	}

	private void okButtonActionPerformed(ActionEvent event) {
		pt.name = nameTF.getText();
		pt.desc = descriptionTF.getText();
		
		pt.print();
		db.addProviderType(pt);
	
		this.dispose();
	}
	
	private void cancelButtonActionPerformed(ActionEvent event) {
		this.dispose();
	}
	
	public static void main(String[] args) {
		AddProviderType providerType = new AddProviderType();
		providerType.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}