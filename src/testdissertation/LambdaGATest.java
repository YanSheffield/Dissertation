package testdissertation;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.util.Arrays;

import org.junit.Test;

import dissertation.KnapsackProblem;
//import dissertation.Knapsack;
import dissertation.LamdaGA;
//import dissertation.Partition;
//import dissertation.ThreeMaxSatGraphic;
//import dissertation.Three_MAX_SAT;
import dissertation.StandardGA;

public class LambdaGATest {


//	
	@Test
	public void testMutationOprator(){
		LamdaGA lamdaGA = new LamdaGA(1, 1, 5, 1, 1.0f, 1.0f);
		lamdaGA.init();
		int[] copyOldPopu = {1,0,0,1,1};
		int[][] twoOld = {{1,0,0,1,1}};
		assertTrue(Arrays.toString(copyOldPopu).equals(Arrays.deepToString(twoOld).substring(1, 16)));
		lamdaGA.setOldPopulation(copyOldPopu);
		lamdaGA.mutationOperator();
		assertFalse(Arrays.toString(lamdaGA.getOldPopulation()).equals(Arrays.deepToString(lamdaGA.getNewPopulation()).substring(1, 16)));
	}

	@Test
	public void testCalcuFitnessChirldernKnap(){
		LamdaGA lamdaGA = new LamdaGA(1, 3, 2, 1, 0.1f, 0.1f);
		lamdaGA.init();
		int[][] copyNewPopulation = {{1,1},{0,1},{1,1}};
		lamdaGA.setNewPopulation1(copyNewPopulation);
		lamdaGA.calculateChirldrenFitnessKnapsack(new KnapsackProblem());
		assertEquals("428",Arrays.toString(lamdaGA.getFitness()).substring(1, 4));
	}
	
//	
	@Test
	public void testSelectBestGh(){
		LamdaGA lamdaGA = new LamdaGA(1, 4, 1, 1, 0.1f, 0.1f);
		lamdaGA.init();
		int[] copyFitness = {30,40,50,60};
		lamdaGA.setFitness(copyFitness);
		lamdaGA.selectBestGh();
		assertEquals(60, lamdaGA.getBestLength());
	}
//	
	@Test
	public void testUniformCrossOver(){
		LamdaGA lamdaGA = new LamdaGA(1, 4, 4, 1, 1.0f, 0.1f);
		lamdaGA.init();
		int[] sameBestTour = {1,0,1,1};
		lamdaGA.setBestTour(sameBestTour);
		lamdaGA.callUniformCrossover();
		assertTrue(Arrays.toString(lamdaGA.getBestTour()).equals(Arrays.deepToString(lamdaGA.getNewPopulation1()).substring(1, 13)));
		
		LamdaGA lamdaGA1 = new LamdaGA(1, 4, 4, 1, 0.0f, 0.1f);
		lamdaGA1.init();
		int[] copyOld = {1,0,1,1};
		lamdaGA.setOldPopulation(copyOld);
		lamdaGA1.callUniformCrossover();
		assertTrue(Arrays.toString(lamdaGA1.getOldPopulation()).equals(Arrays.deepToString(lamdaGA1.getNewPopulation1()).substring(1, 13)));
	}


	@Test
	public void testCalcuOldPopulation(){
		LamdaGA lamdaGA = new LamdaGA(1, 1, 2, 3, 0.1f, 0.1f, 3, 50);
		lamdaGA.init();
		int[] copyOldPopulation = {1,0};
		lamdaGA.setOldPopulation(copyOldPopulation);
		lamdaGA.calcuOldPopulation(new KnapsackProblem());
		assertEquals(220, lamdaGA.getFitnessParent());
	}
	
	@Test
	public void testJudgeValueParent(){
		LamdaGA lamdaGA1 = new LamdaGA(1, 1, 4, 1, 0.0f, 0.1f);
		lamdaGA1.init();
		lamdaGA1.setBestLength(110);
		lamdaGA1.setFitnessParent(100);
		int[] copyBestTour = {1,1,1,1};
		lamdaGA1.setBestTour(copyBestTour);
		lamdaGA1.judgeValueWithParent();
		assertEquals(110, lamdaGA1.getFitnessParent());
		assertTrue(Arrays.toString(copyBestTour).equals(Arrays.toString(lamdaGA1.getOldPopulation())));
		
		LamdaGA lamdaGA = new LamdaGA(1, 1, 4, 1, 0.0f, 0.1f);
		lamdaGA.init();
		lamdaGA.setBestLength(100);
		lamdaGA.setFitnessParent(110);
		int[] setBestTour = {1,1,1,0};
		lamdaGA.setBestTour(setBestTour);
		lamdaGA.judgeValueWithParent();
		assertFalse(Arrays.toString(setBestTour).equals(Arrays.toString(lamdaGA.getOldPopulation())));
	}
	
	@Test
	public void testSetAndGet(){
		LamdaGA lamdaGA = new LamdaGA(1, 1, 2, 1, 0.1f, 0.1f);
		lamdaGA.init();
		lamdaGA.setFitnessParent(3);
		assertEquals(3, lamdaGA.getFitnessParent());
		lamdaGA.setMAX_GEN(9);
		assertEquals(9, lamdaGA.getMAX_GEN());
		lamdaGA.setLL(10);
		assertEquals(10, lamdaGA.getLL());
		lamdaGA.setScale(22);
		assertEquals(22, lamdaGA.getScale());
		lamdaGA.setBestLength(30);
		assertEquals(30, lamdaGA.getBestLength());
		int[] copyOldPopulation = {1,1};
		lamdaGA.setOldPopulation(copyOldPopulation);
		assertTrue(Arrays.toString(copyOldPopulation).equals(Arrays.toString(lamdaGA.getOldPopulation())));
		int[][] copyNewPopulation = {{1,1}};
		lamdaGA.setNewPopulation(copyNewPopulation);
		assertTrue(Arrays.deepToString(copyNewPopulation).equals(Arrays.deepToString(lamdaGA.getNewPopulation())));
	}
}
