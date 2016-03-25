package GUI.ControlPanel;

import javax.swing.JPanel;

import GUI.MainGUI;
import GUI.BranchUI.AddBranch;
import GUI.BranchUI.AddBranch;
import GUI.Product.AddProduct;
import GUI.ReportUI.BranchReportRename;
import GUI.ReportUI.FinancialReport;

import javax.swing.JButton;
import javax.swing.JOptionPane;

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

import Branch.Branch;
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
	
	
	private FinancialReport financialReport = new FinancialReport(mainGUI);
	private AddProduct addProduct = new AddProduct(mainGUI); 
	private AddBranch addBranch;
	private JScrollPane scrollPane = new JScrollPane();
	private JTable jTable = new JTable();
	private ManagerBranch manBranch = new ManagerBranch();

	public GUIClientUpperControlPanel(MainGUI mainGUI){
		ActListener act = new ActListener();
		this.mainGUI = mainGUI;
		addBranch = new AddBranch(mainGUI);
		jPanel.setSize(240,450);
		jPanel.setLayout(new MigLayout("", "[28.00][190px][28.00]", "[23px][146px][23px][23px][23px]"));
		btnAllBranches.addActionListener(act);
		jPanel.add(btnAllBranches, "cell 1 0,growx,aligny center");
		btnAddBranch.addActionListener(act);
		jPanel.add(btnAddBranch, "cell 1 2,growx,aligny center" );
		btnDeleteBranch.addActionListener(act);
		jPanel.add(btnDeleteBranch, "cell 1 2,growx,aligny center");
		btnInventory.addActionListener(act);
		jPanel.add(btnInventory, "cell 1 3,grow" );
		btnLogout.addActionListener(act);
		jPanel.add(btnLogout, "cell 1 4,grow" );
		
		jPanel.add(scrollPane, "cell 1 1,grow");
		jTable.setBackground(Color.WHITE);
		
		DefaultTableModel model = new DefaultTableModel(new Object[]{"Branch Name"}, 0) {
 		   @Override
 		   public boolean isCellEditable(int row, int column) {
 		       return false;
 		   }
		};
		
		ArrayList<Branch> branches = new ArrayList<Branch>();
		branches = manBranch.getBranches();
		
		for(int i = 0; i < branches.size();i++){
			model.addRow(new Object[]{branches.get(i).getBranchName()});
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
    			mainGUI.setRightSplit(addBranch.getJPanel());
    		}
    		if(e.getSource() == btnDeleteBranch){
    			if(jTable.getSelectedRow()== -1)
    				JOptionPane.showMessageDialog(null, "Please select a branch to delete!");
    			else{
	    			String temp = (String) jTable.getValueAt(jTable.getSelectedRow(), jTable.getSelectedColumn());
	    			if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete " + temp + " ?", "Branch", 
	    				    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)
	    				    == JOptionPane.YES_OPTION)
	    				{
	    					ManagerBranch managerBranch = new ManagerBranch();
	    					managerBranch.deleteBranch(temp);
	    				}
	    			mainGUI.removeAllLeftSplit();
	    			mainGUI.setLeftSplit();
    			}
    			
    		}
    		if(e.getSource() == btnInventory){
    			mainGUI.removeAllRightSplit();
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