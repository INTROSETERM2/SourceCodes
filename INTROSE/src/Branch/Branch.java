package Branch;
import java.util.ArrayList;

import DB.DBConnect;
import Staff.Staff;

public class Branch {
	private static int BRANCH_ID;
	private String branchName;
	private int branchNumberOfStaff;
	private ArrayList<Staff> branchStafflist;
	private DBConnect db = new DBConnect();
	
	public Branch(String branchName, int branchNumberOfStaff)
	{
		Branch.BRANCH_ID = db.getNextAvailableBranchID();
		this.branchName = branchName;
		this.branchNumberOfStaff = branchNumberOfStaff;
		//this.branchStafflist = branchStafflist;
	}
	
	public String getBranchName()
	{
		return branchName;
	}
	
	
	//no need na for this kasi static naman
	public int getBranchID()
	{
		return BRANCH_ID;
	}
	
	public int getBranchNumberOfStaff()
	{
		return branchNumberOfStaff;
	}
	
	public ArrayList<Staff> getBranchStafflist()
	{
		return branchStafflist;
	}
	
}
