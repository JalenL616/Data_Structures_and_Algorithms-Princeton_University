/**
 * @author Jalen Locke attests that this code is their original work and was written in compliance with the class Academic Integrity and Collaboration Policy found in the syllabus. 
 
    I found it quite elegant that you can actually use the hashmap both to check for nouns and use the iterables. 
    I'd initally had a separate nouns array, but that turned out to be even less efficient due to duplicates
    So it was a happy realization seeing that the hashmap served both purposes
 */

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import java.util.ArrayList;
import java.util.HashMap;

public class WordNet {

    private final ArrayList<String[]> synsetsArr;
    private Digraph G;
    private final SAP Sap;
    private final HashMap<String, ArrayList<Integer>> toVertex;
    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms)
    {
        toVertex = new HashMap<String, ArrayList<Integer>>();
        synsetsArr = new ArrayList<String[]>();
        loadSynsets(synsets);
        //sort alphabetically
        loadHypernyms(hypernyms);
        Sap = new SAP(G);
    }
 
    private void loadSynsets(String synsets)
    {
        In in = new In(synsets);
        String[] lines = in.readAllLines();
        for (String line : lines)
        {
            String[] fields = line.split("\\,");
            int id = Integer.parseInt(fields[0]);
 
            String[] nouns = fields[1].split(" ");
            for (String noun : nouns)
            {
                ArrayList<Integer> arr = toVertex.get(noun);
                if (arr != null)
                {
                    arr.add(id);
                }
                else
                {
                    ArrayList<Integer> newArr = new ArrayList<Integer>();
                    newArr.add(id);
                    toVertex.put(noun, newArr);
                }
            }
            synsetsArr.add(nouns);
        }
        G = new Digraph(synsetsArr.size());
    }

    private void loadHypernyms(String hypernyms)
    {
        In in = new In(hypernyms);
        String[] lines = in.readAllLines();
        for (String line : lines)
        {
            String[] fields = line.split(",");
            int id = Integer.parseInt(fields[0]);
 
            for (int i = 1; i < fields.length; i++)
            {
                G.addEdge(id, Integer.parseInt(fields[i]));
            }
        }
    }

    // returns all WordNet nouns
    public Iterable<String> nouns()
    {
        return toVertex.keySet();
    }
 
    // is the word a WordNet noun?
    public boolean isNoun(String word)
    {
        if (word == null) throw new IllegalArgumentException();
        return toVertex.containsKey(word);
    }
 
    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB)
    {
        return Sap.length(toVertex.get(nounA), toVertex.get(nounB));
    }
 
    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB)
    {
        String[] arr = synsetsArr.get(Sap.ancestor(toVertex.get(nounA), toVertex.get(nounB)));
        String output = "";
        for (String a : arr)
        {
            output += a + " ";
        }
        return output;
    }
 
    
 }