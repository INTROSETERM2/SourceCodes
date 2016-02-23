
public class ProductType {
	private int productTypeID;
	private String productTypeName;
	
	
	public ProductType(int productTypeID, String productTypeName)
	{
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


