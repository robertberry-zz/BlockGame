/**
 * 
 */
package com.sunderance.block_game;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Entry in the score table
 * 
 * @author Robert Berry
 */
public class ScoreTableEntry implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private int score;
	
	/**
	 * Creates a score table entry with the given name of the person whose score
	 * it is and their score
	 * 
	 * @param name The name
	 * @param score The score
	 */
	public ScoreTableEntry(String name, int score) {
		super();
		this.name = name;
		this.score = score;
	}
		
	/**
	 * Name of whose score it is
	 * 
	 * @return The name
	 */
	public String getName() {
		return name;
	}
		
	/**
	 * The score
	 * 
	 * @return The score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Comparator for ordering entries by score
	 * 
	 * @author Robert Berry
	 */
	public static class ScoreComparator implements
		Comparator<ScoreTableEntry>  {
		
		public int compare(ScoreTableEntry score1, ScoreTableEntry score2) {
			return score1.getScore() - score2.getScore();
		}
	}
}