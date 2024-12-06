package save;

import model.MapMatrix;

import java.io.Serializable;

public class MapSave implements Serializable {
    public static final long serialVersionUID = 1L;
    private MapMatrix mapMatrix;
    private int heroRow;
    private int heroCol;
    private int boxCnt;
    private int[][] boxes;
    public MapSave(MapMatrix mapmatrix) {
        this.mapMatrix = mapmatrix;
        for(int i=0;i< mapmatrix.getHeight();i++){
            for(int j=0;j< mapmatrix.getWidth();j++){
                if(mapmatrix.getId(i,j)/10==2){
                    this.heroRow = i;
                    this.heroCol = j;
                }
                if(mapmatrix.getId(i,j)/10==1){
                    this.boxCnt++;
                }
            }
        }
        this.boxes = new int[boxCnt][2];
        int tem=0;
        for(int i=0;i< mapmatrix.getHeight();i++){
            for(int j=0;j< mapmatrix.getWidth();j++){
                if(mapmatrix.getId(i,j)/10==1){
                    this.boxes[tem][0] = i;
                    this.boxes[tem][1] = j;
                    tem++;
                }
            }
        }
    }

    public MapMatrix getMapMatrix() {
        return mapMatrix;
    }
    public void setMapMatrix(MapMatrix mapMatrix) {
        this.mapMatrix = mapMatrix;
    }

    public int[][] getMatrix() {
        return mapMatrix.getMatrix();
    }
    public int getHeroRow() {
        return heroRow;
    }
    public int getHeroCol() {
        return heroCol;
    }
    public int getBoxCnt() {
        return boxCnt;
    }
    public int[][] getBoxes() {
        return boxes;
    }
}
