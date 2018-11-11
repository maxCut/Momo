import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

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
	public double decrement = 0.005;
	final int width = 93;
	final int height = 100;
	
	final int Kirbywidth = 450;
	final int Kirbyheight = 450;
	
	public BufferedImage[] sad;
	public BufferedImage[] angry;
	public BufferedImage[] idle;
	public BufferedImage[] happy;
	public BufferedImage[] eating;
	public BufferedImage[] jumping;
	public BufferedImage currentImage; 
	public BufferedImage dead;
	
	private int counter = 0;
	private int delay = 0;
	private boolean isFeeding = false;
	private boolean isPlaying = false;
	public boolean isDead = false;
    public ThoughtBubble mind = new ThoughtBubble();
	public Pet(){
        mind.think("Hi, I am momo!");
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
		angry[0] = img.getSubimage(1891 , 1250 + height, width, height);
		angry[1] = img.getSubimage(1891 + width+12, 1250 + height, width, height);
		
		happy = new BufferedImage[3];
		for(int i =0; i< 3; i++) {
			happy[i] = img.getSubimage(1891 +(i*(width)), 1250 + 300, width, height);
		}
		
		idle = new BufferedImage[3];
		idle[0] = img.getSubimage(15, 0, width, height);
		idle[1] = img.getSubimage(9 + width, 0, width, height);
		idle[2] = img.getSubimage(3 + 2*width, 0, width, height);
		
		eating = new BufferedImage[3];
		eating[0] = img.getSubimage(5, 465, width, height);
		eating[1] = img.getSubimage(width, 465, width, height);
		eating[2] = img.getSubimage(2*width, 465, width, height);
		
		jumping = new BufferedImage[9];
		jumping[0] = img.getSubimage(5 , 370, width, height);
		jumping[1] = img.getSubimage(5 + width , 370, width, height);
		jumping[2] = img.getSubimage(10 + 2*width, 370, width, height);
		jumping[3] = img.getSubimage(15+ 3*width, 370, width, height);
		jumping[4] = img.getSubimage(5+4*width, 370, width, height);
		jumping[5] = img.getSubimage(-1+ 5*width, 370, width, height);
		jumping[6] = img.getSubimage(-1+ 6*width, 370, width, height);
		jumping[7] = img.getSubimage( -4+7*width, 370, width, height);
		jumping[8] = img.getSubimage(-2+8*width, 370, width, height);
		
		dead = img.getSubimage(675, 825, width +5, height);
		currentImage = happy[0];
		
	}
	//updates the stats every time the game updates called
	public void update() {
		if(!isDead) {
			hunger = hunger - decrement;
			thirst = thirst - decrement;
			mood = mood - decrement;
			if(hunger <= 0 || thirst <= 0 || mood <= 0) {
				death();
			}
			if(delay % 100 == 0) {
	        }
            mind.update();
			updateCurrentImage();
		}
	}
	public void updateCurrentImage() {
		delay++;
		if(isFeeding == true) {
			if(delay % 50 == 0) {
				if (counter >= eating.length) {
					currentImage = eating[eating.length-1];
				}
				else {
					currentImage = eating[counter];
					counter++;
				}
			}
		}
		else if(isPlaying == true) {
			if(delay % 25 ==0) {
				if (counter >= jumping.length) {
					isPlaying = false;
				}
				else {
					currentImage = jumping[counter];
					counter++;
				}
			}
		}
		else {
			Double[] num = { this.hunger, this.thirst, this.mood}; 
			  
	        // using Collections.min() to find minimum element 
	        // using only 1 line. 
            if(this.hunger<5)
            {
                mind.think("Im Hungry");
            }
            if(this.thirst<5)
            {
                mind.think("Im Thirsty");

            }
            if(this.mood<5)
            {
                mind.think("Im Lonely");
            }

	        Double min = Collections.min(Arrays.asList(num)); 
			if(min > 0.0 && min <= 2.5) {
				if(delay % 50 ==0) {
					if (counter >= sad.length) 
						counter = 0;
					currentImage = sad[counter];
					counter++;
					
						
				}
			}
			else if(min> 2.5 && min <=5.0) {
				if(delay % 50 ==0) {
					if (counter >= angry.length) 
						counter = 0;
					currentImage = angry[counter];
					counter++;
					
				}
			}
			else if(min>5.0 && min<=7.5) {
				
				if(delay % 50 ==0) {
					if (counter >= idle.length) 
						counter = 0;
					currentImage = idle[counter];
					counter++;
					
			}
			}
			else if(min <= 0) {
				currentImage = dead;
			}
			else {
				if(delay % 50 ==0) {
					if (counter >= happy.length) 
						counter = 0;
					currentImage = happy[counter];
					counter++;
					
			}
			}
			
		}
		
		
	}
	//Called when user plays with momo, increase's mood
	public void play(int x, int y) {
		int Minx = Kirbywidth;
		int Maxx = Minx + currentImage.getWidth();
		int Miny = Kirbyheight;
		int Maxy = Miny + currentImage.getHeight();
		if(x>Minx && x<Maxx && y>Miny && y<Maxy && !isDead) {
		    isFeeding = false;
		    isPlaying = true;
		    mood += 5.0;
		    if(mood > 10.0)
			    mood =10;
		    counter = 0;
		    updateCurrentImage();
        }
	}
	public void feed(int x, int y) {
		isFeeding = false;//keep this line here it is not an error
		int Minx = Kirbywidth;
		int Maxx = Minx + currentImage.getWidth();
		int Miny = Kirbyheight;
		int Maxy = Miny + currentImage.getHeight();
		if(x>Minx && x<Maxx && y>Miny && y<Maxy && !isDead) {
            mind.blank();
			hunger += 5.0;
			if(hunger > 10.0)
				hunger =10;
			counter = 0;
			updateCurrentImage();
		}
	}
	public void water(int x, int y) {
		isFeeding = false;
		int Minx = Kirbywidth;
		int Maxx = Minx + currentImage.getWidth();
		int Miny = Kirbyheight;
		int Maxy = Miny + currentImage.getHeight();
		if(x>Minx && x<Maxx && y>Miny && y<Maxy && !isDead) {
            mind.blank();
			thirst += 5.0;
			if(thirst > 10.0)
				thirst =10;
			counter = 0;
			updateCurrentImage();
		}
		
	}
	
	public void draw(Graphics g) {
		g.drawImage(currentImage,Kirbywidth,Kirbyheight,null);
        mind.draw(g,Kirbywidth,Kirbyheight);
	}
	public void death() {
		currentImage = dead;
		isDead = true;
	}
	public void feedingTime() {
		isFeeding = true;
		isPlaying = false;
	}
	public void drinkingTime() {
		isFeeding = true;
		isPlaying = false;
	}

	
	
}
