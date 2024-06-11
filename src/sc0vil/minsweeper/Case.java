package sc0vil.minsweeper;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class Case extends JLabel implements MouseListener{
	
	final static int SIZE = 50;
	Color couleur = Color.LIGHT_GRAY;
	private Gui gui;
	public int x;
	public int y;
	public boolean isRevealed;
	private boolean lose;
	private boolean win;
	private boolean isFlag;
	private Image bombImage;
	private Image flag;

	
	Case(Gui gui, int  x, int y) {
		isRevealed = false;
		lose = false;
		win = false;
		this.gui = gui;
		this.x = x;
		this.y = y;
		ImageIcon bomb = new ImageIcon("./resources/willemdafoe.jpg");
		bombImage = bomb.getImage();
		ImageIcon flagJeu = new ImageIcon("./resources/drapeau-france.jpg");
		flag = flagJeu.getImage();
		//imgBomb = new ImageIcon(getClass().getRessource("/exit.png")).getImage();
		setPreferredSize(new Dimension (SIZE, SIZE));
		addMouseListener(this);
	}
	
	protected void paintComponent(Graphics gc) {
		super.paintComponent(gc);
		if(isFlag) {
			gc.drawImage(flag,0,0,this.getWidth(), this.getHeight(), null);
		}
		else if(isRevealed) {
			if(gui.nbCaseRevealed < gui.winCondition) {
				gui.nbCaseRevealed++;
			}
			if(gui.getMain().getChamp().isMines(x,y)) {
				gc.drawImage(bombImage,0,0,this.getWidth(), this.getHeight(), null);
			}
			else {
				switch(gui.getMain().getChamp().nbMinesAutour(x,y)) {
				case 0 :
				//la couleur en position (x,y) je la colore car pas de mines
					gc.setColor(Color.green);
					gc.drawString("", SIZE/2, SIZE/2);
					gc.setFont(getFont());
				break;
				case 1 :
					gc.setColor(Color.green);
					gc.drawString(String.valueOf(gui.getMain().getChamp().nbMinesAutour(x,y)), SIZE/2, SIZE/2);
					gc.setFont(getFont());
				break;
				case 2 :
					gc.setColor(Color.orange);
					gc.drawString(String.valueOf(gui.getMain().getChamp().nbMinesAutour(x,y)), SIZE/2, SIZE/2);
					gc.setFont(getFont());
				break;
				default :
					gc.setColor(Color.red);
					gc.drawString(String.valueOf(gui.getMain().getChamp().nbMinesAutour(x,y)), SIZE/2, SIZE/2);
					gc.setFont(getFont());
				break;
				
				}	
			}
		}
		else if(!isRevealed){
			gc.setColor(couleur);
			gc.fillRect(0,0,this.getWidth(),this.getHeight());
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("nb cases revelees : " + gui.nbCaseRevealed);
		System.out.println("Wincondition : " + gui.winCondition);
		System.out.println("nb mines : " + gui.getMain().getChamp().getNbMines());
		gui.score();
		if(e.getButton() == MouseEvent.BUTTON1) {
			
			if(gui.nbCaseRevealed == gui.winCondition) {
				System.out.println("bravo");
				win = true;
				isRevealed = true;
				repaint();
				gui.win(win);
			}
			else if(gui.getMain().getChamp().isMines(x,y)) {
				lose = true;
				isRevealed = true;
				repaint();
				gui.lost(lose);
			}
			
			if(gui.getMain().getChamp().nbMinesAutour(x,y)==0){
				gui.propagation(x,y);		
			}
			else {
				isRevealed = true;
				repaint();
			}
		}
		else if(e.getButton() == MouseEvent.BUTTON3) {
			isFlag = !isFlag;
			repaint();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
