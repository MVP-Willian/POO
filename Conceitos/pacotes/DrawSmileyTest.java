import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

class DrawSmiley extends JPanel{
    public void paintComponent(Graphics g){
        super.paintComponent(g);


        g.setColor(Color.YELLOW);
        g.fillOval(10, 10, 200, 200);


        g.setColor(Color.BLACK);
        g.fillOval(55,65,30,30);


        g.fillOval(50, 65, 30, 30);

        g.fillOval(50, 110, 120, 60);


        g.setColor(Color.YELLOW);

        g.fillRect(50, 110, 120, 30);
        g.fillRect(50, 120, 120, 40);
    }
}

public class DrawSmileyTest{
    public static void main(String[] args) {
        DrawSmiley panel = new DrawSmiley();
        JFrame application = new JFrame();
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.add(panel);
        application.setSize(230, 250);
        application.setVisible(true);
    }
}
