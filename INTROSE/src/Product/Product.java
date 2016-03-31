package Product;

import java.util.Date;



import java.text.SimpleDateFormat;

import Branch.Branch;
import DB.DBConnect;
import ProductType.ProductType;

public class Product {
	private int productID;
	private int quantity;
	private String productName;
	private double buyPrice;
	private Date buyDate;
	private int productTypeID;
	private Branch branch;
	private String buyOrigin;
	private String picture;
	
	public Product(int quantity, Date buyDate, String productName, double buyPrice, int productID, int productTypeID, String picture, Branch branch, String buyOrigin)
	{
		this.productID = productID;
		this.quantity = quantity;
		this.productName = productName;
		this.buyPrice = buyPrice;
		this.buyDate = buyDate;
		this.productTypeID = productTypeID;
		this.picture = picture;
		this.branch = branch;
		this.buyOrigin = buyOrigin;
	}
	
	public Product(int quantity, Date buyDate, String productName, double buyPrice, int productID, int productTypeID, String picture, String buyOrigin)
	{
		this.productID = productID;
		this.quantity = quantity;
		this.productName = productName;
		this.buyPrice = buyPrice;
		this.buyDate = buyDate;
		this.productTypeID = productTypeID;
		this.picture = picture;
		this.buyOrigin = buyOrigin;
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
	
	public Date getBuyDate()
	{
		return buyDate;
	}
	
	public int getProductTypeID()
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
