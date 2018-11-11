import javax.swing.JFrame;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Graphics;
public class Game extends JComponent
{
    static boolean feeding = false;
    static int mouseX;
    static int mouseY;
    
    static BufferedImage Background;

    static Pet momo = new Pet();
    static Food food = new Food();

    public Game()
    {
        addMouseMotionListener(new MouseMotionAdapter()
                {
                    public void mouseMoved(MouseEvent e)
                    {
                        mouseX = e.getX();
                        mouseY = e.getY();
                    }
                });

    }
    public static void main(String[] args)
    {
        try
        {
            Background = ImageIO.read(new File("./GameBackground.jpg"));
        }
        catch(Exception e)
        {
            System.out.println("Error loading image");
        }
        JFrame frame = new JFrame();
        Game M = new Game();
        frame.add(M);
        frame.setSize(930,700);
        frame.setVisible(true);
        while(true)
        {

        }
    }
    public void paintComponent(Graphics g)
    {
        g.drawImage(Background,0,0,null);
        momo.draw(g);
        food.draw(mouseX,mouseY,feeding,g);
    }
}
