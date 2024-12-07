package view.game;

import controller.GameController;
import model.Direction;
import model.MapMatrix;
import player.PlayerManager;
import save.LoadSave;
import save.MapSave;
import view.FrameUtil;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * It is the subclass of ListenerPanel, so that it should implement those four methods: do move left, up, down ,right.
 * The class contains a grids, which is the corresponding GUI view of the matrix variable in MapMatrix.
 */
public class GamePanel extends ListenerPanel {

    private GridComponent[][] grids;
    private MapMatrix model;
    private MapSave originalModel;
    private GameController controller;
    private JLabel stepLabel;
    private int steps;
    private final int GRID_SIZE = 50;

    private Hero hero;

    private PlayerManager playerManager;
    private String PlayerName;
    private GameFrame gameFrame;
    private LoadSave loadSave;

    public GamePanel(MapMatrix model, MapSave originalModel, GameFrame gameFrame, LoadSave loadSave ,JLabel stepLabel) {
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(null);
        this.setSize(model.getWidth() * GRID_SIZE + 4, model.getHeight() * GRID_SIZE + 4);
        this.stepLabel = stepLabel;
        this.gameFrame = gameFrame;

        this.controller = new GameController(this, model);
        if(loadSave.getmapnum()==0){
            this.model = model;
            this.originalModel = originalModel;
            this.loadSave = loadSave;
            this.loadSave.addmap(originalModel);
            this.grids = new GridComponent[model.getHeight()][model.getWidth()];
            initialGame();
        }
        else{
            this.model=model;
            this.originalModel=originalModel;
            this.loadSave=loadSave;
            this.grids = new GridComponent[model.getHeight()][model.getWidth()];
            initialGame();
            /*
            JButton dobtn=FrameUtil.createButton(this.gameFrame, "Up", new Point(0, 0), 20, 20);
            MapSave imap=loadSave.getimap(1);
            dobtn.addActionListener(e -> {
                controller.changeModelto(imap);
                afterMove();
            });
            imap=loadSave.getimap(2);
            dobtn.addActionListener(e -> {
                controller.changeModelto(imap);
                afterMove();
            });
            */
            int cnt=0;
            for(int i=1;i<loadSave.getmapnum();i++){
                MapSave imap=loadSave.getimap(i);
                controller.changeModelto(imap);
                afterMove();
                cnt++;
            //    if(cnt==1)break;
                System.out.println("Fresh");
            //    pausefor(400);
            }
        }

    }

    public void initialGame() {

        this.steps = 0;
        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids[i].length; j++) {
                //Units digit maps to id attribute in GridComponent. (The no change value)
                grids[i][j] = new GridComponent(i, j, model.getId(i, j) % 10, this.GRID_SIZE);
                grids[i][j].setLocation(j * GRID_SIZE + 2, i * GRID_SIZE + 2);
                //Ten digit maps to Box or Hero in corresponding location in the GridComponent. (Changed value)
                switch (model.getId(i, j) / 10) {
                    case 1:
                        grids[i][j].setBoxInGrid(new Box(GRID_SIZE - 10, GRID_SIZE - 10, i, j));
                        break;
                    case 2:
                        this.hero = new Hero(GRID_SIZE - 16, GRID_SIZE - 16, i, j);
                        grids[i][j].setHeroInGrid(hero);
                        break;
                }
                this.add(grids[i][j]);
            }
        }
        this.repaint();
    }

    public void undostep(){
        this.steps--;
        this.stepLabel.setText(String.format("Step: %d", this.steps));
        MapSave imap=loadSave.getimap(steps);
        if(imap.equals(null)){
            System.out.println("this map is null");
        }
        else{
            controller.changeModelto(imap);
            loadSave.deletelast();
        }
    //    System.out.println(loadSave.getmapnum());
    }

    public void Overcheck(){
        if(controller.GameWin()){
            System.out.println("WIN!");
            gameFrame.WinDialog();
            if(!PlayerName.equals("Visitor")){
                playerManager.PlayerWin(this.PlayerName);
                playerManager.updateData();
            }
            //有待完善
        }
        else{
            if(controller.GameLose()){
                System.out.println("LOSE!");
                gameFrame.LoseDialog();
                //有待完善
            }
        }
    }

    @Override
    public void doMoveRight() {
        System.out.println("Click VK_RIGHT");
        if (controller.doMove(hero.getRow(), hero.getCol(), Direction.RIGHT)) {
            this.afterMove();
            int[][] temmat=new int[model.getHeight()][model.getWidth()];
            for(int i=0;i<model.getHeight();i++){
                for(int j=0;j<model.getWidth();j++){
                    temmat[i][j]=model.getId(i,j);
                }
            }
            loadSave.addmap(new MapSave(new MapMatrix(temmat)));
        }
        Overcheck();
    }

    @Override
    public void doMoveLeft() {
        System.out.println("Click VK_LEFT");
        if(controller.doMove(hero.getRow(), hero.getCol(), Direction.LEFT)){
            this.afterMove();
            int[][] temmat=new int[model.getHeight()][model.getWidth()];
            for(int i=0;i<model.getHeight();i++){
                for(int j=0;j<model.getWidth();j++){
                    temmat[i][j]=model.getId(i,j);
                }
            }
            loadSave.addmap(new MapSave(new MapMatrix(temmat)));
        }
        Overcheck();
    }

    @Override
    public void doMoveUp() {
        System.out.println("Click VK_Up");
        if( controller.doMove(hero.getRow(), hero.getCol(), Direction.UP)){
           this.afterMove();
            int[][] temmat=new int[model.getHeight()][model.getWidth()];
            for(int i=0;i<model.getHeight();i++){
                for(int j=0;j<model.getWidth();j++){
                    temmat[i][j]=model.getId(i,j);
                }
            }
            loadSave.addmap(new MapSave(new MapMatrix(temmat)));
        }
        Overcheck();
    }

    @Override
    public void doMoveDown() {
        System.out.println("Click VK_DOWN");
        if(controller.doMove(hero.getRow(), hero.getCol(), Direction.DOWN)){
            this.afterMove();
            int[][] temmat=new int[model.getHeight()][model.getWidth()];
            for(int i=0;i<model.getHeight();i++){
                for(int j=0;j<model.getWidth();j++){
                    temmat[i][j]=model.getId(i,j);
                }
            }
            loadSave.addmap(new MapSave(new MapMatrix(temmat)));
        }
        Overcheck();
    }

    public void afterMove() {
        this.steps++;
        this.stepLabel.setText(String.format("Step: %d", this.steps));
    }

    public void setStepLabel(JLabel stepLabel) {
        this.stepLabel = stepLabel;
    }


    public void setController(GameController controller) {
        this.controller = controller;
    }

    public GameController getController() {
        return controller;
    }

    public GridComponent getGridComponent(int row, int col) {
        return grids[row][col];
    }

    public void resetStep(){
        this.steps = 0;
        this.stepLabel.setText("Start");
    }

    public void setPlayerManager(PlayerManager playerManager){
        this.playerManager = playerManager;
    }

    public void setPlayerName(String playerName){
        this.PlayerName = playerName;
    }

    public void setGameFrame(GameFrame gameFrame){
        this.gameFrame = gameFrame;
    }

    public MapSave getOriginalModel() {
        return originalModel;
    }

    public int getSteps(){
        return steps;
    }

    public void loadreset(){
        this.loadSave.resetwith(originalModel);
    }

    public void pausefor(int ptime){
        try {
            Thread.sleep(ptime);     //设置暂停的时间
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
