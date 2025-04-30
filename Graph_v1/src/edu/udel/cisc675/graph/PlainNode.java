package edu.udel.cisc675.graph;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * A plain node in a directed graph. It stores an integer ID number, a text
 * field, and its list of successor nodes. For each successor node v, there is
 * an edge in the graph from this node to v.
 */
public class PlainNode {

	private int id;

	private String text;

	private Set<PlainNode> successors = new LinkedHashSet<>();

	PlainNode(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public String toString() {
		return "Node[" + id + ", " + getText() + "]";
	}

	void addSuccessor(PlainNode that) {
		successors.add(that);
	}

	public Collection<PlainNode> successors() {
		return successors;
	}
}
