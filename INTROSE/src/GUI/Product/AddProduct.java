package GUI.Product;

import java.awt.Font;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import Branch.Branch;

//import org.jdesktop.swingx.JXDatePicker;

import DB.DBConnect;
import GUI.MainGUI;
import GUI.Receipt.EditReceipt;
import GUI.Receipt.POSReceipt;
import Product.ManagerProduct;
import Product.PictureFinder;
import Product.Product;

public class AddProduct implements ActionListener {
	private MainGUI mainGUI;
	private JFrame calenderFrame;
	private JPanel jPanel = new JPanel();

	// Text Fields
	private JTextField txtDate = new JTextField();
	private JTextField txtProductName = new JTextField();
	private JTextField txtQuantity = new JTextField();
	private JTextField txtTotalCost = new JTextField();
	private JTextField txtOrigin = new JTextField();
	private JTextField txtPicturePath = new JTextField();

	// Labels
	private JLabel lblInventory = new JLabel("Inventory");
	private JLabel lblProductId = new JLabel("Product ID");
	private JLabel lblNextAvailableID = new JLabel("#####");
	private JLabel lblDate = new JLabel("Date");
	private JLabel lblProductName = new JLabel("Product Name");
	private JLabel lblPicture = new JLabel("Picture");
	private JLabel lblProductType = new JLabel("Product Type");
	private JLabel lblQuantity = new JLabel("Quantity");
	private JLabel lblTotalCost = new JLabel("Total Cost");
	private JLabel lblOrigin = new JLabel("Origin");

	// Combo Boxes
	private JComboBox cmbProductType;

	// Buttons
	private JButton btnAddPicture = new JButton("Add Picture");
	private JButton btnAdd = new JButton("ADD");
	private JButton btnSelectDate = new JButton("Select date");
	private JButton btnSet = new JButton("Set");
	private JButton btnPreview = new JButton("Preview");

	// Table
	private JScrollPane scrollPane = new JScrollPane();
	private JTable productTable = new JTable();

	private DBConnect db = new DBConnect();
	private Product product;
	private ManagerProduct managerProduct = new ManagerProduct();
	private ActListener act = new ActListener();
	private String resultDateFrom;
	private JXDatePicker picker;
	private Date dateFrom;
	private PictureFinder pictureFinder;
	
	
	
	public static ImageIcon IMAGE = null;


	public AddProduct(MainGUI mainGUI) {
		txtPicturePath.setColumns(10);
		this.mainGUI = mainGUI;
		txtProductName.setColumns(10);
		txtOrigin.setColumns(10);

		jPanel.setSize(1067, 605);
		GridBagLayout gbl_jPanel = new GridBagLayout();
		gbl_jPanel.columnWidths = new int[] { 50, 110, 110, 110, 110, 110, 110, 110, 0, 110, 0, 0 };
		gbl_jPanel.rowHeights = new int[] { 20, 24, 340, 30, 30, 30, 20, 0 };
		gbl_jPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_jPanel.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		jPanel.setLayout(gbl_jPanel);

		lblInventory.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblAddProduct = new GridBagConstraints();
		gbc_lblAddProduct.fill = GridBagConstraints.VERTICAL;
		gbc_lblAddProduct.gridwidth = 9;
		gbc_lblAddProduct.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddProduct.gridx = 1;
		gbc_lblAddProduct.gridy = 1;
		jPanel.add(lblInventory, gbc_lblAddProduct);

		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 9;
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

		GridBagConstraints gbc_lblNextAvailableID = new GridBagConstraints();
		gbc_lblNextAvailableID.fill = GridBagConstraints.BOTH;
		gbc_lblNextAvailableID.insets = new Insets(0, 0, 5, 5);
		gbc_lblNextAvailableID.gridx = 1;
		gbc_lblNextAvailableID.gridy = 4;
		jPanel.add(lblNextAvailableID, gbc_lblNextAvailableID);

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

		cmbProductType = new JComboBox(db.getProductTypeNames().toArray());

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
		jPanel.add(txtTotalCost, gbc_txtBuyingPrice);
		txtTotalCost.setColumns(10);

		GridBagConstraints gbc_txtOrigin = new GridBagConstraints();
		gbc_txtOrigin.insets = new Insets(0, 0, 5, 5);
		gbc_txtOrigin.fill = GridBagConstraints.BOTH;
		gbc_txtOrigin.gridx = 7;
		gbc_txtOrigin.gridy = 4;
		jPanel.add(txtOrigin, gbc_txtOrigin);
		
		GridBagConstraints gbc_txtPicturePath = new GridBagConstraints();
		gbc_txtPicturePath.gridwidth = 2;
		gbc_txtPicturePath.insets = new Insets(0, 0, 5, 5);
		gbc_txtPicturePath.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPicturePath.gridx = 8;
		gbc_txtPicturePath.gridy = 4;
		jPanel.add(txtPicturePath, gbc_txtPicturePath);

		GridBagConstraints gbc_btnSelectDate = new GridBagConstraints();
		gbc_btnSelectDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSelectDate.insets = new Insets(0, 0, 5, 5);
		gbc_btnSelectDate.gridx = 2;
		gbc_btnSelectDate.gridy = 5;
		jPanel.add(btnSelectDate, gbc_btnSelectDate);
		btnSelectDate.addActionListener(act);

		lblNextAvailableID.setText(Integer.toString(db.getNextAvailableProductID()));

		DefaultTableModel model = new DefaultTableModel(new Object[] { "Product ID", "Date Bought", "Product Name",
				"Product Type", "Quantity", "Total Cost", "Place Bought", "Picture" }, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		ListSelectionModel listSelectionModel = productTable.getSelectionModel();
		listSelectionModel.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				ListSelectionModel lsm = (ListSelectionModel) e.getSource();
			}
		});

		productTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		ArrayList<Product> products = db.getProducts();
		System.out.println(products.size());
		for (int i = 0; i < products.size(); i++) {
			model.addRow(new Object[] { products.get(i).getProductID(), products.get(i).getBuyDate(),
					products.get(i).getProductName(), db.getProductTypeName(products.get(i).getProductTypeID()),
					products.get(i).getQuantity(), products.get(i).getBuyPrice(), products.get(i).getBuyOrigin(),
					products.get(i).getPicture() });
		}
		productTable.setModel(model);
		
		btnSet.addActionListener(act);
		btnPreview.addActionListener(act);
		
		productTable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {

				if (e.getClickCount() == 2) {

					int row = productTable.getSelectedRow();
					int column = productTable.getColumnCount();

					int productID = 0;
					Date dateBought;
					String productName = "";
					String productType = "";
					int quantity = 0;
					double totalCost = 0;
					String origin = "";
					String picture = "";

					productID = (Integer) productTable.getValueAt(row, 0);
					dateBought = (Date) productTable.getValueAt(row, 1);
					productName = (String) productTable.getValueAt(row, 2);
					productType = (String) productTable.getValueAt(row, 3);
					quantity = (Integer) productTable.getValueAt(row, 4);
					totalCost = new Double(productTable.getValueAt(row, 5).toString());
					origin = (String) productTable.getValueAt(row, 6);
					picture = (String) productTable.getValueAt(row, 7);

					EditProduct editProduct = new EditProduct(mainGUI, productID, dateBought, productName, productType,
							quantity, totalCost, origin, picture);
				}

			}
		});
		
		AutoCompleteDecorator.decorate(this.cmbProductType);

		txtDate.setEnabled(false);
		
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.gridwidth = 2;
		gbc_btnAdd.insets = new Insets(0, 0, 0, 5);
		gbc_btnAdd.fill = GridBagConstraints.BOTH;
		gbc_btnAdd.gridx = 8;
		gbc_btnAdd.gridy = 6;
		btnAdd.addActionListener(act);
		btnAdd.setEnabled(false);
		
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
				if (txtDate.getText().equals("") ||txtProductName.getText().equals("") || txtQuantity.getText().equals("") 
						|| txtTotalCost.getText().equals("") || txtOrigin.getText().equals("") || txtPicturePath.getText().equals(""))
					btnAdd.setEnabled(false);
				else 
					btnAdd.setEnabled(true);
			}
		});
		
		txtProductName.getDocument().addDocumentListener(new DocumentListener() {
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
				if (txtDate.getText().equals("") ||txtProductName.getText().equals("") || txtQuantity.getText().equals("") 
						|| txtTotalCost.getText().equals("") || txtOrigin.getText().equals("") || txtPicturePath.getText().equals(""))
					btnAdd.setEnabled(false);
				else 
					btnAdd.setEnabled(true);
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
				if (txtDate.getText().equals("") ||txtProductName.getText().equals("") || txtQuantity.getText().equals("") 
						|| txtTotalCost.getText().equals("") || txtOrigin.getText().equals("") || txtPicturePath.getText().equals(""))
					btnAdd.setEnabled(false);
				else 
					btnAdd.setEnabled(true);
			}
		});
		
		txtTotalCost.getDocument().addDocumentListener(new DocumentListener() {
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
				if (txtDate.getText().equals("") ||txtProductName.getText().equals("") || txtQuantity.getText().equals("") 
						|| txtTotalCost.getText().equals("") || txtOrigin.getText().equals("") || txtPicturePath.getText().equals(""))
					btnAdd.setEnabled(false);
				else 
					btnAdd.setEnabled(true);
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
				if (txtDate.getText().equals("") ||txtProductName.getText().equals("") || txtQuantity.getText().equals("") 
						|| txtTotalCost.getText().equals("") || txtOrigin.getText().equals("") || txtPicturePath.getText().equals(""))
					btnAdd.setEnabled(false);
				else 
					btnAdd.setEnabled(true);
			}
		});
		
		txtPicturePath.getDocument().addDocumentListener(new DocumentListener() {
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
				if (txtDate.getText().equals("") ||txtProductName.getText().equals("") || txtQuantity.getText().equals("") 
						|| txtTotalCost.getText().equals("") || txtOrigin.getText().equals("") || txtPicturePath.getText().equals(""))
					btnAdd.setEnabled(false);
				else 
					btnAdd.setEnabled(true);
			}
		});
		
		GridBagConstraints gbc_btnAddPicture = new GridBagConstraints();
		gbc_btnAddPicture.fill = GridBagConstraints.BOTH;
		gbc_btnAddPicture.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddPicture.gridx = 8;
		gbc_btnAddPicture.gridy = 5;
		btnAddPicture.addActionListener(act);
		jPanel.add(btnAddPicture, gbc_btnAddPicture);
		
		GridBagConstraints gbc_btnPreview = new GridBagConstraints();
		gbc_btnPreview.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPreview.insets = new Insets(0, 0, 5, 5);
		gbc_btnPreview.gridx = 9;
		gbc_btnPreview.gridy = 5;
		jPanel.add(btnPreview, gbc_btnPreview);
		jPanel.add(btnAdd, gbc_btnAdd);
	}

	public JPanel getJPanel() {
		return jPanel;
	}

	private class ActListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnSelectDate) {
				// for calendar
				calenderFrame = new JFrame("Calendar");
				JPanel panel = new JPanel();
				calenderFrame.setBounds(400, 400, 250, 100);

				picker = new JXDatePicker();
				picker.setDate(Calendar.getInstance().getTime());
				picker.setFormats(new SimpleDateFormat("dd/MM/yyyy"));

				panel.add(picker);
				panel.add(btnSet);
				calenderFrame.getContentPane().add(panel);

				calenderFrame.setVisible(true);

				// end of calendar
			}
			
			if(e.getSource() == btnPreview){
				
				System.out.println("preview");
				String filePath = txtPicturePath.getText();
				
				if (filePath.equals("")) {
					JOptionPane.showMessageDialog(null, "No picture for selected product or no product selected!");
				} else {
					try {
						IMAGE = new ImageIcon(filePath);
					} catch (Exception q) {
						System.out.println("Error: btnPreview");q.printStackTrace();
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
				
			if (e.getSource() == btnAddPicture) {
				//check if file chosen is a photo
				pictureFinder = new PictureFinder();
				txtPicturePath.setText(pictureFinder.getPicturePath());
			}
	
			if (e.getSource() == btnSet) {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
				dateFrom = picker.getDate();
				resultDateFrom = formatter.format(dateFrom);
	
				txtDate.setText("");
	
				txtDate.setText(resultDateFrom);
	
				calenderFrame.dispose();
	
			}
			if (e.getSource() == btnAdd) {
				boolean isNumQuantity;  	   // number input checker for quantity
				boolean isNumTotalCost = true; // number input checker for total cost
				boolean dec = true;			   // 2 decimal places checker
				
				try {
					Double d = Double.parseDouble(txtTotalCost.getText());
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
					txtTotalCost.setText("");
				}else if (dec == false) { //2 decimal places checker
					JOptionPane.showMessageDialog(null, "Decimal places are limited to 2!");
					txtTotalCost.setText("");
				}else if (Double.parseDouble(txtTotalCost.getText()) < 0) {//positive number checker
					JOptionPane.showMessageDialog(null, "Please input numbers not less than 0!");
					txtTotalCost.setText("");
				}else if(cmbProductType.getSelectedItem().toString().equals("Select")){//Check if there is a selected product type
					JOptionPane.showMessageDialog(null, "Please select Product Type!");
				}else{
					product = new Product(Integer.parseInt(txtQuantity.getText()), dateFrom, txtProductName.getText(),
							Double.parseDouble(txtTotalCost.getText()) / Integer.parseInt(txtQuantity.getText()),
							Integer.parseInt(lblNextAvailableID.getText()),
							db.getProductTypeID(cmbProductType.getSelectedItem().toString()), txtPicturePath.getText(),
							txtOrigin.getText());
		
					managerProduct.addProduct(product);
					
					JOptionPane.showMessageDialog(null, "Product Successfully Added.");
					mainGUI.removeAllRightSplit();
					AddProduct addProduct = new AddProduct(mainGUI);
					mainGUI.setRightSplit(addProduct.getJPanel());
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
