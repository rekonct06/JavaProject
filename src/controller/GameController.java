package controller;

import model.Direction;
import model.MapMatrix;
import view.game.Box;
import view.game.GamePanel;
import view.game.GridComponent;
import view.game.Hero;

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
    }

    public boolean doMove(int row, int col, Direction direction) {
        GridComponent currentGrid = view.getGridComponent(row, col);
        //target row can column.
        int tRow = row + direction.getRow();
        int tCol = col + direction.getCol();
        GridComponent targetGrid = view.getGridComponent(tRow, tCol);
        int[][] map = model.getMatrix();
        if (map[tRow][tCol] == 0 || map[tRow][tCol] == 2) {
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
        }
        else{
            if(map[tRow][tCol] == 10 || map[tRow][tCol] == 12){
                int t1Row = tRow + direction.getRow();
                int t1Col = tCol + direction.getCol();
                GridComponent current1Grid = view.getGridComponent(tRow, tCol);
                GridComponent target1Grid = view.getGridComponent(t1Row, t1Col);
                if(map[t1Row][t1Col] == 0 || map[t1Row][t1Col] == 2 ){
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

    //todo: add other methods such as loadGame, saveGame...

}
