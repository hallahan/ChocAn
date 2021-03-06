package controller;

import model.*;

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
        Application.windows().memberSearch = this;
        
        setJMenuBar(new Menu());
        
		initComponents();
		memberSearchTable.setAutoCreateRowSorter(true);
		
		//set width of some columns
		TableColumn col = memberSearchTable.getColumnModel().getColumn(0);
		col.setPreferredWidth(30);
		col = memberSearchTable.getColumnModel().getColumn(5);
		col.setPreferredWidth(160);
		col = memberSearchTable.getColumnModel().getColumn(7);
		col.setPreferredWidth(30);
		
		setVisible(true);
    }

    private void initComponents() {

        memberSearchLabel 			= new JLabel();
        memberSearchTextField 		= new JTextField();
        memberSearchButton 			= new JButton();
        memberSearchTableScrollPane = new JScrollPane();
        memberSearchTable 			= new JTable();
        addMemberButton				= new JButton();
        cancelButton 				= new JButton();
        selectButton 				= new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        memberSearchLabel.setText("Member Search:");

        String defaultText = "Search by the contents of any field in the member table...";
        memberSearchTextField.setText(defaultText);
        memberSearchTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                memberSearchActionPerformed(evt);
            }
        });
        memberSearchTextField.setSelectionEnd(defaultText.length()-1);
        memberSearchTextField.setSelectionStart(0);

        memberSearchButton.setText("Search");
        memberSearchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                memberSearchActionPerformed(evt);
            }
        });

        memberTableModel = new MemberTableModel();
        memberSearchTable.setModel(memberTableModel);
        memberSearchTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        memberSearchTable.setRowSelectionAllowed(true);
        memberSearchTable.setColumnSelectionAllowed(false);
        memberSearchTable.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                tableKeyPressed(evt);
            }
        });
        
        memberSearchTableScrollPane.setViewportView(memberSearchTable);

        addMemberButton.setText("Add Member");
        addMemberButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		addMemberButtonActionPerformed(evt);
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
                    .add(GroupLayout.LEADING, memberSearchTableScrollPane, GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE)
                    .add(GroupLayout.LEADING, layout.createSequentialGroup()
                        .add(memberSearchLabel)
                        .addPreferredGap(LayoutStyle.RELATED)
                        .add(memberSearchTextField, GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.RELATED)
                        .add(memberSearchButton))
                    .add(layout.createSequentialGroup()
                    	.add(addMemberButton, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
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
                    .add(memberSearchLabel)
                    .add(memberSearchTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .add(memberSearchButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(memberSearchTableScrollPane, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                .add(18, 18, 18)
                .add(layout.createParallelGroup(GroupLayout.BASELINE)
                    .add(selectButton)
                    .add(cancelButton)
                    .add(addMemberButton))
                .addContainerGap())
        );
        this.setTitle("Member Search");
        pack();
    }

    private void memberSearchActionPerformed(ActionEvent evt) {
        String searchKey = memberSearchTextField.getText();
        memberTableModel.search(searchKey);
    }

    private void addMemberButtonActionPerformed(ActionEvent evt) {
    	new AddMember();
    }
    
    private void cancelButtonActionPerformed(ActionEvent evt) {
    	Application.windows().closeAllWindows();
		new Login();
    }

    private void selectButtonActionPerformed(ActionEvent evt) {
    	int row = memberSearchTable.getSelectedRow();
    	Member selMem = memberTableModel.getMember(row);
        new MemberInformation(selMem);
      
    	dispose();
    }

    private void tableKeyPressed(KeyEvent evt) {
    	int keyCode = evt.getKeyCode();
    	if (keyCode == 10) {  //10 is the key code for Return (Enter)
    		selectButtonActionPerformed(null);
    	}
    }
    
    public static void main(String args[]) {
    	Application.setNimbusLookAndFeel();
//    	Application.setNativeLookAndFeel();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MemberSearch();
            }
        });
    }
    

    
    public void updateWindow() {
    	memberSearchTextField.setText("");
    	memberTableModel.search("");
    }

//    private JButton addMemberButton;
    private JButton addMemberButton;
    private JButton cancelButton;
    private JButton memberSearchButton;
    private JLabel memberSearchLabel;
    private JTable memberSearchTable;
    private JScrollPane memberSearchTableScrollPane;
    private JTextField memberSearchTextField;
    private JButton selectButton;

    private MemberTableModel memberTableModel;
}
