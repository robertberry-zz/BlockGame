package com.sunderance.block_game;

import java.util.ArrayList;

import Jama.Matrix;

/**
 * Factory with templates for creating the various block types
 * 
 * @author Robert Berry
 * @version 0.1
 */
public class BlockComponentsFactory {
	/**
	 * Helper method for creating BlockComponents from an array of vectors
	 * 
	 * @param vectors The vectors
	 * @return The components
	 */
	private BlockComponents fromVectors(double[][][] vectors) {
		ArrayList<Matrix> components = new ArrayList<Matrix>();
		
		for (double[][] vector : vectors) {
			components.add(new Matrix(vector));
		}
		
		return new BlockComponents(components);
	}

	/**
	 * Components for an L-block
	 * 
	 * @return The components
	 */
	public BlockComponents l() {
		double[][][] vectors = {
				{{0}, {0}},
				{{0}, {1}},
				{{0}, {-1}},
				{{1}, {-1}}
		};
		return fromVectors(vectors);
	}
	
	/**
	 * Components for an S-block
	 * 
	 * @return The components
	 */
	public BlockComponents s() {
		double[][][] vectors = {
				{{0}, {0}},
				{{1}, {0}},
				{{0}, {1}},
				{{-1}, {1}}
		};
		return fromVectors(vectors);
	}
	
	/**
	 * Components for a Z-block
	 * 
	 * @return The components
	 */
	public BlockComponents z() {
		double[][][] vectors = {
				{{0}, {0}},
				{{-1}, {0}},
				{{0}, {1}},
				{{1}, {1}}
		};
		return fromVectors(vectors);
	}
	
	/**
	 * Components for an O-block
	 * 
	 * @return The components
	 */
	public BlockComponents o() {
		double[][][] vectors = {
				{{0}, {0}},
				{{1}, {0}},
				{{0}, {1}},
				{{1}, {1}}
		};
		return fromVectors(vectors);
	}
	
	/**
	 * Components for an I-block
	 * 
	 * @return The components
	 */
	public BlockComponents i() {
		double[][][] vectors = {
				{{0}, {0}},
				{{0}, {-1}},
				{{0}, {1}},
				{{0}, {2}}
		};
		return fromVectors(vectors);
	}
}
