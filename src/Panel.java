import javax.swing.*;
import java.awt.*;

/**
 * Created by Himanshu on 11/23/2015.
 */
public class Panel extends JPanel {

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.blue);
        g.drawOval(300,300,100,100);
        g.fillOval(300,300,100,100);

        g.setColor(Color.red);
        g.drawRect(100,100,600,100);
        g.fillRect(100,100,300,100);
        g.setColor(Color.green);
        g.fillRect(300,100,300,100);
    }
}
