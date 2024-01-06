package org.tue.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class GameResult {
    private List<Integer> wonByOdd;
    private List<Integer> wonByEven;
    private int totalLifts;
    private int totalSuccessfulLifts;
}
