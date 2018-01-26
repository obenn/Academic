import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Un objet de la classe <b>GameController</b> sert de contrôleur pour cette
 * application. Sa méthode <b>selectColor</b> est appelée chaque fois que
 * l'usager sélectionne une nouvelle couleur. Il faut alors faire les
 * changements liés à l'opération «flood id» (inondation), mettre à jour le
 * modèle et informer la vue.
 *
 * @author Oliver Benning, 7798804. University of Ottawa.
 */

public class GameController implements ActionListener {

    private int size;
    private GameModel model;
    private GameView view;
    private FloodStack stack;

    /**
     * Initialisation du contrôleur. Il faut entre autres créer le modèle et la
     * vue.
     *
     * @param size
     *            la taille du jeu.
     */
    public GameController(int size) {

        this.size = size; // Store game dimension value
        model = new GameModel(size); // Initialise model
        view = new GameView(model, this); // Initialise view
        stack = new FloodStack(size); // Stack for selectColor()
        selectColor(model.getCurrentSelectedColor()); // Run once, prep values
    }

    /**
     * Remise à zéro du jeu.
     */
    public void reset() {
        model.reset();
        selectColor(model.getCurrentSelectedColor());
    }

    /**
     * Fonction de rappel. Cette méthode est appellée lorsque l'usager
     * sélectionne une couleur, ou qu'il appuit sur les boutons Quit ou Reset.
     *
     * @param e
     *            la référence d'un objet de la classe ActionEvent.
     */
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Reset")) { // When reset button pressed
          reset();
          view.update();
        }

        if (e.getActionCommand().equals("Quit")) { // When quit button pressed
          System.exit(0);
        }

        if (e.getSource() instanceof DotButton) { // When color selector dot pressed
          selectColor(((DotButton)e.getSource()).getColor()); // Set color to selected color
          model.step();
          view.update();
        }
    }

    /**
     * Cette méthode est appelée chaque fois que l'usager sélectionne une
     * nouvelle couleur. Si la couleur est différente de la couleur déjà
     * sélectionnée, on applique la logique du jeu en capturant le plus grand
     * nombre possible de pastilles. La méthode vérifie ensuite si le jeu est
     * terminé, auquel cas, la méthode félicite le joueur, affiche le nombre de
     * tours, et lui offre deux options, redémarrer ou quitter.
     *
     * @param color
     *            la couleur sélectionner par le joueur.
     */
    public void selectColor(int color) {

        model.setCurrentSelectedColor(color); // Update model
        DotInfo temp;

        loadStackWithCapturedDots();


        while (!stack.isEmpty()) {
            temp = stack.pop();
            captureAdjacentSquares(temp.getX(), temp.getY(), color);
        }
    }

    //Begin private methods

    private void loadStackWithCapturedDots() {

      //Traverse grid, push dot if is captured
      for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
          if (model.get(i,j).isCaptured()) {
            stack.push(model.get(i,j));
          }
        }
      }
    }

    private void captureAdjacentSquares(int x, int y, int color) {

      // Check if passed edge, check if adjacent, check if current color
      // Right
      if ((x-1 >= 0) && !model.get(x-1, y).isCaptured() && model.get(x-1, y).getColor() == color) {
          model.capture(x-1, y);
          stack.push(model.get(x-1, y));
      }
      // Left
      if ((x+1 < size) && !model.get(x+1, y).isCaptured() && model.get(x+1, y).getColor() == color) {
          model.capture(x+1, y);
          stack.push(model.get(x+1, y));
      }
      // Below
      if ((y-1 >= 0) && !model.get(x, y-1).isCaptured() && model.get(x, y-1).getColor() == color) {
          model.capture(x, y-1);
          stack.push(model.get(x, y-1));
      }
      // Above
      if ((y+1 < size) && !model.get(x, y+1).isCaptured() && model.get(x, y+1).getColor() == color) {
          model.capture(x, y+1);
          stack.push(model.get(x, y+1));
      }
    }
}
