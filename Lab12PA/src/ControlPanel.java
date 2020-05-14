import javax.swing.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ControlPanel extends JPanel {
    private final MainFrame frame;
    private JLabel classNameLabel;
    private JLabel textLabel;
    private JTextField classNameField;
    private JTextField textField;
    private JButton addButton;

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        classNameLabel = new JLabel("type class name:");
        textLabel = new JLabel("type text:");
        classNameField = new JTextField(30);
        textField = new JTextField(30);
        addButton = new JButton("add component");
        add(classNameLabel);
        add(classNameField);
        add(textLabel);
        add(textField);
        add(addButton);
        addButton.addActionListener(this::add);
    }

    private void add(ActionEvent e) {
        JComponent component = createComponent(classNameField.getText());
        setText(component, textField.getText());
        frame.designPanel.addComponent(component);
    }

    private JComponent createComponent(String className) {
        try {
            Class clazz = Class.forName(className);
            return (JComponent) clazz.getConstructor().newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }

    }

    private void setText(JComponent component, String text) {
        Class clazz = component.getClass();
        Method method = null;
        try {
            method = clazz.getMethod("setText", String.class);
            method.invoke(component, text);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}


