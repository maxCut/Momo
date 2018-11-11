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
    public void update()
    {
        if(TimeToIdle>0)
        {
            TimeToIdle--;
        }
    }
    public void think(String s)
    {
        thought = s;
        TimeToIdle = 280;
    }
    public void blank()
    {
        TimeToIdle = 0; 
    }
    public void text(String s)
    {

    }
    public void draw(Graphics g, int x, int y)
    {
        if(TimeToIdle>0)
        {
            g.drawImage(bubbleImg,x-100,y-200,null);
            g.drawString(thought,x-50,y-100);
        }
    }
}
