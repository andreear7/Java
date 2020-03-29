import javax.swing.*;

public class ShapesPanel extends JPanel {
    final MainFrame frame;
    JLabel label; // we’re drawing regular polygons
    JSpinner sidesField; // number of sides
    JComboBox shapeCombo; // the color of the shape

    public ShapesPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }
    private void init() {
        label = new JLabel("Available shapes:");
        String[] shapes={"Regular polygons","Circles"};
        shapeCombo=new JComboBox(shapes);
        add(label); //JPanel uses FlowLayout by default
        //add(sidesField);
        add(shapeCombo);
    }

}
