package org.tue;

import org.tue.dto.GameResult;
import org.tue.dto.Node;
import org.tue.solver.SPMSolver;
import org.tue.utils.PGParser;

import java.io.File;
import java.util.Arrays;

public class ParityGame {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Provide the path to the folder containing files.");
            System.exit(-1);
        }

        for (String folderPath : args) {
            File folder = new File(folderPath);
            if (!folder.isDirectory()) {
                System.out.println("Path provided is not a folder.");
                continue;
            }

            File[] files = folder.listFiles();
            if (files == null || files.length == 0) {
                System.out.println("Folder is empty.");
                continue;
            }

            for (File file : files) {
                if (file.isFile()) {
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
        }
    }
}
