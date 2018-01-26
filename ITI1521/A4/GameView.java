import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * The class <b>GameView</b> provides the current view of the entire Game. It extends
 * <b>JFrame</b> and lays out an instance of  <b>BoardView</b> (the actual game) and
 * two instances of JButton. The action listener for the buttons is the controller.
 *
 * @author Oliver Benning, 7798804, University of Ottawa
 */

public class GameView extends JFrame {
    /**
     * The board is a two dimensionnal array of DotButtons instances
     */
    private DotButton[][] board;

    /**
     * Reference to the model of the game
     */
    private GameModel  gameModel;

	/**
     * Reference to the controller of the game
     */
    private GameController gameController;

	/**
     * Label showing score/choose color dialog
     */
    private JLabel scoreLabel;

	/**
     * JFrame for settings pane
     */
	private JFrame frame;
	/**
	 * Undo and redo buttons
	 */
	private JButton buttonUndo, buttonRedo;

    /**
     * Constructor used for initializing the Frame
     *
     * @param model
     *            the model of the game (already initialized)
     * @param gameController
     *            the controller
     */
    public GameView(GameModel model, GameController gameController) {
        super("Flood it -- the ITI 1521 version");

        this.gameModel = model;
        this.gameController = gameController;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setBackground(Color.WHITE);

		JPanel topControl = new JPanel();
		topControl.setBackground(Color.WHITE);
        scoreLabel = new JLabel();
		topControl.setBorder(BorderFactory.createEmptyBorder(20, 10, 0, 10));

		buttonUndo = new JButton("Undo");
        buttonUndo.setFocusPainted(false);
		buttonUndo.setEnabled(false);
        buttonUndo.addActionListener(gameController);

        buttonRedo = new JButton("Redo");
        buttonRedo.setFocusPainted(false);
		buttonRedo.setEnabled(false);
        buttonRedo.addActionListener(gameController);

		JButton buttonSettings = new JButton("Settings");
        buttonSettings.setFocusPainted(false);
        buttonSettings.addActionListener(gameController);

		topControl.add(buttonUndo);
        topControl.add(buttonRedo);
        topControl.add(buttonSettings);

		add(topControl, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(new GridLayout(gameModel.getSize(), gameModel.getSize()));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));
        board = new DotButton[gameModel.getSize()][gameModel.getSize()];
        for (int row = 0; row < gameModel.getSize(); row++) {
            for (int column = 0; column < gameModel.getSize(); column++) {
                board[row][column] = new DotButton(row, column, gameModel.getColor(row,column),
                    (gameModel.getSize() < 26 ? DotButton.MEDIUM_SIZE : DotButton.SMALL_SIZE));
                buttonPanel.add(board[row][column]);
				board[row][column].addActionListener(gameController);
            }
        }
    	add(buttonPanel, BorderLayout.CENTER);

		JPanel bottomControl = new JPanel();
        bottomControl.setBackground(Color.WHITE);
        scoreLabel = new JLabel();
		bottomControl.setBorder(BorderFactory.createEmptyBorder(20, 10, 0, 10));

        JButton buttonReset = new JButton("Reset");
        buttonReset.setFocusPainted(false);
        buttonReset.addActionListener(gameController);

        JButton buttonExit = new JButton("Quit");
        buttonExit.setFocusPainted(false);
        buttonExit.addActionListener(gameController);

        bottomControl.add(scoreLabel);
        bottomControl.add(buttonReset);
        bottomControl.add(buttonExit);

        add(bottomControl, BorderLayout.SOUTH);

		pack();
		setLocationRelativeTo(null);
    	setResizable(false);
    	setVisible(true);

    }

	/**
	 * Set game model reference
	 */
	public void setGameModel(GameModel gameModel) {
		this.gameModel = gameModel;
	}

    /**
     * update the status of the board's DotButton instances based on the current game model
     */
    public void update() {
        for(int i = 0; i < gameModel.getSize(); i++){
            for(int j = 0; j < gameModel.getSize(); j++){
                board[i][j].setColor(gameModel.getColor(i,j));
            }
        }
		if (gameModel.getNumberOfSteps() == -1) {
			scoreLabel.setText("Select initial dot");
		}
		else {
        	scoreLabel.setText("Number of steps: " + gameModel.getNumberOfSteps());
		}
		buttonUndo.setEnabled(gameController.isUndoEnabled());
		buttonRedo.setEnabled(gameController.isRedoEnabled());
    }

	/**
	 * Method to close settings panel from controller
	 */
	public void closeSettingsPane() {
		frame.dispose();
	}

	/**
	 * Creates the settings pane for the settings button
	 */
	public void settingsPane() {
		frame = new JFrame("Settings");
		JPanel optionPanel = new JPanel(new GridLayout(6, 1));
		JPanel buttonPanel = new JPanel();

		JLabel torusLabel = new JLabel();
		torusLabel.setText("Play on plane or torus?");

		JRadioButton planeButton = new JRadioButton("Plane", !gameModel.isTorus());
		JRadioButton torusButton = new JRadioButton("Torus", gameModel.isTorus());

		JLabel diagonalLabel = new JLabel();
		diagonalLabel.setText("Diagonal moves?");

		JRadioButton orthogonalButton = new JRadioButton("Orthogonal", !gameModel.isDiagonal());
		JRadioButton diagonalButton = new JRadioButton("Diagonal", gameModel.isDiagonal());

		ButtonGroup torusGroup = new ButtonGroup();
		ButtonGroup diagonalGroup = new ButtonGroup();

		optionPanel.add(torusLabel);
		optionPanel.add(planeButton);
		optionPanel.add(torusButton);
		optionPanel.add(diagonalLabel);
		optionPanel.add(orthogonalButton);
		optionPanel.add(diagonalButton);

		torusGroup.add(planeButton);
		torusGroup.add(torusButton);
		diagonalGroup.add(orthogonalButton);
		diagonalGroup.add(diagonalButton);

		planeButton.addActionListener(gameController);
		torusButton.addActionListener(gameController);
		orthogonalButton.addActionListener(gameController);
		diagonalButton.addActionListener(gameController);

		JButton OKConfirmation = new JButton("OK");
		OKConfirmation.setFocusPainted(false);
		OKConfirmation.addActionListener(gameController);
		buttonPanel.add(OKConfirmation, BorderLayout.EAST);

		frame.add(optionPanel, BorderLayout.NORTH);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		frame.setSize(180,180);
		frame.setLocationRelativeTo(this);
		frame.setVisible(true);
	}
}
