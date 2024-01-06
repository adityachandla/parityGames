package org.tue;

import org.tue.dto.GameResult;
import org.tue.dto.Node;
import org.tue.solver.BFSLifting;
import org.tue.solver.LiftingStrategy;
import org.tue.solver.SPMSolver;
import org.tue.utils.PGParser;

import java.io.File;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

public class ParityGame {

    private static final Map<String, Function<PGParser.ParseOutput, int[]>> liftingStrategyMap =
            Map.of("inputOrder", PGParser.ParseOutput::inputOrder,
                    "random", (res) -> LiftingStrategy.getRandomLiftingStrategy(res.nodes()),
                    "orderedNodes", (res) -> LiftingStrategy.getOrderedNodeTypeStrategy(res.nodes()),
                    "bfsOrder", ParityGame::getBfsOrderStrategy
            );

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Provide the path to the input file/folder optionally followed by lifting strategy.");
            System.exit(-1);
        }
        Function<PGParser.ParseOutput, int[]> liftingStrategy;
        if (args.length == 2 && liftingStrategyMap.containsKey(args[1])) {
            liftingStrategy = liftingStrategyMap.get(args[1]);
            System.out.printf("Using %s lifting strategy\n", args[1]);
        } else {
            liftingStrategy = liftingStrategyMap.get("inputOrder");
            System.out.println("Using input order strategy");
        }
        var input = new File(args[0]);
        if (input.isFile()) {
            processFile(input, liftingStrategy);
        } else if (input.isDirectory()) {
            var files = Objects.requireNonNull(input.listFiles());
            Stream.of(files).forEach((f) -> processFile(f, liftingStrategy));
        }
    }

    private static void processFile(File file, Function<PGParser.ParseOutput, int[]> liftingStrategy) {
        String filePath = file.getAbsolutePath();
        var parseOutput = PGParser.parseFile(filePath);
        var order = liftingStrategy.apply(parseOutput);
        var solver = new SPMSolver(parseOutput.nodes());

        GameResult gameResult = solver.solve(order);

        double successfulLiftPercentage = gameResult.getTotalSuccessfulLifts() / (double) gameResult.getTotalLifts();
        System.out.printf("""
                        File=%s Id0WonBy=%s WonByEven=%d WonByOdd=%d TotalLifts=%d SuccessfulLifts=%d SuccessPercentage=%f
                        """,
                file.getName(), gameResult.getWonByEven().contains(0) ? "even" : "odd",
                gameResult.getWonByEven().size(), gameResult.getWonByOdd().size(), gameResult.getTotalLifts(),
                gameResult.getTotalSuccessfulLifts(), successfulLiftPercentage);
    }

    private static int[] getBfsOrderStrategy(PGParser.ParseOutput parseOutput) {
        var bfsLifting = new BFSLifting(parseOutput.nodes());
        return bfsLifting.getOddNodeBFSStrategy();
    }
}
