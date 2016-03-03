package GUI.ReportUI;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientDailyReport implements ActionListener{
	
	private JPanel jPanel = new JPanel();
	private JTable table = new JTable();
	private JLabel lblBranch = new JLabel("Branch");
	private JLabel label = new JLabel("#");
	private JLabel lblTotal = new JLabel("Total");
	private JLabel lblP = new JLabel("P #");
	private JLabel lblDate = new JLabel("Date");
	private JComboBox cmbDate = new JComboBox();
	private JButton btnGenerate = new JButton("GENERATE");
	
	public ClientDailyReport(){
			
		ActListener act = new ActListener();
			
		jPanel.setSize(600,450);
		GridBagLayout gbl_jPanel = new GridBagLayout();
		gbl_jPanel.columnWidths = new int[]{81, 41, 86, 121, 41, 137, 0};
		gbl_jPanel.rowHeights = new int[]{62, 14, 20, 254, 23, 0};
		gbl_jPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_jPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		jPanel.setLayout(gbl_jPanel);
	
		GridBagConstraints gbc_lblBranch = new GridBagConstraints();
		gbc_lblBranch.anchor = GridBagConstraints.NORTH;
		gbc_lblBranch.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblBranch.insets = new Insets(0, 0, 5, 5);
		gbc_lblBranch.gridx = 1;
		gbc_lblBranch.gridy = 1;
		jPanel.add(lblBranch, gbc_lblBranch);
		
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.NORTH;
		gbc_label.fill = GridBagConstraints.HORIZONTAL;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 2;
		gbc_label.gridy = 1;
		jPanel.add(label, gbc_label);
		
		GridBagConstraints gbc_lblTotal = new GridBagConstraints();
		gbc_lblTotal.anchor = GridBagConstraints.NORTH;
		gbc_lblTotal.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTotal.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotal.gridx = 4;
		gbc_lblTotal.gridy = 1;
		jPanel.add(lblTotal, gbc_lblTotal);
		
		GridBagConstraints gbc_lblP = new GridBagConstraints();
		gbc_lblP.anchor = GridBagConstraints.NORTH;
		gbc_lblP.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblP.insets = new Insets(0, 0, 5, 0);
		gbc_lblP.gridx = 5;
		gbc_lblP.gridy = 1;
		jPanel.add(lblP, gbc_lblP);
		
		GridBagConstraints gbc_lblDate = new GridBagConstraints();
		gbc_lblDate.anchor = GridBagConstraints.NORTH;
		gbc_lblDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblDate.gridx = 1;
		gbc_lblDate.gridy = 2;
		jPanel.add(lblDate, gbc_lblDate);
		
		GridBagConstraints gbc_cmbDate = new GridBagConstraints();
		gbc_cmbDate.anchor = GridBagConstraints.NORTH;
		gbc_cmbDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbDate.insets = new Insets(0, 0, 5, 5);
		gbc_cmbDate.gridx = 2;
		gbc_cmbDate.gridy = 2;
		jPanel.add(cmbDate, gbc_cmbDate);
		
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.insets = new Insets(0, 0, 5, 0);
		gbc_table.gridwidth = 5;
		gbc_table.gridx = 1;
		gbc_table.gridy = 3;
		jPanel.add(table, gbc_table);
		
		GridBagConstraints gbc_btnGenerate = new GridBagConstraints();
		gbc_btnGenerate.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnGenerate.gridx = 5;
		gbc_btnGenerate.gridy = 4;
		btnGenerate.addActionListener(act);
		jPanel.add(btnGenerate, gbc_btnGenerate);
	}
	
	public JPanel getJPanel() {
		return jPanel;
	}
	
	private class ActListener implements ActionListener
    {
    	public void actionPerformed(ActionEvent e){
    		if(e.getSource() == btnGenerate){
    			System.out.println("t(O_O)t");	
    		}
    	}
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
