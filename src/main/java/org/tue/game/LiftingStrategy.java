package org.tue.game;

import org.tue.dto.Node;

public class LiftingStrategy {

    public int[] getOrderedLiftingStrategy(Node[] nodes) {
        int[] order = new int[nodes.length];
        for (int i = 0; i < nodes.length; i++) {
            order[i] = nodes[i].getId();
        }
        return order;
    }
}
