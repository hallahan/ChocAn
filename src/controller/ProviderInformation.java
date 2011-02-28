package controller;
import model.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.util.Vector;
import java.awt.event.*;
import java.awt.*;

public class ProviderInformation extends JFrame {

   
    public ProviderInformation(Provider p) {
        Application.windows().providerInformation = this;
        provider = p;
        db = SQLiteInterface.singleton();
        pt = db.retrieveProviderType(p.providertype_id);
        setJMenuBar(Application.windows().menu);
    	initComponents();
    	setVisible(true);
    }


    private void initComponents() {

        reportTimespan = new ButtonGroup();
        providerInformationPanel = new JPanel();
        contactInformationLabel = new JLabel();
        contactInformationScrollPane = new JScrollPane();
        providerInformationTextArea = new JTextArea();
        providerIDLabel = new JLabel();
        providerIDValueLabel = new JLabel();
        providerTypeLabel = new JLabel();
        providerTypeValue = new JLabel();
        dashLabel = new JLabel();
        providerTypeDescriptionLabel = new JLabel();
        deleteProviderButton = new JButton();
        editProviderButton = new JButton();
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
        serviceHistoryPanel = new JPanel();
        tableScrollPane = new JScrollPane();
        table = new JTable();
        fromRadio = new JRadioButton();
        fromTextField = new JTextField();
        toLabel = new JLabel();
        toTextField = new JTextField();
        pastWeekRadio = new JRadioButton();
        entireHistoryRadio = new JRadioButton();
        viewMemberButton = new JButton();
        generateProviderReportButton = new JButton();
//        addServiceInstanceButton = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        providerInformationPanel.setBorder(BorderFactory.createTitledBorder(null, "Provider Information", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Lucida Grande", 2, 13))); 

        contactInformationLabel.setText("Contact Information:");

        providerInformationTextArea.setColumns(20);
        providerInformationTextArea.setEditable(false);
        providerInformationTextArea.setRows(5);
        contactInformationScrollPane.setViewportView(providerInformationTextArea);
    
        
        providerTypeLabel.setText("Provider Type:");
        dashLabel.setText("- ");
        
        setProviderInformationText();
        
        deleteProviderButton.setText("Delete");
        if (Application.isManagerMode() == false) {
        	deleteProviderButton.setEnabled(false);
        }
        deleteProviderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                deleteProviderButtonActionPerformed(evt);
            }
        });

        editProviderButton.setText("Edit");
        if (Application.isManagerMode() == false) {
        	editProviderButton.setEnabled(false);
        }
        editProviderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                editProviderButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout providerInformationPanelLayout = new org.jdesktop.layout.GroupLayout(providerInformationPanel);
        providerInformationPanel.setLayout(providerInformationPanelLayout);
        providerInformationPanelLayout.setHorizontalGroup(
            providerInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(providerInformationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(providerInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(contactInformationLabel)
                    .add(providerInformationPanelLayout.createSequentialGroup()
                        .add(24, 24, 24)
                        .add(contactInformationScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE))
                    .add(providerInformationPanelLayout.createSequentialGroup()
                        .add(providerInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(providerIDLabel)
                            .add(providerTypeLabel)
                            .add(dashLabel))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(providerInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(providerIDValueLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                            .add(providerTypeValue, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                            .add(providerTypeDescriptionLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, providerInformationPanelLayout.createSequentialGroup()
                        .add(deleteProviderButton)
                        .add(5, 5, 5)
                        .add(editProviderButton)))
                .addContainerGap())
        );

        providerInformationPanelLayout.linkSize(new Component[] {deleteProviderButton, editProviderButton}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        providerInformationPanelLayout.setVerticalGroup(
            providerInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(providerInformationPanelLayout.createSequentialGroup()
                .add(contactInformationLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(contactInformationScrollPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(providerInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(providerIDLabel)
                    .add(providerIDValueLabel))
                .add(8, 8, 8)
                .add(providerInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(providerTypeLabel)
                    .add(providerTypeValue))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(providerInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(dashLabel)
                    .add(providerTypeDescriptionLabel))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 50, Short.MAX_VALUE)
                .add(providerInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(editProviderButton)
                    .add(deleteProviderButton)))
        );

        providerInformationPanelLayout.linkSize(new Component[] {deleteProviderButton, editProviderButton}, org.jdesktop.layout.GroupLayout.VERTICAL);

        serviceInformationPanel.setBorder(BorderFactory.createTitledBorder(null, "Service Information", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Lucida Grande", 2, 13)));

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
        deleteServiceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                deleteServiceButtonActionPerformed(evt);
            }
        });

        editServiceButton.setText("Edit");
        editServiceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
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
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 179, Short.MAX_VALUE))
                            .add(commentsScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE))))
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
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 26, Short.MAX_VALUE)
                .add(serviceInformationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(editServiceButton)
                    .add(deleteServiceButton)))
        );

        serviceHistoryPanel.setBorder(BorderFactory.createTitledBorder(null, "Service History", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Lucida Grande", 2, 13))); // NOI18N

        setupTable();

        reportTimespan.add(fromRadio);
        fromRadio.setText("From:");
        fromRadio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                fromRadioActionPerformed(evt);
            }
        });

        fromTextField.setText("01-01-2001");
        

        toLabel.setText("To:");

        toTextField.setText("02-09-2011");
        

        reportTimespan.add(pastWeekRadio);
        pastWeekRadio.setText("Past Week");
        pastWeekRadio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                pastWeekRadioActionPerformed(evt);
            }
        });

        reportTimespan.add(entireHistoryRadio);
        entireHistoryRadio.setSelected(true);
        entireHistoryRadio.setText("Entire History");
        entireHistoryRadio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                entireHistoryRadioActionPerformed(evt);
            }
        });

        viewMemberButton.setText("View Member Information");
        viewMemberButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                viewMemberButtonActionPerformed(evt);
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
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 142, Short.MAX_VALUE)
                        .add(viewMemberButton)))
                .addContainerGap())
        );

        serviceHistoryPanelLayout.linkSize(new Component[] {fromTextField, toTextField}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        serviceHistoryPanelLayout.setVerticalGroup(
            serviceHistoryPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(serviceHistoryPanelLayout.createSequentialGroup()
                .add(tableScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(serviceHistoryPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(viewMemberButton)
                    .add(serviceHistoryPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(fromRadio)
                        .add(fromTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(toLabel)
                        .add(toTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(pastWeekRadio)
                        .add(entireHistoryRadio))))
        );

        generateProviderReportButton.setText("Generate Provider Report");
        generateProviderReportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                generateProviderReportButtonActionPerformed(evt);
            }
        });

//        addServiceInstanceButton.setText("Add Service Instance");
//        addServiceInstanceButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent evt) {
//                addServiceInstanceButtonActionPerformed(evt);
//            }
//        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, serviceHistoryPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                        .add(providerInformationPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(serviceInformationPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .add(generateProviderReportButton)
//                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
//                        .add(addServiceInstanceButton)
                    ))
                .addContainerGap())
        );

//        layout.linkSize(new Component[] {addServiceInstanceButton, generateProviderReportButton}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(serviceInformationPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(providerInformationPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(serviceHistoryPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
//                    .add(addServiceInstanceButton)
                    .add(generateProviderReportButton))
                .addContainerGap())
        );

        layout.linkSize(new Component[] {providerInformationPanel, serviceInformationPanel}, org.jdesktop.layout.GroupLayout.VERTICAL);

        pack();
    }

    private void editServiceButtonActionPerformed(ActionEvent evt) {                                                  
    	new AddOrEditServiceInstance(selectedServiceInstance);
    }                                                 

    private void deleteServiceButtonActionPerformed(ActionEvent evt) {                                                    
    	db.deleteServiceInstance(selectedServiceInstance.instance_id);
        updateWindow();
    }                                                   

//    private void addServiceInstanceButtonActionPerformed(ActionEvent evt) {                                                         
//    	new AddOrEditServiceInstance();
//    }                                                        

    private void viewMemberButtonActionPerformed(ActionEvent evt) {                                                 
        dispose();
        int mid = selectedServiceInstance.member_id;
        Member m = db.retrieveMember(mid);
        new MemberInformation(m);
    }                                                

    private void pastWeekRadioActionPerformed(ActionEvent evt) {                                              
        // TODO add your handling code here:
    }                                             

    private void entireHistoryRadioActionPerformed(ActionEvent evt) {                                                   
        // TODO add your handling code here:
    }                                                  

    private void deleteProviderButtonActionPerformed(ActionEvent evt) {
        db.deleteProvider(provider.provider_id);
        dispose();
    }

    private void editProviderButtonActionPerformed(ActionEvent evt) {
        new AddOrEditProvider(provider);
    }

    private void generateProviderReportButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void fromRadioActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    public void setupTable() {
    	tableModel = new ServiceInstanceTableModel(provider.provider_id, false);
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
    
    	
    	
    }
    
    //is there a better way than setting up the table all over agian?
    public void updateWindow() {
//    	tableModel.fireTableDataChanged();
    	setupTable();
    	refreshServiceInfoPane();
    }
    
    public void updateProviderInformation(Provider p) {
    	provider = p;
    	setProviderInformationText();
    }
    
    private void setProviderInformationText() {
    	providerInformationTextArea.setText(provider.name + "\n" + provider.address + "\n" + provider.city + ", " + provider.state + " " + provider.zip);
        if (Application.isManagerMode() == true) {
	        providerIDLabel.setText("Provider ID:");
	        providerIDValueLabel.setText(String.valueOf(provider.provider_id));
        }
        pt = db.retrieveProviderType(provider.providertype_id);
        providerTypeValue.setText(pt.name);
        providerTypeDescriptionLabel.setText(pt.desc);
    }
    
    public static void main(String args[]) {
    	Application.setNimbusLookAndFeel();
//    	Application.setManagerMode("12steps");
        EventQueue.invokeLater(new Runnable() {
            public void run() {
            	Provider prov = new Provider();
            	prov.provider_id = 111;
            	prov.name = "test";
            	prov.address = "test";
            	prov.city = "test";
            	prov.state = "AK";
            	prov.zip = "60068";
            	prov.providertype_id = 1;
                new ProviderInformation(prov);
            }
        });
    }

    private Provider provider;
    private ProviderType pt;
    private SQLiteInterface db;
    private ServiceInstanceTableModel tableModel;
    private ServiceInstance selectedServiceInstance;
    
    
//    private JButton 		addServiceInstanceButton;
    private JLabel 			billingTimestampLabel;
    private JLabel 			billingTimestampValueLabel;
    private JLabel 			commentsLabel;
    private JScrollPane 	commentsScrollPane;
    private JTextArea 		commentsTextArea;
    private JLabel 			contactInformationLabel;
    private JScrollPane 	contactInformationScrollPane;
    private JTextArea 		providerInformationTextArea;
    private JLabel 			dashLabel;
    private JLabel 			dateProvidedLabel;
    private JLabel 			dateProvidedValueLabel;
    private JButton 		deleteProviderButton;
    private JButton 		deleteServiceButton;
    private JButton 		editProviderButton;
    private JButton 		editServiceButton;
    private JRadioButton 	entireHistoryRadio;
    private JLabel 			feeLabel;
    private JLabel 			feeValueLabel;
    private JRadioButton 	fromRadio;
    private JTextField 		fromTextField;
    private JButton 		generateProviderReportButton;
    private JLabel 			instanceIDLabel;
    private JLabel 			instanceIDValueLabel;
    private JRadioButton 	pastWeekRadio;
    private JLabel 			providerIDLabel;
    private JLabel 			providerIDValueLabel;
    private JPanel 			providerInformationPanel;
    private JLabel 			providerTypeDescriptionLabel;
    private JLabel 			providerTypeLabel;
    private JLabel 			providerTypeValue;
    private ButtonGroup 	reportTimespan;
    private JPanel 			serviceHistoryPanel;
    private JPanel 			serviceInformationPanel;
    private JLabel 			serviceLabel;
    private JLabel 			serviceValueLabel;
    private JTable 			table;
    private JScrollPane 	tableScrollPane;
    private JLabel 			toLabel;
    private JTextField 		toTextField;
    private JButton 		viewMemberButton;
    
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
