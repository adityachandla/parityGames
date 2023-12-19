package org.tue.game;

import org.tue.dto.Node;
import org.tue.dto.Owner;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class SPMSolver {

    private final Node[] nodes;
    private final int maxPriority;
    private Measure[] measures;
    private Map<Integer, Integer> priorityCount;

    public SPMSolver(Node[] nodes) {
        this.nodes = nodes;
        int max = 0;
        for (var n : nodes) {
            max = Integer.max(max, n.getPriority());
        }
        this.maxPriority = max;
        createNodePriorityMap();
    }

    public void createNodePriorityMap() {
        priorityCount = new HashMap<>();
        for (var n : this.nodes) {
            var priority = n.getPriority();
            if (priority % 2 != 0) {
                int currVal = 0;
                if (priorityCount.containsKey(priority)) {
                    currVal = priorityCount.get(priority);
                }
                priorityCount.put(priority, currVal + 1);
            }
        }
    }

    public GameResult solve(int[] liftingOrder) {
        initializeMeasures();
        int iterationsWithoutProgress = 0;
        int idx = 0;
        while (iterationsWithoutProgress < this.nodes.length) {
            var nodeToLift = this.nodes[liftingOrder[idx]];
            if (lift(nodeToLift)) {
                iterationsWithoutProgress = 0;
            } else {
                iterationsWithoutProgress++;
            }
        }
        return null;
    }

    private boolean lift(Node node) {
        BiFunction<Measure, Measure, Measure> function;
        Measure startValue;
        if (node.getOwner() == Owner.EVEN) {
            function = this::minMeasure;
            startValue = new Measure.Top();
        } else {
            function = this::maxMeasure;
            startValue = new Measure.M(new int[maxPriority + 1]);
        }
        for (var succ : node.getSuccessors()) {
            startValue = function.apply(prog(node, this.nodes[succ]), startValue);
        }
        var currMeasure = measures[node.getId()];
        if (currMeasure.equals(startValue)) {
            return false;
        }
        if (minMeasure(currMeasure, startValue) == currMeasure) {
            measures[node.getId()] = startValue;
        }
        return true;
    }

    private Measure prog(Node src, Node dest) {
        return new Measure.Top();
    }

    private Measure minMeasure(Measure one, Measure two) {
        if (one instanceof Measure.Top t) {
            return two;
        }
        if (two instanceof Measure.Top t) {
            return one;
        }
        var oneArray = (Measure.M) one;
        var twoArray = (Measure.M) two;
        for (int i = 1; i < oneArray.arr().length; i += 2) {
            if (oneArray.arr()[i] < twoArray.arr()[i]) {
                return one;
            }
        }
        return two;
    }

    private Measure maxMeasure(Measure one, Measure two) {
        if (one instanceof Measure.Top t) {
            return one;
        }
        if (two instanceof Measure.Top t) {
            return two;
        }
        var oneArray = (Measure.M) one;
        var twoArray = (Measure.M) two;
        for (int i = 1; i < oneArray.arr().length; i += 2) {
            if (oneArray.arr()[i] < twoArray.arr()[i]) {
                return two;
            }
        }
        return one;
    }

    private void initializeMeasures() {
        this.measures = new Measure[this.nodes.length];
        for (int i = 0; i < this.measures.length; i++) {
            var emptyArray = new int[maxPriority + 1];
            this.measures[i] = new Measure.M(emptyArray);
        }
    }
}
