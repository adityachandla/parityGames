package org.tue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tue.dto.GameResult;
import org.tue.solver.LiftingStrategy;
import org.tue.solver.SPMSolver;
import org.tue.utils.PGParser;

import java.util.List;

public class ParityGameTest {
    private static final String TEST_PATH = "./tests/";

    @Test
    public void testSlideExample() {
        var nodes = PGParser.parseFile(TEST_PATH + "endOfSlide.gm");
        var solver = new SPMSolver(nodes);
        int[] liftingStrategy = LiftingStrategy.getOrderedLiftingStrategy(nodes);
        GameResult gameResult = solver.solve(liftingStrategy);
        Assertions.assertEquals(gameResult.getWonByEven().size(), 0);
        Assertions.assertEquals(gameResult.getWonByOdd().size(), nodes.length);
    }

    @Test
    public void testTestcase1() {
        var nodes = PGParser.parseFile(TEST_PATH + "testcase1.gm");
        var solver = new SPMSolver(nodes);
        int[] liftingStrategy = LiftingStrategy.getOrderedLiftingStrategy(nodes);
        GameResult gameResult = solver.solve(liftingStrategy);
        Assertions.assertEquals(gameResult.getWonByEven().size(), 0);
        Assertions.assertEquals(gameResult.getWonByOdd().size(), nodes.length);
    }
    @Test
    public void testTestcase3() {
        var nodes = PGParser.parseFile(TEST_PATH + "testcase3.gm");
        var solver = new SPMSolver(nodes);
        int[] liftingStrategy = LiftingStrategy.getOrderedLiftingStrategy(nodes);
        GameResult gameResult = solver.solve(liftingStrategy);
        Assertions.assertEquals(gameResult.getWonByEven().size(), nodes.length);
        Assertions.assertEquals(gameResult.getWonByOdd().size(), 0);
    }

    @Test
    public void testTestcase4() {
        var nodes = PGParser.parseFile(TEST_PATH + "testcase4.gm");
        var solver = new SPMSolver(nodes);
        int[] liftingStrategy = LiftingStrategy.getOrderedLiftingStrategy(nodes);
        GameResult gameResult = solver.solve(liftingStrategy);
        Assertions.assertEquals(gameResult.getWonByEven().size(), nodes.length);
    }
    @Test
    public void testTestcase6() {
        var nodes = PGParser.parseFile(TEST_PATH + "testcase6.gm");
        var solver = new SPMSolver(nodes);
        int[] liftingStrategy = LiftingStrategy.getOrderedLiftingStrategy(nodes);
        GameResult gameResult = solver.solve(liftingStrategy);
        Assertions.assertEquals(gameResult.getWonByOdd(), List.of(1,2));
        Assertions.assertEquals(gameResult.getWonByEven(), List.of(0,3,4));
    }
    @Test
    public void testTestcase7() {
        var nodes = PGParser.parseFile(TEST_PATH + "testcase7.gm");
        var solver = new SPMSolver(nodes);
        int[] liftingStrategy = LiftingStrategy.getOrderedLiftingStrategy(nodes);
        GameResult gameResult = solver.solve(liftingStrategy);
        Assertions.assertEquals(gameResult.getWonByOdd(), List.of(1,2));
        Assertions.assertEquals(gameResult.getWonByEven(), List.of(0));
    }
    @Test
    public void testTestcase8() {
        var nodes = PGParser.parseFile(TEST_PATH + "testcase8.gm");
        var solver = new SPMSolver(nodes);
        int[] liftingStrategy = LiftingStrategy.getOrderedLiftingStrategy(nodes);
        GameResult gameResult = solver.solve(liftingStrategy);
        Assertions.assertEquals(gameResult.getWonByOdd(), List.of(3,5));
        Assertions.assertEquals(gameResult.getWonByEven(), List.of(0,1,2,4));
    }
}
