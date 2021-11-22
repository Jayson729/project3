/*****************************************************************
Class with logic required for a MySingleWithTailLinkedList

@author Jayson Willey, Jacob Avalos
@version Fall 2021
 *****************************************************************/
package project3;

import java.io.Serializable;
import java.util.Random;

public class MySingleWithTailLinkedList implements Serializable
{
	/** Node at the top of the list */
	private Node top;

	/** Node at the end of the list */
	private Node tail;

	/********************************************************************************************
	 * Default constructor, sets both top and tail to null
	 * 
	 */
	public MySingleWithTailLinkedList() {
		top = tail = null;

	}

	// This method has been provided and you are not permitted to modify
	/********************************************************************************************
	 * returns the current size of the list
	 * 
	 * @return size of list
	 * @throws RuntimeException when tail is not pointing at the end of the list
	 */
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
	/********************************************************************************************
	 * Clears the list using the remove method
	 * 
	 */
	public void clear () {
		Random rand = new Random(13);
		while (size() > 0) {
			int number = rand.nextInt(size());
			remove(number);
		}
	}

	/********************************************************************************************
	 * Adds a Rental to the store sorted by due date. 
	 * If due dates are equal it then sorts by name.
	 *
	 * @param rental the rental being added
	 */
	public void add(Rental rental) {

		//if list is empty
		if (top == null) {
			tail = top = new Node(rental, null);
			return;
		}

		//sets top to rental if rental is due before top
		if(rental instanceof Game && 
				rental.dueBack.before(top.getData().dueBack)) {
			top = new Node(rental, top);
			return;
		}

		//if rental's due date is the same as top, sort by name
		if(rental instanceof Game && 
				rental.dueBack.equals(top.getData().dueBack)) {

			//if rental's name of renter is before or equal to top's, set top
			if(rental.nameOfRenter.compareTo(top.getData().nameOfRenter) <= 0) {
				top = new Node(rental, top);
				return;
			}
		}

		Node current = top;
		while(current.getNext() != null) {
			if(rental instanceof Game && 
					current.getNext().getData() instanceof Console) {
				
				//if rental is the last game, break the loop at the last game
				break;
			}
			else if(rental instanceof Console && 
					current.getNext().getData() instanceof Game) {
				
				//if rental is a console, set current to last game
				current = current.getNext();
			}
			else if(rental.dueBack.after(current.getNext().getData().dueBack)) {
				current = current.getNext();
			}
			else if(rental.dueBack.equals(current.getNext().getData().dueBack) && 
					rental.nameOfRenter.compareTo(
							current.getNext().getData().nameOfRenter) > 0) {
				
				//if due dates are equal, sort based on name
				current = current.getNext();
			}
			else {
				break;
			}
		}

		//insert after current
		Node newNode = new Node(rental, current.getNext());
		if(current.getNext() == null) {
			tail = newNode;
		}
		current.setNext(newNode);
	}

	/********************************************************************************************
	 * Removes a node at a specified index
	 * 
	 * @param index the index of the node being removed
	 * @return A Rental containing the data of the Node that was removed
	 * @throws IllegalArgumentException when index is out of range or when the list is empty
	 */
	public Rental remove(int index) {
		if(index < 0 || index >= size() || top == null) {
			throw new IllegalArgumentException();
		}
		else if(index == 0) {

			//sets new top
			Rental data = top.getData();
			top = top.getNext();
			return data;
		}
		else {
			int currentIndex = 0;
			Node current = top;

			//current is at index - 1
			while(currentIndex < index - 1) {
				current = current.getNext();
				currentIndex++;
			}
			Rental data = current.getNext().getData();

			//if the space after index is null, index is the tail
			if(current.getNext().getNext() != null) {
				current.setNext(current.getNext().getNext());
			}
			else {
				current.setNext(null);
				tail = current;
			}

			return data;
		}
	}

	/********************************************************************************************
	 * Returns the Rental at a given index
	 * 
	 * @param index the index of the rental
	 * @return The Rental at the given index
	 * @throws IllegalArgumentException when index is out of range
	 */
	public Rental get(int index) {
		if(index < 0 || index >= size()) {
			throw new IllegalArgumentException();
		}
		else {
			int currentIndex = 0;
			Node current = top;

			//current is at index
			while(currentIndex < index) {
				current = current.getNext();
				currentIndex++;
			}
			return current.getData();
		}
	}

	/********************************************************************************************
	 * Displays the list of rentals in the console
	 * 
	 */
	public void display() {
		Node temp = top;
		while (temp != null) {
			System.out.println(temp.getData());
			temp = temp.getNext();
		}
	}

	/********************************************************************************************
	 * toString method for MySingleWithTailLinkedList
	 * 
	 * @return String A string that represents a MySingleWithTailLinkedList object
	 */
	@Override
	public String toString() {
		return "LL {" +
				"top=" + top +
				", size=" + size() +
				'}';
	}
}