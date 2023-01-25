package entity;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {	
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2; // INSTANCES DES IMAGES CREES EN PIXELS 16*12 
	
	//public int x, y; // DETERMINE LA POSITION DU PERSONNAGE SUR LA CARTE (MAP PART)
	public int worldX, worldY; 
	
	public int speed; // DETERMINE A QUELLE VITESSE LE PERSONNAGE SE DEPLACE SUR LA CARTE	
	public String direction; 	
	public int imgCounter = 0;
	public int imgNum = 1;	
	
	public Rectangle solidArea;
	public boolean collisionOn = false;
}