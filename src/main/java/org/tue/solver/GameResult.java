package org.tue.solver;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@AllArgsConstructor
public class GameResult {
    private List<Integer> wonByOdd;
    private List<Integer> wonByEven;
}
