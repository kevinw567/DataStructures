/**
 * A self implemented List library mimicing the List library of the Java
 * standard library. 
 */
import java.util.Iterator;
import java.util.Random;

public class List<T> {
    // the list that stores the elements
    private T[] elements;
    // the number of elements in the list
    private int size;
    // the maximum size of the list
    private int capacity;

    public List() {
        // default size is 10
        capacity = 10;
        elements = (T[]) new Object[10];
    }

    public List(int size) {
        elements = (T[]) new Object[10];
        this.capacity = size;
    }
    
    public List(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            elements[i] = list.elements[i];
        }
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
     * 
     * @return the size of the list
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
        for (int i = 0; i < size; i++) {
            if (elements[i] == element) {
                return true;
            }
        }

        return false;
    }

    /**
     * Insert the specified element into the end of the list, if the list
     * reaches 75% capacity, double the size of the list
     * 
     * @return void
     */
    public void add(T element) {
        if (size + 1 >= capacity * 0.75) {
            resize(capacity * 2);
        }

        elements[size] = element;
        size++;
    }

    /**
     * Insert the specified element into the specified index of the list,
     * if the list reaches 75% capacity, double the size of the list
     * 
     * @param index the index to insert the element into 
     * @param element the element to insert into the list
     * 
     * @return void
     */
    public void add(int index, T element) {
        if (size + 1 >= capacity * 0.75) {
            resize(capacity * 2);
        }

        elements[size] = element;
        size++;
    }

    /**
     * Get the element at the specified index
     * @param index The element index
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
    public void remove(int index) {
        for (int i = index; i < size; i++) {
            if (i + 1 < size) {
                elements[i] = elements[i + 1];
                elements[i + 1] = null;
            }
        }

        // if the size is 1/4 of the capacity, resize the capacity to half
        if (size <= capacity / 4) {
            capacity /= 2;
            resize(capacity / 2);
        }
    }

    /**
     * Resize the list to the current size and change the max capacity
     * 
     * @param newCap the new capacity of the list
     */
    private void resize(int newCap) {
        System.out.println("Newcap: " + newCap);
        T[] temp = (T[]) new Object[newCap];
        capacity = newCap;
        for (int i = 0; i < size; i++) {
            temp[i] = elements[i];
        }

        elements = temp;
    }

    /**
     * Return the list as a string
     * 
     * @return a string representation of this list
     */
    @Override
    public String toString() {
        String s = "";
        s += "[";
        // add all items in the list to the string s
        for (int i = 0; i < size; i++) {
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
        List<Integer> l = new List<>();
        System.out.println("Default size list: " + l.capacity);
        List<Integer> l1 = new List<>(11);
        System.out.println("Initialized size list: " + l1.capacity);
        Random rand = new Random();
        int loops = rand.nextInt(10);
        System.out.println("Loops: " + loops);
        for (int i = 0; i < loops; i++) {
            l.add(rand.nextInt());
            System.out.println("L = " + l);
            if (rand.nextInt() % 2 == 0) {
                System.out.println("L1 = " + l1);
                l1.add(rand.nextInt());
            }
        }

        System.out.println("List size: " + l.size());
        System.out.println(l);
        System.out.println("List1 size: " + l1.size());
        System.out.println(l1);
    }
}

class ListIterator<T> implements Iterator<T> {
    
    public ListIterator() {
        return;
    }

    public boolean hasNext() {
        return;
    }

    public T next() {
        return;
    }


}