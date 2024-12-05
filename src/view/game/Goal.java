package view.game;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Goal extends JComponent {
    private int row;
    private int col;

    private final int value = 2;

    public Goal(int width, int height, int row, int col) {
        this.row = row;
        this.col = col;
        this.setSize(width, height);
        this.setLocation(0, 0);
    }

    public void paintComponent(Graphics g) {


        g.setColor(Color.GREEN);
        int[] xPoints = {getWidth() / 2, getWidth(), getWidth() / 2, 0};
        int[] yPoints = {0, getHeight() / 2, getHeight(), getHeight() / 2};
        g.fillPolygon(xPoints, yPoints, 4);
        g.setColor(Color.BLACK);
        g.drawPolygon(xPoints, yPoints, 4);

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
