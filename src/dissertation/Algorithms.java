package dissertation;

import java.util.Arrays;

public class Algorithms {

	private LamdaGA lamda = new LamdaGA(0, 20, 50, 501, 0.1f, 0.2f);

	private float[][] standardBestSelection;
	private float[][] lamdaArray;
	private float[][] oneEAArray;

	private float[][] oneEAFitnessEvaluation;

	private float[] averageBestStandardGA;
	private float[] averageLambdaArray;
	private float[] averageOneEAArray;

	private float[] averageOneEAFitnessEvaluation;

	private static int RUNTIMES = 100;
	private static int NUMGENERATION;
	private static int LengthOfBit = 50;

	private static int offspringScaleGeneratonAgainstFitness;
	private static int offspringScaleFitnessEvaluationAgainstFitness;

	private int[] GeneratonAgainstFitnessParent = new int[LengthOfBit];

	// generation against fitness,standard GA
	public float[] callStandarGA(int generation, FitnessFunctions fitnessFunctions,int offSpringScale,int sampleData) {
		NUMGENERATION = generation + 1;
		offspringScaleGeneratonAgainstFitness = offSpringScale;
		standardBestSelection = new float[RUNTIMES][NUMGENERATION];
		averageBestStandardGA = new float[NUMGENERATION];
		StandardGA standard = new StandardGA(offspringScaleGeneratonAgainstFitness, LengthOfBit, NUMGENERATION, 0.02f);
		// Initialization population
		for (int i = 0; i < RUNTIMES; i++) {
//			System.out.println("not "+i+": "+Arrays.deepToString(standard.getOldPopulation()));
//			standard.setOldPopulation(null);
			standard.init();
			standard.initGroup();
			// calculate fitness value of Initialization population
			standard.fitness(fitnessFunctions);
			// calculate the fitness probability of population
			standard.countRate();	
			if (i == 0) {
				GeneratonAgainstFitnessParent = standard.selectBestForFair();
			}		
			// the whole process
			for (int j = 0; j < NUMGENERATION; j++) {
				// apply elitism and Roulette wheel
//				System.out.println("after "+i+": "+Arrays.deepToString(standard.getOldPopulation()));
				standard.selectBestGh();
				standard.select();
				// float r;
				// crossover and mutation operation
				for (int k = 0; k < standard.getquantity(); k = k + 2) {
					// call crossover method
					standard.OrderCross(k, k + 1);
					// call mutation operator method
					standard.OnCVariation(k);
					standard.OnCVariation(k + 1);
				}
				
				// copy the new population to old population and prepare for the
				// next generation
				standard.copyNewPopuToOldPopu();
				// calculate fitness value of population
				standard.fitness(fitnessFunctions);
				// calculate the probability of each fitness value
				standardBestSelection[i][j] = standard.getbestChromosome();
				if(j == sampleData&&sampleData!=0){
					System.out.println("Standard GA: "+standardBestSelection[i][j]+", ");
				}
				standard.countRate();
			}
			
		}
//		System.out.println();
//		System.out.println("+"+Arrays.deepToString(standardKnapsackBestSelection));
//		System.out.println();
		calcuAverageFitness(standardBestSelection, averageBestStandardGA);
		// calcuAverageFitness(standardKnapsackFirstAverageArray,
		// averageStandardGAKapsackArray);
		// System.out.println("average values of Standard GA: " +
		// Arrays.toString(averageStandardGAKapsackArray));
		// System.out.println("Best values of Standard GA" +
		// Arrays.toString(averageBestStandardGAKnapsack));
		return averageBestStandardGA;
	}

	// calculate average fitness after running 500 generation with 50 times
	public float[] calcuAverageFitness(float[][] totalData, float[] average) {
		for (lamda.t = 0; lamda.t < NUMGENERATION; lamda.t++) {
			float sum = 0f;
			for (int i = 0; i < RUNTIMES; i++) {
				sum = totalData[i][lamda.t] + sum;
			}
			average[lamda.t] = sum / RUNTIMES;
		}
		return average;
	}

	// generation against fitness,(1+1)EA
	public float[] callOneEA(int generation, FitnessFunctions fitnessFunctions,int sampleData) {
		NUMGENERATION = generation + 1;
		oneEAArray = new float[RUNTIMES][NUMGENERATION];
		averageOneEAArray = new float[NUMGENERATION];
		OneEA oneEA = new OneEA(LengthOfBit, NUMGENERATION, 0.02f);
		
		for (int i = 0; i < RUNTIMES; i++) {
			oneEA.init();
			do {
				oneEA.initGroup();
				oneEA.setNewPopulation(oneEA.getOldPopulation().clone());
				oneEA.calcuFitnessValueOfNewPopulatiomnBeforeMutation(fitnessFunctions);
			} while (oneEA.getFitnessParent() == 0);
			for (oneEA.t = 0; oneEA.t < oneEA.getMAX_GEN(); oneEA.t++) {
				// mutation operation
				oneEA.EAOneMutation();
				oneEA.calcuFitnessValueOfPopulationAfterMutation(fitnessFunctions);// offspring
				oneEA.judgeValue();
				oneEAArray[i][oneEA.t] = oneEA.getFitnessParent();
				if(oneEA.t == sampleData&&sampleData!=0){
					System.out.println("One EA:"+oneEAArray[i][oneEA.t]);
				}
			}
		}
		calcuAverageFitness(oneEAArray, averageOneEAArray);
		// System.out.println("(1+1)EA" +
		// Arrays.toString(averageOneEAKnapsackArray));
		return averageOneEAArray;
	}

	// generation against fitness;fitness evaluations against fitness,lambda GA
	public float[] callLamdaGA(int generation, FitnessFunctions fitnessFunctions,int offSpringScale,int sampleData) {
		NUMGENERATION = generation + 1;
		offspringScaleGeneratonAgainstFitness = offSpringScale;
		lamdaArray = new float[RUNTIMES][NUMGENERATION];
		averageLambdaArray = new float[NUMGENERATION];
		// Initialize the one size parent,and the fitness value does not equal
		// with zero
		LamdaGA lamda = new LamdaGA(0, offspringScaleGeneratonAgainstFitness, LengthOfBit, NUMGENERATION, 0.1f, 0.2f);// cross																														// //		
		for (int i = 0; i < RUNTIMES; i++) {
			// do {
//			 lamda.initGroup();
			lamda.init();
			lamda.setOldPopulation(GeneratonAgainstFitnessParent);
//			 lamda.setFitnessParent(lamda.evaluateKnasack(lamda.getOldPopulation(),50));
			lamda.calcuOldPopulation(fitnessFunctions);
			// } while (lamda.getFitnessParent() == 0);
			// System.out.println("old
			// "+Arrays.toString(lamda.getOldPopulation()));
			// System.out.println(lamda.getFitnessParent());
			for (lamda.t = 0; lamda.t < lamda.getMAX_GEN(); lamda.t++) {
				// mutation operator
				// System.out.println("parent "+lamda.getFitnessParent());
				lamda.mutationOperator();
				// calculate the fitness value of children population before
				// selecting best one
				lamda.calculateChirldrenFitnessKnapsack(fitnessFunctions);
				// select best one
				lamda.selectBestGh();
				// uniform crossover between the best child and the parent
				lamda.callUniformCrossover();
				// calculate the fitness value of children population before
				// selecting best one
				lamda.calculateChirldrenFitnessKnapsack(fitnessFunctions);
				// select best one
				lamda.selectBestGh();
				// System.out.println("offspring "+lamda.getBestLength());
				lamda.judgeValueWithParent();
				// System.out.println("final "+lamda.getFitnessParent());
				lamdaArray[i][lamda.t] = lamda.getFitnessParent();
				if(lamda.t == sampleData&&sampleData!=0){
					System.out.println("lambda GA: "+lamdaArray[i][lamda.t]+", ");
				}
				
				// give the best value to next generation

			}
			// lamda.print();
		}
		calcuAverageFitness(lamdaArray, averageLambdaArray);
		// System.out.println("(1+λ) λ :" +
		// Arrays.toString(averageKapsackArray));
		return averageLambdaArray;
	}

	// fitness evaluations against fitness,EA
	public float[] callOneEAFitnessEvaluation(int generation, FitnessFunctions fitnessFunctions,int offSpringScale,int sampleData) {
		NUMGENERATION = generation + 1;
		offspringScaleFitnessEvaluationAgainstFitness = offSpringScale;
		oneEAFitnessEvaluation = new float[RUNTIMES][NUMGENERATION];
		averageOneEAFitnessEvaluation = new float[NUMGENERATION];
		
		for (int i = 0; i < RUNTIMES; i++) {
			OneEA oneEAfitnessevaluation = new OneEA(offspringScaleFitnessEvaluationAgainstFitness, 50, NUMGENERATION,
					0.02f);
			oneEAfitnessevaluation.initFitnessEvaluation();
			oneEAfitnessevaluation.setOldPopulation(GeneratonAgainstFitnessParent);
			oneEAfitnessevaluation.calcuOldPopulation(fitnessFunctions);
			// copy old population to new population
			oneEAfitnessevaluation.setNewPopulationFitEvaluation(oneEAfitnessevaluation.getOldPopulation().clone());
			for (oneEAfitnessevaluation.t = 0; oneEAfitnessevaluation.t < oneEAfitnessevaluation
					.getMAX_GEN(); oneEAfitnessevaluation.t++) {// produce 500
																// generations
				oneEAfitnessevaluation.mutationOperator();// mutation
				oneEAfitnessevaluation.calculateKnapsackFitnessEvaluation(fitnessFunctions);// 40
																							// fitness
																							// evaluations
				oneEAfitnessevaluation.selectBestGh();// select best one
				oneEAfitnessevaluation.CompareValueWithPatentFitnessEvaluation();// compare
																					// with
																					// parent
				oneEAFitnessEvaluation[i][oneEAfitnessevaluation.t] = oneEAfitnessevaluation.getFitnessParent();
				if(oneEAfitnessevaluation.t==sampleData&&sampleData!=0){
					System.out.println("Lambda EA: "+oneEAFitnessEvaluation[i][oneEAfitnessevaluation.t]+", ");
				}
			}
		}
		calcuAverageFitness(oneEAFitnessEvaluation, averageOneEAFitnessEvaluation);
		return averageOneEAFitnessEvaluation;
	}


	public float[] getAverageBestStandardGAKnapsack() {
		return averageBestStandardGA;
	}

	public void setAverageBestStandardGAKnapsack(float[] averageBestStandardGAKnapsack) {
		this.averageBestStandardGA = averageBestStandardGAKnapsack;
	}

	public float[] getAverageKapsackArray() {
		return averageLambdaArray;
	}

	public void setAverageKapsackArray(float[] averageKapsackArray) {
		this.averageLambdaArray = averageKapsackArray;
	}

	public float[] getAverageOneEAKnapsackArray() {
		return averageOneEAArray;
	}

	public void setAverageOneEAKnapsackArray(float[] averageOneEAKnapsackArray) {
		this.averageOneEAArray = averageOneEAKnapsackArray;
	}

	public static int getRUNTIMES() {
		return RUNTIMES;
	}

	public static void setRUNTIMES(int rUNTIMES) {
		RUNTIMES = rUNTIMES;
	}

	public static int getNUMGENERATION() {
		return NUMGENERATION;
	}

	public static void setNUMGENERATION(int nUMGENERATION) {
		NUMGENERATION = nUMGENERATION;
	}

}
