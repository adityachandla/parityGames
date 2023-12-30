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
    public void testTestcase1() {
        var nodes = PGParser.parseFile(TEST_PATH + "testcase1.gm");
        var solver = new SPMSolver(nodes.nodes());
        int[] liftingStrategy = LiftingStrategy.getOrderedLiftingStrategy(nodes.nodes());
        GameResult gameResult = solver.solve(liftingStrategy);
        Assertions.assertEquals(gameResult.getWonByEven().size(), 0);
        Assertions.assertEquals(gameResult.getWonByOdd().size(), nodes.nodes().length);
    }

    @Test
    public void testTestcase2() {
        var nodes = PGParser.parseFile(TEST_PATH + "testcase2.gm");
        var solver = new SPMSolver(nodes.nodes());
        int[] liftingStrategy = LiftingStrategy.getOrderedLiftingStrategy(nodes.nodes());
        GameResult gameResult = solver.solve(liftingStrategy);
        Assertions.assertEquals(gameResult.getWonByEven().size(), 0);
        Assertions.assertEquals(gameResult.getWonByOdd().size(), nodes.nodes().length);
    }

    @Test
    public void testTestcase3() {
        var nodes = PGParser.parseFile(TEST_PATH + "testcase3.gm");
        var solver = new SPMSolver(nodes.nodes());
        int[] liftingStrategy = LiftingStrategy.getOrderedLiftingStrategy(nodes.nodes());
        GameResult gameResult = solver.solve(liftingStrategy);
        Assertions.assertEquals(gameResult.getWonByEven().size(), nodes.nodes().length);
        Assertions.assertEquals(gameResult.getWonByOdd().size(), 0);
    }

    @Test
    public void testTestcase4() {
        var nodes = PGParser.parseFile(TEST_PATH + "testcase4.gm");
        var solver = new SPMSolver(nodes.nodes());
        int[] liftingStrategy = LiftingStrategy.getOrderedLiftingStrategy(nodes.nodes());
        GameResult gameResult = solver.solve(liftingStrategy);
        Assertions.assertEquals(gameResult.getWonByEven().size(), nodes.nodes().length);
    }
    @Test
    public void testTestcase5() {
        var nodes = PGParser.parseFile(TEST_PATH + "testcase5.gm");
        var solver = new SPMSolver(nodes.nodes());
        int[] liftingStrategy = LiftingStrategy.getOrderedLiftingStrategy(nodes.nodes());
        GameResult gameResult = solver.solve(liftingStrategy);
        Assertions.assertEquals(gameResult.getWonByEven().size(), 0);
        Assertions.assertEquals(gameResult.getWonByOdd().size(), nodes.nodes().length);
    }
    @Test
    public void testTestcase5RandomOrder() {
        var nodes = PGParser.parseFile(TEST_PATH + "testcase5.gm");
        var solver = new SPMSolver(nodes.nodes());
        int[] liftingStrategy = LiftingStrategy.getRandomLiftingStrategy(nodes.nodes());
        GameResult gameResult = solver.solve(liftingStrategy);
        Assertions.assertEquals(gameResult.getWonByEven().size(), 0);
        Assertions.assertEquals(gameResult.getWonByOdd().size(), nodes.nodes().length);
    }
    @Test
    public void testTestcase6() {
        var nodes = PGParser.parseFile(TEST_PATH + "testcase6.gm");
        var solver = new SPMSolver(nodes.nodes());
        int[] liftingStrategy = LiftingStrategy.getOrderedLiftingStrategy(nodes.nodes());
        GameResult gameResult = solver.solve(liftingStrategy);
        Assertions.assertEquals(gameResult.getWonByOdd(), List.of(1,2));
        Assertions.assertEquals(gameResult.getWonByEven(), List.of(0,3,4));
    }
    @Test
    public void testTestcase7() {
        var nodes = PGParser.parseFile(TEST_PATH + "testcase7.gm");
        var solver = new SPMSolver(nodes.nodes());
        int[] liftingStrategy = LiftingStrategy.getOrderedLiftingStrategy(nodes.nodes());
        GameResult gameResult = solver.solve(liftingStrategy);
        Assertions.assertEquals(gameResult.getWonByOdd(), List.of(1,2));
        Assertions.assertEquals(gameResult.getWonByEven(), List.of(0));
    }
    @Test
    public void testTestcase8() {
        var nodes = PGParser.parseFile(TEST_PATH + "testcase8.gm");
        var solver = new SPMSolver(nodes.nodes());
        int[] liftingStrategy = LiftingStrategy.getOrderedLiftingStrategy(nodes.nodes());
        GameResult gameResult = solver.solve(nodes.inputOrder());
        Assertions.assertArrayEquals(nodes.inputOrder(), liftingStrategy);
        Assertions.assertEquals(gameResult.getWonByOdd(), List.of(3,5));
        Assertions.assertEquals(gameResult.getWonByEven(), List.of(0,1,2,4));
    }
}
