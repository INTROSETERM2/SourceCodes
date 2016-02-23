import java.util.ArrayList;

public class Branch {
	private static int BRANCH_ID;
	private String branchName;
	private int branchNumberOfStaff;
	private ArrayList<Staff> branchStafflist;
	
	public Branch(int branchID, String branchName, int branchNumberOfStaff)
	{
		this.BRANCH_ID = branchID;
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
	
	public int branchNumberOfStaff()
	{
		return branchNumberOfStaff;
	}
	
	public ArrayList<Staff> getBranchStafflist()
	{
		return branchStafflist;
	}
	
}
