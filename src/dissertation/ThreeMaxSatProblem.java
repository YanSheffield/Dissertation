package dissertation;

import java.math.BigDecimal;
import java.util.Arrays;

public class ThreeMaxSatProblem extends FitnessFunctions {

	protected int[][] maxsat = { { 0, 4, 6 }, { 5, 3, 6 }, { 8, 6, 9 }, { 0, 6, 4 }, { 1, 2, 8 }, { 7, 2, 0 },
			{ 2, 5, 1 }, { 4, 9, 5 }, { 8, 0, 2 }, { 4, 7, 3 }, { 12, 14, 16 }, { 15, 13, 16 }, { 18, 16, 19 },
			{ 10, 16, 14 }, { 11, 12, 18 }, { 17, 12, 10 }, { 12, 15, 11 }, { 14, 19, 15 }, { 18, 10, 12 },
			{ 14, 17, 13 }, { 22, 24, 26 }, { 25, 23, 26 }, { 28, 26, 29 }, { 20, 26, 24 }, { 21, 22, 28 },
			{ 27, 22, 20 }, { 22, 25, 21 }, { 24, 29, 25 }, { 28, 20, 22 }, { 24, 27, 23 }, { 32, 34, 36 },
			{ 35, 33, 36 }, { 38, 36, 39 }, { 30, 36, 34 }, { 31, 32, 38 }, { 37, 32, 30 }, { 32, 35, 31 },
			{ 34, 39, 35 }, { 38, 30, 32 }, { 34, 37, 33 }, { 42, 44, 46 }, { 45, 43, 46 }, { 48, 46, 49 },
			{ 40, 46, 44 }, { 41, 42, 48 }, { 47, 42, 40 }, { 42, 45, 41 }, { 44, 49, 45 }, { 48, 40, 42 },
			{ 44, 47, 43 } };

	protected int numberVariables = 3;
	private BigDecimal b;
	private float f1;
//	int[] chrom = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,1,1,1,1,1,0,0,0,1,1,1,1,0,0,0,0,1,1,1,1,0,1,0,1};
	public int evaluate(int[] chromosome, int LL) {
		int testPoint = 0;
		String position = null;
		int intPosition = 0;
		String[] arrayTarget = { "0", "1", "2", "3", "6", "7", "12", "13", "15", "17", "22", "23", "25", "27", "32",
				"33", "35", "37", "42", "43", "45", "47" };
		for (int i = 0; i < LL; i++) {// the number of clause is 50
			int[] copyChromosome = chromosome.clone(); //remain the original bitString
			for (int k = 0; k < numberVariables; k++) {// 3 Variables in each
														// clause
				position = Integer.toString(maxsat[i][k]);
				intPosition = Integer.parseInt(position);
				if (Arrays.asList(arrayTarget).contains(position) && i % 2 == 0) {//flip bit string
					if (chromosome[intPosition] == 1) {
						copyChromosome [intPosition] = copyChromosome [intPosition] - 1;	
					}else {
						copyChromosome [intPosition] = copyChromosome [intPosition] + 1;
					}
				}
				
				if (copyChromosome [intPosition] == 1) {
					testPoint = testPoint + 1;
					break;
				}
			}
//			System.out.println("chro "+Arrays.toString(chromosome));
		}
		
		return testPoint;
	}

	public String range(float i) {
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
