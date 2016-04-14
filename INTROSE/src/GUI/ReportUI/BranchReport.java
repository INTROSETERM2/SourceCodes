package GUI.ReportUI;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import Branch.Branch;
import Branch.ManagerBranch;
import DB.DBConnect;
import GUI.MainGUI;
import GUI.Receipt.EditReceipt;
import GUI.Receipt.EditReceiptDailySales;
import GUI.Receipt.POSReceipt;
import Receipt.ManagerReceipt;
import Receipt.Receipt;
import javax.swing.JTextField;
import java.awt.Cursor;

public class BranchReport {
	// Frames
	JFrame calendarFrame;

	// Panels
	private JPanel mainPanel = new JPanel();
	private JPanel dailyPanel = new JPanel();
	private JPanel monthlyPanel = new JPanel();
	private JPanel datePickPanel = new JPanel();

	// Scroll Panes
	private JScrollPane dailyScroll = new JScrollPane();
	private JScrollPane monthlyScroll = new JScrollPane();

	// Labels
	private JLabel lblBranchD = new JLabel("Branch");
	private JLabel lblTotalSalesDisplayD = new JLabel("Total Sales: ");
	private JLabel lblTotalSalesD = new JLabel("");

	private JLabel lblBranchM = new JLabel("Branch");
	private JLabel lblTotalSalesDisplayM = new JLabel("Total Sales: ");
	private JLabel lblTotalSalesM = new JLabel("");

	private JLabel lblMonth = new JLabel("Month");
	private JLabel lblYear = new JLabel("Year");
	private JLabel lblDay = new JLabel("Day");

	// Text Fields
	private JTextField txtDay = new JTextField();

	// Buttons
	private JButton btnPrevMonth = new JButton("<");
	private JButton btnNextMonth = new JButton(">");
	private JButton btnPrevYear = new JButton("<");
	private JButton btnNextYear = new JButton(">");
	private JButton btnPickDate = new JButton("Pick Date");
	private JButton btnSet = new JButton("Set");
	private JButton btnGenerate = new JButton("Generate Report");

	// Combo Boxes
	private JComboBox cmbMonth = new JComboBox();
	private JComboBox cmbYear = new JComboBox();

	// Tables
	private JTable monthlyTable;
	private JTable dailyTable;

	// Date Picker
	JXDatePicker datePicker = new JXDatePicker();

	private ActListener act = new ActListener();
	private ManagerReceipt manRec = new ManagerReceipt();
	private ManagerBranch manBra = new ManagerBranch();
	private double total = 0;
	private int branchNumber;
	private Date dateFrom;
	private String resultDateFrom;
	private MainGUI mainGUI;

	public BranchReport(MainGUI mainGUI, int branchNumber) {
		this.mainGUI = mainGUI;
		txtDay.setColumns(10);
		this.branchNumber = branchNumber;
		ArrayList<Branch> branches = new ArrayList<Branch>();
		branches = manBra.getBranches();
		String branch = "";
		for (int i = 0; i < branches.size(); i++)
			if (branches.get(i).getBranchID() == branchNumber) {
				branch = branches.get(i).getBranchName();
			}

		lblBranchD.setText(branch);
		lblBranchM.setText(branch);

		lblTotalSalesD.setText("");
		mainPanel.setSize(1040, 609);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[] { 0, 0 };
		gbl_mainPanel.rowHeights = new int[] { 0, 0 };
		gbl_mainPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_mainPanel.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		mainPanel.setLayout(gbl_mainPanel);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		mainPanel.add(tabbedPane, gbc_tabbedPane);

		tabbedPane.add(
				"<html><body><table width='50' height='5'><tr><td><center>Daily</center></td></tr></table></body></html>",
				dailyPanel);
		GridBagLayout gbl_dailyPanel = new GridBagLayout();
		gbl_dailyPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 510, 150, 150, 0, 0 };
		gbl_dailyPanel.rowHeights = new int[] { 0, 35, 0, 0, 0, 0, 0 };
		gbl_dailyPanel.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_dailyPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		dailyPanel.setLayout(gbl_dailyPanel);

		lblBranchD.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_lblBranch = new GridBagConstraints();
		gbc_lblBranch.gridwidth = 5;
		gbc_lblBranch.anchor = GridBagConstraints.WEST;
		gbc_lblBranch.insets = new Insets(0, 0, 5, 5);
		gbc_lblBranch.gridx = 1;
		gbc_lblBranch.gridy = 1;
		dailyPanel.add(lblBranchD, gbc_lblBranch);

		lblTotalSalesDisplayD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblTotalSalesDisplay = new GridBagConstraints();
		gbc_lblTotalSalesDisplay.anchor = GridBagConstraints.EAST;
		gbc_lblTotalSalesDisplay.fill = GridBagConstraints.VERTICAL;
		gbc_lblTotalSalesDisplay.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotalSalesDisplay.gridx = 6;
		gbc_lblTotalSalesDisplay.gridy = 1;
		dailyPanel.add(lblTotalSalesDisplayD, gbc_lblTotalSalesDisplay);

		GridBagConstraints gbc_lblTotalSales = new GridBagConstraints();
		gbc_lblTotalSales.anchor = GridBagConstraints.WEST;
		gbc_lblTotalSales.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotalSales.gridx = 7;
		gbc_lblTotalSales.gridy = 1;
		dailyPanel.add(lblTotalSalesD, gbc_lblTotalSales);

		GridBagConstraints gbc_lblDay = new GridBagConstraints();
		gbc_lblDay.insets = new Insets(0, 0, 5, 5);
		gbc_lblDay.gridx = 2;
		gbc_lblDay.gridy = 2;
		dailyPanel.add(lblDay, gbc_lblDay);

		GridBagConstraints gbc_txtDay = new GridBagConstraints();
		gbc_txtDay.insets = new Insets(0, 0, 5, 5);
		gbc_txtDay.fill = GridBagConstraints.BOTH;
		gbc_txtDay.gridx = 2;
		gbc_txtDay.gridy = 3;
		dailyPanel.add(txtDay, gbc_txtDay);

		GridBagConstraints gbc_btnPickdate = new GridBagConstraints();
		gbc_btnPickdate.insets = new Insets(0, 0, 5, 5);
		gbc_btnPickdate.gridx = 4;
		gbc_btnPickdate.gridy = 3;

		GridBagConstraints gbc_btnPickDate = new GridBagConstraints();
		gbc_btnPickDate.insets = new Insets(0, 0, 5, 5);
		gbc_btnPickDate.gridx = 4;
		gbc_btnPickDate.gridy = 3;
		dailyPanel.add(btnPickDate, gbc_btnPickDate);

		GridBagConstraints gbc_dailyScroll = new GridBagConstraints();
		gbc_dailyScroll.gridwidth = 7;
		gbc_dailyScroll.insets = new Insets(0, 0, 5, 5);
		gbc_dailyScroll.fill = GridBagConstraints.BOTH;
		gbc_dailyScroll.gridx = 1;
		gbc_dailyScroll.gridy = 4;
		dailyPanel.add(dailyScroll, gbc_dailyScroll);

		tabbedPane.add(monthlyPanel);
		tabbedPane.add(
				"<html><body><table width='50' height='5'><tr><td><center>Monthly</center></td></tr></table></body></html>",
				monthlyPanel);
		GridBagLayout gbl_monthlyPanel = new GridBagLayout();
		gbl_monthlyPanel.columnWidths = new int[] { 0, 10, 70, 10, 10, 70, 1, 265, 150, 150, 0, 0 };
		gbl_monthlyPanel.rowHeights = new int[] { 0, 35, 0, 0, 0, 0, 0 };
		gbl_monthlyPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_monthlyPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		monthlyPanel.setLayout(gbl_monthlyPanel);

		lblBranchM.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_lblBranchM = new GridBagConstraints();
		gbc_lblBranchM.gridwidth = 7;
		gbc_lblBranchM.anchor = GridBagConstraints.WEST;
		gbc_lblBranchM.insets = new Insets(0, 0, 5, 5);
		gbc_lblBranchM.gridx = 1;
		gbc_lblBranchM.gridy = 1;
		monthlyPanel.add(lblBranchM, gbc_lblBranchM);

		lblTotalSalesDisplayM.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblTotalSalesDisplayM = new GridBagConstraints();
		gbc_lblTotalSalesDisplayM.anchor = GridBagConstraints.EAST;
		gbc_lblTotalSalesDisplayM.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotalSalesDisplayM.gridx = 8;
		gbc_lblTotalSalesDisplayM.gridy = 1;
		monthlyPanel.add(lblTotalSalesDisplayM, gbc_lblTotalSalesDisplayM);

		GridBagConstraints gbc_lblTotalSalesM = new GridBagConstraints();
		gbc_lblTotalSalesM.anchor = GridBagConstraints.WEST;
		gbc_lblTotalSalesM.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotalSalesM.gridx = 9;
		gbc_lblTotalSalesM.gridy = 1;
		monthlyPanel.add(lblTotalSalesM, gbc_lblTotalSalesM);

		GridBagConstraints gbc_lblMonth = new GridBagConstraints();
		gbc_lblMonth.insets = new Insets(0, 0, 5, 5);
		gbc_lblMonth.gridx = 2;
		gbc_lblMonth.gridy = 2;
		monthlyPanel.add(lblMonth, gbc_lblMonth);

		GridBagConstraints gbc_lblYear = new GridBagConstraints();
		gbc_lblYear.insets = new Insets(0, 0, 5, 5);
		gbc_lblYear.gridx = 5;
		gbc_lblYear.gridy = 2;
		monthlyPanel.add(lblYear, gbc_lblYear);

		GridBagConstraints gbc_btnPrevMonth = new GridBagConstraints();
		gbc_btnPrevMonth.fill = GridBagConstraints.VERTICAL;
		gbc_btnPrevMonth.insets = new Insets(0, 0, 5, 5);
		gbc_btnPrevMonth.gridx = 1;
		gbc_btnPrevMonth.gridy = 3;
		monthlyPanel.add(btnPrevMonth, gbc_btnPrevMonth);

		GridBagConstraints gbc_cmbMonth = new GridBagConstraints();
		gbc_cmbMonth.insets = new Insets(0, 0, 5, 5);
		gbc_cmbMonth.fill = GridBagConstraints.VERTICAL;
		gbc_cmbMonth.gridx = 2;
		gbc_cmbMonth.gridy = 3;
		monthlyPanel.add(cmbMonth, gbc_cmbMonth);

		GridBagConstraints gbc_btnNextMonth = new GridBagConstraints();
		gbc_btnNextMonth.fill = GridBagConstraints.VERTICAL;
		gbc_btnNextMonth.insets = new Insets(0, 0, 5, 5);
		gbc_btnNextMonth.gridx = 3;
		gbc_btnNextMonth.gridy = 3;
		monthlyPanel.add(btnNextMonth, gbc_btnNextMonth);

		GridBagConstraints gbc_btnPrevYear = new GridBagConstraints();
		gbc_btnPrevYear.fill = GridBagConstraints.VERTICAL;
		gbc_btnPrevYear.insets = new Insets(0, 0, 5, 5);
		gbc_btnPrevYear.gridx = 4;
		gbc_btnPrevYear.gridy = 3;
		monthlyPanel.add(btnPrevYear, gbc_btnPrevYear);

		GridBagConstraints gbc_cmbYear = new GridBagConstraints();
		gbc_cmbYear.insets = new Insets(0, 0, 5, 5);
		gbc_cmbYear.fill = GridBagConstraints.VERTICAL;
		gbc_cmbYear.gridx = 5;
		gbc_cmbYear.gridy = 3;
		cmbYear.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		monthlyPanel.add(cmbYear, gbc_cmbYear);

		GridBagConstraints gbc_btnNextYear = new GridBagConstraints();
		gbc_btnNextYear.fill = GridBagConstraints.VERTICAL;
		gbc_btnNextYear.insets = new Insets(0, 0, 5, 5);
		gbc_btnNextYear.gridx = 6;
		gbc_btnNextYear.gridy = 3;
		monthlyPanel.add(btnNextYear, gbc_btnNextYear);

		GridBagConstraints gbc_btnGenerate = new GridBagConstraints();
		gbc_btnGenerate.anchor = GridBagConstraints.WEST;
		gbc_btnGenerate.insets = new Insets(0, 0, 5, 5);
		gbc_btnGenerate.gridx = 7;
		gbc_btnGenerate.gridy = 3;
		monthlyPanel.add(btnGenerate, gbc_btnGenerate);

		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 9;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 4;
		monthlyPanel.add(monthlyScroll, gbc_scrollPane_1);

		AutoCompleteDecorator.decorate(cmbMonth);
		AutoCompleteDecorator.decorate(cmbYear);

		// Action Listeners
		btnNextMonth.addActionListener(act);
		btnPrevMonth.addActionListener(act);
		btnNextYear.addActionListener(act);
		btnPrevYear.addActionListener(act);
		btnPickDate.addActionListener(act);
		btnSet.addActionListener(act);
		cmbMonth.addActionListener(act);
		cmbYear.addActionListener(act);
		btnGenerate.addActionListener(act);

		for (int i = 0; i < 12; i++)
			cmbMonth.addItem(i + 1);

		cmbMonth.setSelectedIndex(0);

		for (int i = manRec.getEarlyYear(branchNumber); i < manRec.getLatestYear(branchNumber) + 1; i++)
			cmbYear.addItem(i);

		cmbYear.setSelectedIndex(0);

		if (cmbYear.getItemCount() == 1) {
			btnNextYear.setEnabled(false);
			btnPrevYear.setEnabled(false);
		}

		monthlyTable = new JTable();
		setMonthlyTable();

		// Get Today
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();

		txtDay.setEditable(false);
		txtDay.setText(dateFormat.format(date));

		dailyTable = new JTable();

		// Branch Report (Daily) start
		DefaultTableModel dailyModel = new DefaultTableModel(
				new Object[] { "Date", "Receipt Number", "Product", "Price", "Quantity", "Customer", "Staff" }, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		ArrayList<Receipt> receiptDaily = manRec.getDayReceipt(branchNumber, dateFormat.format(date));

		for (int i = 0; i < receiptDaily.size(); i++) {
			dailyModel.addRow(new Object[] { receiptDaily.get(i).getSoldDate(), receiptDaily.get(i).getReceiptID(),
					receiptDaily.get(i).getSoldProductName(), receiptDaily.get(i).getSoldPrice(),
					receiptDaily.get(i).getSoldQuantity(), receiptDaily.get(i).getCustomerName(),
					receiptDaily.get(i).getStaffName() });
			total += receiptDaily.get(i).getSoldPrice();
		}

		DecimalFormat df = new DecimalFormat("#.00");
		lblTotalSalesD.setText(df.format(total));
		dailyTable.setModel(dailyModel);
		dailyScroll.setViewportView(dailyTable);
		// Branch Report (Daily) ends

		ListSelectionModel listSelectionModel = dailyTable.getSelectionModel();
		listSelectionModel.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				ListSelectionModel lsm = (ListSelectionModel) e.getSource();
			}
		});

		dailyTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		dailyTable.getTableHeader().setReorderingAllowed(false);
		monthlyTable.getTableHeader().setReorderingAllowed(false);

		// Edit Table (If double clicked)
		dailyTable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2) {
					mainGUI.getJFrame().setEnabled(false);
					mainGUI.removeAllRightSplit();

					BranchReport branchReport = new BranchReport(mainGUI, branchNumber);
					mainGUI.setRightSplit(branchReport.getJPanel());

					int row = dailyTable.getSelectedRow();
					int column = dailyTable.getColumnCount();

					Date sold_date = null;
					int receiptNumber = 0;
					String productName = "";
					double price = 0;
					int quantity = 0;
					String staff = "";
					String customer = "";
					boolean dec = true;

					// sold_date = (Date) dailyTable.getValueAt(row, 1);
					receiptNumber = Integer.parseInt(dailyTable.getValueAt(row, 1).toString());
					productName = dailyTable.getValueAt(row, 2).toString();
					price = new Double(dailyTable.getValueAt(row, 3).toString());
					quantity = Integer.parseInt(dailyTable.getValueAt(row, 4).toString());
					customer = dailyTable.getValueAt(row, 5).toString();
					staff = dailyTable.getValueAt(row, 6).toString();

					EditReceiptDailySales editReceiptDailySales = new EditReceiptDailySales(branchNumber, mainGUI,
							receiptNumber, productName, price, quantity, customer, staff);

				}
			}
		});

	}

	private class ActListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int max = manRec.getLatestYear(branchNumber) - manRec.getEarlyYear(branchNumber);
			if (e.getSource() == btnNextMonth) {
				int currMonth = cmbMonth.getSelectedIndex();

				cmbMonth.setSelectedIndex(currMonth + 1);
				setMonthlyTable();
				if (currMonth == 10)
					btnNextMonth.setEnabled(false);
				else
					btnPrevMonth.setEnabled(true);

			}
			if (e.getSource() == btnPrevMonth) {
				int currMonth = cmbMonth.getSelectedIndex();

				cmbMonth.setSelectedIndex(currMonth - 1);
				setMonthlyTable();
				if (currMonth == 1)
					btnPrevMonth.setEnabled(false);
				else
					btnNextMonth.setEnabled(true);
			}
			if (e.getSource() == btnNextYear) {

				int currYear = cmbYear.getSelectedIndex();
				cmbYear.setSelectedIndex(currYear + 1);
				setMonthlyTable();
				if (currYear == max - 1)
					btnNextYear.setEnabled(false);
				else
					btnPrevYear.setEnabled(true);

			}

			if (e.getSource() == btnPrevYear) {
				int currYear = cmbYear.getSelectedIndex();
				cmbYear.setSelectedIndex(currYear - 1);
				setMonthlyTable();
				if (currYear == 1)
					btnPrevYear.setEnabled(false);
				else
					btnNextYear.setEnabled(true);

			}

			if (e.getSource() == cmbMonth) {
				if (cmbMonth.getSelectedIndex() == 0) {
					btnPrevMonth.setEnabled(false);
					btnNextMonth.setEnabled(true);
				}
				if (cmbMonth.getSelectedIndex() == 11) {
					btnNextMonth.setEnabled(false);
					btnPrevMonth.setEnabled(true);
				}
				if (cmbMonth.getSelectedIndex() != 0 && cmbMonth.getSelectedIndex() != 11){
					btnPrevMonth.setEnabled(true);
					btnNextMonth.setEnabled(true);
				}
			}

			if (e.getSource() == cmbYear) {
				if (cmbYear.getSelectedIndex() == 0) {
					btnPrevYear.setEnabled(false);
					btnNextYear.setEnabled(true);
				}
				if (cmbYear.getSelectedIndex() == max) {
					btnNextYear.setEnabled(false);
					btnPrevYear.setEnabled(true);
				}
				if (cmbYear.getSelectedIndex() != 0 && cmbYear.getSelectedIndex() != max){
					btnNextYear.setEnabled(true);
					btnPrevYear.setEnabled(true);
				}
			}

			if (e.getSource() == btnPickDate) {
				// for calendar
				mainGUI.getJFrame().setEnabled(false);

				calendarFrame = new JFrame("Calendar");
				calendarFrame.addWindowListener(new java.awt.event.WindowAdapter() {
					@Override
					public void windowClosing(java.awt.event.WindowEvent windowEvent) {
						mainGUI.getJFrame().setEnabled(true);

					}
				});
				datePickPanel = new JPanel();
				calendarFrame.setAlwaysOnTop(true);
				calendarFrame.setBounds(400, 400, 250, 100);

				datePicker = new JXDatePicker();
				datePicker.setDate(Calendar.getInstance().getTime());
				datePicker.setFormats(new SimpleDateFormat("dd-MM-yyyy"));

				datePickPanel.add(datePicker);
				datePickPanel.add(btnSet);
				calendarFrame.getContentPane().add(datePickPanel);

				calendarFrame.setVisible(true);

				// end of calendar
			}

			if (e.getSource() == btnSet) {
				mainGUI.getJFrame().setEnabled(true);

				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				dateFrom = datePicker.getDate();
				resultDateFrom = formatter.format(dateFrom);

				txtDay.setText("");

				txtDay.setText(resultDateFrom);

				calendarFrame.dispose();
				calendarFrame = new JFrame("Calendar");
				setDailyTable();
			}

			if (e.getSource() == btnGenerate) {
				setMonthlyTable();
			}

		}
	}

	// updates the monthly table
	public void setMonthlyTable() {
		total = 0;
		DefaultTableModel monthlyModel = new DefaultTableModel(
				new Object[] { "Date", "Receipt Number", "Product", "Price", "Quantity", "Customer", "Staff" }, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		ArrayList<Receipt> receiptMonthly = manRec.getMonthReceipts(branchNumber,
				Integer.parseInt(cmbMonth.getSelectedItem().toString()),
				Integer.parseInt(cmbYear.getSelectedItem().toString()));

		for (int i = 0; i < receiptMonthly.size(); i++) {
			monthlyModel.addRow(new Object[] { receiptMonthly.get(i).getSoldDate(),
					receiptMonthly.get(i).getReceiptID(), receiptMonthly.get(i).getSoldProductName(),
					receiptMonthly.get(i).getSoldPrice(), receiptMonthly.get(i).getSoldQuantity(),
					receiptMonthly.get(i).getCustomerName(), receiptMonthly.get(i).getStaffName() });
			total += receiptMonthly.get(i).getSoldPrice();
		}
		DecimalFormat df = new DecimalFormat("#.00");
		lblTotalSalesM.setText(df.format(total));
		monthlyTable.setModel(monthlyModel);
		monthlyScroll.setViewportView(monthlyTable);
	}

	// updates the daily table
	public void setDailyTable() {
		total = 0;
		DefaultTableModel dailyModel = new DefaultTableModel(
				new Object[] { "Date", "Receipt Number", "Product", "Price", "Quantity", "Customer", "Staff" }, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		ArrayList<Receipt> receiptDaily = manRec.getDayReceipt(branchNumber, resultDateFrom);

		for (int i = 0; i < receiptDaily.size(); i++) {
			dailyModel.addRow(new Object[] { receiptDaily.get(i).getSoldDate(), receiptDaily.get(i).getReceiptID(),
					receiptDaily.get(i).getSoldProductName(), receiptDaily.get(i).getSoldPrice(),
					receiptDaily.get(i).getSoldQuantity(), receiptDaily.get(i).getCustomerName(),
					receiptDaily.get(i).getStaffName() });
			total += receiptDaily.get(i).getSoldPrice();
		}

		DecimalFormat df = new DecimalFormat("#.00");
		lblTotalSalesD.setText(df.format(total));
		dailyTable.setModel(dailyModel);
		dailyScroll.setViewportView(dailyTable);
	}

	public JPanel getJPanel() {
		return mainPanel;
	}
}