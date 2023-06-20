package project;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.io.Serializable;

public class Meat extends foodItem implements Rot, Serializable{
	private String cut; // slices, fillet, whole...
	
	public Meat() {
		super();
		this.cut = "none";
	}
	
	public Meat(long ino, String ina, double itemVal, double qty, String qty_type, String exp, String dop, String sup, String cut) {
		super(ino,ina,itemVal,qty,qty_type,exp,dop,sup);
		this.cut = cut;
	}
	
	public void setCut(String cut) {
		this.cut = cut;
	}
	
	public String getCut() {
		return cut;
	}
	
	@Override
	public void getData() {
		Scanner console = new Scanner(System.in);
		super.getData();
		System.out.print("Enter Cut of Meat (slices/fillet/whole): ");
		this.cut = console.next();
	}
	
	@Override
	public String toString () {
		String txt = "Food type: Meat and Seafood";
		txt += "\nItem number: "+this.getin() +
		"\nItem name: "+this.getina()+ " (Portions: "+this.cut+")"+
		"\nValue: AED "+this.getVal()+"/pack"+
		"\nQuantity: "+this.getquan()+" "+this.getqt()+
		"\nDate of production: "+this.getdop()+
		"\nExpiry date: "+this.getexp()+
		"\nSupplier: "+this.getsup();
		return txt;
	}
	
	
	
	public void RotTime() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        // Parse the date string into a LocalDate object
        LocalDate targetDate = LocalDate.parse(this.getexp(), formatter);
		LocalDate currentDate = LocalDate.now();
		System.out.println("The meat will rot in " + ChronoUnit.DAYS.between(currentDate, targetDate) + " days \n");
	}
}

