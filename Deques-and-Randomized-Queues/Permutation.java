import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;

/**
 * @author Jalen Locke attests that this code is their original work and was written in compliance with the class Academic Integrity and Collaboration Policy found in the syllabus. 
 
 Honestly, the only real challenge here was understanding the prompt. I did have a major fumble though
 when I accidentally created my iterator in the for loop. That didn't make the autograder very happy.
 */


public class Permutation {

    public static void main(String[] args)
    {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
        int k = Integer.parseInt(args[0]);
        while (!StdIn.isEmpty()) {
            randomizedQueue.enqueue(StdIn.readString());
        }
        Iterator<String> iterator = randomizedQueue.iterator();
        for (int i = 0; i < k; i++)
        {
            StdOut.println(iterator.next());
        }
    }

 }