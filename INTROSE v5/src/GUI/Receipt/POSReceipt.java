package GUI.Receipt;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.miginfocom.swing.MigLayout;

public class POSReceipt {
	JPanel jPanel = new JPanel();
	private JTextField txtTotalAmount;
	private JTable table;
	private JTextField txtItemName;
	private JTextField txtQuality;
	private JTextField txtPrice;
	private JTextField txtStaff;

	public POSReceipt() {
		jPanel.setSize(600,450);
		jPanel.setLayout(new MigLayout("", "[33px][12px][59px][16px][71px][17px][74px][42px][64px][12px][89px][12px][85px]", "[20px][20px][218px][14px][23px][23px]"));
		
		JLabel lblBranch = new JLabel("Branch");
		jPanel.add(lblBranch, "cell 0 0,alignx left,aligny center");
		
		JLabel label = new JLabel("#");
		jPanel.add(label, "cell 2 0");
		
		JLabel lblTotalAmount = new JLabel("Total Amount");
		jPanel.add(lblTotalAmount, "cell 8 0,alignx left,aligny center");
		
		txtTotalAmount = new JTextField();
		jPanel.add(txtTotalAmount, "cell 10 0 3 1,growx,aligny top");
		txtTotalAmount.setColumns(10);
		
		JLabel lblDate = new JLabel("Date");
		jPanel.add(lblDate, "cell 0 1,alignx right,aligny center");
		
		JLabel lblDatenow = new JLabel("Datenow");
		jPanel.add(lblDatenow, "cell 2 1");
		
		table = new JTable();
		jPanel.add(table, "cell 0 2 13 1,grow");
		
		JLabel lblItemName = new JLabel("Item name");
		jPanel.add(lblItemName, "cell 0 3 3 1,alignx left,aligny top");
		
		JLabel lblQuantity = new JLabel("Quantity");
		jPanel.add(lblQuantity, "cell 6 3,alignx left,aligny top");
		
		JLabel lblPrice = new JLabel("Price");
		jPanel.add(lblPrice, "cell 8 3,alignx left,aligny top");
		
		JLabel lblStaff = new JLabel("Staff");
		jPanel.add(lblStaff, "cell 12 3,alignx left,aligny top");
		
		txtItemName = new JTextField();
		jPanel.add(txtItemName, "cell 0 4 3 1,growx,aligny center");
		txtItemName.setColumns(10);
		
		JButton btnPreview = new JButton("Preview");
		jPanel.add(btnPreview, "cell 4 4,alignx left,aligny top");
		
		txtQuality = new JTextField();
		jPanel.add(txtQuality, "cell 6 4,growx,aligny center");
		txtQuality.setColumns(10);
		
		txtPrice = new JTextField();
		jPanel.add(txtPrice, "cell 8 4 3 1,growx,aligny center");
		txtPrice.setColumns(10);
		
		txtStaff = new JTextField();
		jPanel.add(txtStaff, "cell 12 4,alignx left,aligny center");
		txtStaff.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		jPanel.add(btnAdd, "cell 0 5 13 1,growx,aligny top");
	}
	
	public JPanel getJPanel() {
		return jPanel;
	}
}