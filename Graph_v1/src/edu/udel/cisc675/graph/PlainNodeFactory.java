package edu.udel.cisc675.graph;

public class PlainNodeFactory implements NodeFactoryIF {
    @Override
    public Node createNode(int id) {
        return new PlainNode(id);
    }
}