package project;
import java.io.Serializable;
import java.util.Scanner;


public class Canned extends foodItem implements Serializable {
	private String brand;
	
	public Canned() {
		super();
		this.brand = null;
	}
	
	public Canned(long ino, String ina, double itemVal, double quan, String qt, String exp, String dop, String sup,String brand) {
		super(ino, ina, itemVal, quan, qt, exp, dop, sup);
		this.brand = brand;
	}
	
	public void setBrand(String brand) {
		this.brand=brand;
	}
	
	public String getBrand() {
		return brand;
	}
	
	@Override
	public void getData() {
		Scanner console = new Scanner(System.in);
		super.getData();
		System.out.print("Enter Brand of Item (Heinz/Dole/Spam): ");
		this.brand = console.next();
	}
	
	@Override
	public String toString() {
		String txt = "Food type: Canned";
		txt += "\nItem number: "+this.getin() +
		"\nItem name: "+this.getina()+
		"\nBrand: "+this.getBrand()+
		"\nValue: AED "+this.getVal()+"/pack"+
		"\nQuantity: "+this.getquan()+" "+this.getqt()+
		"\nDate of production: "+this.getdop()+
		"\nExpiry date: "+this.getexp()+
		"\nSupplier: "+this.getsup()+'\n';
		return txt;
	}
}
