package dissertation;

import java.math.BigDecimal;

public class PartitionProblem extends FitnessFunctions{
	
	protected int[] workload = { 220, 208, 198, 192, 180, 180, 165, 162, 160, 158, 155, 130, 125, 122, 120, 118, 115, 110, 105,
			101, 100, 100, 98, 96, 95, 90, 88, 82, 80, 77, 75, 73, 72, 70, 69, 66, 65, 63, 60, 58, 56, 50, 30, 20, 15,
			10, 8, 5, 3, 1 };// items value for Knapsack, or workload for
								// partition problem
	protected int LL;
	protected int gap;
	private BigDecimal b;
	private float f1;
	public int evaluate(int[] chromosome,int LL) {
		this.LL = LL;
		int vv = 0;
		int bb = 0;
		for (int i = 0; i < LL; i++) {
			if (chromosome[i] == 1) {
				vv += workload[i];// 累加价值
			} else {
				bb += workload[i];
			}
		}
		gap = 3000 - (Math.abs(bb - vv));
		return Math.abs(gap);
	}
	public String range(float i){
		String s;
		b = new BigDecimal(i);
		f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
		s = String.valueOf(f1);
		if (s.indexOf(".") > 0) {
			s = s.replaceAll(".0+?$", "");
		}
		return s;
	}
}
