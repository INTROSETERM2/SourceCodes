
public class ManagerReceipt {
	private DBConnect db = new DBConnect();
	public void addReceipt(Receipt receipt)
	{
		db.addReceipt(receipt);
	}
	
	public void deleteProduct(int receiptID)
	{
		db.deleteReceipt(receiptID);
	}
	
	public void modifyProduct(int receiptID)
	{

	}
	

}
