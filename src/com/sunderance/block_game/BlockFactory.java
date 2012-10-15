package com.sunderance.block_game;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.SlickException;

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
	private BlockGrid grid;
	private Random pieceGenerator;
	private BlockImageFactory imageFactory;
	
	/**
	 * Creates a BlockFactory that will produce blocks at the given X and Y
	 * co-ordinates
	 * 
	 * @param initialX The initial x co-ordinate for blocks to appear
	 * @param initialY The initial y co-ordinate for blocks to appear
	 * @throws SlickException If cannot find block images
	 */
	public BlockFactory(BlockGrid grid) 
			throws SlickException {
		this.grid = grid;
		
		BlockComponentsFactory componentsFactory = new BlockComponentsFactory();
		
		lComponents = withRotations(componentsFactory.l());
		sComponents = withRotations(componentsFactory.s());
		zComponents = withRotations(componentsFactory.z());
		oComponents = withRotations(componentsFactory.o());
		iComponents = withRotations(componentsFactory.i());
		
		pieceGenerator = new Random();
		imageFactory = new BlockImageFactory();
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
	 * Helper method for generating a block from a list of components
	 * 
	 * @param components The list of components
	 * @return The generated block
	 */
	private Block generateFromComponents(
			ArrayList<BlockComponents> components) {
		
		return new Block(grid, components, 
				imageFactory.random(components.size()), 0);
	}

	/**
	 * Creates an S-block
	 * 
	 * @return The block
	 */
	public Block s() {
		return generateFromComponents(sComponents);
	}
	
	/**
	 * Creates a Z-block
	 * 
	 * @return The block
	 */
	public Block z() {
		return generateFromComponents(zComponents);
	}

	/**
	 * Creates an O-block
	 * 
	 * @return The block
	 */
	public Block o() {
		return generateFromComponents(oComponents);
	}
	
	/**
	 * Creates a i-block
	 * 
	 * @return The block
	 */
	public Block i() {
		return generateFromComponents(iComponents);
	}
	
	/**
	 * Creates a L-block
	 * 
	 * @return The block
	 */
	public Block l() {
		return generateFromComponents(lComponents);
	}
}
