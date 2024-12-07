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


public class SaveLoadFrame extends JFrame {
    private String NowName;
    private LevelFrame levelFrame;
    private PlayerManager playerManager;
    private LoadSave loadSave;
    private GameFrame gameFrame;

    public SaveLoadFrame(String NowName,LevelFrame levelFrame,PlayerManager playerManager,LoadSave loadSave,GameFrame gameFrame) {

        this.setTitle("Choose a Load");
        this.setLayout(null);
        this.setSize(300, 500);

        this.NowName = NowName;
        this.levelFrame = levelFrame;
        this.playerManager = playerManager;
        this.loadSave = loadSave;
        this.gameFrame = gameFrame;



        JButton load1Btn = FrameUtil.createButton(this, "Load 1", new Point(30, 50), 200, 40);
        JButton load2Btn = FrameUtil.createButton(this, "Load 2", new Point(30, 100), 200, 40);
        JButton load3Btn = FrameUtil.createButton(this, "Load 3", new Point(30, 150), 200, 40);
        JButton closeBtn = FrameUtil.createButton(this, "Close", new Point(30, 200), 100, 40);

        if(playerManager.getloadofid(NowName,0)==null)load1Btn.setText("Load 1:Empty");
        if(playerManager.getloadofid(NowName,1)==null)load2Btn.setText("Load 2:Empty");
        if(playerManager.getloadofid(NowName,2)==null)load3Btn.setText("Load 3:Empty");

        load1Btn.addActionListener(l->{
            playerManager.AddLoadto(NowName,loadSave,0);
            playerManager.updateData();
            this.dispose();
            this.setVisible(false);
            this.gameFrame.setVisible(false);
            this.gameFrame.dispose();
            this.levelFrame.setVisible(true);
        });

        load2Btn.addActionListener(l->{
            playerManager.AddLoadto(NowName,loadSave,1);
            playerManager.updateData();
            this.dispose();
            this.setVisible(false);
            this.gameFrame.setVisible(false);
            this.gameFrame.dispose();
            this.levelFrame.setVisible(true);
        });

        load3Btn.addActionListener(l->{
            playerManager.AddLoadto(NowName,loadSave,2);
            playerManager.updateData();
            this.dispose();
            this.setVisible(false);
            this.gameFrame.setVisible(false);
            this.gameFrame.dispose();
            this.levelFrame.setVisible(true);
        });

        closeBtn.addActionListener(l->{
            this.dispose();
            this.setVisible(false);
            this.gameFrame.setVisible(true);
        });

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }



}
