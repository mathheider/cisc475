package edu.udel.cisc675.graph;

import java.util.Collection;

public abstract class Node {
    protected int id; 
    
    public Node(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public abstract String getText();
    public abstract Collection<? extends Node> successors(); 
    public abstract void addSuccessor(Node successor); 

    @Override
    public String toString() {
        String textContent = getText();
        return "Node[" + id + ", " + (textContent != null ? textContent : "") + "]";
    }
}