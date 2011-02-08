package controller;

import javax.swing.*;
import javax.swing.table.*;

import java.awt.event.*;
import java.awt.*;

/* This is contained in the imported jar "swing-layout-1.0.4.jar"
 * Be sure to add this to your build path. 
 */
import org.jdesktop.layout.GroupLayout;
import org.jdesktop.layout.LayoutStyle;

public class MemberSearch extends javax.swing.JFrame {

    public MemberSearch() {
        initComponents();
    }

    private void initComponents() {

        memberSearchLabel 			= new JLabel();
        memberSearchTextField 		= new JTextField();
        memberSearchButton 			= new JButton();
        memberSearchTableScrollPane = new JScrollPane();
        memberSearchTable 			= new JTable();
        cancelButton 				= new JButton();
        selectButton 				= new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        memberSearchLabel.setText("Member Search:");

        memberSearchTextField.setText("Enter Member ID, First, Middle, Last Name, or Address...");
        memberSearchTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                memberSearchTextFieldActionPerformed(evt);
            }
        });

        memberSearchButton.setText("Search");

        memberSearchTable.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Status", "First", "Middle", "Last", "Address", "City", "State", "Zip"
            }
        ));
        memberSearchTableScrollPane.setViewportView(memberSearchTable);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        selectButton.setText("Select");
        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                selectButtonActionPerformed(evt);
            }
        });
        getRootPane().setDefaultButton(selectButton);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.LEADING)
            .add(GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(GroupLayout.TRAILING)
                    .add(GroupLayout.LEADING, memberSearchTableScrollPane, GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE)
                    .add(GroupLayout.LEADING, layout.createSequentialGroup()
                        .add(memberSearchLabel)
                        .addPreferredGap(LayoutStyle.RELATED)
                        .add(memberSearchTextField, GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.RELATED)
                        .add(memberSearchButton))
                    .add(layout.createSequentialGroup()
                        .add(cancelButton, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.UNRELATED)
                        .add(selectButton)))
                .addContainerGap())
        );

        layout.linkSize(new Component[] {cancelButton, selectButton}, GroupLayout.HORIZONTAL);

        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(GroupLayout.BASELINE)
                    .add(memberSearchLabel)
                    .add(memberSearchTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .add(memberSearchButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(memberSearchTableScrollPane, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                .add(18, 18, 18)
                .add(layout.createParallelGroup(GroupLayout.BASELINE)
                    .add(selectButton)
                    .add(cancelButton))
                .addContainerGap())
        );

        pack();
    }

    private void memberSearchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
        String searchKey = memberSearchTextField.getText();
        
    }

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void selectButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MemberSearch().setVisible(true);
            }
        });
    }


    private JButton cancelButton;
    private JButton memberSearchButton;
    private JLabel memberSearchLabel;
    private JTable memberSearchTable;
    private JScrollPane memberSearchTableScrollPane;
    private JTextField memberSearchTextField;
    private JButton selectButton;

}
