package GUI.UserAccount;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.miginfocom.swing.MigLayout;

public class Login implements ActionListener{
	JPanel jPanel = new JPanel();
	JButton buttonLogin = new JButton("Login");
	private JTextField fieldUsername;
	private JTextField fieldPassword;
	private final JLabel lblPassword = new JLabel("Password:");
	private final JLabel lblStatus = new JLabel("Status:");
	private final JLabel lblWrongPassword = new JLabel("wrong password");

	public Login() {
		ActListener act = new ActListener();
		jPanel.setLayout(new MigLayout("", "[52px][57px][][][][][][][][]", "[20px][20px][23px][][][][][][][][]"));
		jPanel.setSize(600,450);
		JLabel lblUsername = new JLabel("Username:");
		jPanel.add(lblUsername, "cell 4 7,alignx left,aligny center");
		
		fieldUsername = new JTextField();
		jPanel.add(fieldUsername, "cell 5 7,growx,aligny top");
		fieldUsername.setColumns(10);
		
		jPanel.add(lblPassword, "cell 4 8,alignx left,aligny center");
		
		fieldPassword = new JTextField();
		jPanel.add(fieldPassword, "cell 5 8,growx,aligny top");
		fieldPassword.setColumns(10);
		buttonLogin.addActionListener(act);
		jPanel.add(buttonLogin, "cell 5 9,alignx left,aligny top");
		
		jPanel.add(lblStatus, "cell 4 10");
		
		jPanel.add(lblWrongPassword, "cell 5 10");
	}
	
	public JPanel getJPanel() {
		return jPanel;
	}
	
	private class ActListener implements ActionListener
    {
    	public void actionPerformed(ActionEvent e)
    	{
    		if(e.getSource() == buttonLogin)
    		{
    			System.out.println("t(O_O)t");	
    		}
    	}
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}