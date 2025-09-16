import java.util.Iterator;

/**
 * @author Jalen Locke attests that this code is their original work and was written in compliance with the class Academic Integrity and Collaboration Policy found in the syllabus. 
 
 The hardest part of the code is probably what I'm most proud of. I figured out the algorithm for 
 randomized queues with an array all on my own and then with a little help from my teammates, I was
 able to figure out how I needed to then proceed implementing that code into the iterator
 */


public class RandomizedQueue<Item> implements Iterable<Item> {

    private int arrSize;
    private int numItems;
    private Item[] arr;
    // construct an empty randomized queue
    public RandomizedQueue()
    {
        arrSize = 8;
        arr = (Item[]) new Object[arrSize];
        numItems = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty()
    {
        return (numItems == 0);
    }

    // return the number of items on the randomized queue
    public int size()
    {
        return numItems;
    }

    // add the item
    public void enqueue(Item item)
    {
        if (item == null) throw new IllegalArgumentException();
        if(numItems == arrSize)
        {
            Item[] newArr = (Item[]) new Object[arrSize * 2];
            for (int i = 0; i < arrSize; i++)
            {
                newArr[i] = arr[i];
            }
            arrSize *= 2;
            arr = newArr;
        }
        arr[numItems] = item;
        numItems++;
    }

    // remove and return a random item

    public Item dequeue()
    {
        if (numItems == 0) throw new java.util.NoSuchElementException();
        int randIndex =  (int) (Math.random() * numItems);
        Item item = arr[randIndex];
        // Swap last item to fill empty spot
        arr[randIndex] = arr[numItems - 1];
        arr[numItems - 1] = null;
        numItems --;

        if (numItems >= 4 && numItems <= arrSize / 4)
        {
            arrSize /= 4;
            Item[] newArr = (Item[]) new Object[arrSize];
            for (int i = 0; i < arrSize; i++)
            {
                newArr[i] = arr[i];
            }
            arr = newArr;
        }

        return item;
    }
    

    // return a random item (but do not remove it)

    public Item sample()
    {
        if (numItems == 0) throw new java.util.NoSuchElementException();
        return arr[(int) (Math.random() * numItems)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator()
    {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>
    {
        private Item[] iteratorArr = (Item[]) new Object[arrSize];
        private int current = numItems - 1;

        public boolean hasNext()
        {
            return (current >= 0);
        }
        public Item next()
        {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            int randIndex =  (int) (Math.random() * (current + 1));
            Item item = arr[randIndex];
            // Swap last item to fill empty spot
            arr[randIndex] = arr[current];
            arr[current] = null;
            current --;

            return item;
        } 
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args)
    {

    }

}