package java2048.controller;
 
import java.awt.event.ActionEvent;

 
import javax.swing.AbstractAction;
 
import java2048.model.Modele;
import java2048.view.Frame;
 
//Cette classe permet d'effectuer un mouvement vers la droite, si ce n'est pas possible, la méthode 
//actionPerformed désactive le clavier pour la droite sinon met à jour les cellules

public class MouvementDroite extends AbstractAction {
 
    private static final long serialVersionUID = 2982995823948983992L;
    private Modele monModele;    
    private Frame monFrame;  
 
    public MouvementDroite(Frame frame, Modele modele) {
        this.monFrame = frame;
        this.monModele = modele;
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
        if (monModele.isDirectionActive()) {
            if (monModele.deplacementDroite()) {
                if (monModele.gameOver()) {
                    monModele.activerClavier(false);
                } else {
                    monModele.ajouterCellule();
                     
                    monFrame.recolorGrille();
                    monFrame.updateScorePanel();
                }
            }
        }
    }
 
}