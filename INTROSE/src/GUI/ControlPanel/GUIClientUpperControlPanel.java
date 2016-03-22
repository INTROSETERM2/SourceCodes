package GUI.ControlPanel;

import javax.swing.JPanel;

import GUI.MainGUI;
import GUI.BranchUI.AddBranch;
import GUI.BranchUI.AddBranch;
import GUI.Product.AddProductRename;
import GUI.Receipt.POSReceipt;
import GUI.ReportUI.BranchReportRename;
import GUI.ReportUI.FinancialReport;
import GUI.ReportUI.FinancialReportRename;
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
	
	private JPanel jPanel = new JPanel();	
	private JButton btnAllBranches = new JButton("Yearly Report (All Branches)");	
	private JButton btnInventory = new JButton("Inventory");
	private JButton btnAddBranch = new JButton("Add Branch");
	private JButton btnDeleteBranch = new JButton("Del Branch");
	private JButton btnLogout = new JButton("Logout");		
	private ActListener act = new ActListener();
	private MainGUI mainGUI;
	private FinancialReport financialReport = new FinancialReport();
	
	private Login login = new Login(mainGUI);
	private final JScrollPane scrollPane = new JScrollPane();
	private JTable table = new JTable();
	private ManagerBranch manBranch = new ManagerBranch();

	public GUIClientUpperControlPanel(MainGUI mainGUI){
		
		this.mainGUI = mainGUI;
		jPanel.setSize(223,450);
		jPanel.setLayout(null);
		
		btnAddBranch.setBounds(7, 237, 92, 23);
		btnAddBranch.addActionListener(act);
		btnAllBranches.setBounds(7, 11, 190, 23);
		btnAllBranches.addActionListener(act);
		btnInventory.setBounds(7, 278, 190, 23);
		btnInventory.addActionListener(act);
		btnLogout.setBounds(7, 326, 190, 23);
		btnLogout.addActionListener(act);

		
		jPanel.add(btnAllBranches);
		jPanel.add(btnAddBranch );
		jPanel.add(btnInventory );
		jPanel.add(btnLogout );
		
		btnDeleteBranch.setBounds(98, 237, 99, 23);
		jPanel.add(btnDeleteBranch);
		scrollPane.setBounds(7, 62, 190, 146);
		
		jPanel.add(scrollPane);
		table.setBackground(Color.WHITE);
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
		table.setBorder(new EmptyBorder(0, 0, 0, 0));
		table.setBackground(UIManager.getColor("Button.background"));
		table.setModel(model);
		scrollPane.setViewportView(table);
		mainGUI.removeAllRightSplit();
		System.out.println("t(O_O)t"); 
		FinancialReportRename financialReport = new FinancialReportRename(mainGUI); 
		mainGUI.setRightSplit(financialReport.getJPanel());
		
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent m) {
            	if (m.getClickCount() == 2) {
            		
        			mainGUI.removeAllRightSplit();
        			System.out.println("t(O_O)t"); 
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
    			//Kung ano mangyayari kapag pinindot button
    			mainGUI.removeAllRightSplit();
    			System.out.println("t(O_O)t"); 
    			FinancialReportRename financialReport = new FinancialReportRename(mainGUI); 
    			mainGUI.setRightSplit(financialReport.getJPanel());
    		}
    		if(e.getSource() == btnAddBranch){
    			mainGUI.removeAllRightSplit();
    			System.out.println("t(O_O)t");
    			AddBranch addBranch = new AddBranch(mainGUI);
    			mainGUI.setRightSplit(addBranch.getJPanel());
    		}
    		if(e.getSource() == btnInventory){
    			//Kung ano mangyayari kapag pinindot button
    			mainGUI.removeAllRightSplit();
    			System.out.println("t(O_O)t"); 
    			AddProductRename addProduct = new AddProductRename(mainGUI); 
    			mainGUI.setRightSplit(addProduct.getJPanel());
    		}
    		if(e.getSource() == btnLogout){
    			//Kung ano mangyayari kapag pinindot button
    			
    			
    			MainGUI.BRANCH.setBranchID(-1);
//
//    			mainGUI.removeAllRightSplit();
//    			mainGUI.setRightSplit(login.getJPanel());
//				
//    			
//    			mainGUI.removeAllLeftSplit();
//    			
//    			
//    			System.out.println("t(O_O)t");	
    			
    			mainGUI.logout();
    		}
    	}
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}