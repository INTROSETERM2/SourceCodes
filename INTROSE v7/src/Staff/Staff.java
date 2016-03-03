package Staff;
import java.text.SimpleDateFormat;

import Branch.Branch;
import DB.DBConnect;

public class Staff {
	
	private int staffID;
	private String userName;
	private String password;
	private String firstname;
	private String lastname;
	private static Boolean RANK;
	private Branch branch;
	private SimpleDateFormat creationDate;
	private DBConnect db = new DBConnect();
	
	public Staff(String userName, String password, String firstname, String lastname, Boolean rank, Branch branch, SimpleDateFormat creationDate) {
		// TODO Auto-generated constructor stub
		this.staffID = db.getNextAvailableStaffID();
		this.userName = userName;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		Staff.RANK = rank;
		this.branch = branch;
		this.creationDate = creationDate;

	}
	
	public int getStaffID()
	{
		return staffID;
	}
	
	public String getUsername()
	{
		return userName;
	}
	
	public String getPassword()
	{
		return password;
	}

	public String getFirstname()
	{
		return firstname;
	}

	public String getLastname()
	{
		return lastname;
	}
	
	public Boolean getRank()
	{
		return RANK;
	}
	
	public Branch getBranch()
	{
		return branch;
		
	}
	
	public SimpleDateFormat getCreationDate()
	{
		return creationDate;
	}

}

