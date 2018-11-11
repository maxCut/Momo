import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

class ThoughtBubble
{
    static BufferedImage bubbleImg;

    public String thought = "";
    public int TimeToIdle = 0;
    public ThoughtBubble()
    {
        try {
		    bubbleImg = ImageIO.read(new File("./speech-bubble.png"));
		} catch (Exception e) {
			System.out.println("Error loading image");
		}
    }
    public void updated()
    {

    }
    public void think(String s)
    {
    
    }
    public void text(String s)
    {

    }
    public void draw(Graphics g, int x, int y)
    {
        g.drawImage(bubbleImg,x,y,null);
        g.drawString(thought,x+5,y+5);
    }
}
