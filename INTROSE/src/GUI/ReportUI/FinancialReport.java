package GUI.ReportUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import DB.DBConnect;
import GUI.MainGUI;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;

public class FinancialReport implements ActionListener {
	private JPanel jPanel = new JPanel();

	// Labels
	private JLabel lblYearlyReport = new JLabel("Yearly Report of All Branches");
	private JLabel lblTotalCapital = new JLabel("Total Capital:");
	private JLabel lblTotalNet = new JLabel("Total Net Sales:");
	private JTable tblYearReport = new JTable();

	// Scroll Pane
	private JScrollPane scrollPane = new JScrollPane();
	private final JLabel lblTo = new JLabel("To  ");

	private final JLabel lblFilterBranch = new JLabel("                 Filter Branch:");
	private JComboBox cmbBranches;

	private MainGUI mainGUI;

	private DBConnect db = new DBConnect();
	
	SimpleDateFormat formatterDefault;
	Date dateDefault;	
	private JXDatePicker pickerDefault;
	private JLabel lblTotalCapitalDisplay = new JLabel();
	private JLabel lblTotalNetSalesDisplay = new JLabel();
	private JComboBox cmbFromYear = new JComboBox();
	private JComboBox cmbToYear = new JComboBox();
	
	public FinancialReport(MainGUI mainGUI) {
		ActListener act = new ActListener();
		this.mainGUI = mainGUI;
		jPanel.setPreferredSize(new Dimension(1000, 778));
		jPanel.setLayout(
				new MigLayout("", "[70.00px][90.00px][70.00px][90.00px][610px]", "[50px][25px][495px][23px][23px]"));

		// Labels
		lblYearlyReport.setFont(new Font("Tahoma", Font.BOLD, 18));
		jPanel.add(lblYearlyReport, "cell 4 0,alignx left,growy");
		
		//for the defaultDate
		pickerDefault = new JXDatePicker();
		pickerDefault.setDate(Calendar.getInstance().getTime());
		pickerDefault.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
		formatterDefault = new SimpleDateFormat("yyyy/MM/dd");
		dateDefault = pickerDefault.getDate();
		cmbFromYear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		

		
		jPanel.add(cmbFromYear, "cell 1 1,grow");
		cmbToYear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		jPanel.add(cmbToYear, "cell 3 1,grow");
		lblFilterBranch.setFont(new Font("Tahoma", Font.PLAIN, 14));

		jPanel.add(lblFilterBranch, "flowx,cell 4 1,alignx left,aligny center");

		lblTotalCapital.setFont(new Font("Tahoma", Font.BOLD, 16));
		jPanel.add(lblTotalCapital, "cell 0 3 2 1,alignx left,growy");
		lblTotalCapitalDisplay.setForeground(Color.BLACK);
		lblTotalCapitalDisplay.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		jPanel.add(lblTotalCapitalDisplay, "cell 2 3 2 1,grow");

		lblTotalNet.setFont(new Font("Tahoma", Font.BOLD, 16));
		jPanel.add(lblTotalNet, "cell 0 4 2 1,grow");

		tblYearReport = new JTable();
		tblYearReport.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(tblYearReport);
		tblYearReport.getTableHeader().setReorderingAllowed(false);

		tblYearReport.setModel(
				db.retrieveYearlyReport(db.getEarliestDate().toString(), db.getLatestDate().toString(), "None"));
		
		int row = tblYearReport.getRowCount();
		Double totalCapital = 0.0;
		Double totalNetSales = 0.0;
		for (int i = 0; i < row; i++) {
			totalCapital += Double.parseDouble(tblYearReport.getValueAt(i, 2).toString());
			totalNetSales += Double.parseDouble(tblYearReport.getValueAt(i, 3).toString());
		}
		lblTotalCapitalDisplay.setText(totalCapital.toString());
		lblTotalNetSalesDisplay.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTotalNetSalesDisplay.setText(totalNetSales.toString());
		

		jPanel.add(scrollPane, "cell 0 2 5 1,grow");

		JLabel lblFrom = new JLabel("From");
		lblFrom.setFont(new Font("Tahoma", Font.PLAIN, 16));
		jPanel.add(lblFrom, "flowx,cell 0 1,alignx center,growy");
		lblTo.setFont(new Font("Tahoma", Font.PLAIN, 16));

		jPanel.add(lblTo, "flowx,cell 2 1,alignx center,growy");

		cmbBranches = new JComboBox(db.getBranchNames().toArray());
		cmbBranches.setFont(new Font("Tahoma", Font.PLAIN, 14));
		AutoCompleteDecorator.decorate(this.cmbBranches);
		AutoCompleteDecorator.decorate(this.cmbFromYear);
		AutoCompleteDecorator.decorate(this.cmbToYear);

		jPanel.add(cmbBranches, "cell 4 1,grow");
		
		jPanel.add(lblTotalNetSalesDisplay, "cell 2 4 2 1,grow");
		for (int i = db.getEarliestYear(); i <= db.getLatestYear(); i++) {
			cmbFromYear.addItem(i);
			cmbToYear.addItem(i);
		}
		cmbFromYear.addActionListener(act);
		cmbToYear.addActionListener(act);
		cmbBranches.addActionListener(act);

	}

	private class ActListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            
            if(e.getSource() == cmbFromYear){
            	if(Integer.parseInt(cmbFromYear.getSelectedItem().toString()) > 
            	Integer.parseInt(cmbToYear.getSelectedItem().toString())){
            		JOptionPane.showMessageDialog(null, "From Year cannot be more than To Year!");
            	}else{
	                mainGUI.removeAllRightSplit();
	 
	                tblYearReport.setModel(db.retrieveYearlyReport(cmbFromYear.getSelectedItem().toString(), cmbToYear.getSelectedItem().toString(),
	                        cmbBranches.getSelectedItem().toString()));
	                
	        		int row = tblYearReport.getRowCount();
	        		Double totalCapital = 0.0;
	        		Double totalNetSales = 0.0;
	        		for (int i = 0; i < row; i++) {
	        			totalCapital += Double.parseDouble(tblYearReport.getValueAt(i, 2).toString());
	        			totalNetSales += Double.parseDouble(tblYearReport.getValueAt(i, 3).toString());
	        		}
	        		lblTotalCapitalDisplay.setText(totalCapital.toString());
	        		lblTotalNetSalesDisplay.setText(totalNetSales.toString());
	 
	                FinancialReport financialReport = new FinancialReport(mainGUI);
	                mainGUI.setRightSplit(getJPanel());
            	}
            }
            
            if(e.getSource() == cmbToYear){
            	if(Integer.parseInt(cmbFromYear.getSelectedItem().toString()) > 
            	Integer.parseInt(cmbToYear.getSelectedItem().toString())){
            		JOptionPane.showMessageDialog(null, "From Year cannot be more than To Year!");
            	}else{
	                mainGUI.removeAllRightSplit();
	 
	                tblYearReport.setModel(db.retrieveYearlyReport(cmbFromYear.getSelectedItem().toString(), cmbToYear.getSelectedItem().toString(),
	                        cmbBranches.getSelectedItem().toString()));
	                
	        		int row = tblYearReport.getRowCount();
	        		Double totalCapital = 0.0;
	        		Double totalNetSales = 0.0;
	        		for (int i = 0; i < row; i++) {
	        			totalCapital += Double.parseDouble(tblYearReport.getValueAt(i, 2).toString());
	        			totalNetSales += Double.parseDouble(tblYearReport.getValueAt(i, 3).toString());
	        		}
	        		lblTotalCapitalDisplay.setText(totalCapital.toString());
	        		lblTotalNetSalesDisplay.setText(totalNetSales.toString());
	 
	                FinancialReport financialReport = new FinancialReport(mainGUI);
	                mainGUI.setRightSplit(getJPanel());
            	}
            }
            
            if(e.getSource() == cmbBranches){
            	if(Integer.parseInt(cmbFromYear.getSelectedItem().toString()) > 
            	Integer.parseInt(cmbToYear.getSelectedItem().toString())){
            		JOptionPane.showMessageDialog(null, "From Year cannot be more than To Year!");
            	}else{
	                mainGUI.removeAllRightSplit();
	 
	                tblYearReport.setModel(db.retrieveYearlyReport(cmbFromYear.getSelectedItem().toString(), cmbToYear.getSelectedItem().toString(),
	                        cmbBranches.getSelectedItem().toString()));
	                
	        		int row = tblYearReport.getRowCount();
	        		Double totalCapital = 0.0;
	        		Double totalNetSales = 0.0;
	        		for (int i = 0; i < row; i++) {
	        			totalCapital += Double.parseDouble(tblYearReport.getValueAt(i, 2).toString());
	        			totalNetSales += Double.parseDouble(tblYearReport.getValueAt(i, 3).toString());
	        		}
	        		lblTotalCapitalDisplay.setText(totalCapital.toString());
	        		lblTotalNetSalesDisplay.setText(totalNetSales.toString());
	 
	                FinancialReport financialReport = new FinancialReport(mainGUI);
	                mainGUI.setRightSplit(getJPanel());
            	}
            }
        }
    }
	
	public JPanel getJPanel() {
		return this.jPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
