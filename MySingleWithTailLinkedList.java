package project3;

import java.io.Serializable;
import java.util.Random;

public class MySingleWithTailLinkedList implements Serializable
{
    private Node top;
    private Node tail;

    public MySingleWithTailLinkedList() {
        top = tail = null;
    }

    // This method has been provided and you are not permitted to modify
    public int size() {
        if (top == null)
            return 0;

        int total = 0;
        Node temp = top;
        while (temp.getNext() != null) {
            total++;
            temp = temp.getNext();
        }

        if (temp != tail)
            throw new RuntimeException("Tail is not pointing at the end of the list");
        else
            total++;

        return total;
    }

    // This method has been provided and you are not permitted to modify
    public void clear () {
        Random rand = new Random(13);
        while (size() > 0) {
            int number = rand.nextInt(size());
            remove(number);
        }
    }

    /********************************************************************************************
     *
     *    Your task is to complete this method.
     *
     *
     *
     * @param rental the unit begin rented
     */
    public void add(Rental rental) {
        Node temp = top;

        // no list
        if (top == null) {
            tail = top = new Node(rental, null);
            return;
        }

        // rental is a Game, and rental goes on top
        if (rental instanceof Game && top.getData().dueBack.after(rental.dueBack)) {
            top = new Node(rental, top);
            return;
        }

        //  more code goes here.

        return;

    }

    public Rental remove(int index) {
    	
    	//copy-pasted from lab, will probably need to be changed somewhat
    	if(index < 0 || index >= size() || top == null) {
    		throw new IllegalArgumentException();
    	}
    	else if(index == 0) {
    		if(top == null) {
        		throw new IllegalArgumentException();
        	}
        	else {
        		Rental data = top.getData();
        		top = top.getNext();
        		return data;
        	}
    	}
    	else {
    		int currentIndex = 0;
    		Node current = top;
    		while(currentIndex < index - 1) {
    			current = current.getNext();
    			currentIndex++;
    		}
    		Rental data = current.getNext().getData();
    		current.setNext(current.getNext().getNext());
    		return data;
    	}
    }

    public Rental get(int index) {
    	
    	//copy-pasted from lab with a few changes
    	//will probably need to be changed more
    	if(index < 0 || index >= size()) {
    		throw new IllegalArgumentException();
    	}
    	else if(top == null) {
    		return null;
    	}
    	else if(index == 0) {
    		return top.getData();
    	}
    	else {
    		int currentIndex = 0;
    		Node current = top;
    		while(currentIndex < index) {
    			current = current.getNext();
    			currentIndex++;
    		}
    		return current.getData();
    	}
//        if (top == null)
//            return null;
//
//        return top.getData(); // this line will need to be changed
         }

    public void display() {
        Node temp = top;
        while (temp != null) {
            System.out.println(temp.getData());
            temp = temp.getNext();
        }
    }

    @Override
    public String toString() {
        return "LL {" +
                "top=" + top +
                ", size=" + size() +
                '}';
    }
}

