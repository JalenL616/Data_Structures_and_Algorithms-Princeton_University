import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * @author Jalen Locke attests that this code is their original work and was written in compliance with the class Academic Integrity and Collaboration Policy found in the syllabus. 
 
 The Open method was by far the most important and the most challenging part of the program. 
 Although the general concept made sense to me, I struggle to implement it, especially within the given WeightedQuickUnionUF API
and the data structure I had to create for my own use. Making sure I threw exceptions was also something new 
for me, but at least that was an easy fix.
 */

public class Percolation {

    private boolean[][] arr;
    private WeightedQuickUnionUF UF;
    private WeightedQuickUnionUF percCheck;
    private int bottomNode;
    private int topNode;
    private int numOpen;
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n)
    {
        if (n <= 0)
        {
            throw new IllegalArgumentException();
        }
        arr = new boolean[n][n];
        UF = new WeightedQuickUnionUF(n * n + 2);
        percCheck = new WeightedQuickUnionUF(n * n + 2);
        bottomNode = n * n;
        topNode = (n * n) + 1;
        numOpen = 0;
    }

    private boolean inBounds(int row, int col)
    {
        if (row < 0 || row >= arr.length || col < 0 || col >= arr.length)
        {
            return false;
        }
        return true;
    }

    private int to1D(int row, int col)
    {
        return (arr.length * (row)) + (col);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col)
    {
        if (inBounds(row - 1, col - 1))
        {
            if (!arr[row - 1][col - 1])
            {
                arr[row - 1][col - 1] = true;
                numOpen++;
            }
            //top row
            if (row == 1)
            {
                UF.union(to1D(row - 1, col - 1), topNode);
                percCheck.union(to1D(row - 1, col - 1), topNode);
            }
            if (row == arr.length)
            {
                percCheck.union(to1D(row - 1, col - 1), bottomNode);
            }
            // right
            if (inBounds(row - 1, col) && arr[row - 1][col])
            {
                UF.union(to1D(row - 1, col - 1), to1D(row - 1, col));
                percCheck.union(to1D(row - 1, col - 1), to1D(row - 1, col));
            }
            // left
            if (inBounds(row - 1, col - 2) && arr[row - 1][col - 2])
            {
                UF.union(to1D(row - 1, col - 1), to1D(row - 1, col - 2));
                percCheck.union(to1D(row - 1, col - 1), to1D(row - 1, col - 2));
            }
            // down
            if (inBounds(row, col - 1) && arr[row][col - 1])
            {
                UF.union(to1D(row - 1, col - 1), to1D(row, col - 1));
                percCheck.union(to1D(row - 1, col - 1), to1D(row, col - 1));
            }
            // up
            if (inBounds(row - 2, col - 1) && arr[row - 2][col - 1])
            {
                UF.union(to1D(row - 1, col - 1), to1D(row - 2, col - 1));
                percCheck.union(to1D(row - 1, col - 1), to1D(row - 2, col - 1));
            }
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col)
    {
        if (inBounds(row - 1, col - 1))
        {
            return arr[row - 1][col - 1];
        }
        return false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col)
    {
        if (inBounds(row - 1, col -1))
        {
            return (UF.find(to1D(row - 1, col - 1)) == UF.find(topNode));
        }
        else
        {
            return false;
        }
    }

    // returns the number of open sites
    public int numberOfOpenSites()
    {
        return numOpen;
    }

    // does the system percolate?
    public boolean percolates()
    {
        return (percCheck.find(topNode) == percCheck.find(bottomNode));
    }

    // test client (optional)
    public static void main(String[] args)
    {
    }
}
