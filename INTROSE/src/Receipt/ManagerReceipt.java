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
	
	public ArrayList<String> getMonthReceipts(int branchNum, int month, int year){
		return db.getMonthReceipts(branchNum, month, year);
	}
	
	public ArrayList<Receipt> getDayReceipt(int branchNumber, String date){
		return db.getDayReceipts(branchNumber, date);
	}
	
	public int getEarlyYear(int branchNumber){
		return db.getEarliestYear(branchNumber);
	}
	
	public int getLatestYear(int branchNumber){
		return db.getLatestYear(branchNumber);
	}
	

}
