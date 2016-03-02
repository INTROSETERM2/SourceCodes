package DB;
import java.text.SimpleDateFormat;

import Branch.Branch;
import Receipt.Receipt;

public class TryCodeClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DBConnect db = new DBConnect();
		
		db.signup("new", "bootyeater", "new chester", "new gaw", 1, 0);
		//System.out.println(db.login("3d12", "chesterpass"));
		//db.addReceipt(1, 30, 2, "Your mom", 2);
	
		//db.deleteReceipt(1);
		
		//ProductType productType = new ProductType("Glenn");
		
	//	db.addProductType(productType);
		//db.deleteProductType(1);
	
//	System.out.println(db.getNextAvailableProductTypeID());
	//	System.out.println(db.getPicture(1));
	
	
	}
}
	