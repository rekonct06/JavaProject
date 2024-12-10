package view.game;

import controller.GameController;
import model.Direction;
import model.MapMatrix;
import save.MapSave;
import view.sound.AudioPlayer;

import javax.swing.*;
import java.util.concurrent.Future;

public class NewMapPanel extends ListenerPanel{
    private final int GRID_SIZE = 50;
    private MapMatrix model;

    private Temp temp;

    private GridComponent[][] grids;
    private NewController controller;

    private NewMapFrame frame;

    public NewMapPanel(MapMatrix model){
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(null);
        this.setSize(model.getWidth() * GRID_SIZE + 4, model.getHeight() * GRID_SIZE + 4);
        this.controller=new NewController(this, model);
        this.model=model;
        this.grids = new GridComponent[model.getHeight()][model.getWidth()];
        initialGame();
    }


    public void initialGame() {
        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids[i].length; j++) {
                if(grids[i][j]==null){continue;}
                if(grids[i][j].getBox()!=null){
                    grids[i][j].removeBoxFromGrid();
                    grids[i][j].revalidate();
                    grids[i][j].repaint();
                }
                if(grids[i][j].getHero()!=null){
                    grids[i][j].removeHeroFromGrid();
                    grids[i][j].revalidate();
                    grids[i][j].repaint();
                }
                if(grids[i][j].getTemp()!=null){
                    grids[i][j].removeTempFromGrid();
                    grids[i][j].revalidate();
                    grids[i][j].repaint();
                }
                grids[i][j].removeAll();
            }
        }

        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids[i].length; j++) {
                //Units digit maps to id attribute in GridComponent. (The no change value)
                if(grids[i][j]==null)grids[i][j] = new GridComponent(i, j, model.getId(i, j) % 10, this.GRID_SIZE);
                else grids[i][j].setId(model.getId(i, j) % 10);
                grids[i][j].setLocation(j * GRID_SIZE + 2, i * GRID_SIZE + 2);
                //Ten digit maps to Box or Hero in corresponding location in the GridComponent. (Changed value)
                if(model.getId(i, j) >=50){
                    this.temp = new Temp(GRID_SIZE - 20, GRID_SIZE - 20, i, j);
                    grids[i][j].setTempInGrid(this.temp);
                }
                int deltemp=0;
                if(model.getId(i, j) >=50){deltemp=model.getId(i, j)-50;}
                else deltemp=model.getId(i, j);
                switch (deltemp/ 10) {
                    case 1:
                        grids[i][j].setBoxInGrid(new Box(GRID_SIZE , GRID_SIZE , i, j));
                        break;
                    case 2:
                        Hero hero = new Hero(GRID_SIZE, GRID_SIZE, i, j);
                        grids[i][j].setHeroInGrid(hero);
                        break;
                }
                this.add(grids[i][j]);
            }
        }
        this.repaint();
    }

    public void doMoveRight() {
        System.out.println("Click VK_RIGHT");
        if (controller.doMove(temp.getRow(), temp.getCol(), Direction.RIGHT)) {
            String tempath = "data/sound/short.wav";
            Future<?> future1 = AudioPlayer.playSound(tempath);
        }
        initialGame();
    }
    public void doMoveLeft() {
        System.out.println("Click VK_LEFT");
        if (controller.doMove(temp.getRow(), temp.getCol(), Direction.LEFT)) {
            String tempath = "data/sound/short.wav";
            Future<?> future1 = AudioPlayer.playSound(tempath);
        }
        initialGame();
    }
    public void doMoveUp() {
        System.out.println("Click VK_UP");
        if (controller.doMove(temp.getRow(), temp.getCol(), Direction.UP)) {
            String tempath = "data/sound/short.wav";
            Future<?> future1 = AudioPlayer.playSound(tempath);
        }
        initialGame();
    }
    public void doMoveDown() {
        System.out.println("Click VK_DOWN");
        if (controller.doMove(temp.getRow(), temp.getCol(), Direction.DOWN)) {

            String tempath = "data/sound/short.wav";
            Future<?> future1 = AudioPlayer.playSound(tempath);
        }
        initialGame();
    }

    public boolean HaveBox(){
        int deltemp=0;
        if(model.getId(temp.getRow(), temp.getCol()) >=50){deltemp=model.getId(temp.getRow(), temp.getCol())-50;}
        else deltemp=model.getId(temp.getRow(), temp.getCol());
        if(deltemp/10==1)return true;
        else return false;
    }

    public boolean HaveHero(){
        int deltemp=0;
        if(model.getId(temp.getRow(), temp.getCol()) >=50){deltemp=model.getId(temp.getRow(), temp.getCol())-50;}
        else deltemp=model.getId(temp.getRow(), temp.getCol());
        if(deltemp/10==2)return true;
        else return false;
    }

    public boolean HaveGoal(){
        int deltemp=0;
        if(model.getId(temp.getRow(), temp.getCol()) >=50){deltemp=model.getId(temp.getRow(), temp.getCol())-50;}
        else deltemp=model.getId(temp.getRow(), temp.getCol());
        if(deltemp%10==2)return true;
        else return false;
    }


    public void deleteall(){
        model.getMatrix()[temp.getRow()][temp.getCol()]=50;
        initialGame();
    }

    public boolean addHero(){
        if(model.getMatrix()[temp.getRow()][temp.getCol()]==60||model.getMatrix()[temp.getRow()][temp.getCol()]==62){return false;}
        if(model.getMatrix()[temp.getRow()][temp.getCol()]==51)return false;
        if(model.getMatrix()[temp.getRow()][temp.getCol()]==53)return false;
        String tempath = "data/sound/cling.wav";
        Future<?> future1 = AudioPlayer.playSound(tempath);
        model.getMatrix()[temp.getRow()][temp.getCol()]+=20;
    //    this.grids = new GridComponent[model.getHeight()][model.getWidth()];
        initialGame();
        return true;
    }

    public boolean addBox(){
        if(model.getMatrix()[temp.getRow()][temp.getCol()]==70)return false;
        if(model.getMatrix()[temp.getRow()][temp.getCol()]==51)return false;

        String tempath = "data/sound/cling.wav";
        Future<?> future1 = AudioPlayer.playSound(tempath);
        model.getMatrix()[temp.getRow()][temp.getCol()]+=10;
   //     this.grids = new GridComponent[model.getHeight()][model.getWidth()];
        initialGame();
        return true;
    }

    public boolean addGoal(){
        if(model.getMatrix()[temp.getRow()][temp.getCol()]==51)return false;

        String tempath = "data/sound/cling.wav";
        Future<?> future1 = AudioPlayer.playSound(tempath);
        model.getMatrix()[temp.getRow()][temp.getCol()]+=2;
    //    this.grids = new GridComponent[model.getHeight()][model.getWidth()];
        initialGame();
        return true;
    }

    public boolean addHole(){
        if(model.getMatrix()[temp.getRow()][temp.getCol()]==51)return false;

        String tempath = "data/sound/cling.wav";
        Future<?> future1 = AudioPlayer.playSound(tempath);
        model.getMatrix()[temp.getRow()][temp.getCol()]+=3;
        //    this.grids = new GridComponent[model.getHeight()][model.getWidth()];
        initialGame();
        return true;
    }

    public boolean addBrick(){
        if(model.getMatrix()[temp.getRow()][temp.getCol()]==51)return false;
        if(model.getMatrix()[temp.getRow()][temp.getCol()]!=50)return false;
        String tempath = "data/sound/cling.wav";
        Future<?> future1 = AudioPlayer.playSound(tempath);
        model.getMatrix()[temp.getRow()][temp.getCol()]+=1;
    //    this.grids = new GridComponent[model.getHeight()][model.getWidth()];
        initialGame();
        return true;
    }

    public void setController(NewController controller) {
        this.controller = controller;
    }
    public NewController getController() {
        return controller;
    }

    public GridComponent getGridComponent(int row, int col) {
        return grids[row][col];
    }
    public void setFrame(NewMapFrame frame) {
        this.frame = frame;
    }
}
