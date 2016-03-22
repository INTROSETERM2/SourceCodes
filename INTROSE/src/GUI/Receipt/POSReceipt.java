package GUI.Receipt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import GUI.ControlPanel.GUIPictureControlPanel;
import Product.ManagerProduct;
import Receipt.ManagerReceipt;
import Receipt.Receipt;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;

public class POSReceipt implements ActionListener {
	JPanel jPanel = new JPanel();
	DBConnect db = new DBConnect();

	// private JTable table = new JTable();
	private JTable table = new JTable();

	private JComboBox cmbItemName;
	private JComboBox cmbQuantity;
	private JTextField txtPrice = new JTextField();
	private JTextField txtStaff = new JTextField();
	private JTextField txtCustomer = new JTextField();

	private JLabel lblCustomer = new JLabel("Customer");
	private JLabel lblBranch = new JLabel("Branch:");
	private JLabel branchNumber = new JLabel(Integer.toString(MainGUI.BRANCH.getBranchID()));
	private JLabel lblTotalAmount = new JLabel("Total Amount");
	private JLabel lblDate = new JLabel("Date:");

	// for Date
	private DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
	private Date today = Calendar.getInstance().getTime();
	private String currentDate = df.format(today);

	private JLabel lblDatenow = new JLabel(currentDate);
	private JLabel lblItemName = new JLabel("Item name");
	private JLabel lblQuantity = new JLabel("Quantity");
	private JLabel lblPrice = new JLabel("Price");
	private JLabel lblStaff = new JLabel("Staff");
	private JButton btnPreview = new JButton("Preview");
	private JButton btnAdd = new JButton("Add");
	private JLabel lblTotalAmountComputed = new JLabel(String.valueOf(db.getTotalSalesToday()) + " php");
	private MainGUI mainGUI;

	public static ImageIcon IMAGE = null;

	public POSReceipt(MainGUI mainGUI) {
		this.mainGUI = mainGUI;
		lblTotalAmountComputed.setFont(new Font("Tahoma", Font.PLAIN, 20));

		lblTotalAmountComputed.setForeground(Color.red);
		lblTotalAmountComputed.setBackground(Color.white);
		lblTotalAmountComputed.setOpaque(true);
		setTable();
		ActListener act = new ActListener();

		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setViewportView(table);
		jPanel.setPreferredSize(new Dimension(1000, 500));
		jPanel.setLayout(new MigLayout("", "[33px][12px][140.00px][grow][16px][71px][17px][74px][42px][64px][12px][89px][12px][85px]", "[20px][20px][218px][14px][23px][23px][][][][][][][][][][][][][][]"));
		
				jPanel.add(lblTotalAmount, "cell 7 0,alignx left,aligny center");

		jPanel.add(lblTotalAmountComputed, "cell 8 0 6 1");

		jPanel.add(lblBranch, "cell 0 0,alignx right,aligny center");

		jPanel.add(branchNumber, "cell 2 0");

		jPanel.add(lblDate, "cell 0 1,alignx right,aligny center");

		jPanel.add(lblDatenow, "cell 2 1");

		jPanel.add(scrollPane, "cell 0 2 14 15,grow");
		ListSelectionModel listSelectionModel = table.getSelectionModel();
		listSelectionModel.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				ListSelectionModel lsm = (ListSelectionModel) e.getSource();
			}
		});

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2) {
					System.out.println("double clicked");
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
					// chester changes
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

		btnAdd.addActionListener(act);

		jPanel.add(lblItemName, "cell 0 17,growx,aligny bottom");

		btnPreview.addActionListener(act);
		jPanel.add(btnPreview, "cell 2 17,growx,aligny top");

		jPanel.add(lblQuantity, "cell 3 17,alignx left,aligny bottom");

		jPanel.add(lblCustomer, "cell 5 17,aligny bottom");

		jPanel.add(lblPrice, "cell 8 17,alignx left,aligny bottom");

		jPanel.add(lblStaff, "cell 13 17,alignx left,aligny bottom");

		cmbQuantity = new JComboBox();
		cmbQuantity.addActionListener(act);
		cmbItemName = new JComboBox(db.getProducts().toArray());
		cmbItemName.addActionListener(act);
		jPanel.add(cmbItemName, "cell 0 18 3 1,growx,aligny center");

		AutoCompleteDecorator.decorate(this.cmbItemName);

		jPanel.add(cmbQuantity, "cell 3 18,growx,aligny center");
		AutoCompleteDecorator.decorate(this.cmbQuantity);

		txtCustomer.setColumns(10);
		jPanel.add(txtCustomer, "cell 5 18 3 1,growx");

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
			//removed txtCustomer.getTExt().equals("") in if statement
			public void changed() {
				if (txtStaff.getText().equals("") || txtPrice.getText().equals("")) {
					btnAdd.setEnabled(false);
				} else {
					btnAdd.setEnabled(true);
				}

			}
		});

		jPanel.add(txtPrice, "cell 8 18 4 1,growx,aligny center");
		txtPrice.setColumns(10);

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
			//removed txtCustomer.getTExt().equals("") in if statement
			public void changed() {
				if (txtStaff.getText().equals("") || txtPrice.getText().equals("")) {
					btnAdd.setEnabled(false);
				} else {
					btnAdd.setEnabled(true);
				}
			}
		});

		jPanel.add(txtStaff, "cell 13 18,alignx left,aligny center");
		txtStaff.setColumns(10);

		txtStaff.setText("");

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
			//removed txtCustomer.getTExt().equals("") in if statement
			public void changed() {
				if (txtStaff.getText().equals("") || txtPrice.getText().equals("")) {
					btnAdd.setEnabled(false);
				} else {
					btnAdd.setEnabled(true);
				}

			}
		});

		jPanel.add(btnAdd, "cell 0 19 14 1,growx,aligny top");

		btnAdd.setEnabled(false);

		// txtStaff.addFocusListener(new FocusListener() {
		//
		// @Override
		// public void focusGained(FocusEvent f) {
		// btnAdd.setEnabled(false);
		// }
		//
		// @Override
		// public void focusLost(FocusEvent f) {
		// btnAdd.setEnabled(false);
		//
		// }
		//
		// });
		// txtCustomer.addFocusListener(new FocusListener() {
		//
		// @Override
		// public void focusGained(FocusEvent f) {
		// btnAdd.setEnabled(false);
		// }
		//
		// @Override
		// public void focusLost(FocusEvent f) {
		// btnAdd.setEnabled(false);
		//
		// }
		//
		// });
		// txtPrice.addFocusListener(new FocusListener() {
		//
		// @Override
		// public void focusGained(FocusEvent f) {
		// btnAdd.setEnabled(false);
		// }
		//
		// @Override
		// public void focusLost(FocusEvent f) {
		// btnAdd.setEnabled(false);
		//
		// }
		//
		// });
		//
		
	}

	public JPanel getJPanel() {
		return jPanel;
	}

	public void setTable() {
		table = new JTable(db.retrieveDailySales());
		table.getTableHeader().setReorderingAllowed(false);

	}

	private class ActListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {

			if (a.getSource() == cmbItemName) {
				

				cmbQuantity.removeAllItems();
				db.retrieveDailySales();

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

			if (a.getSource() == btnPreview) {
				GUIPictureControlPanel picControl = new GUIPictureControlPanel();

				String filePath = db.getPicture(db.getProductID(cmbItemName.getSelectedItem().toString()));
				if (filePath == "no") {
					JOptionPane.showMessageDialog(null, "No picture for selected product or no product selected!");
				} else {
					try {
						IMAGE = new ImageIcon(filePath);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				JLabel image = new JLabel(IMAGE);
				JDialog dialog = new JDialog();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setTitle("Image Preview");
				dialog.getContentPane().add(image);
				dialog.pack();
				dialog.setLocationRelativeTo(null);
				dialog.setVisible(true);
				mainGUI.removeAllLeftSplit();
//				mainGUI.setLeftSplit(); BUG NI ANDRIC
			}

			if (a.getSource() == btnAdd) {
				// check if number o hindi

				boolean ret = true;
				boolean dec = true;// chester changes
				try {
					Double d = Double.parseDouble(txtPrice.getText());
					String[] split = d.toString().split("\\.");

					if (split[1].length() > 2) {
						dec = false;
					}
				} catch (final NumberFormatException e) {
					ret = false;
				}
				// chester changes end
				if (ret == false) {
					JOptionPane.showMessageDialog(null, "Please input numbers only on the price");
					txtPrice.setText("");
					// txtStaff.setText("");
					// txtCustomer.setText("");
					// cmbItemName.setSelectedIndex(0);

					// mainGUI.removeAllRightSplit();
					// POSReceipt posReceipt = new POSReceipt(mainGUI);
					// mainGUI.setRightSplit(posReceipt.getJPanel());
				} // chester changes
				else if (dec == false) {
					JOptionPane.showMessageDialog(null, "Decimal places are limited to 2");
					mainGUI.removeAllRightSplit();
					POSReceipt posReceipt = new POSReceipt(mainGUI);
					mainGUI.setRightSplit(posReceipt.getJPanel());
				} // chester changes end
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
					db.retrieveDailySales();

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