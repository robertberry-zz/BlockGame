/**
 * 
 */
package com.sunderance.block_game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Table of scores, ordered from highest to least
 * 
 * @author Robert Berry
 */
public class ScoreTable implements Iterable<ScoreTableEntry> {
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
	 * Creates a score table from a given data file
	 * 
	 * @param data The file
	 * @return The score table
	 * @throws FileNotFoundException If the file cannot be found
	 */
	public static ScoreTable fromFile(File data) throws FileNotFoundException {
		Scanner scanner = new Scanner(data, "UTF-8").useDelimiter("\\|");
		
		ArrayList<ScoreTableEntry> scores = new ArrayList<ScoreTableEntry>();
		
		while (scanner.hasNextInt()) {
			int score = scanner.nextInt();
			System.out.println(score);
			String name = scanner.next();
			System.out.println(name);
			
			scores.add(new ScoreTableEntry(name, score));
		}
		
		return new ScoreTable(scores, scores.size());
	}

	@Override
	public Iterator<ScoreTableEntry> iterator() {
		return scores.iterator();
	}
}
