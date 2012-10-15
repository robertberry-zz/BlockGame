package com.sunderance.utils;

/**
 * Utility functions for working with Jama.Matrix
 * 
 * @author Robert Berry
 * @version 0.1
 */
public final class Matrix {
	private Matrix() {
		throw new AssertionError("Cannot instantiate Matrix utility class.");
	}
	
	/**
	 * Rounds all of the elements in the matrix
	 * 
	 * @param m The matrix
	 * @return The matrix, rounded
	 */
	public static Jama.Matrix round(Jama.Matrix m) {
		Jama.Matrix rounded = m.copy();
		
		for (int i = 0; i < m.getRowDimension(); i++) {
			for (int j = 0; j < m.getColumnDimension(); j++) {
				rounded.set(i, j, Math.round(m.get(i, j)));
			}
		}
		
		return rounded;
	}
}
