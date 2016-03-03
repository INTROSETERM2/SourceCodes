package ProductType;

import DB.DBConnect;

public class ProductType {
	private int productTypeID;
	private String productTypeName;
	private DBConnect db = new DBConnect();
	
	
	public ProductType(String productTypeName)
	{
		this.productTypeID = db.getNextAvailableProductTypeID();
		this.productTypeName = productTypeName;
	}
	
	public int getProductTypeId()
	{
		return productTypeID;
	}
	
	public String getProductTypeName()
	{
		return productTypeName;
	}
}


