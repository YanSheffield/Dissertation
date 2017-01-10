package dissertation;

import java.util.Arrays;
//import java.util.Random;
import cern.jet.random.engine.MersenneTwister;

public class OneEA{

	private int pb = 1000;// the total capacity
	private int LL;// length of chromosome
	private int MAX_GEN;// total generation
	private int numberVariables;// the number variables for each clause 3
	private int numberClause;// the number of clauses for individual 22
	private int gap;
	private int scale;
	private float Pm;
	private int bestT;// the number of generation that best value appears
	private int bestLength;// the best value of individual
	private int[] bestTour;// the best individual

	private int[] oldPopulation;
	private int[] newPopulation;// offspring
	private int[] newPopulation1;
	private int[] newPopulation2;
	private int[][] variable;
	private int fitnessParent;
	private int fitnessOffspring;

	private int[] newPopulationFitEvaluation;
	private int[][] newPopulation1FitEvaluation;
	private int[][] newPopulation2FitEvaluation;
	private int[][] newPopulation3FitEvaluation;
	private int[] fitness;

	public int t;// the current number of generation
	private MersenneTwister random;

	public OneEA(int l, int g, float p) {
		LL = l;
		MAX_GEN = g;
		Pm = p;
	}

	public OneEA(int s, int l, int g, float m) {
		scale = s;
		LL = l;
		MAX_GEN = g;
		bestLength = 0;
		bestTour = new int[LL];
		Pm = m;
	}

	public OneEA(int l, int g, int v, int nc, float m) {
		LL = l;
		MAX_GEN = g;
		numberVariables = v;
		numberClause = nc;
		Pm = m;
	}

	public OneEA(int s, int l, int g, int v, int nc, float m) {
		scale = s;
		LL = l;
		MAX_GEN = g;
		numberVariables = v;
		numberClause = nc;
		bestLength = 0;
		bestTour = new int[LL];
		Pm = m;
	}

	public void init() {
		t = 0;
		newPopulation = new int[LL];
		newPopulation1 = new int[LL];
		newPopulation2 = new int[LL];
		oldPopulation = new int[LL];
		variable = new int[LL][numberVariables];
		random = new MersenneTwister(new java.util.Date());
	}

	public void initFitnessEvaluation() {
		t = 0;
		oldPopulation = new int[LL];
		newPopulationFitEvaluation = new int[LL];
		newPopulation1FitEvaluation = new int[scale][LL];
		newPopulation2FitEvaluation = new int[scale][LL];
		newPopulation3FitEvaluation = new int[scale][LL];
		fitness = new int[scale];
		random = new MersenneTwister(new java.util.Date());
	}

	public void calcuOldPopulation(FitnessFunctions fitnessFunctions){
		fitnessParent = fitnessFunctions.evaluate(oldPopulation,LL);
//		System.out.println("lambdaEA: "+fitnessParent);
	}
	
	public void calcuFitnessValueOfNewPopulatiomnBeforeMutation(FitnessFunctions fitnessFunctions) {
		fitnessParent = fitnessFunctions.evaluate(newPopulation,LL);
//		System.out.println("oneEA "+fitnessParent);
		newPopulation1 = newPopulation.clone();
	}

	public void calcuFitnessOfNewPopuBeforeMutationFitnessEvaluation(FitnessFunctions fitnessFunctions) {
		fitnessParent = fitnessFunctions.evaluate(newPopulationFitEvaluation,LL);
	}


	public void calcuFitnessValueOfPopulationAfterMutation(FitnessFunctions fitnessFunctions) {
		fitnessOffspring = fitnessFunctions.evaluate(newPopulation,LL);
		newPopulation2 = newPopulation.clone();
	}

	// calculate the fitness value of children population
	public void calculateKnapsackFitnessEvaluation(FitnessFunctions fitnessFunctions) {
		for (int k = 0; k < scale; k++) {
			fitness[k] = fitnessFunctions.evaluate(newPopulation2FitEvaluation[k],LL);
		}
	}

	// compare fitness with parent
	public void judgeValue() {
		if (fitnessOffspring >= fitnessParent) {
			newPopulation = newPopulation2;
			fitnessParent = fitnessOffspring;
		} 
	}

	public int[] getNewPopulation1() {
		return newPopulation1;
	}

	public void setNewPopulation1(int[] newPopulation1) {
		this.newPopulation1 = newPopulation1;
	}

	public int[] getNewPopulation2() {
		return newPopulation2;
	}

	public void setNewPopulation2(int[] newPopulation2) {
		this.newPopulation2 = newPopulation2;
	}

	public void CompareValueWithPatentFitnessEvaluation() {
		if (bestLength >= fitnessParent) {
			newPopulationFitEvaluation = bestTour;
			fitnessParent = bestLength;
		}
	}

	public int[] getBestTour() {
		return bestTour;
	}

	public void setBestTour(int[] bestTour) {
		this.bestTour = bestTour;
	}

	public int getFitnessOffspring() {
		return fitnessOffspring;
	}

	public void setFitnessOffspring(int fitnessOffspring) {
		this.fitnessOffspring = fitnessOffspring;
	}

	public void EAOneMutation() {
//		int ran1, ran2, temp;
//		int count;
//		count = random.nextInt() % LL;
//		if (count < 0) {
//			count = count * (-1);
//		}
		for (int i = 0; i < LL; i++) {
			if (random.nextFloat() <= Pm) {// mutation probability
//				ran1 = random.nextInt() % LL;
//				if (ran1 < 0) {
//					ran1 = ran1 * (-1);
//				}
//				ran2 = random.nextInt() % LL;
//				if (ran2 < 0) {
//					ran2 = ran2 * (-1);
//				}
//				while (ran1 == ran2) {
//					ran2 = random.nextInt() % LL;
//					if (ran2 < 0) {
//						ran2 = ran2 * (-1);
//					}
//				}
//				temp = newPopulation[ran1];
//				newPopulation[ran1] = newPopulation[ran2];
//				newPopulation[ran2] = temp;
				if(newPopulation[i]==0){
					newPopulation[i] = newPopulation[i] +1;
				}else {
					newPopulation[i] = newPopulation[i] -1;
				}
			}
		}
	}
	//one point mutation
	public void mutationOperator() {
		for (int k = 0; k < scale; k++) {
			// mutation from parent
			newPopulation1FitEvaluation[k] = newPopulationFitEvaluation.clone();
			for (int j = 0; j < LL; j++) {
				if (random.nextFloat() >= Pm) {
					newPopulation2FitEvaluation[k][j] = newPopulation1FitEvaluation[k][j];
				} else {// call mutaion method
					if(newPopulation1FitEvaluation[k][j] == 1){
						newPopulation1FitEvaluation[k][j] = newPopulation1FitEvaluation[k][j] -1;
					}else {
						newPopulation1FitEvaluation[k][j] = newPopulation1FitEvaluation[k][j] +1;
					}				
					newPopulation2FitEvaluation[k][j] = newPopulation1FitEvaluation[k][j];
				}
			}
		}
	}

	public int[][] getNewPopulation2FitEvaluation() {
		return newPopulation2FitEvaluation;
	}

	public void setNewPopulation2FitEvaluation(int[][] newPopulation2FitEvaluation) {
		this.newPopulation2FitEvaluation = newPopulation2FitEvaluation;
	}

	public int[][] getNewPopulation1FitEvaluation() {
		return newPopulation1FitEvaluation;
	}

	public void setNewPopulation1FitEvaluation(int[][] newPopulation1FitEvaluation) {
		this.newPopulation1FitEvaluation = newPopulation1FitEvaluation;
	}

	// initialize one size parent
	public void initGroup() {
		int k;
		for (k = 0; k < LL; k++) {
			if(random.nextFloat()<=0.5){
				oldPopulation[k] = 0;
			}else{
				oldPopulation[k] = 1;
			}
		}
	}

	// select best one
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
				bestTour[i] = newPopulation2FitEvaluation[maxid][i];
			}
		}
	}

	public int getBestLength() {
		return bestLength;
	}

	public void setBestLength(int bestLength) {
		this.bestLength = bestLength;
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

	public int[] getNewPopulation() {
		return newPopulation;
	}

	public void setNewPopulation(int[] newPopulation) {
		this.newPopulation = newPopulation;
	}

	public int getMAX_GEN() {
		return MAX_GEN;
	}

	public void setMAX_GEN(int mAX_GEN) {
		MAX_GEN = mAX_GEN;
	}

	public int[] getNewPopulationFitEvaluation() {
		return newPopulationFitEvaluation;
	}

	public void setNewPopulationFitEvaluation(int[] newPopulationFitEvaluation) {
		this.newPopulationFitEvaluation = newPopulationFitEvaluation;
	}

	public int[] getFitness() {
		return fitness;
	}

	public void setFitness(int[] fitness) {
		this.fitness = fitness;
	}
}
