

/**
 * Process Class - a process object with values for its priority,
 * time needed to finish processing, arrival time of process, and
 * time spent not processing. 
 * 
 * @author michaelelliott
 * 
 */
public class Process implements Comparable<Process> {
	
	int priorityLevel, timeRemainingToFinish, timeNotProcessed, arrivalTime;
	
	/**
	 * Constucts a Process object. 
	 * @param priority: the priority level given to the process object
	 * @param timeRemaining: how long the process requires to operate
	 * @param arrivalTime: the time that the process arrived at the PQueue
	 */
	public Process(int priority, int timeRemaining, int arrivalTime) {
		timeNotProcessed = 0;
		this.arrivalTime = arrivalTime;
		priorityLevel = priority;
		timeRemainingToFinish = timeRemaining;
		
	}
	
	/**
	 * get the time remaining for the specific process
	 * @return time remaining on the process
	 */
	public int getTimeRemaining() {
		return timeRemainingToFinish;
	}
	
	/**
	 * get the time spent not processing for a specific process
	 * @return time not processed
	 */
	public int getTimeNotProcessed() {
		return timeNotProcessed;
	}
	
	/**
	 * reset the time not processed instance variable to zero
	 */
	public void resetTimeNotProcessed() {
		timeNotProcessed = 0;
	}
	
	/**
	 * Deprecate the time remaining on a process
	 */
	public void reduceTimeRemaining() { 
		timeRemainingToFinish--;
	}
	
	/**
	 * Increase the priority level by one, this helps fix the
	 * "starvation problem"
	 */
	public void increasePriority() {
		priorityLevel++;
	}
	
	/**
	 * Gets the priority level for a specific process
	 * @return priorityLevel
	 */
	public int getPriority() {
		return priorityLevel;
	}
	
	/**
	 * Tells the CPUScheduler if the process is finished
	 * by checking the timeRemainingToFinish instance variable
	 * @return True if timeRemainingToFinish < 1, else false
	 */
	public boolean finish() {
		boolean retVal = false;
		if (timeRemainingToFinish < 1) {
			retVal = true;
		}
		return retVal;
	}
	
	/**
	 * Gets the arrival time of a specific process
	 * @return arrival time
	 */
	public int getArrivalTime() {
		return arrivalTime;
	}
	
	@Override
	/**
	 *  This compareTo method is implemented for a Priority Queue
	 *  The priority attribute of each Process object will have the
	 *  higher precedence for comparative purposes. The secondary precedence
	 *  is set by arrival time of the process. Because no two processes
	 *  can have the same arrival time in this simulation, there is no
	 *  chance of compareTo() returning zero.
	 *  
	 *  Example usage: 
	 *  procA.compareTo(procB): 
	 *  if procA priority > procB priority
	 *  then procA is more important and should run first, return +1
	 *  if procB has a higher priority than procA, procB should run first,
	 *  return -1; if their priority levels are equal, then compare the
	 *  arrival times, if procA arrived before procB, procA should run first,
	 *  return +1, else return -1.
	 */
	public int compareTo(Process o) {
		int retVal;
		if(this.priorityLevel > o.priorityLevel) { // procA has higher priority
			retVal = 1;
		}
		else if (this.priorityLevel < o.priorityLevel) { // procA has lower priority
			retVal = -1;
		}
		else { // equal priority, compare arrival times
			 if(this.arrivalTime < o.arrivalTime) { // procA arrived first
				 retVal = 1;
			 }
			 else { 
				 retVal = -1;
			 }
		}
		return retVal;
	}

	
	


}
