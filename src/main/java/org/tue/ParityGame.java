package org.tue;

import org.tue.dto.GameResult;
import org.tue.dto.Node;
import org.tue.solver.SPMSolver;
import org.tue.utils.PGParser;

public class ParityGame {


    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Provide the path to the file.");
            System.exit(-1);
        }
        var parseOutput = PGParser.parseFile(args[0]);
        Node[] nodes = parseOutput.nodes();
        var solver = new SPMSolver(nodes);
//        int[] liftingStrategy = LiftingStrategy.getOrderedLiftingStrategy(nodes);
        GameResult gameResult = solver.solve(parseOutput.inputOrder());
        System.out.println(gameResult);
    }

}
