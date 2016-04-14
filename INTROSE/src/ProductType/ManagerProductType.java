package ProductType;
import java.util.ArrayList;

import DB.DBConnect;

public class ManagerProductType {

	private DBConnect db = new DBConnect();
	public void addProductType(ProductType productType)
	{
		db.addProductType(productType);
	}
	
	public void deleteProductType(int productID)
	{
		db.deleteProductType(productID);
	}
	
	public void modifyProductType()
	{
		
	}
	
	public ArrayList<ProductType> getProductTypes(){
		return db.getProductTypes();
	}
}
