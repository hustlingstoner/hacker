package project;

public class appException extends Exception {
	int errorCode;
	
	public appException(int errorCode) {
		this.errorCode = errorCode;
	};
	
	@Override
	public String toString() {
		String msg = "";
		if (this.errorCode == 1) {
			msg = "Food item with given item number already exists";
		} else if (this.errorCode == 2) {
			msg = "Invalid date format for expiry date or date of production";
		}
		return msg;
	}

}
