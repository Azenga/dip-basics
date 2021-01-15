package org.example.display;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Display {

    private JFrame frame;
    private String title;
    private int width;
    private int height;
    private JPanel theMainPanel;

    public Display(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        initFrame();
    }

    private void initFrame() {
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLayout(new BorderLayout());

        //Create and add the panel
        theMainPanel = new JPanel(new BorderLayout());

        setUpTheSelectImageButton();

        theMainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        frame.add(theMainPanel, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
    }

    private void setUpTheSelectImageButton() {

        JButton selectImageButton = new JButton("Select Image");

        selectImageButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home"));

            FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("image/*", "jpg", "png", "jpeg", "bmp");

            fileChooser.addChoosableFileFilter(extensionFilter);

            int result = fileChooser.showOpenDialog(frame);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();

                showSelectedImage(selectedFile);
            }

        });

        theMainPanel.removeAll();

        theMainPanel.add(selectImageButton, BorderLayout.CENTER);

        theMainPanel.updateUI();

    }

    private void showSelectedImage(File file) {
        try {
            BufferedImage image = ImageIO.read(file);

            theMainPanel.removeAll();

            theMainPanel.add(new JLabel(new ImageIcon(image)), BorderLayout.CENTER);

            theMainPanel.updateUI();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void show() {

        frame.setVisible(true);

    }


}
