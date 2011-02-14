/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MemberInformation.java
 *
 * Created on Feb 9, 2011, 6:15:13 PM
 */

package controller;

/**
 *
 * @author montanajack
 */
public class MemberInformation extends javax.swing.JFrame {

    /** Creates new form MemberInformation */
    public MemberInformation() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        reportTimespan = new javax.swing.ButtonGroup();
        memberInformationPanel = new javax.swing.JPanel();
        contactInformationLabel = new javax.swing.JLabel();
        contactInformationScrollPane = new javax.swing.JScrollPane();
        contactInformationTextArea = new javax.swing.JTextArea();
        memberIDLabel = new javax.swing.JLabel();
        memberIDValueLabel = new javax.swing.JLabel();
        memberStatusLabel = new javax.swing.JLabel();
        memberStatusValueLabel = new javax.swing.JLabel();
        changeMemberStatusButton = new javax.swing.JButton();
        deleteMemberButton = new javax.swing.JButton();
        editMemberButton = new javax.swing.JButton();
        serviceInformationPanel = new javax.swing.JPanel();
        instanceIDLabel = new javax.swing.JLabel();
        instanceIDValueLabel = new javax.swing.JLabel();
        serviceLabel = new javax.swing.JLabel();
        serviceValueLabel = new javax.swing.JLabel();
        feeLabel = new javax.swing.JLabel();
        feeValueLabel = new javax.swing.JLabel();
        dateProvidedLabel = new javax.swing.JLabel();
        dateProvidedValueLabel = new javax.swing.JLabel();
        billingTimestampLabel = new javax.swing.JLabel();
        billingTimestampValueLabel = new javax.swing.JLabel();
        commentsLabel = new javax.swing.JLabel();
        commentsScrollPane = new javax.swing.JScrollPane();
        commentsTextArea = new javax.swing.JTextArea();
        deleteServiceButton = new javax.swing.JButton();
        editServiceButton = new javax.swing.JButton();
        serviceHistoryPanel = new javax.swing.JPanel();
        tableScrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        fromRadio = new javax.swing.JRadioButton();
        fromTextField = new javax.swing.JTextField();
        toLabel = new javax.swing.JLabel();
        toTextField = new javax.swing.JTextField();
        pastWeekRadio = new javax.swing.JRadioButton();
        entireHistoryRadio = new javax.swing.JRadioButton();
        allProvidersCheckBox = new javax.swing.JCheckBox();
        viewProviderButton = new javax.swing.JButton();
        generateMemberReportButton = new javax.swing.JButton();
        addServiceInstanceButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        memberInformationPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Member Information", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 2, 13))); // NOI18N

        contactInformationLabel.setText("Contact Information:");

        contactInformationTextArea.setColumns(20);
        contactInformationTextArea.setEditable(false);
        contactInformationTextArea.setRows(5);
        contactInformationTextArea.setText("Nicholas Hallahan\n1802 SW 10th Ave. Apt. 345\nPortland, Oregon 97201");
        contactInformationScrollPane.setViewportView(contactInformationTextArea);

        memberIDLabel.setText("Member ID:");

        memberIDValueLabel.setText("45");

        memberStatusLabel.setText("Member Status:");

        memberStatusValueLabel.setFont(memberStatusValueLabel.getFont().deriveFont(memberStatusValueLabel.getFont().getStyle() | java.awt.Font.BOLD));
        memberStatusValueLabel.setForeground(new java.awt.Color(204, 0, 0));
        memberStatusValueLabel.setText("Inactive");

        changeMemberStatusButton.setText("Activate");
        changeMemberStatusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeMemberStatusButtonActionPerformed(evt);
            }
        });

        deleteMemberButton.setText("Delete");

        editMemberButton.setText("Edit");

        org.jdesktop.layout.GroupLayout memberInformationPanelLayout = new org.jdesktop.layout.GroupLayout(memberInformationPanel);
        memberInformationPanel.setLayout(memberInformationPanelLayout);
        memberInformationPanelLayout.setHorizontalGroup(
            memberInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(memberInformationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(memberInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(contactInformationLabel)
                    .add(memberInformationPanelLayout.createSequentialGroup()
                        .add(24, 24, 24)
                        .add(contactInformationScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE))
                    .add(memberInformationPanelLayout.createSequentialGroup()
                        .add(memberInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(memberIDLabel)
                            .add(memberStatusLabel))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(memberInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(memberIDValueLabel)
                            .add(memberStatusValueLabel)))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, memberInformationPanelLayout.createSequentialGroup()
                        .add(changeMemberStatusButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(deleteMemberButton)
                        .add(5, 5, 5)
                        .add(editMemberButton)))
                .addContainerGap())
        );

        memberInformationPanelLayout.linkSize(new java.awt.Component[] {changeMemberStatusButton, deleteMemberButton, editMemberButton}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        memberInformationPanelLayout.setVerticalGroup(
            memberInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(memberInformationPanelLayout.createSequentialGroup()
                .add(contactInformationLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(contactInformationScrollPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(memberInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(memberIDLabel)
                    .add(memberIDValueLabel))
                .add(8, 8, 8)
                .add(memberInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(memberStatusLabel)
                    .add(memberStatusValueLabel))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 44, Short.MAX_VALUE)
                .add(memberInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(changeMemberStatusButton)
                    .add(editMemberButton)
                    .add(deleteMemberButton)))
        );

        memberInformationPanelLayout.linkSize(new java.awt.Component[] {changeMemberStatusButton, deleteMemberButton, editMemberButton}, org.jdesktop.layout.GroupLayout.VERTICAL);

        serviceInformationPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Service Information", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 2, 13))); // NOI18N

        instanceIDLabel.setText("Instance ID:");

        instanceIDValueLabel.setText("34");

        serviceLabel.setText("Service:");

        serviceValueLabel.setText("Foot Massage");

        feeLabel.setText("Fee:");

        feeValueLabel.setText("$32.56");

        dateProvidedLabel.setText("Date Provided:");

        dateProvidedValueLabel.setText("10/10/2010");

        billingTimestampLabel.setText("Billing Timestamp:");

        billingTimestampValueLabel.setText("01/31/01 6:56:21 PM");

        commentsLabel.setText("Comments:");

        commentsTextArea.setColumns(20);
        commentsTextArea.setEditable(false);
        commentsTextArea.setRows(5);
        commentsScrollPane.setViewportView(commentsTextArea);

        deleteServiceButton.setText("Delete");
        deleteServiceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteServiceButtonActionPerformed(evt);
            }
        });

        editServiceButton.setText("Edit");
        editServiceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editServiceButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout serviceInformationPanelLayout = new org.jdesktop.layout.GroupLayout(serviceInformationPanel);
        serviceInformationPanel.setLayout(serviceInformationPanelLayout);
        serviceInformationPanelLayout.setHorizontalGroup(
            serviceInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, serviceInformationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(serviceInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(serviceInformationPanelLayout.createSequentialGroup()
                        .add(deleteServiceButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(editServiceButton))
                    .add(serviceInformationPanelLayout.createSequentialGroup()
                        .add(serviceInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(dateProvidedLabel)
                            .add(billingTimestampLabel)
                            .add(feeLabel)
                            .add(serviceLabel)
                            .add(instanceIDLabel)
                            .add(commentsLabel))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(serviceInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(instanceIDValueLabel)
                            .add(serviceValueLabel)
                            .add(feeValueLabel)
                            .add(serviceInformationPanelLayout.createSequentialGroup()
                                .add(serviceInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(billingTimestampValueLabel)
                                    .add(dateProvidedValueLabel))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 254, Short.MAX_VALUE))
                            .add(commentsScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE))))
                .addContainerGap())
        );
        serviceInformationPanelLayout.setVerticalGroup(
            serviceInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(serviceInformationPanelLayout.createSequentialGroup()
                .add(serviceInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(instanceIDLabel)
                    .add(instanceIDValueLabel))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(serviceInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(serviceLabel)
                    .add(serviceValueLabel))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(serviceInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(serviceInformationPanelLayout.createSequentialGroup()
                        .add(serviceInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(feeLabel)
                            .add(feeValueLabel))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(dateProvidedValueLabel))
                    .add(dateProvidedLabel))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(serviceInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(billingTimestampValueLabel)
                    .add(billingTimestampLabel))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(serviceInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(commentsScrollPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(commentsLabel))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(serviceInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(editServiceButton)
                    .add(deleteServiceButton)))
        );

        serviceHistoryPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Service History", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 2, 13))); // NOI18N

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Billing Timestamp", "Date Provided", "Service Name", "Provider Name", "Fee"
            }
        ));
        tableScrollPane.setViewportView(table);

        reportTimespan.add(fromRadio);
        fromRadio.setText("From:");

        fromTextField.setText("01/01/2001");
        fromTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fromTextFieldActionPerformed(evt);
            }
        });

        toLabel.setText("To:");

        toTextField.setText("02/09/2011");
        toTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toTextFieldActionPerformed(evt);
            }
        });

        reportTimespan.add(pastWeekRadio);
        pastWeekRadio.setText("Past Week");
        pastWeekRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pastWeekRadioActionPerformed(evt);
            }
        });

        reportTimespan.add(entireHistoryRadio);
        entireHistoryRadio.setSelected(true);
        entireHistoryRadio.setText("Entire History");
        entireHistoryRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entireHistoryRadioActionPerformed(evt);
            }
        });

        allProvidersCheckBox.setSelected(true);
        allProvidersCheckBox.setText("All Providers");
        allProvidersCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allProvidersCheckBoxActionPerformed(evt);
            }
        });

        viewProviderButton.setText("View Provider Information");
        viewProviderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewProviderButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout serviceHistoryPanelLayout = new org.jdesktop.layout.GroupLayout(serviceHistoryPanel);
        serviceHistoryPanel.setLayout(serviceHistoryPanelLayout);
        serviceHistoryPanelLayout.setHorizontalGroup(
            serviceHistoryPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(serviceHistoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(serviceHistoryPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(tableScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 918, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, serviceHistoryPanelLayout.createSequentialGroup()
                        .add(8, 8, 8)
                        .add(fromRadio)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(fromTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 97, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(toLabel)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(toTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 74, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(pastWeekRadio)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(entireHistoryRadio)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 207, Short.MAX_VALUE)
                        .add(allProvidersCheckBox)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(viewProviderButton)))
                .addContainerGap())
        );

        serviceHistoryPanelLayout.linkSize(new java.awt.Component[] {fromTextField, toTextField}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        serviceHistoryPanelLayout.setVerticalGroup(
            serviceHistoryPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(serviceHistoryPanelLayout.createSequentialGroup()
                .add(tableScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(serviceHistoryPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(serviceHistoryPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(viewProviderButton)
                        .add(allProvidersCheckBox))
                    .add(serviceHistoryPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(fromRadio)
                        .add(fromTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(toLabel)
                        .add(toTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(pastWeekRadio)
                        .add(entireHistoryRadio))))
        );

        generateMemberReportButton.setText("Generate Member Report");

        addServiceInstanceButton.setText("Add Service Instance");
        addServiceInstanceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addServiceInstanceButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, serviceHistoryPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                        .add(memberInformationPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(serviceInformationPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .add(generateMemberReportButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(addServiceInstanceButton)))
                .addContainerGap())
        );

        layout.linkSize(new java.awt.Component[] {addServiceInstanceButton, generateMemberReportButton}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(memberInformationPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(serviceInformationPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(serviceHistoryPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(addServiceInstanceButton)
                    .add(generateMemberReportButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void changeMemberStatusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeMemberStatusButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_changeMemberStatusButtonActionPerformed

    private void editServiceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editServiceButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editServiceButtonActionPerformed

    private void deleteServiceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteServiceButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteServiceButtonActionPerformed

    private void addServiceInstanceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addServiceInstanceButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addServiceInstanceButtonActionPerformed

    private void viewProviderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewProviderButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_viewProviderButtonActionPerformed

    private void fromTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fromTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fromTextFieldActionPerformed

    private void toTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_toTextFieldActionPerformed

    private void pastWeekRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pastWeekRadioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pastWeekRadioActionPerformed

    private void allProvidersCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allProvidersCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_allProvidersCheckBoxActionPerformed

    private void entireHistoryRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entireHistoryRadioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_entireHistoryRadioActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MemberInformation().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addServiceInstanceButton;
    private javax.swing.JCheckBox allProvidersCheckBox;
    private javax.swing.JLabel billingTimestampLabel;
    private javax.swing.JLabel billingTimestampValueLabel;
    private javax.swing.JButton changeMemberStatusButton;
    private javax.swing.JLabel commentsLabel;
    private javax.swing.JScrollPane commentsScrollPane;
    private javax.swing.JTextArea commentsTextArea;
    private javax.swing.JLabel contactInformationLabel;
    private javax.swing.JScrollPane contactInformationScrollPane;
    private javax.swing.JTextArea contactInformationTextArea;
    private javax.swing.JLabel dateProvidedLabel;
    private javax.swing.JLabel dateProvidedValueLabel;
    private javax.swing.JButton deleteMemberButton;
    private javax.swing.JButton deleteServiceButton;
    private javax.swing.JButton editMemberButton;
    private javax.swing.JButton editServiceButton;
    private javax.swing.JRadioButton entireHistoryRadio;
    private javax.swing.JLabel feeLabel;
    private javax.swing.JLabel feeValueLabel;
    private javax.swing.JRadioButton fromRadio;
    private javax.swing.JTextField fromTextField;
    private javax.swing.JButton generateMemberReportButton;
    private javax.swing.JLabel instanceIDLabel;
    private javax.swing.JLabel instanceIDValueLabel;
    private javax.swing.JLabel memberIDLabel;
    private javax.swing.JLabel memberIDValueLabel;
    private javax.swing.JPanel memberInformationPanel;
    private javax.swing.JLabel memberStatusLabel;
    private javax.swing.JLabel memberStatusValueLabel;
    private javax.swing.JRadioButton pastWeekRadio;
    private javax.swing.ButtonGroup reportTimespan;
    private javax.swing.JPanel serviceHistoryPanel;
    private javax.swing.JPanel serviceInformationPanel;
    private javax.swing.JLabel serviceLabel;
    private javax.swing.JLabel serviceValueLabel;
    private javax.swing.JTable table;
    private javax.swing.JScrollPane tableScrollPane;
    private javax.swing.JLabel toLabel;
    private javax.swing.JTextField toTextField;
    private javax.swing.JButton viewProviderButton;
    // End of variables declaration//GEN-END:variables

}
