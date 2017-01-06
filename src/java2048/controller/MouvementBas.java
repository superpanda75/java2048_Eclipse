package java2048.controller;
 
import java.awt.event.ActionEvent; 
 
import javax.swing.AbstractAction;
 
import java2048.model.Modele;
import java2048.view.Frame;
 
//Cette classe permet d'effectuer un mouvement vers le bas, si ce n'est pas possible, la méthode 
//actionPerformed désactive le clavier pour le bas sinon met à jour les cellules

public class MouvementBas extends AbstractAction {
 
    private static final long serialVersionUID = 7347478777733160296L;
 
    private Frame monFrame;
     
    private Modele monModele;
 
    public MouvementBas(Frame frame, Modele modele) {
        this.monFrame = frame;
        this.monModele = modele;
    }
     
    @Override
    public void actionPerformed(ActionEvent event) {    
        if (monModele.isDirectionActive()) {
            if (monModele.deplacementBas()) {
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