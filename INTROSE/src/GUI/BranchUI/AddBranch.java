package GUI.BranchUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import DB.DBConnect;
import GUI.MainGUI;
import GUI.ControlPanel.GUIPictureControlPanel;
import Product.ManagerProduct;
import Receipt.ManagerReceipt;
import Receipt.Receipt;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class AddBranch implements ActionListener {
	JPanel jPanel = new JPanel();
	DBConnect db = new DBConnect();

	// private JTable table = new JTable();
	private JTable table = new JTable();
	private JComboBox cmbQuantity;


	// for Date
	private JButton btnPreview = new JButton("Preview");
	private JButton btnAdd = new JButton("Add");
	private JLabel lblTotalAmountComputed = new JLabel(String.valueOf(db.getTotalSalesToday()) + " php");
	
	private MainGUI mainGUI;
	private final JLabel lblBranchCreation = new JLabel("Branch Creation");
	private JTextField txtNameBranch;
	private JTextField txtUsername;
	private JPasswordField txtPassword;

	//public static ImageIcon IMAGE = null;

	public AddBranch(MainGUI mainGUI) {
		this.mainGUI = mainGUI;
		lblTotalAmountComputed.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTotalAmountComputed.setForeground(Color.red);
		lblTotalAmountComputed.setBackground(Color.white);
		lblTotalAmountComputed.setOpaque(true);
		setTable();
		//ActListener act = new ActListener();

		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setViewportView(table);
		jPanel.setPreferredSize(new Dimension(1000, 500));
		
	
	

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2) {
				
				}
			}
		});
		GridBagLayout gbl_jPanel = new GridBagLayout();
		gbl_jPanel.columnWidths = new int[]{1, 146, 200, 0, 202, 0, 0};
		gbl_jPanel.rowHeights = new int[]{69, 53, 32, 34, 32, 32, 34, 23, 154, 1, 0};
		gbl_jPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_jPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		jPanel.setLayout(gbl_jPanel);
				lblBranchCreation.setFont(new Font("Tahoma", Font.BOLD, 18));
				
				GridBagConstraints gbc_lblBranchCreation = new GridBagConstraints();
				gbc_lblBranchCreation.anchor = GridBagConstraints.SOUTHEAST;
				gbc_lblBranchCreation.insets = new Insets(0, 0, 5, 5);
				gbc_lblBranchCreation.gridx = 2;
				gbc_lblBranchCreation.gridy = 1;
				jPanel.add(lblBranchCreation, gbc_lblBranchCreation);
				
				JLabel lblNameOfBranch = new JLabel("Name Of Branch");
				GridBagConstraints gbc_lblNameOfBranch = new GridBagConstraints();
				gbc_lblNameOfBranch.anchor = GridBagConstraints.EAST;
				gbc_lblNameOfBranch.insets = new Insets(0, 0, 5, 5);
				gbc_lblNameOfBranch.gridx = 2;
				gbc_lblNameOfBranch.gridy = 2;
				jPanel.add(lblNameOfBranch, gbc_lblNameOfBranch);
				
				txtNameBranch = new JTextField();
				GridBagConstraints gbc_txtNameBranch = new GridBagConstraints();
				gbc_txtNameBranch.fill = GridBagConstraints.BOTH;
				gbc_txtNameBranch.insets = new Insets(0, 0, 5, 5);
				gbc_txtNameBranch.gridx = 4;
				gbc_txtNameBranch.gridy = 2;
				jPanel.add(txtNameBranch, gbc_txtNameBranch);
				txtNameBranch.setColumns(10);
				
				JLabel lblUsername = new JLabel("Username");
				GridBagConstraints gbc_lblUsername = new GridBagConstraints();
				gbc_lblUsername.anchor = GridBagConstraints.EAST;
				gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
				gbc_lblUsername.gridx = 2;
				gbc_lblUsername.gridy = 4;
				jPanel.add(lblUsername, gbc_lblUsername);
				
				txtUsername = new JTextField();
				txtUsername.setColumns(10);
				GridBagConstraints gbc_txtUsername = new GridBagConstraints();
				gbc_txtUsername.fill = GridBagConstraints.BOTH;
				gbc_txtUsername.insets = new Insets(0, 0, 5, 5);
				gbc_txtUsername.gridx = 4;
				gbc_txtUsername.gridy = 4;
				jPanel.add(txtUsername, gbc_txtUsername);
				
				JLabel lblPassword = new JLabel("Password");
				GridBagConstraints gbc_lblPassword = new GridBagConstraints();
				gbc_lblPassword.anchor = GridBagConstraints.EAST;
				gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
				gbc_lblPassword.gridx = 2;
				gbc_lblPassword.gridy = 5;
				jPanel.add(lblPassword, gbc_lblPassword);
				
				txtPassword = new JPasswordField();
				GridBagConstraints gbc_txtPassword = new GridBagConstraints();
				gbc_txtPassword.fill = GridBagConstraints.BOTH;
				gbc_txtPassword.insets = new Insets(0, 0, 5, 5);
				gbc_txtPassword.gridx = 4;
				gbc_txtPassword.gridy = 5;
				jPanel.add(txtPassword, gbc_txtPassword);
				
				
				
				
						
				
				
					
				
						GridBagConstraints gbc_btnAdd = new GridBagConstraints();
						gbc_btnAdd.fill = GridBagConstraints.HORIZONTAL;
						gbc_btnAdd.anchor = GridBagConstraints.NORTH;
						gbc_btnAdd.insets = new Insets(0, 0, 5, 5);
						gbc_btnAdd.gridx = 4;
						gbc_btnAdd.gridy = 7;
						jPanel.add(btnAdd, gbc_btnAdd);

		//btnAdd.setEnabled(false);
	
	}

	public JPanel getJPanel() {
		return jPanel;
	}

	public void setTable() {
		table = new JTable(db.retrieveDailySales());
		table.getTableHeader().setReorderingAllowed(false);

	}

	private class ActListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			if (a.getSource() == btnAdd) {
				
				
			}
			}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}