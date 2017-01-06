package java2048.model;
 
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
 
//Cette classe est muni de plusieurs méthodes permettant de gérer l'etat des cellules

public class Cellule {
     
    private static final int LONGUEUR_CELLULE = 120;
     
    private int var;
     
    private Point positionCellule;
     
    public Cellule(int value) {
        setVar(value);
    }
 
    public static int getLongueurCellule() {
        return LONGUEUR_CELLULE;
    }
     
    public int getVar() {
        return var;
    }
 
    public void setVar(int value) {
        this.var = value;
    }
     
    public boolean isZeroValue() {
        return (var == 0);
    }
     
    public void setPositionCellule(int x, int y) {
        setPositionCellule(new Point(x, y));
    }
 
    public void setPositionCellule(Point cellLocation) {
        this.positionCellule = cellLocation;
    }
 
    public void draw(Graphics g) {
        if (var == 0) {
            g.setColor(Color.GRAY);
            g.fillRect(positionCellule.x, positionCellule.y, 
                    LONGUEUR_CELLULE, LONGUEUR_CELLULE);
        } else {        
            Font font = g.getFont();
            FontRenderContext frc = 
                    new FontRenderContext(null, true, true);
     
            String s = Integer.toString(var);
            BufferedImage image = 
                    createImage(font, frc, LONGUEUR_CELLULE, s);
             
            g.drawImage(image, positionCellule.x, positionCellule.y, null);
        }
    }
     
    private BufferedImage createImage(Font font, FontRenderContext frc,
            int width, String s) {
 
        Font largeFont = font.deriveFont((float) (width / 4));
        Rectangle2D r = largeFont.getStringBounds(s, frc); //Retourne un Rectangle dans lequel s'inscrit l'écriture de la chaîne de caractères.
        int rWidth = (int) Math.round(r.getWidth());
        int rHeight = (int) Math.round(r.getHeight());
        int rX = (int) Math.round(r.getX());
        int rY = (int) Math.round(r.getY());
 
        BufferedImage image = new BufferedImage(width, width,
                BufferedImage.TYPE_INT_RGB);
         
        Graphics gg = image.getGraphics();
        gg.setColor(getTileColor());
        gg.fillRect(0, 0, image.getWidth(), image.getHeight());
 
        int x = (width / 2) - (rWidth / 2) - rX;
        int y = (width / 2) - (rHeight / 2) - rY;
         
        gg.setFont(largeFont);
        gg.setColor(getTextColor());
        gg.drawString(s, x, y);
        gg.dispose();
        return image;
    }
     
    private Color getTileColor() {
        Color color = Color.WHITE;
         
        switch (var) {
            case 2:     color = Color.WHITE;
                        break;
            case 4:     color = Color.WHITE;
                        break;
            case 8:     color = new Color(255, 255, 170);
                        break;
            case 16:    color = new Color(255, 255, 128);
                        break;
            case 32:    color = new Color(255, 255, 85);
                        break;
            case 64:    color = new Color(255, 255, 43);
                        break;
            case 128:   color = new Color(255, 255, 0);
                        break;
            case 256:   color = new Color(213, 213, 0);
                        break;
            case 512:   color = new Color(170, 170, 0);
                        break;
            case 1024:  color = new Color(128, 128, 0);
                        break;
            case 2048:  color = new Color(85, 85, 0);
                        break;
            default:    color = new Color(43, 43, 0);
                        break;
        }
         
        return color;
    }
     
    private Color getTextColor() {
        return (var >= 256) ? Color.WHITE : Color.BLACK;
    }
}