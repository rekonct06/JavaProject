package view.game;

import model.Direction;
import model.MapMatrix;

public class NewController {

    private final NewMapPanel view;
    private final MapMatrix model;
    public NewController(NewMapPanel view, MapMatrix model) {
        this.view = view;
        this.model = model;
        view.setController(this);
    }
    public boolean doMove(int row, int col, Direction direction) {
        GridComponent currentGrid = view.getGridComponent(row, col);
        int tRow = row + direction.getRow();
        int tCol = col + direction.getCol();
        GridComponent targetGrid = view.getGridComponent(tRow, tCol);
        int[][] map= model.getMatrix();
    //    if(map[tRow][tCol]==1) return false;
        model.getMatrix()[row][col]-=50;
        model.getMatrix()[tRow][tCol]+=50;
        Temp t=currentGrid.removeTempFromGrid();
        targetGrid.setTempInGrid(t);
        t.setRow(tRow);
        t.setCol(tCol);
        return true;
    }
}
