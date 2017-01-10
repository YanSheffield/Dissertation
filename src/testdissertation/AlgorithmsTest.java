package testdissertation;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import dissertation.Algorithms;
import dissertation.KnapsackProblem;
import dissertation.LamdaGA;

public class AlgorithmsTest {

	private Algorithms algorithms = new Algorithms();
//	@Test	
//	public void testStandardFitnessEvaluation(){		
//		float[] standardFitnessEvaluation = null;
//		standardFitnessEvaluation = algorithms.callStandardGAFitnessEvaluation(50, new KnapsackProblem());
//		assertNotNull(standardFitnessEvaluation );
//	}
	
//	
	@Test
	public void testStandard(){
		float[] standard = null;
		standard = algorithms.callStandarGA(50, new KnapsackProblem(),20,1);
		assertNotNull(standard);
	}
//	
	@Test
	public void testOneEA(){
		float[] oneEA = null;
		oneEA = algorithms.callOneEA(50, new KnapsackProblem(),1);
		assertNotNull(oneEA);
	}

	@Test
	public void testLambda(){
		float[] lambda = null;
		lambda = algorithms.callLamdaGA(50, new KnapsackProblem(),20,1);
		assertNotNull(lambda);
	}
//	
	@Test
	public void testOneEAFitnessEvaluation(){
		float[] oneFitnessEvaluation = null;
		oneFitnessEvaluation = algorithms.callOneEAFitnessEvaluation(50, new KnapsackProblem(),20,1);
		assertNotNull(oneFitnessEvaluation);
	}
//	
	@Test
	public void testCalAverage(){
		float[][] copyTotal = {{3.0f,3.0f,3.0f},
							   {4.0f,4.0f,4.0f},
							   {5.0f,5.0f,5.0f}};
		float[] copyAverage = new float[3];
		LamdaGA lamdaGA = new LamdaGA(1,3,3,3,0.1f,0.1f);		
		Algorithms.setNUMGENERATION(3);
		Algorithms.setRUNTIMES(3);
		float[] expectedAver = {4.0f,4.0f,4.0f};
		lamdaGA.setMAX_GEN(3);
		float[] actual = algorithms.calcuAverageFitness(copyTotal, copyAverage);
		assertTrue(Arrays.toString(actual).equals(Arrays.toString(expectedAver)));
	}
	
	@Test
	public void TestKnapGetAndSet(){
		algorithms.setNUMGENERATION(501);
		assertEquals(501, algorithms.getNUMGENERATION());
		
		float[] copyAverafeBestStan = {2.1f,3.1f};
		algorithms.setAverageBestStandardGAKnapsack(copyAverafeBestStan);
		assertTrue(Arrays.toString(copyAverafeBestStan).equals(Arrays.toString(algorithms.getAverageBestStandardGAKnapsack())));
//		
		algorithms.setAverageKapsackArray(copyAverafeBestStan);
		assertTrue(Arrays.toString(copyAverafeBestStan).equals(Arrays.toString(algorithms.getAverageKapsackArray())));
//		
		algorithms.setAverageOneEAKnapsackArray(copyAverafeBestStan);
		assertTrue(Arrays.toString(copyAverafeBestStan).equals(Arrays.toString(algorithms.getAverageOneEAKnapsackArray())));		
		
		algorithms.setRUNTIMES(50);
		assertEquals(50, algorithms.getRUNTIMES());
	}
	
//	@Test
//	public void callEA(){
//		EATest eaTest = new EATest();
//		eaTest.callEATests();
//	}

}
