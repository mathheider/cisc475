package edu.udel.cisc675.graph;

import java.util.Collection;

public abstract class Node {
    protected int id; // Keep protected for direct access by subclasses if preferred, or private with getter

    public Node(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    // Abstract methods to be implemented by concrete node types
    public abstract String getText();
    public abstract Collection<? extends Node> successors(); // Allows PlainNode to return Collection<PlainNode>, etc.
    public abstract void addSuccessor(Node successor); // Generic way to add a successor

    @Override
    public String toString() {
        // Use getText() which will be implemented by subclasses.
        // Ensure getText() doesn't return null to avoid "null" string here.
        String textContent = getText();
        return "Node[" + id + ", " + (textContent != null ? textContent : "") + "]";
    }
}