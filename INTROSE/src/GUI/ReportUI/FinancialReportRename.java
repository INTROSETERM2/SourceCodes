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
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

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

public class FinancialReportRename implements ActionListener {
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
	private final JLabel lblYearlyReport = new JLabel("Yearly Report of All Branches");
	private JScrollPane scrollPane_1;
	private JTable table_1;

	//public static ImageIcon IMAGE = null;

	public FinancialReportRename(MainGUI mainGUI) {
		this.mainGUI = mainGUI;
		lblTotalAmountComputed.setFont(new Font("Tahoma", Font.PLAIN, 20));

		lblTotalAmountComputed.setForeground(Color.red);
		lblTotalAmountComputed.setBackground(Color.white);
		lblTotalAmountComputed.setOpaque(true);
		setTable();
		ActListener act = new ActListener();

		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setViewportView(table);
		jPanel.setPreferredSize(new Dimension(1000, 778));
		
	

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2) {
				
				}
			}
		});
		btnAdd.setBounds(475, 345, -6, 2);

		btnAdd.addActionListener(act);


		cmbQuantity = new JComboBox();
		cmbQuantity.addActionListener(act);
		cmbItemName = new JComboBox(db.getProducts().toArray());
		cmbItemName.setBounds(7, 488, -87, -22);
		cmbItemName.addActionListener(act);
		jPanel.setLayout(null);
		lblYearlyReport.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblYearlyReport.setBounds(33, 27, 280, 53);
		
		jPanel.add(lblYearlyReport);
		jPanel.add(cmbItemName);




		


	

		jPanel.add(btnAdd);
		
		JLabel lblTotalCapital = new JLabel("Total Capital:");
		lblTotalCapital.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTotalCapital.setBounds(33, 585, 122, 23);
		jPanel.add(lblTotalCapital);
		
		JLabel TotalNet = new JLabel("Total Net:");
		TotalNet.setFont(new Font("Tahoma", Font.BOLD, 16));
		TotalNet.setBounds(33, 619, 122, 23);
		jPanel.add(TotalNet);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(33, 79, 957, 495);
		jPanel.add(scrollPane_1);
		
		

		  table_1 = new JTable(new DefaultTableModel(new Object[]{"Period Covered","Branch1", "Branch2","Gross","Net"}, 0));
		  table_1.setBorder(new EmptyBorder(0, 0, 0, 0));
		  table_1.setBackground(UIManager.getColor("Button.background"));
		scrollPane_1.setViewportView(table_1);

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


			
			}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}