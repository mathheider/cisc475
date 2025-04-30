package edu.udel.cisc675.graph;

import edu.udel.cisc675.graph.CFGNode.CFGNodeKind;

public class CFGNodeDemo {
	public static void main(String[] args) {
		CFGNode n0 = new CFGNode(0), n1 = new CFGNode(1), n2 = new CFGNode(2);
		n0.setLineno(10);
		n0.setKind(CFGNodeKind.ASSIGN);
		n0.setSourceCode("x=10;");
		n1.setLineno(15);
		n1.setKind(CFGNodeKind.BRANCH);
		n1.setSourceCode("if (x==0)");
		n2.setLineno(20);
		n2.setKind(CFGNodeKind.CALL);
		n2.setSourceCode("foo()");
		System.out.println(n0);
		System.out.println(n1);
		System.out.println(n2);
	}
}
