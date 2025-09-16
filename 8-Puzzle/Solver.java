/**
 * @author Jalen Locke attests that this code is their original work and was written in compliance with the class Academic Integrity and Collaboration Policy found in the syllabus. 
 
 Greatest challenge / most fun part: I really liked the practical game part of this project!
    The algorithm made intuitive sense, and as long as I didn't accidentally create bugs, it worked as I imagined/understood.
    Something that really threw a wrench in my plans was the fact that clone() only makes a shallow copy of 2D arrays, which I did not know :(
 */

import java.util.ArrayList;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.MinPQ;

public class Solver {

    private boolean solved = false;
    private ArrayList<Board> moves;
    
    private class Node implements Comparable<Node> {
        private Board board;
        private int moves;
        private Node prevNode;
        public Node(Board board, Node prevNode)
        {
            this.board = board;
            if (prevNode != null)
            {
                this.moves = prevNode.moves + 1;
                this.prevNode = prevNode;
            }
        }
        public int numMoves()
        {
            return moves;
        }
        public Board getBoard()
        {
            return board;
        }
            
        public int compareTo(Node other)
        {
            //System.out.println("My compare: " + (this.board.manhattan() + this.numMoves()) + "\n Their compare: " + (other.board.manhattan() + other.numMoves()));
            return (this.board.manhattan() + this.numMoves())  - (other.board.manhattan() + other.numMoves());
        }

        public void updateMoves(ArrayList<Board> moves)
        {
            if (prevNode != null)
            {
                prevNode.updateMoves(moves);
            }
            moves.add(board);
        }
    }
    
    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial)
    {
        if (initial == null) throw new IllegalArgumentException();
        moves = new ArrayList<Board>();
        MinPQ<Node> PQ = new MinPQ<Node>();
        MinPQ<Node> twinPQ = new MinPQ<Node>();
        
        Node initialNode = new Node(initial, null);
        initialNode.moves = 0;
        Node twinInitial = new Node(initialNode.board.twin(), null);
        twinInitial.moves = 0;
        PQ.insert(initialNode);
        twinPQ.insert(twinInitial);

        if (initial.isGoal())
        {
            solved = true; 
            moves.add(initial);
        }
        while (!solved)
        {
            //System.out.println("PQ size: " + PQ.size());
            // Save the min for neighbors
            Node minNode = PQ.min();
            Board minBoard = minNode.getBoard();
            Node twinNode = twinPQ.min();
            Board twinBoard = twinPQ.delMin().getBoard();
            //System.out.println("minboard: " + minBoard);
            // Update moves / delete min
            PQ.delMin();

            // Check if solved!
            //System.out.println("Check: " + moves.get(moves.size() - 1));
            solved = minBoard.isGoal();
            if (solved)
            {
                minNode.updateMoves(moves);
                break;
            }
            if (twinBoard.isGoal()) break;
            // Get neighbors
            Iterable<Board> neighbors = minBoard.neighbors();
            Iterable<Board> twinNeighbors = twinBoard.neighbors();
            //System.out.println("neighbors: " + neighbors);
            // Add neighbors to PQ
            for (Board current : neighbors)
            {
                // Critical optimization of A* (no duplicate of prev)
                if ((minNode.prevNode == null) || !current.equals(minNode.prevNode.board))
                {
                    //if (current.manhattan() <= minBoard.manhattan())
                    {
                        PQ.insert(new Node(current, minNode));
                    }
                }
            }
            for (Board twinCurrent : twinNeighbors)
            {
                // Critical optimization of A* (no duplicate of prev)
                if ((twinNode.prevNode == null) || !twinCurrent.equals(twinNode.prevNode.board))
                {
                    //if (current.manhattan() <= minBoard.manhattan())
                    {
                        twinPQ.insert(new Node(twinCurrent, twinNode));
                    }
                }
            }

        }

    }

    // is the initial board solvable? (see below)
    public boolean isSolvable()
    {
        return solved;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves()
    {
        if (!solved)
        {
            return -1;
        }
        return moves.size() - 1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution()
    {
        if (!solved)
        {
            return null;
        }
        return moves;
    }

    // test client (see below) 
    public static void main(String[] args) {

    // create initial board from file
    In in = new In("TestCases/puzzle45.txt");
    int n = in.readInt();
    int[][] tiles = new int[n][n];
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            tiles[i][j] = in.readInt();
    Board initial = new Board(tiles);

    // solve the puzzle
    Solver solver = new Solver(initial);

    // print solution to standard output
    if (!solver.isSolvable())
        StdOut.println("No solution possible");
    else {
        StdOut.println("Minimum number of moves = " + solver.moves());
        for (Board board : solver.solution())
            StdOut.println(board);
    }

    
}

}