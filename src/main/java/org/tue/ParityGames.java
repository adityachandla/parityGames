package org.tue;

import org.tue.dto.Node;
import org.tue.solver.GameResult;
import org.tue.solver.LiftingStrategy;
import org.tue.solver.SPMSolver;
import org.tue.utils.PGParser;

public class ParityGames {


    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Provide the path to the file.");
            System.exit(-1);
        }
        Node[] nodes = PGParser.parseFile(args[0]);
        var solver = new SPMSolver(nodes);
        int[] liftingStrategy = LiftingStrategy.getOrderedLiftingStrategy(nodes);
        GameResult gameResult = solver.solve(liftingStrategy);
        System.out.println(gameResult);
    }

}
