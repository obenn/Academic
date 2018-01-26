/**
 * La classe <b>FloodIt</b> lance l'application.
 *
 * @author Oliver Benning, 7798804. University of Ottawa.
 */
public class FloodIt {

    /**
     * Méthode principale. Cette méthode crée une instance de la classe
     * <b>GameController</b> et ainsi lance l'exécution de l'application.
     * L'usager peut donner la taille du jeu sur la ligne de commande. La taille
     * minimale valide est 10. S'il n'y a aucune valeur sur la ligne de commande
     * ou si la valeur est inférieure à 10, utilsez la valeur 12 comme taille.
     *
     * @param args
     *            référence vers un tableau de chaînes de cartères (les éléments
     *            passé sur la ligne de commande)
     */
    public static void main(String[] args) throws NumberFormatException{ // Prevents crash if user inputs NaN

        StudentInfo.display(); // Display student info in console

        int size = 12; // Default size value
        if (args.length >= 1) { // Set size to first arg if >= 10 and a number
            if (Integer.parseInt(args[0]) >= 10) {
                    size = Integer.parseInt(args[0]);
            }
        }

        new GameController(size); // Start game
    }
}
