package com.sunderance.block_game;

import java.util.ArrayList;

import org.newdawn.slick.Image;

import Jama.Matrix;

/**
 * A tetronimo block
 * 
 * @author Robert Berry
 * @version 0.1
 */
public class Block {	
	private BlockGrid grid;
	
	private double x;
	
	private double y;
	
	private ArrayList<BlockComponents> projections;
	
	private Image image;
	
	private int currentProjection;

	/**
	 * Creates a block at the given co-ordinates with the given list of
	 * projections of component pieces for the four possible rotations of
	 * the block
	 * 
	 * @param grid The game grid
	 * @param x The initial x position in the grid
	 * @param y The initial y position in the grid
	 * @param projections The rotation vectors
	 * @param currentProjection The current rotation (0-3)
	 */
	public Block(BlockGrid grid,
			ArrayList<BlockComponents> projections,
			Image image, int currentProjection) {
		super();
		this.grid = grid;
		this.x = grid.getStartX();
		this.y = grid.getStartY();
		this.projections = projections;
		this.image = image;
		this.currentProjection = currentProjection;
	}
	
	/**
	 * The current components object for the block at its current rotation
	 * 
	 * @return The projection vectors
	 */
	private BlockComponents getProjection() {
		return projections.get(currentProjection);
	}
	
	/**
	 * The projection vectors for the block at its current rotation
	 * 
	 * @return The vectors
	 */
	private ArrayList<Matrix> getComponents() {
		return getProjection().getComponents();
	}
	
	/**
	 * Co-ordinates of the component pieces of the block within the grid
	 * 
	 * @return The co-ordinates
	 */
	public ArrayList<Matrix> getGridCoordinates() {
		ArrayList<Matrix> coordinates = new ArrayList<Matrix>();
		
		double[][] center_coordinates = {{x}, 
				{y}};
		Matrix center = new Matrix(center_coordinates);
		
		for (Matrix component : getComponents()) {
			coordinates.add(center.plus(component));
		}
		
		return coordinates;
	}
	
	/**
	 * Co-ordinates of the component pieces of the block on the screen
	 * 
	 * @return The co-ordinates
	 */
	public ArrayList<Matrix> getCoordinates() {
		ArrayList<Matrix> coordinates = new ArrayList<Matrix>();
		
		double[][] top_left_coordinates = {{grid.getX()}, 
				{grid.getY()}};
		Matrix top_left = new Matrix(top_left_coordinates);
		
		double[][] a_components = {{0}, {grid.getColumns()}};
		Matrix A = new Matrix(a_components);

		for (Matrix component : getGridCoordinates()) {
			component.set(1, 0, -component.get(1, 0));
			coordinates.add(
				top_left.plus(A.plus(component).times(grid.getBlockSize())));
		}
		
		return coordinates;
	}
	
	/**
	 * Rotates the piece 90 degrees to the right
	 */
	public void rotateRight() {
		if (currentProjection == 0) {
			currentProjection = projections.size() - 1;
		} else {
			currentProjection -= 1;
		}
	}
	
	/**
	 * Rotates the piece 90 degrees to the left
	 */
	public void rotateLeft() {
		if (currentProjection == projections.size() - 1) {
			currentProjection = 0;
		} else {
			currentProjection += 1;
		}
	}
	
	/**
	 * Renders the block
	 */
	public void render() {
		for (Matrix m : getCoordinates()) {
			double x = m.get(0, 0);
			double y = m.get(1, 0);
			image.draw((float) x, (float) y);
		}
	}
	
	/**
	 * The x co-ordinate of the left-most component
	 * 
	 * @return The co-ordinate
	 */
	private int getLeft() {
		return (int) com.sunderance.utils.Matrix
				.minByIndex(getGridCoordinates(), 0, 0).get(0, 0);
	}
	
	/**
	 * The x co-ordinate of the right-most component
	 * 
	 * @return The co-ordinate
	 */
	private int getRight() {
		return (int) com.sunderance.utils.Matrix
				.maxByIndex(getGridCoordinates(), 0, 0).get(0, 0);
	}

	/**
	 * Whether the piece is able to move left
	 * 
	 * @return Whether able to move left
	 */
	public boolean canMoveLeft() {
		return getLeft() > 0;
	}
	
	/**
	 * Whether the piece is able to move right
	 * 
	 * @return Whether able to move right
	 */
	public boolean canMoveRight() {
		return getRight() < grid.getColumns();
	}
	
	/**
	 * Moves the piece left
	 */
	public void moveLeft() {
		x -= 1;
	}
	
	/**
	 * Moves the piece right
	 */
	public void moveRight() {
		x += 1;
	}
	
	public boolean canMoveDown() {
		return false;
	}
	
	public void moveDown() {
		y -= 1;
	}
}
