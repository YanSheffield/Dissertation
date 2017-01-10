package dissertation;

public class KnapsackProblem extends FitnessFunctions{
	private int[] item = { 220, 208, 198, 192, 180, 180, 165, 162, 160, 158, 155, 130, 125, 122, 120, 118, 115, 110, 105,
			101, 100, 100, 98, 96, 95, 90, 88, 82, 80, 77, 75, 73, 72, 70, 69, 66, 65, 63, 60, 58, 56, 50, 30, 20, 15,
			10, 8, 5, 3, 1 };// items value for 
	// capacity of each item
	private int[] weight = { 80, 82, 85, 70, 72, 70, 66, 50, 55, 25, 50, 55, 40, 48, 50, 32, 22, 60, 30, 32, 40, 38, 35, 32,
				25, 28, 30, 22, 50, 30, 45, 30, 60, 50, 20, 65, 20, 25, 30, 10, 20, 25, 15, 10, 10, 10, 4, 4, 2, 1 };
	
	private int LL;
	private int boundary = 1000;// the total capacity
	
	public int evaluate(int[] chromosome,int LL) {
		this.LL = LL;
		int vv = 0;
		int bb = 0;
		for (int i = 0; i < LL; i++) {
			if (chromosome[i] == 1) {
				vv += this.item[i];// the accumulated value
				bb += this.weight[i];// the accumulated capacity
			}
		}
		if (bb > boundary) {
			return 0;
		} else {
			return vv;
		}
	}
	
	public String range(float i){
		String s;
		s = String.valueOf((float) Math.ceil(i));
		if (s.indexOf(".") > 0) {
			s = s.replaceAll(".0+?$", "");
		}
		return s;
	}
}
