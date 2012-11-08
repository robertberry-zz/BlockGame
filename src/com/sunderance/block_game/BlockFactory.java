package com.sunderance.block_game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import org.newdawn.slick.Image;
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
	
	private Image greenBlock;
	private Image redBlock;
	private Image yellowBlock;
	private Image blueBlock;
	private Image lightBlueBlock;
	private Image purpleBlock;
	private Image orangeBlock;
	
	private BlockGrid grid;
	
	private LinkedList<Integer> pieceBag;
	
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
		
		greenBlock = new Image("res/blocks/green.png");
		redBlock = new Image("res/blocks/red.png");
		yellowBlock = new Image("res/blocks/yellow.png");
		blueBlock = new Image("res/blocks/blue.png");
		lightBlueBlock = new Image("res/blocks/light-blue.png");
		purpleBlock = new Image("res/blocks/purple.png");
		orangeBlock = new Image("res/blocks/orange.png");
		
		createPieceBag();
	}
	
	/**
	 * Creates a new piece bag for randomly generating blocks
	 */
	private void createPieceBag() {
		pieceBag = new LinkedList<Integer>();
		
		for (int i = 0; i < N_TYPES; i++) {
			pieceBag.add(i);
		}
		
		Collections.shuffle(pieceBag);
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
		if (pieceBag.isEmpty()) {
			createPieceBag();
		}
		int type = pieceBag.removeFirst();
		
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
	
	private Block makeBlock(ArrayList<BlockComponents> components, 
			Image image) {
		return new Block(grid, 0, 0, components, image, 0);
	}

	/**
	 * Creates an S-block
	 * 
	 * @return The block
	 */
	public Block s() {
		return makeBlock(sComponents, orangeBlock);
	}
	
	/**
	 * Creates a Z-block
	 * 
	 * @return The block
	 */
	public Block z() {
		return makeBlock(zComponents, redBlock);
	}

	/**
	 * Creates an O-block
	 * 
	 * @return The block
	 */
	public Block o() {
		return makeBlock(oComponents, purpleBlock);
	}
	
	/**
	 * Creates a i-block
	 * 
	 * @return The block
	 */
	public Block i() {
		return makeBlock(iComponents, lightBlueBlock);
	}
	
	/**
	 * Creates a L-block
	 * 
	 * @return The block
	 */
	public Block l() {
		return makeBlock(lComponents, blueBlock);
	}
	
	/**
	 * Creates a J-block
	 * 
	 * @return The block
	 */
	public Block j() {
		return makeBlock(jComponents, greenBlock);
	}
	
	/**
	 * Generates a T-block
	 * 
	 * @return The block
	 */
	public Block t() {
		return makeBlock(tComponents, yellowBlock);
	}

	/**
	 * Resets block factory for a new game
	 */
	public void reset() {
		createPieceBag();
	}
}
