package project;
import java.io.Serializable;
import java.util.Scanner;

public class Pickled extends foodItem implements Serializable {
	private String brand;
	private String packing;//type of packing such as jar, bottles etc..
	
	public Pickled() {
		super();
		this.brand = null;
		this.packing = null;
	}
	
	public Pickled(long ino, String ina, double itemVal, double quan, String qt, String exp, String dop, String sup,String brand,String packing) {
		super(ino, ina, itemVal, quan, qt, exp, dop, sup);
		this.brand=brand;
		this.packing=packing;
	}
	
	public void setBrand(String brand){
		this.brand=brand;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public void setPacking(String packing){
		this.packing= packing;
	}
	
	public String getPacking(){
		return packing;
	}
	
	@Override
	public void getData() {
		Scanner console = new Scanner(System.in);
		super.getData();
		System.out.print("Enter Brand of Item (Heinz/Dole/Spam): ");
		this.brand = console.next();
		System.out.print("Enter type of Packing (jar/bottle): ");
		this.packing = console.next();
	}
	
	@Override
	public String toString() {
		String txt = "Food type: Pickled";
		txt += "\nItem number: "+this.getin() +
		"\nItem name: "+this.getina()+
		"\nBrand: "+this.getBrand()+
		"\nValue: AED "+this.getVal()+"/pack"+
		"\nQuantity: "+this.getquan()+" "+this.getqt()+
		"\nDate of production: "+this.getdop()+
		"\nExpiry date: "+this.getexp()+
		"\nSupplier: "+this.getsup()+
		"\nPacking: "+this.getPacking()+'\n';
		return txt;
	}
}

