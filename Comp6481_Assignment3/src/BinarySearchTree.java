import java.util.ArrayList;
import java.util.List;

class BinarySearchTree {

	static List<Long> keys = new ArrayList<Long>();

	class Node {
		long key;
		Node left, right;

		public Node(long item) {
			key = item;
			left = right = null;
		}
	}
	
	// Root of BST
	Node root;
	Node temp = new Node(0L);

	// Constructor
	BinarySearchTree() {
		root = null;
	}

	void insert(long key) {
		root = insertRec(root, key);
	}

	Node insertRec(Node root, long key) {

		if (root == null) {
			root = new Node(key);
			return root;
		}

		if (key < root.key)
			root.left = insertRec(root.left, key);
		else if (key > root.key)
			root.right = insertRec(root.right, key);

		return root;
	}
	/**
	 * @return root
	 */

	void inorder() {
		inorderRec(root);
	}

	void inorderRec(Node root) {

		if (root != null) {
			inorderRec(root.left);
			System.out.println(root.key);
			inorderRec(root.right);
		}
	}
	/**
	 * 
	 * @param root
	 */

	void listAllKeys(Node root) {
		if (root != null) {
			listAllKeys(root.left);
			keys.add(root.key);
			listAllKeys(root.right);
		}
	}
	/**
	 * 
	 * @param root
	 * @param key
	 * @return
	 */

	long prevKey(Node root, long key) {
		keys.clear();
		listAllKeys(root);
		long prev = -1L;
		for (Long k : keys) {
			if (k == key)
				return prev;
			else
				prev = k;
		}
		return prev;
	}
	/**
	 * 
	 * @param root
	 * @param key1
	 * @param key2
	 * @return
	 */

	List<Long> rangeKeys(Node root, long key1, long key2) {
		keys.clear();
		listAllKeys(root);
		List<Long> filtered = new ArrayList();
		for (long k : keys) {
			if (k >= key1 && k <= key2) {
				filtered.add(k);
			}
		}
		return filtered;
	}
	/**
	 * 
	 * @param root
	 * @param key
	 * @return
	 */

	long nextKey(Node root, long key) {
		Node current = containsNode(key);
		if (current.right != null) {
			Node nextNode = leftMost(current.right);
			return nextNode.key;
		}
		if (current.right == null) {
			Node right = rightMost(root);
			if (right == current)
				return 0;
			else
				return findInorderRecursive(root, current).key;
		}
		return key;

	}
	/**
	 * 
	 * @param current
	 * @param value
	 * @return
	 */

	private Node containsNodeRecursive(Node current, long value) {
		if (current == null) {
			return null;
		}
		if (value == current.key) {
			return current;
		}
		return value < current.key ? containsNodeRecursive(current.left, value)
				: containsNodeRecursive(current.right, value);
	}
	/**
	 * 
	 * @param value
	 * @return
	 */

	public Node containsNode(long value) {
		return containsNodeRecursive(root, value);
	}
	/**
	 * 
	 * @param root
	 * @param key
	 * @return
	 */

	public Node remove(Node root, long key) {
		if (root == null)
			return root;

		if (key < root.key)
			root.left = remove(root.left, key);
		else if (key > root.key)
			root.right = remove(root.right, key);
		else {
			if (root.left == null)
				return root.right;
			else if (root.right == null)
				return root.left;

			root.key = minVal(root.right);
			root.right = remove(root.right, root.key);

		}
		return root;

	}
	/**
	 * 
	 * @param root
	 * @return
	 */

	private long minVal(Node root) {
		long min = root.key;
		while (root.left != null) {
			min = root.left.key;
			root = root.left;
		}
		return min;
	}
/**
 * 
 * @param node
 * @return
 */
	private Node leftMost(Node node) {
		while (node != null && node.left != null)
			node = node.left;
		return node;
	}
	/**
	 * 
	 * @param node
	 * @return
	 */

	private Node rightMost(Node node) {
		while (node != null && node.right != null)
			node = node.right;
		return node;
	}
	/**
	 * 
	 * @param root
	 * @param x
	 * @return
	 */

	Node findInorderRecursive(Node root, Node x) {
		if (root == null)
			return null;

		if (root == x || (temp = findInorderRecursive(root.left, x)) != null
				|| (temp = findInorderRecursive(root.right, x)) != null) {
			if (temp != null) {
				if (root.left == temp) {
					return root;
				}
			}

			return root;
		}
	
		return null;
	}
	

}

