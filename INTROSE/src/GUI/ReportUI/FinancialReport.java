package GUI.ReportUI;

import javax.swing.JPanel;

import GUI.MainGUI;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JComboBox;

public class FinancialReport implements ActionListener  {
	private JPanel jPanel = new JPanel();
	
	// Labels
	private JLabel lblYearlyReport = new JLabel("Yearly Report of All Branches");
	private JLabel lblTotalCapital = new JLabel("Total Capital:");
	private JLabel lblCapital = new JLabel("");
	private JLabel lblTotalNet = new JLabel("Total Net:");
	private JLabel lblNet = new JLabel("");
	private JTable tblYearReport;
	
	//Scroll Pane
	private JScrollPane scrollPane = new JScrollPane();
	
	public FinancialReport(MainGUI mainGUI){
		jPanel.setPreferredSize(new Dimension(1000, 778));
		jPanel.setLayout(null);
		lblYearlyReport.setBounds(368, 28, 263, 50);
		
		// Labels
		lblYearlyReport.setFont(new Font("Tahoma", Font.BOLD, 18));
		jPanel.add(lblYearlyReport);
		lblTotalCapital.setBounds(21, 627, 122, 23);
		
		lblTotalCapital.setFont(new Font("Tahoma", Font.BOLD, 16));
		jPanel.add(lblTotalCapital);
		lblTotalNet.setBounds(21, 661, 122, 23);
		
		lblTotalNet.setFont(new Font("Tahoma", Font.BOLD, 16));
		jPanel.add(lblTotalNet);
		lblCapital.setBounds(153, 627, 0, 23);
		
		jPanel.add(lblCapital);
		lblNet.setBounds(153, 661, 0, 23);
		jPanel.add(lblNet);
		
		tblYearReport = new JTable();
		scrollPane.setBounds(21, 121, 957, 495);
		scrollPane.setViewportView(tblYearReport);
		
		jPanel.add(scrollPane);
		
		DefaultTableModel model = new DefaultTableModel(new Object[]{"Period Covered","Branches","Gross","Net"}, 0) {
 		   @Override
 		   public boolean isCellEditable(int row, int column) {
 		       return false;
 		   }
		};
		
		tblYearReport.setModel(model);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(88, 89, 79, 23);
		jPanel.add(comboBox);
		
		JLabel lblYear = new JLabel("Year: ");
		lblYear.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblYear.setBounds(34, 87, 65, 23);
		jPanel.add(lblYear);
		
	}
	
	public JPanel getJPanel() {
		return this.jPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
