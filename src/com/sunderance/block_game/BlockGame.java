package com.sunderance.block_game;

import Jama.Matrix;

public class BlockGame {
	private static BlockComponentsFactory componentsFactory = 
			new BlockComponentsFactory();
	
	/**
	 * Currently just test the libraries (should set up proper unit tests,
	 * but that's next)
	 * 
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		BlockComponents l = componentsFactory.l();
		
		System.out.println("Initial");
		for (Matrix m : l.getComponents()) {
			m.print(2, 2);
		}
		
		System.out.println("After rotation");
		
		for (Matrix m : l.getRotation().getComponents()) {
			m.print(2, 2);
		}
	}

}
