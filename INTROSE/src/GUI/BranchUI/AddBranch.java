package GUI.BranchUI;

import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import GUI.MainGUI;
import GUI.ControlPanel.GUIClientUpperControlPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Branch.Branch;
import Branch.ManagerBranch;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Font;

public class AddBranch implements ActionListener {
	private JPanel jPanel = new JPanel();
	private MainGUI mainGUI;
	
	// Text Fields
	private JTextField txtBranchName = new JTextField();
	private JTextField txtUsername = new JTextField();
	private JPasswordField password = new JPasswordField();
	private JPasswordField confirmPass = new JPasswordField();
	
	// Labels
	private JLabel lblBranchCreation = new JLabel("Branch Creation");
	private JLabel lblBranchName = new JLabel("Branch Name");
	private JLabel lblUsername = new JLabel("Username: ");
	private JLabel lblPassword = new JLabel("Password:");
	private JLabel lblConfirmPass = new JLabel("Confirm Password:");
	
	// Buttons
	private JButton btnAdd = new JButton("Add");
	
	private ManagerBranch manBranch = new ManagerBranch();
	
	public AddBranch(MainGUI mainGUI){
		ActListener act = new ActListener();

		this.mainGUI = mainGUI;
		
		jPanel.setPreferredSize(new Dimension(1037, 628));
		jPanel.setLayout(new MigLayout("", "[20.00][130.00][130.00][20.00]", "[20.00][40.00][25.00][25][25][25][25][25][20]"));
		lblBranchCreation.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		
		jPanel.add(lblBranchCreation, "cell 1 1 2 1,alignx center,aligny center");
		lblBranchName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jPanel.add(lblBranchName, "cell 1 2,alignx center,aligny center");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jPanel.add(lblUsername, "cell 1 4,alignx center,aligny center");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jPanel.add(lblPassword, "cell 1 5,alignx center,aligny center");
		lblConfirmPass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jPanel.add(lblConfirmPass, "cell 1 6,alignx center,aligny center");
		
		jPanel.add(txtBranchName, "cell 2 2,grow");
		txtBranchName.setColumns(10);
		jPanel.add(txtUsername, "cell 2 4,grow");
		txtUsername.setColumns(10);
		jPanel.add(password, "cell 2 5,grow");
		jPanel.add(confirmPass, "cell 2 6,grow");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jPanel.add(btnAdd, "cell 2 7,grow");
		btnAdd.addActionListener(act);
		btnAdd.setEnabled(false);
		jPanel.setVisible(true);
		
		txtBranchName.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				changed();
			}

			public void removeUpdate(DocumentEvent e) {
				changed();
			}

			public void insertUpdate(DocumentEvent e) {
				changed();
			}
			
			@SuppressWarnings("deprecation")
			public void changed() {
				if (txtBranchName.getText().equals("") || txtUsername.getText().equals("") || password.getText().equals("") || confirmPass.getText().equals("")) {
					btnAdd.setEnabled(false);
				} else {
					btnAdd.setEnabled(true);
				}
			}
		});
		
		txtUsername.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				changed();
			}

			public void removeUpdate(DocumentEvent e) {
				changed();
			}

			public void insertUpdate(DocumentEvent e) {
				changed();
			}
			
			@SuppressWarnings("deprecation")
			public void changed() {
				if (txtBranchName.getText().equals("") || txtUsername.getText().equals("") || password.getText().equals("") || confirmPass.getText().equals("")) {
					btnAdd.setEnabled(false);
				} else {
					btnAdd.setEnabled(true);
				}
			}
			
		});
		
		password.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				changed();
			}

			public void removeUpdate(DocumentEvent e) {
				changed();
			}

			public void insertUpdate(DocumentEvent e) {
				changed();
			}
			
			@SuppressWarnings("deprecation")
			public void changed() {
				if (txtBranchName.getText().equals("") || txtUsername.getText().equals("") || password.getText().equals("") || confirmPass.getText().equals("")) {
					btnAdd.setEnabled(false);
				} else {
					btnAdd.setEnabled(true);
				}
			}
			
		});
		
		confirmPass.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				changed();
			}

			public void removeUpdate(DocumentEvent e) {
				changed();
			}

			public void insertUpdate(DocumentEvent e) {
				changed();
			}
			
			@SuppressWarnings("deprecation")
			public void changed() {
				if (txtBranchName.getText().equals("") || txtUsername.getText().equals("") || password.getText().equals("") || confirmPass.getText().equals("")) {
					btnAdd.setEnabled(false);
				} else {
					btnAdd.setEnabled(true);
				}
			}
			
		});
		
	}
	
	public JPanel getJPanel() {
		return this.jPanel;
	}

	private class ActListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
				if (a.getSource() == btnAdd) {
					boolean sameBranchName = false;
					boolean sameUserName = false;
					ArrayList<Branch> branches = new ArrayList<Branch>();
					branches = manBranch.getBranches();
					for (int i = 0; i < branches.size(); i++) {
						if(branches.get(i).getBranchName().equalsIgnoreCase(txtBranchName.getText()))
							sameBranchName = true;
						if(branches.get(i).getBranchUsername().equalsIgnoreCase(txtUsername.getText()))
							sameUserName = true;
					}
					
					if(sameBranchName == true){
						JOptionPane.showMessageDialog(null, "Branch Name is already existing!");
					}else if(sameUserName == true){
						JOptionPane.showMessageDialog(null, "Username is already existing!");
					}
					else if(!(password.getText().equals(confirmPass.getText()))){
						JOptionPane.showMessageDialog(null, "Password and Confirm Password did not match!");

					}
					else{
						ManagerBranch managerBranch = new ManagerBranch();
						managerBranch.addBranch(new Branch(txtBranchName.getText(), txtUsername.getText(), password.getText()));
						txtBranchName.setText("");
						txtUsername.setText("");
						password.setText("");
						confirmPass.setText("");
						JOptionPane.showMessageDialog(null, "New branch successfully created!");

						mainGUI.removeAllLeftSplit();
						mainGUI.setLeftSplit();
					}
				}
			}
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}