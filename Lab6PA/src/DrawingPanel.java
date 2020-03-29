import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

public class DrawingPanel extends JPanel {
    final MainFrame frame;
    final static int W = 800, H = 600;
    BufferedImage image; //the offscreen image
    Graphics2D graphics; //the "tools" needed to draw in the image

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        init();
    }

    void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.WHITE); //fill the image with white
        graphics.fillRect(0, 0, W, H);
    }

    private void init() {
        setVisible(true);
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (frame.shapesPanel.shapeCombo.getSelectedItem() == "Regular polygons") {
                    drawShape(e.getX(), e.getY());
                } else {
                    drawCircle(e.getX(), e.getY());
                }
                //revalidate();
                //repaint();
                //nu isi face refresh,nu stiu de ce,cu repaint() nu si face nici refresh,nici nu ma lasa sa mai desenez
            }
        });
    }

    private void drawShape(int x, int y) {
        Random random = new Random();
        int radius = random.nextInt(100);
        System.out.println(radius);
        int sides = (Integer) frame.configPanel.sidesField.getValue();
        Random rand = new Random();
        if (frame.configPanel.colorCombo.getSelectedItem() == "BLACK") {
            graphics.setColor(Color.BLACK);
        } else {
            graphics.setColor(new Color(rand.nextInt(0xFFFFFF)));
        }
        graphics.fill(new RegularPolygon(x, y, radius, sides));
    }

    private void drawCircle(int x, int y) {
        int radius = (Integer) frame.configPanel.sidesField.getValue();
        Random rand = new Random();
        if (frame.configPanel.colorCombo.getSelectedItem() == "BLACK") {
            graphics.setColor(Color.BLACK);
        }
        if (frame.configPanel.colorCombo.getSelectedItem() == "BLUE") {
            graphics.setColor(Color.BLUE);
        }
        if (frame.configPanel.colorCombo.getSelectedItem() == "YELLOW") {
            graphics.setColor(Color.YELLOW);
        }
        if (frame.configPanel.colorCombo.getSelectedItem() == "RANDOM") {
            graphics.setColor(new Color(rand.nextInt(0xFFFFFF)));
        }
        graphics.fill(new NodeShape(x, y, radius));
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;

    }

    @Override
    public Graphics2D getGraphics() {
        return graphics;
    }

    public void setGraphics(Graphics2D graphics) {
        this.graphics = graphics;
    }

    @Override
    public void update(Graphics g) {
    } //Why did I do that?

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }
}
