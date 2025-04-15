package edu.udel.cisc475.numlist;

/** An immutable list of int. */
public abstract class ImmutableNumList {

	/** Single, fixed copy of the empty list. */
	private final static ImmutableNumList emptyList = new EmptyNumList();

	ImmutableNumList() {
	}

	/** Returns the empty list */
	public static ImmutableNumList emptyList() {
		return emptyList;
	}

	/**
	 * Is this list empty? Note: if it is not empty, this object can be safely
	 * cast to {@link NonemptyNumList}.
	 */
	public abstract boolean isEmpty();

	/** Returns the list obtained by prepending val to this list. */
	public abstract NonemptyNumList cons(int val);

	/** Computes the size of this list, i.e., the number of elements */
	public abstract int size();

	/** Determines whether this list contains x. */
	public abstract boolean contains(int x);

	/**
	 * Finds the first index into this list where the given value occurs, if the
	 * value occurs in this list. If the value does not occur, returns -1;
	 */
	public abstract int find(int x);

	/**
	 * Returns the list resulting by changing the idx-th element of this list to
	 * val.
	 */
	public abstract NonemptyNumList set(int idx, int val);

	/**
	 * Returns the list obtained by appending that list to the end of this one.
	 * [675 only]
	 */
	// public abstract ImmutableNumList append(ImmutableNumList that);

	/**
	 * Returns the list obtained by removing all zeros from this one . [675
	 * only]
	 */
	// public abstract ImmutableNumList removeZeros();

	/**
	 * Returns the list obtained by adding {@code x} to every element of this
	 * list. [675 only]
	 */
	// public abstract ImmutableNumList addToAll(int x);
}
