package GUI.BranchUI;

import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.MainGUI;
import GUI.ControlPanel.GUIClientUpperControlPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import javax.swing.JTextField;

import Branch.Branch;
import Branch.ManagerBranch;

import javax.swing.JButton;
import javax.swing.JPasswordField;

public class AddBranch {
	private JPanel jPanel = new JPanel();
	private MainGUI mainGUI;
	private JTextField txtBranchName = new JTextField();
	private JTextField txtUsername = new JTextField();
	private final JPasswordField passwordField = new JPasswordField();
	
	private JLabel lblBranchCreation = new JLabel("Branch Creation");
	private JLabel lblBranchName = new JLabel("Branch Name");
	private JLabel lblUsername = new JLabel("Username: ");
	private JLabel lblPassword = new JLabel("Password:");
	
	private JButton btnAdd = new JButton("Add");
	
	
	public AddBranch(){
		ActListener act = new ActListener();
		
		jPanel.setPreferredSize(new Dimension(1037, 628));
		jPanel.setLayout(new MigLayout("", "[20.00][130.00][130.00][20.00]", "[20.00][40.00][25.00][25][25][25][][20]"));
		
		
		jPanel.add(lblBranchCreation, "cell 1 1 2 1,alignx center,aligny center");
		jPanel.add(lblBranchName, "cell 1 2,alignx center,aligny center");
		jPanel.add(lblUsername, "cell 1 4,alignx center,aligny center");
		jPanel.add(lblPassword, "cell 1 5,alignx center,aligny center");
		
		jPanel.add(txtBranchName, "cell 2 2,grow");
		txtBranchName.setColumns(10);
		jPanel.add(txtUsername, "cell 2 4,grow");
		txtUsername.setColumns(10);
		
		btnAdd.addActionListener(act);
		
		jPanel.add(passwordField, "cell 2 5,grow");
		jPanel.add(btnAdd, "cell 2 6,grow");
		
	}
	
	public JPanel getJPanel() {
		return jPanel;
	}

	private class ActListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
				if (a.getSource() == btnAdd) {
					//bawal ata same branch?
					ManagerBranch managerBranch = new ManagerBranch();
					managerBranch.addBranch(new Branch(txtBranchName.getText(), txtUsername.getText(), passwordField.getText()));
					txtBranchName.setText("");
					txtUsername.setText("");
					passwordField.setText("");
					JOptionPane.showMessageDialog(null, "New branch successfully created!");
//					mainGUI.removeAllLeftSplit();
//					mainGUI.setLeftSplit();
//					refresh branches in left split help @glenn
				}
			}
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
