import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton saveBtn = new JButton("Save");
    JButton loadBtn = new JButton("Load");
    JButton resetBtn = new JButton("Reset");
    JButton exitBtn = new JButton("Exit");

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        setLayout(new GridLayout(1, 4));
        add(saveBtn);
        add(loadBtn);
        add(resetBtn);
        add(exitBtn);
        saveBtn.addActionListener(this::save);
        loadBtn.addActionListener(this::loadd);
        resetBtn.addActionListener(this::reset);
        exitBtn.addActionListener(this::exit);
    }

    private void save(ActionEvent e) {
        try {
            JFileChooser fileChooser = new JFileChooser(new File("/home"));
            fileChooser.showSaveDialog(frame);
            ImageIO.write(frame.canvas.image, "PNG", new File(fileChooser.getSelectedFile() + ".png"));
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    private void loadd(ActionEvent e) {
        try { JFileChooser fileChooser = new JFileChooser(new File("/home"));
            fileChooser.showOpenDialog(frame);
            BufferedImage bImage = ImageIO.read(fileChooser.getSelectedFile());
            frame.canvas.setImage(bImage);
            frame.canvas.paintComponent(frame.canvas.graphics);
            //System.out.println("merge");
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    private void reset(ActionEvent e) {
        frame.canvas.createOffscreenImage();
    }

    private void exit(ActionEvent e) {
        System.exit(0);
        frame.dispose();
        frame.setVisible(false);
    }

}
