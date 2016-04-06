package Receipt;
import java.util.ArrayList;

import DB.DBConnect;

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
	
	public ArrayList<Receipt> getTodayReceipt(int branchNumber){
		return db.getTodayReceipts(branchNumber);
	}
	

}
