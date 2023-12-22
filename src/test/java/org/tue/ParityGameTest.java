package org.tue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tue.dto.GameResult;
import org.tue.solver.LiftingStrategy;
import org.tue.solver.SPMSolver;
import org.tue.utils.PGParser;

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
}
