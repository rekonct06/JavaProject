package view.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import java.util.concurrent.Future;

import controller.GameController;
import model.MapMatrix;
import player.Player;
import player.PlayerManager;
import save.LoadSave;
import save.MapSave;
import view.FrameUtil;
import view.level.LevelFrame;
import view.level.SaveLoadFrame;
import view.sound.AudioPlayer;

public class GameFrame extends JFrame {

    private GameController controller;
    private JButton restartBtn;
    private JButton loadBtn;
    private JButton upBtn;
    private JButton downBtn;
    private JButton leftBtn;
    private JButton rightBtn;
    private JButton quitBtn;
    private JButton undoBtn;

    private Timer timer;

    private JLabel stepLabel;
    private GamePanel gamePanel;

    private LevelFrame levelFrame;
    private PlayerManager playerManager;
    private String PlayerName;

    private LoadSave loadSave;

    public GameFrame(int width, int height, MapMatrix mapMatrix, MapSave orimapsave, LoadSave loadSave ,PlayerManager playerManager, String playerName, LevelFrame levelFrame) {
        this.setTitle("2024 CS109 Project Demo");
        this.setLayout(null);
        this.setSize(width+50, height+50);


        // 调用 playSound 方法来播放音效
        String soundPath = "data/sound/start.wav";
        Future<?> future = AudioPlayer.playSound(soundPath);

        this.levelFrame = levelFrame;
        this.loadSave = loadSave;
        this.playerManager = playerManager;
        this.PlayerName = playerName;

        this.CreateTimer();
        this.stepLabel = FrameUtil.createJLabel(this, "Start", new Font("serif", Font.ITALIC, 22), new Point((mapMatrix.getWidth() * 50 + 4) + 80, 30), 180, 50);
    //    gamePanel.setStepLabel(stepLabel);


        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setVisible(true);
        this.levelFrame.setVisible(false);


        gamePanel = new GamePanel(mapMatrix, orimapsave ,this, loadSave, stepLabel);
        gamePanel.setLocation(30, height / 2 - gamePanel.getHeight() / 2);
        gamePanel.setGameFrame(this);
        gamePanel.setPlayerManager(playerManager);
        gamePanel.setPlayerName(playerName);
        this.add(gamePanel);

        this.setController(gamePanel.getController());

        gamePanel.requestFocusInWindow();

    //    this.controller = new GameController(gamePanel, mapMatrix);

        this.upBtn=FrameUtil.createButton(this, "Up", new Point(gamePanel.getWidth() + 100, 280), 70, 50);
        this.downBtn=FrameUtil.createButton(this, "Down", new Point(gamePanel.getWidth() + 100, 380), 70, 50);
        this.leftBtn=FrameUtil.createButton(this, "Left", new Point(gamePanel.getWidth() + 60, 330), 70, 50);
        this.rightBtn=FrameUtil.createButton(this, "Right", new Point(gamePanel.getWidth() + 140, 330), 70, 50);

        this.restartBtn = FrameUtil.createButton(this, "Restart", new Point(gamePanel.getWidth() + 80, 80), 90, 40);
        this.loadBtn = FrameUtil.createButton(this, "Save", new Point(gamePanel.getWidth() + 80, 130), 90, 40);
        this.quitBtn = FrameUtil.createButton(this, "Menu", new Point(gamePanel.getWidth() + 80, 180), 90, 40);
        this.undoBtn = FrameUtil.createButton(this, "Undo", new Point(gamePanel.getWidth() + 80, 230), 90, 40);


        this.restartBtn.addActionListener(e -> {

            String tempath = "data/sound/cling.wav";
            Future<?> future1 = AudioPlayer.playSound(tempath);

            controller.restartGame();
            gamePanel.requestFocusInWindow();//enable key listener
        });
        this.loadBtn.addActionListener(e -> {
            timer.stop();

            String tempath = "data/sound/cling.wav";
            Future<?> future2 = AudioPlayer.playSound(tempath);

            if(playerName.equals("Visitor")) {
                JDialog dialog = new JDialog(this, "Sorry!", true);  // true: modal dialog
                dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));  // 垂直布局

                dialog.add(new JLabel("Visitor cannot save a load"));

                // 添加关闭按钮
                JButton closeButton = new JButton("Close");
                closeButton.addActionListener(ev -> {
                    dialog.dispose();
                    gamePanel.requestFocusInWindow();
                });  // 关闭对话框
                dialog.add(closeButton);

                dialog.setSize(250, 100);
                dialog.setLocationRelativeTo(this);  // 将弹窗定位在主窗口中心
                dialog.setVisible(true);
            }
            else{
                /*
                int loadid;
                playerManager.AddLoadto(PlayerName,loadSave,loadid);
                playerManager.updateData();
                */

                SaveLoadFrame saveLoadFrame=new SaveLoadFrame(PlayerName,levelFrame,playerManager,loadSave ,this);

            }
            timer.start();
            /* 这是原版自定义存档路径的代码，我将改成可以选择存档窗口的模式
            String string = JOptionPane.showInputDialog(this, "Input path:");
            System.out.println(string);
            gamePanel.requestFocusInWindow();//enable key listener
             */
        });

        this.undoBtn.addActionListener(e -> {

            String tempath = "data/sound/cling.wav";
            Future<?> future3 = AudioPlayer.playSound(tempath);

            if(gamePanel.getSteps()==0){
            //    JOptionPane.showMessageDialog(this, "You are on the first step", "Error", JOptionPane.ERROR_MESSAGE);
                JDialog dialog = new JDialog(this, "Sorry!", true);  // true: modal dialog
                dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));  // 垂直布局
                dialog.add(new JLabel("You are on the first step"));
                // 添加关闭按钮
                JButton closeButton = new JButton("Close");
                closeButton.addActionListener(ev -> dialog.dispose());  // 关闭对话框
                dialog.add(closeButton);
                dialog.setSize(250, 100);
                dialog.setLocationRelativeTo(this);  // 将弹窗定位在主窗口中心
                dialog.setVisible(true);
            }
            else{
                gamePanel.undostep();
            //    this.setFocusable(true);
            }
            gamePanel.requestFocusInWindow();
        });

        this.upBtn.addActionListener(e -> {
            gamePanel.doMoveUp();
            gamePanel.requestFocusInWindow();
        });
        this.downBtn.addActionListener(e -> {
            gamePanel.doMoveDown();
            gamePanel.requestFocusInWindow();
        });
        this.leftBtn.addActionListener(e -> {
            gamePanel.doMoveLeft();
            gamePanel.requestFocusInWindow();
        });
        this.rightBtn.addActionListener(e -> {
            gamePanel.doMoveRight();
            gamePanel.requestFocusInWindow();
        });

        this.quitBtn.addActionListener(e -> {

            // 调用 playSound 方法来播放音效
            String tempath = "data/sound/cling.wav";
            Future<?> future4 = AudioPlayer.playSound(tempath);

            if(!playerName.equals("Visitor")){

            }
            this.levelFrame.setVisible(true);
            this.dispose();
        });

        //todo: add other button here




        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public void CreateTimer(){
        JLabel timerLabel = new JLabel("0", SwingConstants.CENTER);
        timerLabel.setFont(new Font("Serif", Font.BOLD, 25));

        // 设置 JLabel 的位置和大小：x, y, width, height
        timerLabel.setBounds(520, 10, 100, 50);
        this.add(timerLabel);

        this.timer = new Timer(1000, e -> {
            // 解析当前秒数并更新文本
            int seconds = Integer.parseInt(timerLabel.getText().replace("Time: ", ""));
            timerLabel.setText("Time: " + (seconds + 1)); // 更新文本
        });

        // 启动计时器
        timer.start();

    }

    public void WinDialog(){

        // 调用 playSound 方法来播放音效
        String soundPath = "data/sound/win.wav";
        Future<?> future5 = AudioPlayer.playSound(soundPath);

        JDialog dialog = new JDialog(this, "Congratulations!", true);  // true: modal dialog
        dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));  // 设置对齐方式和组件间距
        dialog.add(new JLabel("You win!"));
        JButton closeButton = new JButton("Back To Menu");
        closeButton.addActionListener(ev -> {
            dialog.dispose();
            this.levelFrame.setVisible(true);
            this.dispose();
        });  // 关闭对话框
        dialog.add(closeButton);
        dialog.setSize(250, 150);
        dialog.setLocationRelativeTo(this);  // 将弹窗定位在主窗口中心
        dialog.setVisible(true);
    }

    public void LoseDialog(){

        // 调用 playSound 方法来播放音效
        String soundPath = "data/sound/die.wav";
        Future<?> future6 = AudioPlayer.playSound(soundPath);

        JDialog dialog = new JDialog(this, "Sorry", true);
        dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));
        dialog.add(new JLabel("You lose!"));
        JButton closeButton = new JButton("Back To Menu");
        JButton restartButton = new JButton("Restart");
        closeButton.addActionListener(ev -> {
            dialog.dispose();
            this.levelFrame.setVisible(true);
            this.dispose();
        });
        restartButton.addActionListener(ev -> {
            controller.restartGame();
            gamePanel.requestFocusInWindow();
            dialog.dispose();
        });
        dialog.add(closeButton);
        dialog.add(restartButton);
        dialog.setSize(250, 150);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    public String getPlayerName(){
        return this.PlayerName;
    }
    public void setPlayerName(String playerName){
        this.PlayerName = playerName;
    }

    public void setLevelFrame(LevelFrame levelFrame){
        this.levelFrame = levelFrame;
    }

    public void setPlayerManager(PlayerManager playerManager){
        this.playerManager = playerManager;
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }

}
