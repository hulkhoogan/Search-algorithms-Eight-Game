package com.eightgamesolver.common;

public class HeuristicNode implements Comparable<HeuristicNode> {

    private String state;
    private int manhattan;
    private int depth;

    public HeuristicNode(String state, int depth, int manhattan) {
        this.state = state;
        this.depth = depth;
        this.manhattan = manhattan;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getManhattan() {
        return manhattan;
    }

    public void setManhattan(int manhattan) {
        this.manhattan = manhattan;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    @Override
    public int compareTo(HeuristicNode toCompare) {
        return Integer.compare(this.manhattan, toCompare.getManhattan());
    }

    @Override
    public int hashCode() { return Integer.parseInt(state); }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof HeuristicNode)) {
            return false;
        }
        HeuristicNode toCompare = (HeuristicNode) other;
        boolean manhattanCompare = this.getManhattan() == toCompare.getManhattan();
        boolean stateCompare = this.getState().equals(toCompare.getState());
        return manhattanCompare && stateCompare && this.getDepth() == toCompare.getDepth();
    }

}
