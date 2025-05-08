package edu.udel.cisc475.numlist;

/** An empty, immutable list of int */
public class EmptyNumList extends ImmutableNumList {

	public EmptyNumList() {
	}

	public boolean isEmpty() {
		return true;
	}

	public String toString() {
		return "{}";
	}

	public NonemptyNumList cons(int val) {
		return new NonemptyNumList(val, this);
	}

	public int size() {
		return 0;
	}

	public boolean contains(int x) {
		return false;
	}

	public int find(int x) {
		return -1;
	}

	public NonemptyNumList set(int idx, int val) {
		throw new IllegalArgumentException("idx exceeds length of list");
	}

	public boolean equals(Object obj) {
		return obj instanceof EmptyNumList;
	}

	public int hashCode() {
		return 234823; // just some randomly chosen int
	}
	public ImmutableNumList append(ImmutableNumList that) {
		return that;
	}

	@Override
	public ImmutableNumList removeZeros() {
		return this; 
	}

	
	@Override
	public ImmutableNumList addToAll(int x) {
		return this; 
	}
}
