import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

/**
 * Un <b>JButton</b> spécialisé afin de représenter une pastille du jeu. Affiche
 * l'une des six couleurs.
 *
 * Les icônes sont sauvegardées dans le répertoire data. Il y a 3 tailles:
 * normale (N), moyenne (M), et petite (S).
 *
 * Les images sont: ball-0.png (grise), ball-1.png (orange), ball-2.png (bleu),
 * ball-3.png (verte) ball-4.png (violet) ball-5.png (rouge).
 *
 * <a href=
 * "http://developer.apple.com/library/safari/#samplecode/Puzzler/Introduction/Intro.html%23//apple_ref/doc/uid/DTS10004409"
 * >Based on Puzzler by Apple</a>.
 *
 * @author Oliver Benning, 7798804. University of Ottawa.
 */
public class DotButton extends JButton {

    private int row, column, color, iconSize;

    /**
     * Inialise cet objet.
     *
     * @param row
     *            rangée.
     * @param column
     *            colonne.
     * @param color
     *            couleur.
     * @param iconSize
     *            taille de l'icône : SMALL_SIZE, MEDIUM_SIZE ou MEDIUM_SIZE.
     */
     public DotButton(int row, int column, int color, int iconSize) {

        this.row = row;
        this.column = column;
        this.color = color;
        this.iconSize = iconSize;
        ImageIcon icon;

        if (iconSize == 1) { // Set icon size based on passed parameter
            icon = new ImageIcon("data/S/ball-"+color+".png");
        } else {
            icon = new ImageIcon("data/M/ball-"+color+".png");
        }

        // Set JButton display properies
        setBackground(Color.WHITE);
        setIcon(icon);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setBorderPainted(false);
        setFocusPainted(false);
    }

    /**
     * Un second constructeur. Dans ce cas-ci, vous devez utiliser les valeur -1
     * et -1 comme rangée et colonne.
     *
     * @param color
     *            couleur
     * @param iconSize
     *            taille de l'icône : SMALL_SIZE, MEDIUM_SIZE ou MEDIUM_SIZE
     */
    public DotButton(int color, int iconSize) {

        this.row = -1; // Disregard row and column values
        this.column = -1;
        this.color = color;
        this.iconSize = iconSize;

        // Set JButton display properties
        ImageIcon icon = new ImageIcon("data/N/ball-"+color+".png");
        setIcon(icon);
        setBackground(Color.WHITE);
        setIcon(icon);
        Border emptyBorder = BorderFactory.createEmptyBorder(0, 0, 0, 0);
    	  setBorder(emptyBorder);
    	  setBorderPainted(false);
    }

    /**
     * Change la valeur de l'attribut color et met à jour l'image.
     *
     * @param color
     *            la nouvelle couleur
     */
    public void setColor(int color) {
        this.color = color;
    }

    /**
     * Retourne la valeur de l'attribut color.
     *
     * @return color
     */
    public int getColor() {
        return color;
    }

    /**
     * Retourne la valeur de l'attribut row.
     *
     * @return la valeur de l'attribut row.
     */

    public int getRow() {
        return row;
    }

    /**
     * Retourne la valeur de l'attribut column.
     *
     * @return la valeur de l'attribut column.
     */

    public int getColumn() {
        return column;
    }
}
