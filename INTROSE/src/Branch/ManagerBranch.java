package Branch;

import DB.DBConnect;

public class ManagerBranch {
	private DBConnect db = new DBConnect();
	 
	public void addBranch(Branch branch)
	{
		//db.addBranch(branch);
	}
	
	public void deleteBranch(int branchID)
	{
		db.deleteBranch(branchID);
	}
	
	public void modifyBranch()
	{

	}
}
