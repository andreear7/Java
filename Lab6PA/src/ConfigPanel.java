import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ConfigPanel extends JPanel {

    final MainFrame frame;
    JLabel label; // we’re drawing regular polygons
    JSpinner sidesField; // number of sides
    JComboBox colorCombo; // the color of the shape

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
        //configPanelul se schimba doar daca faci click pe el..altfel nu stiu cum sa l fac sa se schimbe :))
        //trebuie dat click ca sa mearga bine desenatul cercului
        //nu si da refresh singur ,se schimba doar daca faci resize la frame,nu stiu de ce
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                remove(label); //JPanel uses FlowLayout by default
                remove(sidesField);
                remove(colorCombo);
                init();
                //revalidate();
                //repaint();
            }
        });
    }

    private void init() {
        if (frame.shapesPanel.shapeCombo.getSelectedItem()=="Regular polygons")
        {
        label = new JLabel("Number of sides:");
        sidesField = new JSpinner(new SpinnerNumberModel(3, 3, 100, 1));
        sidesField.setValue(6); //default number of sides
        String[] colors = {"RANDOM", "BLACK"};
        colorCombo = new JComboBox(colors);}
        else
        { label = new JLabel("Radius:");
            sidesField = new JSpinner(new SpinnerNumberModel(20, 20, 100, 10));
            sidesField.setValue(20); //default number of sides
            String[] colors = {"RANDOM", "BLACK","BLUE","YELLOW"};
            colorCombo = new JComboBox(colors);
        }
        add(label); //JPanel uses FlowLayout by default
        add(sidesField);
        add(colorCombo);
    }
}
