
public class ManagerProduct {
	private DBConnect db = new DBConnect();
 
	public void addProduct(Product product)
	{
		db.addProduct(product);
	}
	
	public void deleteProduct(int productID)
	{
		db.deleteProduct(productID);
	}
	
	public void modifyProduct(int productID)
	{

	}
	
	public void decrementProduct(int productID)
	{
		db.decrementProduct(productID);
	}
}
