package GUI.UserAccount;

import javax.swing.*;

import DB.DBConnect;
import GUI.MainGUI;
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
	private POSReceipt posReceipt;
	

	public Login(MainGUI mainGUI) {
		

		this.mainGUI = mainGUI;
		posReceipt = new POSReceipt();
		ActListener act = new ActListener();
		jPanel.setLayout(new MigLayout("", "[52px][57px][][][][][][][][]", "[20px][20px][23px][][][][][][][][]"));
		jPanel.setSize(600,450);
		JLabel lblUsername = new JLabel("Username:");
		jPanel.add(lblUsername, "cell 4 7,alignx left,aligny center");
		
		txtUsername = new JTextField();
		jPanel.add(txtUsername, "cell 5 7,growx,aligny top");
		txtUsername.setColumns(10);
		
		jPanel.add(lblPassword, "cell 4 8,alignx left,aligny center");
		
		txtPassword = new JPasswordField();
		jPanel.add(txtPassword, "cell 5 8,growx,aligny top");
		txtPassword.setColumns(10);
		
		btnLogin.addActionListener(act);
		jPanel.add(btnLogin, "cell 5 9,alignx left,aligny top");
		
	}
	
	public JPanel getJPanel() {
		return jPanel;
	}
	
	private class ActListener implements ActionListener{
    	public void actionPerformed(ActionEvent e){
    		
    		if(e.getSource() == btnLogin)
    		{
	    		MainGUI.ACCOUNT_NUMBER = db.login(txtUsername.getText(), txtPassword.getText());
	    		MainGUI.RANK = db.getRank(MainGUI.ACCOUNT_NUMBER);
				if(MainGUI.ACCOUNT_NUMBER != -1)
				{
					mainGUI.setRightSplit(posReceipt.getJPanel());
					mainGUI.setLeftSplit();
					mainGUI.removeRightSplit(getJPanel());

	    			System.out.println(MainGUI.RANK);
				}
				else  
				{
					JOptionPane.showMessageDialog(null, "Wrong Username of Password");
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