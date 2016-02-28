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

public class FinancialReport {
	
	JPanel jPanel = new JPanel();
	private JTable table;
	
	public FinancialReport(){
		
	jPanel.setSize(600,450);
	GridBagLayout gbl_jPanel = new GridBagLayout();
	gbl_jPanel.columnWidths = new int[]{54, 38, 37, 114, 24, 114, 12, 144, 0};
	gbl_jPanel.rowHeights = new int[]{30, 17, 44, 20, 282, 0};
	gbl_jPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	gbl_jPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	jPanel.setLayout(gbl_jPanel);
	
	JLabel lblFinancialReport = new JLabel("Financial Report");
	lblFinancialReport.setFont(new Font("Tahoma", Font.PLAIN, 14));
	GridBagConstraints gbc_lblFinancialReport = new GridBagConstraints();
	gbc_lblFinancialReport.anchor = GridBagConstraints.NORTHWEST;
	gbc_lblFinancialReport.insets = new Insets(0, 0, 5, 5);
	gbc_lblFinancialReport.gridwidth = 3;
	gbc_lblFinancialReport.gridx = 1;
	gbc_lblFinancialReport.gridy = 1;
	jPanel.add(lblFinancialReport, gbc_lblFinancialReport);
	
	JLabel lblTimeline = new JLabel("Timeline");
	GridBagConstraints gbc_lblTimeline = new GridBagConstraints();
	gbc_lblTimeline.anchor = GridBagConstraints.WEST;
	gbc_lblTimeline.insets = new Insets(0, 0, 5, 5);
	gbc_lblTimeline.gridx = 1;
	gbc_lblTimeline.gridy = 3;
	jPanel.add(lblTimeline, gbc_lblTimeline);
	
	JComboBox comboBox = new JComboBox();
	GridBagConstraints gbc_comboBox = new GridBagConstraints();
	gbc_comboBox.anchor = GridBagConstraints.NORTH;
	gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
	gbc_comboBox.insets = new Insets(0, 0, 5, 5);
	gbc_comboBox.gridx = 3;
	gbc_comboBox.gridy = 3;
	jPanel.add(comboBox, gbc_comboBox);
	
	JLabel lblFrom = new JLabel("From");
	GridBagConstraints gbc_lblFrom = new GridBagConstraints();
	gbc_lblFrom.anchor = GridBagConstraints.WEST;
	gbc_lblFrom.insets = new Insets(0, 0, 5, 5);
	gbc_lblFrom.gridx = 4;
	gbc_lblFrom.gridy = 3;
	jPanel.add(lblFrom, gbc_lblFrom);
	
	JComboBox comboBox_1 = new JComboBox();
	GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
	gbc_comboBox_1.anchor = GridBagConstraints.NORTH;
	gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
	gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
	gbc_comboBox_1.gridx = 5;
	gbc_comboBox_1.gridy = 3;
	jPanel.add(comboBox_1, gbc_comboBox_1);
	
	JLabel lblTo = new JLabel("To");
	GridBagConstraints gbc_lblTo = new GridBagConstraints();
	gbc_lblTo.anchor = GridBagConstraints.WEST;
	gbc_lblTo.insets = new Insets(0, 0, 5, 5);
	gbc_lblTo.gridx = 6;
	gbc_lblTo.gridy = 3;
	jPanel.add(lblTo, gbc_lblTo);
	
	JComboBox comboBox_2 = new JComboBox();
	GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
	gbc_comboBox_2.anchor = GridBagConstraints.NORTH;
	gbc_comboBox_2.fill = GridBagConstraints.HORIZONTAL;
	gbc_comboBox_2.insets = new Insets(0, 0, 5, 0);
	gbc_comboBox_2.gridx = 7;
	gbc_comboBox_2.gridy = 3;
	jPanel.add(comboBox_2, gbc_comboBox_2);
	
	table = new JTable();
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
