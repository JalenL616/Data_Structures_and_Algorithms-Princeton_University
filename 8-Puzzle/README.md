8-Puzzle Solver
===============

This project is an implementation of a solver for the 8-puzzle problem, completed as part of my high school data structures and algorithms course. The solver uses the __A* search algorithm__ to find the shortest sequence of moves to solve a given 8-puzzle. This project was completed as __a programming assignment from the Coursera "Introduction to Algorithms, Part 1" course offered by Princeton University.__

https://www.coursera.org/learn/algorithms-part1/programming/iqOQi/8-puzzle

**Project Description**

The goal of this project is to efficiently find the shortest solution for any solvable configuration of the 8-puzzle. The 8-puzzle is a sliding puzzle that consists of a 3x3 grid with eight numbered tiles and one blank space. The objective is to rearrange the tiles from a given initial configuration to a target "goal" configuration by repeatedly sliding a tile into the blank space.
This implementation employs the A* search algorithm pathfinding and graph traversal algorithm to explore the puzzle's state space and find the optimal solution.

**The 8-Puzzle Problem**

The 8-puzzle is played on a 3x3 grid containing 8 tiles numbered 1 through 8, and one empty square. A move consists of sliding a tile adjacent to the empty square into the empty square. The goal is to reach a state where the tiles are in ascending order, as shown below:
1 2 3
4 5 6
7 8
Not all initial configurations of the 8-puzzle are solvable. A board is solvable if the number of inversions (pairs of tiles that are in the wrong order) is even. This implementation can detect and handle unsolvable puzzles.

**Solution Approach**

This project uses the __A* search algorithm__ to find the shortest path from the initial state to the goal state. A* uses a priority queue to explore the most promising states first. It balances the cost of the path so far with an estimated cost to reach the goal.
The priority of each search node is determined by the function:
f(n) = g(n) + h(n)
Where:
g(n) is the cost of the path from the starting node to the current node (the number of moves made so far).
h(n) is a heuristic function that estimates the cost of the cheapest path from the current node to the goal.

**Heuristics**

The efficiency of the A* algorithm heavily depends on the quality of the heuristic function. This implementation uses the Manhattan distance as its primary heuristic.
**Manhattan Distance**: This heuristic is the sum of the Manhattan distances (the sum of the horizontal and vertical distances) of each tile from its goal position. It never overestimates the actual number of moves required to solve the puzzle, which guarantees that the A* algorithm will find the shortest solution.

**Data Structures**

Priority Queue: A **min-priority queue** is used to store the search nodes. The node with the lowest priority (f(n) value) is always selected for expansion. This is a crucial component of the A* algorithm.
Board Representation: The state of the puzzle is represented by a 2D array or a similar data structure that allows for efficient generation of neighboring board states.

**Dependencies**

This project requires the algs4.jar library from the Princeton Algorithms, Part I course, which includes implementations of standard data structures like the priority queue.
