package java2048.controller;
 
import java.awt.event.ActionEvent; 
 
import javax.swing.AbstractAction;
 
import java2048.model.Modele;
import java2048.view.Frame;
 //Cette classe permet d'effectuer un mouvement sur la gauche, si ce n'est pas possible, la méthode 
//actionPerformed désactive le clavier pour la gauche sinon met à jour les cellules
public class MouvementGauche extends AbstractAction {
 
    private static final long serialVersionUID = 863330348471372562L;
 
    private Frame monFrame;
     
    private Modele monModele;
 
    public MouvementGauche(Frame frame, Modele modele) {
        this.monFrame = frame;
        this.monModele = modele;
    }
     
    @Override
    public void actionPerformed(ActionEvent e) {
        if (monModele.isDirectionActive()) {
            if (monModele.deplacementGauche()) {
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