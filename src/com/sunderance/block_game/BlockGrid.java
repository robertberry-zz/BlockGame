package com.sunderance.block_game;


/**
 * The game grid
 * 
 * @author Robert Berry
 * @version 0.1
 */
public class BlockGrid {
	private int x;
	private int y;
	private int blockSize;
	private int columns;

	private int rows;
	
	private int[][] grid;
	
	/**
	 * Creates a BlockGrid with the given number of columns and rows
	 * 
	 * @param x The x co-ordinate of the bottom left of the grid
	 * @param y The y co-ordinate of the bottom left of the grid
	 * @param columns The number of columns
	 * @param rows The number of rows
	 */
	public BlockGrid(int x, int y, int blockSize, int columns, int rows) {
		super();
		this.x = x;
		this.y = y;
		this.blockSize = blockSize;
		this.columns = columns;
		this.rows = rows;
		this.grid = new int[columns][rows];
	}
	
	/**
	 * Returns the y co-ordinate of the top of the grid
	 * 
	 * @return The co-ordinate
	 */
	public double getBottomY() {
		return y + blockSize * rows;
	}
	
	public int getStartX() {
		return (int) Math.ceil((columns - 1) / 2);
	}
	
	public int getStartY() {
		return rows - 1;
	}
	
	/**
	 * Throws an error if x and y are outside the bounds of the grid
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
	
	/**
	 * X co-ordinate of top-left of grid
	 * 
	 * @return The x co-ordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * Y co-ordinate of top-left of grid
	 * 
	 * @return The y co-ordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * Size of a block in pixels
	 * 
	 * @return The size
	 */
	public int getBlockSize() {
		return blockSize;
	}

	public int getColumns() {
		return columns;
	}

	public int getRows() {
		return rows;
	}
}
