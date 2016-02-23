
public class ManagerProduct {
	private DBConnect db;
	public void addProduct(Product product)
	{
		db = new DBConnect();
		
		db.addProduct(product.getQuantity(), product.getProductName(), product.getBuyPrice(), 
				product.getProductTypeID().getProductTypeId(), product.getBranch().getBranchID(), 
				product.getBuyOrigin(), product.getPicture());
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
