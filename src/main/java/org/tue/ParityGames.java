package org.tue;

import org.tue.utils.PGParser;

import java.util.Arrays;

public class ParityGames {


    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Provide the path to the file.");
            System.exit(-1);
        }
        var nodes = PGParser.parseFile(args[0]);
        System.out.println(Arrays.toString(nodes));
    }

}
