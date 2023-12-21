package org.tue.game;

import org.tue.dto.Node;
import org.tue.dto.Owner;
import org.tue.game.measure.M;
import org.tue.game.measure.Measure;
import org.tue.game.measure.Top;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;

public class SPMSolver {

    private final Node[] nodes;
    private final int maxPriority;
    private Measure[] measures;
    //Stores the number of nodes with the given priority.
    private Map<Integer, Integer> priorityCount;

    public SPMSolver(Node[] nodes) {
        this.nodes = nodes;
        int max = 0;
        for (var n : nodes) {
            max = Integer.max(max, n.getPriority());
        }
        this.maxPriority = max;
        createPriorityCountMap();
    }

    public void createPriorityCountMap() {
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

    private void initializeMeasures() {
        this.measures = new Measure[this.nodes.length];
        for (int i = 0; i < this.measures.length; i++) {
            var emptyArray = new int[maxPriority + 1];
            this.measures[i] = new M(emptyArray);
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
        BinaryOperator<Measure> reduceFunction;
        if (node.getOwner() == Owner.EVEN) {
            reduceFunction = Measure::getMin;
        } else {
            reduceFunction = Measure::getMax;
        }
        var liftedMeasure = node.getSuccessors().stream()
                .map(successor -> progress(node, this.nodes[successor]))
                .reduce(reduceFunction)
                .orElseThrow();
        //Is the lifted measure different from the current one.
        var currMeasure = measures[node.getId()];
        //Simple equals method checks for reference equality which is not what we want.
        boolean liftHappened = currMeasure.measureEqual(liftedMeasure);
        //Values are monotonic. It is safe to just assign the new value.
        measures[node.getId()] = liftedMeasure;
        return liftHappened;
    }

    private Measure progress(Node src, Node dest) {
        //TODO implement.
        return Top.getInstance();
    }
}
