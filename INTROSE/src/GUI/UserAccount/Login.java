package GUI.UserAccount;

import javax.swing.*;

import Branch.Branch;
import DB.DBConnect;
import GUI.MainGUI;
import GUI.ControlPanel.GUIClientLandingPanel;
import GUI.Product.AddProduct;
import GUI.Receipt.POSReceipt;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.miginfocom.swing.MigLayout;

public class Login implements ActionListener{
	
	JPanel jPanel = new JPanel();
	private JButton btnLogin = new JButton("Login");
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private final JLabel lblPassword = new JLabel("Password:");	
	
	private DBConnect db = new DBConnect();
	private MainGUI mainGUI;

	public Login(MainGUI mainGUI) {
		

		this.mainGUI = mainGUI;
		ActListener act = new ActListener();
		jPanel.setLayout(new MigLayout("", "[52px][57px][][][][][][][][]", "[20px][20px][23px][][][][][][][][]"));
		jPanel.setSize(600,450);
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jPanel.add(lblUsername, "cell 4 7,alignx left,aligny center");
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jPanel.add(txtUsername, "cell 5 7,growx,aligny top");
		txtUsername.setColumns(10);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		jPanel.add(lblPassword, "cell 4 8,alignx left,aligny center");
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jPanel.add(txtPassword, "cell 5 8,growx,aligny top");
		txtPassword.setColumns(10);
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		btnLogin.addActionListener(act);
		jPanel.add(btnLogin, "cell 5 9,growx,aligny top");
		
		
	}
	
	public JPanel getJPanel() {
		return jPanel;
	}
	
	private class ActListener implements ActionListener{
    	public void actionPerformed(ActionEvent e){
    		
    		if(e.getSource() == btnLogin)
    		{
	    		MainGUI.BRANCH = db.login(txtUsername.getText(), txtPassword.getText());
	   

	    		System.out.println("ID:" + MainGUI.BRANCH.getBranchID());
	    		if(MainGUI.BRANCH.getBranchID() != -1)
				{
	    			if(MainGUI.BRANCH.getBranchID() == 0)//si client eto
	    			{
	    				GUIClientLandingPanel guiClientLandingPanel = new GUIClientLandingPanel();
	    				mainGUI.setRightSplit(guiClientLandingPanel.getJPanel());
						mainGUI.removeRightSplit(getJPanel());
						mainGUI.setLeftSplit();
						

	    			}
	    			
	    			else
	    			{
	    				
					POSReceipt posReceipt = new POSReceipt(mainGUI);				

					mainGUI.setRightSplit(posReceipt.getJPanel());
					mainGUI.setLeftSplit();
					//mainGUI.removeRightSplit(getJPanel());
					mainGUI.removeAllRightSplit();

					mainGUI.setRightSplit(posReceipt.getJPanel());

			
	    			}
				}
				else  
				{
					JOptionPane.showMessageDialog(null, "Wrong Username or Password");
					txtPassword.setText("");
					txtUsername.setText("");
				}
    		}

    	}
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}