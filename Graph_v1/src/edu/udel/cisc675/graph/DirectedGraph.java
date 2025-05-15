package edu.udel.cisc675.graph;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class DirectedGraph {

    private String name;
    private NodeFactoryIF nodeFactory;
    private ArrayList<Node> nodes = new ArrayList<>();

    public DirectedGraph(String name, NodeFactoryIF factory) {
        this.name = name;
        this.nodeFactory = factory;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "DiGraph " + name;
    }

    public Collection<Node> nodes() {
        return nodes;
    }

    public Node addNode() {
        Node node = nodeFactory.createNode(nodes.size());
        nodes.add(node);
        return node;
    }

    private void checkNode(Node node) { 
        if (node == null)
            throw new IllegalArgumentException("Error: expected a Node but got null");
        int id = node.getId();
        if (id < 0 || id >= nodes.size() || nodes.get(id) != node)
            throw new IllegalArgumentException("Error: node does not belong to " + this + ": " + node);
    }

    public void addEdge(Node source, Node destination) { 
        checkNode(source);
        checkNode(destination);
        source.addSuccessor(destination);
    }

    public void print(PrintStream out) {
        out.println("digraph \"" + name.replace("\"", "\\\"") + "\" {"); // Escape quotes in name
        for (Node u : nodes) {
            String label = u.getText() != null ? u.getText().replace("\"", "\\\"") : ""; // Escape quotes in label
            out.println("  " + u.getId() + " [label=\"" + label + "\"];");
        }
        for (Node u : nodes) { 
            if (u.successors() != null) {
                for (Node v : u.successors()) { 
                    out.println("  " + u.getId() + " -> " + v.getId() + ";");
                }
            }
        }
        out.println("}");
    }

    class CycleSearcher {
        private Set<Node> stack = new HashSet<>();      
        private Set<Node> visited = new HashSet<>();    
        private boolean searchForCycle(Node u) {        // Parameter changed to Node
            if (visited.contains(u))
                return false;
            if (stack.contains(u))
                return true;
            stack.add(u);
            if (u.successors() != null) {
                for (Node v : u.successors()) {         
                    if (searchForCycle(v))
                        return true;
                }
            }
            stack.remove(u);
            visited.add(u);
            return false;
        }

        boolean hasCycle() {
            for (Node u : nodes) {                      
                assert stack.isEmpty() : "Stack should be empty";

                if (visited.contains(u))
                    continue;
                if (searchForCycle(u))
                    return true;
            }
            return false;
        }
    }

    public boolean isAcyclic() {
        CycleSearcher cs = new CycleSearcher();
        return !cs.hasCycle();
    }
}