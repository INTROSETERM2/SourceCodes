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

public class AddBranchRename implements ActionListener {
	JPanel jPanel = new JPanel();
	DBConnect db = new DBConnect();

	// private JTable table = new JTable();
	private JTable table = new JTable();

	private JComboBox cmbItemName;
	private JComboBox cmbQuantity;


	// for Date
	private JButton btnPreview = new JButton("Preview");
	private JButton btnAdd = new JButton("Add");
	private JLabel lblTotalAmountComputed = new JLabel(String.valueOf(db.getTotalSalesToday()) + " php");
	
	private MainGUI mainGUI;
	private final JLabel lblBranchCreation = new JLabel("Branch Creation");
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;

	//public static ImageIcon IMAGE = null;

	public AddBranchRename(MainGUI mainGUI) {
		this.mainGUI = mainGUI;
		lblTotalAmountComputed.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTotalAmountComputed.setForeground(Color.red);
		lblTotalAmountComputed.setBackground(Color.white);
		lblTotalAmountComputed.setOpaque(true);
		setTable();
		ActListener act = new ActListener();

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
		btnAdd.setBounds(295, 311, 128, 23);

		btnAdd.addActionListener(act);


		cmbQuantity = new JComboBox();
		cmbQuantity.addActionListener(act);
		cmbItemName = new JComboBox(db.getProducts().toArray());
		cmbItemName.setBounds(7, 488, -87, -22);
		cmbItemName.addActionListener(act);
		jPanel.setLayout(null);
		lblBranchCreation.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblBranchCreation.setBounds(206, 69, 217, 53);
		
		jPanel.add(lblBranchCreation);
		jPanel.add(cmbItemName);




		


	

		jPanel.add(btnAdd);
		
		JLabel lblNameOfBranch = new JLabel("Name Of Branch");
		lblNameOfBranch.setBounds(260, 139, 122, 23);
		jPanel.add(lblNameOfBranch);
		
		textField = new JTextField();
		textField.setBounds(357, 134, 129, 32);
		jPanel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(357, 200, 129, 32);
		jPanel.add(textField_1);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(260, 205, 122, 23);
		jPanel.add(lblUsername);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(357, 245, 129, 32);
		jPanel.add(passwordField);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(260, 250, 122, 23);
		jPanel.add(lblPassword);

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

			if (a.getSource() == cmbItemName) {
				

				
			}

			if (a.getSource() == btnPreview) {
				
			}

			if (a.getSource() == btnAdd) {
				
				
			}
			}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}