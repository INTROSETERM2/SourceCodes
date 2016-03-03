package GUI.ControlPanel;
import javax.swing.*;


import DB.DBConnect;
import GUI.MainGUI;
import GUI.Product.AddProduct;
import GUI.Receipt.POSReceipt;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class GUIClientLandingPanel implements ActionListener{
	
	private JPanel jPanel = new JPanel();
	
	
	
	private JMenuBar jmb = new JMenuBar();

	
	private JMenu product = new JMenu("Product");   
	private JMenu report = new JMenu("Report");
	private JMenu account = new JMenu("Account");
	private JMenu branch = new JMenu("Branch");

	

	private JMenuItem addProduct = new JMenuItem("Add Staff");
	private JMenuItem removeProduct = new JMenuItem("Remove Staff");
	private JMenuItem editProduct = new JMenuItem("Edit Staff");
	
	private JMenuItem addBranch = new JMenuItem("Add Branch");
	private JMenuItem removeBranch = new JMenuItem("Remove Branch");
	private JMenuItem editBranch = new JMenuItem("Edit Branch");
	
	private JMenuItem dailyReport = new JMenuItem("Daily Report");
	private JMenuItem weeklyReport = new JMenuItem("Weekly Report");
	private JMenuItem annualReport = new JMenuItem("Annual Report");
	
	private JMenuItem editUsername = new JMenuItem("Edit Username");
	private JMenuItem editPassword = new JMenuItem("Edit Password");
	
	
	
	
	
	  	
	public GUIClientLandingPanel() {
	
		ActListener act = new ActListener();
		
		
		jPanel.setSize(600,450);
		jPanel.setLayout(null);
				
		
		jPanel.setLayout(new BorderLayout());
		jPanel.add(jmb, BorderLayout.NORTH);
		
		//for Branch tab
		branch.add(addBranch);
		branch.add(removeBranch);
		branch.add(editBranch);
		
		addBranch.addActionListener(act);
		removeBranch.addActionListener(act);
		editBranch.addActionListener(act);
		
		//for product tab
		product.add(addProduct);
		product.add(removeProduct);
		product.add(editProduct);
		
		addProduct.addActionListener(act);
		removeProduct.addActionListener(act);
		editProduct.addActionListener(act);

		//for  report tab
		report.add(dailyReport);
		report.add(weeklyReport);
		report.add(annualReport);
		
		dailyReport.addActionListener(act);
		weeklyReport.addActionListener(act);
		annualReport.addActionListener(act);
		
		//for account tab
		account.add(editPassword);
		account.add(editUsername);
		
		editPassword.addActionListener(act);
		editUsername.addActionListener(act);
		
	    jmb.add(branch);	  	
	    jmb.add(product);
	    jmb.add(report);
	    jmb.add(account);
	
	    
	}
	
	public JPanel getJPanel() {
		return jPanel;
	}
	
	
	
	private class ActListener implements ActionListener{
    	public void actionPerformed(ActionEvent e){
    		
    		if(e.getSource() == addBranch)
    		{
	    		System.out.println("vbhjb hjn");
    		}

    	}
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}