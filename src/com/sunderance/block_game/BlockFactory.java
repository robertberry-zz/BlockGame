package com.sunderance.block_game;

import java.util.ArrayList;
import java.util.Random;

/**
 * Factory for creating blocks at a given co-ordinate
 * 
 * @author Robert Berry
 * @version 0.1
 */
public class BlockFactory {
	private ArrayList<BlockComponents> lComponents;
	private ArrayList<BlockComponents> sComponents;
	private ArrayList<BlockComponents> zComponents;
	private ArrayList<BlockComponents> oComponents;
	private ArrayList<BlockComponents> iComponents;
	
	private double initialX;
	private double initialY;
	
	private Random pieceGenerator;
	
	/**
	 * Creates a BlockFactory that will produce blocks at the given X and Y
	 * co-ordinates
	 * 
	 * @param initialX
	 * @param initialY
	 */
	public BlockFactory(double initialX, double initialY) {
		this.initialX = initialX;
		this.initialY = initialY;
		
		BlockComponentsFactory componentsFactory = new BlockComponentsFactory();
		
		lComponents = withRotations(componentsFactory.l());
		sComponents = withRotations(componentsFactory.s());
		zComponents = withRotations(componentsFactory.z());
		oComponents = withRotations(componentsFactory.o());
		iComponents = withRotations(componentsFactory.i());
		
		pieceGenerator = new Random();
	}
	
	/**
	 * Helper method for producing a list of 90 degree rotations of a given
	 * BlockComponents
	 * 
	 * @param block The original components
	 * @return The original with three 90-degree rotations
	 */
	private ArrayList<BlockComponents> withRotations(BlockComponents block) {
		ArrayList<BlockComponents> rotations = new ArrayList<BlockComponents>();

		rotations.add(block);
		
		int n_rotations = 3;
		
		for (int n = 0; n < n_rotations; n++) {
			block = block.getRotation();
			rotations.add(block);
		}
		
		return rotations;
	}
	
	/**
	 * Creates a random block
	 * 
	 * @return The block
	 */
	public Block random() {
		int type = pieceGenerator.nextInt(5);
		
		switch (type) {
		case 0:
			return s();
		case 1:
			return z();
		case 2:
			return l();
		case 3:
			return i();
		default:
			return o();
		}
	}

	/**
	 * Creates an S-block
	 * 
	 * @return The block
	 */
	public Block s() {
		return new Block(initialX, initialY, sComponents, 0);
	}
	
	/**
	 * Creates a Z-block
	 * 
	 * @return The block
	 */
	public Block z() {
		return new Block(initialX, initialY, zComponents, 0);
	}

	/**
	 * Creates an O-block
	 * 
	 * @return The block
	 */
	public Block o() {
		return new Block(initialX, initialY, oComponents, 0);
	}
	
	/**
	 * Creates a i-block
	 * 
	 * @return The block
	 */
	public Block i() {
		return new Block(initialX, initialY, iComponents, 0);
	}
	
	/**
	 * Creates a L-block
	 * 
	 * @return The block
	 */
	public Block l() {
		return new Block(initialX, initialY, lComponents, 0);
	}
}
