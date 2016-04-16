package GUI.Receipt;

import java.awt.Color;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
import GUI.ReportUI.BranchReport;
import Product.ManagerProduct;
import Receipt.Receipt;
import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.JCheckBox;

public class EditReceiptDailySales extends JFrame implements ActionListener {
	private DBConnect db = new DBConnect();
	private DecimalFormat df = new DecimalFormat("#.00");

	JPanel jPanel = new JPanel();
	// Combo Box
	private JComboBox cmbProductName;
	private JComboBox cmbQuantity = new JComboBox();

	// Text Fields
	private JTextField txtPrice = new JTextField();
	private JTextField txtStaff = new JTextField();

	// Labels
	private JLabel lblEditReceipt = new JLabel("EDIT RECEIPT");
	private JLabel lblReceiptNo = new JLabel("Receipt No:");
	private JLabel lblReceiptNumber;
	private JLabel lblCurrentProduct = new JLabel("Current Product:");
	private JLabel lblOldProduct;
	private JLabel lblNewProduct = new JLabel("New Product:");
	private JLabel lblPrice = new JLabel("Price:");
	private JLabel lblQuantity = new JLabel("Quantity:");
	private JLabel lblCustomer;
	private JLabel lblStaff = new JLabel("Staff:");

	// Button
	private JButton btnEdit;


	private MainGUI mainGUI;
	private int receiptNumber;
	private String productName;
	private double price;
	private int quantity;
	private String customer;
	private String staff;

	private int oldQuantity;
	private boolean priceField;
	private boolean staffField;

	private int branchNumber;
	private JLabel lblCustomer_1;
	
	private JFrame jFrame = new JFrame();

	public EditReceiptDailySales(int branchNumber, MainGUI mainGUI, int receiptNumber, String productName, double price,
			int quantity, String customer, String staff) {
		jFrame = this;

		jFrame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				mainGUI.getJFrame().setEnabled(true);

			}
		});
		
		mainGUI.getJFrame().setEnabled(false);
		jPanel.setLayout(new MigLayout("", "[52px][57px,grow][][][][][][][][]", "[20px][20px][23px][][][][][][][][]"));
		jPanel.setSize(300, 250);
		jPanel.setOpaque(true);

		this.mainGUI = mainGUI;
		this.receiptNumber = receiptNumber;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
		this.customer = customer;
		this.staff = staff;
		this.branchNumber = branchNumber;
		this.setResizable(false);
		this.setTitle("Edit Transaction");
		this.setVisible(true);

		ActListener act = new ActListener();

		// Combo Boxes
		cmbProductName = new JComboBox(db.getNameProducts().toArray());
		cmbProductName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cmbProductName.addActionListener(act);
		jPanel.add(cmbProductName, "cell 1 3 7 1,growx");

		cmbQuantity = new JComboBox();
		cmbQuantity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jPanel.add(cmbQuantity, "cell 1 5 7 1,growx");
		txtPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));

		// Text Fields
		txtPrice.setText(df.format(price));
		jPanel.add(txtPrice, "cell 1 4 7 1,growx");
		txtPrice.setColumns(10);
		txtStaff.setFont(new Font("Tahoma", Font.PLAIN, 14));

		txtStaff.setText(staff);
		jPanel.add(txtStaff, "cell 1 7 7 1,growx");
		txtStaff.setColumns(10);

		// Labels
		lblEditReceipt.setFont(new Font("Tahoma", Font.BOLD, 18));
		jPanel.add(lblEditReceipt, "cell 0 0");
		lblReceiptNo.setFont(new Font("Tahoma", Font.PLAIN, 14));

		jPanel.add(lblReceiptNo, "cell 0 1,alignx left");

		lblReceiptNumber = new JLabel(Integer.toString(receiptNumber));
		lblReceiptNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
		jPanel.add(lblReceiptNumber, "cell 1 1");
		lblCurrentProduct.setFont(new Font("Tahoma", Font.PLAIN, 14));

		jPanel.add(lblCurrentProduct, "cell 0 2");

		lblOldProduct = new JLabel(productName);
		lblOldProduct.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jPanel.add(lblOldProduct, "cell 1 2");
		lblNewProduct.setFont(new Font("Tahoma", Font.PLAIN, 14));

		jPanel.add(lblNewProduct, "cell 0 3,alignx left");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jPanel.add(lblPrice, "cell 0 4,alignx left");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jPanel.add(lblQuantity, "cell 0 5,alignx left");

		JLabel lblCustomer = new JLabel("Customer:");
		lblCustomer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jPanel.add(lblCustomer, "cell 0 6,alignx left");

		lblCustomer_1 = new JLabel("Name");
		lblCustomer_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jPanel.add(lblCustomer_1, "cell 1 6 7 1");
		lblStaff.setFont(new Font("Tahoma", Font.PLAIN, 14));

		jPanel.add(lblStaff, "cell 0 7,alignx left");

		// Buttons
		btnEdit = new JButton("Edit");
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jPanel.add(btnEdit, "cell 1 9 7 1,growx");
		btnEdit.addActionListener(act);
		btnEdit.setEnabled(false);


		lblCustomer_1.setText(customer);
		
		if(!(txtPrice.getText().equals("")) || !(txtStaff.getText().equals("")))
			btnEdit.setEnabled(true);

		txtPrice.getDocument().addDocumentListener(new DocumentListener() {
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
				if (txtPrice.getText().equals("") || txtStaff.getText().equals(""))
					btnEdit.setEnabled(false);
				else 
					btnEdit.setEnabled(true);
			}
		});
		
		txtStaff.getDocument().addDocumentListener(new DocumentListener() {
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
				if (txtPrice.getText().equals("") || txtStaff.getText().equals(""))
					btnEdit.setEnabled(false);
				else 
					btnEdit.setEnabled(true);
			}
		});
		
		AutoCompleteDecorator.decorate(this.cmbProductName);
		AutoCompleteDecorator.decorate(this.cmbQuantity);

		getContentPane().add(jPanel);
		this.setSize(400, 300);

	}

	private class ActListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			if (a.getSource() == cmbProductName) {
				
				ArrayList<String> quantityContent = new ArrayList<String>();

				String selectedItem = cmbProductName.getSelectedItem().toString();

				int i;

				cmbQuantity.removeAllItems();

				if (selectedItem.equals(productName)) {

					for (i = 0; i < db.getQuantity(productName); i++)
						quantityContent.add(Integer.toString(i + 1));

					cmbQuantity.insertItemAt("Select", 0);
					cmbQuantity.setSelectedIndex(0);

					for (i = 0; i < quantity; i++)
						quantityContent.add(Integer.toString(i + 1));

					for (i = 0; i < quantityContent.size(); i++)
						cmbQuantity.insertItemAt(i + 1, i);

				} else {

					for (i = 0; i < db.getQuantity(selectedItem); i++)
						quantityContent.add(Integer.toString(i + 1));

					cmbQuantity.insertItemAt("Select", 0);
					cmbQuantity.setSelectedIndex(0);

					for (i = 0; i < quantityContent.size(); i++)
						cmbQuantity.insertItemAt(i + 1, i);

				}

				oldQuantity = db.getQuantity(productName) + quantity;
				i = 0;
			}

			if (a.getSource() == btnEdit) {
				mainGUI.getJFrame().setEnabled(true);
				boolean ret = true;
				try {
					Double.parseDouble(txtPrice.getText().toString());
				} catch (final NumberFormatException e) {
					ret = false;
				}

				boolean dec = true;

				try {
					Double d = Double.parseDouble(txtPrice.getText());
					String[] split = d.toString().split("\\.");

					if (split[1].length() > 2) {
						dec = false;
					}
				} catch (final NumberFormatException e) {
					System.out.println(e);
				}

				if (ret == false) {
					JOptionPane.showMessageDialog(null, "Please input numbers only on the price");
					EditReceiptDailySales editReceiptDailySales = new EditReceiptDailySales(branchNumber, mainGUI, receiptNumber,
							productName, price, quantity, customer, staff);
					dispose();

				} else if (Double.parseDouble(txtPrice.getText()) < 0) {
					JOptionPane.showMessageDialog(null, "Please input numbers not less than 0");
					EditReceiptDailySales editReceiptDailySales = new EditReceiptDailySales(branchNumber, mainGUI, receiptNumber, productName,
							price, quantity, customer, staff);
					dispose();
				}

				else if (cmbProductName.getSelectedItem().toString() == "Select"
						|| cmbQuantity.getSelectedItem().toString() == "Select" || txtStaff.getText() == "") {
					JOptionPane.showMessageDialog(null, "Please fill in all the data");
					EditReceiptDailySales editReceiptDailySales = new EditReceiptDailySales(branchNumber, mainGUI, receiptNumber, productName,
							price, quantity, customer, staff);
					dispose();

				} else {
					ManagerProduct managerProduct = new ManagerProduct();

					if (!(productName.equals(cmbProductName.getSelectedItem().toString())))
						db.changedQuantity(db.getProductID(productName), oldQuantity);

					db.changedQuantity(db.getProductID(productName), oldQuantity);

					managerProduct.decrementProduct(cmbProductName.getSelectedItem().toString(),
							Integer.parseInt(cmbQuantity.getSelectedItem().toString()));

					ret = true;

					try {
						Double.parseDouble(txtPrice.getText().toString());

					} catch (final NumberFormatException e) {
						ret = false;
					}

					if (ret == false) {
						// JOptionPane.showMessageDialog(null, "Four Please
						// input numbers only on the price");
						// EditReceiptDailySales editReceipt = new
						// EditReceiptDailySales(mainGUI, receiptNumber,
						// productName, price, quantity,
						// staff);
						// dispose();

					} else if (Double.parseDouble(txtPrice.getText()) < 0) {
						JOptionPane.showMessageDialog(null, "Please input numbers not less than 0");
						EditReceiptDailySales editReceiptDailySales = new EditReceiptDailySales(branchNumber, mainGUI, receiptNumber,
								productName, price, quantity, customer, staff);
						dispose();
					} else if (dec == false) {
						JOptionPane.showMessageDialog(null, "Decimal places are limited to 2");

						EditReceiptDailySales editReceiptDailySales = new EditReceiptDailySales(branchNumber, mainGUI, receiptNumber,
								productName, price, quantity, customer, staff);
						dispose();
					} else {
						Receipt receipt = new Receipt(Integer.parseInt(lblReceiptNumber.getText()), txtStaff.getText(),
								Double.parseDouble(txtPrice.getText().toString()),
								Integer.parseInt(cmbQuantity.getSelectedItem().toString()), null, null, null, -1,
								cmbProductName.getSelectedItem().toString());
						db.editReceipt(receipt);
						JOptionPane.showMessageDialog(null, "Transaction was successfully edited");
						mainGUI.removeAllRightSplit();
						BranchReport branchReport = new BranchReport(mainGUI, branchNumber);
						mainGUI.setRightSplit(branchReport.getJPanel());
						dispose();
					}
				}
			}
		}
	}

	public JPanel getJPanel() {
		return jPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}