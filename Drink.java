import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Drink 
{
    final int width = 60;
    final int height= 60;
    final int locationX = 770;
    final int locationY = 100;

    static BufferedImage DrinkImage;
    public Drink()
    {
        try
        {
            DrinkImage = ImageIO.read(new File("./drink.png"));
        }
        catch(Exception e)
        {
            System.out.println("Error loading image");
        }
    }
    public boolean isWatering(int x,int y)
    {
        return (x>locationX && x<locationX + width && y>locationY && y<locationY+height);
    }
    public void draw(int x, int y,boolean feeding,Graphics g)
    {
        if(feeding)
        {
            g.drawImage(DrinkImage,x-width/2,y-height/2,width,height,null);
        }
        else
        {
            g.drawImage(DrinkImage,locationX,locationY,width,height,null);
        }
    }
}
