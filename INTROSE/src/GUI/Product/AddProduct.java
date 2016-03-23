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
import javax.swing.JScrollPane;

public class AddProduct implements ActionListener{
	
	JPanel jPanel = new JPanel();
	
	// Text Fields
	private JTextField txtDate = new JTextField();
	private JTextField txtQuantity = new JTextField();
	private JTextField txtBuyingPrice = new JTextField();
	
	// Labels
	private JLabel lblAddProduct = new JLabel("Inventory");
	private JLabel lblDate = new JLabel("Date");
	private JLabel lblProductId = new JLabel("Product ID");
	private JLabel lblPicture = new JLabel("Picture");
	private JLabel lblProductType = new JLabel("Product Type");
	private JLabel lblQuantity = new JLabel("Quantity");
	private JLabel lblBuyingPrice = new JLabel("Buying price");
	private JLabel lblgeneratedBy = new JLabel("#####");
	private JComboBox cmbProductType = new JComboBox();
	private JButton btnAdd = new JButton("ADD");
	
	private JButton btnAddPicture = new JButton("Add Picture");
	private JScrollPane scrollPane = new JScrollPane();
	private JTable productTable = new JTable();
	
	public AddProduct(){
		
		ActListener act = new ActListener();
			
		jPanel.setSize(940,605);
		GridBagLayout gbl_jPanel = new GridBagLayout();
		gbl_jPanel.columnWidths = new int[]{50, 150, 150, 150, 150, 150, 150, 50, 0};
		gbl_jPanel.rowHeights = new int[]{20, 24, 360, 30, 30, 30, 20, 0};
		gbl_jPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_jPanel.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		jPanel.setLayout(gbl_jPanel);
		
		lblAddProduct.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblAddProduct = new GridBagConstraints();
		gbc_lblAddProduct.fill = GridBagConstraints.VERTICAL;
		gbc_lblAddProduct.gridwidth = 6;
		gbc_lblAddProduct.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddProduct.gridx = 1;
		gbc_lblAddProduct.gridy = 1;
		jPanel.add(lblAddProduct, gbc_lblAddProduct);
		
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 6;
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
		
		GridBagConstraints gbc_lblPicture = new GridBagConstraints();
		gbc_lblPicture.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPicture.insets = new Insets(0, 0, 5, 5);
		gbc_lblPicture.gridx = 3;
		gbc_lblPicture.gridy = 3;
		jPanel.add(lblPicture, gbc_lblPicture);
		
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
		jPanel.add(lblBuyingPrice, gbc_lblBuyingPrice);
		
		GridBagConstraints gbc_lblgeneratedBy = new GridBagConstraints();
		gbc_lblgeneratedBy.fill = GridBagConstraints.BOTH;
		gbc_lblgeneratedBy.insets = new Insets(0, 0, 5, 5);
		gbc_lblgeneratedBy.gridx = 1;
		gbc_lblgeneratedBy.gridy = 4;
		jPanel.add(lblgeneratedBy, gbc_lblgeneratedBy);
		
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.gridwidth = 6;
		gbc_btnAdd.insets = new Insets(0, 0, 5, 5);
		gbc_btnAdd.fill = GridBagConstraints.BOTH;
		gbc_btnAdd.gridx = 1;
		gbc_btnAdd.gridy = 5;
		btnAdd.addActionListener(act);
		
		GridBagConstraints gbc_txtDate = new GridBagConstraints();
		gbc_txtDate.fill = GridBagConstraints.BOTH;
		gbc_txtDate.insets = new Insets(0, 0, 5, 5);
		gbc_txtDate.gridx = 2;
		gbc_txtDate.gridy = 4;
		jPanel.add(txtDate, gbc_txtDate);
		txtDate.setColumns(10);
		
		GridBagConstraints gbc_btnAddPicture = new GridBagConstraints();
		gbc_btnAddPicture.fill = GridBagConstraints.BOTH;
		gbc_btnAddPicture.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddPicture.gridx = 3;
		gbc_btnAddPicture.gridy = 4;
		jPanel.add(btnAddPicture, gbc_btnAddPicture);
		
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

