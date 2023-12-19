package org.tue;

import lombok.SneakyThrows;
import org.tue.dto.Node;
import org.tue.dto.Owner;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ParityGames {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Provide the path to the file.");
            System.exit(-1);
        }
        var nodes = parseFile(args[0]);
        System.out.println(Arrays.toString(nodes));
    }

    @SneakyThrows({FileNotFoundException.class, IOException.class})
    private static Node[] parseFile(String input) {
        var streamReader = new InputStreamReader(new FileInputStream(input));
        var reader = new BufferedReader(streamReader);
        var numNodesString = reader.readLine().split(" ")[1];
        int numNodes = Integer.parseInt(numNodesString.substring(0, numNodesString.length() - 1));
        //The count starts from 0
        numNodes++;
        var nodes = new Node[numNodes];
        String line;
        //Remove the semicolon
        while ((line = reader.readLine()) != null) {
            line = line.substring(0, line.length()-1);
            String[] nodeParts = line.split(" ");
            String name = "";
            if (nodeParts.length > 4) {
                name = parseName(line);
            }
            var index = Integer.parseInt(nodeParts[0]);
            var priority = Integer.parseInt(nodeParts[1]);
            var owner = Owner.parseOwner(nodeParts[2]);
            var successors = Stream.of(nodeParts[3].split(","))
                    .map(Integer::parseInt)
                    .toList();
            nodes[index] = Node.builder()
                    .id(index)
                    .priority(priority)
                    .owner(owner)
                    .name(name)
                    .successors(successors)
                    .build();
        }
        return nodes;
    }

    private static String parseName(String line) {
        int idx = line.indexOf('\"');
        idx++;
        var sb = new StringBuilder();
        while (line.charAt(idx) != '\"') {
            sb.append(line.charAt(idx));
            idx++;
        }
        return sb.toString();
    }

}
