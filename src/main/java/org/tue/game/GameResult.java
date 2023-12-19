package org.tue.game;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.List;

@ToString
@AllArgsConstructor
public class GameResult {
    private List<Integer> wonByOdd;
    private List<Integer> wonByEven;
}
