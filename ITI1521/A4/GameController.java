import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.*;
import java.io.*;

/**
 * The class <b>GameController</b> is the controller of the game. It has a method
 * <b>selectColor</b> which is called by the view when the player selects the next
 * color. It then computesthe next step of the game, and  updates model and view.
 *
 * @author Oliver Benning, 7798804, University of Ottawa
 */
public class GameController implements ActionListener {

    /**
     * Reference to the view of the board
     */
    private GameView gameView;

    /**
     * Reference to the model of the game
     */
    private GameModel gameModel;

	/**
	 * Stacks to store model states for undo and redo
	 */
	private Stack<GameModel> moves;
	private Stack<GameModel> undoneMoves;

    /**
     * Constructor used for initializing the controller. It creates the game's view
     * and the game's model instances
     *
     * @param size
     *            the size of the board on which the game will be played
     */
    public GameController(int size) {
		// Create new model and stacks, potentially overriden by savedGame.ser file
		gameModel = new GameModel(size);
		moves = new GenericLinkedStack<GameModel>();
		undoneMoves = new GenericLinkedStack<GameModel>();
		try { // Read object from savedGame file if exists
			File save = new File("savedGame.ser");
			FileInputStream input = new FileInputStream(save);
            ObjectInputStream stream = new ObjectInputStream(input);
			GameModel inmodel =  (GameModel)stream.readObject();
			if (inmodel.getSize() == size) { // Skip if different board size, impying new game
				gameModel = inmodel;
			}
			stream.close();
			save.delete();
		} catch (FileNotFoundException er) {
			//Do nothing, prev savedGame does not exist
		} catch (IOException er) {
			er.printStackTrace();
		} catch (ClassNotFoundException er) {
			er.printStackTrace();
		}
		// Create game view and update
        gameView = new GameView(gameModel, this);
        gameView.update();
    }

    /**
     * resets the game
     */
    public void reset(){
		moves.push(gameModel);
        gameModel.reset();
        flood();
        gameView.update();
    }

    /**
     * Callback used when the user clicks a button (reset or quit)
     *
     * @param e
     *            the ActionEvent
     */
    public void actionPerformed(ActionEvent e) {
		//Dot
        if (e.getSource() instanceof DotButton) { // Capture inital button, push inital state onto stack
			if (gameModel.getNumberOfSteps() == -1) {
				moves.push(gameModel.clone());
				gameModel.capture(((DotButton)(e.getSource())).getRow(), ((DotButton)(e.getSource())).getColumn());
				gameModel.setCurrentSelectedColor(((DotButton)(e.getSource())).getColor());
				flood();
	            gameModel.step();
	            gameView.update();
			}
			else {
				selectColor(((DotButton)(e.getSource())).getColor());
			}
		}

		else if (e.getSource() instanceof JButton) {
			JButton clicked = (JButton)(e.getSource());
			//Undo
			if (clicked.getText().equals("Undo")) {
				undoneMoves.push(gameModel.clone());
				gameModel = moves.pop();
				gameView.setGameModel(gameModel);
				gameView.update();
			}
			//Redo
			else if (clicked.getText().equals("Redo")) {
				moves.push(gameModel.clone());
				gameModel = undoneMoves.pop();
				gameView.setGameModel(gameModel);
				gameView.update();
			}
			//Settings
			else if (clicked.getText().equals("Settings")) {
				gameView.settingsPane();
			}
			//Quit
			else if (clicked.getText().equals("Quit")) {
				try { // Serialize model to file on quit
                    FileOutputStream file = new FileOutputStream("savedGame.ser");
					ObjectOutputStream out = new ObjectOutputStream(file);
					out.writeObject(gameModel);
					out.close();
				} catch (FileNotFoundException er) {
					er.printStackTrace();
				} catch (IOException er) {
					er.printStackTrace();
				}
            	System.exit(0);
        	}
			// Reset
			else if (clicked.getText().equals("Reset")) {
				moves.push(gameModel.clone());
            	reset();
        	}
			// OK, from Settings menu
			else if (clicked.getText().equals("OK")) {
				gameView.closeSettingsPane();
			}
		}

		else if (e.getSource() instanceof JRadioButton) {
			JRadioButton clicked = (JRadioButton)(e.getSource());
			if (clicked.getText().equals("Plane")) {
				gameModel.setTorus(false);
			} else if (clicked.getText().equals("Torus")) {
				gameModel.setTorus(true);
			} else if (clicked.getText().equals("Orthogonal")) {
				gameModel.setDiagonal(false);
			} else if (clicked.getText().equals("Diagonal")) {
				gameModel.setDiagonal(true);
			}
    	}
	}

    /**
     * <b>selectColor</b> is the method called when the user selects a new color.
     * If that color is not the currently selected one, then it applies the logic
     * of the game to capture possible locations. It then checks if the game
     * is finished, and if so, congratulates the player, showing the number of
     * moves, and gives to options: start a new game, or exit
     * @param color
     *            the newly selected color
     */
    public void selectColor(int color) {
		// Does nothing if color pressed already selected
		if (color != gameModel.getCurrentSelectedColor()) {
			while(!undoneMoves.isEmpty()) { // Empty redo stack
				undoneMoves.pop();
			}
			moves.push(gameModel.clone());
			gameModel.setCurrentSelectedColor(color);
            flood();
            gameModel.step();
            gameView.update();

			// Check if game is finished, print finished window if true
            if (gameModel.isFinished()) {
                Object[] options = {"Play Again", "Quit"};
                    int n = JOptionPane.showOptionDialog(gameView,
                        "Congratulations, you won in " + gameModel.getNumberOfSteps()
                        + " steps!\n Would you like to play again?", "Won",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]);

				if (n == 0) {
                    reset();
                }
				else {
                    System.exit(0);
                }
            }
        }
    }

   /**
     * <b>flood</b> is the method that computes which new dots should be ``captured''
     * when a new color has been selected. The Model is updated accordingly
     */
     private void flood() {
		// Method variables
        Stack<DotInfo> stack = new GenericLinkedStack<DotInfo>();
		int dotX, dotY, count, tX, tY;
		int size = gameModel.getSize();
		boolean torus = gameModel.isTorus();
		boolean diagonal = gameModel.isDiagonal();

        for (int i =0; i < size; i++) {
           for (int j =0; j < size; j++) {
                if (gameModel.isCaptured(i,j)) {
                    stack.push(gameModel.get(i,j));
                }
           }
        }
		// Check adjacencies
        while (!stack.isEmpty()) {
            DotInfo DotInfo = stack.pop();
			dotX = DotInfo.getX();
			dotY = DotInfo.getY();
			count = 0;
			for (int x = dotX - 1; x <= dotX + 1; x++) {
				for (int y = dotY - 1; y <= dotY + 1; y++) {
					tX = x;
					tY = y;
					if (count++ % 2 == 0 && !diagonal) {continue;}
					if (x == dotX && y == dotY) {continue;}
					if (x < 0) {
						if (!torus) {continue;}
						tX += size;
					} else if (x >= size) {
						if (!torus) {continue;}
						tX -= size;
					}
					if (y < 0) {
						if (!torus) {continue;}
						tY += size;
					} else if (y >= size) {
						if (!torus) {continue;}
						tY -= size;
					}
					if (shouldBeCaptured(tX, tY)) {
						gameModel.capture(tX, tY);
	                	stack.push(gameModel.get(tX, tY));
					}
				}
			}
        }
    }

	/**
	 * Return if undo stack empty, used by view when refreshing display
	 */
	public boolean isUndoEnabled() {
		return !moves.isEmpty();
	}
	/**
	 * Return if redo stack empty, used by view when refreshing display
	 */
	public boolean isRedoEnabled() {
		return !undoneMoves.isEmpty();
	}

    /**
     * <b>shouldBeCaptured</b> is a helper method that decides if the dot
     * located at position (i,j), which is next to a captured dot, should
     * itself be captured
     * @param i
     *            row of the dot
     * @param j
     *            column of the dot
     */
	private boolean shouldBeCaptured(int i, int j) {
        if (!gameModel.isCaptured(i,j) &&
        	(gameModel.getColor(i,j) == gameModel.getCurrentSelectedColor())) {
            return true;
        }
		else {
            return false;
        }
    }
}
