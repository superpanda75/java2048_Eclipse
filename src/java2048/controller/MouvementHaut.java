package java2048.controller;
 
import java.awt.event.ActionEvent; 
 
import javax.swing.AbstractAction;
 
import java2048.model.Modele;
import java2048.view.Frame;
 
//Cette classe permet d'effectuer un mouvement vers le haut, si ce n'est pas possible, la méthode 
//actionPerformed désactive le clavier pour le haut sinon met à jour les cellules

public class MouvementHaut extends AbstractAction {
 
    private static final long serialVersionUID = -2851527479086591525L;
     
    private Frame monFrame;
     
    private Modele monModele;
 
    public MouvementHaut(Frame frame, Modele modele) {
        this.monFrame = frame;
        this.monModele = modele;
    }
 
    @Override
    public void actionPerformed(ActionEvent event) {        
        if (monModele.isDirectionActive()) {
            if (monModele.deplacementHaut()) {
                if (monModele.gameOver()) {
                    monModele.activerClavier(false);
                } else {
                    addNewCell();
                }
            }
        }
    }
 
    private void addNewCell() {
        monModele.ajouterCellule();
         
        monFrame.recolorGrille();
        monFrame.updateScorePanel();
    }
 
 
 
}