package GUI.Receipt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
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

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import DB.DBConnect;
import GUI.MainGUI;
import Product.ManagerProduct;
import Receipt.ManagerReceipt;
import Receipt.Receipt;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;

public class POSReceipt implements ActionListener {
	JPanel jPanel = new JPanel();
	DBConnect db = new DBConnect();
	
	// for Date
	private DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
	private Date today = Calendar.getInstance().getTime();
	private String currentDate = df.format(today);
	
	// Combo Boxes
	private JComboBox cmbItemName;
	private JComboBox cmbQuantity;
	
	// Text Fields
	private JTextField txtCustomer = new JTextField();
	private JTextField txtPrice = new JTextField();
	private JTextField txtStaff = new JTextField();

	// Labels
	private JLabel lblBranch = new JLabel("Branch:");
	private JLabel branchNumber = new JLabel(Integer.toString(MainGUI.BRANCH.getBranchID()));
	private JLabel lblTotalAmount = new JLabel("Total Amount");
	private JLabel lblTotalAmountComputed = new JLabel();
	private JLabel lblDate = new JLabel("Date:");
	private JLabel lblDatenow = new JLabel(currentDate);
	private JLabel lblItemName = new JLabel("Item name");
	private JLabel lblQuantity = new JLabel("Quantity");
	private JLabel lblCustomer = new JLabel("Customer");
	private JLabel lblPrice = new JLabel("Total Price of Transaction");
	private JLabel lblStaff = new JLabel("Staff");

	// Buttons
	private JButton btnPreview = new JButton("Preview");
	private JButton btnAdd = new JButton("Add");
	
	// Tables
	private JTable table = new JTable();
	
	public static ImageIcon IMAGE = null;
	private MainGUI mainGUI;

	

	public POSReceipt(MainGUI mainGUI) {
		this.mainGUI = mainGUI;
		ActListener act = new ActListener();
		lblTotalAmountComputed.setText("");
		lblTotalAmountComputed.setText(String.valueOf(db.getTotalSalesToday(mainGUI.BRANCH.getBranchID())) + " php");
		
		// Panel
		jPanel.setPreferredSize(new Dimension(1000, 500));
		jPanel.setLayout(new MigLayout("", "[33px][12px][140.00px][grow][16px][71px][17px][74px][42px][64px][12px][89px][12px][85px]", "[20px][20px][218px][14px][23px][23px][][][][][][][][][][][][][][]"));
		
		// Combo Boxes
		cmbItemName = new JComboBox(db.getNameProducts().toArray());
		cmbItemName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cmbItemName.addActionListener(act);
		jPanel.add(cmbItemName, "cell 0 18 3 1,growx,aligny center");
		AutoCompleteDecorator.decorate(this.cmbItemName);
		
		cmbQuantity = new JComboBox();
		cmbQuantity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cmbQuantity.addActionListener(act);
		jPanel.add(cmbQuantity, "cell 3 18,growx,aligny center");
		AutoCompleteDecorator.decorate(this.cmbQuantity);
		txtCustomer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		// Text Fields
		txtCustomer.setColumns(10);
		jPanel.add(txtCustomer, "cell 5 18 3 1,growx");
		txtPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtPrice.setColumns(10);
		jPanel.add(txtPrice, "cell 8 18 4 1,growx,aligny center");
		txtStaff.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtStaff.setColumns(10);
		jPanel.add(txtStaff, "cell 13 18,alignx left,aligny center");
		txtStaff.setText("");
		lblBranch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		// Labels
		jPanel.add(lblBranch, "cell 0 0,alignx left,aligny center");
		branchNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jPanel.add(branchNumber, "cell 2 0");
		lblTotalAmount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jPanel.add(lblTotalAmount, "cell 7 0,alignx left,aligny center");
		
		lblTotalAmountComputed.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTotalAmountComputed.setForeground(Color.red);
		lblTotalAmountComputed.setBackground(Color.white);
		lblTotalAmountComputed.setOpaque(true);
		jPanel.add(lblTotalAmountComputed, "cell 8 0 6 1");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		jPanel.add(lblDate, "cell 0 1,alignx left,aligny center");
		lblDatenow.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jPanel.add(lblDatenow, "cell 2 1");
		lblItemName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jPanel.add(lblItemName, "cell 0 17,growx,aligny bottom");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jPanel.add(lblQuantity, "cell 3 17,alignx left,aligny bottom");
		lblCustomer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jPanel.add(lblCustomer, "cell 5 17,aligny bottom");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jPanel.add(lblPrice, "cell 8 17,alignx left,aligny bottom");
		lblStaff.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jPanel.add(lblStaff, "cell 13 17,alignx left,aligny bottom");
		btnPreview.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		// Buttons
		btnPreview.addActionListener(act);
		jPanel.add(btnPreview, "cell 2 17,growx,aligny top");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		btnAdd.addActionListener(act);
		jPanel.add(btnAdd, "cell 0 19 14 1,growx,aligny top");
		btnAdd.setEnabled(false);

		// Tables
		setTable();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		jPanel.add(scrollPane, "cell 0 2 14 15,grow");
		ListSelectionModel listSelectionModel = table.getSelectionModel();
		listSelectionModel.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				ListSelectionModel lsm = (ListSelectionModel) e.getSource();
			}
		});

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// Edit Table (If double clicked)
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2) {
					mainGUI.removeAllRightSplit();
					POSReceipt posReceipt = new POSReceipt(mainGUI);
					mainGUI.setRightSplit(posReceipt.getJPanel());

					int row = table.getSelectedRow();
					int column = table.getColumnCount();

					int receiptNumber = 0;
					String productName = "";
					double price = 0;
					int quantity = 0;
					String staff = "";
					String customer = "";
					boolean dec = true;
					
					try {
						Double d = Double.parseDouble(txtPrice.getText());
						String[] split = d.toString().split("\\.");

						if (split[1].length() > 2) {
							dec = false;
						}
					} catch (final NumberFormatException e1) {
						// System.out.println(e);
					}

					if (dec == false) {
						JOptionPane.showMessageDialog(null, "Decimal places are limited to 2");

					} else {
						receiptNumber = Integer.parseInt((String) table.getValueAt(row, 0));
						productName = (String) table.getValueAt(row, 1);
						price = new Double(table.getValueAt(row, 2).toString());
						quantity = Integer.parseInt((String) table.getValueAt(row, 3));
						customer = (String) table.getValueAt(row, 4);
						staff = (String) table.getValueAt(row, 5);

						EditReceipt editReceipt = new EditReceipt(mainGUI, receiptNumber, productName, price, quantity,
								customer, staff);
					}
				}
			}
		});

		// Enables btnAdd if fields are filled
		txtCustomer.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				changed();
			}

			public void removeUpdate(DocumentEvent e) {
				changed();
			}

			public void insertUpdate(DocumentEvent e) {
				changed();
			}
			
			public void changed() {
				if (txtStaff.getText().equals("") || txtPrice.getText().equals("")) {
					btnAdd.setEnabled(false);
				} else {
					btnAdd.setEnabled(true);
				}
			}
			
		});
		
		// Enables btnAdd if fields are filled
		txtPrice.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				changed();
			}

			public void removeUpdate(DocumentEvent e) {
				changed();
			}

			public void insertUpdate(DocumentEvent e) {
				changed();
			}
			
			public void changed() {
				if (txtStaff.getText().equals("") || txtPrice.getText().equals("")) {
					btnAdd.setEnabled(false);
				} else {
					btnAdd.setEnabled(true);
				}
			}
			
		});

		// Enables btnAdd if fields are filled
		txtStaff.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				changed();
			}

			public void removeUpdate(DocumentEvent e) {
				changed();
			}

			public void insertUpdate(DocumentEvent e) {
				changed();
			}
			
			public void changed() {
				if (txtStaff.getText().equals("") || txtPrice.getText().equals("")) {
					btnAdd.setEnabled(false);
				} else {
					btnAdd.setEnabled(true);
				}
			}
			
		});
	}
	//End of POSReceipt constructor

	public JPanel getJPanel() {
		return jPanel;
	}

	public void setTable() {
		table = new JTable(db.retrieveDailySales(MainGUI.BRANCH.getBranchID()));
		table.getTableHeader().setReorderingAllowed(false);
	}

	//ActionListeners
	private class ActListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			
			//Listens for input of cmbItemName
			//Places cmbQuantity content based on cmbItemName
			if (a.getSource() == cmbItemName) {

				cmbQuantity.removeAllItems();
				db.retrieveDailySales(MainGUI.BRANCH.getBranchID());

				ArrayList<String> quantityContent = new ArrayList<String>();

				String selectedItem = cmbItemName.getSelectedItem().toString();

				int i;

				for (i = 0; i < db.getQuantity(selectedItem); i++)
					quantityContent.add(Integer.toString(i + 1));

				cmbQuantity.insertItemAt("Select", 0);
				cmbQuantity.setSelectedIndex(0);

				for (i = 0; i < quantityContent.size(); i++)
					cmbQuantity.insertItemAt(quantityContent.get(i), i);
				
			}
			
			//Shows image according to currently selected product
			if (a.getSource() == btnPreview) {
				String filePath = db.getPicture(db.getProductID(cmbItemName.getSelectedItem().toString()));
				
				if (filePath == "no") {
					JOptionPane.showMessageDialog(null, "No picture for selected product or no product selected!");
				} else {
					try {
						IMAGE = new ImageIcon(filePath);
					} catch (Exception e) {
						e.printStackTrace();
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

			//Adds products if all criterion are met
			if (a.getSource() == btnAdd) {

				boolean ret = true;// number input checker
				boolean dec = true;// 2 decimal places checker
				try {
					Double d = Double.parseDouble(txtPrice.getText());
					String[] split = d.toString().split("\\.");

					if (split[1].length() > 2) 
						dec = false;
					
				} catch (final NumberFormatException e) {
					ret = false;
				}
				
				if (ret == false) { // number checker
					JOptionPane.showMessageDialog(null, "Please input numbers only on the price");
					txtPrice.setText("");
				} 
				else if (dec == false) { //2 decimal places checker
					JOptionPane.showMessageDialog(null, "Decimal places are limited to 2");
					mainGUI.removeAllRightSplit();
					POSReceipt posReceipt = new POSReceipt(mainGUI);
					mainGUI.setRightSplit(posReceipt.getJPanel());
				} //positive number checker
				else if (Double.parseDouble(txtPrice.getText()) < 0) {
					JOptionPane.showMessageDialog(null, "Please input numbers not less than 0");
					mainGUI.removeAllRightSplit();
					POSReceipt posReceipt = new POSReceipt(mainGUI);
					mainGUI.setRightSplit(posReceipt.getJPanel());
				}
				else if (cmbItemName.getSelectedItem().toString() == "Select"
						|| cmbQuantity.getSelectedItem().toString() == "Select") {
					JOptionPane.showMessageDialog(null, "Please fill in all the data");
					mainGUI.removeAllRightSplit();
					POSReceipt posReceipt = new POSReceipt(mainGUI);
					mainGUI.setRightSplit(posReceipt.getJPanel());

				} else { 
					table = new JTable();
					db.retrieveDailySales(MainGUI.BRANCH.getBranchID());

					jPanel.revalidate();
					jPanel.repaint();
					ManagerProduct managerProduct = new ManagerProduct();
					ManagerReceipt managerReceipt = new ManagerReceipt();
					
					Receipt receipt = new Receipt(txtStaff.getText(), Double.parseDouble(txtPrice.getText()),
							Integer.parseInt(cmbQuantity.getSelectedItem().toString()), today, txtCustomer.getText(),
							MainGUI.BRANCH, 1, cmbItemName.getSelectedItem().toString());

					managerReceipt.addReceipt(receipt);
					
					managerProduct.decrementProduct(cmbItemName.getSelectedItem().toString(),
							Integer.parseInt(cmbQuantity.getSelectedItem().toString()));

					JOptionPane.showMessageDialog(null, "Transaction Successfully Added.");

					mainGUI.removeAllRightSplit();
					POSReceipt posReceipt = new POSReceipt(mainGUI);
					mainGUI.setRightSplit(posReceipt.getJPanel());
				}
			}

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}