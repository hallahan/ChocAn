package controller;
import model.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.util.Vector;
import java.awt.event.*;

public class MemberInformation extends JFrame {

    public MemberInformation(Member m) {
        Application.windows().memberInformation = this;
    	this.db = SQLiteInterface.singleton();
    	this.m = m;
    	setJMenuBar(Application.windows().menu);
    	initComponents();
        
        if (Application.isManagerMode() == false) {
        	activateButton.setEnabled(false);
        }
        
        
        
        setVisible(true);
    }

    private void initComponents() {

        reportTimespan = new ButtonGroup();
        memberInformationPanel = new JPanel();
        contactInformationLabel = new JLabel();
        contactInformationScrollPane = new JScrollPane();
        contactInformationTextArea = new JTextArea();
        memberIDLabel = new JLabel();
        providerIDValueLabel = new JLabel();
        memberStatusLabel = new JLabel();
        memberStatusValue = new JLabel();
        deleteMemberButton = new JButton();
        editMemberButton = new JButton();
        activateButton = new JButton();
        serviceInformationPanel = new JPanel();
        instanceIDLabel = new JLabel();
        instanceIDValueLabel = new JLabel();
        serviceLabel = new JLabel();
        serviceValueLabel = new JLabel();
        feeLabel = new JLabel();
        feeValueLabel = new JLabel();
        dateProvidedLabel = new JLabel();
        dateProvidedValueLabel = new JLabel();
        billingTimestampLabel = new JLabel();
        billingTimestampValueLabel = new JLabel();
        commentsLabel = new JLabel();
        commentsScrollPane = new JScrollPane();
        commentsTextArea = new JTextArea();
        deleteServiceButton = new JButton();
        editServiceButton = new JButton();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        serviceHistoryPanel = new JPanel();
        tableScrollPane = new JScrollPane();
        table = new JTable();
        fromRadio = new JRadioButton();
        fromTextField = new JTextField();
        toLabel = new JLabel();
        toTextField = new JTextField();
        pastWeekRadio = new JRadioButton();
        entireHistoryRadio = new JRadioButton();
        viewProviderButton = new JButton();
        allProvidersCheckBox = new JCheckBox();
        generateMemberReportButton = new JButton();
        addServiceInstanceButton = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        memberInformationPanel.setBorder(BorderFactory.createTitledBorder(null, "Member Information", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 2, 13))); // NOI18N

        contactInformationLabel.setText("Contact Information:");

        contactInformationTextArea.setColumns(20);
        contactInformationTextArea.setEditable(false);
        contactInformationTextArea.setRows(5);
//        contactInformationTextArea.setText(formatContactText());
        setContactInformationText();
        contactInformationScrollPane.setViewportView(contactInformationTextArea);

        memberIDLabel.setText("Member ID:");

        providerIDValueLabel.setText(String.valueOf(m.member_id));

        memberStatusLabel.setText("Member Status:");

//        memberStatusValue.setFont(memberStatusValue.getFont().deriveFont(memberStatusValue.getFont().getStyle() | java.awt.Font.BOLD));
//        memberStatusValue.setForeground(new java.awt.Color(204, 0, 0));
//        memberStatusValue.setText("Inactive");
        setMemberStatusValue();
        
        deleteMemberButton.setText("Delete");
        deleteMemberButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteMemberButtonActionPerformed(evt);
            }
        });

        editMemberButton.setText("Edit");
        editMemberButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editMemberButtonActionPerformed(evt);
            }
        });
        
        setMemberStatusButtonColorAndText();
        activateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activateButtonActionPerformed(evt);
            }
        });
        
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
                        .add(contactInformationScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE))
                    .add(memberInformationPanelLayout.createSequentialGroup()
                        .add(memberInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(memberIDLabel)
                            .add(memberStatusLabel))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(memberInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(providerIDValueLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                            .add(memberStatusValue, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, memberInformationPanelLayout.createSequentialGroup()
                        .add(activateButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(deleteMemberButton)
                        .add(5, 5, 5)
                        .add(editMemberButton)))
                .addContainerGap())
        );

        memberInformationPanelLayout.linkSize(new java.awt.Component[] {activateButton, deleteMemberButton, editMemberButton}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        memberInformationPanelLayout.setVerticalGroup(
            memberInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(memberInformationPanelLayout.createSequentialGroup()
                .add(contactInformationLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(contactInformationScrollPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(memberInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(memberIDLabel)
                    .add(providerIDValueLabel))
                .add(8, 8, 8)
                .add(memberInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(memberStatusLabel)
                    .add(memberStatusValue))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 74, Short.MAX_VALUE)
                .add(memberInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(editMemberButton)
                    .add(deleteMemberButton)
                    .add(activateButton)))
        );

        memberInformationPanelLayout.linkSize(new java.awt.Component[] {activateButton, deleteMemberButton, editMemberButton}, org.jdesktop.layout.GroupLayout.VERTICAL);

        serviceInformationPanel.setBorder(BorderFactory.createTitledBorder(null, "Service Information", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 2, 13)));

    
    	instanceIDLabel.setText("Instance ID:");
        instanceIDValueLabel.setText(" "); 

        serviceLabel.setText("Service:");

        serviceValueLabel.setText(" ");

        feeLabel.setText("Fee:");

        feeValueLabel.setText(" ");

        dateProvidedLabel.setText("Date Provided:");

        dateProvidedValueLabel.setText(" ");

        billingTimestampLabel.setText("Billing Timestamp:");

        billingTimestampValueLabel.setText(" ");

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

        setProviderID(" ");
     

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
                            .add(serviceValueLabel)
                            .add(feeValueLabel)
                            .add(serviceInformationPanelLayout.createSequentialGroup()
                                .add(serviceInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(billingTimestampValueLabel)
                                    .add(dateProvidedValueLabel))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 191, Short.MAX_VALUE))
                            .add(commentsScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                            .add(serviceInformationPanelLayout.createSequentialGroup()
                                .add(instanceIDValueLabel)
                                .add(106, 106, 106)
                                .add(jLabel1)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabel2)))))
                .addContainerGap())
        );
        serviceInformationPanelLayout.setVerticalGroup(
            serviceInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(serviceInformationPanelLayout.createSequentialGroup()
                .add(serviceInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(instanceIDLabel)
                    .add(instanceIDValueLabel)
                    .add(jLabel1)
                    .add(jLabel2))
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
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 26, Short.MAX_VALUE)
                .add(serviceInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(editServiceButton)
                    .add(deleteServiceButton)))
        );

        serviceHistoryPanel.setBorder(BorderFactory.createTitledBorder(null, "Service History", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 2, 13)));

        setupTable();

        reportTimespan.add(fromRadio);
        fromRadio.setText("From:");
        fromRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fromRadioActionPerformed(evt);
            }
        });

        fromTextField.setText("01-01-2001");

        toLabel.setText("To:");

        toTextField.setText("02-09-2011");

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
        allProvidersCheckBox.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		checkBoxActionPerformed(evt);
        	}
        });
        
        viewProviderButton.setText("View Provider Information");
        viewProviderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewProviderButtonActionPerformed(evt);
            }
        });

        allProvidersCheckBox.setText("All Providers");
        allProvidersCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allProvidersCheckBoxActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout serviceHistoryPanelLayout = new org.jdesktop.layout.GroupLayout(serviceHistoryPanel);
        serviceHistoryPanel.setLayout(serviceHistoryPanelLayout);
        serviceHistoryPanelLayout.setHorizontalGroup(
            serviceHistoryPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(serviceHistoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(serviceHistoryPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(tableScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 895, Short.MAX_VALUE)
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
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 24, Short.MAX_VALUE)
                        .add(allProvidersCheckBox)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(viewProviderButton)))
                .addContainerGap())
        );

        serviceHistoryPanelLayout.linkSize(new java.awt.Component[] {fromTextField, toTextField}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        serviceHistoryPanelLayout.setVerticalGroup(
            serviceHistoryPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(serviceHistoryPanelLayout.createSequentialGroup()
                .add(tableScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
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
        generateMemberReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateMemberReportButtonActionPerformed(evt);
            }
        });

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
                    .add(layout.createSequentialGroup()
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
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(serviceInformationPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(memberInformationPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(serviceHistoryPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(addServiceInstanceButton)
                    .add(generateMemberReportButton))
                .addContainerGap())
        );

        layout.linkSize(new java.awt.Component[] {memberInformationPanel, serviceInformationPanel}, org.jdesktop.layout.GroupLayout.VERTICAL);

        pack();
    }
    
    private void checkBoxActionPerformed(ActionEvent evt) {
    	boolean checkBoxSelected = allProvidersCheckBox.isSelected();
    	tableModel.allProvidersSelected(checkBoxSelected);
    	selectFirstRow();
    }

    private void editServiceButtonActionPerformed(ActionEvent evt) {                                                  
        new AddOrEditServiceInstance(selectedServiceInstance);
    }                                                 

    private void deleteServiceButtonActionPerformed(ActionEvent evt) {                                                    
        db.deleteServiceInstance(selectedServiceInstance.instance_id);
        updateWindow();
    }                                                   

    private void addServiceInstanceButtonActionPerformed(ActionEvent evt) {                                                         
        new AddOrEditServiceInstance();
    }                                                        

    private void viewProviderButtonActionPerformed(ActionEvent evt) {                                                   
        dispose();
        int pid = selectedServiceInstance.provider_id;
        Provider p = db.retrieveProvider(pid);
        new ProviderInformation(p);
    }                                                  

    private void pastWeekRadioActionPerformed(ActionEvent evt) {                                              
        // TODO add your handling code here:
    }                                             

    private void entireHistoryRadioActionPerformed(ActionEvent evt) {                                                   
        // TODO add your handling code here:
    }                                                  

    private void generateMemberReportButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void allProvidersCheckBoxActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void fromRadioActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void editMemberButtonActionPerformed(ActionEvent evt) {
        new EditMember(m);
    }

    private void deleteMemberButtonActionPerformed(ActionEvent evt) {
        db.deleteMember(m.member_id);
        dispose();
    }

    private void activateButtonActionPerformed(ActionEvent evt) {
        if (m.active_status == 0) {
        	m.active_status = 1;
        } else {
        	m.active_status = 0;
        }
        SQLiteInterface.singleton().updateMember(m);
        setMemberStatusValue();
        setMemberStatusButtonColorAndText();
    }

    
    private void setContactInformationText() {
    	String f = m.first + " " + m.middle + " " + m.last + "\n"
		+ m.address + "\n"
		+ m.city + ", " + m.state + " " + m.zip;
    	
    	contactInformationTextArea.setText(f);
    }
    
    private void setMemberStatusValue() {
        memberStatusValue.setFont(memberStatusValue.getFont().deriveFont(memberStatusValue.getFont().getStyle() | java.awt.Font.BOLD));
        if (m.active_status == 0) {
	        memberStatusValue.setForeground(new java.awt.Color(204, 0, 0));
	        memberStatusValue.setText("Inactive");
        } else {
        	memberStatusValue.setForeground(new java.awt.Color(0, 153, 0));
	        memberStatusValue.setText("Active");
        }
    }
    
    //the activateButton
    private void setMemberStatusButtonColorAndText() {
    	if (m.active_status == 0) {
	        activateButton.setForeground(new java.awt.Color(0, 153, 0));
	        activateButton.setText("Activate");
        } else { //204 for red, 153 for green
        	activateButton.setForeground(new java.awt.Color(204, 0, 0));
	        activateButton.setText("Suspend");
        }
    }
    
    public void updateMemberInformation(Member mem) {
    	m = mem;
    	setContactInformationText();
    	setMemberStatusValue();
    	setMemberStatusButtonColorAndText();
    }
    
    public static void main(String args[]) {
    	Application.setNimbusLookAndFeel();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MemberInformation(SQLiteInterface.singleton().retrieveMember(1));
            }
        });
    }

    private void setProviderID(String id) {
    	if (Application.isManagerMode() == true) {
    		jLabel1.setText("Provider ID:");
            jLabel2.setText(id);
    	}
    }
   
    private void setupTable() {
    	tableModel = new ServiceInstanceTableModel(m.member_id, true);
        table.setModel(tableModel);
        table.setAutoCreateRowSorter(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(false);
        
        //customizing column widths
        TableColumn tableColumn = table.getColumnModel().getColumn(0);
        tableColumn.setPreferredWidth(10);
        tableColumn = table.getColumnModel().getColumn(2);
        tableColumn.setPreferredWidth(50);
        
        tableScrollPane.setViewportView(table);
        
        selectFirstRow();
        
        table.getSelectionModel().addListSelectionListener(new RowListener());
    }
    
    private void selectFirstRow() {
    	if (tableModel.getRowCount() > 0) {
        	table.setRowSelectionInterval(0, 0);
        	selectedServiceInstance = tableModel.getRow(0);
        	refreshServiceInfoPane();
        }
    }
    
    private void rowSelected() {
    	int selRow = table.getSelectedRow();
    	selectedServiceInstance = tableModel.getRow(selRow);
    	refreshServiceInfoPane();
    }
    
    private void refreshServiceInfoPane() {
    	if (selectedServiceInstance == null) return;
    	
    	instanceIDValueLabel.setText(String.valueOf(selectedServiceInstance.instance_id));
    	
    	int sid = selectedServiceInstance.service_id;
    	Service ser = db.retrieveService(sid);
    	String sname = ser.name;
    	serviceValueLabel.setText(sname);
    	
    	String fee = ser.fee;
    	feeValueLabel.setText(fee);
    	
    	dateProvidedValueLabel.setText(selectedServiceInstance.date_provided);
    	billingTimestampValueLabel.setText(selectedServiceInstance.time_stamp);
    	commentsTextArea.setText(selectedServiceInstance.comments);
    	setProviderID(String.valueOf(selectedServiceInstance.provider_id));
    	
    	
    }
    
    //is there a better way than setting up the table all over agian?
    public void updateWindow() {
//    	tableModel.fireTableDataChanged();
    	setupTable();
    	refreshServiceInfoPane();
    }
    
    private Member m;
    private ServiceInstanceTableModel tableModel;
    private SQLiteInterface db;
    private ServiceInstance selectedServiceInstance;
    
    private JButton activateButton;
    private JButton addServiceInstanceButton;
    private JCheckBox allProvidersCheckBox;
    private JLabel billingTimestampLabel;
    private JLabel billingTimestampValueLabel;
    private JLabel commentsLabel;
    private JScrollPane commentsScrollPane;
    private JTextArea commentsTextArea;
    private JLabel contactInformationLabel;
    private JScrollPane contactInformationScrollPane;
    private JTextArea contactInformationTextArea;
    private JLabel dateProvidedLabel;
    private JLabel dateProvidedValueLabel;
    private JButton deleteMemberButton;
    private JButton deleteServiceButton;
    private JButton editMemberButton;
    private JButton editServiceButton;
    private JRadioButton entireHistoryRadio;
    private JLabel feeLabel;
    private JLabel feeValueLabel;
    private JRadioButton fromRadio;
    private JTextField fromTextField;
    private JButton generateMemberReportButton;
    private JLabel instanceIDLabel;
    private JLabel instanceIDValueLabel;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel memberIDLabel;
    private JPanel memberInformationPanel;
    private JLabel memberStatusLabel;
    private JLabel memberStatusValue;
    private JRadioButton pastWeekRadio;
    private JLabel providerIDValueLabel;
    private ButtonGroup reportTimespan;
    private JPanel serviceHistoryPanel;
    private JPanel serviceInformationPanel;
    private JLabel serviceLabel;
    private JLabel serviceValueLabel;
    private JTable table;
    private JScrollPane tableScrollPane;
    private JLabel toLabel;
    private JTextField toTextField;
    private JButton viewProviderButton;
    
    //action listener for selecting a row in the service history table
    private class RowListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent event) {
            if (event.getValueIsAdjusting()) {
                return;
            }
            rowSelected();
        }
    }


}
