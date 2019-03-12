package com.eightgamesolver.utils;

import com.eightgamesolver.common.Node;
import com.eightgamesolver.exceptions.Exceptions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public interface NodesGenerator {
    Map<String, Node> visitedMap = new HashMap<>();
    LinkedList<String> generatedStates = new LinkedList<>();
    String NULL = "null";

    default String getSolutionPath(int[] initialState, int[] goalState) throws Exceptions.InvalidPath {
        Node root = new Node(0, "");
        String arrayToStringRegex = "\\[|]|,|\\s";
        String rootState = Arrays.toString(initialState).replaceAll(arrayToStringRegex, "");
        setFinalState(Arrays.toString(goalState).replaceAll(arrayToStringRegex, ""));

        generatedStates.add(rootState);
        visitedMap.put(rootState, root);
        while (!generatedStates.isEmpty()) {
            String state = generatedStates.remove();
            if (generateDescendents(state)) {
                return getSolutionPath();
            }
        }
        throw new Exceptions.InvalidPath();
    }

    default boolean generateDescendents(String parentState) {
        int zeroPosition = 0;
        int boardSize = parentState.length();
        char[] parsedState;
        for (int i = 0; i < boardSize; i++) {
            if (parentState.charAt(i) == '0') {
                zeroPosition = i;
                break;
            }
        }

        //up
        if (zeroPosition > 2) {
            parsedState = parentState.toCharArray();
            char temp = parsedState[zeroPosition];
            parsedState[zeroPosition] = parsedState[zeroPosition - 3];
            parsedState[zeroPosition - 3] = temp;
            String childState = new String(parsedState);
            if (validateState(parentState, childState, "U")) {
                return true;
            }
        }
        //down
        if (zeroPosition < 6) {
            parsedState = parentState.toCharArray();
            char temp = parsedState[zeroPosition];
            parsedState[zeroPosition] = parsedState[zeroPosition + 3];
            parsedState[zeroPosition + 3] = temp;
            String childState = new String(parsedState);
            if (validateState(parentState, childState, "D")) {
                return true;
            }
        }
        //left
        if (zeroPosition != 0 && zeroPosition != 3 && zeroPosition != 6) {
            parsedState = parentState.toCharArray();
            char temp = parsedState[zeroPosition];
            parsedState[zeroPosition] = parsedState[zeroPosition - 1];
            parsedState[zeroPosition - 1] = temp;
            String childState = new String(parsedState);
            if (validateState(parentState, childState, "L")) {
                return true;
            }
        }
        //right
        if (zeroPosition != 2 && zeroPosition != 5 && zeroPosition != 8) {
            parsedState = parentState.toCharArray();
            char temp = parsedState[zeroPosition];
            parsedState[zeroPosition] = parsedState[zeroPosition + 1];
            parsedState[zeroPosition + 1] = temp;
            String childState = new String(parsedState);
            return validateState(parentState, childState, "R");
        }
        return false;
    }

    boolean validateState(String parentState, String childState, String move);

    String getSolutionPath();

    void setSolutionPath(String path);

    String getFinalState();

    void setFinalState(String finalState);

}