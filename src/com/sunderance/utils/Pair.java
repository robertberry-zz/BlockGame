/**
 * 
 */
package com.sunderance.utils;

/**
 * Pair of values
 * 
 * @author Robert Berry
 * @version 0.1
 */
public class Pair<N, M> {
	N first;
	M second;

	public Pair(N first, M second) {
		this.first = first;
		this.second = second;
	}

	/**
	 * The first entry
	 * 
	 * @return The first
	 */
	public N getFirst() {
		return first;
	}

	/**
	 * Sets the first entry
	 * 
	 * @param first New value for the first
	 */
	public void setFirst(N first) {
		this.first = first;
	}

	/**
	 * The second entry
	 * 
	 * @return The second
	 */
	public M getSecond() {
		return second;
	}

	/**
	 * Sets the second entry
	 * 
	 * @param second New value for the second
	 */
	public void setSecond(M second) {
		this.second = second;
	}
}
