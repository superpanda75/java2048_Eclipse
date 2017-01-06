package java2048.model;
 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;
 
public class Modele {
     
		//constantes permettant de gérer les génération de la grille avec la fonction GenerationGrille()
		//la constante DEBUG permet de tester la bonne sortie ou non de la méthode NewTuile()  
	private static final int NB_CELLULE = 16;
    private static final int LONGUEUR_GRILLE = 4;
    private static final boolean DEBUG = false;  
     
    private boolean directionActive;     
    private int meilleureScore;
    private int celluleMax;
    private int scoreActuel;
    private int celluleMaxActuelle;
     
    private Cellule[][] grille;
     
    private Random random;
     
    public Modele() {
        this.grille = new Cellule[LONGUEUR_GRILLE][LONGUEUR_GRILLE];
        this.random = new Random();
        this.meilleureScore = 0;
        this.celluleMax = 0;
        this.scoreActuel = 0;
        this.celluleMaxActuelle = 0;
        this.directionActive = false;
        generateurGrille();
    }
     
    public void generateurGrille() {
        int xx = NB_CELLULE;
        for (int x = 0; x < LONGUEUR_GRILLE; x++) {
            int yy = NB_CELLULE;
            for (int y = 0; y < LONGUEUR_GRILLE; y++) {
                Cellule cell = new Cellule(0);
                cell.setPositionCellule(xx, yy);
                grille[x][y] = cell;
                yy += NB_CELLULE + Cellule.getLongueurCellule();
            }
            xx += NB_CELLULE + Cellule.getLongueurCellule();
        }
    }
     
    public void setMeilleursScores() {
        meilleureScore = (scoreActuel > meilleureScore) ? 
                scoreActuel : meilleureScore;
        celluleMax = (celluleMaxActuelle > celluleMax) ?
                celluleMaxActuelle : celluleMax;
        scoreActuel = 0;
        celluleMaxActuelle = 0;
    }
     
    public boolean gameOver() {
        return grillePleine() && !deplacementPossible();
    }
     
    private boolean grillePleine() {
        for (int x = 0; x < LONGUEUR_GRILLE; x++) {
            for (int y = 0; y < LONGUEUR_GRILLE; y++) {
                if (grille[x][y].isZeroValue()) {
                    return false;
                }
            }
        }
        return true;
    }
     
    private boolean deplacementPossible() {
        for (int x = 0; x < LONGUEUR_GRILLE; x++) {
            for (int y = 0; y < (LONGUEUR_GRILLE - 1); y++) {
                int yy = y + 1;
                if (grille[x][y].getVar() == grille[x][yy].getVar()) {
                    return true;
                }
            }
        }
         
        for (int y = 0; y < LONGUEUR_GRILLE; y++) {
            for (int x = 0; x < (LONGUEUR_GRILLE - 1); x++) {
                int xx = x + 1;
                if (grille[x][y].getVar() == grille[xx][y].getVar()) {
                    return true;
                }
            }
        }
         
        return false;
    }
     
    public void ajouterCellule() {
        int value = (random.nextInt(10) < 9) ?  2 : 4;
         
        boolean locationFound = false;
        while(!locationFound) {
            int x = random.nextInt(LONGUEUR_GRILLE);
            int y = random.nextInt(LONGUEUR_GRILLE);
            if (grille[x][y].isZeroValue()) {
                grille[x][y].setVar(value);
                locationFound = true;
                if (DEBUG) {
                    System.out.println(affichageAjoutCellule(x, y));
                }
            }
        }
         
        updateScore(0, value);
    }
    
     //builder.append : le stringbuilder est en général utilisé non pas pour économiser de la mémoire, mais pour ses performances
    //le meme traitement avec un stringbuilder est beaucoup plus rapide qu'avec un string (réellement significatif sur certains traitements) 

    private String affichageAjoutCellule(int x, int y) {
        StringBuilder builder = new StringBuilder();
        builder.append("Cell added at [");
        builder.append(x);
        builder.append("][");
        builder.append(y);
        builder.append("].");
         
        return builder.toString();
    }
     
    public boolean deplacementHaut() {
        boolean dirty = false;
         
        if (boucleHaut())  dirty = true;
         
        for (int x = 0; x < LONGUEUR_GRILLE; x++) {
            for (int y = 0; y < (LONGUEUR_GRILLE - 1); y++) {
                int yy = y + 1;
                dirty = combinaisonCellules(x, yy, x, y, dirty);
            }
        }
         
        if (boucleHaut())  dirty = true;
         
        return dirty;
    }
     
    private boolean boucleHaut() {
        boolean dirty = false;         
        for (int x = 0; x < LONGUEUR_GRILLE; x++) {
            boolean columnDirty = false;
            do {
                columnDirty = false;
                for (int y = 0; y < (LONGUEUR_GRILLE - 1); y++) {
                    int yy = y + 1;
                    boolean cellDirty = deplacerCellule(x, yy, x, y);
                    if (cellDirty) {
                        columnDirty = true;
                        dirty = true;
                    }
                }
            } while (columnDirty);      
        }
         
        return dirty;
    }
     
    public boolean deplacementBas() {
        boolean dirty = false;
         
        if (boucleBas())    dirty = true;
         
        for (int x = 0; x < LONGUEUR_GRILLE; x++) {
            for (int y = LONGUEUR_GRILLE - 1; y > 0; y--) {
                int yy = y - 1;
                dirty = combinaisonCellules(x, yy, x, y, dirty);
            }
        }
         
        if (boucleBas())    dirty = true;
         
        return dirty;
    }
     
    private boolean boucleBas() {
        boolean dirty = false;
         
        for (int x = 0; x < LONGUEUR_GRILLE; x++) {
            boolean columnDirty = false;
            do {
                columnDirty = false;
                for (int y = LONGUEUR_GRILLE - 1; y > 0; y--) {
                    int yy = y - 1;
                    boolean cellDirty = deplacerCellule(x, yy, x, y);
                    if (cellDirty) {
                        columnDirty = true;
                        dirty = true;
                    }
                }
            } while (columnDirty);      
        }
         
        return dirty;
    }
     
    public boolean deplacementGauche() {
        boolean dirty = false;
         
        if (boucleGauche())    dirty = true;
         
        for (int y = 0; y < LONGUEUR_GRILLE; y++) {
            for (int x = 0; x < (LONGUEUR_GRILLE - 1); x++) {
                int xx = x + 1;
                dirty = combinaisonCellules(xx, y, x, y, dirty);
            }
        }
         
        if (boucleGauche())    dirty = true;
         
        return dirty;
    }
     
    private boolean boucleGauche() {
        boolean dirty = false;
         
        for (int y = 0; y < LONGUEUR_GRILLE; y++) {
            boolean rowDirty = false;
            do {
                rowDirty = false;
                for (int x = 0; x < (LONGUEUR_GRILLE - 1); x++) {
                    int xx = x + 1;
                    boolean cellDirty = deplacerCellule(xx, y, x, y);
                    if (cellDirty) {
                        rowDirty = true;
                        dirty = true;
                    }
                }
            } while (rowDirty);     
        }
         
        return dirty;
    }
     
    public boolean deplacementDroite() {
        boolean dirty = false;
         
        if (boucleDroite())   dirty = true;
         
        for (int y = 0; y < LONGUEUR_GRILLE; y++) {
            for (int x = (LONGUEUR_GRILLE - 1); x > 0; x--) {
                int xx = x - 1;
                dirty = combinaisonCellules(xx, y, x, y, dirty);
            }
        }
         
        if (boucleDroite())   dirty = true;
         
        return dirty;
    }
 
    private boolean boucleDroite() {
        boolean dirty = false;
         
        for (int y = 0; y < LONGUEUR_GRILLE; y++) {
            boolean rowDirty = false;
            do {
                rowDirty = false;
                for (int x = (LONGUEUR_GRILLE - 1); x > 0; x--) {
                    int xx = x - 1;
                    boolean cellDirty = deplacerCellule(xx, y, x, y);
                    if (cellDirty) {
                        rowDirty = true;
                        dirty = true;
                    }
                }
            } while (rowDirty);     
        }
         
        return dirty;
    }
     
    private boolean combinaisonCellules(int x1, int y1, int x2, int y2,
            boolean dirty) {
        if (!grille[x1][y1].isZeroValue()) {
            int value = grille[x1][y1].getVar();
            if (grille[x2][y2].getVar() == value) {
                int newValue = value + value;
                grille[x2][y2].setVar(newValue);
                grille[x1][y1].setVar(0);
                updateScore(newValue, newValue);
                dirty = true;
            }
        }
        return dirty;
    }
     
    private boolean deplacerCellule(int x1, int y1, int x2, int y2) {
        boolean dirty = false;
        if (!grille[x1][y1].isZeroValue() 
                && (grille[x2][y2].isZeroValue())) {
            if (DEBUG) {
                System.out.println(afficherDeplaceement(x1, y1, x2, y2));
            }
            int value = grille[x1][y1].getVar();
            grille[x2][y2].setVar(value);
            grille[x1][y1].setVar(0);
            dirty = true;
        }
        return dirty;
    }
     
    private String afficherDeplaceement(int x1, int y1, int x2, int y2) {
        StringBuilder builder = new StringBuilder();
        builder.append("Moving cell [");
        builder.append(x1);
        builder.append("][");
        builder.append(y1);
        builder.append("] to [");
        builder.append(x2);
        builder.append("][");
        builder.append(y2);
        builder.append("].");
         
        return builder.toString();
    }
     
    private void updateScore(int value, int cellValue) {
        scoreActuel += value;
        celluleMaxActuelle = (cellValue > celluleMaxActuelle) ? 
                cellValue : celluleMaxActuelle;
    }
     
    public Cellule getCellule(int x, int y) {
        return grille[x][y];
    }
     
    public int getMeilleureScore() {
        return meilleureScore;
    }
 
    public int getCelluleMax() {
        return celluleMax;
    }
 
    public void setMeilleureScore(int highScore) {
        this.meilleureScore = highScore;
    }
 
    public void setCelluleMax(int highCell) {
        this.celluleMax = highCell;
    }
 
    public int getScoreActuel() {
        return scoreActuel;
    }
 
    public int getCelluleMaxActuelle() {
        return celluleMaxActuelle;
    }
 
    public boolean isDirectionActive() {
        return directionActive;
    }
 
    public void activerClavier(boolean arrowActive) {
        this.directionActive = arrowActive;
    }
 
    public Dimension getTaillePref() {
        int width = LONGUEUR_GRILLE * Cellule.getLongueurCellule() + 
                NB_CELLULE * 5;
        return new Dimension(width, width);
    }
     
    public void draw(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        Dimension d = getTaillePref();
        g.fillRect(0, 0, d.width, d.height);
         
        for (int x = 0; x < LONGUEUR_GRILLE; x++) {
            for (int y = 0; y < LONGUEUR_GRILLE; y++) {
                grille[x][y].draw(g);
            }
        }
    }
 
}