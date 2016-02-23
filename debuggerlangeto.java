
public class debuggerlangeto {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DBConnect db = new DBConnect();
		
		//db.signup("new", "bootyeater", "new chester", "new gaw", 1, 0);
		//System.out.println(db.login("3d12", "chesterpass"));
		//db.addReceipt(1, 30, 2, "Your mom", 2);
	
		db.deleteReceipt(1);
		System.out.println(db.getPicture(1));
	}

}
