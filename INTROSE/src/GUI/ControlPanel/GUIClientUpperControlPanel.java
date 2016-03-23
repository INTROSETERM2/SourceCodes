package GUI.ControlPanel;

import javax.swing.JPanel;

import GUI.MainGUI;
import GUI.BranchUI.AddBranch;
import GUI.BranchUI.AddBranch;
import GUI.Product.AddProductRename;
import GUI.Receipt.POSReceipt;
import GUI.ReportUI.BranchReportRename;
import GUI.ReportUI.FinancialReport;
import GUI.ReportUI.FinancialReport;
import GUI.UserAccount.Login;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Branch.ManagerBranch;

import java.awt.Color;

public class GUIClientUpperControlPanel implements ActionListener{
	private MainGUI mainGUI;
	private JPanel jPanel = new JPanel();
	
	//Buttons
	private JButton btnAllBranches = new JButton("Yearly Report (All Branches)");	
	private JButton btnAddBranch = new JButton("Add Branch");
	private JButton btnDeleteBranch = new JButton("Del Branch");
	private JButton btnInventory = new JButton("Inventory");
	private JButton btnLogout = new JButton("Logout");		
	
	private ActListener act = new ActListener();
	
	private FinancialReport financialReport = new FinancialReport(mainGUI);
	
	private JScrollPane scrollPane = new JScrollPane();
	private JTable jTable = new JTable();
	private ManagerBranch manBranch = new ManagerBranch();

	public GUIClientUpperControlPanel(MainGUI mainGUI){
		
		this.mainGUI = mainGUI;
		jPanel.setSize(223,450);
		jPanel.setLayout(null);
		
		// Buttons
		btnAllBranches.setBounds(7, 11, 190, 23);
		btnAllBranches.addActionListener(act);
		jPanel.add(btnAllBranches);
		
		btnAddBranch.setBounds(7, 237, 92, 23);
		btnAddBranch.addActionListener(act);
		jPanel.add(btnAddBranch );
		
		btnDeleteBranch.setBounds(98, 237, 99, 23);
		btnDeleteBranch.addActionListener(act);
		jPanel.add(btnDeleteBranch);
		
		jPanel.add(btnInventory );
		btnInventory.setBounds(7, 278, 190, 23);
		btnInventory.addActionListener(act);
		
		jPanel.add(btnLogout );
		btnLogout.setBounds(7, 326, 190, 23);
		btnLogout.addActionListener(act);

		
		
		scrollPane.setBounds(7, 62, 190, 146);
		jPanel.add(scrollPane);
		jTable.setBackground(Color.WHITE);
		
		DefaultTableModel model = new DefaultTableModel(new Object[]{"Branch Name"}, 0) {
 		   @Override
 		   public boolean isCellEditable(int row, int column) {
 		       return false;
 		   }
		};
		
		ArrayList<String> branches = new ArrayList<String>();
		branches = manBranch.getBranches();
		
		for(int i = 0; i < branches.size();i++){
			model.addRow(new Object[]{branches.get(i)});
		}
		
		jTable.setBorder(new EmptyBorder(0, 0, 0, 0));
		jTable.setBackground(UIManager.getColor("Button.background"));
		jTable.setModel(model);
		scrollPane.setViewportView(jTable);
		
		mainGUI.removeAllRightSplit();
		mainGUI.setRightSplit(financialReport.getJPanel());
		
        jTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent m) {
            	if (m.getClickCount() == 2) {
        			mainGUI.removeAllRightSplit();
        			BranchReportRename viewBranchReport = new BranchReportRename(mainGUI); 
        			mainGUI.setRightSplit(viewBranchReport.getJPanel());
            	}
            }
        });
	}
	
	public JPanel getJPanel() {
		return jPanel;
	}
	
	private class ActListener implements ActionListener{
    	public void actionPerformed(ActionEvent e){
    		if(e.getSource() == btnAllBranches){
    			mainGUI.removeAllRightSplit();
    			mainGUI.setRightSplit(financialReport.getJPanel());
    		}
    		if(e.getSource() == btnAddBranch){
    			mainGUI.removeAllRightSplit();
    			AddBranch addBranch = new AddBranch(mainGUI);
    			mainGUI.setRightSplit(addBranch.getJPanel());
    		}
    		if(e.getSource() == btnDeleteBranch){
    			
    		}
    		if(e.getSource() == btnInventory){
    			mainGUI.removeAllRightSplit();
    			AddProductRename addProduct = new AddProductRename(mainGUI); 
    			mainGUI.setRightSplit(addProduct.getJPanel());
    		}
    		if(e.getSource() == btnLogout){
    			MainGUI.BRANCH.setBranchID(-1);
    			mainGUI.logout();
    		}
    	}
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}