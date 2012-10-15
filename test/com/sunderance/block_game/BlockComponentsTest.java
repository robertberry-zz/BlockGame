package com.sunderance.block_game;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import com.sunderance.utils.Pair;
import com.sunderance.utils.Zip2Iterable;

import Jama.Matrix;

/**
 * Tests for BlockComponents class
 * 
 * @author Robert Berry
 * @version 0.1
 */
public class BlockComponentsTest {
	/**
	 * Test constructing from an array of doubles works correctly
	 */
	@Test
	public void testFromVectors() {
		double[][][] vectors = {
				{{0}, {0}},
				{{1}, {1}},
				{{2}, {2}}
		};
		
		ArrayList<Matrix> components = BlockComponents.fromVectors(vectors).getComponents();
		
		for (Pair<double[][], Matrix> pair : 
				new Zip2Iterable<double[][], Matrix>(Arrays.asList(vectors), components)) {
			double[][] vector = pair.getFirst();
			Matrix c = pair.getSecond();
			assertEquals(c.getColumnDimension(), 1);
			assertEquals(c.getRowDimension(), 2);
			assertArrayEquals(c.getArray(), vector);
		}
	}
	
	private void assertComponentsEqualVectors(ArrayList<Matrix> components, double[][][] vectors) {
		for (Pair<double[][], Matrix> pair : 
			new Zip2Iterable<double[][], Matrix>(Arrays.asList(vectors), components)) {
			double[][] vector = pair.getFirst();
			Matrix c = pair.getSecond();
			assertArrayEquals(c.getArray(), vector);
		}
	}

	/**
	 * Test rotation of components works correctly
	 */
	@Test
	public void testGetRotation() {
		double[][][] vectors = {
				{{0}, {0}},
				{{1}, {0}},
				{{0}, {2}},
				{{3}, {1}}
		};
		
		BlockComponents rotation = BlockComponents.fromVectors(vectors).getRotation();
		double[][][] rotated90 = {
				{{0}, {0}},
				{{0}, {1}},
				{{-2}, {0}},
				{{-1}, {3}}
		};
		assertComponentsEqualVectors(rotation.getComponents(), rotated90);
		
		rotation = rotation.getRotation();
		double[][][] rotated180 = {
				{{0}, {0}},
				{{-1}, {0}},
				{{0}, {-2}},
				{{-3}, {-1}}
		};
		assertComponentsEqualVectors(rotation.getComponents(), rotated180);
		
		rotation = rotation.getRotation();
		double[][][] rotated270 = {
				{{0}, {0}},
				{{0}, {-1}},
				{{2}, {0}},
				{{1}, {-3}}
		};
		assertComponentsEqualVectors(rotation.getComponents(), rotated270);
	}

}
