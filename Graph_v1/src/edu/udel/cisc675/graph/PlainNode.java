package edu.udel.cisc675.graph;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class PlainNode extends Node { 

    private String text;
    private Set<PlainNode> successors = new LinkedHashSet<>();

    PlainNode(int id) {
        super(id);
    }
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }
    void addSuccessor(PlainNode that) {
        successors.add(that);
    }

    @Override
    public void addSuccessor(Node successor) {
        if (successor instanceof PlainNode) {
            this.addSuccessor((PlainNode) successor); // Call the specific method above
        } else {
            throw new IllegalArgumentException("Cannot add successor of type " +
                                               successor.getClass().getName() +
                                               " to a PlainNode. Expected PlainNode.");
        }
    }

    @Override
    public Collection<PlainNode> successors() { 
    	return successors;

    }

}