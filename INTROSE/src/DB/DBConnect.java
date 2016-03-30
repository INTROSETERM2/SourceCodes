package DB;

import java.sql.Date;

import java.sql.DriverManager;

import java.sql.ResultSet;
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
//			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/introse_mp","root","");
//			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/introse_mp","root", "");

			con.createStatement();

		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
	}

	public double getTotalSalesToday() {
		String query = "select sum(sold_price) from receipts where sold_date = ?";
		double total = 0;
		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			preparedStatement.setDate(1, getCurrentDate());

			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				total = rs.getDouble("sum(sold_price)");
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}

		return Math.round(total * 100.0) / 100.0;
	}

	public DefaultTableModel retrieveDailySales() {
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

		String query = "select receiptID, sold_ProductName, sold_price, sold_quantity, customer_name, staffName from receipts where sold_date = ?";
		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			preparedStatement.setDate(1, getCurrentDate());
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
	
	public ArrayList<Product> getProducts(){
		String query = "SELECT * FROM products p, product_types pt, branches b WHERE (p.branch = b.branchID) & (p.product_typeID = pt.product_typeID);";
		ArrayList<Product> products = new ArrayList<Product>();
		try{
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			rs = preparedStatement.executeQuery();
			while(rs.next())
				products.add(new Product(rs.getInt("quantity"), rs.getDate("buy_date"), rs.getString("product_name"), rs.getDouble("buy_price"), rs.getInt("productID"), rs.getInt("product_typeID"), rs.getString("picture"), new Branch(rs.getInt("branchID"), rs.getString("branchName"), rs.getString("branchUsername"), rs.getString("branchPassword"), rs.getDate("branchCreationDate")), rs.getString("buy_origin")));
		
		}catch(Exception ex){
			System.out.println(ex + "getProducts");
		}
		return products;
	}
	
	public int getProductTypeID(String productTypeName){
		String query = "SELECT product_typeID from product_types where product_type_name = ?";
		int productTypeID = 0;
		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			preparedStatement.setString(1, productTypeName);
			rs = preparedStatement.executeQuery(query);

			while (rs.next()) {
				productTypeID = rs.getInt("product_typeID");
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}
		return productTypeID;
	}
	public ArrayList<ProductType> getProductTypes(){
		String query = "SELECT * FROM product_types";
		ArrayList<ProductType> productType = new ArrayList<ProductType>();
		
		try{
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			rs = preparedStatement.executeQuery();
			
			while (rs.next()){
				productType.add(new ProductType(rs.getInt("product_typeID"), rs.getString("product_type_name")));
			}
		}catch(Exception ex){
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
		String query = "insert into products(productID, quantity, product_name, buy_price, buy_date, product_typeID, branch, buy_origin, picture) VALUES (?,?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			preparedStatement.setInt(1, product.getProductID());
			preparedStatement.setInt(2, product.getQuantity());
			preparedStatement.setString(3, product.getProductName());
			preparedStatement.setDouble(4, product.getBuyPrice());
			preparedStatement.setDate(5, getCurrentDate());
			preparedStatement.setInt(6, product.getProductTypeID());
			preparedStatement.setInt(7, product.getBranch().getBranchID());
			preparedStatement.setString(8, product.getBuyOrigin());
			preparedStatement.setString(9, product.getPicture());
			preparedStatement.executeUpdate();

		} catch (Exception ex) {
			System.out.println(ex);
		}
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
				branches.add(new Branch(rs.getInt("branchID"), rs.getString("branchName"), rs.getString("branchUsername"), rs.getString("branchPassword"), rs.getDate("branchCreationDate")));
		} catch (Exception e) {
			System.out.println(e + "getBranches");
		}
		return branches;
	}
// try change
//	public void deleteBranch(int branchID) {
//		String query = "delete from branches where branchID = ?";
//
//		try {
//			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
//			preparedStatement.setInt(1, branchID);
//			preparedStatement.executeUpdate();
//
//		} catch (Exception ex) {
//			System.out.println(ex);
//		}
//	}
	
	public void deleteBranch(String branchName){
		String query = "delete from branches where branchName = ?";
		
		try{
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			preparedStatement.setString(1, branchName);
			preparedStatement.executeUpdate();
		}
		catch(Exception ex){
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

}
