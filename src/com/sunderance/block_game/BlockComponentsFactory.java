package com.sunderance.block_game;

import java.util.ArrayList;

import Jama.Matrix;

public class BlockComponentsFactory {
	
	private BlockComponents componentsFromVectors(double[][][] vectors) {
		ArrayList<Matrix> components = new ArrayList<Matrix>();
		
		for (double[][] vector : vectors) {
			components.add(new Matrix(vector));
		}
		
		return new BlockComponents(components);
	}

	public BlockComponents l() {
		double[][][] vectors = {
				{{0}, {0}},
				{{0}, {1}},
				{{0}, {-1}},
				{{1}, {-1}}
		};
		return componentsFromVectors(vectors);
	}
}
