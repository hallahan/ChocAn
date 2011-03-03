package controller;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

import model.*;

import java.util.Vector;

public class AddOrEditServiceInstance extends JFrame {
	//constructor to add a new service instance
	public AddOrEditServiceInstance() {
		this(new ServiceInstance());
		addServiceInstance = true;
	}
	//constructor to edit an existing service instance
    public AddOrEditServiceInstance(ServiceInstance si) {
    	Application.windows().addOrEditServiceInstance = this;	//lets the application manager point to this
        db = SQLiteInterface.singleton();
        this.si = si;
        
    	initComponents();
        loadListWithServices("");	//loads all of the services into the list
        
        if (Application.isManagerMode() == false) {
        	addButton.setEnabled(false);
        	editButton.setEnabled(false);
        }

        getRootPane().setDefaultButton(okButton);
        
        setVisible(true);
    }


    private void initComponents() {

        servicePanel 			= new JPanel();
        searchLabel				= new JLabel();
        searchTextField 		= new JTextField();
        searchButton 			= new JButton();
        servicesListScrollPane 	= new JScrollPane();
        servicesList 			= new JList();
        addButton 				= new JButton();
        editButton 				= new JButton();
        detailsPanel 			= new JPanel();
        currentDateRadio 		= new JRadioButton();
        specifiedDateRadio 		= new JRadioButton();
        dateText 				= new JTextField();
        currentTimestampRadio 	= new JRadioButton();
        specifiedTimestampRadio = new JRadioButton();
        timestampTextField 		= new JTextField();
        commentsTextField 		= new JLabel();
        commentsTextAreaScrollPane = new JScrollPane();
        commentsTextArea 		= new JTextArea();
        cancelButton 			= new JButton();
        okButton 				= new JButton();

//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        servicePanel.setBorder(BorderFactory.createTitledBorder(null, "Services", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Helvetica", 2, 14)));

        searchLabel.setText("Search:");

        searchTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                searchTextFieldActionPerformed(evt);
            }
        });

        searchButton.setText("Search");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                searchTextFieldActionPerformed(evt);
            }
        });

        listModel = new DefaultListModel();
        
        servicesList.setModel(listModel);
        
        servicesListScrollPane.setViewportView(servicesList);

        addButton.setText("Add");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        editButton.setText("Edit");
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout servicePanelLayout = new org.jdesktop.layout.GroupLayout(servicePanel);
        servicePanel.setLayout(servicePanelLayout);
        servicePanelLayout.setHorizontalGroup(
            servicePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(servicePanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(servicePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(servicesListScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
                    .add(servicePanelLayout.createSequentialGroup()
                        .add(searchLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(searchTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(searchButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, servicePanelLayout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 384, Short.MAX_VALUE)
                        .add(addButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 75, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(editButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 75, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        servicePanelLayout.linkSize(new Component[] {addButton, editButton}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        servicePanelLayout.setVerticalGroup(
            servicePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(servicePanelLayout.createSequentialGroup()
                .add(servicePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(searchLabel)
                    .add(searchTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(searchButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(servicesListScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(servicePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(editButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(addButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        detailsPanel.setBorder(BorderFactory.createTitledBorder(null, "Details", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Helvetica", 2, 14)));

        currentDateRadio.setText("Current Date");
        currentDateRadio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                currentDateRadioActionPerformed(evt);
            }
        });
        
        specifiedDateRadio.setText("Specified Date:");
        specifiedDateRadio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                specifiedDateRadioActionPerformed(evt);
            }
        });
        
        ButtonGroup dateGroup = new ButtonGroup();
		dateGroup.add(currentDateRadio);
		dateGroup.add(specifiedDateRadio);
		

		if (si.getDate_provided().equals(" ") == true) {
			currentDateRadio.setSelected(true);
			dateText.setText("XX-XX-XXXX");
			dateText.setEnabled(false);
		} else {
			specifiedDateRadio.setSelected(true);
			dateText.setText(si.getDate_provided());
			dateText.setEnabled(true);
		}
        

        currentTimestampRadio.setText("Current Timestamp");
        currentTimestampRadio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	currentTimestampRadioActionPerformed(evt);
            }
        });
        
        specifiedTimestampRadio.setText("Specified Timestamp:");
        specifiedTimestampRadio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	specifiedTimestampRadioActionPerformed(evt);
            }
        });

        ButtonGroup timeGroup = new ButtonGroup();
		timeGroup.add(currentTimestampRadio);
		timeGroup.add(specifiedTimestampRadio);
		
		
		
		if (si.getTime_stamp().equals(" ") == true) {
			currentTimestampRadio.setSelected(true);
			timestampTextField.setText("XX-XX-XXXX XX:XX:XX");
			timestampTextField.setEnabled(false);
		} else {
			specifiedTimestampRadio.setSelected(true);
			timestampTextField.setText(si.getTime_stamp());
			timestampTextField.setEnabled(true);
		}
        

        commentsTextField.setText("Comments:");

//        commentsTextArea.setColumns(20);
//        commentsTextArea.setRows(5);
        
        commentsTextArea.setText(si.comments);
        
        commentsTextAreaScrollPane.setViewportView(commentsTextArea);

        org.jdesktop.layout.GroupLayout detailsPanelLayout = new org.jdesktop.layout.GroupLayout(detailsPanel);
        detailsPanel.setLayout(detailsPanelLayout);
        detailsPanelLayout.setHorizontalGroup(
            detailsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(detailsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(detailsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(commentsTextAreaScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
                    .add(detailsPanelLayout.createSequentialGroup()
                        .add(detailsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(currentTimestampRadio, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(detailsPanelLayout.createSequentialGroup()
                                .add(currentDateRadio, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(41, 41, 41)))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(detailsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(specifiedTimestampRadio, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(detailsPanelLayout.createSequentialGroup()
                                .add(specifiedDateRadio, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(41, 41, 41)))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(detailsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(dateText, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                            .add(timestampTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
                        .add(42, 42, 42))
                    .add(commentsTextField))
                .addContainerGap())
        );
        detailsPanelLayout.setVerticalGroup(
            detailsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(detailsPanelLayout.createSequentialGroup()
                .add(detailsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(currentDateRadio)
                    .add(specifiedDateRadio)
                    .add(dateText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(detailsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(currentTimestampRadio)
                    .add(specifiedTimestampRadio)
                    .add(timestampTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(commentsTextField)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(commentsTextAreaScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                .addContainerGap())
        );

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        okButton.setText("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
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
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, servicePanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(detailsPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 414, Short.MAX_VALUE)
                        .add(cancelButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 86, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(okButton)))
                .addContainerGap())
        );

        layout.linkSize(new Component[] {cancelButton, okButton}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(servicePanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(detailsPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(okButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(cancelButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>

    private void searchTextFieldActionPerformed(ActionEvent evt) {
        System.out.println("searchTextFieldActionPerformed");
        
        loadListWithServices( searchTextField.getText() );
        
    }

    private void addButtonActionPerformed(ActionEvent evt) {
    	System.out.println("add button action performed");
    	
    	new AddService();
    	
    }

    private void editButtonActionPerformed(ActionEvent evt) {
        System.out.println("edit button action performed");
        
        Service ser = (Service)servicesList.getSelectedValue();
        new EditService(ser);
        
    }

    private void cancelButtonActionPerformed(ActionEvent evt) {
    	System.out.println("cancel button");
    	dispose();
    }
    
    private void currentDateRadioActionPerformed(ActionEvent evt) {
    	dateText.setEnabled(false);
    }
    
    private void specifiedDateRadioActionPerformed(ActionEvent evt) {
    	dateText.setEnabled(true);
    }
    
    private void currentTimestampRadioActionPerformed(ActionEvent evt) {
    	timestampTextField.setEnabled(false);
    }
    
    private void specifiedTimestampRadioActionPerformed(ActionEvent evt) {
    	timestampTextField.setEnabled(true);
    }
    
    private void okButtonActionPerformed(ActionEvent evt) {
    	System.out.println("ok button");
    	
    	if (si.instance_id == 0) {
    		si.member_id = Application.selectedMemberId;
    		si.provider_id = Application.appOperatorProviderId;
    	}
    	Service selectedService = (Service)servicesList.getSelectedValue();
    	si.service_id = selectedService.service_id;
    	
    	if (currentDateRadio.isSelected() == true) {
    		si.setDate_provided(DateAndTime.getCurrentDate());
    	} else {
    		String inputDate = dateText.getText();
//    		String validatedInputDate = DateAndTime.formatDate(inputDate);
    		si.setDate_provided(inputDate);
    	}
    	
    	if (currentTimestampRadio.isSelected() == true) {
    		si.setTime_stamp(DateAndTime.getCurrentTimestamp());
    	} else {
    		si.setTime_stamp(timestampTextField.getText());
    	}
    	
    	//this insures that an improperly formatted date or time doesn't cause null
    	//to be written for this service instance record
    	if (si.getDate_provided() == null || si.getTime_stamp() == null) {
    		return;
    	}
    	
    	si.comments = commentsTextArea.getText();
    	
    	if (si.instance_id == 0) {
    		db.addServiceInstance(si);
    	} else {
    		db.updateServiceInstance(si);
    	}
    	if (Application.windows() != null)
    		Application.windows().memberInformation.updateWindow();
    	if (Application.windows() != null)
    		Application.windows().providerInformation.updateWindow();
    	dispose();
    }

    public void loadListWithServices(String searchKey) {
    	Service selectedService = null;
    	
    	listModel.clear();
    	
    	Vector<Service> s = db.retrieveServiceTableSorted(searchKey, "name", true);
    	//adds all of the services retrieved into the listModel
    	for (Service each : s) {
    		listModel.addElement(each);
    		if (each.service_id == si.service_id) {
    			selectedService = each;
    		}
    	}
    	
    	if (selectedService != null) {
    		servicesList.setSelectedValue(selectedService, true);
    	}
    }
    
    public void updateList() {
    	searchTextField.setText("");
    	loadListWithServices("");
    }
    
    public static void main(String args[]) {
    	Application.setNimbusLookAndFeel();
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddOrEditServiceInstance();
            }
        });
    }

    private JButton cancelButton;
    private JButton searchButton;
    private JButton addButton;
    private JButton editButton;
    private JButton okButton;
    
    private JTextArea commentsTextArea;
    
    private JTextField dateText;
    private JTextField searchTextField;
    private JTextField timestampTextField;
    
    private JList servicesList;
    
    private JRadioButton currentDateRadio;
    private JRadioButton specifiedDateRadio;
    private JRadioButton currentTimestampRadio;
    private JRadioButton specifiedTimestampRadio;
    
    private JPanel servicePanel;
    private JPanel detailsPanel;

    private JLabel searchLabel;
    private JLabel commentsTextField;
    
    private JScrollPane servicesListScrollPane;
    private JScrollPane commentsTextAreaScrollPane;
    
    private DefaultListModel listModel;
    
    SQLiteInterface db;
    
    private boolean addServiceInstance = false;
    private ServiceInstance si;
}
