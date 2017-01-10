package testdissertation;

import static org.junit.Assert.*;

import org.junit.Test;

import dissertation.PartitionProblem;

public class FitnessPartitionTest {

	private PartitionProblem partitionProblem = new PartitionProblem();
	private int fitness;
	private String range;
	
	@Test
	public void testfitness() {
		int[] oldPopulation = {1,0};
		fitness = partitionProblem.evaluate(oldPopulation, 2);
		assertEquals(2988, fitness);
	}
	
	@Test
	public void testRang(){
		range = partitionProblem.range(10.334f);
		assertEquals("10.33",range);
	}
}
