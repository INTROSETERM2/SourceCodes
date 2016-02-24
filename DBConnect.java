import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;

public class DBConnect {

	private java.sql.Connection con;
	private ResultSet rs;
	
	public DBConnect(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
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
	
	//gets current time and translates it into java.sql.Date
	private static java.sql.Date getCurrentDate() {
	    java.util.Date today = new java.util.Date();
	    return new java.sql.Date(today.getTime());
	}
	
	//login
	public int login(String username, String password)
	{
		String query = "select username, password, staffID from staff_account where username = ? and password = ?";
		String userpassword = "";
		String dbusername = "";
		int user_accountnumber = -1; 
		try{
			  PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			  preparedStatement.setString(1, username);
			  preparedStatement.setString(2, password);	

			rs = preparedStatement.executeQuery();
			
			while(rs.next())
			{
				
				dbusername = rs.getString("username");
				userpassword = rs.getString("password");

				user_accountnumber = rs.getInt("staffID");
		
				if(userpassword == password && dbusername == username)
				{
					return user_accountnumber;
				}
			}
			

		}catch(Exception ex){
			System.out.println(ex);
		}
	
		return user_accountnumber;
	}
		  
	public void signup(String username, String password, String firstname, String lastname, int branch, int rank)
	{
		String query = "insert into staff_account(username, password, firstname, lastname, branch, rank, creationdate) VALUES (?,?,?,?,?,?,?)";		
		
		try{
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			  preparedStatement.setString(1, username);
			  preparedStatement.setString(2, password);	
			  preparedStatement.setString(3, firstname);
			  preparedStatement.setString(4, lastname);
			  preparedStatement.setInt(5, branch);
			  preparedStatement.setInt(6, rank);
			  preparedStatement.setDate(7, getCurrentDate());
			  preparedStatement.executeUpdate();
			
		}catch(Exception ex){
			System.out.println(ex);
		}
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
	
	

	/*for ManagerProduct*/
//	public void addProduct(int quantity, String productName, float buyPrice, int productTypeID, int branch, String buyOrigin, String picture)
//	{
//		String query = "insert into products(quantity, product_name, buy_price, buy_date, product_typeID, branch, buy_origin, picture) VALUES (?,?,?,?,?,?,?,?)";		
//		
//		try{
//			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
//			  preparedStatement.setInt(1, quantity);
//			  preparedStatement.setString(2, productName);	
//			  preparedStatement.setFloat(3, buyPrice);
//			  preparedStatement.setDate(4, getCurrentDate());
//			  preparedStatement.setInt(5, productTypeID);
//			  preparedStatement.setInt(6, branch);
//			  preparedStatement.setString(7, buyOrigin);
//			  preparedStatement.setString(8, picture);
//			  preparedStatement.executeUpdate();
//			
//		}catch(Exception ex){
//			System.out.println(ex);
//		}
//	}
//	
	public void addProduct(Product product)
	{
		String query = "insert into products(quantity, product_name, buy_price, buy_date, product_typeID, branch, buy_origin, picture) VALUES (?,?,?,?,?,?,?,?)";		
		
		try{
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			  preparedStatement.setInt(1, product.getQuantity());
			  preparedStatement.setString(2, product.getProductName());	
			  preparedStatement.setFloat(3, product.getBuyPrice());
			  preparedStatement.setDate(4, getCurrentDate());
			  preparedStatement.setInt(5, product.getProductTypeID().getProductTypeId());
			  preparedStatement.setInt(6, product.getBranch().getBranchID());
			  preparedStatement.setString(7, product.getBuyOrigin());
			  preparedStatement.setString(8, product.getPicture());
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
	public void decrementProduct(int productID)
	{
		String query = "UPDATE products SET quantity= quantity - 1 where productID = ? and quantity > 0";		
		
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
		String query = "insert into product_types(product_type_name) VALUES (?)";		
		
		try{
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			  preparedStatement.setString(1, productType.getProductTypeName());

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
		String query = "insert into receipts(receiptID, staffID, sold_price, sold_quantity, sold_date, customer_name, sold_branch, sold_total_price) VALUES (?,?,?,?,?,?,?,?)";		
		
		try{
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			  preparedStatement.setInt(1, receipt.getReceiptID());
			  preparedStatement.setInt(2, receipt.getStaffID());	
			  preparedStatement.setFloat(3, receipt.getSoldPrice());
			  preparedStatement.setInt(4, receipt.getSoldQuantity());
			  preparedStatement.setDate(5, getCurrentDate());
			  preparedStatement.setString(6, receipt.getCustomerName());
			  preparedStatement.setInt(7, receipt.getSoldBranch().getBranchID());
			  preparedStatement.setFloat(8, receipt.getSoldTotalPrice());
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
}
		  