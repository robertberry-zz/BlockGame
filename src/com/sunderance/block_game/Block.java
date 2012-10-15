package com.sunderance.block_game;

import java.util.ArrayList;

import org.newdawn.slick.Image;

import com.sunderance.utils.Pair;

import Jama.Matrix;

/**
 * A tetronimo block
 * 
 * @author Robert Berry
 * @version 0.1
 */
public class Block {
	private final static int SIZE = 32;
	
	private BlockGrid grid;
	
	private double x;
	
	private double y;
	
	private ArrayList<BlockComponents> projections;
	
	private ArrayList<Image> images;
	
	private int currentProjection;

	/**
	 * Creates a block at the given co-ordinates with the given list of
	 * projections of component pieces for the four possible rotations of
	 * the block
	 * 
	 * @param grid The game grid
	 * @param x The initial x co-ordinate
	 * @param y The initial y co-ordinate
	 * @param projections The rotation vectors
	 * @param currentProjection The current rotation (0-3)
	 */
	public Block(BlockGrid grid,
			ArrayList<BlockComponents> projections,
			ArrayList<Image> images, int currentProjection) {
		super();
		this.grid = grid;
		this.x = grid.getStartX();
		this.y = grid.getStartY();
		this.projections = projections;
		this.images = images;
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
	 * Co-ordinates of the component pieces of the block
	 * 
	 * @return The co-ordinates
	 */
	public ArrayList<Matrix> getCoordinates() {
		ArrayList<Matrix> coordinates = new ArrayList<Matrix>();
		
		double[][] center_coordinates = {{x}, {y}};
		Matrix center = new Matrix(center_coordinates);
		
		for (Matrix component : getComponents()) {
			coordinates.add(center.plus(component.times(SIZE)));
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
	
	public void render() {
		
	}
}
