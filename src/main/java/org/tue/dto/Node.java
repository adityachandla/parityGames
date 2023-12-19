package org.tue.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
public class Node {
    private int id;
    private Owner owner;
    private int priority;
    private String name;
    private List<Integer> successors;
}
