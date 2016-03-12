package Receipt;
import java.text.SimpleDateFormat;
import java.util.Date;

import Branch.Branch;
import DB.DBConnect;

public class Receipt {
	private int receiptID;
	private String staffName;
	private double soldPrice;
	private int soldQuantity;
	private Date soldDate;
	private String customerName;
	private Branch soldBranch;
	private int soldProductID;	
	private String soldProductName;
	private DBConnect db = new DBConnect();
	
	public Receipt(String staffName, double soldPrice, int soldQuantity, Date soldDate, String customerName, Branch soldBranch, int soldProductID, String soldProductName)
	{
		this.receiptID = db.getNextAvailableReceiptID();
		this.staffName= staffName;
		this.soldPrice = soldPrice;
		this.soldQuantity = soldQuantity;
		this.soldDate = soldDate;
		this.customerName = customerName; 
		this.soldBranch = soldBranch;
		this.soldProductID = soldProductID;
		this.soldProductName = soldProductName;

	}
	
	public Receipt(int receiptID, String staffName, double soldPrice, int soldQuantity, Date soldDate, String customerName, Branch soldBranch, int soldProductID, String soldProductName)
	{
		this.receiptID = receiptID;
		this.staffName= staffName;
		this.soldPrice = soldPrice;
		this.soldQuantity = soldQuantity;
		this.soldDate = soldDate;
		this.customerName = customerName; 
		this.soldBranch = soldBranch;
		this.soldProductID = soldProductID;
		this.soldProductName = soldProductName;

	}

	public int getReceiptID(){
		return receiptID;

	}
	public String getStaffName() {
		return staffName;
	}

	public double getSoldPrice() {
		return soldPrice;
	}

	public int getSoldQuantity() {
		return soldQuantity;
	}

	public Date getSoldDate() {
		return soldDate;
	}

	public String getCustomerName() {
		return customerName;
	}

	public Branch getSoldBranch() {
		return soldBranch;
	}
	
	public int getSoldProductID() {
		return soldProductID;
	}

	public String getSoldProductName() {
		return soldProductName;
	}

	
	
	
}
