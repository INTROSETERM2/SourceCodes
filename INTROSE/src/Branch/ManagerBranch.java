package Branch;

import java.util.ArrayList;

import DB.DBConnect;

public class ManagerBranch {
	private DBConnect db = new DBConnect();
	 
	public void addBranch(Branch branch)
	{
		db.addBranch(branch);
	}
	
//	public void deleteBranch(int branchID)
//	{
//		db.deleteBranch(branchID);
//	}
	
	public void deleteBranch(String branchName){
		db.deleteBranch(branchName);
	}
	
	public void modifyBranch()
	{

	}
	
	public ArrayList<String> getBranches(){
		return db.getBranches();
	}
}
