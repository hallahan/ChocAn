package controller;

import javax.swing.JOptionPane;
import model.*;


public class Login extends javax.swing.JFrame {

   
    public Login() {
        initComponents();
        getRootPane().setDefaultButton(okButton);
        setVisible(true);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        providerIDLabel = new javax.swing.JLabel();
        idTextField = new javax.swing.JTextField();
        overrideCheckBox = new javax.swing.JCheckBox();
        passwordLabel = new javax.swing.JLabel();
        passwordTextField = new javax.swing.JPasswordField();
        cancelButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setResizable(false);

        providerIDLabel.setText("Provider ID:");

        overrideCheckBox.setText("Managerial Override");
        overrideCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                overrideCheckBoxActionPerformed(evt);
            }
        });

        passwordLabel.setText("Password:");

        passwordTextField.setEnabled(false);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, idTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, providerIDLabel)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                .add(29, 29, 29)
                                .add(passwordLabel)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(passwordTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE))
                            .add(org.jdesktop.layout.GroupLayout.LEADING, overrideCheckBox))
                        .addContainerGap())
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(cancelButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(okButton)
                        .add(18, 18, 18))))
        );

        layout.linkSize(new java.awt.Component[] {cancelButton, okButton}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(providerIDLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(idTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(overrideCheckBox)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(passwordLabel)
                    .add(passwordTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(okButton)
                    .add(cancelButton))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

    private void overrideCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {
        if (overrideCheckBox.isSelected() == true) {
        	passwordTextField.setEnabled(true);
        } else {
        	passwordTextField.setEnabled(false);
        }
    }

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        
    }

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (passwordTextField.isEnabled() == true) {
        	char[] pass = passwordTextField.getPassword();
        	String password = String.valueOf(pass);
        	boolean success = Application.setManagerMode(password);
        	if (success == false) {
        		JOptionPane.showMessageDialog(null, "Incorrect Password. The application will function in Standard Provider Mode.", "Invalid Password:", JOptionPane.ERROR_MESSAGE);
        	}
        }
    	
        SQLiteInterface db = SQLiteInterface.singleton();
        Provider p;
        try {
        	p = db.retrieveProvider(Integer.valueOf(idTextField.getText()));
			if (p.provider_id == 0) {
				System.out.println("p.provider_id == 0");
				invalidID();
			} else {
				Application.appOperatorProviderId = p.provider_id;
				Application.windows().memberSearch = new MemberSearch();
				dispose();
			}
        } catch (NumberFormatException nfe) {
        	invalidID();
        }
        
    }
    
	private void invalidID() {
		JOptionPane.showMessageDialog(null, "Invalid ID!", "Invalid ID:", JOptionPane.ERROR_MESSAGE);
	}

    public static void main(String args[]) {
    	Application.setNimbusLookAndFeel();
//    	Application.setNativeLookAndFeel();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login();
            }
        });
    }
    

    // Variables declaration - do not modify
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField idTextField;
    private javax.swing.JButton okButton;
    private javax.swing.JCheckBox overrideCheckBox;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JPasswordField passwordTextField;
    private javax.swing.JLabel providerIDLabel;
    // End of variables declaration

}
