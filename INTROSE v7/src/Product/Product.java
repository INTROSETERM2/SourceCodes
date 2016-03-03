package Product;

import java.text.SimpleDateFormat;

import Branch.Branch;
import DB.DBConnect;
import ProductType.ProductType;

public class Product {
	private int productID;
	private int quantity;
	private String productName;
	private double buyPrice;
	private SimpleDateFormat buyDate;
	private ProductType productTypeID;
	private Branch branch;
	private String buyOrigin;
	private String picture;
	private DBConnect db = new DBConnect();
	
	public Product(int quantity, SimpleDateFormat buyDate, String productName, double buyPrice, int productId, ProductType productTypeID, String picture, Branch branch, String buyOrigin)
	{
		this.productID = db.getNextAvailableProductID();
		this.quantity = quantity;
		this.productName = productName;
		this.buyPrice = buyPrice;
		this.buyDate = buyDate;
		this.productTypeID = productTypeID;
		this.branch = branch;
		this.buyOrigin = buyOrigin;
		this.picture = picture;
	}
	
	public int getProductID()
	{
		return productID;
	}
	
	public int getQuantity()
	{
		return quantity;
	}
	
	public String getProductName()
	{
		return productName;
	}

	public double getBuyPrice()
	{
		return buyPrice;
	}
	
	public SimpleDateFormat getBuyDate()
	{
		return buyDate;
	}
	
	public ProductType getProductTypeID()
	{
		return productTypeID;
	}
	
	public Branch getBranch()
	{
		return branch;
	}
	
	public String getBuyOrigin()
	{
		return buyOrigin;
	}
	public String getPicture()
	{
		return picture;
	}

}
