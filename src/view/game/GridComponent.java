package view.game;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GridComponent extends JComponent {
    private int row;
    private int col;
    private int id; // represents the units digit value. It cannot be changed during one game.

    private Image emptyGroundImage; // 空地图片
    private Image wallImage; // 墙
    private Image targetImage; // 目标位置图片
    private Image holeImage;
    private ImageIcon boxOnTargetImage;

    private static final int GRID_SIZE = 50; // 假设格子大小为50x50像素

    private Hero hero;
    private Box box;
    private Temp temp;
    static Color color = new Color(246, 246, 229);

    public GridComponent(int row, int col, int id, int gridSize) {
        this.setSize(gridSize, gridSize);
        this.row = row;
        this.col = col;
        this.id = id;
    //    try {
            emptyGroundImage = new ImageIcon("src/image/空地.png").getImage();
                   wallImage = new ImageIcon("src/image/wall1.png").getImage();
            targetImage = new ImageIcon("src/image/1.jpg").getImage();
            holeImage=new ImageIcon("src/image/hole.jpg").getImage();
            //boxOnTargetImage = new ImageIcon(ImageIO.read(new File("C:\\Users\\lenovo\\IdeaProjects\\JavaProject\\src\\image\\Down.png")));
      //  } catch (FileNotFoundException e) {
   //         e.printStackTrace();
    //        System.out.println("Failed to load grid images");
   //     }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.printComponents(g);
        Color borderColor = color;
        switch (id % 10) {
            /*
            case 1:
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(0, 0, getWidth(), getHeight());
                borderColor = Color.DARK_GRAY;
                break;
            case 0:
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, getWidth(), getHeight());
                break;
            case 2:
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, getWidth(), getHeight());
                g.setColor(Color.GREEN);
                int[] xPoints = {getWidth() / 2, getWidth(), getWidth() / 2, 0};
                int[] yPoints = {0, getHeight() / 2, getHeight(), getHeight() / 2};
                g.fillPolygon(xPoints, yPoints, 4);
                g.setColor(Color.BLACK);
                g.drawPolygon(xPoints, yPoints, 4);
                break;
             */
            case 1:
            g.drawImage(wallImage, 0, 0, GRID_SIZE, GRID_SIZE, this);
            break;
            case 0:
            //    g.drawImage(emptyGroundImage, 0, 0, GRID_SIZE, GRID_SIZE, this);
                break;
            case 2:
                g.drawImage(targetImage, 0, 0, getWidth(), getHeight(), this);
                break;
            case 3:
                g.drawImage(holeImage, 0, 0, getWidth(), getHeight(), this);
                break;
            /*case 3:
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, getWidth(), getHeight());
                g.setColor(Color.RED);
                int[] x1Points = {getWidth() / 2, getWidth(), getWidth() / 2, 0};
                int[] y1Points = {0, getHeight() / 2, getHeight(), getHeight() / 2};
                g.fillPolygon(x1Points, y1Points, 4);
                g.setColor(Color.BLACK);
                g.drawPolygon(x1Points, y1Points, 4);
                break;*/
        }
        Border border = BorderFactory.createLineBorder(borderColor, 1);
        this.setBorder(border);
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

    public int getId() {
        return id;
    }

    public Hero getHero() {
        return hero;
    }
    public Box getBox() {
        return box;
    }

    public Temp getTemp() {
        return temp;
    }

    //When adding a hero in this grid, invoking this method.
    public void setHeroInGrid(Hero hero) {
        this.hero = hero;
        this.add(hero);
    }

    //When adding a box in this grid, invoking this method.
    public void setBoxInGrid(Box box) {
        this.box = box;
        this.add(box);
    }
    //When removing hero from this grid, invoking this method
    public Hero removeHeroFromGrid() {
        this.remove(this.hero);//remove hero component from grid component
        Hero h = this.hero;
        this.hero = null;//set the hero attribute to null
        this.revalidate();//Update component painting in real time
        this.repaint();
        return h;
    }
    //When removing box from this grid, invoking this method
    public Box removeBoxFromGrid() {
        this.remove(this.box);//remove box component from grid component
        Box b = this.box;
        this.box = null;//set the hero attribute to null
        this.revalidate();//Update component painting in real time
        this.repaint();
        return b;
    }

    public void setTempInGrid(Temp temp) {
        this.temp = temp;
        this.add(temp);
    }

    public Temp removeTempFromGrid() {
        this.remove(this.temp);
        Temp t = this.temp;
        this.temp = null;
        this.revalidate();
        this.repaint();
        return t;
    }

    public void reset(){
        this.revalidate();
        this.repaint();
    }
    public void setId(int id){
        this.id=id;
        this.revalidate();
        this.repaint();
    }
}
