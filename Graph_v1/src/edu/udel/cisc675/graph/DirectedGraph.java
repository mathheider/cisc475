package edu.udel.cisc675.graph;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * A directed graph, in which nodes are instances of class {@link PlainNode}.
 */
public class DirectedGraph {

	/** The name of this graph */
	private String name;

	/** The nodes which occur in this directed graph, in order of their IDs. */
	private ArrayList<PlainNode> nodes = new ArrayList<>();

	/** Constructs a new empty graph. */
	public DirectedGraph(String name) {
		this.name = name;
	}

	/** Gets the name of this graph. */
	public String getName() {
		return name;
	}

	/** Returns short string representation of this graph. */
	public String toString() {
		return "DiGraph " + name;
	}

	/**
	 * Returns the set of nodes in this graph. Client should not modify this
	 * set.
	 */
	public Collection<PlainNode> nodes() {
		return nodes;
	}

	/**
	 * Adds a node to the graph. The new node has no outgoing (or incoming)
	 * edges.
	 * 
	 * @return the new node
	 */
	public PlainNode addNode() {
		PlainNode node = new PlainNode(nodes.size());
		nodes.add(node);
		return node;
	}

	/**
	 * Checks that the given node is non-null and actually belongs to this
	 * graph. If not, an exception is thrown.
	 * 
	 * @param node
	 *                 a node (might be null)
	 * @exception IllegalArgumentException
	 *                                         if {@code node} is null or does
	 *                                         not belong to this graph
	 */
	private void checkNode(PlainNode node) {
		if (node == null)
			throw new IllegalArgumentException(
					"Error: expected a Node but got null");
		int id = node.getId();
		if (id < 0 || id >= nodes.size() || nodes.get(id) != node)
			throw new IllegalArgumentException(
					"Error: node does not belong to " + this + ": " + node);
	}

	/**
	 * Adds an edge in this graph from the source node to the destination node.
	 * Precondition: both nodes must belong to this graph.
	 */
	public void addEdge(PlainNode source, PlainNode destination) {
		checkNode(source);
		checkNode(destination);
		source.addSuccessor(destination);
	}

	/**
	 * Prints the directed graph in standard GraphViz format.
	 */
	public void print(PrintStream out) {
		out.println("digraph " + name + " {");
		// print the nodes...
		for (PlainNode u : nodes)
			out.println("  " + u.getId() + " [" + u.getText() + "];");
		// print the edges...
		for (PlainNode u : nodes)
			for (PlainNode v : u.successors())
				out.println("  " + u.getId() + " -> " + v.getId() + ";");
		out.println("}");
	}

	/**
	 * A simple class to perform a DFS to determine whether this graph has a
	 * cycle.
	 */
	class CycleSearcher {
		private Set<PlainNode> stack = new HashSet<>();
		private Set<PlainNode> visited = new HashSet<>();

		/* Depth first search from u. Returns true iff cycle is found. */
		private boolean searchForCycle(PlainNode u) {
			if (visited.contains(u))
				return false;
			if (stack.contains(u))
				return true;
			stack.add(u);
			for (PlainNode v : u.successors()) {
				if (searchForCycle(v))
					return true;
			}
			stack.remove(u);
			visited.add(u);
			return false;
		}

		/**
		 * This is the entry method for this class. Returns true iff this graph
		 * has a cycle.
		 */
		boolean hasCycle() {
			for (PlainNode u : nodes) {
				assert stack.isEmpty();
				if (visited.contains(u))
					continue;
				if (searchForCycle(u))
					return true;
			}
			return false;
		}
	}

	/**
	 * Determines whether this graph is acyclic (i.e., this graph is a DAG).
	 * 
	 * @return {@code true} iff this graph has no directed cycle
	 */
	public boolean isAcyclic() {
		CycleSearcher cs = new CycleSearcher();
		return !cs.hasCycle();
	}
}
