package testdissertation;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import dissertation.KnapsackProblem;
import dissertation.StandardGA;


public class StandardGATest {
	
	StandardGA standardGA = new StandardGA(1, 1, 1, 0.1f);
	
	@Test
	public void testInitGroup(){
		StandardGA standardGA = new StandardGA(1, 3, 4, 0.1f, 3, 50);
		standardGA.init();
		standardGA.initGroup();
		assertNotNull(Arrays.toString(standardGA.getOldPopulation()));
	}
	
	@Test
	public void testCountRate(){
		StandardGA standardGA = new StandardGA(2, 2, 2, 0.1f);
		standardGA.init();
		int[] fitness = new int[]{30,30};
		standardGA.setFitness(fitness);
		standardGA.countRate();
		float[] pi = {0.5f,1.0f};
		assertArrayEquals(pi, standardGA.proportion,0);
	}
	
	@Test
	public void testSeletBestGh(){
		StandardGA standardGA = new StandardGA(4, 4, 4, 0.1f);
		standardGA.init();
		int[] fitness = new int[]{30,40,50,60};
		standardGA.setFitness(fitness);
		standardGA.selectBestGh();
		standardGA.select();
		assertEquals(60, standardGA.getbestChromosome());
	}
	
	@Test
	public void testSelectBestForFair(){
		StandardGA standardGA = new StandardGA(4, 4, 4, 0.1f);
		standardGA.init();
		int[] recieveReturnValue = new int[4];
		int[] fitness = new int[]{60,0,40,30};
		int[][] copyOldPopulation = {{1,1,1,1},{0,0,0,0},{1,0,0,0},{0,1,0,1}};
		standardGA.setOldPopulation(copyOldPopulation);
		standardGA.setFitness(fitness);
		recieveReturnValue = standardGA.selectBestForFair();
		assertEquals(60, standardGA.getbestChromosome());
		assertTrue(Arrays.deepToString(copyOldPopulation).substring(1, 13).equals(Arrays.toString(recieveReturnValue)));
	}
////	
	@Test
	public void testOXCross(){
		StandardGA standardGA = new StandardGA(4, 4, 4, 0.1f);
		standardGA.init();
		int[][] copyNewPopulation =  new int[][]{{1,1,1,1},
												{1,1,1,1},
												{0,0,0,0},
												{0,0,0,0}};
		standardGA.setNewPopulation(copyNewPopulation);
		int newPopulationClone[][] = new int[][]{{1,1,1,1},{1,1,1,1},{0,0,0,0},{0,0,0,0}};
		standardGA.OrderCross(1, 2);
		assertFalse(Arrays.toString(newPopulationClone[1]).equals(Arrays.deepToString(standardGA.getNewPopulation()).substring(15, 27)));
//		assertFalse(Arrays.deepToString(copyNewPopulation));
	}
////	
	
	@Test
	public void testOnCVariation(){
		StandardGA standardGA = new StandardGA(4, 4, 4, 1.0f);
		standardGA.init();
		int[][] copyNewPopulation = {{1,1,1,1},{1,1,1,1},{0,1,0,1},{0,0,0,0}};
		standardGA.setNewPopulation(copyNewPopulation);
		int newPopulationClone[][] = new int[][]{{1,1,1,1},{1,1,1,1},{0,1,0,1},{0,0,0,0}};
		standardGA.OnCVariation(2);
		assertFalse(Arrays.toString(newPopulationClone[1]).equals(Arrays.deepToString(standardGA.getNewPopulation()).substring(5, 27)));
	}

//	
	@Test
	public void testCopyNewToOld(){
		StandardGA standardGA = new StandardGA(4, 4, 4, 0.1f);
		standardGA.init();
		int[][] copyNewPopulation = {{1,1,1,1},{1,1,1,1},{0,0,0,0},{0,0,0,0}};
		standardGA.setNewPopulation(copyNewPopulation);
		standardGA.copyNewPopuToOldPopu();
		assertTrue(Arrays.deepToString(copyNewPopulation).equals(Arrays.deepToString(standardGA.getOldPopulation())));
	}
//	
	@Test
	public void testGetAndSet(){
		StandardGA standardGA = new StandardGA(1, 2, 4, 0.1f);
		standardGA.init();
		standardGA.setquantity(3);
		assertEquals(3,standardGA.getquantity());
		standardGA.setMaxGeneration(5);
		assertEquals(5, standardGA.getMaxGeneration());
		standardGA.setbestChromosome(12);
		assertEquals(12, standardGA.getbestChromosome());
		
	}
	
	@Test
	public void testfitness(){
		StandardGA standardGA = new StandardGA(1, 2, 2, 0.1f);
		standardGA.init();
		int[][] copyOldPopu = {{1,0}};
		int[] copyFitnessArray = {220};
		standardGA.setOldPopulation(copyOldPopu);
		standardGA.fitness(new KnapsackProblem());
		assertTrue(Arrays.toString(standardGA.getFitness()).equals(Arrays.toString(copyFitnessArray)));
	}
//		
	
}
