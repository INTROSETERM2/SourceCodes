package Receipt;
import java.text.SimpleDateFormat;
import java.util.Date;

import Branch.Branch;
import DB.DBConnect;

public class Receipt {
	private int receiptID;
	private int staffID;
	private double soldPrice;
	private int soldQuantity;
	private Date soldDate;
	private String customerName;
	private Branch soldBranch;
	private double soldTotalPrice;
	private int soldProductID;	
	private DBConnect db = new DBConnect();
	
	public Receipt(int staffID, double soldPrice, int soldQuantity, Date soldDate, String customerName, Branch soldBranch, int soldProductID)
	{
		this.receiptID = db.getNextAvailableReceiptID();
		this.staffID = staffID;
		this.soldPrice = soldPrice;
		this.soldQuantity = soldQuantity;
		this.soldDate = soldDate;
		this.customerName = customerName; 
		this.soldBranch = soldBranch;
		this.soldTotalPrice = soldQuantity * soldPrice; 
		this.soldProductID = soldProductID;
	}

	public int getReceiptID(){
		return receiptID;

	}
	public int getStaffID() {
		return staffID;
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

	public double getSoldTotalPrice() {
		return soldTotalPrice;
	}

	public int getSoldProductID() {
		return soldProductID;
	}
	
	
	
	
}
