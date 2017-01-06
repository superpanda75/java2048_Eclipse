package java2048.view;
 
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
 
import javax.swing.JButton;
import javax.swing.JPanel;
 
import java2048.controller.StartGame;
import java2048.model.Modele;
 
public class GestionDirection {
     
    private static final Insets regularInsets   = 
            new Insets(10, 10, 0, 10);
     
    private Frame frame;
     
    private Modele modele;
     
    private JPanel panel;
 
    public GestionDirection(Frame frame, Modele modele) {
        this.frame = frame;
        this.modele = modele;
        createPartControl();
    }
     
    private void createPartControl() {
        StartGame listener = 
                new StartGame(frame, modele);
         
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
 
        int gridy = 0;
         
        JButton startGameButton = new JButton("Start Game");
        startGameButton.addActionListener(listener);
        addComponent(panel, startGameButton, 0, gridy++, 1, 1, 
                regularInsets, GridBagConstraints.LINE_START, 
                GridBagConstraints.HORIZONTAL);
    }
 
    private void addComponent(Container container, Component component,
            int gridx, int gridy, int gridwidth, int gridheight, 
            Insets insets, int anchor, int fill) {
        GridBagConstraints gbc = new GridBagConstraints(gridx, gridy,
                gridwidth, gridheight, 1.0D, 1.0D, anchor, fill, 
                insets, 0, 0);
        container.add(component, gbc);
    }
     
    public JPanel getPanel() {
        return panel;
    }
}