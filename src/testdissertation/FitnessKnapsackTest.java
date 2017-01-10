package testdissertation;

import static org.junit.Assert.*;

import org.junit.Test;

import dissertation.KnapsackProblem;

public class FitnessKnapsackTest {
	
	private KnapsackProblem knap = new KnapsackProblem();
	
	private int fitness;
	
	private String range;
	@Test
	public void testfitness() {
		int[] oldPopulation = {1,0};
		fitness = knap.evaluate(oldPopulation,2);
		assertEquals(220, fitness);
		
		int[] oldPopu = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
		fitness = knap.evaluate(oldPopu, 38);
		assertEquals(0, fitness);
	}

	@Test
	public void testRang(){
		range = knap.range(10.5f);
		assertEquals("11",range);
	}
	
}
