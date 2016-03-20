package GUI.Product;

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
import javax.swing.JRadioButton;
import javax.swing.DefaultComboBoxModel;

public class AddProductRename implements ActionListener {
	JPanel jPanel = new JPanel();
	DBConnect db = new DBConnect();

	// private JTable table = new JTable();
	private JTable table = new JTable();

	private JComboBox cmbItemName;
	private JComboBox cmbQuantity;


	// for Date
	private JButton btnPreview = new JButton("Preview");
	private JLabel lblTotalAmountComputed = new JLabel(String.valueOf(db.getTotalSalesToday()) + " php");
	
	private MainGUI mainGUI;
	private final JLabel lblInventory = new JLabel("Inventory");
	private JTable table_1;
	JScrollPane scrollPane_1 = new JScrollPane();
	private final JLabel label = new JLabel("Date");
	private final JTextField textField = new JTextField();
	private final JLabel label_1 = new JLabel("Product ID");
	private final JLabel label_2 = new JLabel("Product Type");
	private final JLabel label_3 = new JLabel("Quantity");
	private final JLabel label_4 = new JLabel("Buying price");
	private final JLabel label_5 = new JLabel("#####");
	private final JComboBox comboBox = new JComboBox();
	private final JTextField textField_1 = new JTextField();
	private final JTextField textField_2 = new JTextField();
	private final JButton button = new JButton("ADD");
	private final JButton btnAddImage = new JButton("Add Image");
	//public static ImageIcon IMAGE = null;

	public AddProductRename(MainGUI mainGUI) {
		this.mainGUI = mainGUI;
		lblTotalAmountComputed.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTotalAmountComputed.setForeground(Color.red);
		lblTotalAmountComputed.setBackground(Color.white);
		lblTotalAmountComputed.setOpaque(true);
		setTable();

		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setViewportView(table);
		jPanel.setPreferredSize(new Dimension(1000, 575));
		
	
	

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2) {
				
				}
			}
		});


		cmbQuantity = new JComboBox();
		//cmbQuantity.addActionListener(act);
		cmbItemName = new JComboBox(db.getProducts().toArray());
		cmbItemName.setBounds(7, 488, -87, -22);
		//cmbItemName.addActionListener(act);
		jPanel.setLayout(null);
		lblInventory.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblInventory.setBounds(57, 11, 217, 53);
		
		jPanel.add(lblInventory);
		jPanel.add(cmbItemName);

		scrollPane_1.setBounds(56, 64, 828, 352);
		jPanel.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		label.setBounds(57, 427, 86, 14);
		
		jPanel.add(label);
		textField.setColumns(10);
		textField.setBounds(57, 443, 86, 23);
		
		jPanel.add(textField);
		label_1.setBounds(57, 471, 86, 14);
		
		jPanel.add(label_1);
		label_2.setBounds(259, 471, 84, 14);
		
		jPanel.add(label_2);
		label_3.setBounds(363, 471, 66, 14);
		
		jPanel.add(label_3);
		label_4.setBounds(474, 471, 177, 14);
		
		jPanel.add(label_4);
		label_5.setBounds(57, 490, 86, 14);
		
		jPanel.add(label_5);
		comboBox.setBounds(259, 488, 84, 22);
		
		jPanel.add(comboBox);
		textField_1.setColumns(10);
		textField_1.setBounds(363, 488, 86, 23);
		
		jPanel.add(textField_1);
		textField_2.setColumns(10);
		textField_2.setBounds(474, 488, 177, 23);
		
		jPanel.add(textField_2);
		button.setBounds(474, 522, 177, 42);
		
		jPanel.add(button);
		btnAddImage.setBounds(147, 488, 89, 23);
		
		jPanel.add(btnAddImage);

		//btnAdd.setEnabled(false);
	
	}

	public JPanel getJPanel() {
		return jPanel;
	}

	public void setTable() {
		table = new JTable(db.retrieveDailySales());
		table.getTableHeader().setReorderingAllowed(false);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}