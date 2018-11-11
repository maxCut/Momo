import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.event.MouseEvent;
import javax.event.MouseMotionAdapter;
public class Game extends JComponent
{
    static int mouseX;
    static int mouseY;
    static Pet momo = new Pet();
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
        JFrame frame = new JFrame();
        Game M = new Game();
        frame.add(M);
        frame.setSize(3000,3000);
        frame.setVisable(true);
        while(true)
        {

        }
    }
    public void paintComponent()
    {

    }
}
