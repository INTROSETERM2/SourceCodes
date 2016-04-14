package GUI.ReportUI;

import javax.swing.JPanel;

import GUI.MainGUI;
import GUI.Product.AddProduct;
import Product.PictureFinder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import DB.DBConnect;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;

public class FinancialReport implements ActionListener {
	private JPanel jPanel = new JPanel();

	// Labels
	private JLabel lblYearlyReport = new JLabel("Yearly Report of All Branches");
	private JLabel lblTotalCapital = new JLabel("Total Capital:");
	private JLabel lblTotalNet = new JLabel("Total Net:");
	private JTable tblYearReport = new JTable();

	// Scroll Pane
	private JScrollPane scrollPane = new JScrollPane();
	private final JLabel lblTo = new JLabel("To  ");
	private JXDatePicker pickerFrom;
	private Date dateFrom;
	private JFrame calenderFrameFrom;
	private JButton btnSetFrom = new JButton("Set");

	private JXDatePicker pickerTo;
	private Date dateTo;
	private JFrame calenderFrameTo;
	private JButton btnSetTo = new JButton("Set");
	private final JLabel lblFilterBranch = new JLabel("Filter Branch:");
	private JComboBox cmbBranches;

	private MainGUI mainGUI;

	private DBConnect db = new DBConnect();
	private final JButton btnGenerate = new JButton("Generate");
	
	SimpleDateFormat formatterDefault;
	Date dateDefault;	
	private JXDatePicker pickerDefault;
	private JLabel lblTotalCapitalDisplay = new JLabel();
	private JLabel lblTotalNetSalesDisplay = new JLabel();
	private final JComboBox cmbFromYear = new JComboBox();
	private final JComboBox cmbToYear = new JComboBox();
	
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
		

		
		jPanel.add(cmbFromYear, "cell 1 1,grow");
		
		jPanel.add(cmbToYear, "cell 3 1,grow");

		jPanel.add(lblFilterBranch, "flowx,cell 4 1,alignx left,aligny center");

		lblTotalCapital.setFont(new Font("Tahoma", Font.BOLD, 16));
		jPanel.add(lblTotalCapital, "cell 0 3 2 1,alignx left,growy");
		
		jPanel.add(lblTotalCapitalDisplay, "cell 2 3 2 1,grow");

		lblTotalNet.setFont(new Font("Tahoma", Font.BOLD, 16));
		jPanel.add(lblTotalNet, "cell 0 4 2 1,grow");

		tblYearReport = new JTable();
		scrollPane.setViewportView(tblYearReport);

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
		lblTotalNetSalesDisplay.setText(totalNetSales.toString());
		

		jPanel.add(scrollPane, "cell 0 2 5 1,grow");

		JLabel lblFrom = new JLabel("From");
		lblFrom.setFont(new Font("Tahoma", Font.PLAIN, 16));
		jPanel.add(lblFrom, "flowx,cell 0 1,alignx left,growy");
		lblTo.setFont(new Font("Tahoma", Font.PLAIN, 16));

		jPanel.add(lblTo, "flowx,cell 2 1,alignx trailing,growy");

		cmbBranches = new JComboBox(db.getBranchNames().toArray());
		AutoCompleteDecorator.decorate(this.cmbBranches);

		jPanel.add(cmbBranches, "cell 4 1,grow");

		jPanel.add(btnGenerate, "cell 4 1,growy");
		
		jPanel.add(lblTotalNetSalesDisplay, "cell 2 4 2 1,grow");
		for (int i = db.getEarliestYear(); i <= db.getLatestYear(); i++) {
			cmbFromYear.addItem(i);
			cmbToYear.addItem(i);
		}
		btnGenerate.addActionListener(act);

	}

	private class ActListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnGenerate) {
 
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
