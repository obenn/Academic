/**
 * The class <b>A1Q3</b> implements a static function 
 * that counts the number of positive integers
 * in an array
 *
 * @author Marcel Turcotte (turcotte@eecs.uottawa.ca)
 *
 */


public class A1Q3 {

	/** 
     * Returns the longest run of consecutive identical elements in elems.
     * We assume that the list is not null.
     * 
     *   @param elems the list of integers
     *   @return size of the longest run
     */
 
    private static int getLongestRun(int[] elems) {
        
        int count = 1;
        int countmax = 1;

        if (elems.length > 1){
            
            for (int i = 0; i < elems.length - 1; i++) {
                
                if (elems[i] == elems[i+1]) {
                    count++;
                }
                
                else {
                    count = 1;   
                }
                
                if (count > countmax) {
                    countmax = count;
                }    
            }
        }
        
        else if (elems.length == 0){
            countmax = 0;
        }

        return countmax;
    }

 	/**
     * The main method of this program. Gets an array of
     * strings as input parameter. The array is assumed to
     * be non-null, and all the strings in the array are
     * parsable as integer.
     *
     * The function prints out the longest run of consecutive 
     * identical integers parsed in args
     * @param args space-separated list of strings parsable as integers
	 */    

 	public static void main(String[] args) {

        int[] intarray = new int[args.length];

        for (int i = 0; i < args.length; i++) {
            intarray[i] = Integer.parseInt(args[i]);
        }
        
        System.out.println(getLongestRun(intarray));

    }
}