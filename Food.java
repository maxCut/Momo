import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Food
{
    final int width = 60;
    final int height= 60;
    final int locationX = 100;
    final int locationY = 100;

    static BufferedImage FoodImage;
    public Food()
    {
        try
        {
            FoodImage = ImageIO.read(new File("./food.png"));
        }
        catch(Exception e)
        {
            System.out.println("Error loading image");
        }
    }
    public boolean IsFeeding(int x,int y)
    {
        return (x>locationX && x<locationX + width && y>locationY && y<locationY+height);
    }
    public void draw(int x, int y,boolean feeding,Graphics g)
    {
        if(feeding)
        {
            g.drawImage(FoodImage,x,y,width,height,null);
        }
        else
        {
            g.drawImage(FoodImage,locationX,locationY,width,height,null);
        }
    }
}
