package view.game;

import javax.swing.*;
import java.awt.*;

import controller.GameController;
import model.MapMatrix;
import view.FrameUtil;

public class GameFrame extends JFrame {

    private GameController controller;
    private JButton restartBtn;
    private JButton loadBtn;
    private JButton upBtn;
    private JButton downBtn;
    private JButton leftBtn;
    private JButton rightBtn;


    private JLabel stepLabel;
    private GamePanel gamePanel;

    public GameFrame(int width, int height, MapMatrix mapMatrix) {
        this.setTitle("2024 CS109 Project Demo");
        this.setLayout(null);
        this.setSize(width, height);
        gamePanel = new GamePanel(mapMatrix);
        gamePanel.setLocation(30, height / 2 - gamePanel.getHeight() / 2);
        this.add(gamePanel);
        this.controller = new GameController(gamePanel, mapMatrix);

        this.upBtn=FrameUtil.createButton(this, "Up", new Point(gamePanel.getWidth() + 100, 200), 70, 50);
        this.downBtn=FrameUtil.createButton(this, "Down", new Point(gamePanel.getWidth() + 100, 300), 70, 50);
        this.leftBtn=FrameUtil.createButton(this, "Left", new Point(gamePanel.getWidth() + 60, 250), 70, 50);
        this.rightBtn=FrameUtil.createButton(this, "Right", new Point(gamePanel.getWidth() + 140, 250), 70, 50);
        this.restartBtn = FrameUtil.createButton(this, "Restart", new Point(gamePanel.getWidth() + 80, 80), 80, 50);
        this.loadBtn = FrameUtil.createButton(this, "Load", new Point(gamePanel.getWidth() + 80, 140), 80, 50);
        this.stepLabel = FrameUtil.createJLabel(this, "Start", new Font("serif", Font.ITALIC, 22), new Point(gamePanel.getWidth() + 80, 30), 180, 50);
        gamePanel.setStepLabel(stepLabel);

        this.restartBtn.addActionListener(e -> {
            controller.restartGame();
            gamePanel.requestFocusInWindow();//enable key listener
        });
        this.loadBtn.addActionListener(e -> {
            String string = JOptionPane.showInputDialog(this, "Input path:");
            System.out.println(string);
            gamePanel.requestFocusInWindow();//enable key listener
        });

        this.upBtn.addActionListener(e -> {
            gamePanel.doMoveUp();
        });
        this.downBtn.addActionListener(e -> {
            gamePanel.doMoveDown();
        });
        this.leftBtn.addActionListener(e -> {
            gamePanel.doMoveLeft();
        });
        this.rightBtn.addActionListener(e -> {
            gamePanel.doMoveRight();
        });
        //todo: add other button here
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
