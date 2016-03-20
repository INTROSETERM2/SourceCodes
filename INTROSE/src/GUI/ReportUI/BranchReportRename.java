package GUI.ReportUI;

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

public class BranchReportRename implements ActionListener {
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
	private final JLabel lblBranchName = new JLabel("Branch Name");
	private JTable table_1;
	
	JRadioButton rdbtnDaily = new JRadioButton("Daily");
	JRadioButton rdbtnMonthly = new JRadioButton("Monthly");
	JComboBox comboBoxMonthly = new JComboBox();
	JComboBox comboBoxYearly = new JComboBox();
	JLabel lblMonth = new JLabel("Month");
	JLabel lblYear = new JLabel("Year");
	JScrollPane scrollPane_1 = new JScrollPane();
	private final JLabel lblTotalSales = new JLabel("Total Sales:");
	//public static ImageIcon IMAGE = null;

	public BranchReportRename(MainGUI mainGUI) {
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
		lblBranchName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblBranchName.setBounds(57, 11, 217, 53);
		
		jPanel.add(lblBranchName);
		jPanel.add(cmbItemName);
		
		
		rdbtnDaily.setBounds(56, 66, 63, 23);
		jPanel.add(rdbtnDaily);
		
		rdbtnMonthly.setBounds(117, 66, 73, 23);
		jPanel.add(rdbtnMonthly);

		comboBoxMonthly.setModel(new DefaultComboBoxModel(new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));
		comboBoxMonthly.setBounds(96, 106, 85, 20);
		jPanel.add(comboBoxMonthly);

		comboBoxYearly.setBounds(220, 106, 73, 20);
		jPanel.add(comboBoxYearly);

		lblMonth.setBounds(57, 106, 63, 20);
		jPanel.add(lblMonth);

		lblYear.setBounds(191, 109, 63, 17);
		jPanel.add(lblYear);

		scrollPane_1.setBounds(56, 150, 828, 371);
		jPanel.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		lblTotalSales.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTotalSales.setBounds(57, 530, 122, 23);
		
		jPanel.add(lblTotalSales);

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