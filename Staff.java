import java.text.SimpleDateFormat;

public class Staff {
	
	private int staffID;
	private String userName;
	private String password;
	private String firstname;
	private String lastname;
	private static Boolean RANK;
	private Branch branch;
	private SimpleDateFormat creationDate;
	
	public Staff() {
		// TODO Auto-generated constructor stub
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

	public String firstname()
	{
		return firstname;
	}

	public String lastname()
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

