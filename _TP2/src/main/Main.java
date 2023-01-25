package main;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {		
		// PREPARATION DE L'ECRAN
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("2D Adventure");
		
		// PREMARATION DE LA TUILE JOUABLE
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);		
		window.pack();
		
		// VISIBILITE
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		// APPEL DU THREAD
		gamePanel.startGameThread();		
	}
}