package edu.udel.cisc675.graph;

public class CFGNodeFactory implements NodeFactoryIF {
    @Override
    public Node createNode(int id) {
        return new CFGNode(id);
    }
}