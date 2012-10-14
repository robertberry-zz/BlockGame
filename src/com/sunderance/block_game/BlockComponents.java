package com.sunderance.block_game;

import java.util.ArrayList;

import Jama.Matrix;

/**
 * BlockComponents class.
 * 
 * @author Robert Berry
 * @version 0.1
 */
public class BlockComponents {
	private static Matrix R;
	
	private ArrayList<Matrix> components;

	/**
	 * Creates a block with the given component vectors. The component vectors
	 * denote the position of the sub-blocks around the centre of rotation.
	 * 
	 * @param components The component vectors
	 */
	public BlockComponents(ArrayList<Matrix> components) {
		super();
		this.components = components;
	}

	/**
	 * The vectors of the sub-blocks that this block comprises
	 * 
	 * @return The component vectors
	 */
	public ArrayList<Matrix> getComponents() {
		return components;
	}

	/**
	 * The 90-degree rotation matrix
	 * 
	 * @return The rotation matrix
	 */
	private static Matrix getR() {
		if (R == null) {
			double angle = Math.toRadians(90);
			double[][] matrix = {{Math.cos(angle), -Math.sin(angle)}, 
					{Math.sin(angle), Math.cos(angle)}};
			R = new Matrix(matrix);
		}
		
		return R;
	}
	
	/**
	 * Returns the block that is a 90-degree rotation of this block
	 * 
	 * @return The rotated block
	 */
	public BlockComponents getRotation() {
		Matrix R = getR();
		ArrayList<Matrix> new_components = new ArrayList<Matrix>();
		
		for (Matrix component : components) {
			new_components.add(R.times(component));
		}
		
		return new BlockComponents(new_components);
	}
}
