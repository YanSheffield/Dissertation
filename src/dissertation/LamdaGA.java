package dissertation;

import java.util.Arrays;

import cern.jet.random.engine.MersenneTwister;

public class LamdaGA{
	
	private int pb = 1000;// total capacity
	private int LL;// the length of chromosome
	private int scale;// the scale of population
	private int MAX_GEN;// the number of generation
	private int bestT;// the best generation
	private int bestLength;// the best value of offspring
	private int[] bestTour;// the best bit string
	private int scaleParent;
	private int numberVariables;// the number variables for each clause 3
	private int numberClause;// the number of clauses for individual 22
	private int[] variable;
	private int gap;
	private MersenneTwister random;
	/*
	 * 父代种群，行数（第一个）表示规模，一行代表一个个体：染色体。 列表示染色体基因片段，0或者1
	 */
	private int[] oldPopulation;
	private int[][] newPopulation;
	

	private int[][] newPopulation1;
	private int[] fitness;// fitness array
	private int fitnessParent;
	private float Pc;// uniform crossover probability with parent
	private float Pm;// mutation probability
	public int t;// current generation
//	private Random random;

	public LamdaGA(int p, int s, int l, int g, float c, float m) {
		scaleParent = p;
		scale = s;
		LL = l;
		MAX_GEN = g;
		Pc = c;
		Pm = m;
	}

	public LamdaGA(int p, int s, int l, int g, float c, float m, int v, int nc) {
		scaleParent = p;
		scale = s;
		LL = l;
		MAX_GEN = g;
		Pc = c;
		Pm = m;
		numberVariables = v;
		numberClause = nc;
	}

	public void init() {
		bestLength = 0;
		bestTour = new int[LL];
		bestT = 0;
		t = 0;
		newPopulation = new int[scale][LL];
		newPopulation1 = new int[scale][LL];
		oldPopulation = new int[LL];
		fitness = new int[scale];
		variable = new int[numberVariables];
		random = new MersenneTwister(new java.util.Date());
	}

	public void mutationOperator() {
		for (int k = 0; k < scale; k++) {
			// mutation from parent
			newPopulation[k] = oldPopulation.clone();
			for (int j = 0; j < LL; j++) {
				if (random.nextFloat() >= Pm){
					newPopulation1[k][j] = newPopulation[k][j];
				}else {
					if(newPopulation[k][j]==1){
						newPopulation[k][j] = newPopulation[k][j] -1;
					}else {
						newPopulation[k][j] = newPopulation[k][j] +1;
					}					
					newPopulation1[k][j] = newPopulation[k][j];
				}				
			}
		}
	}

	public void calcuOldPopulation(FitnessFunctions fitnessFunctions){
		fitnessParent = fitnessFunctions.evaluate(oldPopulation, LL);
//		System.out.println("lambdaGA "+fitnessParent);
	}
	
	// calculate the fitness value of children population
	public void calculateChirldrenFitnessKnapsack(FitnessFunctions fitnessFunctions) {
		for (int k = 0; k < scale; k++) {
			fitness[k] = fitnessFunctions.evaluate(newPopulation1[k],LL);
		}
	}

	public int[] getFitness() {
		return fitness;
	}

	public void setFitness(int[] fitness) {
		this.fitness = fitness;
	}

	public int[][] getNewPopulation1() {
		return newPopulation1;
	}

	public void setNewPopulation1(int[][] newPopulation1) {
		this.newPopulation1 = newPopulation1;
	}

	// call uniform crossover
	public void callUniformCrossover() {
		for (int u = 0; u < scale; u++) {
//			uniformCrossover(u);
			for (int y = 0; y < LL; y++) {
//				boolean b = random.nextFloat() <= Pc;
				if (random.nextFloat() <= Pc) {
					newPopulation1[u][y] = bestTour[y];			
				} else {
					newPopulation1[u][y] = oldPopulation[y];
				}
			}
		}
	}
	
//	public void uniformCrossover(int u) {
//		for (int y = 0; y < LL; y++) {
//			boolean b = random.nextFloat() <= Pc;
//			if (b) {
//				newPopulation1[u][y] = bestTour[y];			
//			} else {
//				newPopulation1[u][y] = oldPopulation[y];
//			}
//		}
//	}
	// // select the best individual and copy to next generation.
	public void selectBestGh() {
		int k, i, maxid;
		int maxevaluation;
		maxid = 0;
		maxevaluation = fitness[0];
		for (k = 1; k < scale; k++) {
			if (maxevaluation < fitness[k]) {
				maxevaluation = fitness[k];
				maxid = k;
			}
		}
		if (bestLength < maxevaluation) {
			bestLength = maxevaluation;
			bestT = t;
			for (i = 0; i < LL; i++) {
				bestTour[i] = newPopulation1[maxid][i];
			}
		}
	}

	public void judgeValueWithParent(){
		if(bestLength>=fitnessParent){
			oldPopulation = bestTour;
			fitnessParent = bestLength;
		}
		
	}

	public int getFitnessParent() {
		return fitnessParent;
	}

	public void setFitnessParent(int fitnessParent) {
		this.fitnessParent = fitnessParent;
	}

	public int[] getOldPopulation() {
		return oldPopulation;
	}

	public void setOldPopulation(int[] oldPopulation) {
		this.oldPopulation = oldPopulation;
	}

	public int getMAX_GEN() {
		return MAX_GEN;
	}

	public void setMAX_GEN(int mAX_GEN) {
		MAX_GEN = mAX_GEN;
	}

	public int getLL() {
		return LL;
	}

	public void setLL(int lL) {
		LL = lL;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public int[] getBestTour() {
		return bestTour;
	}

	public void setBestTour(int[] bestTour) {
		this.bestTour = bestTour;
	}
	public int getBestLength() {
		return bestLength;
	}

	public void setBestLength(int bestLength) {
		this.bestLength = bestLength;
	}
	public int[][] getNewPopulation() {
		return newPopulation;
	}

	public void setNewPopulation(int[][] newPopulation) {
		this.newPopulation = newPopulation;
	}
}
