package GUI.Product;

import java.awt.Color;

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
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
import Product.PictureFinder;
import Product.Product;
import Receipt.Receipt;
import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

public class EditProduct extends JFrame implements ActionListener {
	private DBConnect db = new DBConnect();
	private DecimalFormat df = new DecimalFormat("#.00");
	
	ActionListener act = new ActListener();
	JPanel jPanel = new JPanel();

	// Text Fields
	private JTextField txtNewProductName = new JTextField();
	private JTextField txtTotalPrice = new JTextField();
	private JTextField txtQuantity = new JTextField();
	private JTextField txtDate = new JTextField();
	private JTextField txtOrigin = new JTextField();
	private JTextField txtPicture = new JTextField();

	// Labels
	private JLabel lblEditProduct = new JLabel("EDIT PRODUCT");
	private JLabel lblProductNo = new JLabel("Product No:");
	private JLabel lblProductNumber;
	private JLabel lblTotalPrice = new JLabel("Price:");
	private JLabel lblCustomer;
	private JLabel lblPicture = new JLabel("Picture");
	private JLabel lblNewProductName = new JLabel("Product Name");
	private JLabel lblOldProductName = new JLabel("Old Product Name");
	private JLabel lbloldProductName = new JLabel("New label");
	private JLabel lblDate = new JLabel("Date:");
	private JLabel lblQuantity = new JLabel("Quantity:");

	private JButton btnChange = new JButton("Set");
	private JButton btnSet = new JButton("Set");
	private JButton btnChangePicture = new JButton("Change Picture");
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
	
	private JFrame calenderFrame;
	private JXDatePicker picker;
	private Date dateFrom;
	private String resultDateFrom;
	

	private PictureFinder pictureFinder;
	private JButton btnPreview = new JButton("Preview");
	public static ImageIcon IMAGE = null;
	private JFrame jFrame = new JFrame();

	public EditProduct(MainGUI mainGUI, int productID, Date dateBought, String productName, String productType,
			int quantity, double totalCost, String origin, String picture) {

		jFrame = this;

		jFrame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				mainGUI.getJFrame().setEnabled(true);

			}
		});
		
		mainGUI.getJFrame().setEnabled(false);
		
		ActListener act = new ActListener();

		txtPicture.setColumns(10);
		txtQuantity.setColumns(10);
		jPanel.setLayout(
				new MigLayout("", "[52px][57px,grow][][][][][][][][][][][]", "[20px][20px][23px][][][][][][][][][]"));
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

		jPanel.add(lbloldProductName, "cell 1 2 10 1");

		jPanel.add(lblNewProductName, "cell 0 3,alignx trailing");
		txtNewProductName.setColumns(10);

		jPanel.add(txtNewProductName, "cell 1 3 10 1,growx,aligny top");

		// Text Fields
		jPanel.add(txtTotalPrice, "cell 1 4 10 1,growx");
		txtTotalPrice.setColumns(10);

		// Labels
		lblEditProduct.setFont(new Font("Tahoma", Font.BOLD, 13));
		jPanel.add(lblEditProduct, "cell 0 0");

		jPanel.add(lblProductNo, "cell 0 1,alignx right");

		lblProductNumber = new JLabel(Integer.toString(productID));
		lblProductNumber.setFont(new Font("Tahoma", Font.BOLD, 11));
		jPanel.add(lblProductNumber, "cell 1 1 10 1");
		jPanel.add(lblTotalPrice, "cell 0 4,alignx trailing");

		getContentPane().add(jPanel);

		jPanel.add(lblQuantity, "cell 0 5,alignx trailing");

		jPanel.add(txtQuantity, "cell 1 5 10 1,growx,aligny top");

		jPanel.add(lblDate, "cell 0 6,alignx trailing");
		txtDate.setColumns(10);

		jPanel.add(txtDate, "cell 1 6 7 1,growx");

		jPanel.add(btnChange, "cell 8 6 3 1");

		JLabel lblOrigin = new JLabel("Origin:");
		jPanel.add(lblOrigin, "cell 0 7,alignx trailing");
		txtOrigin.setColumns(10);

		jPanel.add(txtOrigin, "cell 1 7 10 1,growx");

		jPanel.add(lblPicture, "cell 0 8,alignx trailing");

		lbloldProductName.setText(productName);
		txtNewProductName.setText(productName);
		txtTotalPrice.setText(df.format(totalCost));
		txtQuantity.setText(Integer.toString(quantity));
		txtDate.setText(dateBought.toString());
		txtOrigin.setText(origin);
		txtPicture.setText(picture);

		jPanel.add(txtPicture, "cell 1 8 10 1,growx");
		btnChangePicture.setHorizontalAlignment(SwingConstants.LEADING);

		jPanel.add(btnChangePicture, "flowx,cell 1 9 3 1,growx");

		jPanel.add(btnPreview, "cell 4 9,growx");

		//
		// // Check Boxes
		// chckbxCheckInput = new JCheckBox("Check Input");
		// jPanel.add(chckbxCheckInput, "cell 1 10");
		// chckbxCheckInput.addActionListener(act);

		// Buttons
		btnEdit = new JButton("Edit");
		jPanel.add(btnEdit, "cell 1 11 10 1,growx");
		btnChangePicture.addActionListener(act);
		btnEdit.addActionListener(act);
		btnChange.addActionListener(act);
		btnSet.addActionListener(act);

		this.setSize(400, 388);

		dateFrom = dateBought;
		txtDate.setEnabled(false);
	
		btnPreview.addActionListener(act);
		
		txtNewProductName.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e){
				changed();
			}
			public void removeUpdate(DocumentEvent e){
				changed();
			}
			public void insertUpdate(DocumentEvent e){
				changed();
			}
			@SuppressWarnings("deprecation")
			public void changed() {
				if (txtNewProductName.getText().equals("") ||txtTotalPrice.getText().equals("") || txtQuantity.getText().equals("") 
						|| txtDate.getText().equals("") || txtOrigin.getText().equals("") || txtPicture.getText().equals(""))
					btnEdit.setEnabled(false);
				else 
					btnEdit.setEnabled(true);
			}
		});
		
		txtTotalPrice.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e){
				changed();
			}
			public void removeUpdate(DocumentEvent e){
				changed();
			}
			public void insertUpdate(DocumentEvent e){
				changed();
			}
			@SuppressWarnings("deprecation")
			public void changed() {
				if (txtNewProductName.getText().equals("") ||txtTotalPrice.getText().equals("") || txtQuantity.getText().equals("") 
						|| txtDate.getText().equals("") || txtOrigin.getText().equals("") || txtPicture.getText().equals(""))
					btnEdit.setEnabled(false);
				else 
					btnEdit.setEnabled(true);
			}
		});
		
		txtQuantity.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e){
				changed();
			}
			public void removeUpdate(DocumentEvent e){
				changed();
			}
			public void insertUpdate(DocumentEvent e){
				changed();
			}
			@SuppressWarnings("deprecation")
			public void changed() {
				if (txtNewProductName.getText().equals("") ||txtTotalPrice.getText().equals("") || txtQuantity.getText().equals("") 
						|| txtDate.getText().equals("") || txtOrigin.getText().equals("") || txtPicture.getText().equals(""))
					btnEdit.setEnabled(false);
				else 
					btnEdit.setEnabled(true);
			}
		});
		
		txtDate.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e){
				changed();
			}
			public void removeUpdate(DocumentEvent e){
				changed();
			}
			public void insertUpdate(DocumentEvent e){
				changed();
			}
			@SuppressWarnings("deprecation")
			public void changed() {
				if (txtNewProductName.getText().equals("") ||txtTotalPrice.getText().equals("") || txtQuantity.getText().equals("") 
						|| txtDate.getText().equals("") || txtOrigin.getText().equals("") || txtPicture.getText().equals(""))
					btnEdit.setEnabled(false);
				else 
					btnEdit.setEnabled(true);
			}
		});
		
		txtOrigin.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e){
				changed();
			}
			public void removeUpdate(DocumentEvent e){
				changed();
			}
			public void insertUpdate(DocumentEvent e){
				changed();
			}
			@SuppressWarnings("deprecation")
			public void changed() {
				if (txtNewProductName.getText().equals("") ||txtTotalPrice.getText().equals("") || txtQuantity.getText().equals("") 
						|| txtDate.getText().equals("") || txtOrigin.getText().equals("") || txtPicture.getText().equals(""))
					btnEdit.setEnabled(false);
				else 
					btnEdit.setEnabled(true);
			}
		});
		
		txtPicture.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e){
				changed();
			}
			public void removeUpdate(DocumentEvent e){
				changed();
			}
			public void insertUpdate(DocumentEvent e){
				changed();
			}
			@SuppressWarnings("deprecation")
			public void changed() {
				if (txtNewProductName.getText().equals("") ||txtTotalPrice.getText().equals("") || txtQuantity.getText().equals("") 
						|| txtDate.getText().equals("") || txtOrigin.getText().equals("") || txtPicture.getText().equals(""))
					btnEdit.setEnabled(false);
				else 
					btnEdit.setEnabled(true);
			}
		});
	}

	public JPanel getJPanel() {
		return jPanel;
	}

	private class ActListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			if (a.getSource() == btnChangePicture) {
				pictureFinder = new PictureFinder();
				txtPicture.setText(pictureFinder.getPicturePath());
			}
			
			if (a.getSource() == btnPreview) {

				String filePath = txtPicture.getText();

				if (filePath.equals("")) {
					JOptionPane.showMessageDialog(null, "No picture for selected product or no product selected!");
				} else {
					try {
						IMAGE = new ImageIcon(filePath);
					} catch (Exception q) {
						q.printStackTrace();
					}
					JLabel image = new JLabel(IMAGE);
					JDialog dialog = new JDialog();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setTitle("Image Preview");
					dialog.getContentPane().add(image);
					dialog.pack();
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);
				}
			}
			
			if (a.getSource() == btnEdit) {
				boolean isNumQuantity;  	   // number input checker for quantity
				boolean isNumTotalCost = true; // number input checker for total cost
				boolean dec = true;			   // 2 decimal places checker
				
				try {
					Double d = Double.parseDouble(txtTotalPrice.getText());
					String[] split = d.toString().split("\\.");

					if (split[1].length() > 2) 
						dec = false;
					
				} catch (NumberFormatException n) {
					isNumTotalCost = false;
				}
				
				isNumQuantity = Pattern.matches("^\\d*$", txtQuantity.getText());
				
				if (isNumQuantity == false) {//check if txtQuantity input is numeric
					JOptionPane.showMessageDialog(null, "Please input numerical quantity!");
					txtQuantity.setText("");
				}else if (isNumTotalCost == false) { // check if txtTotalCost is numeric
					JOptionPane.showMessageDialog(null, "Please input numbers only on the price!");
					txtTotalPrice.setText("");
				}else if (dec == false) { //2 decimal places checker
					JOptionPane.showMessageDialog(null, "Decimal places are limited to 2!");
					txtTotalPrice.setText("");
				}else if (Double.parseDouble(txtTotalPrice.getText()) < 0) {//positive number checker
					JOptionPane.showMessageDialog(null, "Please input numbers not less than 0!");
					txtTotalPrice.setText("");
				}
//				else if(cmbProductType.getSelectedItem().toString().equals("Select")){//Check if there is a selected product type
//					JOptionPane.showMessageDialog(null, "Please select Product Type!");
//				}
				else{
				Product product = new Product(Integer.parseInt(txtQuantity.getText()), dateFrom,
						txtNewProductName.getText(), Double.parseDouble(txtTotalPrice.getText()),
						Integer.parseInt(lblProductNumber.getText()),
//						db.getProductTypeID(cmbProductType.getSelectedItem().toString())
						1, txtPicture.getText(),
						txtOrigin.getText());

				db.editProduct(product);
				JOptionPane.showMessageDialog(null, "Transaction was successfully edited");
				
				mainGUI.removeAllRightSplit();
				AddProduct addProduct = new AddProduct(mainGUI);
				mainGUI.setRightSplit(addProduct.getJPanel());
				mainGUI.getJFrame().setEnabled(true);
				dispose();
				}
			}

			if (a.getSource() == btnChange) {
				// for calendar
				calenderFrame = new JFrame("Calendar");
				JPanel panel = new JPanel();
				calenderFrame.setBounds(400, 400, 250, 100);

				picker = new JXDatePicker();

				picker.setFormats(new SimpleDateFormat("dd/MM/yyyy"));

				picker.setDate(dateBought);

				panel.add(picker);
				panel.add(btnSet);
				calenderFrame.getContentPane().add(panel);

				calenderFrame.setVisible(true);

			}

			if (a.getSource() == btnSet) {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
				dateFrom = picker.getDate();
				resultDateFrom = formatter.format(dateFrom);

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