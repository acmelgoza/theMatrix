package com.mtrx;

/**
 * Linked list for Sale class
 * 
 * @author amelgoza and ppande
 *
 */
public class SaleList {

	@Override
	public String toString() {
		return "SaleList [head=" + head + ", size=" + size + "]";
	}

	private Node head;
	private int size;

	/**
	 * Creates list
	 */
	public SaleList() {
		this.head = null;
		this.size = 0;
	}

	/**
	 * Returns size of list
	 * 
	 * @return
	 */
	public int getSize() {
		return this.size;
	}

	/**
	 * Clears list
	 */
	public void clear() {
		this.head = null;
		this.size = 0;
	}

	/*
	 * Adds element to start
	 */
	public void addToStart(Sale element) {
		Node newhead = new Node(element);
		newhead.setNext(this.head);
		this.head = newhead;
		this.size++;
	}

	/*
	 * Adds element to end
	 */
	public void addToEnd(Sale t) {
		this.addAtIndex(t, getSize());
	}

	/**
	 * Adds specific element
	 * 
	 * @param element
	 * @param index
	 */
	public void addAtIndex(Sale element, int index) {
		if (index < 0 || index > this.size) {

		}
		if (index == 0) {
			this.addToStart(element);
		} else {
			Node prev = this.head;
			for (int i = 0; i < index - 1; i++) {
				prev = prev.getNext();

			}
		}

	}

	/**
	 * Removes specific node
	 * 
	 * @param index
	 */
	public void remove(int index) {
		if (index < 0 || index >= this.size) {
			throw new IllegalArgumentException("Index: " + index + " is out of bounds!");
		}
		Node previous = this.getNode(index - 1);
		Node current = previous.getNext();

		if (index == 0) {
			(head) = head.getNext();
		} else {
			previous.setNext(current.getNext());
		}
		size = size - 1;
	}

	/**
	 * Returns specific element
	 * 
	 * @param index
	 * @return
	 */
	public Sale getElement(int index) {
		Node current = head;
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}
		return current.getItem();
	}

	/**
	 * Returns specific node
	 * 
	 * @param index
	 * @return
	 */
	private Node getNode(int index) {
		Node current = this.head;
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}
		return current;
	}

	/**
	 * Inserts node in alphabetical order
	 * 
	 * @param a
	 */
	public void insertName(Node a) {
		if (head == null || Sort.compareName(head.getItem(), a.getItem()) >= 0) {
			a.next = head;
			head = a;
			size++;
			return;
		}
		Node curr = head;
		while (curr.next != null && Sort.compareName(curr.next.getItem(), a.getItem()) < 0) {
			curr = curr.getNext();
		}
		a.next = curr.next;
		curr.next = a;
		size++;
		return;
	}

	/**
	 * Inserts node in order by total price
	 * 
	 * @param a
	 */
	public void insertTotal(Node a) {
		if (head == null || Sort.compareTotal(head.getItem(), a.getItem()) <= 0) {
			a.next = head;
			head = a;
			size++;
			return;
		}
		Node curr = head;
		while (curr.next != null && Sort.compareTotal(curr.next.getItem(), a.getItem()) > 0) {
			curr = curr.next;
		}
		a.next = curr.next;
		curr.next = a;
		size++;
		return;
	}

}
