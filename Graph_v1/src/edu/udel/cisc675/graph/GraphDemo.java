package edu.udel.cisc675.graph;


public class GraphDemo {

    static void plainDemoWithoutCycle() {
        System.out.println("--- plainDemoWithoutCycle ---");
        DirectedGraph graph = new DirectedGraph("PlainAcyclic", new PlainNodeFactory());

        PlainNode n0 = (PlainNode) graph.addNode();
        PlainNode n1 = (PlainNode) graph.addNode();
        PlainNode n2 = (PlainNode) graph.addNode();
        PlainNode n3 = (PlainNode) graph.addNode();

        n0.setText("red");
        n1.setText("blue");
        n2.setText("green");
        n3.setText("yellow");

        graph.addEdge(n0, n1);
        graph.addEdge(n1, n3);
        graph.addEdge(n0, n2);
        graph.addEdge(n2, n3);

        graph.print(System.out);
        System.out.println("Graph is acyclic: " + graph.isAcyclic());
        assert graph.isAcyclic() : "plainDemoWithoutCycle should be acyclic";
    }

    static void plainDemoWithCycle() {
        System.out.println("--- plainDemoWithCycle ---");
        DirectedGraph graph = new DirectedGraph("PlainCyclic", new PlainNodeFactory());

        PlainNode n0 = (PlainNode) graph.addNode();
        PlainNode n1 = (PlainNode) graph.addNode();
        PlainNode n2 = (PlainNode) graph.addNode();
        PlainNode n3 = (PlainNode) graph.addNode();

        n0.setText("apple");
        n1.setText("banana");
        n2.setText("cherry");
        n3.setText("date");

        graph.addEdge(n0, n1);
        graph.addEdge(n1, n2);
        graph.addEdge(n2, n3);
        graph.addEdge(n3, n1); 

        graph.print(System.out);
        System.out.println("Graph is acyclic: " + graph.isAcyclic());
        assert !graph.isAcyclic() : "plainDemoWithCycle should have a cycle";
    }

    static void cfgDemoWithoutCycle() {
        System.out.println("--- cfgDemoWithoutCycle ---");
        DirectedGraph graph = new DirectedGraph("CFGAcyclic", new CFGNodeFactory());

        CFGNode n0 = (CFGNode) graph.addNode();
        CFGNode n1 = (CFGNode) graph.addNode();
        CFGNode n2 = (CFGNode) graph.addNode();

        n0.setLineno(10);
        n0.setKind(CFGNode.CFGNodeKind.ASSIGN);
        n0.setSourceCode("int i = 0;");

        n1.setLineno(11);
        n1.setKind(CFGNode.CFGNodeKind.BRANCH);
        n1.setSourceCode("if (i < 10)");

        n2.setLineno(12);
        n2.setKind(CFGNode.CFGNodeKind.ASSIGN);
        n2.setSourceCode("i++; // loop body or then-branch");

        graph.addEdge(n0, n1); // Entry to condition
        graph.addEdge(n1, n2); // Condition to loop body/then-branch

        graph.print(System.out);
        System.out.println("Graph is acyclic: " + graph.isAcyclic());
        assert graph.isAcyclic() : "cfgDemoWithoutCycle should be acyclic";
    }

    static void cfgDemoWithCycle() {
        System.out.println("--- cfgDemoWithCycle ---");
        // Use CFGNodeFactory
        DirectedGraph graph = new DirectedGraph("CFGCyclic", new CFGNodeFactory());

        CFGNode n0 = (CFGNode) graph.addNode();
        CFGNode n1 = (CFGNode) graph.addNode();
        CFGNode n2 = (CFGNode) graph.addNode();

        n0.setLineno(20);
        n0.setKind(CFGNode.CFGNodeKind.ASSIGN);
        n0.setSourceCode("L0: x = 1;");

        n1.setLineno(21);
        n1.setKind(CFGNode.CFGNodeKind.BRANCH);
        n1.setSourceCode("L1: if (x > 0)");

        n2.setLineno(22);
        n2.setKind(CFGNode.CFGNodeKind.ASSIGN);
        n2.setSourceCode("L2: x--;");

        graph.addEdge(n0, n1); 
        graph.addEdge(n1, n2); 
        graph.addEdge(n2, n1); 
        graph.print(System.out);
        System.out.println("Graph is acyclic: " + graph.isAcyclic());
        assert !graph.isAcyclic() : "cfgDemoWithCycle should have a cycle";
    }

    public static void main(String[] args) {
        System.out.println("Running demos with assertions potentially enabled.");

        plainDemoWithoutCycle();
        System.out.println();

        plainDemoWithCycle();
        System.out.println();

        cfgDemoWithoutCycle();
        System.out.println();

        cfgDemoWithCycle();
        System.out.println();

        System.out.println("All demos complete.");
    }
}