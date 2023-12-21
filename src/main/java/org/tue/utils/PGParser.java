package org.tue.utils;

import lombok.SneakyThrows;
import org.tue.dto.Node;
import org.tue.dto.Owner;

import java.io.*;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class PGParser {
    private static final Pattern firstLinePattern = Pattern.compile("(?<num>\\d+)");
    private static final Pattern linePattern =
            Pattern.compile("(?<id>\\d+) (?<priority>\\d+) (?<owner>\\d+) (?<successors>[\\d,]+)( \"(?<name>.*)\")?;");


    @SneakyThrows({FileNotFoundException.class, IOException.class})
    public static Node[] parseFile(String input) {
        var streamReader = new InputStreamReader(new FileInputStream(input));
        var reader = new BufferedReader(streamReader);
        String line = reader.readLine();
        var lineMatch = firstLinePattern.matcher(line);
        if (!lineMatch.find()) {
            throw new IllegalStateException("Unable to match the first line");
        }
        var numNodes = Integer.parseInt(lineMatch.group("num")) + 1;
        var nodes = new Node[numNodes];
        while ((line = reader.readLine()) != null) {
            var node = getNodeFromLine(line);
            nodes[node.getId()] = node;
        }
        return nodes;
    }

    private static Node getNodeFromLine(String line) {
        var matcher = linePattern.matcher(line);
        if (!matcher.find()) {
            throw new IllegalStateException("Unable to find a match");
        }
        var nodeBuilder = Node.builder();
        nodeBuilder.id(Integer.parseInt(matcher.group("id")));
        nodeBuilder.owner(Owner.parseOwner(matcher.group("owner")));
        nodeBuilder.priority(Integer.parseInt(matcher.group("priority")));
        var successors = Stream.of(matcher.group("successors").split(","))
                .map(Integer::parseInt)
                .toList();
        nodeBuilder.successors(successors);
        nodeBuilder.name(matcher.group("name"));
        return nodeBuilder.build();
    }
}
