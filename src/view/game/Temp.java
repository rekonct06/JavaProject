package view.game;

import javax.swing.*;
import java.awt.*;

public class Temp extends JComponent {
    private int row;
    private int col;

    private final int value = 30;

    public Temp(int width, int height, int row, int col) {
        this.row = row;
        this.col = col;
        this.setSize(width, height);
        this.setLocation(8, 8);
    }

    public void paintComponent(Graphics g) {
        /*
        g.setColor(Color.BLACK);
        g.fillOval(3, 3, getWidth()-5, getHeight()-5);
         */
        g.setColor(Color.CYAN);
        g.fillOval(4,4,getWidth()-6,getHeight()-6);
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
