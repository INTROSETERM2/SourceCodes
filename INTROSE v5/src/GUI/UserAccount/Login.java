package GUI.UserAccount;

import javax.swing.*;

import DB.DBConnect;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.miginfocom.swing.MigLayout;

public class Login implements ActionListener{
	
	JPanel jPanel = new JPanel();
	private JButton btnLogin = new JButton("Login");
	private JTextField txtUsername = new JTextField();
	private JTextField txtPassword = new JTextField();
	private final JLabel lblPassword = new JLabel("Password:");
	private final JLabel lblStatus = new JLabel("Status:");
	private final JLabel lblWrongPassword = new JLabel("wrong password");

	public Login() {
		ActListener act = new ActListener();
		jPanel.setLayout(new MigLayout("", "[52px][57px][][][][][][][][]", "[20px][20px][23px][][][][][][][][]"));
		jPanel.setSize(600,450);
		JLabel lblUsername = new JLabel("Username:");
		jPanel.add(lblUsername, "cell 4 7,alignx left,aligny center");
		
		jPanel.add(txtUsername, "cell 5 7,growx,aligny top");
		txtUsername.setColumns(10);
		
		jPanel.add(lblPassword, "cell 4 8,alignx left,aligny center");
		
		jPanel.add(txtPassword, "cell 5 8,growx,aligny top");
		txtPassword.setColumns(10);
		
		btnLogin.addActionListener(act);
		jPanel.add(btnLogin, "cell 5 9,alignx left,aligny top");
		
		jPanel.add(lblStatus, "cell 4 10");
		
		jPanel.add(lblWrongPassword, "cell 5 10");
	}
	
	public JPanel getJPanel() {
		return jPanel;
	}
	
	private class ActListener implements ActionListener{
    	public void actionPerformed(ActionEvent e){
    		if(e.getSource() == btnLogin){
    			
    			DBConnect db = new DBConnect();
    			int check = db.login(txtUsername.getText(), txtPassword.getText());
    			//Kung ano mangyayari kapag pinindot button
    			System.out.println("t(O_O)t");	
    		}
    	}
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}