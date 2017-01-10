package testdissertation;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.util.Arrays;

import javax.ws.rs.ext.MessageBodyWriter;

import org.junit.Test;

import dissertation.KnapsackProblem;
import dissertation.OneEA;



public class EATest {

	@Test
	public void test() {
		OneEA oneEA = new OneEA(1, 1, 0.1f);
		oneEA.init();
		oneEA.initGroup();
		assertNotNull(Arrays.toString(oneEA.getOldPopulation()));
	}

	@Test
	public void testCalcuFitNewPopu(){
		OneEA oneEA = new OneEA(3, 1, 1.0f);
		oneEA.init();
		int[] copyNewPopu = {1,0,0};
		oneEA.setNewPopulation(copyNewPopu);
		oneEA.calcuFitnessValueOfNewPopulatiomnBeforeMutation(new KnapsackProblem());
		assertEquals(220, oneEA.getFitnessParent());
	}
//	
	@Test
	public void testEAOneMutation(){
		OneEA oneEA = new OneEA(10, 1, 1.0f);
		oneEA.init();
		int[] copyNewPopu = {1,0,1,0,1,0,1,1,0,0};
		oneEA.setNewPopulation(copyNewPopu.clone());
		oneEA.EAOneMutation();
		assertFalse(Arrays.toString(copyNewPopu).equals(Arrays.toString(oneEA.getNewPopulation())));
	}
	
	@Test
	public void testCalcuAfterMutation(){
		OneEA oneEA = new OneEA(3, 1, 1.0f);
		oneEA.init();
		int[] copyNewPopu = {1,1,0};
		oneEA.setNewPopulation(copyNewPopu);
		oneEA.calcuFitnessValueOfPopulationAfterMutation(new KnapsackProblem());
		assertEquals(428, oneEA.getFitnessOffspring());
	}
//	
	@Test
	public void testJudgeValue(){
		OneEA oneEA = new OneEA(3, 1, 1.0f);
		oneEA.init();
		oneEA.setFitnessOffspring(10);
		oneEA.setFitnessParent(8);
		int[] copyNewPopu2 = {1,1,1};
		oneEA.setNewPopulation2(copyNewPopu2);
		oneEA.judgeValue();
		assertTrue(Arrays.toString(oneEA.getNewPopulation2()).equals(Arrays.toString(oneEA.getNewPopulation())));
		
		OneEA oneEA1 = new OneEA(3, 1, 1.0f);
		oneEA1.init();
		oneEA1.setFitnessOffspring(8);
		oneEA1.setFitnessParent(10);
		int[] copyNewPopu1 = {0,0,0};
		oneEA1.setNewPopulation1(copyNewPopu1);
		oneEA1.judgeValue();
		assertTrue(Arrays.toString(oneEA1.getNewPopulation1()).equals(Arrays.toString(oneEA1.getNewPopulation())));
	}
	
	@Test
	public void testCalcuKnapFitnessEvaluation(){
		OneEA oneEA = new OneEA(1, 3, 1, 0.1f);
		oneEA.initFitnessEvaluation();
		int[][] copyNewPopu2FitEvalu = {{1,0,0}};
		oneEA.setNewPopulation2FitEvaluation(copyNewPopu2FitEvalu);
		oneEA.calculateKnapsackFitnessEvaluation(new KnapsackProblem());
		assertEquals("220", Arrays.toString(oneEA.getFitness()).substring(1, 4));
		assertTrue(Arrays.toString(oneEA.getFitness()).contains("220"));
	}

//	
	@Test
	public void testMutationOperation(){
		OneEA oneEA = new OneEA(1, 5, 1, 1.0f);
		oneEA.initFitnessEvaluation();	
		int[][] copyNewPopu1FitnessEva = {{1,0,0,1,1}};
		oneEA.setNewPopulation1FitEvaluation(copyNewPopu1FitnessEva.clone());
		oneEA.mutationOperator();
		int[][] afterMutaPopu1 = oneEA.getNewPopulation1FitEvaluation();
		int[][] afterMutaPopu2 = oneEA.getNewPopulation2FitEvaluation();
		assertFalse(Arrays.toString(copyNewPopu1FitnessEva[0]).equals(Arrays.toString(afterMutaPopu1[0])));
		assertTrue(Arrays.toString(afterMutaPopu2[0]).equals(Arrays.toString(afterMutaPopu1[0])));
		
		OneEA oneEA1 = new OneEA(1, 5, 1, 0.0f);
		oneEA1.initFitnessEvaluation();	
		int[][] copyNewPopu1FitnessEvaNoMutation = {{1,0,0,1,1}};
		oneEA1.setNewPopulation1FitEvaluation(copyNewPopu1FitnessEvaNoMutation);
		oneEA1.mutationOperator();
		int[][] getNewPopu2FitEva = oneEA1.getNewPopulation2FitEvaluation();
		assertTrue(Arrays.toString(copyNewPopu1FitnessEvaNoMutation[0]).equals(Arrays.toString(getNewPopu2FitEva[0])));
	}
//	
	@Test
	public void testSelectBest(){
		OneEA oneEA = new OneEA(4, 1, 1, 1, 1, 0.1f);
		oneEA.initFitnessEvaluation();
		int[] copyFitnessValue = {20,30,40,50};
		oneEA.setFitness(copyFitnessValue);
		oneEA.selectBestGh();
		assertEquals(50, oneEA.getBestLength());
	}
//	
	@Test
	public void testCalcuNewPopBeforeFitnessEvaluation(){
		OneEA oneEA = new OneEA(2, 1, 3, 1, 0.1f);
		oneEA.init();
		int[] copyNewPopuFitnessEvalu = {1,1};
		oneEA.setNewPopulationFitEvaluation(copyNewPopuFitnessEvalu);        
		oneEA.calcuFitnessOfNewPopuBeforeMutationFitnessEvaluation(new KnapsackProblem());
		assertEquals(428, oneEA.getFitnessParent());
	}


	@Test
	public void TestCompareWithParent(){
		OneEA oneEA = new OneEA(1, 5, 1, 1,1, 0.1f);
		oneEA.initFitnessEvaluation();
		oneEA.setBestLength(20);
		oneEA.setFitnessParent(10);
		int[] copyBestTour = {1,0,1,1,0};
		int[] copyNewpopu = {1,1,1,1};
		oneEA.setBestTour(copyBestTour);
		oneEA.setNewPopulationFitEvaluation(copyNewpopu);
		oneEA.CompareValueWithPatentFitnessEvaluation();
		assertTrue(Arrays.toString(oneEA.getBestTour()).equals(Arrays.toString(oneEA.getNewPopulationFitEvaluation())));
		
		oneEA.setBestLength(8);
		oneEA.setFitnessParent(10);
		oneEA.CompareValueWithPatentFitnessEvaluation();
		assertTrue(Arrays.toString(oneEA.getBestTour()).equals(Arrays.toString(oneEA.getNewPopulationFitEvaluation())));
	}
	
	@Test
	public void testCalcuOldPopulation(){
		OneEA oneEA = new OneEA(1, 5, 1, 1,1, 0.1f);
		oneEA.init();
		int[] copyOldPopulation  = {1,0,0,0,0};
		oneEA.setOldPopulation(copyOldPopulation);
		oneEA.calcuOldPopulation(new KnapsackProblem());
		assertEquals(220, oneEA.getFitnessParent());
	}
	
	@Test
	public void TestGetAndSet(){
		OneEA oneEA = new OneEA(1, 1, 0.1f);
		oneEA.setMAX_GEN(20);
		assertEquals(20, oneEA.getMAX_GEN());
		
		int[] copyOldPopulation = {1};
		oneEA.setOldPopulation(copyOldPopulation);
		assertTrue(Arrays.toString(copyOldPopulation).equals(Arrays.toString(oneEA.getOldPopulation())));
	}
	
	
}
