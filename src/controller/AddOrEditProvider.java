package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.*;
import java.util.Vector;


public class AddOrEditProvider extends JFrame {
	//constructor to add a new provider
    public AddOrEditProvider() {
    	this(new Provider());
    	addProvider = true;
    }
    //constructor to edit a provider
    public AddOrEditProvider(Provider p) {
    	Application.windows().addOrEditProvider = this;
    	this.p = p;
    	pts = SQLiteInterface.singleton().retrieveProviderTypeTableSorted("name", true);
    	initComponents(p.name, p.address, p.city, p.zip, States.full, pts);
    
    	//create button's action listeners...
    	jButton1.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					okButtonPressed(event);
				}
			}
    	);
    	jButton2.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					cancelButtonPressed(event);
				}
			}
        );
    	jButton3.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					editProviderTypesButtonPressed(event);
				}
			}
    	);
    	addProviderType.addActionListener(
    			new ActionListener() {
    				public void actionPerformed(ActionEvent event) {
    					addProviderTypeButtonPressed(event);
    				}
    			}
        	);
    	
    	if (p.providertype_id != 0) {
    		jComboBox2.setSelectedItem(SQLiteInterface.singleton().retrieveProviderType(p.providertype_id));
    	}
    	getRootPane().setDefaultButton(jButton1);
//    	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    
    private void initComponents(String tf1, String tf2, String tf3, String tf4, String[]cb1, Vector<ProviderType> cb2) {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox(States.full);
        jTextField4 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        addProviderType = new javax.swing.JButton();

//        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Provider Name:");

        jLabel2.setText("Address:");

        jLabel3.setText("City:");

        jLabel4.setText("State:");

        jLabel5.setText("Zip:");

        jLabel6.setText("Provider Type:");

        jTextField2.setText(tf2);

        jTextField3.setText(tf3);

        jTextField1.setText(tf1);

        jTextField4.setText(tf4);
        
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(cb1));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(cb2));

        jButton1.setText("OK");

        jButton2.setText("Cancel");

        jButton3.setText("Edit Provider Types");
        addProviderType.setText("Add Provider Type");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                        .addGap(322, 322, 322))
                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                        .addGap(353, 353, 353))
                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                        .addGap(366, 366, 366))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                        .addGap(373, 373, 373))
                    .addComponent(jComboBox1, 0, 473, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                        .addGap(378, 378, 378))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                        .addGap(325, 325, 325))
                    .addComponent(jComboBox2, 0, 473, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    	.addComponent(addProviderType, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
//                        .addGap(135, 135, 135)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(4, 4, 4)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addProviderType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jComboBox1, jComboBox2, jTextField1, jTextField2, jTextField3, jTextField4});

        pack();
    }


    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new AddOrEditProvider();
//            }
//        });
    	Application.setNimbusLookAndFeel();
    	AddOrEditProvider aep = new AddOrEditProvider();
        aep.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void okButtonPressed(ActionEvent e) {
    	p.name = jTextField1.getText();
		p.address = jTextField2.getText();
		p.city = jTextField3.getText();
		ProviderType pt = (ProviderType)jComboBox2.getSelectedItem();
		p.providertype_id = pt.providertype_id;
		int stateIndex = jComboBox1.getSelectedIndex();
		p.state = States.abbrev[stateIndex];
		
		if (p.setZip(jTextField4.getText()) == false) {
			JOptionPane.showMessageDialog(null, "The zip code must be properly formatted.  If 5 characters, it must be all numeric.  If 10 characters, it must be all numeric and the 6th character must be '-'.", "Error setting zip code: ", JOptionPane.ERROR_MESSAGE);
		} else {
	    	if (addProvider == true) {
	    		SQLiteInterface.singleton().addProvider(p);
	    	} else {
	    		SQLiteInterface.singleton().updateProvider(p);
	    	}
	    	this.dispose();
		}
		
		if (Application.windows().providerInformation != null) {
			Application.windows().providerInformation.updateProviderInformation(p);
		}
		if (Application.windows().providerSearch != null) {
			Application.windows().providerSearch.updateWindow();
		}

    }
    public void cancelButtonPressed(ActionEvent e) {
    	this.dispose();
    }
    public void editProviderTypesButtonPressed(ActionEvent e) {
    	ProviderType pt = (ProviderType)jComboBox2.getSelectedItem();
    	if (pt != null)
    		new EditProviderType(pt);
    	else {
    		JOptionPane.showMessageDialog(null, "You must first select a Provider Type from the Combo Box in order to edit a Provider Type!", "Error editing Provider Type: ", JOptionPane.ERROR_MESSAGE);
    	}
    }
    public void addProviderTypeButtonPressed(ActionEvent event) {
    	new AddProviderType();
    }
    
    public void updateProviderTypesBox() {
    	pts = SQLiteInterface.singleton().retrieveProviderTypeTableSorted("name", true);
    	jComboBox2.setModel(new DefaultComboBoxModel(pts));
    }
    
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton addProviderType;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    
    private boolean addProvider = false;
    private Provider p;
    private Vector<ProviderType> pts;
}
