package com.sunderance.block_game;

import com.sunderance.utils.Pair;

/**
 * The game grid
 * 
 * @author Robert Berry
 * @version 0.1
 */
public class BlockGrid {
	private int columns;
	private int rows;
	
	private int[][] grid;
	
	/**
	 * Creates a BlockGrid with the given number of columns and rows
	 * 
	 * @param columns The number of columns
	 * @param rows The number of rows
	 */
	public BlockGrid(int columns, int rows) {
		super();
		this.columns = columns;
		this.rows = rows;
		this.grid = new int[columns][rows];
	}
	
	/**
	 * Returns the initial point where blocks in the grid should appear
	 * 
	 * @return The initial point
	 */
	public Pair<Integer, Integer> getBlockStartPoint() {
		return new Pair<Integer, Integer>(
				(int) Math.ceil((columns - 1) / 2), 
				rows - 1);
	}
	
	/**
	 * Helper method - throws an error if x and y are outside the bounds of the
	 * grid
	 * 
	 * @param x The x co-ordinate
	 * @param y The y co-ordinate
	 */
	private void assertValidCoordinates(int x, int y) {
		if (x > columns || y > rows || x < 0 || y < 0) {
			throw new IndexOutOfBoundsException(
					String.format("(%d, %d) out of range for %dx%d game grid.",
							x, y, columns, rows));
		}
	}
	
	/**
	 * The value of the grid at x, y
	 * 
	 * @param x The x co-ordinate
	 * @param y The y co-ordinate
	 * @return The value
	 */
	public int get(int x, int y) {
		assertValidCoordinates(x, y);
		return this.grid[x][y];
	}
	
	/**
	 * Sets the value of the grid at x, y
	 * 
	 * @param x The x co-ordinate
	 * @param y The y co-ordinate
	 * @param val The new value
	 */
	public void set(int x, int y, int val) {
		assertValidCoordinates(x, y);
		this.grid[x][y] = val;
	}
	
	/**
	 * Clears the grid
	 */
	public void clear() {
		for (int x = 0; x < columns; x++) {
			for (int y = 0; y < rows; y++) {
				set(x, y, 0);
			}
		}
	}
}
