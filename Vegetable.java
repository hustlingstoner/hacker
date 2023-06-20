package project;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Vegetable extends foodItem implements Rot, Serializable {
	private boolean fresh;
	private boolean organic;
	private static int vegetableCount = 0;
	
	public Vegetable() {
		super();
		this.fresh = false;
		this.fresh = false;
	}
	
	public Vegetable(long ino, String ina, double itemVal, double quan, String qt, String exp, String dop, String sup,boolean fresh, boolean organic) {
		super(ino, ina, itemVal, quan, qt, exp, dop, sup);
		this.fresh=fresh;
		this.organic=organic;
	} 
	
	public boolean isFresh() {
		return fresh;
	}
	
	public void setFresh(boolean fresh) {
		this.fresh = fresh;
	}
	
	public boolean isOrganic() {
		return organic;
	}
	
	public void setOrganic(boolean organic) {
		this.organic = organic;
	}
	
	@Override
	public void getData() {
		Scanner console = new Scanner(System.in);
		super.getData();
		System.out.print("Is The Item Fresh? (true/false): ");
		this.fresh = console.nextBoolean();
		System.out.print("Is The Item Organic? (true/false): ");
		this.organic = console.nextBoolean();
	}
	
	@Override
	public String toString () {
		String txt = "Food type: Vegetables";
		txt += "\nItem number: "+this.getin() +
		"\nItem name: "+this.getina()+
		"\nValue: AED "+this.getVal()+"/pack"+
		"\nQuantity: "+this.getquan()+" "+this.getqt()+
		"\nDate of production: "+this.getdop()+
		"\nExpiry date: "+this.getexp()+
		"\nSupplier: "+this.getsup()+"\nFresh: "+this.isFresh()
		+"\nOrganic: "+this.isOrganic();
		return txt;
	}

	@Override
	public void RotTime() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        // Parse the date string into a LocalDate object
        LocalDate targetDate = LocalDate.parse(this.getexp(), formatter);
		LocalDate currentDate = LocalDate.now();
		System.out.println("The vegetable will rot in " + ChronoUnit.DAYS.between(currentDate, targetDate) + " days \n");
		
	}
}
