package com.mtrx;

/**
 * Node class for SaleList
 * 
 * @author amelgoza and ppande
 *
 */
public class Node {

	Sale item;
	Node next;

	public Node(Sale item) {
		this.item = item;

	}

	public Sale getItem() {
		return item;
	}

	public void setItem(Sale item) {
		this.item = item;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public boolean hasNext() {
		return this.next != null;
	}

	@Override
	public String toString() {
		return this.item.toString();
	}
}
