package DB;

import java.sql.Date;

import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.PreparedStatement;

import Branch.Branch;
import GUI.Receipt.POSReceipt;
import Product.Product;
import ProductType.ProductType;
import Receipt.Receipt;

public class DBConnect {

	private java.sql.Connection con;
	private ResultSet rs;

	public DBConnect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// always changed this for DB access
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/introse_mp","root","Helloworld123");
//		 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/introse_mp", "root", "root");
			// con =
			// DriverManager.getConnection("jdbc:mysql://localhost:3306/introse_mp","root",
			// "");

			con.createStatement();

		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
	}

	public double getTotalSalesToday(int branchID) {
		String query = "select sum(sold_price) from receipts where sold_date = ? && sold_branch = ?";
		double total = 0;
		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			preparedStatement.setDate(1, getCurrentDate());
			preparedStatement.setInt(2, branchID);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				total = rs.getDouble("sum(sold_price)");
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}

		return Math.round(total * 100.0) / 100.0;
	}

	public DefaultTableModel retrieveDailySales(int branchNumber) {
		String productName = "";
		String staffName = "";
		String sold_customer = "";
		double sold_price;
		int sold_quantity;
		int receipt_number;

		DefaultTableModel model;
		model = new DefaultTableModel() {
			String[] colname = { "Receipt Number", "Product", "Price", "Quantity", "Customer", "Staff" };

			@Override
			public int getColumnCount() {
				return colname.length;
			}

			@Override
			public String getColumnName(int index) {
				return colname[index];
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};

		String query = "select receiptID, sold_ProductName, sold_price, sold_quantity, customer_name, staffName from receipts where sold_date = ? && sold_branch = ?";
		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			preparedStatement.setDate(1, getCurrentDate());
			preparedStatement.setInt(2, branchNumber);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				receipt_number = rs.getInt("receiptID");
				productName = rs.getString("sold_ProductName");
				sold_price = rs.getDouble("sold_price");
				sold_quantity = rs.getInt("sold_Quantity");
				sold_customer = rs.getString("customer_name");
				staffName = rs.getString("staffName");

				model.addRow(new Object[] { Integer.toString(receipt_number), productName, String.valueOf(sold_price),
						String.valueOf(sold_quantity), sold_customer, staffName });
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}

		return model;

	}

	// get next available productID

	public int getNextAvailableProductID() {
		String query = "select MAX(productID) from products";
		Integer resultMax = 1;
		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);

			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				resultMax = rs.getInt("max(productID)");
				if (resultMax != null)
					return resultMax + 1;
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}

		return resultMax;
	}

	public ArrayList<String> getProductNames() {
		String query = "SELECT product_name FROM products";
		ArrayList<String> products = new ArrayList<String>();
		products.add("Select");

		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				products.add(rs.getString("product_name"));
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}

		return products;
	}

	public ArrayList<String> getBranchNames() {
		String query = "SELECT branchName FROM branches";
		ArrayList<String> products = new ArrayList<String>();
		products.add("None");

		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				products.add(rs.getString("branchName"));
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}

		return products;
	}

	public ArrayList<Product> getProducts() {
		String query = "SELECT * FROM products p, product_types pt WHERE (p.product_typeID = pt.product_typeID);";
		ArrayList<Product> products = new ArrayList<Product>();
		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				products.add(new Product(rs.getInt("quantity"), rs.getDate("buy_date"), rs.getString("product_name"),
						rs.getDouble("buy_price") * rs.getInt("quantity"), rs.getInt("productID"),
						rs.getInt("product_typeID"), rs.getString("picture"), rs.getString("buy_origin")));
			}
		} catch (Exception ex) {
			System.out.println(ex + "getProducts");
		}
		return products;
	}

	public ArrayList<String> getNameProducts() {
		String query = "SELECT product_name FROM introse_mp.products";
		ArrayList<String> products = new ArrayList<String>();
		products.add("Select");

		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				products.add(rs.getString("product_name"));
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}

		return products;
	}

	public ArrayList<String> getProductTypeNames() {
		String query = "SELECT product_type_name FROM product_types";
		ArrayList<String> productTypes = new ArrayList<String>();
		productTypes.add("Select");

		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				productTypes.add(rs.getString("product_type_name"));
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}

		return productTypes;
	}

	public int getProductTypeID(String productTypeName) {
		String query = "SELECT product_typeID from product_types where product_type_name = '" + productTypeName + "'";
		int productTypeID = 0;
		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			rs = preparedStatement.executeQuery(query);

			while (rs.next()) {
				productTypeID = rs.getInt("product_typeID");
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}
		return productTypeID;
	}

	public String getProductTypeName(int id) {
		String query = "SELECT product_type_name from product_types where product_typeID = " + id;
		String productName = "";
		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			rs = preparedStatement.executeQuery(query);

			while (rs.next()) {
				productName = rs.getString("product_type_name");
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}
		return productName;
	}

	public ArrayList<ProductType> getProductTypes() {
		String query = "SELECT * FROM product_types";
		ArrayList<ProductType> productType = new ArrayList<ProductType>();

		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				productType.add(new ProductType(rs.getInt("product_typeID"), rs.getString("product_type_name")));
			}
		} catch (Exception ex) {
			System.out.println(ex + "getProductTypes");
		}
		return productType;

	}

	public void decrementProduct(String productName, int decQuantity) {
		String query = "UPDATE products SET quantity = quantity - ? where product_name = ? and quantity > 0";

		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			preparedStatement.setInt(1, decQuantity);
			preparedStatement.setString(2, productName);
			preparedStatement.executeUpdate();

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public int getQuantity(String product) {
		String query = "SELECT quantity FROM products WHERE product_name = ?";
		int quantity = 0;

		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			preparedStatement.setString(1, product);

			rs = preparedStatement.executeQuery();

			while (rs.next())
				quantity = rs.getInt("quantity");

		} catch (Exception ex) {
			System.out.println(ex);
		}
		return quantity;
	}

	// get next available productTypeID
	public int getNextAvailableProductTypeID() {
		String query = "select MAX(product_TypeID) from product_types";
		Integer resultMax = 1;
		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);

			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				resultMax = rs.getInt("max(product_TypeID)");
				if (resultMax != null)
					return resultMax + 1;
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}

		return resultMax;
	}

	// get next available receiptID

	public int getNextAvailableReceiptID() {
		String query = "select MAX(receiptID) from receipts";
		Integer resultMax = 1;
		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);

			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				resultMax = rs.getInt("max(receiptID)");
				if (resultMax != null)
					return resultMax + 1;
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}

		return resultMax;
	}

	// get next available branchID

	public int getNextAvailableBranchID() {
		String query = "select MAX(branchID) from branches";
		Integer resultMax = 1;
		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);

			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				resultMax = rs.getInt("max(branchID)");
				if (resultMax != null)
					return resultMax + 1;
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}

		return resultMax;
	}

	// get next available staffID

	public int getNextAvailableStaffID() {
		String query = "select MAX(staffID) from staff_account";
		Integer resultMax = 1;
		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);

			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				resultMax = rs.getInt("max(staffID)");
				if (resultMax != null)
					return resultMax + 1;
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}

		return resultMax;
	}

	// gets current time and translates it into java.sql.Date
	public static java.sql.Date getCurrentDate() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Date(today.getTime());
	}

	public Branch login(String username, String password) {
		String query = "select branchUsername, branchPassword, branchID, branchCreationDate, branchName from branches where branchUsername = ? and branchPassword = ?";
		String dbBranchPassword = "";
		String dbBranchUsername = "";
		String dbBranchName = "";
		int dbBranchID = -1;
		Date dbCreationDate = null;

		Branch branch;

		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);

			rs = preparedStatement.executeQuery();

			while (rs.next()) {

				dbBranchUsername = rs.getString("branchUsername");
				dbBranchPassword = rs.getString("branchPassword");
				dbBranchID = rs.getInt("branchID");
				dbBranchName = rs.getString("branchName");
				dbCreationDate = rs.getDate("branchCreationDate");

				if (dbBranchPassword == password && dbBranchUsername == username) {
					branch = new Branch(dbBranchID, dbBranchName, dbBranchUsername, dbBranchPassword, dbCreationDate);
					return branch;
				}
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}
		return branch = new Branch(dbBranchID, dbBranchName, dbBranchUsername, dbBranchPassword, dbCreationDate);
	}

	public int getRank(int account_number) {
		String query = "select rank from staff_account where staffID = ?";
		int staff_rank = -1;
		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			preparedStatement.setInt(1, account_number);

			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				staff_rank = rs.getInt("rank");
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}

		return staff_rank;
	}

	public String getPicture(int productID) {
		String query = "select productID, picture from products where productID  = ?";
		String pictureSource = "no";
		int dbProductID = -1;

		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			preparedStatement.setInt(1, productID);

			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				dbProductID = rs.getInt("productID");
				pictureSource = rs.getString("picture");

				if (dbProductID == productID) {
					return pictureSource;
				}
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}

		return pictureSource;
	}

	public void addProduct(Product product) {
		String query = "insert into products(productID, quantity, product_name, buy_price, buy_date, product_typeID, buy_origin, picture) VALUES (?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			preparedStatement.setInt(1, product.getProductID());
			preparedStatement.setInt(2, product.getQuantity());
			preparedStatement.setString(3, product.getProductName());
			preparedStatement.setDouble(4, product.getBuyPrice());

			preparedStatement.setDate(5, convertJavaDateToSqlDate(product.getBuyDate()));
			preparedStatement.setInt(6, product.getProductTypeID());
			preparedStatement.setString(7, product.getBuyOrigin());
			preparedStatement.setString(8, product.getPicture());
			preparedStatement.executeUpdate();

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public java.sql.Date convertJavaDateToSqlDate(java.util.Date date) {
		return new java.sql.Date(date.getTime());
	}

	public void deleteProduct(int productID) {
		String query = "delete from products where productID = ?";

		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			preparedStatement.setInt(1, productID);
			preparedStatement.executeUpdate();

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	// end of ManagerProduct

	/* For ManagerProductType */
	public void addProductType(ProductType productType) {
		String query = "insert into product_types(product_typeID, product_type_name) VALUES (?,?)";

		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			preparedStatement.setInt(1, productType.getProductTypeId());

			preparedStatement.setString(2, productType.getProductTypeName());

			preparedStatement.executeUpdate();

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public void deleteProductType(int productID) {
		String query = "delete from product_types where product_typeID = ?";

		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			preparedStatement.setInt(1, productID);
			preparedStatement.executeUpdate();

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	/* End of ManagerProductType */

	public int getProductID(String soldProductName) {
		String query = "SELECT p.productID FROM receipts r, products p WHERE p.product_name = '" + soldProductName
				+ "'";
		int productID = 0;
		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);

			rs = preparedStatement.executeQuery(query);

			while (rs.next()) {
				productID = rs.getInt("productID");
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}
		return productID;
	}

	/* For ManagerReceipt */
	public void addReceipt(Receipt receipt)// chesterwashere
	{

		String query = "insert into receipts(receiptID, sold_price, sold_quantity, sold_date, customer_name, sold_branch, staffName, sold_ProductName, productID) VALUES (?,?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			preparedStatement.setInt(1, receipt.getReceiptID());
			preparedStatement.setDouble(2, receipt.getSoldPrice());
			preparedStatement.setInt(3, receipt.getSoldQuantity());
			preparedStatement.setDate(4, getCurrentDate());
			preparedStatement.setString(5, receipt.getCustomerName());
			preparedStatement.setInt(6, receipt.getSoldBranch().getBranchID());
			preparedStatement.setString(7, receipt.getStaffName());
			preparedStatement.setString(8, receipt.getSoldProductName());
			preparedStatement.setInt(9, getProductID(receipt.getSoldProductName()));

			preparedStatement.executeUpdate();

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}



	public Date getEarliestDate() {
		String query = "select min(sold_Date) from receipts";
		Date earlyDate = null;
		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			rs = preparedStatement.executeQuery(query);

			while (rs.next()) {
				earlyDate = rs.getDate("min(sold_Date)");
				System.out.println(earlyDate);
			}
		} catch (Exception ex) {
			System.out.println("Error: getEarliestDate " + ex);
		}

		return earlyDate;
	}

	public Date getLatestDate() {
		String query = "select max(sold_Date) from receipts";
		Date latestDate = null;
		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			rs = preparedStatement.executeQuery(query);

			while (rs.next()) {
				latestDate = rs.getDate("max(sold_Date)");
				System.out.println(latestDate);

			}
		} catch (Exception ex) {
			System.out.println("Error: getEarliestDate " + ex);
		}

		return latestDate;
	}
	
	public int getEarliestYear() {
		String query = "select min(Year(sold_Date)) from receipts";
		String earlyDate = null;
		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			rs = preparedStatement.executeQuery(query);

			while (rs.next()) {
				earlyDate = rs.getString("min(Year(sold_Date))");
			}
		} catch (Exception ex) {
			System.out.println("Error: getEarliestDate " + ex);
		}

		return Integer.parseInt(earlyDate);
	}

	public int getLatestYear() {
		String query = "select max(Year(sold_Date)) from receipts";
		String latestDate = null;
		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			rs = preparedStatement.executeQuery(query);

			while (rs.next()) {
				latestDate = rs.getString("max(Year(sold_Date))");

			}
		} catch (Exception ex) {
			System.out.println("Error: getEarliestDate " + ex);
		}

		return Integer.parseInt(latestDate);
	}

	public int getEarliestYear(int branchNum) {
		String query = "SELECT year(min(sold_date)) as EarlyDate FROM receipts WHERE sold_branch = " + branchNum;
		int earlyDate = 0;
		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			rs = preparedStatement.executeQuery(query);

			while (rs.next()) {
				earlyDate = rs.getInt("EarlyDate");
			}
		} catch (Exception ex) {
			System.out.println("Error: getEarliestDate " + ex);
		}

		return earlyDate;
	}
	
	public int getLatestYear(int branchNum) {
		String query = "SELECT year(max(sold_date)) as LateDate FROM receipts WHERE sold_branch = " + branchNum;
		int earlyDate = 0;
		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			rs = preparedStatement.executeQuery(query);

			while (rs.next()) {
				earlyDate = rs.getInt("LateDate");
			}
		} catch (Exception ex) {
			System.out.println("Error: getEarliestDate " + ex);
		}

		return earlyDate;
	}

	public ArrayList<Receipt> getMonthReceipts(int branchNum, int month, int year) {
		String query = "SELECT * fROM receipts WHERE year(sold_date) = ? && month(sold_date) = ? && sold_branch = ?";
		ArrayList<Receipt> receipts = new ArrayList<Receipt>();
		ArrayList<Branch> branches = new ArrayList<Branch>();
		Branch branch = new Branch();
		branches = getBranches();

		for (int i = 0; i < branches.size(); i++)
			if (branchNum == branches.get(i).getBranchID()) {
				branch = branches.get(i);
				break;
			}

		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			preparedStatement.setInt(1, year);
			preparedStatement.setInt(2, month);
			preparedStatement.setInt(3, branchNum);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int receiptID = rs.getInt("receiptID");
				String staffName = rs.getString("staffName");
				double soldPrice = rs.getDouble("sold_price");
				int soldQuantity = rs.getInt("sold_Quantity");
				Date soldDate = rs.getDate("sold_date");
				String customerName = rs.getString("customer_name");
				int soldProductID = rs.getInt("productID");
				String soldProductName = rs.getString("sold_ProductName");
				System.out.println(branch.getBranchCreationDate());
				receipts.add(new Receipt(receiptID, staffName, soldPrice, soldQuantity, soldDate, customerName, branch,
						soldProductID, soldProductName));
			}
		} catch (Exception e) {
			System.out.println("Error: getReceipts " + e);
		}
		return receipts;

	}

	public ArrayList<Receipt> getDayReceipts(int branchNum, String date) {
		String query = "SELECT * FROM receipts WHERE sold_branch = " + branchNum + " && sold_date = '" + date + "'";
		ArrayList<Receipt> receipts = new ArrayList<Receipt>();
		ArrayList<Branch> branches = getBranches();
		int receiptID;
		String staffName;
		double soldPrice;
		int soldQuantity;
		Date soldDate;
		String customerName;
		Branch branch = new Branch();
		int soldProductID;
		String soldProductName;

		for (int i = 0; i < branches.size(); i++)
			if (branchNum == branches.get(i).getBranchID()) {
				branch = branches.get(i);
				break;
			}

		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			rs = preparedStatement.executeQuery(query);

			while (rs.next()) {
				receiptID = rs.getInt("receiptID");
				staffName = rs.getString("staffName");
				soldPrice = rs.getDouble("sold_price");
				soldQuantity = rs.getInt("sold_Quantity");
				soldDate = rs.getDate("sold_date");
				customerName = rs.getString("customer_name");
				soldProductID = rs.getInt("productID");
				soldProductName = rs.getString("sold_ProductName");

				receipts.add(new Receipt(receiptID, staffName, soldPrice, soldQuantity, soldDate, customerName, branch,
						soldProductID, soldProductName));
			}
		} catch (Exception ex) {
			System.out.println("Error: getTodayReceipts " + ex);
		}

		return receipts;
	}
	

	public void deleteReceipt(int receiptID) {
		String query = "delete from receipts where receiptID = ?";

		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			preparedStatement.setInt(1, receiptID);
			preparedStatement.executeUpdate();

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	/* end of ManagerReceipt */

	public void deleteStaff(int staffID) {
		String query = "delete from staff_account where staffID = ?";

		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			preparedStatement.setInt(1, staffID);
			preparedStatement.executeUpdate();

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	/* end of ManagerStaff */

	/* For ManagerBranch */
	public void addBranch(Branch branch) {
		String query = "insert into branches(branchName, branchUsername, branchPassword, branchCreationDate) VALUES ( ?, ?, ?, ?)";

		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			preparedStatement.setString(1, branch.getBranchName());
			preparedStatement.setString(2, branch.getBranchUsername());
			preparedStatement.setString(3, branch.getBranchPassword());
			preparedStatement.setDate(4, getCurrentDate());

			preparedStatement.executeUpdate();

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public ArrayList<Branch> getBranches() {
		String query = "SELECT * FROM branches";
		ArrayList<Branch> branches = new ArrayList<Branch>();
		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			rs = preparedStatement.executeQuery(query);

			while (rs.next())
				branches.add(
						new Branch(rs.getInt("branchID"), rs.getString("branchName"), rs.getString("branchUsername"),
								rs.getString("branchPassword"), rs.getDate("branchCreationDate")));
		} catch (Exception e) {
			System.out.println(e + "getBranches");
		}
		return branches;
	}
	// try change
	// public void deleteBranch(int branchID) {
	// String query = "delete from branches where branchID = ?";
	//
	// try {
	// PreparedStatement preparedStatement = (PreparedStatement)
	// con.prepareStatement(query);
	// preparedStatement.setInt(1, branchID);
	// preparedStatement.executeUpdate();
	//
	// } catch (Exception ex) {
	// System.out.println(ex);
	// }
	// }

	public void deleteBranch(String branchName) {
		String query = "delete from branches where branchName = ?";

		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			preparedStatement.setString(1, branchName);
			preparedStatement.executeUpdate();
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public void editReceipt(Receipt receipt) {
		String query = "UPDATE receipts SET sold_price = ?, sold_quantity = ?, staffName = ?, sold_ProductName = ? where receiptID = ?";

		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			preparedStatement.setDouble(1, receipt.getSoldPrice());
			preparedStatement.setInt(2, receipt.getSoldQuantity());
			preparedStatement.setString(3, receipt.getStaffName());
			preparedStatement.setString(4, receipt.getSoldProductName());
			preparedStatement.setInt(5, receipt.getReceiptID());

			preparedStatement.executeUpdate();

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	

	public void editProduct(Product product) {
		String query = "UPDATE products SET quantity = ?, product_name = ?, buy_price = ?, buy_date = ?,  product_typeid = ?, buy_origin = ?, picture = ? where productID = ?";

		System.out.println(product.getBuyDate());
		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			// preparedStatement.setInt(1, product.getQuantity());
			//
			// preparedStatement.setString(2, product.getProductName());
			// preparedStatement.setDouble(3, product.getBuyPrice());
			//
			// preparedStatement.setInt(4, product.getProductTypeID());
			//
			// preparedStatement.setString(5, product.getBuyOrigin());
			// preparedStatement.setString(6, product.getPicture() );
			// preparedStatement.setInt(7, product.getProductID());

			preparedStatement.setInt(1, product.getQuantity());

			preparedStatement.setString(2, product.getProductName());
			preparedStatement.setDouble(3, product.getBuyPrice());
			preparedStatement.setDate(4, convertJavaDateToSqlDate(product.getBuyDate()));

			preparedStatement.setInt(5, product.getProductTypeID());

			preparedStatement.setString(6, product.getBuyOrigin());
			preparedStatement.setString(7, product.getPicture());
			preparedStatement.setInt(8, product.getProductID());

			preparedStatement.executeUpdate();

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public void changedQuantity(int productID, int oldQuantity) {
		String query = "UPDATE products SET quantity = ? where productID = ?";
		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			preparedStatement.setInt(1, oldQuantity);
			preparedStatement.setInt(2, productID);

			preparedStatement.executeUpdate();

		} catch (Exception ex) {
			System.out.println(ex);
		}

	}

	public DefaultTableModel retrieveYearlyReport(String from, String to, String branch) {
		Double total_sales;
		int quantity;
		Double capital;
		Double netSales;
		String branchName;
		int month;
		int year;

		DefaultTableModel model;
		model = new DefaultTableModel() {
			String[] colname = { "Total Sales", "Quantity", "Capital", "Net Sales", "Branch", "Month", "Year" };

			@Override
			public int getColumnCount() {
				return colname.length;
			}

			@Override
			public String getColumnName(int index) {
				return colname[index];
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};

		String query = "";
		if (branch.equals("None")) {
			query = "select ROUND(sum(r.sold_price/2),2) as total_sales, sum(r.sold_quantity), p.buy_price*r.sold_quantity as capital, ROUND(sum(r.sold_price/2) - (p.buy_price*r.sold_quantity),2) as net_sales, b.branchName, month(r.sold_date), year(r.sold_date) from receipts r, products p, branches b where Year(R.sold_date) >= ? and Year(R.sold_date) <= ? and b.branchid = r.sold_branch group by r.sold_branch, r.sold_date order by r.sold_branch asc";

			try {	
				PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
				preparedStatement.setString(1, from);
				preparedStatement.setString(2, to);
				rs = preparedStatement.executeQuery();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			query = "select ROUND(sum(r.sold_price/2),2) as total_sales, sum(r.sold_quantity), p.buy_price*r.sold_quantity as capital, ROUND(sum(r.sold_price/2) - (p.buy_price*r.sold_quantity),2) as net_sales, b.branchName, month(r.sold_date), year(r.sold_date) from receipts r, products p, branches b where Year(R.sold_date) >= ? and Year(R.sold_date) <= ? and b.branchid = r.sold_branch and b.branchid = ? group by r.sold_branch, r.sold_date order by r.sold_branch asc";

			try {
				PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
				preparedStatement.setString(1, from.toString());
				preparedStatement.setString(2, to.toString());

				preparedStatement.setInt(3, getBranchID(branch));

				rs = preparedStatement.executeQuery();
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}

		try {
			while (rs.next()) {
				// total_sales = rs.getDouble("total_sales");
				// quantity = rs.getInt("sum(r.sold_quantity)");
				// capital = rs.getDouble("capital");
				// branchName = rs.getString("branch_name");
				// month = rs.getInt("month(r.sold_date)");
				// year = rs.getInt("year(r.sold_da");
				//
				total_sales = rs.getDouble(1);
				quantity = rs.getInt(2);
				capital = rs.getDouble(3);
				netSales = rs.getDouble(4);
				branchName = rs.getString(5);
				month = rs.getInt(6);
				year = rs.getInt(7);

				model.addRow(new Object[] { Double.toString(total_sales), Integer.toString(quantity),
						Double.toString(capital), Double.toString(netSales), branchName, Integer.toString(month), Integer.toString(year) });
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return model;

	}

	public int getBranchID(String branchName) {
		String query = "SELECT branchID from branches where branchName = '" + branchName + "'";
		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);

			rs = preparedStatement.executeQuery(query);

			while (rs.next()) {
				return rs.getInt("branchID");
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}
		return -1;
	}

}
