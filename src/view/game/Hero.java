package view.game;

import model.Direction;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Hero extends JComponent {
    private int row;
    private int col;

    private ImageIcon upImage;
    private ImageIcon downImage;
    private ImageIcon leftImage;
    private ImageIcon rightImage;
    private ImageIcon currentImage;
    private final int GRID_SIZE = 50; // 格子大小
    private final int value = 20;
    private static Color color = new Color(87, 171, 220);

    public Hero(int width, int height, int row, int col) {
        this.row = row;
        this.col = col;
        //    this.setSize(width, height);
        //    this.setLocation(8, 8);

        this.setPreferredSize(new Dimension(GRID_SIZE, GRID_SIZE)); // 设置预设尺寸
        this.setSize(GRID_SIZE, GRID_SIZE); // 设置尺寸
        this.setLocation(0, 0);
        try {
            upImage = new ImageIcon(ImageIO.read(new File("src/image/Up.png")));
            downImage = new ImageIcon(ImageIO.read(new File("src/image/Down.png")));
            leftImage = new ImageIcon(ImageIO.read(new File("src/image/Left1.png")));
            rightImage = new ImageIcon(ImageIO.read(new File("src/image/Right1.png")));

            currentImage = downImage;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load hero images");
        }
    }

    public void paintComponent(Graphics g) {
        /*
        g.setColor(Color.BLACK);
        g.fillOval(0, 0, getWidth(), getHeight());
        g.setColor(color);
        g.fillOval(1,1,getWidth()-2,getHeight()-2);
         */

        super.paintComponent(g);
        if (currentImage != null) {
            g.drawImage(currentImage.getImage(), 0, 0, GRID_SIZE, GRID_SIZE, this);
        }
    }

    public void setDirection(Direction direction) {
        switch (direction) {
            case UP:
                currentImage = upImage;
                break;
            case DOWN:
                currentImage = downImage;
                break;
            case LEFT:
                currentImage = leftImage;
                break;
            case RIGHT:
                currentImage = rightImage;
                break;
        }
        this.repaint();
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
