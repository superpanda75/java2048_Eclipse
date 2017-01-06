package java2048;

import javax.swing.SwingUtilities;

import java2048.model.Modele;
import java2048.view.Frame;
 
//C'est la Main Classe elle lance l'applicatation en instanciant la classe Frame et Modele

public class Java2048 implements Runnable {
 
    @Override
    public void run() {
        new Frame(new Modele());
    }
     
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Java2048());
    }
 
}