package java2048.view;
 
import java.awt.FlowLayout; 
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
 
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
 
import java2048.controller.MouvementBas;
import java2048.controller.MouvementGauche;
import java2048.controller.MouvementDroite;
import java2048.controller.MouvementHaut;
import java2048.model.Modele;
import java2048.properties.MeilleursScores;
 
public class Frame {
     
    private GestionDirection gestionDirection;
     
    private Modele monModele;
     
    private GestionGrille gestionGrille;
     
    private MeilleursScores meilleursScores;
     
    private JFrame monFrame;
     
    private GestionScore gestionScore;
     
    public Frame(Modele modele) {
        this.monModele = modele;
        this.meilleursScores = new MeilleursScores(modele);
        this.meilleursScores.loadProperties();
        createPartControl();
    }
 
    private void createPartControl() {
        gestionGrille = new GestionGrille(monModele);
        gestionScore = new GestionScore(monModele);
        gestionDirection = new GestionDirection(this, monModele);
         
        monFrame = new JFrame();
        monFrame.setTitle("2048");
        monFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        monFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                exitProcedure();
            }
        });
         
        setKeyBindings();
 
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());
        mainPanel.add(gestionGrille);   
        mainPanel.add(createSidePanel());
 
        monFrame.add(mainPanel);
        monFrame.setLocationByPlatform(true);
        monFrame.pack();
        monFrame.setVisible(true);
    }
 
    private JPanel createSidePanel() {
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, 
                BoxLayout.PAGE_AXIS));
        sidePanel.add(gestionScore.getPanel());
        sidePanel.add(Box.createVerticalStrut(30));
        sidePanel.add(gestionDirection.getPanel());
        return sidePanel;
    }
     
    private void setKeyBindings() {
        InputMap inputMap = 
                gestionGrille.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke("W"), "up arrow");
        inputMap.put(KeyStroke.getKeyStroke("S"), "down arrow");
        inputMap.put(KeyStroke.getKeyStroke("A"), "left arrow");
        inputMap.put(KeyStroke.getKeyStroke("D"), "right arrow");
         
        inputMap.put(KeyStroke.getKeyStroke("UP"), "up arrow");
        inputMap.put(KeyStroke.getKeyStroke("DOWN"), "down arrow");
        inputMap.put(KeyStroke.getKeyStroke("LEFT"), "left arrow");
        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "right arrow");
         
        inputMap = gestionGrille.getInputMap(JPanel.WHEN_FOCUSED);
        inputMap.put(KeyStroke.getKeyStroke("UP"), "up arrow");
        inputMap.put(KeyStroke.getKeyStroke("DOWN"), "down arrow");
        inputMap.put(KeyStroke.getKeyStroke("LEFT"), "left arrow");
        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "right arrow");
 
         
        gestionGrille.getActionMap().put("up arrow", 
                new MouvementHaut(this, monModele));
        gestionGrille.getActionMap().put("down arrow", 
                new MouvementBas(this, monModele));
        gestionGrille.getActionMap().put("left arrow", 
                new MouvementGauche(this, monModele));
        gestionGrille.getActionMap().put("right arrow", 
                new MouvementDroite(this, monModele));
    }
     
    public void exitProcedure() {
        monModele.setMeilleursScores();
        meilleursScores.saveProperties();
        monFrame.dispose();
        System.exit(0);
    }
     
    public void recolorGrille() {
        gestionGrille.repaint();
    }
 
    public void updateScorePanel() {
        gestionScore.updatePartControl();
    }
 
}