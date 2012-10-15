/**
 * 
 */
package com.sunderance.block_game;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * Tests for BlockGrid
 * 
 * @author Robert Berry
 * @version 0.1
 */
public class BlockGridTest extends TestCase {
	private static final int X = 0;
	private static final int Y = 0;
	private static final int SIZE = 32;
	
	private static final int ROWS = 3;
	private static final int COLS = 3;
	private BlockGrid grid;
	
	protected void setUp() {
		grid = new BlockGrid(X, Y, SIZE, ROWS, COLS);
	}

	/**
	 * Test method for 
	 * {@link com.sunderance.block_game.BlockGrid#getBlockStartPoint()}.
	 */
	@Test
	public void testGetBlockStartPoint() {
		assertEquals(grid.getStartX(), 1);
		assertEquals(grid.getStartY(), 2);
	}

	/**
	 * Test method for 
	 * {@link com.sunderance.block_game.BlockGrid#get(int, int)}.
	 */
	@Test
	public void testGet() {
		// all should initially be zero
		for (int x = 0; x < COLS; x++) {
			for (int y = 0; y < ROWS; y++) {
				assertEquals(grid.get(x, y), 0);
			}
		}
	}

	/**
	 * Test method for 
	 * {@link com.sunderance.block_game.BlockGrid#set(int, int, int)}.
	 */
	@Test
	public void testSet() {
		for (int x = 0; x < COLS; x++) {
			for (int y = 0; y < ROWS; y++) {
				grid.set(x, y, 1);
				assertEquals(grid.get(x, y), 1);
				grid.set(x, y, 0);
				assertEquals(grid.get(x, y), 0);
			}
		}
	}

	/**
	 * Test method for 
	 * {@link com.sunderance.block_game.BlockGrid#clear()}.
	 */
	@Test
	public void testClear() {
		for (int x = 0; x < COLS; x++) {
			for (int y = 0; y < ROWS; y++) {
				grid.set(x, y, 1);
			}
		}
		grid.clear();
		for (int x = 0; x < COLS; x++) {
			for (int y = 0; y < ROWS; y++) {
				assertEquals(grid.get(x, y), 0);
			}
		}
	}
}
