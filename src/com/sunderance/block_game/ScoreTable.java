/**
 * 
 */
package com.sunderance.block_game;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Table of scores, ordered from highest to least
 * 
 * @author Robert Berry
 */
public class ScoreTable implements Iterable<ScoreTableEntry>, Serializable {
	public static final int[] DEFAULT_SCORES = {
			99999,
			80000,
			70000,
			60000,
			50000,
			40000,
			30000,
			20000,
			10000,
			1
	};	

	private static final String[] DEFAULT_NAMES = {
			"Rob",
			"Jamie",
			"Adam H",
			"Nick",
			"Erik",
			"Amy",
			"Camilla",
			"Andy",
			"Paul",
			"Peter"
	};
	
	public static final String ALLOWED_CHARS = 
			"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890 ";
	
	public static final int MAX_NAME_LENGTH = 15;

	private static final long serialVersionUID = 1L;

	private List<ScoreTableEntry> scores;
	
	private int size;

	/**
	 * The score at the nth index of the table
	 * 
	 * @param n The index
	 * @return The score
	 */
	public ScoreTableEntry get(int n) {
		return scores.get(n);
	}
	
	/**
	 * Creates a score table with the given list of scores and of the given
	 * maximum size.
	 * 
	 * @param scores The score list
	 * @param size The maximum size
	 */
	public ScoreTable(List<ScoreTableEntry> scores, int size) {
		super();
		this.scores = scores;
		this.size = size;
	}
	
	/**
	 * Whether the given score could be inserted into this table
	 * 
	 * @param score The score
	 * @return Whether could be inserted
	 */
	public boolean incorporates(int score) {
		for (ScoreTableEntry entry : this) {
			if (score > entry.getScore()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Constructs a new score table from the current with the addition of 
	 * the given score
	 * 
	 * @param score The new score
	 * @return The new score table
	 */
	public ScoreTable withScore(ScoreTableEntry score) {	
		ArrayList<ScoreTableEntry> updatedScores =
				new ArrayList<ScoreTableEntry>();

		updatedScores.addAll(scores);
		updatedScores.add(score);
		
		Collections.sort(updatedScores, Collections.reverseOrder(
				new ScoreTableEntry.ScoreComparator()));
		
		// additional entry must have gone beyond maximum size of table
		if (updatedScores.size() > size) {
			updatedScores.remove(size);
		}

		return new ScoreTable(updatedScores, size);
	}
	
	/**
	 * Creates a score table from a given input stream
	 * 
	 * @param input The input stream
	 * @return The score table 
	 * @throws IOException If cannot read from the input stream
	 */
	public static ScoreTable fromFile(FileInputStream input) 
			throws IOException {
		ObjectInputStream objectInput = new ObjectInputStream(input);	
		try {
			return (ScoreTable) objectInput.readObject();
		} catch (ClassNotFoundException e) {
			// This shoudn't happen
			e.printStackTrace();
			System.exit(1);
			return null;
		}
	}
	
	/**
	 * The default score table
	 * 
	 * @return The default score table
	 */
	public static ScoreTable fromDefaults() {
		ArrayList<ScoreTableEntry> defaultEntries = new ArrayList<ScoreTableEntry>();
		
		for (int i = 0; i < DEFAULT_SCORES.length; i++) {
			defaultEntries.add(new ScoreTableEntry(DEFAULT_NAMES[i],
					DEFAULT_SCORES[i]));
		}
		
		return new ScoreTable(defaultEntries, defaultEntries.size());
	}
	
	/**
	 * Writes the score table to a file stream
	 * 
	 * @param output The output stream
	 * @throws IOException If the output stream cannot be written to
	 */
	public void writeToFile(FileOutputStream output) throws IOException {
		ObjectOutputStream objectOutput = new ObjectOutputStream(output);
		objectOutput.writeObject(this);
	}

	@Override
	public Iterator<ScoreTableEntry> iterator() {
		return scores.iterator();
	}
}
