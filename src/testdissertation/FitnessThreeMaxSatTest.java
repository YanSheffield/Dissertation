package testdissertation;

import static org.junit.Assert.*;

import org.junit.Test;

import dissertation.ThreeMaxSatProblem;

public class FitnessThreeMaxSatTest {
	
	private int fitness;
	private String range;
	ThreeMaxSatProblem threeMaxSatProblem = new ThreeMaxSatProblem();
	@Test
	public void testFitness() {
		int[] oldPopulation = new int[]{1,0,0,0,0,1,1,0,1,1,0};
		fitness = threeMaxSatProblem.evaluate(oldPopulation, 10);
		System.out.println(fitness);
		assertEquals(8, fitness);
	}
	
	@Test
	public void textRang(){
		range = threeMaxSatProblem.range(10.334f);
		assertEquals("10.33", range);
	}
	
}
