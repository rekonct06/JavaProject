package view.level;

import model.MapMatrix;
import player.PlayerManager;
import view.FrameUtil;
import view.game.GameFrame;
import view.login.LoginFrame;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LevelFrame extends JFrame {
    private String NowName;
    private LoginFrame loginFrame;
    private PlayerManager playerManager;
    public LevelFrame(int width, int height) {
        this.setTitle("Level");
        this.setLayout(null);
        this.setSize(width, height);
        JButton level1Btn = FrameUtil.createButton(this, "Level1", new Point(30, height / 2 - 50), 60, 60);
        JButton level2Btn = FrameUtil.createButton(this, "Level2", new Point(120, height / 2 - 50), 60, 60);
        JButton level3Btn = FrameUtil.createButton(this, "Level3", new Point(210, height / 2 - 50), 60, 60);
        JButton level4Btn = FrameUtil.createButton(this, "Level4", new Point(300, height / 2 - 50), 60, 60);
        JButton level5Btn = FrameUtil.createButton(this, "Level5", new Point(390, height / 2 - 50), 60, 60);

        level1Btn.addActionListener(l->{
            MapMatrix mapMatrix =null;
            try
            {
                FileInputStream fileIn = new FileInputStream("data/map/map1.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                mapMatrix = (MapMatrix) in.readObject();
                in.close();
                fileIn.close();
            }catch(IOException i)
            {
                i.printStackTrace();
                return;
            }catch(ClassNotFoundException c)
            {
                System.out.println("Map class not found");
                c.printStackTrace();
                return;
            }
            GameFrame gameFrame = new GameFrame(600, 450, mapMatrix,playerManager, NowName);
            gameFrame.setPlayerName(NowName);
            gameFrame.setPlayerManager(this.playerManager);
            gameFrame.setLevelFrame(this);
            this.setVisible(false);
            gameFrame.setVisible(true);
        });

        level2Btn.addActionListener(l->{
            MapMatrix mapMatrix =null;
            try
            {
                FileInputStream fileIn = new FileInputStream("data/map/map2.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                mapMatrix = (MapMatrix) in.readObject();
                in.close();
                fileIn.close();
            }catch(IOException i)
            {
                i.printStackTrace();
                return;
            }catch(ClassNotFoundException c)
            {
                System.out.println("Map class not found");
                c.printStackTrace();
                return;
            }
            GameFrame gameFrame = new GameFrame(600, 450, mapMatrix, playerManager, NowName);
            gameFrame.setPlayerName(this.NowName);
            gameFrame.setPlayerManager(this.playerManager);
            gameFrame.setLevelFrame(this);
            this.setVisible(false);
            gameFrame.setVisible(true);
        });

        level3Btn.addActionListener(l->{
            MapMatrix mapMatrix =null;
            try
            {
                FileInputStream fileIn = new FileInputStream("data/map/map3.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                mapMatrix = (MapMatrix) in.readObject();
                in.close();
                fileIn.close();
            }catch(IOException i)
            {
                i.printStackTrace();
                return;
            }catch(ClassNotFoundException c)
            {
                System.out.println("Map class not found");
                c.printStackTrace();
                return;
            }
            GameFrame gameFrame = new GameFrame(600, 450, mapMatrix,playerManager, NowName);
            gameFrame.setPlayerName(this.NowName);
            gameFrame.setPlayerManager(this.playerManager);
            gameFrame.setLevelFrame(this);
            this.setVisible(false);
            gameFrame.setVisible(true);
        });

        level4Btn.addActionListener(l->{
            MapMatrix mapMatrix =null;
            try
            {
                FileInputStream fileIn = new FileInputStream("data/map/map4.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                mapMatrix = (MapMatrix) in.readObject();
                in.close();
                fileIn.close();
            }catch(IOException i)
            {
                i.printStackTrace();
                return;
            }catch(ClassNotFoundException c)
            {
                System.out.println("Map class not found");
                c.printStackTrace();
                return;
            }
            GameFrame gameFrame = new GameFrame(600, 450, mapMatrix,playerManager, NowName);
            gameFrame.setPlayerName(this.NowName);
            gameFrame.setPlayerManager(this.playerManager);
            gameFrame.setLevelFrame(this);
            this.setVisible(false);
            gameFrame.setVisible(true);
        });

        level5Btn.addActionListener(l->{
            MapMatrix mapMatrix =null;
            try
            {
                FileInputStream fileIn = new FileInputStream("data/map/map5.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                mapMatrix = (MapMatrix) in.readObject();
                in.close();
                fileIn.close();
            }catch(IOException i)
            {
                i.printStackTrace();
                return;
            }catch(ClassNotFoundException c)
            {
                System.out.println("Map class not found");
                c.printStackTrace();
                return;
            }
            GameFrame gameFrame = new GameFrame(600, 450, mapMatrix,playerManager, NowName);
            gameFrame.setPlayerName(this.NowName);
            gameFrame.setPlayerManager(this.playerManager);
            gameFrame.setLevelFrame(this);
            this.setVisible(false);
            gameFrame.setVisible(true);
        });

        //todo: complete other level.

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void setNowName(String NName){
        this.NowName=NName;
    }
    public String getNowName(){
        return this.NowName;
    }

    public void setLoginFrame(LoginFrame loginFrame){
        this.loginFrame=loginFrame;
    }

    public void setPlayerManager(PlayerManager playerManager){
        this.playerManager = playerManager;
        if(this.playerManager==null) System.out.println("Player manager is null");
    }

}

/*
level1Btn.addActionListener(l->{
            MapMatrix mapMatrix = new MapMatrix(new int[][]{
                    {1, 1, 1, 1, 1, 1},
                    {1, 20, 0, 0, 0, 1},
                    {1, 0, 0, 10, 2, 1},
                    {1, 0, 2, 10, 0, 1},
                    {1, 1, 1, 1, 1, 1},
            });
            GameFrame gameFrame = new GameFrame(600, 450, mapMatrix);
            this.setVisible(false);
            gameFrame.setVisible(true);
        });

        level2Btn.addActionListener(l->{
            MapMatrix mapMatrix = new MapMatrix(new int[][]{
                    {1, 1, 1, 1, 1, 1, 0},
                    {1, 20, 0, 0, 0, 1, 1},
                    {1, 0, 10, 10, 0, 0, 1},
                    {1, 0, 1, 2, 0, 2, 1},
                    {1, 0, 0, 0, 0, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1},
            });
            GameFrame gameFrame = new GameFrame(600, 450, mapMatrix);
            this.setVisible(false);
            gameFrame.setVisible(true);
        });

        level3Btn.addActionListener(l->{
            MapMatrix mapMatrix = new MapMatrix(new int[][]{
                    {0, 0, 1, 1, 1, 1, 0},
                    {1, 1, 1, 0, 0, 1, 0},
                    {1, 20, 0, 2, 10, 1, 1},
                    {1, 0, 0, 0, 10, 0, 1},
                    {1, 0, 1, 2, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1},
            });
            GameFrame gameFrame = new GameFrame(600, 450, mapMatrix);
            this.setVisible(false);
            gameFrame.setVisible(true);
        });

        level4Btn.addActionListener(l->{
            MapMatrix mapMatrix = new MapMatrix(new int[][]{
                    {0, 1, 1, 1, 1, 1, 0},
                    {1, 1, 20, 0, 0, 1, 1},
                    {1, 0, 0, 1, 0, 0, 1},
                    {1, 0, 10, 12, 10, 0, 1},
                    {1, 0, 0, 2, 0, 0, 1},
                    {1, 1, 0, 2, 0, 1, 1},
                    {0, 1, 1, 1, 1, 1, 0},
            });
            GameFrame gameFrame = new GameFrame(600, 450, mapMatrix);
            this.setVisible(false);
            gameFrame.setVisible(true);
        });

        level5Btn.addActionListener(l->{
            MapMatrix mapMatrix = new MapMatrix(new int[][]{
                    {1, 1, 1, 1, 1, 1, 0, 0},
                    {1, 0, 0, 0, 0, 1, 1, 1},
                    {1, 0, 0, 0, 2, 2, 0, 1},
                    {1, 0, 10, 10, 10, 20, 0, 1},
                    {1, 0, 0, 1, 0, 2, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1},
            });
            GameFrame gameFrame = new GameFrame(600, 450, mapMatrix);
            this.setVisible(false);
            gameFrame.setVisible(true);
        });
 */