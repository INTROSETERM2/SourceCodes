package GUI.ReportUI;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import GUI.MainGUI;

public class BranchReport {
	// Panels
	private JPanel mainPanel = new JPanel();
	private JPanel dailyPanel = new JPanel();
	private JPanel monthlyPanel = new JPanel();
	
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
	
	// Buttons
	private JButton btnPrevMonth = new JButton("<");
	private JButton btnNextMonth = new JButton(">");
	private JButton btnPrevYear = new JButton("<");
	private JButton btnNextYear = new JButton(">");
	
	// Combo Boxes
	private JComboBox cmbMonth = new JComboBox();
	private JComboBox cmbYear = new JComboBox();
	
	// Tables
	private JTable montlyTable;
	private JTable table;
	
	public BranchReport(MainGUI mainGUI){
	
		
		mainPanel.setSize(1040,609);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{0, 0};
		gbl_mainPanel.rowHeights = new int[]{0, 0};
		gbl_mainPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_mainPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_mainPanel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		mainPanel.add(tabbedPane, gbc_tabbedPane);
		
		
		tabbedPane.add("<html><body><table width='50' height='5'><tr><td><center>Daily</center></td></tr></table></body></html>", dailyPanel);
		GridBagLayout gbl_dailyPanel = new GridBagLayout();
		gbl_dailyPanel.columnWidths = new int[]{0, 510, 150, 150, 0, 0};
		gbl_dailyPanel.rowHeights = new int[]{0, 35, 0, 0, 0};
		gbl_dailyPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_dailyPanel.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		dailyPanel.setLayout(gbl_dailyPanel);
		
		
		lblBranchD.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_lblBranch = new GridBagConstraints();
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
		gbc_lblTotalSalesDisplay.gridx = 2;
		gbc_lblTotalSalesDisplay.gridy = 1;
		dailyPanel.add(lblTotalSalesDisplayD, gbc_lblTotalSalesDisplay);
		
		
		GridBagConstraints gbc_lblTotalSales = new GridBagConstraints();
		gbc_lblTotalSales.anchor = GridBagConstraints.WEST;
		gbc_lblTotalSales.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotalSales.gridx = 3;
		gbc_lblTotalSales.gridy = 1;
		dailyPanel.add(lblTotalSalesD, gbc_lblTotalSales);
		
		GridBagConstraints gbc_dailyScroll = new GridBagConstraints();
		gbc_dailyScroll.gridwidth = 3;
		gbc_dailyScroll.insets = new Insets(0, 0, 5, 5);
		gbc_dailyScroll.fill = GridBagConstraints.BOTH;
		gbc_dailyScroll.gridx = 1;
		gbc_dailyScroll.gridy = 2;
		dailyPanel.add(dailyScroll, gbc_dailyScroll);
		
		table = new JTable();
		dailyScroll.setViewportView(table);
		
		
		tabbedPane.add(monthlyPanel);
		tabbedPane.add("<html><body><table width='50' height='5'><tr><td><center>Monthly</center></td></tr></table></body></html>", monthlyPanel);
		GridBagLayout gbl_monthlyPanel = new GridBagLayout();
		gbl_monthlyPanel.columnWidths = new int[]{0, 10, 70, 10, 10, 70, 1, 265, 150, 150, 0, 0};
		gbl_monthlyPanel.rowHeights = new int[]{0, 35, 0, 0, 0, 0, 0};
		gbl_monthlyPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_monthlyPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
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
		gbc_cmbMonth.fill = GridBagConstraints.BOTH;
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
		gbc_cmbYear.fill = GridBagConstraints.BOTH;
		gbc_cmbYear.gridx = 5;
		gbc_cmbYear.gridy = 3;
		monthlyPanel.add(cmbYear, gbc_cmbYear);
		
		GridBagConstraints gbc_btnNextYear = new GridBagConstraints();
		gbc_btnNextYear.fill = GridBagConstraints.VERTICAL;
		gbc_btnNextYear.insets = new Insets(0, 0, 5, 5);
		gbc_btnNextYear.gridx = 6;
		gbc_btnNextYear.gridy = 3;
		monthlyPanel.add(btnNextYear, gbc_btnNextYear);
		
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 9;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 4;
		monthlyPanel.add(monthlyScroll, gbc_scrollPane_1);
		
		montlyTable = new JTable();
		monthlyScroll.setViewportView(montlyTable);
		
		AutoCompleteDecorator.decorate(cmbMonth);
		AutoCompleteDecorator.decorate(cmbYear);
		
		for (int i = 0; i < 12; i++) 
			cmbMonth.insertItemAt(i + 1, i);
		
		int j = 0;
		for (int i = 1900; i < 2401; i++) {
			cmbYear.insertItemAt(i, j);
			j++;
		}
		
	}
	
	
	public JPanel getJPanel() {
		return mainPanel;
	}
}
