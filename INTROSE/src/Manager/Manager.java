package Manager;

import Branch.Branch;
import Product.Product;
import ProductType.ProductType;
import Staff.Staff;

//will be used when i can fix it
public abstract class Manager {

	public abstract void add(Branch branch);
	
	public abstract void add(Staff staff);
	
	public abstract void add(Product product);
	
	public abstract void add(ProductType productType);
	
	public abstract void delete(int i);

	public abstract void modify();

}
