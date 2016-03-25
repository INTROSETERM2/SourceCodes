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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Branch.Branch;
import Branch.ManagerBranch;

import javax.swing.JButton;
import javax.swing.JPasswordField;

public class AddBranch {
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
	private final JLabel lblConfirmPass = new JLabel("Confirm Password:");
	
	// Buttons
	private JButton btnAdd = new JButton("Add");
	
	public AddBranch(MainGUI mainGUI){
		ActListener act = new ActListener();

		this.mainGUI = mainGUI;
		
		jPanel.setPreferredSize(new Dimension(1037, 628));
		jPanel.setLayout(new MigLayout("", "[20.00][130.00][130.00][20.00]", "[20.00][40.00][25.00][25][25][25][25][25][20]"));
		
		
		jPanel.add(lblBranchCreation, "cell 1 1 2 1,alignx center,aligny center");
		jPanel.add(lblBranchName, "cell 1 2,alignx center,aligny center");
		jPanel.add(lblUsername, "cell 1 4,alignx center,aligny center");
		jPanel.add(lblPassword, "cell 1 5,alignx center,aligny center");
		jPanel.add(lblConfirmPass, "cell 1 6,alignx center,aligny center");
		
		jPanel.add(txtBranchName, "cell 2 2,grow");
		txtBranchName.setColumns(10);
		jPanel.add(txtUsername, "cell 2 4,grow");
		txtUsername.setColumns(10);
		jPanel.add(password, "cell 2 5,grow");
		jPanel.add(confirmPass, "cell 2 6,grow");
		jPanel.add(btnAdd, "cell 2 7,grow");
		btnAdd.addActionListener(act);
		btnAdd.setEnabled(false);
		
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
		return jPanel;
	}

	private class ActListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
				if (a.getSource() == btnAdd) {
					//bawal ata same branch?
					if(!(password.getText().equals(confirmPass.getText()))){
						JOptionPane.showMessageDialog(null, "Password and Confirm Password did not macth!");
						password.setText("");
						confirmPass.setText("");
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

//						refresh branches in left split help @glenn
					}
				}
			}
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
