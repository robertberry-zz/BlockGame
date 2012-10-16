package com.sunderance.block_game;

/**
 * Factory with templates for creating the various block types
 * 
 * @author Robert Berry
 * @version 0.1
 */
public class BlockComponentsFactory {
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
		return BlockComponents.fromVectors(vectors);
	}
	
	/**
	 * Components for a J-block
	 * 
	 * @return The components
	 */
	public BlockComponents j() {
		double[][][] vectors = {
				{{0}, {0}},
				{{0}, {1}},
				{{0}, {-1}},
				{{-1}, {-1}}
		};
		return BlockComponents.fromVectors(vectors);
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
		return BlockComponents.fromVectors(vectors);
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
		return BlockComponents.fromVectors(vectors);
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
		return BlockComponents.fromVectors(vectors);
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
		return BlockComponents.fromVectors(vectors);
	}
	
	/**
	 * Components for a T-block
	 * 
	 * @return The components
	 */
	public BlockComponents t() {
		double[][][] vectors = {
				{{0}, {0}},
				{{-1}, {0}},
				{{1}, {0}},
				{{0}, {1}}
		};
		return BlockComponents.fromVectors(vectors);
	}
}
