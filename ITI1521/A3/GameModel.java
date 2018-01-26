import java.util.Random;

/**
 * Un objet de la classe <b>GameModel</b> représente l'état du jeu. En
 * particulier, il faut sauvegarder les information suivantes:
 * <ul>
 * <li>L'état de chaque pastille de la grille de jeu (couleur, conquise ou
 * pas).</li>
 * <li>La taille du jeu.</li>
 * <li>Le nombre de tours.</li>
 * <li>La couleur courane.</li>
 * </ul>
 *
 * Le modèle fournit toutes les méthodes d'accès nécessaires pour que le
 * contrôleur et la vue puissent faire leur travail. *
 *
 * @author Oliver Benning, 7798804. University of Ottawa.
 */
public class GameModel {

    /**
     * Valeurs prédéfinies qu'on utilise pour DotInfo.
     */
    public static final int COLOR_0 = 0;
    public static final int COLOR_1 = 1;
    public static final int COLOR_2 = 2;
    public static final int COLOR_3 = 3;
    public static final int COLOR_4 = 4;
    public static final int COLOR_5 = 5;
    public static final int NUMBER_OF_COLORS = 6;
    private int size, numberOfSteps, currentSelectedColor;
    private DotInfo[][] dotArr;
    private Random rn = new Random();

    /**
     * Initialisation de l'objet.
     *
     * @param size
     *            la taille du jeu.
     */
    public GameModel(int size) {
        this.size = size;
        reset(); // Initialise dot grid with random values
    }

    /**
     * Remise à zéro du modèle.
     */
    public void reset() {

        dotArr = new DotInfo[size][size]; // Reset to initialised size

        for (int i = 0; i < size; i++) { // Traverse grid array, add random color dot
            for (int j = 0; j < size ; j++) {
                dotArr[i][j] = new DotInfo(i, j, rn.nextInt(NUMBER_OF_COLORS));
            }
        }

        // Game starts at top left, capture that dot and start with it's color
        dotArr[0][0].setCaptured(true);
        currentSelectedColor = dotArr[0][0].getColor();
        numberOfSteps = 0;
    }

    /**
     * Méthode d'accès pour l'attribut size.
     *
     * @return la valeu de l'attribut size.
     */
    public int getSize() {
      return size;
    }

    /**
     * Retourne la couleur de la pastille à la position spécifiée.
     *
     * @param i
     *            coordonnée en x.
     * @param j
     *            coordonnée en y.
     * @return la couleur de la pastille (i,j).
     */
    public int getColor(int i, int j) {
        return dotArr[i][j].getColor();
    }

    /**
     * Retourne true si la pastille (i,j) est conquise et false sinon.
     *
     * @param i
     *            coordonnée en x.
     * @param j
     *            coordonnée en y.
     * @return l'état de la pastille (i,j).
     */
    public boolean isCaptured(int i, int j) {
        return dotArr[i][j].isCaptured();
    }

    /**
     * Change l'état de la pastille (i,j).
     *
     * @param i
     *            coordonnée en x.
     * @param j
     *            coordonnée en y.
     */
    public void capture(int i, int j) {
        dotArr[i][j].setCaptured(true);
    }

    /**
     * Retourne le nombre de tours depuis la dernière remise à zéro.
     *
     * @return le nombre de tours.
     */
    public int getNumberOfSteps() {
        return numberOfSteps;
    }

    /**
     * Change la couleur sélectionnée.
     *
     * @param val
     *            la nouvelle couleur sélectionnée.
     */
    public void setCurrentSelectedColor(int val) {
        currentSelectedColor = val;
    }

    /**
     * Retourne la couleur sélectionnée.
     *
     * @return a couleur sélectionnée.
     */
    public int getCurrentSelectedColor() {
        return currentSelectedColor;
    }

    /**
     * Retourne la référence de l'objet DotInfo à la position (i,j) de la
     * grille.
     *
     * @param i
     *            coordonnée en x.
     * @param j
     *            coordonnée en y.
     *
     * @return la référence de l'objet DotInfo à la position (i,j) de la grille.
     */
    public DotInfo get(int i, int j) {
        return dotArr[i][j]; // Return DotInfo object at given coords
    }

    /**
     * Incrémente de un le nombre de tours.
     */
    public void step() {
        numberOfSteps++;
    }

    /**
     * Cette méthode <b>isFinished</b> retourne true si et seulement si le jeu
     * est terminé, c'est à dire que toutes les pastilles font partie de
     * l'ensemble conquises.
     *
     * @return true si le jeu est terminé, false sinon.
     */
    public boolean isFinished() {

      for (int i = 0; i < dotArr.length ; i++) { // Traverse grid
        for (int j = 0; j < dotArr[i].length; j++ ) {
          if (!dotArr[i][j].isCaptured()) {
            return false; // Found dot not captured
          }
        }
      }
      return true; // If traversed entire grid and found no un-captured dot
    }

    /**
     * Une représentation sous forme de chaîne de cartères du modèle.
     *
     * @return une représentation sous forme de chaîne de cartères du modèle.
     */
    public String toString() { // Not called, for testing
        char capchar;
        StringBuffer st = new StringBuffer();
        for (int i = 0; i < dotArr.length ; i++) {
            for (int j = 0; j < dotArr[i].length; j++ ) {
                if (dotArr[i][j].isCaptured()) {
                    st.append(currentSelectedColor);
                }
                else {
                    st.append(dotArr[i][j].getColor());
                }
                st.append(" ");
            }
            st.append("\n");
        }
        return st.toString();
    }
}
