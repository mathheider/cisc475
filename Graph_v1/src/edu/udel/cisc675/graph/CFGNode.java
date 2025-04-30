package edu.udel.cisc675.graph;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * A node in a Control Flow Graph. In addition to an integer ID, the node
 * contains information such as the line number, the source code, and the kind
 * of CFG node represented.
 */
public class CFGNode {

	/** The different kinds of CFG nodes. */
	public enum CFGNodeKind {
		ASSIGN, BRANCH, NOOP, CALL
	};

	/** Unique node ID number */
	private int id;

	/** Line number for this CFG node. */
	private int lineno;

	/** Get source code corresponding to this node */
	private String sourceCode;

	/** The kind of this CFG node */
	private CFGNodeKind kind;

	/** List of successor nodes */
	private Set<CFGNode> successors = new LinkedHashSet<>();

	public CFGNode(int id) {
		this.id = id;
	}

	public void setKind(CFGNodeKind kind) {
		this.kind = kind;
	}

	public CFGNodeKind getKind() {
		return kind;
	}

	public void setLineno(int lineno) {
		this.lineno = lineno;
	}

	public int getLineno() {
		return lineno;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public String toString() {
		return "Node[" + id + ", " + getText() + "]";
	}

	public String getText() {
		return "kind=" + kind + ", lineno=" + lineno + ", sourceCode=\""
				+ sourceCode + "\"";
	}

	void addSuccessor(CFGNode that) {
		successors.add(that);
	}

	public Collection<CFGNode> successors() {
		return successors;
	}
}
