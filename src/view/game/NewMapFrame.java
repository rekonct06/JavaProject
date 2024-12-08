package view.game;

import model.MapMatrix;
import player.PlayerManager;
import save.LoadSave;
import save.MapSave;
import view.FrameUtil;
import view.level.LevelFrame;
import view.level.ReadDataFrame;
import view.sound.AudioPlayer;

import javax.swing.*;
import javax.swing.Box;
import java.awt.*;
import java.util.concurrent.Future;

public class NewMapFrame extends JFrame {
    private JButton upBtn;
    private JButton downBtn;
    private JButton leftBtn;
    private JButton rightBtn;
    private JButton sureBtn;
    private JButton addBoxBtn;
    private JButton addHeroBtn;
    private JButton addBrickBtn;
    private JButton addGoalBtn;
    private JButton deleteBtn;
    private JButton addHoleBtn;

    private NewMapPanel newMapPanel;

    private int BoxCnt;
    private int GoalCnt;
    private int HeroCnt;

    private PlayerManager playerManager;
    private String NowName;

    public NewMapFrame(int width, int height, MapMatrix mapMatrix, LevelFrame levelFrame, ReadDataFrame readDataFrame,PlayerManager playerManager,String NowName) {
        this.setTitle("2024 CS109 Project Demo");
        this.setLayout(null);
        this.setSize(width*50+300, height*50+200);

        this.playerManager = playerManager;
        this.NowName = NowName;

        this.BoxCnt = 0;
        this.GoalCnt = 0;
        this.HeroCnt = 0;

        this.newMapPanel = new NewMapPanel(mapMatrix);

        this.add(newMapPanel);
        newMapPanel.requestFocusInWindow();

        this.upBtn= FrameUtil.createButton(this, "Up", new Point(newMapPanel.getWidth() + 100, 260), 70, 50);
        this.downBtn=FrameUtil.createButton(this, "Down", new Point(newMapPanel.getWidth() + 100, 360), 70, 50);
        this.leftBtn=FrameUtil.createButton(this, "Left", new Point(newMapPanel.getWidth() + 60, 310), 70, 50);
        this.rightBtn=FrameUtil.createButton(this, "Right", new Point(newMapPanel.getWidth() + 140, 310), 70, 50);

        this.sureBtn = FrameUtil.createButton(this, "Finish", new Point(newMapPanel.getWidth() + 180, 10), 90, 40);
        this.deleteBtn= FrameUtil.createButton(this, "Delete", new Point(newMapPanel.getWidth() + 180, 60), 90, 40);
        this.addBoxBtn = FrameUtil.createButton(this, "Add Box", new Point(newMapPanel.getWidth() + 80, 10), 90, 40);
        this.addBrickBtn = FrameUtil.createButton(this, "Add Brick", new Point(newMapPanel.getWidth() + 80, 60), 90, 40);
        this.addGoalBtn = FrameUtil.createButton(this, "Add Goal", new Point(newMapPanel.getWidth() + 80, 110), 90, 40);
        this.addHeroBtn = FrameUtil.createButton(this,"Add Hero", new Point(newMapPanel.getWidth() + 80, 160), 90, 40);
        this.addHoleBtn = FrameUtil.createButton(this,"Add Hole", new Point(newMapPanel.getWidth() + 80, 210), 90, 40);

        this.sureBtn.addActionListener(e -> {
            /*
            for(int i=0;i<newheight;i++){
                for(int j=0;j<newwidth;j++){
                    atry[i][j]=mapMatrix.getId(i,j);
                    if(atry[i][j]>=50){atry[i][j]-=50;}
                    System.out.printf("%d ",atry[i][j]);
                }
                System.out.println();
            }*/
            if(HeroCnt!=1){
                String tempath = "data/sound/die.wav";
                Future<?> future1 = AudioPlayer.playSound(tempath);
                newdialog("There must be one Hero");
            }
            else{
                if(BoxCnt!=GoalCnt||BoxCnt==0||GoalCnt==0){
                    String tempath = "data/sound/die.wav";
                    Future<?> future1 = AudioPlayer.playSound(tempath);
                    newdialog("The number of Boxes and Goals should be same and cannot be zero");
                }
                else{
                    String tempath = "data/sound/win.wav";
                    Future<?> future1 = AudioPlayer.playSound(tempath);

                    for(int i=0;i<height;i++){
                        for(int j=0;j<width;j++){
                            if(mapMatrix.getId(i,j)>=50){mapMatrix.getMatrix()[i][j]-=50;}
                            System.out.printf("%d ",mapMatrix.getMatrix()[i][j]);
                        }
                        System.out.println();
                    }
                    MapSave orisave=new MapSave(mapMatrix);
                    LoadSave loadSave=new LoadSave(NowName);
                    GameFrame gameFrame = new GameFrame(600, 450, mapMatrix, orisave, loadSave ,playerManager, NowName,levelFrame);
                    gameFrame.setPlayerName(this.NowName);
                    gameFrame.setPlayerManager(this.playerManager);
                    gameFrame.setLevelFrame(levelFrame);
                    this.setVisible(false);
                    this.dispose();
                    gameFrame.setVisible(true);
                }
            }
            newMapPanel.requestFocusInWindow();
        });

        this.addBoxBtn.addActionListener(e -> {
            if(newMapPanel.addBox())     BoxCnt++;
            newMapPanel.requestFocusInWindow();
        });

        this.addHeroBtn.addActionListener(e -> {
            if(newMapPanel.addHero())    HeroCnt++;
            newMapPanel.requestFocusInWindow();
        });

        this.addBrickBtn.addActionListener(e -> {
            newMapPanel.addBrick();
            newMapPanel.requestFocusInWindow();
        });

        this.addGoalBtn.addActionListener(e -> {
            if(newMapPanel.addGoal())     GoalCnt++;
            newMapPanel.requestFocusInWindow();
        });

        this.addHoleBtn.addActionListener(e -> {
            newMapPanel.addHole();
            newMapPanel.requestFocusInWindow();
        });

        this.deleteBtn.addActionListener(e -> {
            String tempath = "data/sound/cling.wav";
            Future<?> future1 = AudioPlayer.playSound(tempath);
            if(newMapPanel.HaveBox())BoxCnt--;
            if(newMapPanel.HaveGoal())GoalCnt--;
            if(newMapPanel.HaveHero())HeroCnt--;
            newMapPanel.deleteall();
            newMapPanel.requestFocusInWindow();
        });

        this.upBtn.addActionListener(e -> {
            newMapPanel.doMoveUp();
            newMapPanel.requestFocusInWindow();
        });
        this.downBtn.addActionListener(e -> {
            newMapPanel.doMoveDown();
            newMapPanel.requestFocusInWindow();
        });
        this.leftBtn.addActionListener(e -> {
            newMapPanel.doMoveLeft();
            newMapPanel.requestFocusInWindow();
        });
        this.rightBtn.addActionListener(e -> {
            newMapPanel.doMoveRight();
            newMapPanel.requestFocusInWindow();
        });

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    public void newdialog(String msg) {
        JDialog dialog = new JDialog(this, "Sorry!", true);  // true: modal dialog
        dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));  // 垂直布局

        JLabel TextLabel = new JLabel(msg);
        dialog.add(TextLabel);

        // 添加垂直间隔
        dialog.add(Box.createVerticalStrut(20));  // 你可以调整这个值来增加间距

        // 添加关闭按钮
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(ev -> dialog.dispose());  // 关闭对话框
        dialog.add(closeButton);

        dialog.setSize(500, 150);
        dialog.setLocationRelativeTo(this);  // 将弹窗定位在主窗口中心
        dialog.setVisible(true);
    }

}
