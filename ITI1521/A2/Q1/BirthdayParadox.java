/**
 * The class  <b>BirthdayParadox</b> is used to
 * simulated the so-called Birthday paradox, and uses
 * the class <b>Statistics</b> to store the results of
 * the experiments.
 *
 * @author Oliver Benning (obenn009@uottawa.ca) 7798804
 * University of Ottawa
 * ITI1521A
 * Submitted: Feb 12 2017
 */
public class BirthdayParadox {
	/** Random generator.*/
	private static java.util.Random generator = new java.util.Random();

	/**
     * Runs the series of experiments, and stores the result into
     * a Statistics object
     *
     * @param range the size of the set from which random numbers are drawn
     * @param numberOfRuns the number of experiments to run
	 *
	 * @return a reference to a Statistics instance that holds the result
	 * of the experiment
     */
 	public static Statistics runExperiments(int range, int numberOfRuns) {
        Statistics statistics = new Statistics(numberOfRuns); // Statistics object.
        for (int i = 0; i < numberOfRuns; i++) { // Update Statistics numberOfRuns times.
            statistics.updateStatistics(oneRun(range));
        }
        return statistics;
	}

 	/**
     * Runs a single experiment.
     * The parameter range defines the size of the set from which
     * the experiment is drawn
     *
     * @param range the size of the set from which random number are drawn
     *
	 * @return the number of random draws in the set that the method
	 * used before drawing the same element for the second time
     */
    private static int oneRun(int range) {
        int[] rInt = new int[range+1]; // Stores the past sequence of ints.
        rInt[0] = generator.nextInt(range); // Inital random number.

        for (int count = 1; count <= range; count++) { // Run until return.
            rInt[count] = generator.nextInt(range); // Add randint to current iteration.
            for (int i = 0; i < count; i++) { // Check for past occurence.
                if (rInt[i] == rInt[count]) { // Return if found.
                    return count; // Value to return is iterations until match found.
                }
            }
        }
        throw new AssertionError("unreachable code reached"); // Enable compilation.
	}


	/**
     * Main method. The default size of the set is 365, and
     * the experiment is run 50 times. Both numbers can be reset
     * from the command line.
     * This method runs the experiments and prints the
     * resulting Statistics
     *
     * @param args if not empty, contains the runtime values for
     * the size of the set and the number of runs
     */
	public static void main(String[] args) {
        StudentInfo.display(); // Display student info
        int range = 365; // Default range
        int numberOfRuns = 50; // Default runs

        if (args.length >= 1) { // Manually set range
            range = Integer.parseInt(args[0]);
        }
        if (args.length >= 2) { // Manually set runs
            numberOfRuns = Integer.parseInt(args[1]);
        }

        // Call runExperiments, print Statistics.toString.
        System.out.println((runExperiments(range, numberOfRuns)).toString());
	}
}