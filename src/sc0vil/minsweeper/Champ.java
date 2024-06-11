package sc0vil.minsweeper;
import java.util.Random;

public class Champ {

	final static int[] DIM = {10,20,30};
	final static int[] NBMINESPARLVL = {5,20,30};
	private int nbMines;
	private int hauteur;
	private int largeur;
	public String niveau;
	
	private boolean tabMap[][];
	private Random alea = new Random();
	
	/**
	 * Constructeur du Champ par defaut
	 */
	Champ() {
		this(Level.EASY);
		niveau = Level.EASY.toString();
	}
	/**
	 * Constructeur du Champ par NIVEAU
	 */
	Champ(Level level) {
		this(NBMINESPARLVL[level.ordinal()],DIM[level.ordinal()],DIM[level.ordinal()]);
		if(level.equals(null))
			niveau = Level.CUSTOM.toString();
		else
			niveau = level.toString();
		this.nbMines = NBMINESPARLVL[level.ordinal()];
		this.hauteur = tabMap.length;
		this.largeur = tabMap[0].length;
	}
	/**
	 * Constructeur du Champ final qq soit le niveau
	 */
	Champ(int nbMines, int largeur, int hauteur) {
		this.nbMines = nbMines;
		this.hauteur = hauteur;
		this.hauteur = largeur;
		tabMap = new boolean[largeur][hauteur];
	}
	
	/**
	 * 
	 */
	public void placeMines() {
		int localNbMines = this.nbMines;
		while(localNbMines != 0) {
			int x = alea.nextInt(tabMap.length) ; // tirage au sort nb ∈ [0,DIM-1]
			int y = alea.nextInt(tabMap[0].length) ; // tirage au sort nb ∈ [0,DIM-1]
			if(!tabMap[x][y]){
				tabMap[x][y] = true ;
				localNbMines--;
			}
		}
	}
		
	/**
	 * 
	 */
	public void affText() {
		System.out.println("Niveau : "+niveau);
		System.out.println("---- DANGER ZONE ----");
		for(int i = 0; i < tabMap.length; i++) {
			for (int j = 0; j < tabMap[0].length; j++) {
				if(tabMap[i][j])
					System.out.print("X ");
				else
					System.out.print(nbMinesAutour(i, j)+" ");
			}
			System.out.println("");
		}
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	int nbMinesAutour(int x, int y) {
		int nb = 0;
		
		int bornSupX = x == tabMap.length - 1 ? x : x+1;
		int bornSupY = y == tabMap[0].length - 1 ? y : y+1;
		int bornInfX = x == 0 ? 0 : x-1;
		int bornInfY = y == 0 ? 0 : y-1;
		
		for(int i = bornInfX; i<=bornSupX; i++) {
			for(int j= bornInfY; j<=bornSupY; j++) {
				if(tabMap[i][j])
					nb++;
			}
		}
		return nb;
	}
	/**
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public boolean isMines(int i, int j) {
		return tabMap[i][j];
	}
	/**
	 * 
	 * @return
	 */
	public int getNbMines() {
		return this.nbMines;
	}
	/**
	 * 
	 * @param nbMines
	 */
	public void setNbMines(int nbMines) {
		this.nbMines = nbMines;
	}
	/**
	 * 
	 * @return
	 */
	public boolean[][] getTabMap() {
		return tabMap;
	}
	/**
	 * 
	 * @param tabMap
	 */
	public void setTabMap(boolean[][] tabMap) {
		this.tabMap = tabMap;
	}
	/**
	 * 
	 * @return
	 */
	public int getLargeur() {
		return this.largeur;
	}
	/**
	 * 
	 * @param largeur
	 */
	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}
	/**
	 * 
	 * @return
	 */
	public int getHauteur() {
		return this.hauteur;
	}
	/**
	 * 
	 * @param hauteur
	 */
	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}
}
