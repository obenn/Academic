/**
 * The class <b>A1Q2</b> implements a static function 
 * that counts the number of positive integers
 * in an array
 *
 * @author  Marcel Turcotte (turcotte@eecs.uottawa.ca)
 *
 */


public class A1Q2 {

	/** 
     * Returns true if elems contains at least two consecutive
     * identical number, false otherwise
     *
     *   @param elems the list of integers, assumed to be non null
     *  @return true if elems contains at least two consecutive
     * identical number, false otherwise
     */
 
    private static boolean hasTwoLengthRun(int[] elems) {

        boolean foundPair = false;

        if (elems.length > 1){
            for (int i = 0; i<elems.length - 1; i++) {
                if (elems[i] == elems[i+1]) {
                    foundPair = true;
                }
            }
        }
        return foundPair;
    }

 	/**
     * The main method of this program. Gets an array of
     * strings as input parameter. The array is assumed to
     * be non-null, and all the strings in the array are
     * parsable as integer.
     *
     * The function prints out true if the list of 
     * integers parsed in args contains at least 2 consecutive
     * identical integers
     * @param args space-separated list of strings parsable as integers
	 */    

 	public static void main(String[] args) {

        int[] intarray = new int[args.length];

        for (int i = 0; i < args.length; i++) {
            intarray[i] = Integer.parseInt(args[i]);
        }
        
        System.out.println(hasTwoLengthRun(intarray));
    }
}
