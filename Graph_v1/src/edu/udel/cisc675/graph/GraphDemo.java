package edu.udel.cisc675.graph;

public class GraphDemo {

	static void plainDemo1() {
		DirectedGraph graph = new DirectedGraph("MyGraph");
		PlainNode n0 = graph.addNode(), n1 = graph.addNode(),
				n2 = graph.addNode(), n3 = graph.addNode();
		n0.setText("red");
		n1.setText("blue");
		n2.setText("green");
		n3.setText("yellow");
		graph.addEdge(n0, n1);
		graph.addEdge(n1, n3);
		graph.addEdge(n0, n2);
		graph.addEdge(n2, n3);
		graph.addEdge(n2, n3); // adding twice shouldn't do anything
		graph.print(System.out);
		// should add some checks here using assert...
		System.out.println("Graph is acyclic: " + graph.isAcyclic());
	}

	static void plainDemo2() {
		DirectedGraph graph = new DirectedGraph("MyGraph");
		PlainNode n0 = graph.addNode(), n1 = graph.addNode(),
				n2 = graph.addNode(), n3 = graph.addNode();
		n0.setText("red");
		n1.setText("blue");
		n2.setText("green");
		n3.setText("yellow");
		graph.addEdge(n0, n1);
		graph.addEdge(n1, n2);
		graph.addEdge(n2, n3);
		graph.addEdge(n3, n1);
		graph.print(System.out);
		System.out.println("Graph is acyclic: " + graph.isAcyclic());
	}

	public static void main(String[] args) {
		plainDemo1();
		System.out.println();
		plainDemo2();
	}
}
