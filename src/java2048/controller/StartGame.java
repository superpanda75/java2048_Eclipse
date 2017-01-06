package java2048.controller;
 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
 
import java2048.model.Modele;
import java2048.view.Frame;
 
//Cette classe permet d'initialiser la partie
public class StartGame implements ActionListener {
     
    private Frame monFrame;     
    private Modele monModele;
     
    public StartGame(Frame frame, 
            Modele modele) {
        this.monFrame = frame;
        this.monModele = modele;
    }
 
    
    //Cette méthode est la porte d'entrée de not application, elle instancie : la feuille des scores,
    //generateur de la grille, directions actives (pour empêche de jouer lors d'un game Over nottament),
    //et ajoute deux cellules de valeurs random ( voir ajouterCellule() )
    @Override
    public void actionPerformed(ActionEvent event) {
        monModele.setMeilleursScores();
        monModele.generateurGrille();
        monModele.activerClavier(true);
        monModele.ajouterCellule();
        monModele.ajouterCellule();
         
        monFrame.recolorGrille();
        monFrame.updateScorePanel();
    }
 
}