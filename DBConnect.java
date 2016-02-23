import java.awt.List;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class DBConnect {

	private java.sql.Connection con;
	private java.sql.Statement st;
	private ResultSet rs;
	
	public DBConnect(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/introse_mp","root","");
			st = con.createStatement();
			
		}catch(Exception ex){
			System.out.println("Error: " +ex);
		}
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
	
	/*for ManagerReceipt*/
	public void addReceipt(int staffID, float sold_price, int sold_quantity, String customerName, int sold_branch)
	{
		String query = "insert into receipts(staffID, sold_price, sold_quantity, customer_name, sold_branch, sold_date, sold_total_price) VALUES (?,?,?,?,?,?,?)";		
		
		try{
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			  preparedStatement.setInt(1, staffID);
			  preparedStatement.setFloat(2, sold_price);	
			  preparedStatement.setInt(3, sold_quantity);
			  preparedStatement.setString(4, customerName);
			  preparedStatement.setInt(5, sold_branch);
			  preparedStatement.setDate(6, getCurrentDate());
			  preparedStatement.setFloat(7, sold_price * sold_quantity);
			  preparedStatement.executeUpdate();
			
		}catch(Exception ex){
			System.out.println(ex);
		}
	}
	
	public void deleteReceipt(int receipt_number)
	{
		String query = "delete from receipts where receipt_number = ?";		
		
		try{
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			preparedStatement.setInt(1, receipt_number);  
			preparedStatement.executeUpdate();
			
		}catch(Exception ex){
			System.out.println(ex);
		}
	}
	/*End of ManagerReceipt*/
	

	/*for ManagerProduct*/
	public void addProduct(int quantity, String productName, float buyPrice, int productTypeID, int branch, String buyOrigin, String picture)
	{
		String query = "insert into products(quantity, product_name, buy_price, buy_date, product_typeID, branch, buy_origin, picture) VALUES (?,?,?,?,?,?,?,?)";		
		
		try{
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			  preparedStatement.setInt(1, quantity);
			  preparedStatement.setString(2, productName);	
			  preparedStatement.setFloat(3, buyPrice);
			  preparedStatement.setDate(4, getCurrentDate());
			  preparedStatement.setInt(5, productTypeID);
			  preparedStatement.setInt(6, branch);
			  preparedStatement.setString(7, buyOrigin);
			  preparedStatement.setString(8, picture);
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
	
}
		  