package GUI.Product;

import java.awt.Color;

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Branch.Branch;
import DB.DBConnect;
import GUI.MainGUI;
import GUI.ControlPanel.GUIClientLandingPanel;
import GUI.Receipt.POSReceipt;
import Product.ManagerProduct;
import Product.Product;
import Receipt.Receipt;
import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

public class EditProduct extends JFrame implements ActionListener {
	private DBConnect db = new DBConnect();

	
	ActionListener act = new ActListener();
	JPanel jPanel = new JPanel();
	private JComboBox cmbProductType;

	// Text Fields
	private JTextField txtTotalPrice = new JTextField();

	// Labels
	private JLabel lblEditProduct = new JLabel("EDIT PRODUCT");
	private JLabel lblProductNo = new JLabel("Product No:");
	private JLabel lblProductNumber;
	private JLabel lblTotalPrice = new JLabel("Price:");
	private JLabel lblProductType = new JLabel("Product Type:");
	private JLabel lblCustomer;
	private JLabel lblPicture = new JLabel("Picture");

	// Button
	private JButton btnEdit;

	// Check Box
	private JCheckBox chckbxCheckInput;

	private MainGUI mainGUI;

	private int productID;
	private Date dateBought;
	private String productName;
	private String productType;
	private int quantity;
	private double totalCost;
	private String origin;
	private String picture;

	private int oldQuantity;
	private boolean priceField;
	private boolean staffField;
	private final JLabel lblNewProductName = new JLabel("Product Name");
	private final JTextField txtNewProductName = new JTextField();
	private final JLabel lblOldProductName = new JLabel("Old Product Name");
	private final JLabel lbloldProductName = new JLabel("New label");
	private final JLabel lblDate = new JLabel("Date:");
	private final JTextField txtDate = new JTextField();
	private final JButton btnSet = new JButton("Set");
	private final JTextField txtOrigin = new JTextField();
	private final JButton btnChangePicture = new JButton("Change Picture");
	private final JLabel lblQuantity = new JLabel("Quantity:");
	private final JTextField txtQuantity = new JTextField();
	private final JTextField txtPicture = new JTextField();
	
	private JFrame calenderFrame;
	private JXDatePicker picker;
	private Date dateFrom;
	private String resultDateFrom;


	public EditProduct(MainGUI mainGUI, int productID, Date dateBought, String productName, String productType,
			int quantity, double totalCost, String origin, String picture) {
		
		ActListener act = new ActListener();
		
		txtPicture.setColumns(10);
		txtQuantity.setColumns(10);
		jPanel.setLayout(
				new MigLayout("", "[52px][57px,grow][][][][][][][][][]", "[20px][20px][23px][][][][][][][][][][]"));
		jPanel.setSize(300, 250);
		jPanel.setOpaque(true);

		this.mainGUI = mainGUI;
		this.productID = productID;
		this.dateBought = dateBought;
		this.productName = productName;
		this.productType = productType;
		this.quantity = quantity;
		this.totalCost = totalCost;
		this.origin = origin;
		this.picture = picture;

		this.setResizable(false);
		this.setTitle("Edit Product");
		this.setVisible(true);

		jPanel.add(lblOldProductName, "cell 0 2");

		jPanel.add(lbloldProductName, "cell 1 2 8 1");

		jPanel.add(lblNewProductName, "cell 0 3,alignx trailing");
		txtNewProductName.setColumns(10);

		jPanel.add(txtNewProductName, "cell 1 3 8 1,growx,aligny top");
		
		cmbProductType = new JComboBox(db.getProductTypeNames().toArray());

		
		jPanel.add(cmbProductType, "cell 1 5 8 1,growx");

		// Text Fields
		jPanel.add(txtTotalPrice, "cell 1 4 8 1,growx");
		txtTotalPrice.setColumns(10);

		// Labels
		lblEditProduct.setFont(new Font("Tahoma", Font.BOLD, 13));
		jPanel.add(lblEditProduct, "cell 0 0");

		jPanel.add(lblProductNo, "cell 0 1,alignx right");

		lblProductNumber = new JLabel(Integer.toString(productID));
		lblProductNumber.setFont(new Font("Tahoma", Font.BOLD, 11));
		jPanel.add(lblProductNumber, "cell 1 1 8 1");
		jPanel.add(lblTotalPrice, "cell 0 4,alignx trailing");
		jPanel.add(lblProductType, "cell 0 5,alignx trailing");

		

		AutoCompleteDecorator.decorate(this.cmbProductType);

		getContentPane().add(jPanel);

		jPanel.add(lblQuantity, "cell 0 6,alignx trailing");

		jPanel.add(txtQuantity, "cell 1 6 8 1,growx,aligny top");

		jPanel.add(lblDate, "cell 0 7,alignx trailing");
		txtDate.setColumns(10);

		jPanel.add(txtDate, "cell 1 7 6 1,growx");

		jPanel.add(btnSet, "cell 7 7 2 1");

		JLabel lblOrigin = new JLabel("Origin:");
		jPanel.add(lblOrigin, "cell 0 8,alignx trailing");
		txtOrigin.setColumns(10);

		jPanel.add(txtOrigin, "cell 1 8 8 1,growx");

		jPanel.add(lblPicture, "cell 0 9,alignx trailing");
		
		lbloldProductName.setText(productName);
		txtNewProductName.setText(productName);
		txtTotalPrice.setText(Double.toString(totalCost));
		cmbProductType.setSelectedItem(productType);
		txtQuantity.setText(Integer.toString(quantity));
		txtDate.setText(dateBought.toString());
		txtOrigin.setText(origin);
		txtPicture.setText(picture);
		
		jPanel.add(txtPicture, "cell 1 9 8 1,growx");
		btnChangePicture.setHorizontalAlignment(SwingConstants.LEADING);
		
				jPanel.add(btnChangePicture, "cell 1 10 8 1");
		
		
		
//
//		// Check Boxes
//		chckbxCheckInput = new JCheckBox("Check Input");
//		jPanel.add(chckbxCheckInput, "cell 1 10");
//		chckbxCheckInput.addActionListener(act);

		// Buttons
		btnEdit = new JButton("Edit");
		jPanel.add(btnEdit, "cell 1 12 8 1,growx");
		btnChangePicture.addActionListener(act);
		btnEdit.addActionListener(act);
		btnEdit.setEnabled(true);
		this.setSize(400, 388);

	}

	public JPanel getJPanel() {
		return jPanel;
	}

	private class ActListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			if (a.getSource() == btnEdit){
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    			String dateInString = resultDateFrom;
			

				Product product = new Product(Integer.parseInt(txtQuantity.getText()), dateFrom, txtNewProductName.getText(), 
						Double.parseDouble(txtTotalPrice.getText()), Integer.parseInt(lblProductNumber.getText()), db.getProductTypeID(cmbProductType.getSelectedItem().toString()),
						txtPicture.getText(), txtOrigin.getText());
						//db.editReceipt(receipt);
						JOptionPane.showMessageDialog(null, "Transaction was successfully edited");
	
						mainGUI.removeAllRightSplit();
						POSReceipt posReceipt = new POSReceipt(mainGUI);
						mainGUI.setRightSplit(posReceipt.getJPanel());
						dispose();
			}
			
			if(a.getSource() == btnSet){
    			//for calendar
    			calenderFrame = new JFrame("Calendar");
    	        JPanel panel = new JPanel();
    	        calenderFrame.setBounds(400, 400, 250, 100);
 
    	        picker = new JXDatePicker();
    	        
    	        picker.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
    	        
//    	        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//    	        Date dateInDate = null;
//				try {
//					dateInDate = df.parse(dateBought);
//				} catch (ParseException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
    	        
    	        picker.setDate(dateBought);
 
    	        panel.add(picker);
    	        panel.add(btnSet);
    	        calenderFrame.getContentPane().add(panel);

    	        calenderFrame.setVisible(true);
    		}
    		
    		if(a.getSource() == btnSet)
	     	{
    			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
    	     	dateFrom = picker.getDate();
    	     	resultDateFrom = formatter.format(dateFrom);
    	     	System.out.println(resultDateFrom);
    	     	
    			txtDate.setText("");
    			
    	        txtDate.setText(resultDateFrom);
    	        
    	        
    	   
    	        calenderFrame.dispose();
    	        
	     	}
    		
	}
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}