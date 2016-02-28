package GUI.ReportUI;



import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class ClientDailyReport {
	
	JPanel jPanel = new JPanel();
	private JTable table;
	
	public ClientDailyReport(){
		
	jPanel.setSize(600,450);
	GridBagLayout gbl_jPanel = new GridBagLayout();
	gbl_jPanel.columnWidths = new int[]{81, 41, 86, 121, 41, 137, 0};
	gbl_jPanel.rowHeights = new int[]{62, 14, 20, 254, 23, 0};
	gbl_jPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	gbl_jPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	jPanel.setLayout(gbl_jPanel);
	
	JLabel lblBranch = new JLabel("Branch");
	GridBagConstraints gbc_lblBranch = new GridBagConstraints();
	gbc_lblBranch.anchor = GridBagConstraints.NORTH;
	gbc_lblBranch.fill = GridBagConstraints.HORIZONTAL;
	gbc_lblBranch.insets = new Insets(0, 0, 5, 5);
	gbc_lblBranch.gridx = 1;
	gbc_lblBranch.gridy = 1;
	jPanel.add(lblBranch, gbc_lblBranch);
	
	JLabel label = new JLabel("#");
	GridBagConstraints gbc_label = new GridBagConstraints();
	gbc_label.anchor = GridBagConstraints.NORTH;
	gbc_label.fill = GridBagConstraints.HORIZONTAL;
	gbc_label.insets = new Insets(0, 0, 5, 5);
	gbc_label.gridx = 2;
	gbc_label.gridy = 1;
	jPanel.add(label, gbc_label);
	
	JLabel lblTotal = new JLabel("Total");
	GridBagConstraints gbc_lblTotal = new GridBagConstraints();
	gbc_lblTotal.anchor = GridBagConstraints.NORTH;
	gbc_lblTotal.fill = GridBagConstraints.HORIZONTAL;
	gbc_lblTotal.insets = new Insets(0, 0, 5, 5);
	gbc_lblTotal.gridx = 4;
	gbc_lblTotal.gridy = 1;
	jPanel.add(lblTotal, gbc_lblTotal);
	
	JLabel lblP = new JLabel("P #");
	GridBagConstraints gbc_lblP = new GridBagConstraints();
	gbc_lblP.anchor = GridBagConstraints.NORTH;
	gbc_lblP.fill = GridBagConstraints.HORIZONTAL;
	gbc_lblP.insets = new Insets(0, 0, 5, 0);
	gbc_lblP.gridx = 5;
	gbc_lblP.gridy = 1;
	jPanel.add(lblP, gbc_lblP);
	
	JLabel lblDate = new JLabel("Date");
	GridBagConstraints gbc_lblDate = new GridBagConstraints();
	gbc_lblDate.anchor = GridBagConstraints.NORTH;
	gbc_lblDate.fill = GridBagConstraints.HORIZONTAL;
	gbc_lblDate.insets = new Insets(0, 0, 5, 5);
	gbc_lblDate.gridx = 1;
	gbc_lblDate.gridy = 2;
	jPanel.add(lblDate, gbc_lblDate);
	
	JComboBox comboBox = new JComboBox();
	GridBagConstraints gbc_comboBox = new GridBagConstraints();
	gbc_comboBox.anchor = GridBagConstraints.NORTH;
	gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
	gbc_comboBox.insets = new Insets(0, 0, 5, 5);
	gbc_comboBox.gridx = 2;
	gbc_comboBox.gridy = 2;
	jPanel.add(comboBox, gbc_comboBox);
	
	table = new JTable();
	GridBagConstraints gbc_table = new GridBagConstraints();
	gbc_table.fill = GridBagConstraints.BOTH;
	gbc_table.insets = new Insets(0, 0, 5, 0);
	gbc_table.gridwidth = 5;
	gbc_table.gridx = 1;
	gbc_table.gridy = 3;
	jPanel.add(table, gbc_table);
	
	JButton btnConfirm = new JButton("CONFIRM");
	GridBagConstraints gbc_btnConfirm = new GridBagConstraints();
	gbc_btnConfirm.anchor = GridBagConstraints.NORTHEAST;
	gbc_btnConfirm.gridx = 5;
	gbc_btnConfirm.gridy = 4;
	jPanel.add(btnConfirm, gbc_btnConfirm);
	}
	
	
	public JPanel getJPanel() {
		return jPanel;
	}
}
