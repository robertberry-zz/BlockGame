/**
 * 
 */
package com.sunderance.block_game.events;

import java.util.SortedSet;

/**
 * @author robert
 *
 */
public class LinesClearedEvent {
	SortedSet<Integer> lines;

	public LinesClearedEvent(SortedSet<Integer> lines) {
		super();
		this.lines = lines;
	}

	public SortedSet<Integer> getLines() {
		return lines;
	}
}
