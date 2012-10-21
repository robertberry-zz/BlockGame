package com.sunderance.block_game;

import org.newdawn.slick.Image;

import Jama.Matrix;


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
	
	private Image[][] grid;
	
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
		this.grid = new Image[columns][rows];
	}
	
	/**
	 * Returns the y co-ordinate of the top of the grid
	 * 
	 * @return The co-ordinate
	 */
	public double getBottomY() {
		return y + blockSize * rows;
	}
	
	/**
	 * The initial x position for a block
	 * 
	 * @return The x position
	 */
	public int getStartX() {
		return (int) Math.ceil((columns - 1) / 2);
	}
	
	/**
	 * The initial y position for a block
	 * 
	 * @return The y position
	 */
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
	public Image get(int x, int y) {
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
	public void set(int x, int y, Image val) {
		assertValidCoordinates(x, y);
		this.grid[x][y] = val;
	}
	
	/**
	 * Clears the grid
	 */
	public void clear() {
		for (int x = 0; x < columns; x++) {
			for (int y = 0; y < rows; y++) {
				set(x, y, null);
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

	/**
	 * How many columns in the grid
	 * 
	 * @return Number of columns
	 */
	public int getColumns() {
		return columns;
	}

	/**
	 * How many rows in grid
	 * 
	 * @return Number of rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * Whether space for block in grid
	 * 
	 * @param movement The block
	 * @return Whether space
	 */
	public boolean hasSpaceForBlock(Block movement) {
		for (Matrix coordinate : movement.getGridCoordinates()) {
			double x = coordinate.get(0, 0);
			double y = coordinate.get(1, 0);
			
			if (x < 0 || x >= columns) {
				return false;
			}
			
			if (y >= rows) {
				continue;
			}
			
			if (y <= 0 || get((int) x, (int) y) != null) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Adds the block's images to the grid
	 * 
	 * @param block The block
	 */
	public void consume(Block block) {
		for (Matrix coordinate : block.getGridCoordinates()) {
			set((int) coordinate.get(0, 0), (int) coordinate.get(1, 0), 
					block.getImage());
		}
	}
	
	/**
	 * Given an x co-ordinate within the grid, returns the x co-ordinate on 
	 * the screen
	 * 
	 * @param x The co-ordinate within the grid
	 * @return The screen co-ordinate
	 */
	public float translateX(int x) {		
		return this.x + x * blockSize;
	}
	
	/**
	 * Given a y co-ordinate in the grid, returns the y co-ordinate on the
	 * screen
	 * 
	 * @param y The co-ordinate within the grid
	 * @return The screen co-ordinate
	 */
	public float translateY(int y) {
		return (float) getBottomY() - y * blockSize;
	}
	
	/**
	 * Renders the blocks stacked in the grid
	 */
	public void render() {
		for (int x = 0; x < columns; x++) {
			for (int y = 0; y < rows; y++) {
				Image block = get(x, y);
				if (block != null) {
					block.draw(translateX(x), translateY(y));
				}
			}
		}
	}
}
