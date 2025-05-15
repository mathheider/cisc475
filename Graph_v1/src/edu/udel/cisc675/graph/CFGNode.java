package edu.udel.cisc675.graph;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class CFGNode extends Node { // Extend Node

    public enum CFGNodeKind {
        ASSIGN, BRANCH, NOOP, CALL
    }


    int lineno; 
    String sourceCode; 
    CFGNodeKind kind; 
    
    private Set<CFGNode> successors = new LinkedHashSet<>();

    public CFGNode(int id) {
        super(id); // Call the superclass (Node) constructor
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

    @Override
    public String getText() {
        StringBuilder sb = new StringBuilder();
        if (sourceCode != null && !sourceCode.isEmpty()) {
            sb.append(sourceCode);
        } else {
            sb.append("CFGNode");
        }
        sb.append(" (Kind: ").append(kind != null ? kind : "N/A");
        sb.append(", Line: ").append(lineno > 0 ? lineno : "N/A").append(")");
        return sb.toString();
    }

    void addSuccessor(CFGNode that) {
        successors.add(that);
    }
    @Override
    public void addSuccessor(Node successor) {
        if (successor instanceof CFGNode) {
            this.addSuccessor((CFGNode) successor); 
        } else {
            throw new IllegalArgumentException("Cannot add successor of type " +
                                               successor.getClass().getName() +
                                               " to a CFGNode. Expected CFGNode.");
        }
    }

    @Override
    public Collection<CFGNode> successors() {
        return successors;
    }

}