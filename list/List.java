/**
 * An implementation of the Java List class. This class implements the usual add(),
 * size(), isEmpty(), contains(), get(), and remove().  
 * 
 */
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class List<T> implements Iterable<T> {
    // the list that stores the elements
    private T[] elements;
    // the number of elements in the list
    private int size;
    // the maximum size of the list
    private int capacity;

    public List() {
        // default size is 10
        elements = (T[]) new Object[10];
        capacity = 10;
    }

    public List(int size) {
        elements = (T[]) new Object[size];
        this.capacity = size;
    }

    /**
     * Return the number of elements currently in the list
     * 
     * @return the number of elements in the list
     */
    public int size() {
        return size;
    }

    /**
     * Return the maximum number of elementsthis list can hold
     * 
     * @return the maximum number of elements this list can hold
     */
    public int capacity() {
        return capacity;
    }

    /**
     * Return whether this list contains no elements
     * 
     * @return true if the list is empty, and false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns whether the list contains the specified element
     * 
     * @param element the element to look for in the list
     * 
     * @return true if the element is in the list and false otherwise
     */
    public boolean contains(T element) {
        // iterate over the list, checking for the element to look for
        for (int i = 0; i < size; i++) {
            if (elements[i] == element) {
                return true;
            }
        }

        // if this is reached the element is not in the list
        return false;
    }

    /**
     * Insert the specified element into the end of the list, if the list
     * reaches 75% capacity, double the size of the list
     * 
     * @return true if the element was added to the list and false if it
     * was not
     */
    public boolean add(T element) {
        // if the list is full, do nothing and return false
        if (size == capacity) {
            return false;
        }

        elements[size] = element;
        size++;

        return true;
    }

    /**
     * Insert the specified element into the specified index of the list,
     * if the list reaches 75% capacity, double the size of the list
     * 
     * @param index the index to insert the element into 
     * @param element the element to insert into the list
     * 
     * @return true if the element was added to the list and false if it
     * was not
     */
    public boolean add(int index, T element) {
        // if the list is full, do nothing and return false
        if (size == capacity) {
            return false;
        }

        elements[size] = element;
        size++;

        return true;
    }

    /**
     * Get the element at the specified index
     * 
     * @param index The index to get the element from
     * 
     * @return The element at the specified index
     */
    public T get(int index) {
        return elements[index];
    }

    /**
     * Remove the element at the specified index, if the capacity falls below
     * 25%, the size of the list is reduced to half the original size
     * 
     * @param index the index of the element to remove
     */
    public void remove(int index) throws IndexOutOfBoundsException {
        // if index is out of bounds throw an exception
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException();
        }
    
        // shift all the elements after the deleted element towards the front
        for (int i = index; i < size; i++) {
            if (i + 1 < size) {
                elements[i] = elements[i + 1];
                elements[i + 1] = null;
            }
        }

        size--;
    }

    /**
     * Return a ListIterator for this list
     * 
     * @param list the list to iterate over
     * 
     * @return the ListItertor to iterate over this list
     */
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    /**
     * Return the list as a string
     * 
     * @return a string representation of this list
     */
    @Override
    public String toString() {
        String s = "[";
        // add all items in the list to the string s
        for (int i = 0; i < capacity; i++) {
            s += elements[i] + ", ";
        }

        // remove the last ', ' from the string and add the closing ']'
        if (s.length() > 3) {
            s = s.substring(0, s.length() - 2);
        }
        
        s += "]";

        return s;
    }

    public static void main(String[] args) {
        List<Integer> l = new List<>(5);
        Random rand = new Random();
        System.out.println("Is a new list empty? " + l.isEmpty());
        System.out.println("Adding to list");
        for (int i = 0; i < 5; i++) {
            l.add(rand.nextInt());
            System.out.print("Size = " + l.size() + " ");
            System.out.println(l);
        }

        System.out.println("Is the full list empty? " + l.isEmpty());
        System.out.println("Size = " + (l.size() - 1));
        l.remove(l.size() - 1);
        System.out.println("Removed last element " + l);
        l.add(l.size(), rand.nextInt());
        System.out.println("Testing contain (true) " + l.contains(l.get(0)));
        System.out.println("Testing contain (false) " + l.contains(0));
        Iterator<Integer> iter = l.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }

    class ListIterator extends List<T> implements Iterator<T> {
        private int index;  // index of the current element

        /**
         * Create a ListIterator for the given list
         * 
         * @param list the list to iterate through
         */
        public ListIterator() {
            index = 0;  // index of the current element
        }

        /**
         * Whether the list as another element after the current element
         * 
         * @return true if there is an element after the current element,
         * false otherwise
         */
        public boolean hasNext() {
            return index < List.this.size() - 1;
        }

        /**
         * Return the next element in the list
         * 
         * @return the next element in the list
         */
        public T next() throws NoSuchElementException, IndexOutOfBoundsException {
            try {
                T next = List.this.get(index);
                index++;
                
                return next;
            }

            catch (NoSuchElementException e) {
                throw new NoSuchElementException();
            }

            catch (IndexOutOfBoundsException e) {
                throw new IndexOutOfBoundsException();
            }
        }
    }
}