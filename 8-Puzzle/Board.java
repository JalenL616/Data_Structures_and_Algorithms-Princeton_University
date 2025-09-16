/**
 * @author Jalen Locke attests that this code is their original work and was written in compliance with the class Academic Integrity and Collaboration Policy found in the syllabus. 
 Greatest challenge / most fun part: I never managed to get the memory to work fast enough!
    Probably would've switched to the more efficient 1D array (or even an int!) if I had more free time to tinker away
    Making a seperate Node class in Solver also made for unique unforseen interactions between this code and the a* algorithm.
 
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;


public class Board{

    private int[][] board;
    private int n;
    private String asString;
    private Stack<Board> neighbors;
    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles)
    {
        n = tiles.length;
        board = new int[n][n];
        copyBoard(board, tiles);
        
        neighbors = new Stack<>();
        //System.out.println(asString);
    }
         

    // string representation of this board
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", board[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    // board dimension n
    public int dimension()
    {
        return n;
    }

    // number of tiles out of place
    public int hamming()
    {
        for (int[] row : board)
        {
            for (int num : row)
            {
                //System.out.print(" " + num);
            }
            //System.out.print("\n");
        }
        //System.out.println();
        int numWrong = 0;
        for (int row = 0; row < n; row++)
        {
            for (int col = 0; col < n; col++)
            {
                //System.out.println(board[row][col] + ", but should be: " + ((row * n) + col  + 1));
                if ((board[row][col] != 0) && (board[row][col] != ((row * n) + col  + 1)))
                {
                    numWrong++;
                }
            }
        }
        return numWrong;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan()
    {
        int totalManhattan = 0;
        for (int row = 0; row < n; row++)
        {
            for (int col = 0; col < n; col++)
            {
                totalManhattan += squaresAway(row, col);
            }
        }
        return totalManhattan;
    }

    private int squaresAway(int row, int col)
    {
        int num = board[row][col];
        if (num == 0) return 0;
        int correctRow = (num - 1) / n;
        int correctCol = (num - 1) % n;
        return (Math.abs(correctRow - row) + Math.abs(correctCol - col));
    }

    // is this board the goal board?
    public boolean isGoal()
    {
        int hamming = hamming();
        //System.out.println("Hamming: " + hamming);
        return (hamming == 0);
    }

    // does this board equal y?
    public boolean equals(Object y)
    {
        if(this == y) return true; 
        if(y == null || y.getClass()!= this.getClass()) return false; 
        Board otherBoard = (Board) y;
        for (int row = 0; row < n; row++)
        {
            if (!Arrays.equals(otherBoard.board[row], this.board[row]))
            {
                return false;
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors()
    {
        if (neighbors.empty())
        {
            int row = 0;
            int col = 0;
            for (int i = 0; i < n; i++)
            {
                for (int j = 0; j < n; j++)
                {
                    if (board[i][j] == 0)
                    {
                        row = i;
                        i = n;
                        col = j;
                        j = n;
                    }
                }
            }
            // funny stuff for row and col vals
            int[][] twinBoard = new int[n][n];
            copyBoard(twinBoard, board);
            //System.out.println("zero at: " + row + ", " + col);
            // check sides
            if (row > 0)
            {
                newTwin(twinBoard, row - 1, col, row, col);
                //System.out.println("new neighbor board: " + twinBoard[0][0] + " " + twinBoard[0][1] + " " + twinBoard[1][0] + " " + twinBoard[1][1]);
                neighbors.push(new Board(twinBoard));
            }
            copyBoard(twinBoard, board);
            if (row < (n - 1))
            {
                newTwin(twinBoard, row + 1, col, row, col);
                //System.out.println("new neighbor board: " + twinBoard[0][0] + " " + twinBoard[0][1] + " " + twinBoard[1][0] + " " + twinBoard[1][1]);
                neighbors.push(new Board(twinBoard));
            }
            copyBoard(twinBoard, board);
            if (col > 0)
            {
                newTwin(twinBoard, row, col - 1, row, col);
                //System.out.println("new neighbor board: " + twinBoard[0][0] + " " + twinBoard[0][1] + " " + twinBoard[1][0] + " " + twinBoard[1][1]);
                neighbors.push(new Board(twinBoard));
            }
            copyBoard(twinBoard, board);
            if (col < (n - 1))
            {
                newTwin(twinBoard, row, col + 1, row, col);
                //System.out.println("new neighbor board: " + twinBoard[0][0] + " " + twinBoard[0][1] + " " + twinBoard[1][0] + " " + twinBoard[1][1]);
                neighbors.push(new Board(twinBoard));
            }
        }
        return neighbors;
    }

    private void copyBoard (int[][] board, int[][] toCopy)
    {
        for (int row = 0; row < board.length; row++)
        {
            for (int col = 0; col < board.length; col++)
            {
                board[row][col] = toCopy[row][col];
            }
        }
    }

    private int[][] newTwin(int[][] board, int oldRow, int oldCol, int newRow, int newCol)
    {

        int placeHolder = board[oldRow][oldCol]; 
        
        board[oldRow][oldCol] = board[newRow][newCol];
        board[newRow][newCol] = placeHolder;

        return board;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin()
    {
        int[][] twinBoard = new int[n][n];
        copyBoard(twinBoard, board);
        if (twinBoard[0][0] != 0)
        {
            if (twinBoard[0][1] == 0)
            {
                // we know 0 is at [0][1], so we use [0][0] and [1][0]
                twinBoard = newTwin(twinBoard, 0, 0, 1, 0);
            }
            else
            {
                // we know 0 is not at [0][1] or [0][0]
                twinBoard = newTwin(twinBoard, 0, 0, 0, 1);
            }
        }
        else
        {
            // we know 0 is at [0][0], so we use [0][1] and [1][0]
            twinBoard = newTwin(twinBoard, 0, 1, 1, 0);
        }
        return new Board(twinBoard);
    }

    // unit testing (not graded)
    public static void main(String[] args)
    {

    }

}