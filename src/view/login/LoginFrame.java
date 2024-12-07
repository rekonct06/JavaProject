package view.login;

import player.Player;
import player.PlayerManager;
import player.PlayerType;
import view.FrameUtil;
import view.level.LevelFrame;
import view.sound.AudioPlayer;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.concurrent.Future;

import static player.PlayerType.LOCAL;
import static player.PlayerType.VISITOR;


public class LoginFrame extends JFrame {
    private JTextField username;
    private JTextField password;
    private JButton submitBtn;
    private JButton resetBtn;
    private JButton registerBtn;
    private JButton visitorBtn;
    private JButton rankwinBtn;
    private LevelFrame levelFrame;
    private PlayerManager Manager;
    private static Player NowPlayer;
    private String NowName;

    public LoginFrame(int width, int height) {
        this.setTitle("Login Frame");
        this.setLayout(null);
        this.setSize(width, height);

        // 调用 playBgm 方法来播放背景音乐

        String bgmPath = "data/sound/bgm.wav";

        Future<?> future = AudioPlayer.playBgm(bgmPath);
        System.out.println("Audio started: " + future.isDone());



        /*

        // 创建 AudioPlayer 对象并调用 play 方法来播放音频文件
        AudioPlayer Soundplayer = new AudioPlayer();
        String filePath = "data/sound/bgm.wav";
        Soundplayer.play(filePath);
        */

// 如果需要取消播放，可以调用 future.cancel(true);


        JLabel userLabel = FrameUtil.createJLabel(this, new Point(50, 20), 70, 40, "username:");
        JLabel passLabel = FrameUtil.createJLabel(this, new Point(50, 80), 70, 40, "password:");
        username = FrameUtil.createJTextField(this, new Point(120, 20), 120, 40);
        password = FrameUtil.createJTextField(this, new Point(120, 80), 120, 40);

        submitBtn = FrameUtil.createButton(this, "Confirm", new Point(40, 140), 100, 40);
        resetBtn = FrameUtil.createButton(this, "Reset", new Point(160, 140), 100, 40);
        registerBtn = FrameUtil.createButton(this, "Register", new Point(40, 200), 100, 40);
        visitorBtn = FrameUtil.createButton(this, "Visitor", new Point(160, 200), 100, 40);
        rankwinBtn = FrameUtil.createButton(this, "Rank", new Point(40, 260), 100, 40);


        try
        {
            FileInputStream fileIn = new FileInputStream("data/user/PlayerManager.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Manager = (PlayerManager) in.readObject();
            in.close();
            fileIn.close();
        }catch(IOException i)
        {
            i.printStackTrace();
            return;
        }catch(ClassNotFoundException c)
        {
            System.out.println("PlayerManager class not found");
            c.printStackTrace();
            return;
        }
        if(Manager==null)Manager=new PlayerManager();

        /*
        for(Player i:Manager.getPlayers()){
            i.setLoads(null);
            i.setCntload(0);
        }

        Manager.updateData();

         */

        registerBtn.addActionListener(e -> {
            if(username.getText().equals("") || password.getText().equals("")){
                newdialog("Name and password can't be empty");
            }
            else{
                System.out.println("Username = " + username.getText());
                System.out.println("Password = " + password.getText());
                boolean findplayer = false;
                for(Player iplayer : Manager.getPlayers()){
                    if(iplayer.getName().equals(username.getText())){
                        findplayer = true;
                    }
                }
                if(!findplayer){
                    NowPlayer = new Player(Manager.getCntPlayer()+1, username.getText(), password.getText(), LOCAL);
                    Manager.addPlayer(NowPlayer);
                    Manager.updateData();
                    this.setNowName(username.getText());
                    if (this.levelFrame != null) {
                        this.levelFrame.setNowName(NowName);
                        this.levelFrame.setPlayerManager(Manager);
                        this.levelFrame.setVisible(true);
                        this.setVisible(false);
                    }
                }
                else{
                    System.out.println("Player already exists");
                    newdialog("Player already exists");
                }
            }

        });

        submitBtn.addActionListener(e -> {
            if(username.getText().equals("") || password.getText().equals("")){
                newdialog("Name and password can't be empty");
            }
            else{
                System.out.println("Username = " + username.getText());
                System.out.println("Password = " + password.getText());
                boolean findplayer = false;
                boolean CorPass=false;
                for(Player iplayer : Manager.getPlayers()){
                    if(iplayer.getName().equals(username.getText())){
                        findplayer = true;
                        if(iplayer.getPassword().equals(password.getText())){CorPass = true;}
                    }
                }
                if(!findplayer){System.out.println("Player not found");
                    newdialog("Player not found");
                }
                else{
                    if(!CorPass){System.out.println("Password does not match");
                        newdialog("Password does not match");
                    }
                    else{
                        this.setNowName(username.getText());
                        if (this.levelFrame != null) {
                            this.levelFrame.setNowName(NowName);
                            this.levelFrame.setPlayerManager(Manager);
                            this.levelFrame.setVisible(true);
                            this.setVisible(false);
                        }
                    }
                }
            }


            //todo: check login info

        });
        visitorBtn.addActionListener(e -> {
            System.out.println("You are a visitor");
            NowPlayer = new Player(0, "Visitor", "Visitor", VISITOR);
            this.setNowName("Visitor");
            if (this.levelFrame != null) {
                this.levelFrame.setNowName("Visitor");
                this.levelFrame.setVisible(true);
                this.setVisible(false);
            }
        });
        resetBtn.addActionListener(e -> {
            username.setText("");
            password.setText("");
        });

        rankwinBtn.addActionListener(e -> {
            Manager.sortPlayersByWins();
            JDialog dialog = new JDialog(this, "Ranking", true);  // true: modal dialog
            dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));  // 垂直布局

            // 遍历排序后的玩家列表并构建JLabel显示文本
            for (Player player : Manager.getPlayers()) {
                String playerInfo = player.getName() + ": Wins = " + player.gethasWin();
                dialog.add(new JLabel(playerInfo));
            }

            // 添加关闭按钮
            JButton closeButton = new JButton("Close");
            closeButton.addActionListener(ev -> dialog.dispose());  // 关闭对话框
            dialog.add(closeButton);

            dialog.setSize(250, 200);
            dialog.setLocationRelativeTo(this);  // 将弹窗定位在主窗口中心
            dialog.setVisible(true);
        });

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void newdialog(String msg){
        JDialog dialog = new JDialog(this, "Sorry!", true);  // true: modal dialog
        dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));  // 垂直布局

        dialog.add(new JLabel(msg));

        // 添加关闭按钮
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(ev -> dialog.dispose());  // 关闭对话框
        dialog.add(closeButton);

        dialog.setSize(250, 100);
        dialog.setLocationRelativeTo(this);  // 将弹窗定位在主窗口中心
        dialog.setVisible(true);
    }

    public void setLevelFrame(LevelFrame levelFrame) {
        this.levelFrame = levelFrame;
    }

    public LevelFrame getLevelFrame() {
        return this.levelFrame;
    }

    public String getNowName() {
        return this.NowName;
    }
    public void setNowName(String nowName) {
        this.NowName = nowName;
    }
}
