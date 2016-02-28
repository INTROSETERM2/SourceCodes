package Staff;

import DB.DBConnect;

	public class ManagerStaff{
	private DBConnect db = new DBConnect();
		 
	public void addStaff(Staff staff)
	{
		db.addStaff(staff);
	}
	
	public void deleteStaff(int staffID)
	{
		db.deleteStaff(staffID);
	}
	
	public void modifyStaff()
	{

	}
}
