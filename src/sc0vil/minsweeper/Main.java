package sc0vil.minsweeper;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;


public class Main extends JFrame {
	
	private Gui gui;
	private Champ map;
	
	private Socket sock;
	
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	
	private static String DEFAULTSERVER = "localhost";
	
	Main(Level level){
		//création d'une map avec mines
		map = new Champ(level);
		//Placer les mines
		map.placeMines(); 	
		// affichage des mines et de la map
		map.affText(); 
		//Display la JFrame
		gui = new Gui(this);
		//
		setSize(500,500);
		setContentPane(gui);
	
		//pack() => calcul la taille auto de la fenetre en fct des elements contenus dans le  GUI
		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Bienvenue dans le démineur !");
		new Main(Level.EASY);
	}
	/**
	 * 
	 * @return
	 */
	public Champ getChamp() {
		return map;
	}
	/**
	 * 
	 * @param level
	 */
	public void nouvellePartie(Level level) {
		new Main(level);
	}
	/**
	 * 
	 */
	public void startNetwork() {
		System.out.println("Tentative de connexion");
		try {
			sock = new Socket(DEFAULTSERVER, Server.PORT);
			inputStream = new DataInputStream(sock.getInputStream());
			outputStream = new DataOutputStream(sock.getOutputStream());
			
			outputStream.writeUTF("Wesh la team t'es frais le sang ! ");
			
		}
		catch(UnknownHostException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println("Connecté avec succès");
		
	}
}
