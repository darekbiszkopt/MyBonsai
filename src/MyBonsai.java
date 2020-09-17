import javax.swing.*;
import java.awt.*;

public class MyBonsai {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TreeFrame();
        });
    }
}

class TreeFrame extends JFrame {

    public TreeFrame() {
        setSize(new Dimension(1024, 1024));
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public void paint(Graphics g) {
        super.paint(g);
        // Punkt startowy
        int xFrom = 512;
        int yFrom = 1000;
        // Długość gałęzi
        int radius = 100;
        // Początkowy kąt
        double angleL = Math.PI;
        // Rysowanie


        draw(g, xFrom, yFrom, radius, angleL, 0);
    }

    private void draw(Graphics g, int xFrom, int yFrom, double radius, double angle, int r) {


        double deltaAngle = (Math.random()/2 + 0.1);

        double leftEndX = xFrom + Math.sin(angle + deltaAngle)*radius;
        double leftEndY = yFrom + Math.cos(angle + deltaAngle)*radius;
        double rightEndX = xFrom + Math.sin(angle - deltaAngle)*radius;
        double rightEndY = yFrom + Math.cos(angle - deltaAngle)*radius;

        Graphics2D temp = (Graphics2D) g;
        temp.setStroke(new BasicStroke(10 - r*3/2));

        g.setColor(new Color(93, 53, 0));
        g.drawLine(xFrom, yFrom, (int)leftEndX, (int)leftEndY);
        g.drawLine(xFrom, yFrom, (int)rightEndX, (int)rightEndY);

        if(r == 5){
            double a = (Math.random()/2 + 0.2);
            int height = (int)((Math.sin(a)*50)+50);
            int width = height - (int)((Math.random()*40)+10);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            drawLeaf(g, (int)leftEndX, (int)leftEndY, height, width);
            drawLeaf(g, (int)rightEndX, (int)rightEndY, height, width);
        }

        if( r < 5 ){
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            draw(g, (int)leftEndX, (int)leftEndY, radius-(Math.random()*20), angle + 0.05*(5-r), ++r);
            draw(g, (int)rightEndX, (int)rightEndY, radius-(Math.random()*20), angle - 0.05*(5-r), r);
        }
    }

    public void drawLeaf(Graphics g, int xFrom, int yFrom, int height, int width){
        g.setColor(new Color(0, 93, 15));
        g.fillOval(xFrom-(width/2), yFrom-(height-5), width, height);

        Graphics2D temp = (Graphics2D) g;
        temp.setStroke(new BasicStroke(1));
        g.setColor(Color.BLACK);
        g.drawOval(xFrom-(width/2), yFrom-(height-5), width, height);
    }
}