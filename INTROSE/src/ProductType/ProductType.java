package ProductType;

import DB.DBConnect;

public class ProductType {
	private int productTypeID;
	private String productTypeName;
	private DBConnect db = new DBConnect();
	
	// Constructor if to db
	public ProductType(String productTypeName)
	{
		this.productTypeID = db.getNextAvailableProductTypeID();
		this.productTypeName = productTypeName;
	}
	
	// Constructor if from db
	public ProductType(int productTypeID, String producTypeName){
		this.productTypeID = productTypeID;
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


