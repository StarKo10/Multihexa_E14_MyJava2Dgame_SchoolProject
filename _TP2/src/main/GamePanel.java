package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Timer;
import javax.swing.JPanel;
import entity.Player;
import tile.TileManager;

//PARAMETRES DE L'ECRAN
public class GamePanel extends JPanel implements Runnable{
	final int originalTileSize = 16; // TUILES 16x16 
	final int scale = 3; // ECHELLE
	
	public final int tileSize = originalTileSize * scale; // TUILE 48x48 (16X3)
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; 
	public final int screenHeight = tileSize * maxScreenRow;
	
	// PARAMETRES DU MONDE
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	int FPS = 60; // IMAGES PAR SECONDES
		
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	public CollisionChecker cChecker = new CollisionChecker(this);
	public Player player = new Player(this,keyH);
	
	// CE SONT LES CARACTERISTIQUE DU GAMEPANEL
	public GamePanel() { 	
		this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //DIMENSION
		this.setBackground(Color.black); // COULEUR DU FOND
		this.setDoubleBuffered(true); // NOUS PERMET D'UTILISER NOS CREATIONS EN PROVENANCES D'AILLEURS 
		this.addKeyListener(keyH); // PERMET L'UTILISATION DES CONTROLE W,A,S,D POUR DEPLACER LE PERSO
		this.setFocusable(true); // MET LE FOCUS SUR L'ACTION DU KEYH
	}
	
	// OUVRE LE COMPTEUR DU JEU
	public void startGameThread() {	
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	// DELTA METHOD (POUR CREER UN MOUVEMENT DE 60 FPS)
	@Override
	public void run() {		
		double drawInterval = 1000000000/FPS; // NANO SECONDES (0.0166666 PAR SECONDES)
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
			
		// UTILISE LE SYSTEM DE THREAD BOUCLÉ POUR FAIRE ROULER LE JEU SANS ARRET
		while(gameThread != null) {			
			currentTime = System.nanoTime();			
			delta += (currentTime - lastTime) / drawInterval;			
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				repaint();
				delta--;
			}
		}		
	}
	
	// MODIFIE L'AFFICHAGE EN CONTINU
	public void update() {		
		player.update();
	}
	
	public void paintComponent(Graphics g) {		
		super.paintComponent(g);		
		Graphics2D g2 = (Graphics2D) g;		
		tileM.draw(g2); // ATTENTION CODER LE FOND AVANT LE PERSONNAGE		
		player.draw(g2);		
		g2.dispose();
	}
}