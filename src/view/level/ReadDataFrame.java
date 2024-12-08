package view.level;

import model.MapMatrix;
import player.PlayerManager;
import view.game.NewMapFrame;
import view.sound.AudioPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Future;

import static view.level.LevelFrame.showInputDialog;

public class ReadDataFrame extends JFrame {
    private LevelFrame levelFrame;

    public ReadDataFrame(LevelFrame levelFrame, PlayerManager playerManager, String NowName) {
        this.setTitle("Input");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(100, 150);
        this.setLocationRelativeTo(null);

        this.levelFrame = levelFrame;

        JButton button = new JButton("输入宽高");
        JButton cloBtn = new JButton("Close");

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(button);
        panel.add(Box.createVerticalStrut(10));  // 添加垂直间隔
        panel.add(cloBtn);

        // 按钮的事件监听
        button.addActionListener(ev -> {
            String[] input = showInputDialog();
            if (input != null) {
                try {
                    // 将宽度和高度转换为 int 类型
                    int width = Integer.parseInt(input[0]);
                    int height = Integer.parseInt(input[1]);

                    // 打印转换后的宽度和高度
                    System.out.println("宽度: " + width + ", 高度: " + height);

                    int newwidth = width, newheight = height;
                    int[][] atry = new int[newheight][newwidth];
                    for (int i = 0; i < newheight; i++) {
                        for (int j = 0; j < newwidth; j++) {
                            if (i == 0 || j == 0 || i == newheight - 1 || j == newwidth - 1) {
                                atry[i][j] = 1;
                                continue;
                            }
                            if (i == 1 && j == 1) atry[i][j] = 50;
                            else atry[i][j] = 0;
                        }
                    }

                    MapMatrix mapMatrix = new MapMatrix(atry);
                    NewMapFrame newMapFrame = new NewMapFrame(newwidth, newheight, mapMatrix, levelFrame, this, playerManager, NowName);
                    levelFrame.setVisible(false);
                    this.setVisible(false);
                    this.dispose();
                    newMapFrame.setVisible(true);

                } catch (NumberFormatException ex) {
                    // 如果输入无法转换为整数，给出提示
                    JOptionPane.showMessageDialog(this, "请输入有效的数字！", "输入错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cloBtn.addActionListener(ev -> {
            this.setVisible(false);
            this.dispose();
            levelFrame.setVisible(true);
        });

        this.add(panel, BorderLayout.CENTER);
    }

    public String[] showInputDialog() {
        // 创建一个 JPanel 用于输入框
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JTextField widthField = new JTextField();
        JTextField heightField = new JTextField();

        panel.add(new JLabel("宽度:"));
        panel.add(widthField);
        panel.add(new JLabel("高度:"));
        panel.add(heightField);

        // 创建确认和取消按钮
        int option = JOptionPane.showOptionDialog(this, panel, "请输入宽度和高度",
                JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, new Object[] { "确认", "取消" }, "确认");

        // 如果选择了确认按钮，返回宽度和高度的值
        if (option == JOptionPane.YES_OPTION) {
            return new String[] { widthField.getText(), heightField.getText() };
        } else {
            return null; // 取消输入
        }
    }
}
