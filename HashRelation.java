import java.util.TreeSet;

/**
 *
 * Class using a (Closed Bucket) Hash Table (CBHT) data structure to implement
 * a Relation abstract data type (ADT). A Relation consists of a collection
 * of binary pairs where the combination of values in the pair
 * must be unique. This is unlike a Map ADT where keys alone must be unique.
 *
 * @author Faisal Ahsan
 *
 */
public class HashRelation<X,Y> implements Relation<X, Y>{

	// Instance variable
	private Node<X,Y>[] buckets;

	// Constructor to initialise HashRelation
	@SuppressWarnings("unchecked")
	public HashRelation (int m) {
		buckets = (Node<X,Y>[]) new Node<?,?>[m];
	}

	/**
	 *  Method which hashes X value so we can store (X,Y) pair.
	 *  Uses java's hashCode method.
	 */
	private int hash (X xValue) {
		return Math.abs(xValue.hashCode()) % buckets.length;
	}


	/**
	 * Inner class which defines a Node object. Nodes of the HashRelation
	 * are made up of this Node object
	 */
	private static class Node<X,Y> {

		// Instance variables for Node class
		private X xValue;
		private Y yValue;
		private Node<X,Y> next;

		/**
		 * Constructor for inner class Node
		 * @param xValue
		 * @param yValue
		 * @param nextPair
		 */
		private Node (X xValue, Y yValue, Node<X,Y> nextPair) {
			this.xValue = xValue;
			this.yValue = yValue;
			this.next = nextPair;
		}
	}

	/**
	 * CBHT Search algorithm to check if a relation contains a given pair.
	 *
	 * Time complexity:
	 * best case O(1)
	 * worst case O(n)
	 *
	 * (worst case would effectively be a hash table with a single bucket,
	 * akin to linear search in a singly linked list)
	 *
	 * @see Relation#containsPair(java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean containsPair(X xValue, Y yValue) {

		boolean pairExists = false;	// Boolean returned
		int b = hash(xValue);

		Node<X,Y> currentNode = buckets[b];

		while (currentNode != null) {
			// Combined test as we are comparing both values
			if ((xValue.equals(currentNode.xValue))
					&& (yValue.equals(currentNode.yValue))) {
				pairExists = true;
				break;
			}
		// Traverse to next node.
		currentNode = currentNode.next;
		}
		return pairExists;
	}


	/**
	 * CBHT Search to return all pairs with a given X.
	 *
	 * Time complexity:
	 * best case O(1),
	 * worst case O(n)
	 *
	 * (worst case as in 'containsPair').
	 *
	 * @see Relation#getYValues(java.lang.Object)
	 */
	@Override
	public TreeSet<Y> getYValuesGivenX(X targetX) {

		// Local variables
		int b = hash(targetX);
		Node<X,Y> currentNode = buckets[b];
		TreeSet<Y> yValueSet = new TreeSet<Y>();

		// If the current node has a binary pair (i.e. not null), we
		// check if the x matches the current node's X. If it does, we add
		// this node's Y value to yValueSet.
		while (currentNode != null) {
			if (targetX.equals(currentNode.xValue)) {
				yValueSet.add(currentNode.yValue);
			}
			currentNode = currentNode.next;
		}
		return yValueSet;
	}

	/**
	 * CBHT Search to return all pairs with a given Y.
	 * Requires iteration through all nodes.
	 *
	 * Time complexity:
	 * O(m) where m = number of buckets OR O(n)
	 *
	 * (depends on which of m/n is higher)
	 * (have to iterate through each bucket and node)
	 *
	 * @see Relation#getXValues(java.lang.Object)
	 */
	@Override
	public TreeSet<X> getXValuesGivenY(Y targetY) {

		// Local variables
		TreeSet<X> xValueSet = new TreeSet<X>();

		// Iterate through all nodes.
		for (int i = 0; i < buckets.length; i++) {

			// Reintialised for each outer loop iteration
			Node<X,Y> currentNode = buckets[i];

			while (currentNode != null) {
				if (targetY.equals(currentNode.yValue)) {
					xValueSet.add(currentNode.xValue);
				}
				// Move to next node in bucket.
				currentNode = currentNode.next;
			}
		}
		return xValueSet;
	}

	/**
	 * Empty the relation. Iterate through all buckets
	 * and make them null.
	 *
	 * Time complexity:
	 *
	 * O(m) where m = number of buckets.
	 *
	 * @see Relation#makeEmpty()
	 */
	@Override
	public void makeEmpty() {
		for (int i = 0; i < buckets.length; i++) {
			buckets[i] = null;
		}
	}

	/**
	 * CBHT Insertion algorithm. Must check if a pair exists in hash
	 * table as it is only added if it doesn't exist.
	 *
	 * Time complexity:
	 * best case O(1)
	 * worst case O(n)
	 *
	 * @see Relation#addPair(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void addPair(X xValue, Y yValue) {

		int b = hash(xValue);
		Node<X,Y> currentNode = buckets[b];
		boolean pairExists = false; // Don't want duplicates!


		// Iterate through all nodes to see if this pair exists.
		while (currentNode != null) {
			if (xValue.equals(currentNode.xValue)
					&& (yValue.equals(currentNode.yValue))) {
				pairExists = true;
			}
			// Travserse to next node.
			currentNode = currentNode.next;
		}

		// After checking all nodes, if pair is not a duplicate then
		// we add it to the Hash Table.
		if (pairExists == false) {

			// Ensure we don't overwrite an existing node.
			// Create a link to its predecessor.
			Node<X,Y> previous = buckets[b];
			buckets[b] = new Node<X,Y>(xValue, yValue, previous);
		}
	}

	/**
	 * CBHT Deletion algorithm. Remove a binary pair from the Relation.
	 *
	 * Time complexity:
	 * best case O(1)
	 * worst case O(n)
	 *
	 * @see Relation#removePair(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void removePair(X xValue, Y yValue) {
		int b = hash(xValue);
		Node<X,Y> currentNode = buckets[b];
		Node<X,Y> previousNode = null;

		// Iterate through all nodes to see if this pair exists.
		while (currentNode != null) {

			// Combined test as we test the combination of X and Y.
			if ((xValue.equals(currentNode.xValue)) &&
					(yValue.equals(currentNode.yValue))) {

				// If found, create links between nodes on either side.
				if (previousNode == null) {
					buckets[b] = currentNode.next;
				} else {
					previousNode.next = currentNode.next;
				}
				break;
			}
			// Traverse to next node in bucket; keep track of previous node.
			previousNode = currentNode;
			currentNode = currentNode.next;
		}
	}

	/**
	 * Remove all binary pairs given X for a given X.
	 *
	 * Algorithm is commented within the code to explain purpose of
	 * crucial lines
	 *
	 * Time complexity:
	 * best case O(1)
	 * worst case O(n)
	 *
	 * @see Relation#removeAllPairsGivenX(java.lang.Object)
	 */
	@Override
	public void removeAllPairsGivenX(X xValue) {

		int b = hash(xValue);
		Node<X,Y> currentNode = buckets[b];
		Node<X,Y> previousNode = null;	// Need to track so we don't have any missing links

		// Iterate through all nodes
		while (currentNode != null) {

			if (xValue.equals(currentNode.xValue)) {

				// If no predecessor node, move to next node.
				if (previousNode == null) {
					buckets[b] = currentNode.next;

				// Else we make a link.
				} else {
					previousNode.next = currentNode.next;
				}
			} else {
				// Preserve link to predecessor before moving to next node.
				previousNode = currentNode;
			}
			// Move to next node.
			currentNode = currentNode.next;
		}
	}

	/**
	 * Remove all binary pairs containing Y for a given Y.
	 *
	 * Time complexity:
	 *
	 * O(m) where m = number of buckets OR O(n)
	 *
	 * (depends on which of m/n is higher)
	 * (have to iterate through every node and every bucket)
	 *
	 * @see Relation#removeAllPairsGivenY(java.lang.Object)
	 */
	@Override
	public void removeAllPairsGivenY(Y yValue) {

		// Local variables
		Node<X,Y> currentNode;
		Node<X,Y> previousNode;

		// Iterate through all nodes.
		for (int i = 0; i < buckets.length; i++) {

			// Reinitialise to null inside loop for each iteration
			currentNode = null;
			previousNode = null;

			// Make currentNode point to buckets[i]
			currentNode = buckets[i];

			// If this node is not null..
			while (currentNode != null) {

				// ... if yValue parameter is this node's Y value...
				if (yValue.equals(currentNode.yValue)) {

					// ... and previousNode is null, then we
					// delete by making currentNode.next = buckets[i]
					if (previousNode == null) {
						buckets[i] = currentNode.next;
					} else {
						// else if previousNode is not null, simply
						// link between previous and next nodes
						previousNode.next = currentNode.next;
					}
				} else {
					// Node not to be removed...
					// Iterate along to next node, preserving links.
					previousNode = currentNode;
				}
				// Move to next node.
				currentNode = currentNode.next;
			}
		}
	}

	/**
	 * Prints out representation of the Relation contents.
	 *
	 * Time complexity: O(m) where m = number of buckets.
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		String representation = "";

		// Iterate through all buckets.
		for (int i = 0;i<buckets.length;i++) {

			Node<X,Y> currentNode = buckets[i];

			while (currentNode != null) {
				representation += "(" + currentNode.xValue + "," + currentNode.yValue +"), ";
				currentNode = currentNode.next;
			}
			representation+="\r\n";
		}
		return representation;
	}
}
