package project;

import java.time.LocalDate;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import java.io.Serializable;

public abstract class foodItem implements Serializable{
	private long item_number;
	private String item_name;
	private double itemValue;
	private double quantity;
	private String quantity_type;
	private String expiry_date;
	private String date_of_production;
	private String supplier;
	
	private long close_to_expiry;
	private long low_stock_amount;
	
	public foodItem() { 
		//Default values if no attribute values are set
		item_number = -1;
		item_name = "none";
		itemValue = 0;
		quantity = -1;
		quantity_type = "none";
		expiry_date = "YYYY-MM-DD";
		date_of_production = "YYYY-MM-DD";
		supplier = "N/A";
	}
	
	public foodItem(long ino, String ina,double itemVal,  double quan, String qt, String exp, String dop, String sup) { 
		item_number = ino;
		item_name = ina;
		itemValue = itemVal;
		quantity = quan;
		quantity_type = qt;
		expiry_date = exp;
		date_of_production = dop;
		supplier = sup;
	}
	
	//Set methods
	public void setino(long ino) {
		this.item_number = ino;
	}
	
	public void setina(String ina) {
		this.item_name = ina;
	}
	
	public void setVal(double val) {
		this.itemValue = val;
	}
	
	public void setquan(double q) {
		this.quantity = q;
	}
	
	public void setqt(String qt) {
		this.quantity_type = qt;
	}
	
	public void setexp(String exp) {
		this.expiry_date = exp;
	}
	
	public void setdop (String dop) {
		this.date_of_production = dop;
	}
	
	public void setsup(String sup) {
		this.supplier = sup;
	}
	
	//get methods
	public long getin() {
		return this.item_number;
	}
	
	public String getina() {
		return this.item_name;
	}
	
	public double getVal() {
		return this.itemValue;
	}
	
	public double getquan() {
		return this.quantity;
	}
	
	public String getqt() {
		return this.quantity_type;
	}
	
	public String getexp() {
		return this.expiry_date;
	}
	
	public String getdop() {
		return this.date_of_production;
	}

	public String getsup() {
		return this.supplier;
	}
	
	public void getData() {
		Scanner console = new Scanner(System.in);
		System.out.print("Enter Item Number (ensure item value is unique): ");
		this.item_number = console.nextLong();
		System.out.print("Enter Item Name: ");
		this.item_name = console.next();
		System.out.print("Enter Item Value (AED): ");
		this.itemValue = console.nextDouble();
		System.out.print("Enter Item Quantity: ");
		this.quantity = console.nextDouble();
		System.out.print("Enter Item Quantity Type (Kg/g/mL/L): ");
		this.quantity_type = console.next();
		System.out.print("Enter Expiry Date (yyyy-mm-dd): ");
		this.expiry_date = console.next();
		System.out.print("Enter Date of Production (yyyy-mm-dd): ");
		this.date_of_production = console.next();
		System.out.print("Enter Item Supplier (add '_' instead of spaces: ");
		this.supplier = console.next();
	}
	
	public void setLowExpiry(long cl_exp) {
		this.close_to_expiry = cl_exp;
	}
	
	public void setLowStock(long cl_stock) {
		this.low_stock_amount = cl_stock;
	}
	
	// Prints total value of food item - quantity multiplied by value of individual unit
	public double calcTotalValue() {
		System.out.println("AED "+this.quantity*this.itemValue);
		return this.quantity*this.itemValue;
	}
	
	public boolean LowStockItems() { 
		//Prints item number, name and stock remaining and returns true if the item quantity is less than low stock value
		if (this.quantity < this.low_stock_amount) {
			System.out.println("Item number: "+this.item_number+"\nItem Name: "+this.item_name+"\nStock remaining: "+this.quantity+"\n");
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public boolean CloseToExpiry() {
		// Create a formatter to parse the date string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        // Parse the date string into a LocalDate object
        LocalDate targetDate = LocalDate.parse(this.expiry_date, formatter);
        
        // Get the current date
        LocalDate currentDate = LocalDate.now();
        
        // Calculate the difference in days between the current date and the target date
        long daysRemaining = ChronoUnit.DAYS.between(currentDate, targetDate);
        
        if (daysRemaining < this.close_to_expiry) {
        	System.out.println("Item number: "+this.item_number+"\nItem Name: "+this.item_name+"\nDays remaining till expiry: "+daysRemaining+"\n");
			return true;
        }
        else {
        	System.out.println("Item number: "+this.item_number+"\nItem Name: "+this.item_name+"\nDays remaining till expiry: "+daysRemaining+"\n");
        	return false;
        }
        
       
	}
	
	public boolean isExpired() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        // Parse the date string into a LocalDate object
        LocalDate targetDate = LocalDate.parse(this.getexp(), formatter);
		LocalDate currentDate = LocalDate.now();
		if(ChronoUnit.DAYS.between(currentDate, targetDate) < 0) {
			return true;
		}
		return false;
	}
	
	
	public abstract String toString();
}
