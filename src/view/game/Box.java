package view.game;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Box extends JComponent {
    private int row;
    private int col;
    private final int value = 10;

    private Image boxImage;

    public Box(int width, int height, int row, int col) {
        this.row = row;
        this.col = col;
        this.setSize(width, height);
        this.setLocation(0, 0);
        boxImage = new ImageIcon("src/image/box.png").getImage();
        /*
        try {
            boxImage = new ImageIcon(ImageIO.read(new File("src/image/box.png")));
            boxImage = new ImageIcon(boxImage.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load box image");
        }
        */
    }

    public void paintComponent(Graphics g) {
        /*
        g.setColor(Color.ORANGE);
        g.fillRect(0, 0, getWidth(), getHeight());
        Border border = BorderFactory.createLineBorder(Color.black, 1);
        this.setBorder(border);
         */

    //    super.paintComponent(g);
        if (boxImage != null) {
            g.drawImage(boxImage, 0, 0, 50, 50, this);

        }
    }

    public int getValue() {
        return value;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
