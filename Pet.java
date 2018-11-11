import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pet
{
	//hunger range 0.0 - 10.0, if 0, momo dies.
	private double hunger;
	//thirst range 0.0 - 10.0, if 0, momo dies.
	private double thirst;
	//mood range 0.0 - 10.0, if 0, momo dies of being unhappy.
	private double mood;
	//number stats are decremented by each update;
	public double decrement = 0.001;
	
	public BufferedImage currentImage; 
	public Pet(){
		hunger = 10.0;
		thirst = 10.0;
		mood = 10.0;
		
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("./basic_kirby_sheet__superstar_ultra_style__by_boomerbro6-d4qyeeu.png"));
		} catch (IOException e) {
			System.out.println("Error loading image");
		}
		
		currentImage = img;
		
		
		
		
		
		
		
		
	}
	//updates the stats every time the game updates called
	public void update() {
		hunger = hunger - decrement;
		thirst = thirst - decrement;
		mood = mood - decrement;
	}
	
	//returns stats, if 0.0, momo dies
	public double getHunger() {
		if(hunger == 0.0) {
			death();
		}
		return hunger;
	}
	public double getThirst() {
		if(thirst == 0.0) {
			death();
		}
		return thirst;
	}
	public double getMood() {
		if(mood == 0.0) {
			death();
		}
		return mood;
	}
	//Called when user plays with momo, increase's mood
	public void play() {
		mood += 1.0;
	}
	public void feed(int x, int y) {
		hunger += 5.0;
	}
	public void water() {
		thirst += 5.0;
	}
	
	public void draw(Graphics g) {
		g.drawImage(currentImage,50,50,null);
	}
	public void death() {
		//Momo ded :(
	}
	
	
}
