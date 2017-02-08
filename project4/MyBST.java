package project4;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Creates Binary Search Trees that have a variety of operations on it and that
 * 		can contain objects of all types that implement Comparable.
 * @author Ignacio Carrillo-Batalla
 *
 */
public class MyBST<E extends Comparable<E>> {
	protected Node<E> root;
	private boolean found;
	
	/**
	 * Constructs MyBST objects.
	 */
	public MyBST() {
	}
	
	/**
	 * Adds specified element to MyBST object.
	 * @param element element to be added
	 * @return true if element is successfully added, false otherwise
	 */
	public boolean add(E element) {
		found = false;
		root = recAdd(element, root);
		return found;
	}
	
	/**
	 * Along with add method, adds specified element to MyBST object.
	 * @param element element to be added
	 * @param root current root from which tree springs that element is to be added into
	 * @return root that references to new tree
	 */
	private Node<E> recAdd(E element, Node<E> root) {
		if (root == null) {
			//	insertion point found
			root = new Node<E>(element);
			found = true;
		}
		//	if element to be added is less than or equal to root
		else if (element.compareTo(root.getData()) <= 0)
			root.setLeft(recAdd(element, root.getLeft()));
		//	if element to be added is more than root
		else
			root.setRight(recAdd(element, root.getRight()));
		return root;
	}
	
	/** 
	 * Checks if specified object is in tree.
	 * @param o the object that will be checked if exists in tree
	 * @return true if BST contains specified object, false otherwise
	 * @throws ClassCastException if object is unsuccessfully casted
	 * @throws NullPointerException if object being compared to is null
	 */
	public boolean contains(Object o) throws ClassCastException, NullPointerException {
		return recContains(o, root);
	}
	
	/**
	 * Checks if specified object is in tree.
	 * @param element the object that will be checked if exists in tree
	 * @param root the current root whose tree is being operated on
	 * @return true if BST contains specified object, false otherwise
	 * @throws ClassCastException if object is unsuccessfully casted
	 * @throws NullPointerException if object being compared to is null
	 */
	@SuppressWarnings("unchecked")
	private boolean recContains(Object element, Node<E> root) throws ClassCastException, NullPointerException {
		if (root == null) return false;
		else if (root.getData().equals(element))
			//	does contain
			return true;
		//	if element is less than or equal to root
		else if (((E) element).compareTo(root.getData()) <= 0)
			return recContains(element, root.getLeft());
		//	if element is more than root
		else
			return recContains(element, root.getRight());
	}
	
	/**
	 * Returns first element in BST.
	 * @return first element in BST
	 */
	public E first() {
		Node<E> current = root;
		while (current.getLeft() != null)
			current = current.getLeft();
		return current.getData();
	}
	
	
	/** 
	 * Returns true if BST is empty.
	 * @return true if BST is empty, false otherwise
	 */
	public boolean isEmpty() {
		return (root == null);
	}
	
	/** 
	 * Returns Iterator object for MyBST object.
	 * @return Iterator object for BST
	 */
	public Iterator<E> iterator() {
		return new MyIterator();
	}
	
	/** 
	 * Returns last element in BST.
	 * @return last element in BST
	 */
	public E last() {
		Node<E> current = root;
		while (current.getRight() != null)
			current = current.getRight();
		return current.getData();
	}
	
	/**
	 * Removes specified element from BST
	 * @param element element to remove from BST
	 * @return true if element was successfully removed, false otherwise
	 */
	public boolean remove(E element) {
		root = recRemove(element, root);
		return found;
	}
	
	/**
	 * Removes specified element from BST
	 * @param element element to remove from BST
	 * @param root the current root whose tree is being operated on
	 * @return root referencing new tree
	 */
	private Node<E> recRemove(E element, Node<E> root) {
		if (root == null)
			found = false;
		else if (element.compareTo(root.getData()) < 0)
			root.setLeft(recRemove(element, root.getLeft()));
		else if (element.compareTo(root.getData()) > 0)
			root.setRight(recRemove(element, root.getRight()));
		else {
			root = removeNode(root);
			found = true;
		}
		return root;
	}
	
	/**
	 * Removes specified Node.
	 * @param root Node to be removed
	 * @return root referencing new tree
	 */
	private Node<E> removeNode(Node<E> root) {
		E data;
		
		if (root.getLeft() == null)
			return root.getRight();
		else if (root.getRight() == null)
			return root.getLeft();
		else {
			data = getPredecessor(root.getLeft());
			root.setData(data);
			root.setLeft(recRemove(data, root.getLeft()));
			return root;
		}
	}
	
	/** 
	 * Returns data in rightmost Node in tree referenced by specified tree.
	 * @param root tree referenced by Node being removed
	 * @return data in rightmost node in specified tree
	 */
	private E getPredecessor(Node<E> root) { 
		while (root.getRight() != null)
			root = root.getRight();
		return root.getData();
	}
	
	/**
	 * Creates Iterator for MyBST.
	 * @author Ignacio Carrillo-Batalla
	 *
	 */
	class MyIterator implements Iterator<E> {
		
		ConcurrentLinkedQueue<E> queue;
		
		/**
		 * Constructs instance of MyIterator.
		 */
		public MyIterator() {
			queue = new ConcurrentLinkedQueue<E>();
			inOrder(root);
		}
		
		/**
		 * Adds elements from MyBST to queue InOrder.
		 * @param root root of MyBST instance
		 */
		private void inOrder(Node<E> root) {
			if (root != null) {
				inOrder(root.getLeft());
				queue.add(root.getData());
				inOrder(root.getRight());
			}
		}

		/** 
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			if (queue.peek() == null)
				return false;
			return true;
		}

		/** 
		 * @see java.util.Iterator#next()
		 */
		@Override
		public E next() {
			return queue.poll();
		}
		
	}
}















