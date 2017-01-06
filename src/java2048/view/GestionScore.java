package java2048.view;
 
import java.awt.Component; 
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.NumberFormat;
 
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
 
import java2048.model.Modele;
 
public class GestionScore {
     
    private static final Insets regularInsets   = 
            new Insets(10, 10, 0, 10);
    private static final Insets spaceInsets = 
            new Insets(10, 10, 10, 10);
     
    private static final NumberFormat nf =
            NumberFormat.getInstance();
     
    private Modele modele;
     
    private JPanel panel;
     
    private JTextField meilleurScore;
    private JTextField celluleMax;
    private JTextField meilleurScoreCellule;
    private JTextField maxCelulleActuel;
 
    public GestionScore(Modele modele) {
        this.modele = modele;
        createPartControl();
        updatePartControl();
    }
     
    private void createPartControl() {
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
 
        int gridy = 0;
         
        JLabel highScoreLabel = new JLabel("Champion:");
        addComponent(panel, highScoreLabel, 0, gridy, 1, 1, 
                regularInsets, GridBagConstraints.LINE_START, 
                GridBagConstraints.HORIZONTAL);
         
        meilleurScore = new JTextField(6);
        meilleurScore.setEditable(false);
        meilleurScore.setHorizontalAlignment(JTextField.RIGHT);
        addComponent(panel, meilleurScore, 1, gridy++, 1, 1, 
                regularInsets, GridBagConstraints.LINE_START, 
                GridBagConstraints.HORIZONTAL);
         
        JLabel highCellLabel = new JLabel("Score cellule:");
        addComponent(panel, highCellLabel, 0, gridy, 1, 1, 
                spaceInsets, GridBagConstraints.LINE_START, 
                GridBagConstraints.HORIZONTAL);
         
        celluleMax = new JTextField(6);
        celluleMax.setEditable(false);
        celluleMax.setHorizontalAlignment(JTextField.RIGHT);
        addComponent(panel, celluleMax, 1, gridy++, 1, 1, 
                spaceInsets, GridBagConstraints.LINE_START, 
                GridBagConstraints.HORIZONTAL);
         
        JLabel currentScoreLabel = new JLabel("Score:");
        addComponent(panel, currentScoreLabel, 0, gridy, 1, 1, 
                regularInsets, GridBagConstraints.LINE_START, 
                GridBagConstraints.HORIZONTAL);
         
        meilleurScoreCellule = new JTextField(6);
        meilleurScoreCellule.setEditable(false);
        meilleurScoreCellule.setHorizontalAlignment(JTextField.RIGHT);
        addComponent(panel, meilleurScoreCellule, 1, gridy++, 1, 1, 
                regularInsets, GridBagConstraints.LINE_START, 
                GridBagConstraints.HORIZONTAL);
         
        JLabel currentCellLabel = new JLabel("Max cellule:");
        addComponent(panel, currentCellLabel, 0, gridy, 1, 1, 
                regularInsets, GridBagConstraints.LINE_START, 
                GridBagConstraints.HORIZONTAL);
         
        maxCelulleActuel = new JTextField(6);
        maxCelulleActuel.setEditable(false);
        maxCelulleActuel.setHorizontalAlignment(JTextField.RIGHT);
        addComponent(panel, maxCelulleActuel, 1, gridy++, 1, 1, 
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
     
    public void updatePartControl() {
        meilleurScore.setText(nf.format(modele.getMeilleureScore()));
        celluleMax.setText(nf.format(modele.getCelluleMax()));
        meilleurScoreCellule.setText(nf.format(modele.getScoreActuel()));
        maxCelulleActuel.setText(nf.format(modele.getCelluleMaxActuelle()));
    }
 
    public JPanel getPanel() {
        return panel;
    }
}