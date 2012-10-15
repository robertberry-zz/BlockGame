/**
 * 
 */
package com.sunderance.utils;

import java.util.Iterator;

/**
 * Zip2Iterator - lets user iterate through two iterators simultaneously, producing pairs of
 * the adjacent values
 * 
 * @author Robert Berry
 * @version 0.1
 */
public class Zip2Iterator<A, B> implements Iterator<Pair<A, B>> {

	public Zip2Iterator(Iterator<A> iteratorA, Iterator<B> iteratorB) {
		super();
		this.iteratorA = iteratorA;
		this.iteratorB = iteratorB;
	}

	Iterator<A> iteratorA;
	
	Iterator<B> iteratorB;
	
	/**
	 * Whether has more values
	 * 
	 * @return Has next
	 */
	@Override
	public boolean hasNext() {
		return iteratorA.hasNext() && iteratorB.hasNext();
	}

	/**
	 * The next values
	 * 
	 * @return The pair of values
	 */
	@Override
	public Pair<A, B> next() {
		A first = iteratorA.next();
		B second = iteratorB.next();
		
		return new Pair<A, B>(first, second);
	}

	/**
	 * Remove not currently supported
	 */
	@Override
	public void remove() {
		throw new UnsupportedOperationException("Remove not supported.");
	}
}
