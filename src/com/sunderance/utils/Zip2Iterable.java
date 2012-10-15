/**
 * Zip2Iterable
 */
package com.sunderance.utils;

import java.util.Iterator;

/**
 * Zip2Iterable - creates a new iterable from two iterables. This iterable produces
 * a Zip2Iterator, that allows the user to iterate through both iterators simultaneously,
 * producing pairs of adjacent values
 * 
 * @author Robert Berry
 * @version 0.1
 */
public class Zip2Iterable<A, B> implements Iterable<Pair<A, B>> {
	public Zip2Iterable(Iterable<A> iterableA, Iterable<B> iterableB) {
		super();
		this.iterableA = iterableA;
		this.iterableB = iterableB;
	}

	Iterable<A> iterableA;
	Iterable<B> iterableB;

	@Override
	public Iterator<Pair<A, B>> iterator() {
		Iterator<A> a = iterableA.iterator();
		Iterator<B> b = iterableB.iterator();
		
		return new Zip2Iterator<A, B>(a, b);
	}
}
