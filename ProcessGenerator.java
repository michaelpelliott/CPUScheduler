import java.util.Random;

/**
 *  Process Generator Class
 *  Contains all psuedo-random number generation duties for the
 *  Priority Queue simulation.
 *  
 * @author michaelelliott
 *
 */
public class ProcessGenerator {
	private double probability;
	private Random random;
	/**
	 * Constructs a ProcessGenerator object and
	 * initializes a java Random object for generating
	 * psuedo-random numbers.
	 * @param rate: the rate at which Process objects will
	 * be created
	 */
	public ProcessGenerator(double rate) {
		probability = rate;
		random = new Random();
	}
	
	/**
	 * query() uses the user-defined rate for creation of 
	 * processes and randomly decides if one was created
	 * when the method is called
	 * @return yesNo: the boolean value for creation of processes
	 */
	public boolean query() {
		boolean yesNo = false;
		if (random.nextDouble() < probability) {
			yesNo = true;
		}
		return yesNo;
	}
	
	
	/**
	 * Creates a process with values randomly assigned from a user defined range.
	 * 
	 * @param maxProcessTime: maximum amount of time a process can need to finish, user defined
	 * @param maxPriorityLevel: the maximal priority level that the processes can have
	 * @param arrivalTime: the time at which the process was created, defined by the iteration of
	 * the for-loop in this CPU Scheduler simulation.
	 * @return A new process
	 */
	public Process getNewProcess(int arrivalTime, int maxProcessTime, int maxPriorityLevel) {
		Process process = new Process(random.nextInt(maxPriorityLevel)+1, random.nextInt(maxProcessTime)+1, arrivalTime);
		return process;
	}

}
