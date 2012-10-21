package com.sunderance.utils;

import java.util.List;

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
	
	/**
	 * Given a list of matrices, returns the matrix with the lowest value at
	 * the given m, n index
	 * 
	 * @param matrices The matrices
	 * @param n The n index
	 * @param m The m index
	 * @return The minimum matrix
	 */
	public static Jama.Matrix minByIndex(List<Jama.Matrix> matrices, 
			int n, int m) {
		Jama.Matrix min = matrices.get(0);
		double min_val = min.get(n, m);
		
		for (Jama.Matrix matrix : matrices) {
			if (matrix.get(n, m) < min_val) {
				min = matrix;
			}
		}
		
		return min;
	}
	
	/**
	 * Given a list of matrices, returns the matrix with the highest value at
	 * the given m, n index
	 * 
	 * @param matrices The matrices
	 * @param n The n index
	 * @param m The m index
	 * @return The maximum matrix
	 */
	public static Jama.Matrix maxByIndex(List<Jama.Matrix> matrices, int n, 
			int m) {
		Jama.Matrix max = matrices.get(0);
		double max_val = max.get(0, 0);
		
		for (Jama.Matrix matrix : matrices) {
			if (matrix.get(n, m) > max_val) {
				max = matrix;
			}
		}
		
		return max;
	}
}
