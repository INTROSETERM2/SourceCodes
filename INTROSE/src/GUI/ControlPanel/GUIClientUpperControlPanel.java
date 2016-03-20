package GUI.ControlPanel;

import javax.swing.JPanel;

import GUI.MainGUI;
import GUI.BranchUI.AddBranch;
import GUI.BranchUI.AddBranchRename;
import GUI.Receipt.POSReceipt;
import GUI.ReportUI.FinancialReport;
import GUI.ReportUI.FinancialReportRename;
import GUI.UserAccount.Login;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;

public class GUIClientUpperControlPanel implements ActionListener{
	
	private JPanel jPanel = new JPanel();	
	private JButton btnAllBranches = new JButton("Yearly Report (All Branches)");	
	private JButton btnViewDailyReport = new JButton("Inventory");
	private JButton btnAddBranch = new JButton("Add Branch");
	private JButton btnDeleteBranch = new JButton("Del Branch");
	private JButton btnLogout = new JButton("Logout");		
	private ActListener act = new ActListener();
	private MainGUI mainGUI;
	private FinancialReport financialReport = new FinancialReport();
	
	private Login login = new Login(mainGUI);
	private final JScrollPane scrollPane = new JScrollPane();
	private JTable table = new JTable();

	public GUIClientUpperControlPanel(MainGUI mainGUI){
		
		this.mainGUI = mainGUI;
		jPanel.setSize(223,450);
		jPanel.setLayout(null);
		
		btnAddBranch.setBounds(7, 237, 92, 23);
		btnAddBranch.addActionListener(act);
		btnAllBranches.setBounds(7, 11, 190, 23);
		btnAllBranches.addActionListener(act);
		btnViewDailyReport.setBounds(7, 278, 190, 23);
		btnViewDailyReport.addActionListener(act);
		btnLogout.setBounds(7, 326, 190, 23);
		btnLogout.addActionListener(act);

		
		jPanel.add(btnAllBranches);
		jPanel.add(btnAddBranch );
		jPanel.add(btnViewDailyReport );
		jPanel.add(btnLogout );
		
		btnDeleteBranch.setBounds(98, 237, 99, 23);
		jPanel.add(btnDeleteBranch);
		scrollPane.setBounds(7, 62, 190, 146);
		
		jPanel.add(scrollPane);
		table.setBackground(Color.WHITE);
		 table = new JTable(new DefaultTableModel(new Object[]{"Branch Name"}, 0));
		  table.setBorder(new EmptyBorder(0, 0, 0, 0));
		  table.setBackground(UIManager.getColor("Button.background"));
		scrollPane.setViewportView(table);
		
		JButton btnManageBranch = new JButton("View Report (Select Above)");
		btnManageBranch.setBounds(7, 208, 190, 23);
		jPanel.add(btnManageBranch);
		mainGUI.removeAllRightSplit();
		System.out.println("t(O_O)t"); 
		FinancialReportRename financialReport = new FinancialReportRename(mainGUI); 
		mainGUI.setRightSplit(financialReport.getJPanel());
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
    			AddBranchRename addBranch = new AddBranchRename(mainGUI);
    			mainGUI.setRightSplit(addBranch.getJPanel());
    		}
    		if(e.getSource() == btnViewDailyReport){
    			//Kung ano mangyayari kapag pinindot button
    			System.out.println("t(O_O)t");
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