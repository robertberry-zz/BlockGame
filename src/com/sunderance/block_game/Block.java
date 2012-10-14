package com.sunderance.block_game;

import java.util.ArrayList;

import Jama.Matrix;

/**
 * A tetronimo block
 * 
 * @author Robert Berry
 * @version 0.1
 */
public class Block {
	private double x;
	
	private double y;
	
	private ArrayList<BlockComponents> projections;
	
	private int currentProjection;

	/**
	 * Creates a block at the given co-ordinates with the given list of
	 * projections of component pieces for the four possible rotations of
	 * the block
	 * 
	 * @param x The initial x co-ordinate
	 * @param y The initial y co-ordinate
	 * @param projections The rotation vectors
	 * @param currentProjection The current rotation (0-3)
	 */
	public Block(double x, double y, ArrayList<BlockComponents> projections,
			int currentProjection) {
		super();
		this.x = x;
		this.y = y;
		this.projections = projections;
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
		
		double[][] center_coordinates = {{x, y}};
		Matrix center = new Matrix(center_coordinates);
		
		for (Matrix component : getComponents()) {
			coordinates.add(component.times(center));
		}
		
		return coordinates;
	}
}
