package GUI.Product;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Font;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import DB.DBConnect;
import GUI.MainGUI;
import Product.Product;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;

public class AddProduct implements ActionListener{
	private JPanel jPanel = new JPanel();
	
	// Text Fields
	private JTextField txtDate = new JTextField();
	private JTextField txtProductName = new JTextField();
	private JTextField txtQuantity = new JTextField();
	private JTextField txtBuyingPrice = new JTextField();
	private JTextField txtOrigin = new JTextField();
	
	// Labels
	private JLabel lblInventory = new JLabel("Inventory");
	private JLabel lblProductId = new JLabel("Product ID");
	private JLabel lblgeneratedBy = new JLabel("#####");
	private JLabel lblDate = new JLabel("Date");
	private JLabel lblProductName = new JLabel("Product Name");
	private JLabel lblPicture = new JLabel("Picture");
	private JLabel lblProductType = new JLabel("Product Type");
	private JLabel lblQuantity = new JLabel("Quantity");
	private JLabel lblTotalCost = new JLabel("Total Cost");
	private JLabel lblOrigin = new JLabel("Origin");
	
	// Combo Boxes
	private JComboBox cmbProductType = new JComboBox();
	
	// Buttons
	private JButton btnAddPicture = new JButton("Add Picture");
	private JButton btnAdd = new JButton("ADD");
	
	// Table
	private JScrollPane scrollPane = new JScrollPane();
	private JTable productTable = new JTable();
	
	
	private DBConnect db = new DBConnect();
	
	public AddProduct(MainGUI mainGUI){
		txtProductName.setColumns(10);
		txtOrigin.setColumns(10);
		ActListener act = new ActListener();
			
		jPanel.setSize(1067,605);
		GridBagLayout gbl_jPanel = new GridBagLayout();
		gbl_jPanel.columnWidths = new int[]{50, 110, 110, 110, 110, 110, 110, 110, 110, 0, 0};
		gbl_jPanel.rowHeights = new int[]{20, 24, 340, 30, 30, 30, 20, 0};
		gbl_jPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_jPanel.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		jPanel.setLayout(gbl_jPanel);
		
		lblInventory.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblAddProduct = new GridBagConstraints();
		gbc_lblAddProduct.fill = GridBagConstraints.VERTICAL;
		gbc_lblAddProduct.gridwidth = 8;
		gbc_lblAddProduct.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddProduct.gridx = 1;
		gbc_lblAddProduct.gridy = 1;
		jPanel.add(lblInventory, gbc_lblAddProduct);
		
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 8;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 2;
		jPanel.add(scrollPane, gbc_scrollPane);
		
		scrollPane.setViewportView(productTable);
		
		GridBagConstraints gbc_lblProductId = new GridBagConstraints();
		gbc_lblProductId.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblProductId.insets = new Insets(0, 0, 5, 5);
		gbc_lblProductId.gridx = 1;
		gbc_lblProductId.gridy = 3;
		jPanel.add(lblProductId, gbc_lblProductId);
		
		GridBagConstraints gbc_lblDate = new GridBagConstraints();
		gbc_lblDate.fill = GridBagConstraints.BOTH;
		gbc_lblDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblDate.gridx = 2;
		gbc_lblDate.gridy = 3;
		jPanel.add(lblDate, gbc_lblDate);
		
		GridBagConstraints gbc_lblProductName = new GridBagConstraints();
		gbc_lblProductName.anchor = GridBagConstraints.WEST;
		gbc_lblProductName.insets = new Insets(0, 0, 5, 5);
		gbc_lblProductName.gridx = 3;
		gbc_lblProductName.gridy = 3;
		jPanel.add(lblProductName, gbc_lblProductName);
		
		GridBagConstraints gbc_lblProductType = new GridBagConstraints();
		gbc_lblProductType.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblProductType.insets = new Insets(0, 0, 5, 5);
		gbc_lblProductType.gridx = 4;
		gbc_lblProductType.gridy = 3;
		jPanel.add(lblProductType, gbc_lblProductType);
		
		GridBagConstraints gbc_lblQuantity = new GridBagConstraints();
		gbc_lblQuantity.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblQuantity.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuantity.gridx = 5;
		gbc_lblQuantity.gridy = 3;
		jPanel.add(lblQuantity, gbc_lblQuantity);
		
		GridBagConstraints gbc_lblBuyingPrice = new GridBagConstraints();
		gbc_lblBuyingPrice.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblBuyingPrice.insets = new Insets(0, 0, 5, 5);
		gbc_lblBuyingPrice.gridx = 6;
		gbc_lblBuyingPrice.gridy = 3;
		jPanel.add(lblTotalCost, gbc_lblBuyingPrice);
		
		GridBagConstraints gbc_lblOrigin = new GridBagConstraints();
		gbc_lblOrigin.anchor = GridBagConstraints.WEST;
		gbc_lblOrigin.insets = new Insets(0, 0, 5, 5);
		gbc_lblOrigin.gridx = 7;
		gbc_lblOrigin.gridy = 3;
		jPanel.add(lblOrigin, gbc_lblOrigin);
		
		GridBagConstraints gbc_lblPicture = new GridBagConstraints();
		gbc_lblPicture.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPicture.insets = new Insets(0, 0, 5, 5);
		gbc_lblPicture.gridx = 8;
		gbc_lblPicture.gridy = 3;
		jPanel.add(lblPicture, gbc_lblPicture);
		
		GridBagConstraints gbc_lblgeneratedBy = new GridBagConstraints();
		gbc_lblgeneratedBy.fill = GridBagConstraints.BOTH;
		gbc_lblgeneratedBy.insets = new Insets(0, 0, 5, 5);
		gbc_lblgeneratedBy.gridx = 1;
		gbc_lblgeneratedBy.gridy = 4;
		jPanel.add(lblgeneratedBy, gbc_lblgeneratedBy);
		
		GridBagConstraints gbc_txtDate = new GridBagConstraints();
		gbc_txtDate.fill = GridBagConstraints.BOTH;
		gbc_txtDate.insets = new Insets(0, 0, 5, 5);
		gbc_txtDate.gridx = 2;
		gbc_txtDate.gridy = 4;
		jPanel.add(txtDate, gbc_txtDate);
		txtDate.setColumns(10);
		
		GridBagConstraints gbc_txtProductName = new GridBagConstraints();
		gbc_txtProductName.insets = new Insets(0, 0, 5, 5);
		gbc_txtProductName.fill = GridBagConstraints.BOTH;
		gbc_txtProductName.gridx = 3;
		gbc_txtProductName.gridy = 4;
		jPanel.add(txtProductName, gbc_txtProductName);
		
		GridBagConstraints gbc_cmbProductType = new GridBagConstraints();
		gbc_cmbProductType.fill = GridBagConstraints.BOTH;
		gbc_cmbProductType.insets = new Insets(0, 0, 5, 5);
		gbc_cmbProductType.gridx = 4;
		gbc_cmbProductType.gridy = 4;
		jPanel.add(cmbProductType, gbc_cmbProductType);
		
		GridBagConstraints gbc_txtQuantity = new GridBagConstraints();
		gbc_txtQuantity.fill = GridBagConstraints.BOTH;
		gbc_txtQuantity.insets = new Insets(0, 0, 5, 5);
		gbc_txtQuantity.gridx = 5;
		gbc_txtQuantity.gridy = 4;
		jPanel.add(txtQuantity, gbc_txtQuantity);
		txtQuantity.setColumns(10);
		
		GridBagConstraints gbc_txtBuyingPrice = new GridBagConstraints();
		gbc_txtBuyingPrice.fill = GridBagConstraints.BOTH;
		gbc_txtBuyingPrice.insets = new Insets(0, 0, 5, 5);
		gbc_txtBuyingPrice.gridx = 6;
		gbc_txtBuyingPrice.gridy = 4;
		jPanel.add(txtBuyingPrice, gbc_txtBuyingPrice);
		txtBuyingPrice.setColumns(10);
		
		GridBagConstraints gbc_txtOrigin = new GridBagConstraints();
		gbc_txtOrigin.insets = new Insets(0, 0, 5, 5);
		gbc_txtOrigin.fill = GridBagConstraints.BOTH;
		gbc_txtOrigin.gridx = 7;
		gbc_txtOrigin.gridy = 4;
		jPanel.add(txtOrigin, gbc_txtOrigin);
		
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.insets = new Insets(0, 0, 5, 5);
		gbc_btnAdd.fill = GridBagConstraints.BOTH;
		gbc_btnAdd.gridx = 8;
		gbc_btnAdd.gridy = 5;
		btnAdd.addActionListener(act);
		
		GridBagConstraints gbc_btnAddPicture = new GridBagConstraints();
		gbc_btnAddPicture.fill = GridBagConstraints.BOTH;
		gbc_btnAddPicture.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddPicture.gridx = 8;
		gbc_btnAddPicture.gridy = 4;
		btnAddPicture.addActionListener(act);
		jPanel.add(btnAddPicture, gbc_btnAddPicture);
		jPanel.add(btnAdd, gbc_btnAdd);
		
		DefaultTableModel model = new DefaultTableModel(new Object[]{"Date Bought", "Product Name", "Product Type", "Quantity", "Total Cost", "Place Bought"}, 0) {
 		   @Override
 		   public boolean isCellEditable(int row, int column) {
 		       return false;
 		   }
		};
		
		ArrayList<Product> products = db.getProducts();
		
		for(int i = 0; i < products.size(); i++)
			model.addRow(new Object[]{products.get(i).getBuyDate(), products.get(i).getProductName(), products.get(i).getProductTypeID().getProductTypeId(), products.get(i).getQuantity(), products.get(i).getBuyPrice(), products.get(i).getBuyOrigin()});
		
		productTable.setModel(model);
	}
	
	
	public JPanel getJPanel() {
		return jPanel;
	}
	
	private class ActListener implements ActionListener{
    	public void actionPerformed(ActionEvent e){
    		if(e.getSource() == btnAddPicture){
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

