import javax.swing.*;
import static javax.swing.SpringLayout.NORTH;
import static javax.swing.SwingConstants.CENTER;

public class MainFrame extends JFrame {
    ControlPanel controlPanel;
    DesignPanel designPanel;
    public MainFrame() {
        super("Dynamic Swing Designer");
        init();
        setVisible(true);
    }
    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.controlPanel = new ControlPanel(this);
        this.designPanel = new DesignPanel(this);
        add(controlPanel,NORTH);
        add(designPanel,CENTER);
        pack();
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
    }


}