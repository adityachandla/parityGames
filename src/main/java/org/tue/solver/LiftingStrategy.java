package org.tue.solver;

import org.tue.dto.Node;

import java.util.ArrayList;
import java.util.Collections;

public class LiftingStrategy {

    public static int[] getOrderedLiftingStrategy(Node[] nodes) {
        int[] order = new int[nodes.length];
        for (int i = 0; i < nodes.length; i++) {
            order[i] = nodes[i].getId();
        }
        return order;
    }

    public static int[] getRandomLiftingStrategy(Node[] nodes) {
        var nodeIds = new ArrayList<Integer>(nodes.length);
        for (var n : nodes) {
            nodeIds.add(n.getId());
        }
        Collections.shuffle(nodeIds);
        int[] order = new int[nodes.length];
        for (int i = 0; i < order.length; i++) {
            order[i] = nodeIds.get(i);
        }
        return order;
    }
}
