package java2048.view;
 
import java.awt.Graphics; 
 
import javax.swing.JPanel;
 
import java2048.model.Modele;
 
public class GestionGrille extends JPanel {
 
    private static final long   serialVersionUID    = 
            4019841629547494495L;
     
    private Modele modele;
     
    private GameOver image;
     
    public GestionGrille(Modele modele) {
        this.modele = modele;
        this.setPreferredSize(modele.getTaillePref()); //spécifie la taille préférée d'un composant. C'est la taille optimale du composant et le bon choix quand vous avez un layout manager.
        this.image = new GameOver(modele);
        this.image.run();
    }
     @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        modele.draw(g);
         
        if (modele.gameOver()) {
            g.drawImage(image.getImage(), 0, 0, null);
        }
    }
}