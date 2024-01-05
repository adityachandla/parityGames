package org.tue.solver;

import org.tue.dto.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Predicate;

public class BFSLifting {

    private final Node[] nodes;
    private List<List<Integer>> reverseAdjacency;
    private int idx;
    private final boolean[] visited;
    private int[] resOrder;
    public BFSLifting(Node[] nodes) {
        this.nodes = nodes;
        idx = 0;
        visited = new boolean[nodes.length];
        initializeReverseAdjacency();
    }

    private void initializeReverseAdjacency() {
        reverseAdjacency = new ArrayList<>(nodes.length);
        for(int i = 0; i < nodes.length; i++) {
            reverseAdjacency.add(new ArrayList<>());
        }
        for (var src: nodes) {
            for (var dest : src.getSuccessors()) {
                reverseAdjacency.get(dest).add(src.getId());
            }
        }
    }

    public int[] getOddNodeBFSStrategy() {
        resOrder = new int[nodes.length];
        Predicate<Node> oddOddPredicate = (n) -> n.isOddPriority() && n.isOddOwned();
        Predicate<Node> oddEvenPredicate = (n) -> n.isOddPriority() && !n.isOddOwned();
        Predicate<Node> evenOddPredicate = (n) -> !n.isOddPriority() && n.isOddOwned();
        Predicate<Node> evenEvenPredicate = (n) -> !n.isOddPriority() && !n.isOddOwned();
        var order = List.of(oddOddPredicate, oddEvenPredicate, evenOddPredicate, evenEvenPredicate);
        for (var predicate: order) {
            bfs(predicate);
        }
        if (idx != nodes.length) {
            throw new IllegalStateException("All nodes not visited");
        }
        return resOrder;
    }

    public void bfs(Predicate<Node> predicate) {
        Queue<Integer> bfsQueue = new LinkedList<>();
        for (int i = 0; i < nodes.length; i++) {
            if(!visited[nodes[i].getId()] && predicate.test(nodes[i])) {
                resOrder[idx++] = nodes[i].getId();
                visited[nodes[i].getId()] = true;
                bfsQueue.offer(i);
            }
        }
        while(!bfsQueue.isEmpty()) {
            var toProcess = bfsQueue.poll();
            for (var neighbour : reverseAdjacency.get(toProcess)) {
                if (!visited[neighbour]) {
                    resOrder[idx++] = neighbour;
                    bfsQueue.offer(neighbour);
                    visited[neighbour] = true;
                }
            }
        }
    }

}
