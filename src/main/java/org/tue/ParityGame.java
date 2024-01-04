package org.tue;

import org.tue.dto.GameResult;
import org.tue.dto.Node;
import org.tue.solver.SPMSolver;
import org.tue.utils.PGParser;

import java.io.File;
import java.util.Objects;
import java.util.stream.Stream;

public class ParityGame {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Provide the path to the input file/folder.");
            System.exit(-1);
        }
        var input = new File(args[0]);
        if (input.isFile()) {
            processFile(input);
        } else if (input.isDirectory()) {
            var files = Objects.requireNonNull(input.listFiles());
            Stream.of(files).forEach(ParityGame::processFile);
        }
    }

    private static void processFile(File file) {
        String filePath = file.getAbsolutePath();
        var parseOutput = PGParser.parseFile(filePath);
        Node[] nodes = parseOutput.nodes();
        var solver = new SPMSolver(nodes);

        System.out.printf("Game being solved: %s%n", file);
        GameResult gameResult = solver.solve(parseOutput.inputOrder());

        if (gameResult.getWonByEven().contains(0)) {
            System.out.println("ID 0 is won by even");
        } else {
            System.out.println("ID 0 is won by odd");
        }
        System.out.printf("%d vertices are won by even%n", gameResult.getWonByEven().size());
        System.out.printf("%d vertices are won by odd%n", gameResult.getWonByOdd().size());
    }
}
