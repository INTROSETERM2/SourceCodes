package Branch;
import java.util.Date;

import DB.DBConnect;

public class Branch {
	private int branchID;
	private String branchName;
	private String branchUsername;
	private String branchPassword;
	private Date branchCreationDate;
	
	private DBConnect db = new DBConnect();
	
	public Branch(int branchID, String branchName, String branchUsername, String branchPassword, Date branchCreationDate)
	{
		//Branch.BRANCH_ID = db.getNextAvailableBranchID();
		this.branchName = branchName;
		this.branchID = branchID;
		this.branchUsername = branchUsername;
		this.branchPassword = branchPassword;
		this.branchCreationDate = branchCreationDate;
		
	}
	
	public Branch() {
		// TODO Auto-generated constructor stub
	}

	public String getBranchName()
	{
		return branchName;
	}
	
	//no need na for this kasi static naman
	public int getBranchID()
	{
		return branchID;
	}

	public String getBranchUsername() {
		return branchUsername;
	}
	
	public void setBranchID(int i){
		branchID = i;
	}

	public void setBranchUsername(String branchUsername) {
		this.branchUsername = branchUsername;
	}

	public String getBranchPassword() {
		return branchPassword;
	}

	public void setBranchPassword(String branchPassword) {
		this.branchPassword = branchPassword;
	}

	public Date getBranchCreationDate() {
		return branchCreationDate;
	}

	public void setBranchCreationDate(Date branchCreationDate) {
		this.branchCreationDate = branchCreationDate;
	}
	
}
