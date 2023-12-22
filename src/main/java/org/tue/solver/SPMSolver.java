package org.tue.solver;

import org.tue.dto.Node;
import org.tue.dto.Owner;
import org.tue.solver.measure.M;
import org.tue.solver.measure.Measure;
import org.tue.solver.measure.Top;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BinaryOperator;

public class SPMSolver {

    private final Node[] nodes;
    private final int maxPriority;
    private final Measure[] measures;
    //Stores the maximum possible value of M for this game.
    private final M maxM;

    public SPMSolver(Node[] nodes) {
        this.nodes = nodes;
        int max = 0;
        for (var n : nodes) {
            max = Integer.max(max, n.getPriority());
        }
        this.maxPriority = max;
        maxM = new M(new int[this.maxPriority + 1]);
        initializeMaxM();
        this.measures = new Measure[this.nodes.length];
        initializeMeasures();
    }

    /**
     * Maximum value of M is simply the count of odd
     * nodes in the game.
     */
    private void initializeMaxM() {
        for (var n : nodes) {
            if (n.getPriority() % 2 != 0) {
                maxM.getArr()[n.getPriority()]++;
            }
        }
    }

    /**
     * This method initializes all measures to an empty
     * array of size maxPriority+1.
     */
    private void initializeMeasures() {
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
        return computeGameResult();
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
        var currMeasure = measures[node.getId()];
        //Simple equals method checks for reference equality which is not what we want.
        System.out.printf("Current is %s, lifted is %s\n", currMeasure, liftedMeasure);
        boolean liftHappened = !currMeasure.measureEqual(liftedMeasure);
        //Values are monotonic. It is safe to just assign the new value.
        measures[node.getId()] = liftedMeasure;
        return liftHappened;
    }

    private Measure progress(Node src, Node dest) {
        //TODO implement.
        return Top.getInstance();
    }

    /**
     * This function partitions the nodes into two sets, the
     * ones won by odd and the ones won by even. The nodes
     * whose measure is top are won by odd and the rest are
     * won by even.
     *
     * @return GameResult
     */
    private GameResult computeGameResult() {
        var res = new GameResult(new ArrayList<>(), new ArrayList<>());
        System.out.println(Arrays.toString(measures));
        for (var node : nodes) {
            if (measures[node.getId()] instanceof Top) {
                res.getWonByOdd().add(node.getId());
            } else {
                res.getWonByEven().add(node.getId());
            }
        }
        return res;
    }
}
