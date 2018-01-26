/**
 * The class <b>FloodIt</b> launches the game
 *
 * @author Oliver Benning, 7798804, University of Ottawa
 */
public class FloodIt {

   /**
     * <b>main</b> of the application. Creates the instance of  GameController
     * and starts the game. If a game size (>10) is passed as parameter, it is
     * used as the board size. Otherwise, a default value is passed
     *
     * @param args
     *            command line parameters
     */
	public static void main(String[] args) {
		StudentInfo.display();
        int size = 10;
        if (args.length == 1) { // Limit to one parameter
            try{
                size = Integer.parseInt(args[0]);
                if (size<10) { // Revert to 12 if less than 10 given
                    System.out.println("Invalid argument, using default...");
                    size = 12;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid argument, using default...");
        	}
        }
		// Initialise game
        GameController game = new GameController(size);
    }
}
