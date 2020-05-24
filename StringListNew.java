// Author: Chris Fietkiewicz. Based on StringList.java by David Eck.
// Raja Hammad Mehmood
// This was a modification of the given code. This program demonstrate find, delete, insert
// functions of a Stringlist.
public class StringListNew {
	/**
	 * Internally, the list of strings is represented as a linked list of nodes
	 * belonging to the nested class Node. The strings in the list are stored in
	 * increasing order (using the order given by the compareTo() method from the
	 * string class, which is the same as alphabetical order if all the strings are
	 * made up of lower case letters).
	 */
	private static class Node {
		String item; // One of the items in the list
		Node next; // Pointer to the node containing the next item.
					// In the last node of the list, next is null.
	}

	private Node head; // A pointer to the first node in the linked list.
						// If the list is empty, the value is null.
	/* Used with the "find" method to recursively find an item. */

	private boolean findRecursive(String searchItem, Node currentNode) {

		if (currentNode == null) {
			return false;
		} else if (currentNode.item.equals(searchItem)) {
			return true;
		} else {
			return findRecursive(searchItem, currentNode.next);

		}

	}

	/**
	 * Searches the list for a specified item. (Note: for demonstration purposes,
	 * this method does not use the fact that the items in the list are ordered.)
	 * 
	 * @param searchItem the item that is to be searched for
	 * @return true if searchItem is one of the items in the list or false if
	 *         searchItem does not occur in the list.
	 */
	public boolean find(String searchItem) {
		System.out.println("Finding " + searchItem + "....");
		return findRecursive(searchItem, head);
	} // end find()

	/* Used with the "delete" method to recursively delete an item. */
	private boolean deleteRecursive(String deleteItem, Node currentNode) {
		if (currentNode.next.item.equals(deleteItem)) {
			currentNode.next = currentNode.next.next;
			return true;
		} else if (currentNode.next.item.compareTo(deleteItem) >= 0) {
			return false;
		} else {
			return deleteRecursive(deleteItem, currentNode.next);
		}
	}

	/**
	 * Delete a specified item from the list, if that item is present. If multiple
	 * copies of the item are present in the list, only the one that comes first in
	 * the list is deleted.
	 * 
	 * @param deleteItem the item to be deleted
	 * @return true if the item was found and deleted, or false if the item was not
	 *         in the list.
	 */
	public boolean delete(String deleteItem) {
		System.out.println("Deleting " + deleteItem + "...");
		if (head == null) {
			// The list is empty, so it certainly doesn't contain deleteItem.
			return false;
		} else if (head.item.equals(deleteItem)) { // The string is the first item of
//the list.  Remove it.
			head = head.next;
			return true;
		} else
			return deleteRecursive(deleteItem, head);
	} // end delete()

	/* Used with the "insert" method to recursively insert an item. */
	public void insertRecursive(Node newNode, Node currentNode) {
		if (currentNode.next == null) {
			currentNode.next = newNode;
		} else if (currentNode.next.item.compareTo(newNode.item) >= 0) {
			newNode.next = currentNode.next;
			currentNode.next = newNode;

		} else {
			insertRecursive(newNode, currentNode.next);
		}
	}

	/**
	 * Insert a specified item to the list, keeping the list in order.
	 * 
	 * @param insertItem the item that is to be inserted.
	 */
	public void insert(String insertItem) {
		Node newNode = new Node(); // A Node to contain the new item.
		newNode.item = insertItem; // (N.B. newNode.next is null.)
		if (head == null) // The new item is the first (and only) one in the
//list.
			head = newNode; // Set head to point to it.
		else
			insertRecursive(newNode, head);
	}

	/* Print all items in the list. */
	public void printList() {
		int count; // For counting elements in the list.
		Node runner; // For traversing the list.
		String[] elements; // An array to hold the list elements.

		// First, go through the list and count the number
		// of elements that it contains.

		count = 0;
		runner = head;
		while (runner != null) {
			count++;
			runner = runner.next;
		}

		// Create an array just large enough to hold all the
		// list elements. Go through the list again and
		// fill the array with elements from the list.

		elements = new String[count];
		runner = head;
		count = 0;
		while (runner != null) {
			elements[count] = runner.item;
			System.out.print(elements[count] + " ");
			count++;
			runner = runner.next;
		}
		System.out.println();

	} // end printList()

	public static void main(String[] args) {
		StringListNew myList = new StringListNew();
		System.out.println("Inserting 3 nodes...");
		myList.insert("apple");
		myList.insert("mango");
		myList.insert("orange");
		myList.printList();
		boolean flag = myList.find("orange");
		if (flag == true) {
			System.out.println("Found it!");
		} else {
			System.out.println("Not found");
		}

		boolean flag2 = myList.find("pineapple");
		if (flag2 == true) {
			System.out.println("Found it!");
		} else {
			System.out.println("Not found");
		}

		boolean flag3 = myList.delete("orange");
		if (flag3 == true) {
			System.out.println("Found it! and deleted");
		} else {
			System.out.println("Not found");
		}
		myList.printList();
		boolean flag4 = myList.delete("apple");
		if (flag4 == true) {
			System.out.println("Found it! and deleted");
		} else {
			System.out.println("Not found");
		}
		myList.printList();

	}
} // end class