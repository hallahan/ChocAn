package controller;

import model.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;


public class EditMember extends JFrame {
	//model
	private SQLiteInterface db;
	private Member m;
	
	//font to be used in this window
	Font font;
	
	//Labels
	private JLabel firstLabel, middleLabel, lastLabel, addressLabel, 
					cityLabel, stateLabel, zipLabel, activeStatusLabel;
	
	//Text Fields
	private JTextField firstTF, middleTF, lastTF, addressTF, cityTF, zipTF;
	
	//combo box
	private JComboBox stateCB;
	
	//Buttons
	private JButton okButton, cancelButton;
	
	//Radio Buttons
	private JRadioButton activeRB, inactiveRB;

	
	public EditMember(Member m) {
		//initialize model
		db = SQLiteInterface.singleton();
		this.m = m;
		
		//get content pane and set layout to null
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		
		//setup font
		Font font = new Font("Helvetica", Font.PLAIN, 14);
		
		//setup labels
		firstLabel = new JLabel();
		firstLabel.setText("First Name:");
		firstLabel.setBounds(10, 10, 150, 20);
		firstLabel.setFont(font);
		contentPane.add(firstLabel);
		
		middleLabel = new JLabel();
		middleLabel.setText("Middle Name:");
		middleLabel.setBounds(10, 60, 150, 20);
		middleLabel.setFont(font);
		contentPane.add(middleLabel);
		
		lastLabel = new JLabel();
		lastLabel.setText("Last Name:");
		lastLabel.setBounds(10, 110, 150, 20);
		lastLabel.setFont(font);
		contentPane.add(lastLabel);
		
		addressLabel = new JLabel();
		addressLabel.setText("Address:");
		addressLabel.setBounds(10, 160, 150, 20);
		addressLabel.setFont(font);
		contentPane.add(addressLabel);
		
		cityLabel = new JLabel();
		cityLabel.setText("City:");
		cityLabel.setBounds(10, 210, 150, 20);
		cityLabel.setFont(font);
		contentPane.add(cityLabel);
		
		stateLabel = new JLabel();
		stateLabel.setText("State:");
		stateLabel.setBounds(10, 260, 150, 20);
		stateLabel.setFont(font);
		contentPane.add(stateLabel);
		
		zipLabel = new JLabel();
		zipLabel.setText("Zip:");
		zipLabel.setBounds(10, 310, 150, 20);
		zipLabel.setFont(font);
		contentPane.add(zipLabel);
		
		activeStatusLabel = new JLabel();
		activeStatusLabel.setText("Member Status:");
		activeStatusLabel.setBounds(10, 360, 150, 20);
		activeStatusLabel.setFont(font);
		contentPane.add(activeStatusLabel);
		
		//setup text fields
		firstTF = new JTextField();
		firstTF.setText(m.first);
		firstTF.setBounds(5,30,390,25);
		contentPane.add(firstTF);
		
		middleTF = new JTextField();
		middleTF.setText(m.middle);
		middleTF.setBounds(5,80,390,25);
		contentPane.add(middleTF);
		
		lastTF = new JTextField();
		lastTF.setText(m.last);
		lastTF.setBounds(5,130,390,25);
		contentPane.add(lastTF);
		
		addressTF = new JTextField();
		addressTF.setText(m.address);
		addressTF.setBounds(5,180,390,25);
		contentPane.add(addressTF);
		
		cityTF = new JTextField();
		cityTF.setText(m.city);
		cityTF.setBounds(5,230,390,25);
		contentPane.add(cityTF);
		
		zipTF = new JTextField();
		zipTF.setText(m.zip);
		zipTF.setBounds(5,330,390,25);
		contentPane.add(zipTF);
		
		//Setup Combo Box
		stateCB = new JComboBox(States.full);
		stateCB.setSelectedIndex(States.getIndex(m.state));
		stateCB.setBounds(5,280,390,25);
		contentPane.add(stateCB);
		stateCB.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					statesCBActionPerformed(event);
				}
			}
		);
		
		//Setup Radio Buttons
		if (m.active_status == 1) {
			activeRB = new JRadioButton("Active", true);
		} else {
			activeRB = new JRadioButton("Active", false);
		}
		activeRB.setBounds(10, 375, 100, 30);
		contentPane.add(activeRB);
		activeRB.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					activeRBActionPerformed(event);
				}
			}
		);
		
		if (m.active_status == 1) {
			inactiveRB = new JRadioButton("Inactive", false);
		} else {
			inactiveRB = new JRadioButton("Inactive", true);
		}
		inactiveRB.setBounds(110, 375, 100, 30);
		contentPane.add(inactiveRB);
		inactiveRB.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					inactiveRBActionPerformed(event);
				}
			}
		);
		
		//Group the radio buttons
		//this makes only one button be ON at once
		ButtonGroup group = new ButtonGroup();
		group.add(activeRB);
		group.add(inactiveRB);
		
		
		//setup buttons		
		cancelButton = new JButton();
		cancelButton.setText("Cancel");
		cancelButton.setBounds(190,420,100,30);
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
		okButton.setBounds(290,420,100,30);
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
	
		
		setTitle("Edit Member");
		setSize(405,485);
		setVisible(true);
		setResizable(false);
	}

	private void statesCBActionPerformed(ActionEvent event) {
		JComboBox cb = (JComboBox)event.getSource();
		int index = cb.getSelectedIndex();
		m.state = States.abbrev[index];
//		System.out.println(nm.state);
	}
	
	private void activeRBActionPerformed(ActionEvent event) {
		m.active_status = 1;
	}
	
	private void inactiveRBActionPerformed(ActionEvent event) {
		m.active_status = 0;
	}
	
	private void okButtonActionPerformed(ActionEvent event) {
		m.first = firstTF.getText();
		m.middle = middleTF.getText();
		m.last = lastTF.getText();
		m.address = addressTF.getText();
		m.city = cityTF.getText();
		if (m.setZip(zipTF.getText()) == false) {
			JOptionPane.showMessageDialog(null, "The zip code must be properly formatted.  If 5 characters, it must be all numeric.  If 10 characters, it must be all numeric and the 6th character must be '-'.", "Error setting zip code: ", JOptionPane.ERROR_MESSAGE);
		} else {
			m.print();
			db.updateMember(m);
			this.dispose();
		}
		// updates the views on other windows that may show this member
		// that is being edited
		if (Application.windows().memberSearch != null)
			Application.windows().memberSearch.updateWindow();
		
		if (Application.windows().memberInformation != null) {
			Application.windows().memberInformation.updateMemberInformation(m);
		}
	}
	
	private void cancelButtonActionPerformed(ActionEvent event) {
		this.dispose();
	}
	
	public static void main(String[] args) {
//		StartApplication.setNativeLookAndFeel();
		Application.setNimbusLookAndFeel();
		Member mem = SQLiteInterface.singleton().retrieveMember(1);
		EditMember addMember = new EditMember(mem);
		addMember.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
