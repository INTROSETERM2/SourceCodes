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

import GUI.MainGUI;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class BranchReportRename {
	
	private JPanel jPanel = new JPanel();
	private JLabel lblBranchReport = new JLabel("Branch Report");
	private final JScrollPane scrollPane = new JScrollPane();
	private final JRadioButton rdbtnDaily = new JRadioButton("Daily");
	private final JRadioButton rdbtnMonthly = new JRadioButton("Monthly");
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	public BranchReportRename(MainGUI mainGUI){
		
		jPanel.setSize(853,568);
		GridBagLayout gbl_jPanel = new GridBagLayout();
		gbl_jPanel.columnWidths = new int[]{50, 100, 100, 419, 50, 0};
		gbl_jPanel.rowHeights = new int[]{25, 41, 44, 375, 25, 0};
		gbl_jPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_jPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		jPanel.setLayout(gbl_jPanel);
		
		lblBranchReport.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblBranchReport = new GridBagConstraints();
		gbc_lblBranchReport.gridwidth = 3;
		gbc_lblBranchReport.insets = new Insets(0, 0, 5, 5);
		gbc_lblBranchReport.gridx = 1;
		gbc_lblBranchReport.gridy = 1;
		jPanel.add(lblBranchReport, gbc_lblBranchReport);
		
		GridBagConstraints gbc_rdbtnDaily = new GridBagConstraints();
		gbc_rdbtnDaily.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnDaily.gridx = 1;
		gbc_rdbtnDaily.gridy = 2;
		buttonGroup.add(rdbtnDaily);
		jPanel.add(rdbtnDaily, gbc_rdbtnDaily);
		
		GridBagConstraints gbc_rdbtnMonthly = new GridBagConstraints();
		gbc_rdbtnMonthly.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnMonthly.gridx = 2;
		gbc_rdbtnMonthly.gridy = 2;
		buttonGroup.add(rdbtnMonthly);
		jPanel.add(rdbtnMonthly, gbc_rdbtnMonthly);
		
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 3;
		jPanel.add(scrollPane, gbc_scrollPane);
		
	}
	
	
	public JPanel getJPanel() {
		return jPanel;
	}
}
