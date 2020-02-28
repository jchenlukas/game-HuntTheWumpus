/**
 * Jiyao Chen
 * CS 231 Spring
 * Project 9
 * LinkedList.java
 * implements a generic singly-linked list with an Iterator
 */

import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;

public class LinkedList<T> implements Iterable<T> {

    private Node head;
    private int num;

    /**
     * constructor that initializes the fields so it is an empty list
     */
    public LinkedList() {
        head = null;
        num = 0;
    }

    /**
     * empties the list
     */
    public void clear() {
        head = null;
        num = 0;
    }

    /**
     * returns the size of the list
     */
    public int size() {
        return num;
    }

    /*
     * inserts the item at the beginning of the list
     */
    public void addFirst(T item) {
        Node newnode = new Node(item);
        
        newnode.setNext(head);
        head = newnode;
        
        num++;
    }

    /**
     * appends the item at the end of the list
     */
    public void addLast(T item) {
        Node newnode = new Node(item);

        if(num == 0) {
            head = newnode;
        }
        else {
            Node temp = head;
            while(temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(newnode);
        }
        num++;
    }

    /**
     * inserts the item at the specified poistion in the list
     */
    public void add(int index, T item) {
        Node temp, newnode;
		if( index < 0 || index > num ) {    //check if it is out of bounds
			throw new IndexOutOfBoundsException();
		}

		if(index == 0) {
            addFirst(item);
		}
		else {
            temp = head;
            newnode = new Node(item);
			
			for(int i = 0;i < index - 1; i++) {
				temp = temp.getNext();
			}
			newnode.setNext(temp.getNext());
			temp.setNext(newnode);
			num++;
		}
    }
    
    /**
     * removes the item at the specified position in the list
     */
    public T remove (int index) {
        Node temp, remove;
		
		if( index < 0 || index >= num) {
			throw new IndexOutOfBoundsException();
		}
		
		if(index == 0) {
            remove = head;
            head = head.getNext();
            num--;
            return remove.getThing();
		}
		else {  // general case
            temp = head;

            for(int i = 0;i < index - 1; i++) {
                temp = temp.getNext();
            }
            remove = temp.getNext();
            temp.setNext(remove.getNext());
            num--;
        
            return remove.getThing();
        }
    }

    /**
     * returns an ArrayList of the list contents in order
     */
    public ArrayList<T> toArrayList() {
        ArrayList<T> currlist = new ArrayList<T>();
        Node temp = head;
        for(T element : currlist) {
            currlist.add(temp.getThing());
            temp = temp.getNext();
        }

        return currlist;

    }

    /**
     * returns an ArrayList of the list contents in shuffled order
     */
    public ArrayList<T> toShuffledList() {
        ArrayList<T> currlist = new ArrayList<T>();
        Node temp = head;
        for(int i = 0; i < num; i++) {
            currlist.add(temp.getThing());
            temp = temp.getNext();
        }
        Collections.shuffle(currlist);

        return currlist;
    }

    private class Node {

        Node next;
        T choice;

        /**
         * a constructor that initializes next to null and the container field to item
         */
        public Node(T item) {
            next = null;
            choice = item;
        }

        /**
         * returns the value of the container field
         */
        public T getThing() {
            return choice;
        }

        /**
         * sets next to the given node
         */
        public void setNext(Node n) {
            next = n;
        }

        /**
         * returns the next field
         */
        public Node getNext() {
            return next;
        }
    }
 
    /**
     * Return a new LLIterator pointing to the head of the list
     */
    public Iterator<T> iterator() {
        return new LLIterator(head);
    }

    private class LLIterator implements Iterator<T> {
        Node nextnode;

        /**
         * creates an LLIterator given the head of a list
         */
        public LLIterator(Node head) {
            nextnode = head;
        }

        /**
         * returns true if there are still values to traverse
         */
        public boolean hasNext() {
            return nextnode != null;
        }

        /**
         * returns the next item in the list, which is the item contained in the current node
         */
        public T next() {
            if (hasNext()) {
				T chosen = nextnode.getThing();
				nextnode = nextnode.getNext();
				return chosen;
			}

			return null;
        }

        /**
         * Implementing this function is optional for an Iterator
         */
        public void remove() {

        }
    }
}