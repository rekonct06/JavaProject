package view.level;

import model.MapMatrix;
import player.Player;
import player.PlayerManager;
import save.LoadSave;
import save.MapSave;
import view.FrameUtil;
import view.game.GameFrame;
import view.level.LevelFrame;

import javax.swing.*;
import java.awt.*;
import java.io.*;


public class ReadLoadFrame extends JFrame {
    private String NowName;
    private int loadnum;
    private LevelFrame levelFrame;
    private PlayerManager playerManager;


    private JLabel viewloadnum;

    public ReadLoadFrame(int width, int height,LevelFrame levelFrame, PlayerManager playerManager,String NowName) {
        this.setTitle("Choose a Load");
        this.setLayout(null);
        this.setSize(width+100, height);
        this.levelFrame = levelFrame;
        this.playerManager = playerManager;
        this.NowName = NowName;

        this.loadnum = playerManager.getloadnum(NowName);

        this.viewloadnum = FrameUtil.createJLabel(this, "0", new Font("serif", Font.ITALIC, 22), new Point(30, 30), 180, 50);

        JButton load1Btn = FrameUtil.createButton(this, "Load 1", new Point(50, 100), 200, 40);
        JButton load2Btn = FrameUtil.createButton(this, "Load 2", new Point(50, 150), 200, 40);
        JButton load3Btn = FrameUtil.createButton(this, "Load 3", new Point(50, 200), 200, 40);
        JButton closeBtn = FrameUtil.createButton(this, "Close", new Point(50, 300), 100, 40);

        if(playerManager.getloadofid(NowName,0)==null)load1Btn.setText("Load 1:Empty");
        if(playerManager.getloadofid(NowName,1)==null)load2Btn.setText("Load 2:Empty");
        if(playerManager.getloadofid(NowName,2)==null)load3Btn.setText("Load 3:Empty");

        viewloadnum.setText(String.format("Loads number: %d",loadnum));

        load1Btn.addActionListener(l->{
            LoadSave loadSaveF=playerManager.getloadofid(NowName,0);
            if(loadSaveF==null){
                JOptionPane.showMessageDialog(levelFrame, "Load Failed", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                LoadSave loadSave=new LoadSave(NowName);
                for(MapSave imsave: loadSaveF.getLoad()){
                    int[][] temmat=new int[imsave.getMapMatrix().getHeight()][imsave.getMapMatrix().getWidth()];
                    for(int i=0;i<imsave.getMapMatrix().getHeight();i++){
                        for(int j=0;j<imsave.getMapMatrix().getWidth();j++){
                            temmat[i][j]=imsave.getMapMatrix().getId(i,j);
                        }
                    }
                    loadSave.addmap(new MapSave(new MapMatrix(temmat)));
                }
                MapSave osave=loadSave.getimap(0);
                int[][] temmap=new int[osave.getMapMatrix().getHeight()][osave.getMapMatrix().getWidth()];
                for(int i=0;i<osave.getMapMatrix().getHeight();i++){
                    for(int j=0;j<osave.getMapMatrix().getWidth();j++){
                        temmap[i][j]=osave.getMapMatrix().getId(i,j);
                    }
                }
                MapMatrix mapMatrix=new MapMatrix(temmap);
                MapSave orisave=new MapSave(new MapMatrix(temmap));
                GameFrame gameFrame = new GameFrame(600,450,mapMatrix,orisave,loadSave,playerManager,NowName,levelFrame);
                //    this.levelFrame.setVisible(false);
                //    gameFrame.setVisible(true);
                this.dispose();
            }
            /*

            MapSave orisave=new MapSave(mapMatrix);
            LoadSave loadSave=new LoadSave(NowName);
            GameFrame gameFrame = new GameFrame(600, 450, mapMatrix, orisave, loadSave ,playerManager, NowName);
            this.gameFrame = gameFrame;
            gameFrame.setPlayerName(this.NowName);
            gameFrame.setPlayerManager(this.playerManager);
            gameFrame.setLevelFrame(this);
            this.setVisible(false);
            gameFrame.setVisible(true);
             */
        });

        load2Btn.addActionListener(l->{
            LoadSave loadSaveF=playerManager.getloadofid(NowName,1);
            if(loadSaveF==null){
                JOptionPane.showMessageDialog(levelFrame, "Load Empty", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                LoadSave loadSave=new LoadSave(NowName);
                for(MapSave imsave: loadSaveF.getLoad()){
                    int[][] temmat=new int[imsave.getMapMatrix().getHeight()][imsave.getMapMatrix().getWidth()];
                    for(int i=0;i<imsave.getMapMatrix().getHeight();i++){
                        for(int j=0;j<imsave.getMapMatrix().getWidth();j++){
                            temmat[i][j]=imsave.getMapMatrix().getId(i,j);
                        }
                    }
                    loadSave.addmap(new MapSave(new MapMatrix(temmat)));
                }
                MapSave osave=loadSave.getimap(0);
                int[][] temmap=new int[osave.getMapMatrix().getHeight()][osave.getMapMatrix().getWidth()];
                for(int i=0;i<osave.getMapMatrix().getHeight();i++){
                    for(int j=0;j<osave.getMapMatrix().getWidth();j++){
                        temmap[i][j]=osave.getMapMatrix().getId(i,j);
                    }
                }
                MapMatrix mapMatrix=new MapMatrix(temmap);
                MapSave orisave=new MapSave(new MapMatrix(temmap));
                GameFrame gameFrame = new GameFrame(600,450,mapMatrix,orisave,loadSave,playerManager,NowName,levelFrame);
                //    this.levelFrame.setVisible(false);
                //    gameFrame.setVisible(true);
                this.dispose();
            }
        });

        load3Btn.addActionListener(l->{
            LoadSave loadSaveF=playerManager.getloadofid(NowName,2);
            if(loadSaveF==null){
                JOptionPane.showMessageDialog(levelFrame, "Load Failed", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                LoadSave loadSave=new LoadSave(NowName);
                for(MapSave imsave: loadSaveF.getLoad()){
                    int[][] temmat=new int[imsave.getMapMatrix().getHeight()][imsave.getMapMatrix().getWidth()];
                    for(int i=0;i<imsave.getMapMatrix().getHeight();i++){
                        for(int j=0;j<imsave.getMapMatrix().getWidth();j++){
                            temmat[i][j]=imsave.getMapMatrix().getId(i,j);
                        }
                    }
                    loadSave.addmap(new MapSave(new MapMatrix(temmat)));
                }
                MapSave osave=loadSave.getimap(0);
                int[][] temmap=new int[osave.getMapMatrix().getHeight()][osave.getMapMatrix().getWidth()];
                for(int i=0;i<osave.getMapMatrix().getHeight();i++){
                    for(int j=0;j<osave.getMapMatrix().getWidth();j++){
                        temmap[i][j]=osave.getMapMatrix().getId(i,j);
                    }
                }
                MapMatrix mapMatrix=new MapMatrix(temmap);
                MapSave orisave=new MapSave(new MapMatrix(temmap));
                GameFrame gameFrame = new GameFrame(600,450,mapMatrix,orisave,loadSave,playerManager,NowName,levelFrame);
                //    this.levelFrame.setVisible(false);
                //    gameFrame.setVisible(true);
                this.dispose();
            }
        });

        closeBtn.addActionListener(l->{
            this.dispose();
        });

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void setLevelFrame(LevelFrame levelFrame) {
        this.levelFrame = levelFrame;
    }
    public void setPlayerManager(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }
    public void setNowName(String NowName) {
        this.NowName = NowName;
    }
}
