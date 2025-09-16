/**
 * @author Jalen Locke attests that this code is their original work and was written in compliance with the class Academic Integrity and Collaboration Policy found in the syllabus. 
 
    I had tons of problems here, but what I found most interesting was getting to use multiple new Algs4 APIs
    BreadthFirstDirectedPaths along with Digraph required lots of documentation look ups and I felt like an actual programmer
    Likewise, reading horribly vague and unhelpful specs from Coursera is always a treat, and knowing the actual meaning of
    what is outlined in plain English now, this assignment seems so much easier. Translating english to code and vice versa must be an acquired skill
 */
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;

public class SAP {

    private final Digraph DiG;
    private final int V;
    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G)
    {
        DiG = G;
        V = G.V();
    }
 
    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w)
    {
        BreadthFirstDirectedPaths vBFDP = new BreadthFirstDirectedPaths(DiG, v);
        BreadthFirstDirectedPaths wBFDP = new BreadthFirstDirectedPaths(DiG, w);
        int minDist = Integer.MAX_VALUE;
        for (int i = 0; i < V; i++)
        {
            if (vBFDP.hasPathTo(i) && wBFDP.hasPathTo(i))
            {
                if ((vBFDP.distTo(i) + wBFDP.distTo(i)) < minDist)
                {
                    minDist = (vBFDP.distTo(i) + wBFDP.distTo(i));
                }
            }
        }
        if (minDist == Integer.MAX_VALUE)
        {
            return -1;
        }
        return minDist;
    }
 
    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w)
    {
        BreadthFirstDirectedPaths vBFDP = new BreadthFirstDirectedPaths(DiG, v);
        BreadthFirstDirectedPaths wBFDP = new BreadthFirstDirectedPaths(DiG, w);
        int minDist = Integer.MAX_VALUE;
        int shortestAncestor = -1;
        for (int i = 0; i < V; i++)
        {
            if (vBFDP.hasPathTo(i) && wBFDP.hasPathTo(i))
            {
                if ((vBFDP.distTo(i) + wBFDP.distTo(i)) < minDist)
                {
                    minDist = (vBFDP.distTo(i) + wBFDP.distTo(i));
                    shortestAncestor = i;
                }
            }
        }
        if (minDist == Integer.MAX_VALUE)
        {
            return -1;
        }
        return shortestAncestor;
    }
 
    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w)
    {
        BreadthFirstDirectedPaths vBFDP = new BreadthFirstDirectedPaths(DiG, v);
        BreadthFirstDirectedPaths wBFDP = new BreadthFirstDirectedPaths(DiG, w);
        int minDist = Integer.MAX_VALUE;
        for (int i = 0; i < V; i++)
        {
            if (vBFDP.hasPathTo(i) && wBFDP.hasPathTo(i))
            {
                if ((vBFDP.distTo(i) + wBFDP.distTo(i)) < minDist)
                {
                    minDist = (vBFDP.distTo(i) + wBFDP.distTo(i));
                }
            }
        }
        if (minDist == Integer.MAX_VALUE)
        {
            return -1;
        }
        return minDist;
    }
 
    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w)
    {
        BreadthFirstDirectedPaths vBFDP = new BreadthFirstDirectedPaths(DiG, v);
        BreadthFirstDirectedPaths wBFDP = new BreadthFirstDirectedPaths(DiG, w);
        int minDist = Integer.MAX_VALUE;
        int shortestAncestor = -1;
        for (int i = 0; i < V; i++)
        {
            if (vBFDP.hasPathTo(i) && wBFDP.hasPathTo(i))
            {
                if ((vBFDP.distTo(i) + wBFDP.distTo(i)) < minDist)
                {
                    minDist = (vBFDP.distTo(i) + wBFDP.distTo(i));
                    shortestAncestor = i;
                }
            }
        }
        if (minDist == Integer.MAX_VALUE)
        {
            return -1;
        }
        return shortestAncestor;
    }
 
    /*
    // do unit testing of this class
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length   = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
     */
 }