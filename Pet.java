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
	final int width = 93;
	final int height = 100;
	
	final int Kirbywidth = 450;
	final int Kirbyheight = 450;
	public BufferedImage[] sad;
	public BufferedImage[] angry;
	public BufferedImage[] idle;
	public BufferedImage[] happy;
	public BufferedImage currentImage; 
	private int counter = 0;
	
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
		sad = new BufferedImage[3];
		for(int i =0; i< 3; i++) {
			sad[i] = img.getSubimage(1891 +(i*(width)), 1250, width, height);
		}
		
		angry = new BufferedImage[2];
		for(int i =0; i< 2; i++) {
			angry[i] = img.getSubimage(1891 +(i*(width)), 1250 + height, width, height);
		}
		
		currentImage = angry[0];
		
		
		
		
		
		
		
		
	}
	//updates the stats every time the game updates called
	public void update() {
		hunger = hunger - decrement;
		thirst = thirst - decrement;
		mood = mood - decrement;
		updateCurrentImage();
	}
	public void updateCurrentImage() {
		boolean forever = true;
		counter ++;
		currentImage = sad[counter];
		if (counter == 2) {
				counter = 0;
			}
		
		
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
	public void water(int x, int y) {
		thirst += 5.0;
	}
	
	public void draw(Graphics g) {
		g.drawImage(currentImage,Kirbywidth,Kirbyheight,null);
	}
	public void death() {
		//Momo ded :(
	}

	
	
}
