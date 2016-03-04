package DB;
import java.sql.Date;
import java.sql.DriverManager;



import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import Branch.Branch;
import Product.Product;
import ProductType.ProductType;
import Receipt.Receipt;

public class DBConnect {

	private java.sql.Connection con;
	private ResultSet rs;
	
	public DBConnect(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
//chester	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/introse_mp","root","Helloworld123");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/introse_mp","root","");

			
			con.createStatement();
			
		}catch(Exception ex){
			System.out.println("Error: " +ex);
		}
	}
	
	//get next available productID
	
	public int getNextAvailableProductID()
	{
		String query = "select MAX(productID) from products";
		Integer resultMax = 1;
		try{
			  PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);

			rs = preparedStatement.executeQuery();
			
			while(rs.next())
			{
				resultMax = rs.getInt("max(productID)");
				if(resultMax != null)
					return resultMax+ 1;
			}
			

		}catch(Exception ex){
			System.out.println(ex);
		}
		
		return resultMax;
	}
	//chester edit
	
	public ArrayList<String> getProducts(){
		String query = "SELECT product_name FROM introse_mp.products";
		ArrayList<String> products = new ArrayList<String>();
		
		try{
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				products.add(rs.getString("product_name"));
			}
		}catch(Exception ex){
			System.out.println(ex);
		}
		
		return products;
	}
	
	
	public void decrementProduct(String productName, int decQuantity)
	{
		String query = "UPDATE products SET quantity = quantity - ? where product_name = ? and quantity > 0";		
		
		try{
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			preparedStatement.setInt(1, decQuantity);
			preparedStatement.setString(2, productName);
			preparedStatement.executeUpdate();
			
		}catch(Exception ex){
			System.out.println(ex);
		}
	}
	
	public int getQuantity(String product){
		String query = "SELECT quantity FROM introse_mp.products WHERE product_name = ?";
		int quantity = 0;
		
		try{
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			preparedStatement.setString(1, product);
			
			rs = preparedStatement.executeQuery();
			
			while(rs.next())
				quantity = rs.getInt("quantity");
			
			
		}catch(Exception ex){
			System.out.println(ex);
		}
		return quantity;
	}
	//chester edit
	//get next available productTypeID	
	public int getNextAvailableProductTypeID()
	{
		String query = "select MAX(product_TypeID) from product_types";
		Integer resultMax = 1;
		try{
			  PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);

			rs = preparedStatement.executeQuery();
			
			while(rs.next())
			{
				resultMax = rs.getInt("max(product_TypeID)");
				if(resultMax != null)
					return resultMax+ 1;
			}
			

		}catch(Exception ex){
			System.out.println(ex);
		}
		
		return resultMax;
	}
	
	//get next available receiptID
	
		public int getNextAvailableReceiptID()
		{
			String query = "select MAX(receiptID) from receipts";
			Integer resultMax = 1;
			try{
				  PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);

				rs = preparedStatement.executeQuery();
				
				while(rs.next())
				{
					resultMax = rs.getInt("max(receiptID)");
					if(resultMax != null)
						return resultMax+ 1;
				}
				

			}catch(Exception ex){
				System.out.println(ex);
			}
			
			return resultMax;
		}
		
		//get next available branchID
		
			public int getNextAvailableBranchID()
			{
				String query = "select MAX(branchID) from branches";
				Integer resultMax = 1;
				try{
					  PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);

					rs = preparedStatement.executeQuery();
					
					while(rs.next())
					{
						resultMax = rs.getInt("max(branchID)");
						if(resultMax != null)
							return resultMax+ 1;
					}
					

				}catch(Exception ex){
					System.out.println(ex);
				}
				
				return resultMax;
			}
	
			//get next available staffID
			
			public int getNextAvailableStaffID()
			{
				String query = "select MAX(staffID) from staff_account";
				Integer resultMax = 1;
				try{
					  PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);

					rs = preparedStatement.executeQuery();
					
					while(rs.next())
					{
						resultMax = rs.getInt("max(staffID)");
						if(resultMax != null)
							return resultMax+ 1;
					}
					

				}catch(Exception ex){
					System.out.println(ex);
				}
				
				return resultMax;
			}
			
			
	//gets current time and translates it into java.sql.Date
	private static java.sql.Date getCurrentDate() {
	    java.util.Date today = new java.util.Date();
	    return new java.sql.Date(today.getTime());
	}

	
	public Branch login(String username, String password)
	{
		String query = "select branchUsername, branchPassword, branchID, branchCreationDate, branchName from branches where branchUsername = ? and branchPassword = ?";
		String dbBranchPassword= "";
		String dbBranchUsername = "";
		String dbBranchName = "";
		int dbBranchID = -1;
		Date dbCreationDate = null;
	

		Branch branch;
		
		
		try{
			  PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			  preparedStatement.setString(1, username);
			  preparedStatement.setString(2, password);	

			rs = preparedStatement.executeQuery();
			
			while(rs.next())
			{
				
				dbBranchUsername = rs.getString("branchUsername");
				dbBranchPassword = rs.getString("branchPassword");
				dbBranchID = rs.getInt("branchID");
				dbBranchName = rs.getString("branchName");
				dbCreationDate = rs.getDate("branchCreationDate");
						
				if(dbBranchPassword == password && dbBranchUsername == username)
				{
					branch = new Branch(dbBranchID, dbBranchName, dbBranchUsername, dbBranchPassword, dbCreationDate);
					return branch;
				}
			}
						

		}catch(Exception ex){
			System.out.println(ex);
		}
		return branch  = new Branch(dbBranchID, dbBranchName, dbBranchUsername, dbBranchPassword, dbCreationDate);
	}
	
	
	public int getRank(int account_number)
	{
		String query = "select rank from staff_account where staffID = ?";
		int staff_rank = -1; 
		try{
			  PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			  preparedStatement.setInt(1, account_number);

			rs = preparedStatement.executeQuery();
			
			while(rs.next())
			{	
				staff_rank = rs.getInt("rank");
			}
			

		}catch(Exception ex){
			System.out.println(ex);
		}
	
		return staff_rank;
	}
		  
	public String getPicture(int productID)
	{
		String query = "select productID, picture from product where productID  = ?";
		String pictureSource = "No picture or no product exists";
		int dbProductID = -1;
		try{
			  PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			  preparedStatement.setInt(1, productID);

			rs = preparedStatement.executeQuery();
			
			while(rs.next())
			{
				dbProductID = rs.getInt("productID");
				pictureSource = rs.getString("picture");
		
				if(dbProductID == productID)
				{
					return pictureSource;
				}
			}
			

		}catch(Exception ex){
			System.out.println(ex);
		}
	
		return pictureSource;
	}
	
	public void addProduct(Product product)
	{
		String query = "insert into products(productID, quantity, product_name, buy_price, buy_date, product_typeID, branch, buy_origin, picture) VALUES (?,?,?,?,?,?,?,?,?)";		
		
		try{
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			 preparedStatement.setInt(1, product.getProductID());
				preparedStatement.setInt(2, product.getQuantity());
			  preparedStatement.setString(3, product.getProductName());	
			  preparedStatement.setDouble(4, product.getBuyPrice());
			  preparedStatement.setDate(5, getCurrentDate());
			  preparedStatement.setInt(6, product.getProductTypeID().getProductTypeId());
			  preparedStatement.setInt(7, product.getBranch().getBranchID());
			  preparedStatement.setString(8, product.getBuyOrigin());
			  preparedStatement.setString(9, product.getPicture());
			  preparedStatement.executeUpdate();
			
		}catch(Exception ex){
			System.out.println(ex);
		}
	}
	

	public void deleteProduct(int productID)
	{
		String query = "delete from products where productID = ?";		
		
		try{
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			  preparedStatement.setInt(1, productID);
			  preparedStatement.executeUpdate();
			
		}catch(Exception ex){
			System.out.println(ex);
		}
	}
	
	//end of ManagerProduct
	
	
	/*For ManagerProductType*/
	public void addProductType(ProductType productType)
	{
		String query = "insert into product_types(product_typeID, product_type_name) VALUES (?,?)";		
		
		try{
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			  preparedStatement.setInt(1, productType.getProductTypeId());
  
			preparedStatement.setString(2, productType.getProductTypeName());

			  preparedStatement.executeUpdate();
			
		}catch(Exception ex){
			System.out.println(ex);
		}
	}

	public void deleteProductType(int productID)
	{
		String query = "delete from product_types where product_typeID = ?";		
			 
		try{
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			  preparedStatement.setInt(1, productID);
			  preparedStatement.executeUpdate();
			
		}catch(Exception ex){	
			System.out.println(ex);
		}
	}
	/*End of ManagerProductType*/
	
	/*For ManagerReceipt */
	public void addReceipt(Receipt receipt)
	{
		String query = "insert into receipts(receiptID, sold_price, sold_quantity, sold_date, customer_name, sold_branch, sold_total_price, staffName) VALUES (?,?,?,?,?,?,?, ?)";		
		
		try{
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			  preparedStatement.setInt(1, receipt.getReceiptID());	
			  preparedStatement.setDouble(2, receipt.getSoldPrice());
			  preparedStatement.setInt(3, receipt.getSoldQuantity());
			  preparedStatement.setDate(4, getCurrentDate());
			  preparedStatement.setString(5, receipt.getCustomerName());
			  preparedStatement.setInt(6, receipt.getSoldBranch().getBranchID());
			  preparedStatement.setDouble(7, receipt.getSoldTotalPrice());
			  preparedStatement.setString(8, receipt.getStaffName());
			  preparedStatement.executeUpdate();
			
		}catch(Exception ex){
			System.out.println(ex);
		}
	}

	public void deleteReceipt(int receiptID)
	{
		String query = "delete from receipts where receiptID = ?";		
		
		try{
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			preparedStatement.setInt(1, receiptID);  
			preparedStatement.executeUpdate();
			
		}catch(Exception ex){
			System.out.println(ex);
		}
	}
	/*end of ManagerReceipt*/
	

	public void deleteStaff(int staffID)
	{
		String query = "delete from staff_account where staffID = ?";		
		
		try{
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			preparedStatement.setInt(1, staffID);  
			preparedStatement.executeUpdate();
			
		}catch(Exception ex){
			System.out.println(ex);
		}
	}
	
	/*end of ManagerStaff*/
	
	/*For ManagerBranch*/
//	public void addBranch(Branch branch)
//	{
//		String query = "insert into branches(branchID, branch_name, branch_number_of_staff) VALUES (?,?,?)";		
//		
//		try{
//			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
//			  preparedStatement.setInt(1, branch.getBranchID());
//			  preparedStatement.setString(2, branch.getBranchName());	
//			  preparedStatement.setInt(3, branch.getBranchNumberOfStaff());
//			  preparedStatement.executeUpdate();
//			
//		}catch(Exception ex){
//			System.out.println(ex);
//		}
//	}
//	

	public void deleteBranch(int branchID)
	{
		String query = "delete from branches where branchID = ?";		
		
		try{
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			preparedStatement.setInt(1, branchID);  
			preparedStatement.executeUpdate();
			
		}catch(Exception ex){
			System.out.println(ex);
		}
	}
	
}
		  