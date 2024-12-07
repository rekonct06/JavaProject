package controller;

import model.Direction;
import model.MapMatrix;
import save.MapSave;
import view.game.*;

/**
 * It is a bridge to combine GamePanel(view) and MapMatrix(model) in one game.
 * You can design several methods about the game logic in this class.
 */
public class GameController {
    private final GamePanel view;
    private final MapMatrix model;

    public GameController(GamePanel view, MapMatrix model) {
        this.view = view;
        this.model = model;
        view.setController(this);
    }

    public void restartGame() {
        System.out.println("Do restart game here");
        view.resetStep();
        view.loadreset();
        changeModelto(view.getOriginalModel());
    }

    public boolean doMove(int row, int col, Direction direction) {
        GridComponent currentGrid = view.getGridComponent(row, col);
        //target row can column.
        int tRow = row + direction.getRow();
        int tCol = col + direction.getCol();
        GridComponent targetGrid = view.getGridComponent(tRow, tCol);
        int[][] map = model.getMatrix();
        if (map[tRow][tCol] == 0 || map[tRow][tCol] == 2 || map[tRow][tCol] == 3) {
            //update hero in MapMatrix
            model.getMatrix()[row][col] -= 20;
            model.getMatrix()[tRow][tCol] += 20;
            //Update hero in GamePanel
            Hero h = currentGrid.removeHeroFromGrid();
            targetGrid.setHeroInGrid(h);
            //Update the row and column attribute in hero
            h.setRow(tRow);
            h.setCol(tCol);
            return true;
        } else {
            if (map[tRow][tCol] == 10 || map[tRow][tCol] == 12 || map[tRow][tCol] == 13) {
                int t1Row = tRow + direction.getRow();
                int t1Col = tCol + direction.getCol();
                GridComponent current1Grid = view.getGridComponent(tRow, tCol);
                GridComponent target1Grid = view.getGridComponent(t1Row, t1Col);
                if (map[t1Row][t1Col] == 0 || map[t1Row][t1Col] == 2) {
                    //Move Box
                    model.getMatrix()[tRow][tCol] -= 10;
                    model.getMatrix()[t1Row][t1Col] += 10;
                    //    /*
                    Box b = current1Grid.removeBoxFromGrid();
                    target1Grid.setBoxInGrid(b);
                    b.setRow(t1Row);
                    b.setCol(t1Col);
                    //    */
                    //Move Hero
                    model.getMatrix()[row][col] -= 20;
                    model.getMatrix()[tRow][tCol] += 20;
                    Hero h = currentGrid.removeHeroFromGrid();
                    targetGrid.setHeroInGrid(h);
                    h.setRow(tRow);
                    h.setCol(tCol);
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public void changeModelto(MapSave toMap) {

        MapSave now = new MapSave(model);
        if (now.getBoxCnt() != toMap.getBoxCnt()) {
            System.out.println("Numbers of Boxes not equal!");
            return;
        }

        GridComponent iGird = view.getGridComponent(now.getHeroRow(), now.getHeroCol());
        GridComponent targetGird = view.getGridComponent(toMap.getHeroRow(), toMap.getHeroCol());
        Hero h = iGird.removeHeroFromGrid();
        targetGird.setHeroInGrid(h);
        h.setRow(targetGird.getRow());
        h.setCol(targetGird.getCol());

        for (int i = 0; i < now.getBoxCnt(); i++) {
            iGird = view.getGridComponent(now.getBoxes()[i][0], now.getBoxes()[i][1]);
            targetGird = view.getGridComponent(toMap.getBoxes()[i][0], toMap.getBoxes()[i][1]);
            Box b = iGird.removeBoxFromGrid();
            targetGird.setBoxInGrid(b);
            b.setRow(targetGird.getRow());
            b.setCol(targetGird.getCol());
        }

        for (int i = 0; i < model.getHeight(); i++) {
            for (int j = 0; j < model.getWidth(); j++) {
                model.getMatrix()[i][j] = toMap.getMatrix()[i][j];
            }
        }
    }

    public boolean GameWin() {
        for (int i = 0; i < model.getHeight(); i++) {
            for (int j = 0; j < model.getWidth(); j++) {
                if (model.getId(i, j) == 10) return false; //有不在目的地的箱子，游戏就还未赢
            }
        }
        return true;
    }

    public boolean GameLose() {
        for (int i = 0; i < model.getHeight(); i++) {
            for(int j=0;j<model.getWidth();j++){
                if (model.getId(i, j) == 23) return true;
            }
        }
        for(int i = 0; i < model.getHeight(); i++) {
            for (int j = 0; j < model.getWidth(); j++) {
                if(model.getId(i, j) == 10) {
                    int aroundblock1 = 0, aroundblock2 = 0;
                    if (model.getId(i, j + 1) == 1) aroundblock1++;
                    if (model.getId(i, j - 1) == 1) aroundblock1++;
                    if (model.getId(i + 1, j) == 1) aroundblock2++;
                    if (model.getId(i - 1, j) == 1) aroundblock2++;
                    if (aroundblock1 > 0 && aroundblock2 > 0) return true;
                }
            }
        }
        for(int i = 0; i < model.getHeight(); i++){
            for(int j = 0; j < model.getWidth(); j++){
                if(model.getId(i, j) == 10) {
                    if(model.getId(i+1, j) == 0 || model.getId(i+1, j) == 2 || model.getId(i+1, j)== 20 || model.getId(i+1, j) == 22)
                        if(model.getId(i-1, j) == 0 || model.getId(i-1, j) == 2 || model.getId(i-1, j)== 20|| model.getId(i-1, j) == 22)
                            return false;
                    if(model.getId(i, j+1) == 0 || model.getId(i, j+1) == 2 || model.getId(i, j+1)== 20 || model.getId(i, j+1) == 22)
                        if(model.getId(i, j-1) == 0 || model.getId(i, j-1) == 2 || model.getId(i, j-1)== 20 || model.getId(i, j-1) == 22)
                            return false;
                }
            }
        }
        return true;
    }


    //todo: add other methods such as loadGame, saveGame...

}
