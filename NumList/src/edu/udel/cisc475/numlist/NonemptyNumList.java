package edu.udel.cisc475.numlist;

/** A nonempty, immutable list of int. */
public class NonemptyNumList extends ImmutableNumList {

	/** The first element of the list */
	private int first;

	/** The rest of the list after the first element */
	private ImmutableNumList rest;

	NonemptyNumList(int first, ImmutableNumList rest) {
		this.first = first;
		this.rest = rest;
	}

	/** Returns the first elements of this */
	public int first() {
		return first;
	}

	/** Returns the rest of this list after the first element */
	public ImmutableNumList rest() {
		return rest;
	}

	public NonemptyNumList cons(int val) {
		return new NonemptyNumList(val, this);
	}

	public boolean isEmpty() {
		return false;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{ ");
		ImmutableNumList u = this;
		while (!u.isEmpty()) {
			int val = ((NonemptyNumList) u).first;
			sb.append(val + " ");
			u = ((NonemptyNumList) u).rest;
		}
		sb.append("}");
		return sb.toString();
	}

	public int size() {
		return 1 + rest.size();
	}

	public boolean contains(int x) {
		return first == x || rest.contains(x);
	}

	public int find(int x) {
		if (first == x)
			return 0;
		return 1 + rest.find(x);
	}

	public NonemptyNumList set(int idx, int val) {
		if (idx < 0)
			throw new IllegalArgumentException("Illegal index");
		return idx == 0 ? rest.cons(val) : rest.set(idx - 1, val).cons(first);
	}

	public boolean equals(Object obj) {
		return obj instanceof NonemptyNumList
				&& ((NonemptyNumList) obj).first == first
				&& ((NonemptyNumList) obj).rest.equals(rest);
	}

	public int hashCode() {
		return first + rest.hashCode();
	}
	
	public ImmutableNumList append(ImmutableNumList that) {
		return new NonemptyNumList(this.first, this.rest.append(that));
	}

	@Override
	public ImmutableNumList removeZeros() {
		ImmutableNumList processed = this.rest.removeZeros();
		
		if (this.first == 0) {
			return processed;
		} else {
			return new NonemptyNumList(this.first, processed);
		}
	}

	@Override
	public ImmutableNumList addToAll(int x) {
		int newFirst = this.first + x;
		ImmutableNumList newRest = this.rest.addToAll(x);
		return new NonemptyNumList(newFirst, newRest);
	}

}
