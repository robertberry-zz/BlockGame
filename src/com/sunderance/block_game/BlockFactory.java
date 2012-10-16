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
	private static final int N_TYPES = 7;
	
	private ArrayList<BlockComponents> lComponents;
	private ArrayList<BlockComponents> sComponents;
	private ArrayList<BlockComponents> zComponents;
	private ArrayList<BlockComponents> oComponents;
	private ArrayList<BlockComponents> iComponents;
	private ArrayList<BlockComponents> jComponents;
	private ArrayList<BlockComponents> tComponents;
	
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
		
		jComponents = withRotations(componentsFactory.j(), 4);
		tComponents = withRotations(componentsFactory.t(), 4);
		lComponents = withRotations(componentsFactory.l(), 4);
		sComponents = withRotations(componentsFactory.s(), 2);
		zComponents = withRotations(componentsFactory.z(), 2);
		oComponents = withRotations(componentsFactory.o(), 1);
		iComponents = withRotations(componentsFactory.i(), 2);
		
		pieceGenerator = new Random();
		imageFactory = new BlockImageFactory();
	}
	
	/**
	 * Helper method for producing a list of the original block and its 90-
	 * degree rotation
	 * 
	 * @param block The original components
	 * @return The original with a 90-degree rotation
	 */
	private ArrayList<BlockComponents> withRotations(BlockComponents block,
			int n) {
		ArrayList<BlockComponents> rotations = new ArrayList<BlockComponents>();

		while (n > 0) {
			rotations.add(block);
			block = block.getRotation();
			n--;
		}
		
		return rotations;
	}
	
	/**
	 * Creates a random block
	 * 
	 * @return The block
	 */
	public Block random() {
		int type = pieceGenerator.nextInt(N_TYPES);
		
		switch (type) {
		case 0:
			return s();
		case 1:
			return z();
		case 2:
			return l();
		case 3:
			return i();
		case 4:
			return j();
		case 5:
			return t();
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
				imageFactory.random(components.get(0).getComponents().size()), 0);
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
	
	/**
	 * Creates a J-block
	 * 
	 * @return The block
	 */
	public Block j() {
		return generateFromComponents(jComponents);
	}
	
	/**
	 * Generates a T-block
	 * 
	 * @return The block
	 */
	public Block t() {
		return generateFromComponents(tComponents);
	}
}
