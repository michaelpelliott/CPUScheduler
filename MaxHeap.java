import java.util.ArrayList;

/**
 * Max Heap Class - Uses an array implementation of a max heap
 * A max heap is a complete binary tree where the value of any
 * node is >= its child
 * For this application in a CPU Scheduling simulation, each 
 * node in the max-heap contains a process
 * 
 * A special note about this implementation of a max-heap in an array.
 * This implementation, for time purposes, is such that the initial
 * position of the array, Array[0], is null and never used so that
 * the array can be properly indexed at 1. This preserves our in-class work 
 * as accurate and will suffice for time purposes.
 * @author michaelelliott
 *
 */


@SuppressWarnings({ "serial"}) 
public class MaxHeap<T extends Comparable<T>> extends ArrayList<Process> {

	protected int heapSize; 

	/**
	 * Constructs a MaxHeap object. The add(null) is used
	 * to preserve indexing at 1 instead of the standard 
	 * indexing at zero.
	 */
	public MaxHeap() {	
		heapSize = 0;
		this.add(null);
	}

	/**
	 * Take a max-heap array and heapify it in the upward direction of the tree
	 * This entails comparing the child at i, to it's parent, and potentially
	 * swapping them.
	 * @param A: max-heap to heapify
	 * @param i: index of child node
	 * @return : a max-heap from the location of i up to the root
	 */
	public MaxHeap<Process> maxHeapifyUp(MaxHeap<Process> A, int i) {
		Process procA = A.get(i);
		Process procB = A.get(parent(i));
		while(i > 1 && procA.compareTo(procB) > 0) {
			Process dummyVar = A.get(i);
			A.set(i, A.get(parent(i)));
			A.set(A.parent(i), dummyVar);
			i = parent(i);
			procA = A.get(i);
			procB = A.get(parent(i));
		}
		
		return A;
	}
	
	/**
	 * MaxHeapifyDown takes a given node of an array and recursively 
	 * "heapifies" it downward through the tree from that node.
	 * 
	 * Pre-condition: the left and right subtrees of the node at position i
	 * are already max-heaps
	 * 
	 * Post-condition: the subtree rooted at node i becomes a max-heap
	 * @param A: the ArrayList representation of our binary tree
	 * @param i: the index of the node for which to maxHeapifyDown from
	 * @return A: the ArrayList that is now a max-heap from node i down
	 */
	public MaxHeap<Process> maxHeapifyDown(MaxHeap<Process> A, int i) {
		int largest = 0;
		int l = left(i);
		int r = right(i);
		if(l <= A.heapSize && A.get(l).compareTo(A.get(i)) > 0) {	
			largest = l;
		}
		else {
			largest = i;
		}
		if (r <= A.heapSize && A.get(r).compareTo(A.get(largest)) > 0 ) {
			largest = r;
		}
		if (largest != i) {
			Process dummyVar = A.get(i);
			A.set(i, A.get(largest));
			A.set(largest, dummyVar);
			maxHeapifyDown(A, largest); // may not be in the proper location, check notes
		}
		return A;
	}
	/**
	 * extract the maximal object from the max-heap, this is located at the root of the tree
	 * @param A: max-heap to extract from
	 * @return The max-heap, with the root element moved to the end, and the
	 * 			size of the max heap deprecated
	 */
	public Process extractMax(MaxHeap<Process> A) {
		Process max = A.get(1);
		A.set(1,A.get(A.heapSize));
		A.set(A.heapSize,max);
		A.heapSize--;
		maxHeapifyDown(A,1);
		return max;
	}
	
	/**
	 * insert a new object into an already existing max-heap
	 * @param A: existing max-heap
	 * @param x: object to insert
	 */
	public void maxHeapInsert(MaxHeap<Process> A, Process x) {
		A.heapSize++;
		A.add(A.heapSize,x);
		maxHeapifyUp(A,A.heapSize);
	}
	
	/**
	 * deletes a specific element from the max-heap
	 * @param A: the max-heap to operate on
	 * @param i: the index in which the element resides that is to be removed
	 */
	public Process maxHeapDelete(MaxHeap<Process> A, int i) {
		Process dummyVar = A.get(i);
		if (A.get(i).compareTo(A.get(A.heapSize)) > 0) {
			A.set(i, A.get(A.heapSize));
			A.set(A.heapSize, dummyVar);
			heapSize--;
			maxHeapifyUp(A,i);
		}
		else {
			A.set(i, A.get(A.heapSize));
			A.set(A.heapSize, dummyVar);
			heapSize--;
			maxHeapifyDown(A,i);
		}
		return dummyVar;
	}
	/**
	 * Helper method to return the left child of a parent node
	 * @param parentInt: integer location of the parent
	 * @return integer location in the array of the left child
	 */
	private int left(int parentInt) {
		return 2*parentInt;
	}

	/**
	 * Helper method to retrieve the right child of a parent node
	 * @param parentInt: integer location of the parent
	 * @return integer location in the array of the right child
	 */
	private int right(int parentInt) {
		return (2*parentInt + 1);
	}
	
	/**
	 * Helper method to return the integer location of the parent
	 * of a child node
	 * @param childInt: the integer location of the child
	 * @return The integer location in the array of the parent node
	 */
	private int parent(int childInt) {
		return (childInt/2);
	}

}
