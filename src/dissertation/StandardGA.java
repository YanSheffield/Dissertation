package dissertation;

import java.util.Arrays;
//import java.util.Random;
import cern.jet.random.engine.MersenneTwister;

public class StandardGA{

	private int Length;// length of chromosome
	private int quantity;// scale of population

	private int maxGeneration;// the number of generation


	private int bestOne;// the number of generation that best value appears
	private int bestChromosome;// the best value of individual
	private int[] bestIndividualArray;// the best individual
	private int[] bestTourForFair;

	private int[][] oldPopulation;// parent population
	private int[][] newPopulation;// children population

	

	private int[] fitness;// the fitness array
	
	private int numberVariables;// the number variables for each clause is 3
	private int numberClause;// the number of clauses for individual is 22
	private int[][] variable;

	public float[] proportion;// the fitness proportion of each individual
	private float mutationProbabilty;// he probability of mutation
	public int t;// the current number of generation
	private MersenneTwister random;
	
	KnapsackProblem knapsackProblem = new KnapsackProblem();

	public StandardGA(int s, int l, int g, float m) {
		quantity = s;
		Length = l;
		maxGeneration = g;
		mutationProbabilty = m;
	}

	public StandardGA(int s, int l, int g, float m, int v, int nc) {
		quantity = s;
		Length = l;
		maxGeneration = g;
		mutationProbabilty = m;
		numberVariables = v;// 3 variable
		numberClause = nc;// 100 clause
	}

	public void init() {
		bestChromosome = 0;
		bestIndividualArray = new int[Length];
		bestTourForFair = new int[Length];
		bestOne = 0;
		t = 0;
		newPopulation = new int[quantity][Length];
		oldPopulation = new int[quantity][Length];
		variable = new int[Length][numberVariables];
		fitness = new int[quantity];
		proportion = new float[quantity];
		random = new MersenneTwister(new java.util.Date());
	}

	// one point mutation
	public void OnCVariation(int k) {
		float r;
		for (int i = 0; i < Length; i++) {
			r = random.nextFloat();
			if (r <= mutationProbabilty) {//mutation probability				
				if(newPopulation[k][i]==0){
					newPopulation[k][i] = newPopulation[k][i] +1;
				}else {
					newPopulation[k][i] = newPopulation[k][i] -1;
				}
			}
		}
	}

	public void OrderCross(int k1, int k2) {
		int i, j, flag;
		int ran1, ran2, temp = 0;
		ran1 = Math.abs(random.nextInt() % Length);
		ran2 = Math.abs(random.nextInt() % Length);
		while (ran1 == ran2) {
			ran2 =Math.abs(random.nextInt() % Length);
		}
		if (ran1 > ran2) {
			temp = ran1;
			ran1 = ran2;
			ran2 = temp;
		}
		flag = ran2 - ran1 + 1;
		for (i = 0, j = ran1; i < flag; i++, j++) {
			temp = newPopulation[k1][j];
			newPopulation[k1][j] = newPopulation[k2][j];
			newPopulation[k2][j] = temp;
		}
	}

	// Roulette wheel selection
	public void select() {
		int k, i, selectId;
		float ran1;
		for (k = 0; k < quantity; k++) {
			ran1 = random.nextFloat();
			for (i = 0; i < quantity; i++) {
				if (ran1 <= proportion[i]) {
					break;
				}
			}
			selectId = k;
			copytoNext(k, selectId);
		}
	}
	
	// copy the chromosome of parent to children
	public void copytoNext(int k, int kk) {
		int i;
		for (i = 0; i < Length; i++) {
			newPopulation[k][i] = oldPopulation[kk][i];
		}
	}

	// select the best individual and copy to next generation.ELITISM
	public int[] selectBestForFair() {
		int k, i, maxid;
		int maxevaluation;
		maxid = 0;
		maxevaluation = fitness[0];
		for (k = 1; k < quantity; k++) {
			if (maxevaluation < fitness[k]) {
				maxevaluation = fitness[k];
				maxid = k;
			}
		}
		if (bestChromosome < maxevaluation) {
			bestChromosome = maxevaluation;
			bestOne = t;
			for (i = 0; i < Length; i++) {
				bestTourForFair[i] = oldPopulation[maxid][i];
			}
		}
		return bestTourForFair;	
	}
	
	public void selectBestGh() {
		int k, i, maxid;
		int maxevaluation;
		maxid = 0;
		maxevaluation = fitness[0];
		for (k = 1; k < quantity; k++) {
			if (maxevaluation < fitness[k]) {
				maxevaluation = fitness[k];
				maxid = k;
			}
		}
		if (bestChromosome < maxevaluation) {
			bestChromosome = maxevaluation;
			bestOne = t;
			for (i = 0; i < Length; i++) {
				bestIndividualArray[i] = oldPopulation[maxid][i];
			}

		}
		copytoNext(0, maxid);	
	}
	
	public void fitness(FitnessFunctions fitnessFunctions){
		for (int k = 0; k < quantity; k++) {
			fitness[k] = fitnessFunctions.evaluate(oldPopulation[k],Length);
		}
	}
	
	public void copyNewPopuToOldPopu() {
		for (int k = 0; k < quantity; k++) {
			for (int i = 0; i < Length; i++) {
				oldPopulation[k][i] = newPopulation[k][i];
			}
		}
	}

	// Roulette wheel selection
	public void countRate() {
		int k;
		float sumFitness = 0;// the total number of fitness value
		int[] tempf = new int[quantity];
		for (k = 0; k < quantity; k++) {
			tempf[k] = fitness[k];
			sumFitness = sumFitness + tempf[k];
		}
		proportion[0] = (float) (tempf[0] / sumFitness);
		for (k = 1; k < quantity; k++) {
			proportion[k] = (float) (tempf[k] / sumFitness+proportion[k-1]);
		}
	}

	// Initialize population, the quantity is twenty and the length of chromosome
	// is fifty
	public void initGroup(){
		int k,i;
		for(k = 0;k<quantity;k++){
			for(i = 0;i<Length;i++){
				if(random.nextFloat()<=0.5){
					oldPopulation[k][i] = 1;
				}else{
					oldPopulation[k][i] = 0;
				}
			}
		}
	}
	
	public int getquantity() {
		return quantity;
	}

	public void setquantity(int quantity) {
		this.quantity = quantity;
	}

	public int getbestChromosome() {
		return bestChromosome;
	}

	public void setbestChromosome(int bestChromosome) {
		this.bestChromosome = bestChromosome;
	}

	public int[][] getOldPopulation() {
		return oldPopulation;
	}

	public void setOldPopulation(int[][] oldPopulation) {
		this.oldPopulation = oldPopulation;
	}
	public int getMaxGeneration() {
		return maxGeneration;
	}

	public void setMaxGeneration(int maxGeneration) {
		this.maxGeneration = maxGeneration;
	}
	public int[] getFitness() {
		return fitness;
	}

	public void setFitness(int[] fitness) {
		this.fitness = fitness;
	}
	public int[][] getNewPopulation() {
		return newPopulation;
	}

	public void setNewPopulation(int[][] newPopulation) {
		this.newPopulation = newPopulation;
	}

}
