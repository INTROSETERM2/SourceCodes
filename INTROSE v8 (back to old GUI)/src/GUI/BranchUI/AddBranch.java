package GUI.BranchUI;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;

public class AddBranch implements ActionListener{
	
	JPanel jPanel = new JPanel();
	private JTextField txtBranch = new JTextField();
	private JTextField txtUsername = new JTextField();
	private JTextField txtPassword = new JTextField();
	JLabel lblBranchCreation = new JLabel("Branch Creation");
	JLabel lblNameOfBranch = new JLabel("Name of Branch");
	JLabel lblUsername = new JLabel("Username");
	JLabel lblPassword = new JLabel("Password");
	JButton btnEnter = new JButton("ENTER");
	
	public AddBranch(){
		
		ActListener act = new ActListener();
		
		jPanel.setSize(600,450);
		GridBagLayout gbl_jPanel = new GridBagLayout();
		gbl_jPanel.columnWidths = new int[]{30, 106, 145, 76, 89, 0, 0};
		gbl_jPanel.rowHeights = new int[]{30, 30, 30, 30, 30, 30, 30, 30, 30, 0, 0, 0, 0, 30};
		gbl_jPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_jPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		jPanel.setLayout(gbl_jPanel);
		
		lblBranchCreation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblBranchCreation = new GridBagConstraints();
		gbc_lblBranchCreation.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblBranchCreation.insets = new Insets(0, 0, 5, 5);
		gbc_lblBranchCreation.gridx = 1;
		gbc_lblBranchCreation.gridy = 1;
		jPanel.add(lblBranchCreation, gbc_lblBranchCreation);
		
		GridBagConstraints gbc_lblNameOfBranch = new GridBagConstraints();
		gbc_lblNameOfBranch.anchor = GridBagConstraints.EAST;
		gbc_lblNameOfBranch.insets = new Insets(0, 0, 5, 5);
		gbc_lblNameOfBranch.gridx = 2;
		gbc_lblNameOfBranch.gridy = 3;
		jPanel.add(lblNameOfBranch, gbc_lblNameOfBranch);
		
		GridBagConstraints gbc_txtBranch = new GridBagConstraints();
		gbc_txtBranch.anchor = GridBagConstraints.NORTH;
		gbc_txtBranch.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBranch.insets = new Insets(0, 0, 5, 5);
		gbc_txtBranch.gridx = 3;
		gbc_txtBranch.gridy = 3;
		jPanel.add(txtBranch, gbc_txtBranch);
		txtBranch.setColumns(10);
		
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.anchor = GridBagConstraints.EAST;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 2;
		gbc_lblUsername.gridy = 5;
		jPanel.add(lblUsername, gbc_lblUsername);
		
		GridBagConstraints gbc_txtUsername = new GridBagConstraints();
		gbc_txtUsername.anchor = GridBagConstraints.NORTH;
		gbc_txtUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUsername.insets = new Insets(0, 0, 5, 5);
		gbc_txtUsername.gridx = 3;
		gbc_txtUsername.gridy = 5;
		jPanel.add(txtUsername, gbc_txtUsername);
		txtUsername.setColumns(10);
		
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 2;
		gbc_lblPassword.gridy = 6;
		jPanel.add(lblPassword, gbc_lblPassword);
		
		GridBagConstraints gbc_txtPassword = new GridBagConstraints();
		gbc_txtPassword.anchor = GridBagConstraints.NORTH;
		gbc_txtPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPassword.insets = new Insets(0, 0, 5, 5);
		gbc_txtPassword.gridx = 3;
		gbc_txtPassword.gridy = 6;
		jPanel.add(txtPassword, gbc_txtPassword);
		txtPassword.setColumns(10);
		
		GridBagConstraints gbc_btnEnter = new GridBagConstraints();
		gbc_btnEnter.insets = new Insets(0, 0, 5, 5);
		gbc_btnEnter.gridx = 4;
		gbc_btnEnter.gridy = 10;
		btnEnter.addActionListener(act);
		jPanel.add(btnEnter, gbc_btnEnter);
	}
	
	
	public JPanel getJPanel() {
		return jPanel;
	}
	
	private class ActListener implements ActionListener{
    	public void actionPerformed(ActionEvent e){
    		if(e.getSource() == btnEnter){
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
