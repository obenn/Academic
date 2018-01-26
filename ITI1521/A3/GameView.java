import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

/**
 * La classe <b>GameView</b> produit le rendu visuel de l'application. C'est une
 * sous-classe de la clases <b>JFrame</b>. Cette classe utilise une instance de
 * la classe <b>JPanel</b> pour afficher les pastilles du jeu, ainsi que deux
 * instances de la classe <b>JButton</b>. Le gestionnaire d'événement pour les
 * boutons est un objet de la classe <b>GameController</b>.
 *
 * @author Oliver Benning, 7798804. University of Ottawa.
 */

public class GameView extends JFrame {

    private GameModel model;
    private GameController gameController;
    private JLabel count;
    private JPanel bottomPanel, selectorPanel, gamePanel;
    private JOptionPane finished;
    private JButton reset, quit, dot;
    private int size, iconSize;


    /**
     * Le constructeur de la classe <b>GameView</b>. Construit le rendu visuel
     * de l'application.
     *
     * @param model
     *            la référence du modèle du jeu (déjà initialisé)
     * @param gameController
     *            la référence du contrôleur
     */
    public GameView(GameModel model, GameController gameController) {

      super("Flood-It"); // Set window title
      this.model = model;
      this.gameController = gameController;
      size = model.getSize(); // Store model size, constant and important value

      if (size > 25) { // Set iconSize based on grid size
        iconSize = 1; // Small
      } else {
        iconSize = 0; // Medium
      }

      // JFrame properties
      setVisible(true);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setResizable(false);
      setBackground(Color.WHITE);

      //gamePanel stores and displays the dot grid itsself
      gamePanel = new JPanel(new GridLayout (size, size), (size <= 25));

      // Eliminate redundant code,
      count = new JLabel(); // instanciate early to avoid null pointer
      update(); // allocate dot values and input label string.

      // selectorPanel stores and displays the bubbles used to select desired color
      selectorPanel = new JPanel(new FlowLayout(), (size <= 25));
      for (int i = 0; i < 6; i++) {
        JButton selector = new DotButton(i, 0);
        selector.addActionListener(gameController);
        selectorPanel.add(selector);
      }
      selectorPanel.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.gray));
      selectorPanel.setBackground(Color.WHITE);
      add(selectorPanel, BorderLayout.CENTER);

      // bottomPanel contains the Quit and Reset buttons, and the turn counter
      bottomPanel = new JPanel();
      bottomPanel.add(count);

      reset = new JButton("Reset");
      reset.addActionListener(gameController);
      bottomPanel.add(reset);

      quit = new JButton("Quit");
      quit.addActionListener(gameController);
      bottomPanel.add(quit);


      add(bottomPanel, BorderLayout.SOUTH);

      pack(); // Set window size based on JPanels
    }

    /**
     * Met à jour chaque pastille du jeu (DotButton) en fonction de
     * l'information se trouvant dans le modèle.
     */
    public void update() {

      gamePanel.removeAll(); // Clear dot grid and reallocate, current color if captured
      for (int i = 0; i < size; i++) {
        for (int j = 0; j < size ; j++) {
          if (model.get(i,j).isCaptured()) {
            gamePanel.add(new DotButton(i, j, model.getCurrentSelectedColor(), iconSize));
          } else {
          gamePanel.add(new DotButton(i, j, model.get(i,j).getColor(), iconSize));
          }
        }
      }
      gamePanel.updateUI();
      add(gamePanel, BorderLayout.NORTH);

      // Set count JLabel to current value
      count.setText("Number of steps: " + model.getNumberOfSteps());

      // Display final window if board flooded
      if (model.isFinished()) {
        String done = "Congratulations, you've won in "+model.getNumberOfSteps() + " steps!\nWould you like to play again?";
        String[] buttons = {"Quit", "Play Again"};
        ImageIcon icon = new ImageIcon("data/N/ball-"+model.getCurrentSelectedColor()+".png");
        int reply = JOptionPane.showOptionDialog(this, done, "Won!", 0, 0, icon, buttons, buttons[1]);
        if (reply == 1) {
          model.reset();
          update();
        } else {
          System.exit(0);
        }
      }
    }
} // End class
