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
	
	private int x;
	
	private int y;
	
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
	 * @param image The block image
	 * @param currentProjection The current rotation (0-3)
	 */
	public Block(BlockGrid grid, int x, int y,
			ArrayList<BlockComponents> projections,
			Image image, int currentProjection) {
		super();
		this.grid = grid;
		this.x = x;
		this.y = y;
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
	public ArrayList<Matrix> getCoordinates() {
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
	 * The left rotation of this block
	 * 
	 * @return The left rotation
	 */
	public Block getLeftRotation() {
		return new Block(grid, x, y, projections, image, 
				(currentProjection + 1) % projections.size());
	}
	
	/**
	 * The right rotation of this block
	 * 
	 * @return The right rotation
	 */
	public Block getRightRotation() {
		int newProjection = currentProjection - 1;
		if (newProjection < 0) {
			newProjection += projections.size();
		}
		
		return new Block(grid, x, y, projections, image,
				newProjection);
	}
	
	/**
	 * The block's image
	 * 
	 * @return The image
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * The left movement of this block
	 * 
	 * @return The left movement
	 */
	public Block getLeftMovement() {
		return new Block(grid, x - 1, y, projections, image, currentProjection);
	}
	
	/**
	 * The right movement of this block
	 * 
	 * @return The right movement
	 */
	public Block getRightMovement() {
		return new Block(grid, x + 1, y, projections, image, currentProjection);
	}
	
	/**
	 * The down movement of this block
	 * 
	 * @return The down movement
	 */
	public Block getDownMovement() {
		return new Block(grid, x, y - 1, projections, image, currentProjection);
	}
	
	/**
	 * If the block were to immediately drop down the grid, where it would
	 * drop to
	 * 
	 * @return The block
	 */
	public Block getGhostBlock() {
		Block ghost = this;
		Block next = ghost.getDownMovement();
		
		while (grid.hasSpaceForBlock(next)) {
			ghost = next;
			next = next.getDownMovement();
		}
		
		return ghost;
	}
	
	/**
	 * Renders the block
	 */
	public void render(BlockCoordinateMapper mapper) {
		for (Matrix m : getCoordinates()) {
			double x = mapper.translateX((int) m.get(0, 0));
			double y = mapper.translateY((int) m.get(1, 0));
			image.draw((float) x, (float) y);
		}
	}
}
