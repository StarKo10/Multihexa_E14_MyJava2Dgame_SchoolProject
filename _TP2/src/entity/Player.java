package entity;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	
	public Player(GamePanel gp, KeyHandler keyH) {	
		this.gp = gp;
		this.keyH = keyH;	
		
		// THIS IS WHERE WE PUT THE CHARACTER WITHIN THE SCREEN
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidArea.width = 32;
		solidArea.height = 32;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		// THIS IS THE STARTING POSITION
		worldX = gp.tileSize * 24;
		worldY = gp.tileSize * 22;
		speed = 4;
		direction = "up";
	}
	
	public void getPlayerImage() {
		try {			
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/HeroUp1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/HeroUp2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/HeroDown1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/HeroDown2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/HeroLeft1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/HeroLeft2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/HeroRight1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/HeroRight2.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {		
		if(keyH.upPressed == true || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
			if(keyH.upPressed == true) {
				direction = "up";		
			}
			else if(keyH.downPressed == true){
				direction = "down";
			}
			else if(keyH.leftPressed == true) {
				direction = "left";
			}
			else if(keyH.rightPressed == true) {
				direction = "right";
			}	
			
			// CHECK COLLISION
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			// IF COLLISION IS FALSE, PLAYER CAN MOVE
			if(collisionOn == false) {
				switch(direction) {
				case "up": worldY -= speed; break;
				case "down": worldY += speed; break;
				case "left": worldX -= speed; break;
				case "right": worldX += speed; break;
				}
			}
			
			imgCounter ++;
			if(imgCounter > 12) {
				if(imgNum == 1) {
					imgNum = 2;
				}
				else if(imgNum == 2) {
					imgNum = 1;
				}
				imgCounter = 0;
			}
		}	
	}
	
	public void draw(Graphics2D g2) {		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(imgNum == 1) {
				image = up1;
			}
			if(imgNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if(imgNum == 1) {
				image = down1;
			}
			if(imgNum == 2) {
				image = down2;
			}			
			break;
		case "left":
			if(imgNum == 1) {
				image = left1;
			}
			if(imgNum == 2) {
				image = left2;
			}		
			break;
		case "right":
			if(imgNum == 1) {
				image = right1;
			}
			if(imgNum == 2) {
				image = right2;
			}
			break;
		}
		g2.drawImage(image,  screenX,  screenY,  gp.tileSize,  gp.tileSize, null);		
	}
}