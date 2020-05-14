import javax.swing.*;
import java.awt.*;
import java.util.Random;


public class DesignPanel extends JPanel {
    private final MainFrame frame;
    private static final int W = 800, H = 600;

    public DesignPanel(MainFrame frame) {
        this.frame = frame;
        setPreferredSize(new Dimension(W, H));
        setLayout(new FlowLayout());
        this.repaint();

    }
    public void addComponent(JComponent component) {
       int width = component.getPreferredSize().width;
       int height = component.getPreferredSize().height;
        Random random=new Random();
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        component.setBounds(x, y, width, height);
        component.setToolTipText(component.getClass().getName());
        this.add(component);
        frame.pack();
    }
}
