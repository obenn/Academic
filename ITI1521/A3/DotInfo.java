/**
 * Une classe auxilliaire (<em>helper</em>) pour le modèle afin de sauvegarder
 * la couleur d'une pastille et sont état (conquise ou pas), ainsi que sa
 * position sur la grille (x,y).
 *
 * @author Oliver Benning, 7798804. University of Ottawa.
 */
public class DotInfo {

    private int x, y, color;
    private boolean captured;

    /**
     * Le constructeur initialise les variables d'instances.
     *
     * @param x
     *            la coordonnée en x.
     * @param y
     *            la coordonnée en y.
     * @param color
     *            la couleur initiale.
     */
    public DotInfo(int x, int y, int color) {
        this.x = x; // x coordinate
        this.y = y; // y coordinate
        this.color = color; // int value in [0,6]
    }

    /**
     * Une méthode d'accès pour l'attribut x.
     *
     * @return la valeur de l'attribut x.
     */
    public int getX() {
        return x;
    }

    /**
     * Une méthode d'accès pour l'attribut y.
     *
     * @return la valeur de l'attribut y.
     */
    public int getY() {
        return y;
    }

    /**
     * Méthode d'accès en écriture (setter) pour l'attribut captured.
     *
     * @param captured
     *            la nouvelle valeur pour l'attribut captured
     */
    public void setCaptured(boolean captured) {
        this.captured = captured;
    }

    /**
     * Méthode d'accès en lecture pour l'attribut captured.
     *
     * @return captured la valeur de l'attribut.
     */
    public boolean isCaptured() {
        return captured;
    }

    /**
     * Méthode d'accès en lecture pour l'attribut color.
     *
     * @return color la valeur de l'attribut.
     */
    public int getColor() {
        return color;
    }
}
