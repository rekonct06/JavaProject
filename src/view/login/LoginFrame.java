package view.login;

import player.Player;
import player.PlayerManager;
import player.PlayerType;
import view.FrameUtil;
import view.level.LevelFrame;

import javax.swing.*;
import java.awt.*;
import java.io.*;

import static player.PlayerType.LOCAL;
import static player.PlayerType.VISITOR;


public class LoginFrame extends JFrame {
    private JTextField username;
    private JTextField password;
    private JButton submitBtn;
    private JButton resetBtn;
    private JButton registerBtn;
    private JButton visitorBtn;
    private LevelFrame levelFrame;
    private PlayerManager players;
    private static Player NowPlayer;

    public LoginFrame(int width, int height) {
        this.setTitle("Login Frame");
        this.setLayout(null);
        this.setSize(width, height);
        JLabel userLabel = FrameUtil.createJLabel(this, new Point(50, 20), 70, 40, "username:");
        JLabel passLabel = FrameUtil.createJLabel(this, new Point(50, 80), 70, 40, "password:");
        username = FrameUtil.createJTextField(this, new Point(120, 20), 120, 40);
        password = FrameUtil.createJTextField(this, new Point(120, 80), 120, 40);

        submitBtn = FrameUtil.createButton(this, "Confirm", new Point(40, 140), 100, 40);
        resetBtn = FrameUtil.createButton(this, "Reset", new Point(160, 140), 100, 40);
        registerBtn = FrameUtil.createButton(this, "Register", new Point(40, 200), 100, 40);
        visitorBtn = FrameUtil.createButton(this, "Visitor", new Point(160, 200), 100, 40);

        try
        {
            FileInputStream fileIn = new FileInputStream("data/user/PlayerManager.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            players = (PlayerManager) in.readObject();
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

        registerBtn.addActionListener(e -> {
            System.out.println("Username = " + username.getText());
            System.out.println("Password = " + password.getText());
            NowPlayer = new Player(players.getCntPlayer()+1, username.getText(), password.getText(), LOCAL);
            players.addPlayer(NowPlayer);
            players.updateData();
            if (this.levelFrame != null) {
                this.levelFrame.setVisible(true);
                this.setVisible(false);
            }
        });
        submitBtn.addActionListener(e -> {
            System.out.println("Username = " + username.getText());
            System.out.println("Password = " + password.getText());
            boolean findplayer = false;
            boolean CorPass=false;
            for(Player iplayer : players.getPlayers()){
                if(iplayer.getName().equals(username.getText())){
                    findplayer = true;
                    if(iplayer.getPassword().equals(password.getText())){CorPass = true;}
                }
            }
            if(!findplayer){System.out.println("Player not found");    }
            else{
                if(!CorPass){System.out.println("Password does not match");    }
                else{
                    if (this.levelFrame != null) {
                        this.levelFrame.setVisible(true);
                        this.setVisible(false);
                    }
                }
            }

            //todo: check login info

        });
        visitorBtn.addActionListener(e -> {
            System.out.println("You are a visitor");
            NowPlayer = new Player(0, "Visitor", "Visitor", VISITOR);
            if (this.levelFrame != null) {
                this.levelFrame.setVisible(true);
                this.setVisible(false);
            }
        });
        resetBtn.addActionListener(e -> {
            username.setText("");
            password.setText("");
        });

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void setLevelFrame(LevelFrame levelFrame) {
        this.levelFrame = levelFrame;
    }
}
