package GUI.Product;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JComboBox;

public class AddProduct implements ActionListener{
	
	JPanel jPanel = new JPanel();
	private JTextField txtDate = new JTextField();
	private JTextField txtQuantity = new JTextField();
	private JTextField txtBuyingPrice = new JTextField();
	private JTable table = new JTable();
	private JLabel lblAddProduct = new JLabel("Add Product");
	private JLabel lblDate = new JLabel("Date");
	private JLabel lblProductId = new JLabel("Product ID");
	private JLabel lblProductType = new JLabel("Product Type");
	private JLabel lblQuantity = new JLabel("Quantity");
	private JLabel lblBuyingPrice = new JLabel("Buying price");
	private JLabel lblgeneratedBy = new JLabel("#####");
	private JComboBox cmbProductType = new JComboBox();
	private JButton btnAdd = new JButton("ADD");
	
	public AddProduct(){
		
		ActListener act = new ActListener();
			
		jPanel.setSize(600,450);
		GridBagLayout gbl_jPanel = new GridBagLayout();
		gbl_jPanel.columnWidths = new int[]{45, 88, 89, 86, 177, 0};
		gbl_jPanel.rowHeights = new int[]{48, 17, 161, 14, 20, 14, 20, 35, 23, 0};
		gbl_jPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_jPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		jPanel.setLayout(gbl_jPanel);
		
		lblAddProduct.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblAddProduct = new GridBagConstraints();
		gbc_lblAddProduct.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblAddProduct.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddProduct.gridx = 1;
		gbc_lblAddProduct.gridy = 1;
		jPanel.add(lblAddProduct, gbc_lblAddProduct);
		
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.insets = new Insets(0, 0, 5, 0);
		gbc_table.gridwidth = 4;
		gbc_table.gridx = 1;
		gbc_table.gridy = 2;
		jPanel.add(table, gbc_table);
		
		GridBagConstraints gbc_lblDate = new GridBagConstraints();
		gbc_lblDate.anchor = GridBagConstraints.NORTH;
		gbc_lblDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblDate.gridx = 1;
		gbc_lblDate.gridy = 3;
		jPanel.add(lblDate, gbc_lblDate);
		
		GridBagConstraints gbc_txtDate = new GridBagConstraints();
		gbc_txtDate.anchor = GridBagConstraints.NORTH;
		gbc_txtDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDate.insets = new Insets(0, 0, 5, 5);
		gbc_txtDate.gridx = 1;
		gbc_txtDate.gridy = 4;
		jPanel.add(txtDate, gbc_txtDate);
		txtDate.setColumns(10);
		
		GridBagConstraints gbc_lblProductId = new GridBagConstraints();
		gbc_lblProductId.anchor = GridBagConstraints.NORTH;
		gbc_lblProductId.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblProductId.insets = new Insets(0, 0, 5, 5);
		gbc_lblProductId.gridx = 1;
		gbc_lblProductId.gridy = 5;
		jPanel.add(lblProductId, gbc_lblProductId);
		
		GridBagConstraints gbc_lblProductType = new GridBagConstraints();
		gbc_lblProductType.anchor = GridBagConstraints.NORTH;
		gbc_lblProductType.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblProductType.insets = new Insets(0, 0, 5, 5);
		gbc_lblProductType.gridx = 2;
		gbc_lblProductType.gridy = 5;
		jPanel.add(lblProductType, gbc_lblProductType);
		
		GridBagConstraints gbc_lblQuantity = new GridBagConstraints();
		gbc_lblQuantity.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblQuantity.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuantity.gridx = 3;
		gbc_lblQuantity.gridy = 5;
		jPanel.add(lblQuantity, gbc_lblQuantity);
		
		GridBagConstraints gbc_lblBuyingPrice = new GridBagConstraints();
		gbc_lblBuyingPrice.anchor = GridBagConstraints.NORTH;
		gbc_lblBuyingPrice.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblBuyingPrice.insets = new Insets(0, 0, 5, 0);
		gbc_lblBuyingPrice.gridx = 4;
		gbc_lblBuyingPrice.gridy = 5;
		jPanel.add(lblBuyingPrice, gbc_lblBuyingPrice);
		
		GridBagConstraints gbc_lblgeneratedBy = new GridBagConstraints();
		gbc_lblgeneratedBy.anchor = GridBagConstraints.NORTH;
		gbc_lblgeneratedBy.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblgeneratedBy.insets = new Insets(0, 0, 5, 5);
		gbc_lblgeneratedBy.gridx = 1;
		gbc_lblgeneratedBy.gridy = 6;
		jPanel.add(lblgeneratedBy, gbc_lblgeneratedBy);
		
		GridBagConstraints gbc_cmbProductType = new GridBagConstraints();
		gbc_cmbProductType.anchor = GridBagConstraints.NORTH;
		gbc_cmbProductType.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbProductType.insets = new Insets(0, 0, 5, 5);
		gbc_cmbProductType.gridx = 2;
		gbc_cmbProductType.gridy = 6;
		jPanel.add(cmbProductType, gbc_cmbProductType);
		
		GridBagConstraints gbc_txtQuantity = new GridBagConstraints();
		gbc_txtQuantity.anchor = GridBagConstraints.NORTHWEST;
		gbc_txtQuantity.insets = new Insets(0, 0, 5, 5);
		gbc_txtQuantity.gridx = 3;
		gbc_txtQuantity.gridy = 6;
		jPanel.add(txtQuantity, gbc_txtQuantity);
		txtQuantity.setColumns(10);
		
		GridBagConstraints gbc_txtBuyingPrice = new GridBagConstraints();
		gbc_txtBuyingPrice.anchor = GridBagConstraints.NORTH;
		gbc_txtBuyingPrice.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBuyingPrice.insets = new Insets(0, 0, 5, 0);
		gbc_txtBuyingPrice.gridx = 4;
		gbc_txtBuyingPrice.gridy = 6;
		jPanel.add(txtBuyingPrice, gbc_txtBuyingPrice);
		txtBuyingPrice.setColumns(10);
		
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.anchor = GridBagConstraints.NORTH;
		gbc_btnAdd.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAdd.gridx = 4;
		gbc_btnAdd.gridy = 8;
		btnAdd.addActionListener(act);
		jPanel.add(btnAdd, gbc_btnAdd);
	}
	
	
	public JPanel getJPanel() {
		return jPanel;
	}
	
	private class ActListener implements ActionListener{
    	public void actionPerformed(ActionEvent e){
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

