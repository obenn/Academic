/**
 * The class  <b>BirthdayParadox</b> is used to
 * simulated the so-called Birthday paradox, and uses
 * the class <b>Statistics</b> to store the results of
 * the experiments.
 *
 * @author Oliver Benning (obenn009@uottawa.ca) 7798804
 * University of Ottawa
 * ITI1521A Assignement 2 Question 2.
 * Submitted Feb 12 2017
 */
public class BirthdayParadox {
	/**Random generator.*/
	private static java.util.Random generator = new java.util.Random();

	/**
     * Runs the series of experiments, and stores the result into
     * a Statistics object
     *
     * @param range the size of the set from which random number are drawn
     * @param numberOfRuns the number of experiments to run
	 *
	 * @return a reference to a Statistics instance that holds the result
	 * of the experiment
     */
 	public static Statistics runExperiments(int range, int numberOfRuns){
    Statistics statistics = new Statistics(numberOfRuns); // Statistics object
        for (int i = 0; i < numberOfRuns; i++) { // Update Statistics numberOfRuns times
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
	 * @return the number of random draw in the set that the method
	 * used before drawing the same element for the second time
     */
    private static int oneRun(int range){
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
     * Main method. Runs the experiments numberOfRunstimes,
     * with increasingly large sets (increment in size:step).
     * Stop once the size reaches max.
     * plots the result.
     *
     * @param args if not empty, contains the runtime values for
     * step, max and numberOfRuns
     */
    public static void main(String[] args) {
        StudentInfo.display(); // Display student info
        int step = 100; // Default step amount
        int max = 10000; // Default maximum
        int numberOfRuns = 1000; // Default runs
        ITI1121Chart chart = new ITI1121Chart("chart"); // Create chart.
        Statistics run; // Init Statistics object.

        if (args.length >= 1) { // Manually set range
            step = Integer.parseInt(args[0]);
        }
        if (args.length >= 2) { // Manually set range
            max = Integer.parseInt(args[1]);
        }
        if (args.length >= 3) { // Manually set runs
            numberOfRuns = Integer.parseInt(args[2]);
        }

        for (int range = step; range <= max; range += step) { // Iterate through every multiple of step until max reached.
            run = runExperiments(range, numberOfRuns); // Set Statistics object to current iteration.
            chart.addDataPoint(range, run.average(), run.standardDeviation()); // Add datapoint with current statistics.
        }

        chart.render(); // Render chart.
        chart.addPolynome(0.5); // Closest approximation.
        chart.addPolynome(0.6); // Upper approximation.
        chart.addPolynome(0.4); // Lower approximation.
	}
}
