package dissertation;

public class StackStaff {

	boolean b = false;
	public static void main(String[] args) {
		StackStaff stackStaff = new StackStaff();
		stackStaff.judege();
	}
	
	public String judege(){
		if (b) {
			return "Yan";
		}
		else {
			return "gE";
		}
	}

}
