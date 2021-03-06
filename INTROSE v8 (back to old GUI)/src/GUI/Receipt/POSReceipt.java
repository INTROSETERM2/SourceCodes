package GUI.Receipt;

import javax.swing.*;

import Branch.Branch;
import DB.DBConnect;
import GUI.MainGUI;
import Product.ManagerProduct;
import Receipt.Receipt;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import net.miginfocom.swing.MigLayout;

public class POSReceipt implements ActionListener{
	JPanel jPanel = new JPanel();
	private JTextField txtTotalAmount = new JTextField();
	private JTable table = new JTable();
	private JComboBox cmbItemName;
	private JComboBox cmbQuantity;
	private JTextField txtPrice = new JTextField();
	private JTextField txtStaff = new JTextField();
	private JTextField txtCustomer = new JTextField();
	

	
	private JLabel lblCustomer = new JLabel("Customer");
	private JLabel lblBranch = new JLabel("Branch");
	private JLabel label = new JLabel(Integer.toString(MainGUI.BRANCH.getBranchID()));
	private JLabel lblTotalAmount = new JLabel("Total Amount");
	private JLabel lblDate = new JLabel("Date");

	//for Date
	private DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
	private Date today = Calendar.getInstance().getTime();  	
	private String currentDate = df.format(today);
	
	
	private JLabel lblDatenow = new JLabel(currentDate);
	private JLabel lblItemName = new JLabel("Item name");
	private JLabel lblQuantity = new JLabel("Quantity");
	private JLabel lblPrice = new JLabel("Price");
	private JLabel lblStaff = new JLabel("Staff");
	private JButton btnPreview = new JButton("Preview");
	private JButton btnAdd = new JButton("Add");
	
	public POSReceipt() {
		DBConnect db = new DBConnect();
		ActListener act = new ActListener();
		
		txtCustomer.setColumns(10);
		jPanel.setSize(580,450);
		jPanel.setLayout(new MigLayout("", "[33px][12px][59px][grow][16px][71px][17px][74px][42px][64px][12px][89px][12px][85px]", "[20px][20px][218px][14px][23px][23px][]"));
		
		jPanel.add(lblBranch, "cell 0 0,alignx left,aligny center");
		
		jPanel.add(label, "cell 2 0");
		
		jPanel.add(lblTotalAmount, "cell 9 0,alignx left,aligny center");
		
		jPanel.add(txtTotalAmount, "cell 11 0 3 1,growx,aligny top");
		txtTotalAmount.setColumns(10);
		
		jPanel.add(lblDate, "cell 0 1,alignx right,aligny center");
		
		jPanel.add(lblDatenow, "cell 2 1");
		
		jPanel.add(table, "cell 0 2 14 1,grow");
		
		jPanel.add(lblItemName, "cell 0 3,growx,aligny bottom");
		
		btnPreview.addActionListener(act);
		jPanel.add(btnPreview, "cell 2 3,growx,aligny top");
		
		jPanel.add(lblCustomer, "cell 3 3,aligny bottom");
		
		jPanel.add(lblQuantity, "cell 7 3,alignx left,aligny bottom");
		
		jPanel.add(lblPrice, "cell 9 3,alignx left,aligny bottom");
		
		jPanel.add(lblStaff, "cell 13 3,alignx left,aligny bottom");
		
		cmbQuantity = new JComboBox();
		cmbQuantity.addActionListener(act);
		
		
		
		jPanel.add(cmbQuantity, "cell 7 4,growx,aligny center");
		cmbItemName = new JComboBox(db.getProducts().toArray());
		cmbItemName.addActionListener(act);
		jPanel.add(cmbItemName, "cell 0 4 3 1,growx,aligny center");
		
		jPanel.add(txtPrice, "cell 9 4 3 1,growx,aligny center");
		txtPrice.setColumns(10);
		
		jPanel.add(txtStaff, "cell 13 4,alignx left,aligny center");
		txtStaff.setColumns(10);
		
		btnAdd.addActionListener(act);
		jPanel.add(btnAdd, "cell 0 5 14 1,growx,aligny top");
		
		
	}
	
	public JPanel getJPanel() {
		return jPanel;
	}
	
	private class ActListener implements ActionListener{
    	public void actionPerformed(ActionEvent e){
    		if(e.getSource() == cmbItemName){
    			DBConnect db = new DBConnect();
    			ArrayList<String> quantityContent = new ArrayList<String>();
    			int i;
    			
    			String selectedItem = cmbItemName.getSelectedItem().toString();
    			
    			for(i = 0; i < db.getQuantity(selectedItem); i++)
    				quantityContent.add(Integer.toString(i + 1));
    			
    			for(i = 0; i < quantityContent.size(); i++)
    				cmbQuantity.insertItemAt(quantityContent.get(i), i);
    		}
    		
    		if(e.getSource() == btnPreview){
    			//Kung ano mangyayari kapag pinindot button
    			System.out.println("t(O_O)t");	
    		}
    		
    		if(e.getSource() == btnAdd){
    			//Kung ano mangyayari kapag pinindot button
    			DBConnect db = new DBConnect();
    			ManagerProduct managerProduct = new ManagerProduct();
    			Receipt receipt = new Receipt(txtCustomer.getText(), Double.parseDouble(txtPrice.getText()), 
    					Integer.parseInt(cmbQuantity.getSelectedItem().toString()), today, txtCustomer.getText(), MainGUI.BRANCH, 1);
    			
    			db.addReceipt(receipt);
    			
    			txtTotalAmount.setText(String.valueOf(Double.parseDouble(txtPrice.getText()) * Integer.parseInt(cmbQuantity.getSelectedItem().toString())));
    			
                txtTotalAmount.setForeground(Color.red);
                
                managerProduct.decrementProduct(cmbItemName.getSelectedItem().toString(), Integer.parseInt(cmbQuantity.getSelectedItem().toString()));
                
                
    		}
    	}
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}