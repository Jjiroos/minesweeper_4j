package sc0vil.minsweeper;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

public class Gui extends JPanel implements ActionListener {
	
	private Main main;
	
	private JPanel panelCenter = new JPanel();
	private JPanel panelNorth = new JPanel();
	private JPanel panelSouth = new JPanel();
	
	private JButton butQuit = new JButton("Quitter") ;
	private JMenuItem mQuit = new JMenuItem("Quitter") ;
	private JMenuItem mReseau = new JMenuItem("Reseau", KeyEvent.VK_N);
	private JMenuItem mNiveau = new JMenu("Nouvelle partie");
	private JMenuItem mEasy = new JMenuItem("Easy");
	private JMenuItem mMedium = new JMenuItem("Medium");
	private JMenuItem mHard = new JMenuItem("Hard");
	private JLabel titleLab = new JLabel("Bienvenue dans le d�mineur !");
	private JLabel score = new JLabel("Score :");
	private JLabel timeLabel = new JLabel("time");
	private int time = 0;
	private int largeurMap;
	private int hauteurMap;
	//init des label des mines
	//private JLabel [][] mines;
	public Case [][] map;
	public static int winCondition;
	public int nbCaseRevealed;
	
	
	Gui(Main main){
		this.main = main;
		largeurMap = main.getChamp().getLargeur();
		hauteurMap = main.getChamp().getHauteur();
		//nbMines = main.getChamp().getNbMines();
		nbCaseRevealed = 0;
		setLayout(new BorderLayout()); //postionnement NORD SUD EAST OUEST
		
		panelCenter.setLayout(new GridLayout(main.getChamp().getLargeur(),main.getChamp().getHauteur()));
		
		//cr�ation des panel sous forme de cadrant NORTH SUD CENTRE
		add(panelNorth, BorderLayout.NORTH);
		add(panelSouth, BorderLayout.SOUTH);
		add(panelCenter, BorderLayout.CENTER);
		
		//ajout du bouton quitter au panel sud, puis d'un titre au panel nord
		panelSouth.add(butQuit);
		panelSouth.setBackground(Color.gray);
		panelNorth.add(titleLab);
		panelSouth.add(score);
		panelNorth.setBackground(Color.cyan);
		
		Timer t = new Timer();
		t.schedule(new TimerTask() {

			public void run() {
			updateTime();
			}
		},0,1000);
		panelNorth.add(timeLabel);
		
		// cr�ation de la barre
		JMenuBar menuBar = new JMenuBar();
		// Le menu Partie
		JMenu menuPartie = new JMenu("Partie");
		menuBar.add(menuPartie) ;
		// Cr�ation des items du menu
		//cr�ation du sous menu niveau
		mNiveau.add(mEasy);
		mNiveau.add(mMedium);
		mNiveau.add(mHard);
		/*
		 * 
		 * */
		menuPartie.add(mNiveau);
		menuPartie.add(mReseau);
		menuPartie.add(mQuit);
		
		butQuit.addActionListener(this);
		mQuit.addActionListener(this);
		mReseau.addActionListener(this);
		mEasy.addActionListener(this);
		mMedium.addActionListener(this);
		mHard.addActionListener(this);
		main.setJMenuBar(menuBar) ; // main �tant la JFrame
		
		//cr�ation de la map sur l'interface
		map = new Case [largeurMap][hauteurMap];
		mapping(largeurMap,hauteurMap);
		//remplissage(main, largeurMap, hauteurMap);
		winCondition = map.length * map[0].length - getMain().getChamp().getNbMines();
		
		add(panelCenter, BorderLayout.CENTER);
	}
	
	/**
	 * 
	 */
	private void updateTime() {
		int min = time/60;
		int sec = time %60;
		timeLabel.setText((min<10 ?"0":"") + min + ":" + (sec<10 ? "0" : "")+ sec);
		time+=1;
	}

	/**
	*
	*/
	public void resetTime() {
		time=0;
	}
	
	@Override
	/**
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==butQuit || e.getSource()==mQuit)
			System.exit(0);
		else if(e.getSource()==mReseau) {
			System.out.println("Tentative R�seau");
			main.startNetwork();
		}
		else if(e.getSource()==mEasy) {
			//this.dispatchEvent(new WindowEvent(main, WindowEvent.WINDOW_CLOSING));
			//main.setVisible(false);
			main.dispose();
			main.nouvellePartie(Level.EASY);
		}
		else if(e.getSource()==mMedium) {
			main.dispose();
			main.nouvellePartie(Level.MEDIUM);
		}
		else if(e.getSource()==mHard) {
			main.dispose();
			main.nouvellePartie(Level.HARD);
		}
	}
	
	/**
	 * 
	 */
	public void mapping(int largeurMap, int hauteurMap) {
	//cr�ation du paneau de jeu avec les mines
		for(int i=0; i < largeurMap; i++) {
			for(int j=0; j < hauteurMap; j++) {
				map[i][j] = new Case(this,i,j);
				map[i][j].setBorder(new BevelBorder(1));
				panelCenter.add(map[i][j]);
			}
		}
	}
	
	/**
	 * 
	 */
	public void propagation(int nl, int nc) {
		int X = map[nl][nc].x;
		int Y = map[nl][nc].y;
		
		int bornOutMaxX = X + 1 > map.length - 1 ? X : X+1;
		int bornOutMaxY = Y + 1 > map[0].length - 1 ? Y : Y+1;
		int bornOutMinX = X - 1 < 0 ? X : X-1;
		int bornOutMinY = Y - 1 < 0 ? Y : Y-1;
		
		for(int i = bornOutMinX; i<=bornOutMaxX; i++) {
			for(int j= bornOutMinY; j<=bornOutMaxY; j++) {
				X = i;
				Y = j;
				if(this.getMain().getChamp().nbMinesAutour(X,Y) == 0 && !map[X][Y].isRevealed) {
					map[X][Y].isRevealed = true;
					map[X][Y].repaint();
					propagation(X,Y);
				}
				else if(this.getMain().getChamp().nbMinesAutour(X,Y)!=0){
					if(this.getMain().getChamp().isMines(X,Y)){
						//on fait rien
					}
					else {
						map[X][Y].isRevealed = true;
						repaint();
					}
				}
			}
		}
	}
	
	public void score() {
		/*JLabel score = new JLabel("Score :");
		panelSouth.add(score);*/
	}
	
	public void lost(boolean lose) { 
		int fin = JOptionPane.showConfirmDialog(null, "Dommage Willem Dafoe t'as trouv�... Tu rejoue ?","Option", JOptionPane.YES_NO_CANCEL_OPTION);
		if(fin==JOptionPane.YES_OPTION) {
			this.main.dispose();
			this.main.nouvellePartie(Level.EASY);
		}
		else if(fin==JOptionPane.NO_OPTION){
			System.exit(0);
		}
		else if(fin==JOptionPane.CANCEL_OPTION){
			
		}
	}
	
	public void win(boolean win) {
		int fin = JOptionPane.showConfirmDialog(null, "Bien jou� tu as echapp� au bouffon vert ! Tu rejoue ?","Option", JOptionPane.YES_NO_CANCEL_OPTION);
		if(fin==JOptionPane.YES_OPTION) {
			this.main.dispose();
			this.main.nouvellePartie(Level.EASY);
		}
		else if(fin==JOptionPane.NO_OPTION){
			System.exit(0);
		}
		else if(fin==JOptionPane.CANCEL_OPTION){
			
		}
	}
	
	public Main getMain() {
		return main;
	}
	
}
