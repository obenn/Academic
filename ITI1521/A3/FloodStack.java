/**
 * Implementation de l'interface Stack pour le jeu FloodIt
 *
 * @author Oliver Benning, 7798804. University of Ottawa.
 */

public class FloodStack implements Stack<DotInfo> {
    private DotInfo[] dotArr; // Value storage
    private DotInfo temp; // temp for pop()
    private int top; // Position tracker

    public FloodStack(int size) {
        dotArr = new DotInfo[(int)Math.pow(size, 2)]; // Set to largest size needed, dynamic not necessary
        top = 0; // Start position tracker at 0
    }

    public boolean isEmpty() {
        return top == 0;
    }

    public DotInfo peek() {
        return dotArr[top-1]; // Return only, no change
    }

    public DotInfo pop() {
        temp = dotArr[--top]; // Temp to store, decrement
        dotArr[top] = null; // Scrub popped val
        return temp; // Return top value
    }

    public void push(DotInfo element) {
        dotArr[top++] = element; // Increment top and store
    }
}
