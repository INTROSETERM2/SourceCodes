package GUI.ReportUI;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JComboBox;

public class BranchReport {
	
	private JPanel jPanel = new JPanel();
	private JTable table = new JTable();
	private JLabel lblBranchReport = new JLabel("Branch Report");
	private JLabel lblTimeline = new JLabel("Timeline");
	private JComboBox cmbTimeline = new JComboBox();
	private JLabel lblFrom = new JLabel("From");
	private JLabel lblTo = new JLabel("To");
	private JComboBox cmbFromDate = new JComboBox();
	private JComboBox cmbToDate = new JComboBox();
	
	public BranchReport(){
		
		jPanel.setSize(600,450);
		GridBagLayout gbl_jPanel = new GridBagLayout();
		gbl_jPanel.columnWidths = new int[]{54, 38, 37, 114, 24, 114, 12, 144, 0};
		gbl_jPanel.rowHeights = new int[]{30, 17, 44, 20, 282, 0};
		gbl_jPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_jPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		jPanel.setLayout(gbl_jPanel);
		
		lblBranchReport.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblBranchReport = new GridBagConstraints();
		gbc_lblBranchReport.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblBranchReport.insets = new Insets(0, 0, 5, 5);
		gbc_lblBranchReport.gridwidth = 3;
		gbc_lblBranchReport.gridx = 1;
		gbc_lblBranchReport.gridy = 1;
		jPanel.add(lblBranchReport, gbc_lblBranchReport);
		
		GridBagConstraints gbc_lblTimeline = new GridBagConstraints();
		gbc_lblTimeline.anchor = GridBagConstraints.WEST;
		gbc_lblTimeline.insets = new Insets(0, 0, 5, 5);
		gbc_lblTimeline.gridx = 1;
		gbc_lblTimeline.gridy = 3;
		jPanel.add(lblTimeline, gbc_lblTimeline);
		
		GridBagConstraints gbc_cmbTimeline = new GridBagConstraints();
		gbc_cmbTimeline.anchor = GridBagConstraints.NORTH;
		gbc_cmbTimeline.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbTimeline.insets = new Insets(0, 0, 5, 5);
		gbc_cmbTimeline.gridx = 3;
		gbc_cmbTimeline.gridy = 3;
		jPanel.add(cmbTimeline, gbc_cmbTimeline);
		
		GridBagConstraints gbc_lblFrom = new GridBagConstraints();
		gbc_lblFrom.anchor = GridBagConstraints.WEST;
		gbc_lblFrom.insets = new Insets(0, 0, 5, 5);
		gbc_lblFrom.gridx = 4;
		gbc_lblFrom.gridy = 3;
		jPanel.add(lblFrom, gbc_lblFrom);
		
		GridBagConstraints gbc_cmbFromDate = new GridBagConstraints();
		gbc_cmbFromDate.anchor = GridBagConstraints.NORTH;
		gbc_cmbFromDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbFromDate.insets = new Insets(0, 0, 5, 5);
		gbc_cmbFromDate.gridx = 5;
		gbc_cmbFromDate.gridy = 3;
		jPanel.add(cmbFromDate, gbc_cmbFromDate);
		
		GridBagConstraints gbc_lblTo = new GridBagConstraints();
		gbc_lblTo.anchor = GridBagConstraints.WEST;
		gbc_lblTo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTo.gridx = 6;
		gbc_lblTo.gridy = 3;
		jPanel.add(lblTo, gbc_lblTo);
		
		GridBagConstraints gbc_cmbToDate = new GridBagConstraints();
		gbc_cmbToDate.anchor = GridBagConstraints.NORTH;
		gbc_cmbToDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbToDate.insets = new Insets(0, 0, 5, 0);
		gbc_cmbToDate.gridx = 7;
		gbc_cmbToDate.gridy = 3;
		jPanel.add(cmbToDate, gbc_cmbToDate);
		
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridwidth = 7;
		gbc_table.gridx = 1;
		gbc_table.gridy = 4;
		jPanel.add(table, gbc_table);
	}
	
	
	public JPanel getJPanel() {
		return jPanel;
	}
}
