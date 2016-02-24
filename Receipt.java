import java.text.SimpleDateFormat;

public class Receipt {
	private int receiptID;
	private int staffID;
	private float soldPrice;
	private int soldQuantity;
	private SimpleDateFormat soldDate;
	private String customerName;
	private Branch soldBranch;
	private float soldTotalPrice;
	private int soldProductID;	
	
	public Receipt(int receiptID, int staffID, float soldPrice, int soldQuantity, SimpleDateFormat soldDate, String customerName, Branch soldBranch, int soldProductID)
	{
		this.receiptID = receiptID;
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

	public float getSoldPrice() {
		return soldPrice;
	}

	public int getSoldQuantity() {
		return soldQuantity;
	}

	public SimpleDateFormat getSoldDate() {
		return soldDate;
	}

	public String getCustomerName() {
		return customerName;
	}

	public Branch getSoldBranch() {
		return soldBranch;
	}

	public float getSoldTotalPrice() {
		return soldTotalPrice;
	}

	public int getSoldProductID() {
		return soldProductID;
	}
	
	
	
	
}
