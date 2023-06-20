package project;

// Imports for reading and writing to and from files

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

// Imports for taking user input from console

import java.util.Scanner;

// Imports for using regular expression

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class driver {
	public static void main(String[] args) throws appException {
		Scanner console = new Scanner(System.in);
		int userInput;
		
		System.out.println("---------- Food Storage System ----------");
		
		do {
			System.out.println("\n-----------------------------------------");
			System.out.println("0 : Exit System");
			System.out.println("1 : Look for food item by id");
			System.out.println("2 : Delete a food item");
			System.out.println("3 : Display all food items");
			System.out.println("4 : Add food item");
			System.out.println("5 : Check if an item is expired");
			System.out.print("Enter input --> ");
			userInput = console.nextInt();
			
			switch(userInput) {
				case 1:
	        		findFoodId();
					break;
				case 2:
					deleteFoodItem();
					break;
				case 3:
					System.out.println("All Food Items\n-----------------------------------------");
					displayAll();
					break;
				case 4:
					addFoodItem();
					break;
				case 5:
					checkExpired();
					break;
			}
			
		} while (userInput != 0);
		
		System.out.print("------------- Session Ended -------------");
	}
	
	// Method to check whether an item is expired
	
	public static void checkExpired() {
		ArrayList<foodItem> fooditemsfile = readFoodItemListFromFile();
		System.out.print("Enter Id of Item: ");
		Scanner console = new Scanner(System.in);
		int id = console .nextInt();
		int flag = 0;
		for (foodItem f : fooditemsfile) {
        	if(f.getin() == id) {
        		System.out.println("Is item expired: " + f.isExpired());
        		flag++;
        	}
        }
		if (flag == 0) {
    		System.out.println("No Such Id");
    	}
	}
	
	// Method to add foodItem
	
	public static void addFoodItem() throws appException {
		Scanner console = new Scanner(System.in);
		ArrayList<foodItem> fooditemsfile = readFoodItemListFromFile();
		int userInput;
		
		System.out.println("What Type of Food Item Would You Like To Enter - ");
		System.out.println("1 : Meat and Seafood");
		System.out.println("2 : Pickled");
		System.out.println("3 : Canned");
		System.out.println("4 : Vegetable");
		System.out.print("Enter input --> ");
		userInput = console.nextInt();
		
		switch(userInput) {
			case 1:
				Meat newItem = new Meat();
				newItem.getData();
				suplimentAddFoodItem(newItem, fooditemsfile);
				break;
			case 2:
				Pickled newItem1 = new Pickled();
				newItem1.getData();
				suplimentAddFoodItem(newItem1, fooditemsfile);
				break;
			case 3:
				Canned newItem2 = new Canned();
				newItem2.getData();
				suplimentAddFoodItem(newItem2, fooditemsfile);
				break;
			case 4:
				Vegetable newItem3 = new Vegetable();
				newItem3.getData();
				suplimentAddFoodItem(newItem3, fooditemsfile);
				break;
		}
	}

	public static void suplimentAddFoodItem(foodItem newItem, ArrayList<foodItem> fooditemsfile) throws appException {
		int flag = 0;
		
		// Checking whether the added item has a common id with an already existing item
		
		for (foodItem f : fooditemsfile) {
			if (newItem.getin() == f.getin()) {
				flag = 1;
			}
		}
		
		// regex for expiry date and date of production
		String re = "^\\d{4}\\-\\d{2}\\-\\d{2}$";
		String exp = newItem.getexp();
		String dop = newItem.getdop();
		
		Pattern pt = Pattern.compile(re);
		Matcher mt1 = pt.matcher(exp);
		Matcher mt2 = pt.matcher(dop);
		boolean result1 = mt1.matches();
		boolean result2 = mt2.matches();
		
		if (result1 == false || result2 == false) {
			flag = 2;
		}
		
		if(flag != 0) {
			throw new appException(flag);
		} else {
			fooditemsfile.add(newItem);
			writeFoodItemListToFile(fooditemsfile);
		}
		
	}
	
	// Method to Display all food items
	
	public static void displayAll() {
		ArrayList<foodItem> fooditemsfile = readFoodItemListFromFile();
		int i = 0;
		for (foodItem f : fooditemsfile) {
			System.out.println(f);
			if (f instanceof Meat) {
				((Meat) f).RotTime();
			}
			if (f instanceof Vegetable) {
				((Vegetable) f).RotTime();
			}
		}
	}
	
	// Method to delete foodItem
	
	public static void deleteFoodItem() {
		ArrayList<foodItem> fooditemsfile = readFoodItemListFromFile();
		Scanner console = new Scanner(System.in);
		int flag = 0;
		System.out.print("Enter Id to delete: ");
		int id = console.nextInt();
		for (int i = 0; i < fooditemsfile.size(); i++) {
            if (fooditemsfile.get(i).getin() == id) {
                fooditemsfile.remove(fooditemsfile.get(i));
                flag = 1;
            }
        }
		if (flag == 0) {
    		System.out.println("No Such Id");
    	} else {
    		System.out.println("Item Deleted");
    		writeFoodItemListToFile(fooditemsfile);
    	}
	}
	
	// Method to search for foodItem with particular Id from file
	public static void findFoodId() {
		ArrayList<foodItem> fooditemsfile = readFoodItemListFromFile();
		System.out.print("Enter Id to search: ");
		Scanner console = new Scanner(System.in);
		int id = console .nextInt();
		int flag = 0;
		for (foodItem f : fooditemsfile) {
        	if(f.getin() == id) {
        		System.out.println(f);
        		flag++;
        	}
        }
		if (flag == 0) {
    		System.out.println("No Such Id");
    	}
	}
	
	// Method to write an ArrayList of foodItem objects to a file
    public static void writeFoodItemListToFile(ArrayList<foodItem> list) {
    	String filename = "foodItems.dat";
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Method to read an ArrayList of foodItem objects from a file
    public static ArrayList<foodItem> readFoodItemListFromFile() {
    	String filename = "foodItems.dat";
        ArrayList<foodItem> list = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            list = (ArrayList<foodItem>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

}
