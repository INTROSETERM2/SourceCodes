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
import java.awt.Font;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JComboBox;

public class AddProduct {
	
	JPanel jPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTable table;
	
	public AddProduct(){
		
	jPanel.setSize(600,450);
	GridBagLayout gbl_jPanel = new GridBagLayout();
	gbl_jPanel.columnWidths = new int[]{45, 88, 89, 86, 177, 0};
	gbl_jPanel.rowHeights = new int[]{48, 17, 161, 14, 20, 14, 20, 35, 23, 0};
	gbl_jPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	gbl_jPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	jPanel.setLayout(gbl_jPanel);
	
	JLabel lblAddProduct = new JLabel("Add Product");
	lblAddProduct.setFont(new Font("Tahoma", Font.PLAIN, 14));
	GridBagConstraints gbc_lblAddProduct = new GridBagConstraints();
	gbc_lblAddProduct.anchor = GridBagConstraints.NORTHWEST;
	gbc_lblAddProduct.insets = new Insets(0, 0, 5, 5);
	gbc_lblAddProduct.gridx = 1;
	gbc_lblAddProduct.gridy = 1;
	jPanel.add(lblAddProduct, gbc_lblAddProduct);
	
	table = new JTable();
	GridBagConstraints gbc_table = new GridBagConstraints();
	gbc_table.fill = GridBagConstraints.BOTH;
	gbc_table.insets = new Insets(0, 0, 5, 0);
	gbc_table.gridwidth = 4;
	gbc_table.gridx = 1;
	gbc_table.gridy = 2;
	jPanel.add(table, gbc_table);
	
	JLabel lblDate = new JLabel("Date");
	GridBagConstraints gbc_lblDate = new GridBagConstraints();
	gbc_lblDate.anchor = GridBagConstraints.NORTH;
	gbc_lblDate.fill = GridBagConstraints.HORIZONTAL;
	gbc_lblDate.insets = new Insets(0, 0, 5, 5);
	gbc_lblDate.gridx = 1;
	gbc_lblDate.gridy = 3;
	jPanel.add(lblDate, gbc_lblDate);
	
	textField = new JTextField();
	GridBagConstraints gbc_textField = new GridBagConstraints();
	gbc_textField.anchor = GridBagConstraints.NORTH;
	gbc_textField.fill = GridBagConstraints.HORIZONTAL;
	gbc_textField.insets = new Insets(0, 0, 5, 5);
	gbc_textField.gridx = 1;
	gbc_textField.gridy = 4;
	jPanel.add(textField, gbc_textField);
	textField.setColumns(10);
	
	JLabel lblProductId = new JLabel("Product ID");
	GridBagConstraints gbc_lblProductId = new GridBagConstraints();
	gbc_lblProductId.anchor = GridBagConstraints.NORTH;
	gbc_lblProductId.fill = GridBagConstraints.HORIZONTAL;
	gbc_lblProductId.insets = new Insets(0, 0, 5, 5);
	gbc_lblProductId.gridx = 1;
	gbc_lblProductId.gridy = 5;
	jPanel.add(lblProductId, gbc_lblProductId);
	
	JLabel lblProductType = new JLabel("Product Type");
	GridBagConstraints gbc_lblProductType = new GridBagConstraints();
	gbc_lblProductType.anchor = GridBagConstraints.NORTH;
	gbc_lblProductType.fill = GridBagConstraints.HORIZONTAL;
	gbc_lblProductType.insets = new Insets(0, 0, 5, 5);
	gbc_lblProductType.gridx = 2;
	gbc_lblProductType.gridy = 5;
	jPanel.add(lblProductType, gbc_lblProductType);
	
	JLabel lblQuantity = new JLabel("Quantity");
	GridBagConstraints gbc_lblQuantity = new GridBagConstraints();
	gbc_lblQuantity.anchor = GridBagConstraints.NORTHWEST;
	gbc_lblQuantity.insets = new Insets(0, 0, 5, 5);
	gbc_lblQuantity.gridx = 3;
	gbc_lblQuantity.gridy = 5;
	jPanel.add(lblQuantity, gbc_lblQuantity);
	
	JLabel lblBuyingPrice = new JLabel("Buying price");
	GridBagConstraints gbc_lblBuyingPrice = new GridBagConstraints();
	gbc_lblBuyingPrice.anchor = GridBagConstraints.NORTH;
	gbc_lblBuyingPrice.fill = GridBagConstraints.HORIZONTAL;
	gbc_lblBuyingPrice.insets = new Insets(0, 0, 5, 0);
	gbc_lblBuyingPrice.gridx = 4;
	gbc_lblBuyingPrice.gridy = 5;
	jPanel.add(lblBuyingPrice, gbc_lblBuyingPrice);
	
	JLabel lblgeneratedBy = new JLabel("#####");
	GridBagConstraints gbc_lblgeneratedBy = new GridBagConstraints();
	gbc_lblgeneratedBy.anchor = GridBagConstraints.NORTH;
	gbc_lblgeneratedBy.fill = GridBagConstraints.HORIZONTAL;
	gbc_lblgeneratedBy.insets = new Insets(0, 0, 5, 5);
	gbc_lblgeneratedBy.gridx = 1;
	gbc_lblgeneratedBy.gridy = 6;
	jPanel.add(lblgeneratedBy, gbc_lblgeneratedBy);
	
	JComboBox comboBox = new JComboBox();
	GridBagConstraints gbc_comboBox = new GridBagConstraints();
	gbc_comboBox.anchor = GridBagConstraints.NORTH;
	gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
	gbc_comboBox.insets = new Insets(0, 0, 5, 5);
	gbc_comboBox.gridx = 2;
	gbc_comboBox.gridy = 6;
	jPanel.add(comboBox, gbc_comboBox);
	
	textField_1 = new JTextField();
	GridBagConstraints gbc_textField_1 = new GridBagConstraints();
	gbc_textField_1.anchor = GridBagConstraints.NORTHWEST;
	gbc_textField_1.insets = new Insets(0, 0, 5, 5);
	gbc_textField_1.gridx = 3;
	gbc_textField_1.gridy = 6;
	jPanel.add(textField_1, gbc_textField_1);
	textField_1.setColumns(10);
	
	textField_3 = new JTextField();
	GridBagConstraints gbc_textField_3 = new GridBagConstraints();
	gbc_textField_3.anchor = GridBagConstraints.NORTH;
	gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
	gbc_textField_3.insets = new Insets(0, 0, 5, 0);
	gbc_textField_3.gridx = 4;
	gbc_textField_3.gridy = 6;
	jPanel.add(textField_3, gbc_textField_3);
	textField_3.setColumns(10);
	
	JButton btnAdd = new JButton("ADD");
	GridBagConstraints gbc_btnAdd = new GridBagConstraints();
	gbc_btnAdd.anchor = GridBagConstraints.NORTH;
	gbc_btnAdd.fill = GridBagConstraints.HORIZONTAL;
	gbc_btnAdd.gridx = 4;
	gbc_btnAdd.gridy = 8;
	jPanel.add(btnAdd, gbc_btnAdd);
	}
	
	
	public JPanel getJPanel() {
		return jPanel;
	}
}

