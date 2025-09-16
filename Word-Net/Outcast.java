
public class Outcast {
    private final WordNet w;
    // constructor takes a WordNet object
    public Outcast(WordNet wordnet)    
    {
        w = wordnet;
    }
    // given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns)
    {
        int maxDistance = 0;
        String outcast = "";
        for (String noun1 : nouns)
        {
            int totalDist = 0;
            for (String noun2 : nouns)
            {
                int dist = w.distance(noun1, noun2);
                totalDist += dist;
            }
            if (totalDist > maxDistance)
            {
                maxDistance = totalDist;
                outcast = noun1;
            }
        }
        return outcast;
    }
    /*
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