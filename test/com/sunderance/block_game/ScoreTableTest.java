package com.sunderance.block_game;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

public class ScoreTableTest extends TestCase {
	ScoreTable testTable;
	
	protected void setUp() {
		testTable = ScoreTable.fromDefaults();
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
		try {			
			File tmp = File.createTempFile("temp_scores", ".dat");
			FileOutputStream output = new FileOutputStream(tmp);
			testTable.writeToFile(output);
			
			FileInputStream input = new FileInputStream(tmp.getAbsolutePath());
			try {
				ScoreTable table = ScoreTable.fromFile(input);
				
				int i = 0;
				
				for (ScoreTableEntry entry : table) {
					assertEquals(testTable.get(i).getName(), entry.getName());
					assertEquals(testTable.get(i).getScore(), entry.getScore());
					
					i++;
				}
			} catch (IOException e) {
				fail("Could not read test data.");
			}
			tmp.delete();
		} catch (FileNotFoundException e1) {
			fail("Could not load test data.");
		} catch (IOException e1) {
			System.err.println(e1);
			fail("Could not write test data.");
		}
	}
}
