/**
 * PQueue Class - A priority queue which utilizes a
 * MaxHeap of Process objects. The priority has 2 precedents:
 * the primary precedent is given to the object with the
 * higher priority level, while the secondary precedent is 
 * given to the object that arrived first to the queue.
 * @author michaelelliott
 *
 */
public class PQueue { // doesn't have to be generic but can be
	private MaxHeap<Process> maxHeap;
	/**
	 * Constructs a Priority Queue, utilizing the 
	 * MaxHeap class.
	 */
	public PQueue() {
		maxHeap = new MaxHeap<Process>();
	}
	
	/**
	 * Places a process into the PQueue
	 * @param p: process to put in the PQueue
	 */
	public void enPQueue(Process p) {
		maxHeap.maxHeapInsert(maxHeap, p);
	}
	
	/**
	 * dePQueue removes the first item from the PQueue
	 * @return maxHeapRoot: the highest priority member of 
	 * the PQueue
	 */
	public Process dePQueue() {
		Process maxHeapRoot = maxHeap.extractMax(maxHeap);	
		return maxHeapRoot;
	}
	/**
	 * Checks to see if the Priority Queue has anything
	 * in it.
	 * @return boolean, true if empty, else false
	 */
	public boolean isEmpty() {
		boolean test = true;
		if(maxHeap.heapSize > 0) {
			test = false;
		}
		return test;
	}
	
	/**
	 * Update the Priority Queue to help solve the starvation 
	 * problem. Goes through the maxHeap incrementally and 
	 * increases the timeNotProcessed variable for each process,
	 * also checking if it now needs to increment the priority
	 * level of that process.
	 * @param timeToIncrementLevel: User specified value for the 
	 * length of time to tolerate a process not being given any
	 * processing time.
	 * @param maxLevel: user defined maximum process priority level
	 */
	public void update(int timeToIncrementLevel, int maxLevel) {
		for(int i = 1; i <= maxHeap.heapSize; i++) {
			maxHeap.get(i).timeNotProcessed++; 
			if(maxHeap.get(i).getTimeNotProcessed() >= timeToIncrementLevel) {  // might need to be >= if stats are incorrect
				maxHeap.get(i).resetTimeNotProcessed();
				if(maxHeap.get(i).getPriority() < maxLevel) {
					maxHeap.get(i).increasePriority();
					maxHeap.maxHeapifyUp(maxHeap, i);
				}
			}			
		}
	}
}
