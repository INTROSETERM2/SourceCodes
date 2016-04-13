package GUI.ReportUI;

import javax.swing.JPanel;

import GUI.MainGUI;
import GUI.Product.AddProduct;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

public class FinancialReport implements ActionListener {
	private JPanel jPanel = new JPanel();

	// Labels
	private JLabel lblYearlyReport = new JLabel("Yearly Report of All Branches");
	private JLabel lblTotalCapital = new JLabel("Total Capital:");
	private JLabel lblCapital = new JLabel("");
	private JLabel lblTotalNet = new JLabel("Total Net:");
	private JLabel lblNet = new JLabel("");
	private JTable tblYearReport = new JTable();

	// Scroll Pane
	private JScrollPane scrollPane = new JScrollPane();
	private final JLabel lblTo = new JLabel("To  ");
	private final JLabel lblFromDate = new JLabel("None");
	private final JButton btnToSet = new JButton("Set");
	private final JLabel lblToDate = new JLabel("None");

	JButton btnFromSet;
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

	public FinancialReport(MainGUI mainGUI) {
		this.mainGUI = mainGUI;
		ActListener act = new ActListener();
		jPanel.setPreferredSize(new Dimension(1000, 778));
		jPanel.setLayout(
				new MigLayout("", "[132px][-77.00px][77px][117.00px][72px][610px]", "[50px][25px][495px][23px][23px]"));

		// Labels
		lblYearlyReport.setFont(new Font("Tahoma", Font.BOLD, 18));
		jPanel.add(lblYearlyReport, "cell 5 0,alignx left,growy");
		lblFromDate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));

		jPanel.add(lblFromDate, "cell 2 1,alignx left");
		lblToDate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));

		jPanel.add(lblToDate, "cell 4 1");

		jPanel.add(lblFilterBranch, "flowx,cell 5 1,alignx left");

		lblTotalCapital.setFont(new Font("Tahoma", Font.BOLD, 16));
		jPanel.add(lblTotalCapital, "cell 0 3,alignx left,growy");

		lblTotalNet.setFont(new Font("Tahoma", Font.BOLD, 16));
		jPanel.add(lblTotalNet, "cell 0 4,grow");

		jPanel.add(lblCapital, "cell 1 3,alignx left,growy");
		jPanel.add(lblNet, "cell 1 4,alignx left,growy");

		tblYearReport = new JTable();
		scrollPane.setViewportView(tblYearReport);

		tblYearReport.setModel(db.retrieveYearlyReport(db.getEarliestDate(), db.getLatestDate(), "None"));


		jPanel.add(scrollPane, "cell 0 2 6 1,grow");

		JLabel lblFrom = new JLabel("From");
		lblFrom.setFont(new Font("Tahoma", Font.PLAIN, 16));
		jPanel.add(lblFrom, "flowx,cell 0 1,alignx left,growy");
		lblTo.setFont(new Font("Tahoma", Font.PLAIN, 16));

		jPanel.add(lblTo, "flowx,cell 3 1,alignx left,growy");

		btnFromSet = new JButton("Set");
		jPanel.add(btnFromSet, "cell 0 1,growx,aligny bottom");

		jPanel.add(btnToSet, "cell 3 1,growx");

		cmbBranches = new JComboBox(db.getBranchNames().toArray());
		AutoCompleteDecorator.decorate(this.cmbBranches);

		jPanel.add(cmbBranches, "cell 5 1,growx");

		jPanel.add(btnGenerate, "cell 5 1");

		btnFromSet.addActionListener(act);
		btnToSet.addActionListener(act);
		btnSetFrom.addActionListener(act);
		btnSetTo.addActionListener(act);
		btnGenerate.addActionListener(act);

	}

	public JPanel getJPanel() {
		return this.jPanel;
	}

	private class ActListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnFromSet) {
				// for calendar
				calenderFrameFrom = new JFrame("Calendar");
				JPanel panel = new JPanel();
				calenderFrameFrom.setBounds(400, 400, 250, 100);

				pickerFrom = new JXDatePicker();
				pickerFrom.setDate(Calendar.getInstance().getTime());
				pickerFrom.setFormats(new SimpleDateFormat("dd/MM/yyyy"));

				panel.add(pickerFrom);
				panel.add(btnSetFrom);
				calenderFrameFrom.getContentPane().add(panel);

				calenderFrameFrom.setVisible(true);

				// end of calendar
			}

			if (e.getSource() == btnSetFrom)

			{
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
				dateFrom = pickerFrom.getDate();

				lblFromDate.setText("");

				lblFromDate.setText(formatter.format(dateFrom));

				calenderFrameFrom.dispose();

			}

			if (e.getSource() == btnToSet) {
				// for calendar
				calenderFrameTo = new JFrame("Calendar");
				JPanel panel = new JPanel();
				calenderFrameTo.setBounds(400, 400, 250, 100);

				pickerTo = new JXDatePicker();
				pickerTo.setDate(Calendar.getInstance().getTime());
				pickerTo.setFormats(new SimpleDateFormat("dd/MM/yyyy"));

				panel.add(pickerTo);
				panel.add(btnSetTo);
				calenderFrameTo.getContentPane().add(panel);

				calenderFrameTo.setVisible(true);

				// end of calendar
			}

			if (e.getSource() == btnSetTo)

			{
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
				dateTo = pickerTo.getDate();

				lblToDate.setText("");

				lblToDate.setText(formatter.format(dateTo));

				calenderFrameTo.dispose();

			}

			if (e.getSource() == btnGenerate) {
				mainGUI.removeAllRightSplit();
				tblYearReport.setModel(db.retrieveYearlyReport(db.convertJavaDateToSqlDate(dateFrom),
						db.convertJavaDateToSqlDate(dateFrom), cmbBranches.getSelectedItem().toString()));
				
				System.out.println(tblYearReport.toString());
				FinancialReport financialReport = new FinancialReport(mainGUI);
				mainGUI.setRightSplit(getJPanel());
				

			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
