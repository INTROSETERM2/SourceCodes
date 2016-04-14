package GUI.Product;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ProductType.ManagerProductType;
import ProductType.ProductType;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class AddProductType {
	private JFrame mainFrame = new JFrame("Add Product Type");
	private JPanel deletePanel = new JPanel();
	private JPanel addPanel = new JPanel();
	private JLabel lblAddProductType = new JLabel("Product Type: ");
	private JTextField txtNewProductType = new JTextField();
	private JButton btnAdd = new JButton("Add");
	private JLabel lblDelProductType = new JLabel("Product Type:");
	private JComboBox cmbProductTypes = new JComboBox();
	private JButton btnDelete = new JButton("Delete");
	
	private ManagerProductType manProTyp = new ManagerProductType();
	private ArrayList<ProductType> productTypes = manProTyp.getProductTypes();

	public AddProductType(){
		ActListener act = new ActListener();
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		mainFrame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		tabbedPane.add(
				"<html><body><table width='150' height='5'><tr><td><center>Add Product Type</center></td></tr></table></body></html>",
				addPanel);
		addPanel.setLayout(new MigLayout("", "[100.00][100.00][120.00][100.00]", "[30.00][][]"));
		
		addPanel.add(lblAddProductType, "cell 1 1,alignx right");
		
		addPanel.add(txtNewProductType, "cell 2 1,grow");
		
		addPanel.add(btnAdd, "cell 2 2,grow");
		
		
		tabbedPane.add(
				"<html><body><table width='150' height='5'><tr><td><center>Delete Product Type</center></td></tr></table></body></html>",
				deletePanel);
		deletePanel.setLayout(new MigLayout("", "[100.00][100.00][120.00][100.00]", "[30.00][][]"));
		
		deletePanel.add(lblDelProductType, "cell 1 1,alignx right");
		
		deletePanel.add(cmbProductTypes, "cell 2 1,grow");
		
		deletePanel.add(btnDelete, "cell 2 2,grow");
		
		for (int i = 0; i < productTypes.size(); i++) 
			cmbProductTypes.addItem(productTypes.get(i).getProductTypeName());
		
		btnAdd.addActionListener(act);
		btnDelete.addActionListener(act);
	}
	
	private class ActListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			if(a.getSource() == btnAdd){
				boolean sameProductType = false;
				for (int i = 0; i < productTypes.size(); i++) {
					if(productTypes.get(i).getProductTypeName().equals(txtNewProductType.getText()))
						sameProductType = true;
				}
			}
			
			if(a.getSource() == btnDelete){
				
			}
		}
	}
}
