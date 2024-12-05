package view.game;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Brick extends JComponent {
    private int row;
    private int col;

    private final int value = 1;

    public Brick(int width, int height, int row, int col) {
        this.row = row;
        this.col = col;
        this.setSize(width, height);
        this.setLocation(0, 0);
    }

    public void paintComponent(Graphics g) {

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());
        Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
        this.setBorder(border);

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
