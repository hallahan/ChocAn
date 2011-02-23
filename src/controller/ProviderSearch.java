package controller;

import model.ProviderTableModel;

import javax.swing.*;
import javax.swing.table.*;

import java.awt.event.*;
import java.awt.*;

/* This is contained in the imported jar "swing-layout-1.0.4.jar"
 * Be sure to add this to your build path. 
 */
import org.jdesktop.layout.GroupLayout;
import org.jdesktop.layout.LayoutStyle;

public class ProviderSearch extends JFrame {

	public ProviderSearch() {
		Application.windows().providerSearch = this;
		setJMenuBar(Application.windows().menu);
		initComponents();
    }

    private void initComponents() {

        providerSearchLabel 			= new JLabel();
        providerSearchTextField 		= new JTextField();
        providerSearchButton 			= new JButton();
        providerSearchTableScrollPane = new JScrollPane();
        providerSearchTable 			= new JTable();
        addProviderButton				= new JButton();
        cancelButton 				= new JButton();
        selectButton 				= new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        providerSearchLabel.setText("Provider Search:");

        String defaultText = "Search by the contents of any field in the provider table...";
        providerSearchTextField.setText(defaultText);
        providerSearchTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                providerSearchActionPerformed(evt);
            }
        });
        providerSearchTextField.setSelectionEnd(defaultText.length()-1);
        providerSearchTextField.setSelectionStart(0);

        providerSearchButton.setText("Search");
        providerSearchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                providerSearchActionPerformed(evt);
            }
        });

        providerTableModel = new ProviderTableModel();
        providerSearchTable.setModel(providerTableModel);
        providerSearchTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        providerSearchTable.setRowSelectionAllowed(true);
        providerSearchTable.setColumnSelectionAllowed(false);
        
        providerSearchTableScrollPane.setViewportView(providerSearchTable);

        addProviderButton.setText("Add Provider");
        addProviderButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		addProviderButtonActionPerformed(evt);
        	}
        });
        
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
                    .add(GroupLayout.LEADING, providerSearchTableScrollPane, GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE)
                    .add(GroupLayout.LEADING, layout.createSequentialGroup()
                        .add(providerSearchLabel)
                        .addPreferredGap(LayoutStyle.RELATED)
                        .add(providerSearchTextField, GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.RELATED)
                        .add(providerSearchButton))
                    .add(layout.createSequentialGroup()
                    	.add(addProviderButton, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
                    	.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 291, Short.MAX_VALUE)
                        .add(cancelButton, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.RELATED)
                        .add(selectButton)))
                .addContainerGap())
        );

        layout.linkSize(new Component[] {cancelButton, selectButton}, GroupLayout.HORIZONTAL);

        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(GroupLayout.BASELINE)
                    .add(providerSearchLabel)
                    .add(providerSearchTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .add(providerSearchButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(providerSearchTableScrollPane, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                .add(18, 18, 18)
                .add(layout.createParallelGroup(GroupLayout.BASELINE)
                    .add(selectButton)
                    .add(cancelButton)
                    .add(addProviderButton))
                .addContainerGap())
        );
        this.setTitle("Provider Search");
        pack();
    }

    private void providerSearchActionPerformed(ActionEvent evt) {
        String searchKey = providerSearchTextField.getText();
        providerTableModel.search(searchKey);
        updateWindow();
    }

    private void addProviderButtonActionPerformed(ActionEvent evt) {
    	new AddOrEditProvider();
    }
    
    private void cancelButtonActionPerformed(ActionEvent evt) {
    	this.dispose();
    }

    private void selectButtonActionPerformed(ActionEvent evt) {
        // needs to be implemented
    }

    public static void main(String args[]) {
    	Application.setNimbusLookAndFeel();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProviderSearch().setVisible(true);
            }
        });
    }
    
    public void updateWindow() {
    	providerTableModel.fireTableDataChanged();
    }

//    private JButton addMemberButton;
    private JButton addProviderButton;
    private JButton cancelButton;
    private JButton providerSearchButton;
    private JLabel providerSearchLabel;
    private JTable providerSearchTable;
    private JScrollPane providerSearchTableScrollPane;
    private JTextField providerSearchTextField;
    private JButton selectButton;

    private ProviderTableModel providerTableModel;
}
