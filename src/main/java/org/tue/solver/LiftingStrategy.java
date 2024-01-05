package org.tue.solver;

import org.tue.dto.Node;
import org.tue.dto.Owner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Predicate;

public class LiftingStrategy {

    /**
     * @deprecated I don't think we need this anymore,
     * we already have the input order lifting in the
     * parse results.
     */
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

    /**
     * This function returns a list of nodes ids such that
     * the nodes are in the following order:
     * [OddParity + OddOwned, OddParity + EvenOwned,
     * EvenParity + OddOwned, EvenParity + EvenOwned]
     * @param nodes: array of nodes
     * @return an array of node ids
     */
    public static int[] getOrderedNodeTypeStrategy(Node[] nodes) {
        int[] res = new int[nodes.length];
        Predicate<Node> oddOddPredicate = (n) -> n.getPriority()%2 != 0 && n.getOwner() == Owner.ODD;
        Predicate<Node> oddEvenPredicate = (n) -> n.getPriority()%2 != 0 && n.getOwner() == Owner.EVEN;
        Predicate<Node> evenOddPredicate = (n) -> n.getPriority()%2 == 0 && n.getOwner() == Owner.ODD;
        Predicate<Node> evenEvenPredicate = (n) -> n.getPriority()%2 == 0 && n.getOwner() == Owner.EVEN;
        var order = List.of(oddOddPredicate, oddEvenPredicate, evenOddPredicate, evenEvenPredicate);
        int idx = 0;
        for (var predicate: order) {
            for (var n: nodes) {
                if(predicate.test(n)) {
                    res[idx++] = n.getId();
                }
            }
        }
        return res;
    }

}