package project4;

/**
 * Creates Node objects that contain any type of object that implements comparable.
 * @author Ignacio Carrillo-Batalla
 *
 */
public class Node<E extends Comparable<E>> implements Comparable<E> {
	
	private E data;
	private Node<E> left;
	private Node<E> right;
	
	/**
	 * Constructs Node object.
	 */
	public Node() {
	}
	
	/**
	 * Constructs Node object containing specified parameter.
	 * @param data
	 */
	public Node(E data) {
		setData(data);
	}
	
	/**
	 * Constructs Node object containing specified parameter and that reference specified
	 * 		left and right nodes.
	 * @param data
	 * @param left
	 * @param right
	 */
	public Node(E data, Node<E> left, Node<E> right) {
		setData(data);
		setLeft(left);
		setRight(right);
	}
	
	/** 
	 * Compares Nodes for order.
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(E o) {
		//checks if same object is being compared
		if (this == o) 
			return 0;
		//	cast to Node and compare
		Node<E> other = (Node<E>) o;
		return data.compareTo(other.data);
	}

	/** 
	 * Returns Node data.
	 * @return the data
	 */
	public E getData() {
		return data;
	}

	/**
	 * Sets Node data.
	 * @param data the data to set
	 */
	public void setData(E data) {
		this.data = data;
	}

	/** 
	 * Returns Node's left child.
	 * @return the left
	 */
	public Node<E> getLeft() {
		return left;
	}

	/** 
	 * Sets Node's left child.
	 * @param left the left to set
	 */
	public void setLeft(Node<E> left) {
		this.left = left;
	}

	/**
	 * Returns Node's right child.
	 * @return the right
	 */
	public Node<E> getRight() {
		return right;
	}

	/**
	 * Sets Node's right child.
	 * @param right the right to set
	 */
	public void setRight(Node<E> right) {
		this.right = right;
	}

	/** 
	 * Returns string interpretation of Node.
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "" + data + "";
	}
	
}
