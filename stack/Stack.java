/**
 * An implementation of the Java Stack from scratch. A stack is a data structure
 * that is based on the last-in-first-out (LIFO) concept. This class implements 
 * the usual push(), peek(), pop(), and isEmpty() methods. The stack starts with
 * the default capacity of 10 and grows as more elements are pushed on the stack
 * and shrinks to a minimum capacity of 10 as elements are popped from the stack.
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<T> implements Iterable<T> {
    private T[] elements;
    private int size;
    private int capacity;

    public Stack() {
        this.elements = (T[]) new Object[10];
        this.size = 0;
        this.capacity = 10;
    }

    /**
     * Add an element to the top of the stack
     * 
     * @param element the element to add onto the stack
     * Time Complexity: O(1)
     */
    public void push(T element) {
        if (size >= capacity * 0.75) {
            resize(capacity * 2);
        }

        elements[size] = element;
        size++;
    }

    /**
     * Return and remove the element on top of the stack, if the stack is empty,
     * return null
     * 
     * @return the element on top of the stack
     */
    public T pop() {
        // if the stack is empty return null
        if (size <= 0) {
            return null;
        }

        // if the stack is 1/4 full, shrink the capacity to 1/2 the current capacity if
        // it is a minimum of 10
        if (size <= capacity / 4 && capacity / 2 >= 10) {
            resize(capacity / 2);
        }

        T top = elements[size - 1];
        elements[size - 1] = null;
        size--;

        return top;
    }

    /**
     * Return the object on top of the stack (without removing it), if the stack is
     * empty return null
     * 
     * @return the object on top of the stack
     */
    public T peek() {
        // if the stack is empty, return null
        if (size <= 0) {
            return null;
        }

        return elements[size - 1];
    }

    /**
     * Return whether or not the stack is empty
     * 
     * @return true if the stack is empty and false if it is not
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Return a string representation of the stack
     * 
     * @return the string representation of the stack
     */
    public String toString() {
        String s = "--TOP--\n";
        for (int i = size - 1; i >= 0; i--) {
            s += i + "\n";
        }

        s += "--Bottom--";

        return s;
    }

    public Iterator<T> iterator() {
        return new StackIterator();
    } 

    /**
     * Resize the max capacity of the stack
     * 
     * @param newCap the new maximum capacity of the stack
     */
    private void resize(int newCap) {
        T[] temp = (T[]) new Object[newCap];
        for (int i = 0; i < size; i++) {
            temp[i] = elements[i];
        }

        elements = temp;
        capacity = newCap;
    }

    public static void main(String[] args) {
        Stack<String> s = new Stack<>();
        for (int i = 0; i < 10; i++) {
            s.push("Hello" + i);
        }
        
        for (String str : s) {
            System.out.println(str);
        }

        while (!s.isEmpty()) {
            System.out.println("Popped " + s.pop());
        }

        System.out.println(s);
    }

    class StackIterator extends Stack<T> implements Iterator<T> {
        private int index;

        public StackIterator() {
            index = Stack.this.size - 1;
        }

        public boolean hasNext() {
            return index >= 0;
        }

        public T next() throws NoSuchElementException {
            if (index < 0) {
                throw new NoSuchElementException();
            }

            T top = Stack.this.elements[index];
            index--;

            return top;
        }
    }
}
