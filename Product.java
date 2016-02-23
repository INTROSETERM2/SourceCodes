import java.text.SimpleDateFormat;

public class Product {
	private int productID;
	private int quantity;
	private String productName;
	private float buyPrice;
	private SimpleDateFormat buyDate;
	private ProductType productTypeID;
	private Branch branch;
	private String buyOrigin;
	private String picture;
	
	public Product(int quantity, String productName, float buyPrice, int productId, ProductType productTypeID, String picture, Branch branch, String buyOrigin)
	{
		this.productID = productID;
		this.quantity =quantity;
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

	public float getBuyPrice()
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
