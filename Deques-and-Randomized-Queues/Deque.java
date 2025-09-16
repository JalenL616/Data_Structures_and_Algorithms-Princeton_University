import java.util.Iterator;

/**
 * @author Jalen Locke attests that this code is their original work and was written in compliance with the class Academic Integrity and Collaboration Policy found in the syllabus. 
 
 The hardest part of coding the deque was making the code robust enough to handle edge cases. Although
 firstNode and lastNode are declared at the creation of the class, keeping track of their values when
 there was zero, one, or two values in the deque quickly got messy and led to many bugs.
 */

public class Deque<Item> implements Iterable<Item> {

    private Node firstNode;
    private Node lastNode;
    private int size;

    // construct an empty deque
    public Deque()
    {
        size = 0;
    }

    private class Node
    {
        Item item;
        Node next;
        Node previous;
    }

    // is the deque empty?
    public boolean isEmpty()
    {
        return (size == 0);
    }

    // return the number of items on the deque
    public int size()
    {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item)
    {
        if (item == null) throw new IllegalArgumentException();
        Node newFirst = new Node();
        // Make the old first node the new first's next
        newFirst.next = firstNode;

        newFirst.item = item;

        if (size > 0)
        {
            firstNode.previous = newFirst;
        }

        // Make the firstNode our new Node
        firstNode = newFirst;

        size++;

        if (size == 1)
        {
            lastNode = firstNode;
        }
    }

    // add the item to the back
    public void addLast(Item item)
    {
        if (item == null) throw new IllegalArgumentException();
        Node newLast = new Node();
        newLast.previous = lastNode;
        // Make the old last node's next the new last node
        if (size > 0)
        {
            lastNode.next = newLast;
        }

        newLast.item = item;

        // Make the lastNode our new Node
        lastNode = newLast;

        size++;

        if (size == 1)
        {
            firstNode = lastNode;
        }
    }

    // remove and return the item from the front
    public Item removeFirst()
    {
        if (firstNode == null) throw new java.util.NoSuchElementException();
        Item item = firstNode.item;
        
        if (size > 1)
        {
            // Replace the first node
            Node newFirst = firstNode.next;
            firstNode = newFirst;

            firstNode.previous = null;
        }
        else
        {
            firstNode = null;
            lastNode = null;
        }
        
        size--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast()
    {
        if (lastNode == null) throw new java.util.NoSuchElementException();
        
        Item item = lastNode.item;

        if (size > 1)
        {
            lastNode = lastNode.previous;
            
            lastNode.next = null;
        }
        else
        {
            firstNode = null;
            lastNode = null;
        }
        size--;

        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator()
    {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>
    {
        private Node current = firstNode;

        public boolean hasNext()
        {
            return (current != null);
        }
        public Item next()
        {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            Item item = current.item;
            current = current.next;
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