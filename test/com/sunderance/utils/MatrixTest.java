package com.sunderance.utils;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for Matrix utility class
 * 
 * @author Robert Berry
 * @version 0.1
 */
public class MatrixTest {
	/**
	 * Tests rounding a matrix works
	 */
	@Test
	public void testRound() {
		double[][] m = {{1.2, 2.4, 1.5}, {-1.6, 0.0, 9.3}};
		double[][] r = {{1.0, 2.0, 2.0}, {-2.0, 0.0, 9.0}};
		
		Jama.Matrix rounded = Matrix.round(new Jama.Matrix(m));
		assertArrayEquals(rounded.getArray(), r);
	}
}
