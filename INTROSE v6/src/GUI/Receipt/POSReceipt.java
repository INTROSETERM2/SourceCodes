package GUI.Receipt;

import javax.swing.*;

import GUI.MainGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.miginfocom.swing.MigLayout;

public class POSReceipt implements ActionListener{
	JPanel jPanel = new JPanel();
	private JTextField txtTotalAmount = new JTextField();
	private JTable table = new JTable();
	private JTextField txtItemName = new JTextField();
	private JTextField txtQuality = new JTextField();
	private JTextField txtPrice = new JTextField();
	private JTextField txtStaff = new JTextField();
	private JLabel lblBranch = new JLabel("Branch");
	private JLabel label = new JLabel(Integer.toString(MainGUI.RANK));
	private JLabel lblTotalAmount = new JLabel("Total Amount");
	private JLabel lblDate = new JLabel("Date");
	private JLabel lblDatenow = new JLabel("Datenow");
	private JLabel lblItemName = new JLabel("Item name");
	private JLabel lblQuantity = new JLabel("Quantity");
	private JLabel lblPrice = new JLabel("Price");
	private JLabel lblStaff = new JLabel("Staff");
	private JButton btnPreview = new JButton("Preview");
	private JButton btnAdd = new JButton("Add");
	
	public POSReceipt() {
		ActListener act = new ActListener();
		
		jPanel.setSize(580,450);
		jPanel.setLayout(new MigLayout("", "[33px][12px][59px][16px][71px][17px][74px][42px][64px][12px][89px][12px][85px]", "[20px][20px][218px][14px][23px][23px]"));
		
		jPanel.add(lblBranch, "cell 0 0,alignx left,aligny center");
		
		jPanel.add(label, "cell 2 0");
		
		jPanel.add(lblTotalAmount, "cell 8 0,alignx left,aligny center");
		
		jPanel.add(txtTotalAmount, "cell 10 0 3 1,growx,aligny top");
		txtTotalAmount.setColumns(10);
		
		jPanel.add(lblDate, "cell 0 1,alignx right,aligny center");
		
		jPanel.add(lblDatenow, "cell 2 1");
		
		jPanel.add(table, "cell 0 2 13 1,grow");
		
		jPanel.add(lblItemName, "cell 0 3 3 1,alignx left,aligny top");
		
		jPanel.add(lblQuantity, "cell 6 3,alignx left,aligny top");
		
		jPanel.add(lblPrice, "cell 8 3,alignx left,aligny top");
		
		jPanel.add(lblStaff, "cell 12 3,alignx left,aligny top");
		
		jPanel.add(txtItemName, "cell 0 4 3 1,growx,aligny center");
		txtItemName.setColumns(10);
		
		btnPreview.addActionListener(act);
		jPanel.add(btnPreview, "cell 4 4,alignx left,aligny top");
		
		jPanel.add(txtQuality, "cell 6 4,growx,aligny center");
		txtQuality.setColumns(10);
		
		jPanel.add(txtPrice, "cell 8 4 3 1,growx,aligny center");
		txtPrice.setColumns(10);
		
		jPanel.add(txtStaff, "cell 12 4,alignx left,aligny center");
		txtStaff.setColumns(10);
		
		btnAdd.addActionListener(act);
		jPanel.add(btnAdd, "cell 0 5 13 1,growx,aligny top");
		
		
	}
	
	public JPanel getJPanel() {
		return jPanel;
	}
	
	private class ActListener implements ActionListener{
    	public void actionPerformed(ActionEvent e){
    		if(e.getSource() == btnPreview){
    			//Kung ano mangyayari kapag pinindot button
    			System.out.println("t(O_O)t");	
    		}
    		if(e.getSource() == btnAdd){
    			//Kung ano mangyayari kapag pinindot button
    			System.out.println("t(O_O)t");	
    		}
    	}
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}