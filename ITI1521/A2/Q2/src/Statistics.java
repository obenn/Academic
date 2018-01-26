/**
 * The class  <b>Statistics</b> accumulates the results of
 * the experiments. It knows ahead of time how many experiments
 * will be run, and provides at the end the min, the max, the
 * average and the standard deviation for the data.
 *
 * <b> this class should not use classes such as Array,
 * Lists etc. </b> to store the data, only primitive types
 * and java arrays.
 *
 * @author Oliver Benning (obenn009@uottawa.ca) 7798804
 * University of Ottawa
 * ITI1521A Assignement 2 Question 2.
 * Submitted Feb 12 2017
 */

public class Statistics {
    //Class global variables.
    private double numberOfRuns, standardDeviation, average, resultCurrent, resultTotal, runsSoFar;
    private int minval, maxval;
    private int[] deviationArray;

	/**
     * Constructor.
     *
     * @param numberOfRuns the number of experiments that will be run
     */
    public Statistics(int numberOfRuns) {
        deviationArray = new int[numberOfRuns]; // Array for standardDeviation method.
        this.numberOfRuns = numberOfRuns; // Retain variable name.
        resultTotal = runsSoFar = 0; // //Set trackers to 0.
    }

	  /**
     * Updates statistics after one experiment.
     * This method cannot be called more times than the
     * parameter that was passed in the constructor. If
     * it is, an error message should be printed and
     * no change should occur.
     *   @param value the result of the new experiment
     */
    public void updateStatistics(int value) {
        resultCurrent = value; // Set current result to passed value.
        resultTotal += value; // Add passed value to total result.

        if(runsSoFar == 0) { // Default first values to min/max.
            maxval = value;
            minval = value;
        }
        
        runsSoFar++; // Increment.

        if (value > maxval) { // Max check.
            maxval = value;
        }
        if (value < minval) { // Min check.
          minval = value;
        }

        average = average(); // Call average.
        standardDeviation = standardDeviation(); // Call standardDeviation.
	}

    /**
     *   @return the current average of the values passed
     * to the method updateStatistic
     */
    public double average() {
        return resultTotal/runsSoFar; // Mean calculation.
	}

	/**
     *   @return the current standard deviation of the values passed
     * to the method updateStatistic
     */
    public double standardDeviation() {
        // Calculated using "population" standard deviation, not sample.
        deviationArray[(int)runsSoFar-1] = (int)resultCurrent; // Update array.
        double deviationSummation = 0; // Summation for standardDeviation.
        
        for(int i = 0; i < runsSoFar; i++) { // Sum difference from average squared for current and all prior values.
            deviationSummation += (Math.pow(deviationArray[i]-average, 2));
        }

        return Math.sqrt(deviationSummation/runsSoFar); // Current standard deviation.
	}

	/**
     *  @return Returns the complete statistics information:
     * current minimum, current maximum, current average and
     * current standard deviation. For the last two, only two
     * digits decimals are shown
     */
    public String toString() {
        return( // 5 lines, decimals chopped to two decimal places.
           "We executed " + (int)numberOfRuns + " times.\n"
           + "The minimum value was: " + minval + "\n"
           + "The maximum value was: " + maxval + "\n"
           + "The average value was: " + ((int)(average*100.0))/100.0 + "\n"
           + "The standard deviation was: " + ((int)(standardDeviation*100.0))/100.0);
    }
}