import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * @author Jalen Locke attests that this code is their original work and was written in compliance with the class Academic Integrity and Collaboration Policy found in the syllabus. 
 
 The hardest part of this file came in understanding how to implement the Percolation object from the previous file.
 It was tricky remembering which methods I had access to, and then figuring out which one would fit my need.
 In the end, most of the hard work was already done in Percolation.java, so once I figured out how I'd create my Percolation
 objects then randomly open spots, it went pretty smoothly
 */


public class PercolationStats {

    private double len;
    private double[] results;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials)
    {
        if (n <= 0 || trials <= 0)
        {
            throw new IllegalArgumentException();
        }
        results = new double[trials];
        len = (double) n;
        for (int i = 0; i < trials; i++)
        {
            Percolation perc = new Percolation(n);
            results[i] = percolate(perc);
        }
    }

    private double percolate(Percolation obj)
    {
        //for (int i = 0; i < 30; i++)
        while (!obj.percolates())
        {
            int row = StdRandom.uniformInt((int) len) + 1;
            int col = StdRandom.uniformInt((int) len) + 1;
            obj.open(row, col);
        }
        return (double) obj.numberOfOpenSites() / (len * len);
    }

    // sample mean of percolation threshold
    public double mean()
    {
        return StdStats.mean(results);
    }

    // sample standard deviation of percolation threshold
    public double stddev()
    {
        return StdStats.stddev(results);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo()
    {
        return mean() - ((1.96 * stddev()) / Math.sqrt(len));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi()
    {
        return mean() + ((1.96 * stddev()) / Math.sqrt(len));
    }

   // test client (see below)
    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, trials);

        StdOut.println("mean                    = " + stats.mean());
        StdOut.println("stddev                  = " + stats.stddev());
        StdOut.println("95% confidence interval = [" + stats.confidenceLo() + ", "
                + stats.confidenceHi() + "]");
    }

}