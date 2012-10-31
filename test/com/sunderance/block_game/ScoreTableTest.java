package com.sunderance.block_game;

import junit.framework.TestCase;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ScoreTableTest extends TestCase {
	private List<ScoreTableEntry> testEntries;
	
	private ScoreTable testTable;
	
	public static int[] TEST_SCORES = {
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
	
	private static String[] TEST_NAMES = {
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
	
	protected void setUp() {
		testEntries = new ArrayList<ScoreTableEntry>();
		
		for (int i = 0; i < TEST_SCORES.length; i++) {
			testEntries.add(new ScoreTableEntry(TEST_NAMES[i], TEST_SCORES[i]));
		}
		
		testTable = new ScoreTable(testEntries, testEntries.size());
	}
	
	@Test
	public void testWithScore() {
		ScoreTableEntry newScore = new ScoreTableEntry("Danny", 15000);
		
		ScoreTable newTable = testTable.withScore(newScore);
		
		assertEquals(newTable.get(8), newScore);
		assertEquals(newTable.get(9), testTable.get(8));
	}

	@Test
	public void testFromFile() {
		File file = new File("test/data/scores.dat");
		
		try {
			ScoreTable table = ScoreTable.fromFile(file);
			
			int i = 0;
			
			for (ScoreTableEntry entry : table) {
				assertEquals(TEST_NAMES[i], entry.getName());
				assertEquals(TEST_SCORES[i], entry.getScore());
				
				i++;
			}
		} catch (FileNotFoundException e) {
			fail("Could not load test data for scores");
		}
	}
}
