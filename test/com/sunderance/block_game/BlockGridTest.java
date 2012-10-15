/**
 * 
 */
package com.sunderance.block_game;

import junit.framework.TestCase;

import org.junit.Test;

import com.sunderance.utils.Pair;

/**
 * Tests for BlockGrid
 * 
 * @author Robert Berry
 * @version 0.1
 */
public class BlockGridTest extends TestCase {
	private static final int rows = 3;
	private static final int cols = 3;
	private BlockGrid grid;
	
	protected void setUp() {
		grid = new BlockGrid(rows, cols);
	}

	/**
	 * Test method for 
	 * {@link com.sunderance.block_game.BlockGrid#getBlockStartPoint()}.
	 */
	@Test
	public void testGetBlockStartPoint() {
		Pair<Integer, Integer> coordinates = grid.getBlockStartPoint();
		
		assertEquals(coordinates.getFirst(), new Integer(1));
		assertEquals(coordinates.getSecond(), new Integer(2));
	}

	/**
	 * Test method for 
	 * {@link com.sunderance.block_game.BlockGrid#get(int, int)}.
	 */
	@Test
	public void testGet() {
		// all should initially be zero
		for (int x = 0; x < cols; x++) {
			for (int y = 0; y < rows; y++) {
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
		for (int x = 0; x < cols; x++) {
			for (int y = 0; y < rows; y++) {
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
		for (int x = 0; x < cols; x++) {
			for (int y = 0; y < rows; y++) {
				grid.set(x, y, 1);
			}
		}
		grid.clear();
		for (int x = 0; x < cols; x++) {
			for (int y = 0; y < rows; y++) {
				assertEquals(grid.get(x, y), 0);
			}
		}
	}
}
