import javax.swing.*;

import static javax.swing.SpringLayout.*;
import static javax.swing.SwingConstants.CENTER;


public class MainFrame extends JFrame {
    ConfigPanel configPanel;
    ControlPanel controlPanel;
    DrawingPanel canvas;
    ShapesPanel shapesPanel;

    public MainFrame() {
        super("My Drawing Application");
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        shapesPanel=new ShapesPanel(this);
        canvas = new DrawingPanel(this);
        configPanel = new ConfigPanel(this);
        controlPanel = new ControlPanel(this);
        add(canvas, CENTER);
        add(shapesPanel,WEST);
        //canvas.setVisible(true);
        add(configPanel, NORTH);
        //configPanel.setVisible(true);
        add(controlPanel, SOUTH);

        //controlPanel.setVisible(true);
        //invoke the layout manager
        pack();
        //setLocationRelativeTo(null);
    }

}
