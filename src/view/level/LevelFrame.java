package view.level;

import model.MapMatrix;
import player.PlayerManager;
import save.LoadSave;
import save.MapSave;
import view.FrameUtil;
import view.game.GameFrame;
import view.game.NewMapFrame;
import view.login.LoginFrame;
import view.level.ReadLoadFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import static javax.swing.JOptionPane.showInputDialog;

public class LevelFrame extends JFrame {
    private String NowName;
    private LoginFrame loginFrame;
    private PlayerManager playerManager;
    private GameFrame gameFrame;


    public LevelFrame(int width, int height) {
        this.setTitle("Level");
        this.setLayout(null);
        this.setSize(width, height);
        JButton level1Btn = FrameUtil.createButton(this, "1", new Point(30, 50), 60, 60);
        JButton level2Btn = FrameUtil.createButton(this, "2", new Point(120,  50), 60, 60);
        JButton level3Btn = FrameUtil.createButton(this, "3", new Point(210,  50), 60, 60);
        JButton level4Btn = FrameUtil.createButton(this, "4", new Point(300,  50), 60, 60);
        JButton level5Btn = FrameUtil.createButton(this, "5", new Point(390,  50), 60, 60);
        JButton level6Btn = FrameUtil.createButton(this, "6", new Point(480,  50), 60, 60);
        JButton level7Btn = FrameUtil.createButton(this, "7", new Point(570,  50), 60, 60);

        JButton loadBtn=FrameUtil.createButton(this, "Read loads", new Point(30, height - 100), 100, 60);
        JButton newmapBtn=FrameUtil.createButton(this, "New map", new Point(200, height - 100), 100, 60);

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
            int[][] temmat=new int[mapMatrix.getHeight()][mapMatrix.getWidth()];
            for(int i=0;i<mapMatrix.getHeight();i++){
                for(int j=0;j<mapMatrix.getWidth();j++){
                    temmat[i][j]=mapMatrix.getId(i,j);
                }
            }
            MapSave orisave=new MapSave(new MapMatrix(temmat));
            LoadSave loadSave=new LoadSave(NowName);
            GameFrame gameFrame = new GameFrame(600, 450, mapMatrix, orisave, loadSave ,playerManager, NowName,this);
            this.gameFrame = gameFrame;
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
            int[][] temmat=new int[mapMatrix.getHeight()][mapMatrix.getWidth()];
            for(int i=0;i<mapMatrix.getHeight();i++){
                for(int j=0;j<mapMatrix.getWidth();j++){
                    temmat[i][j]=mapMatrix.getId(i,j);
                }
            }
            MapSave orisave=new MapSave(new MapMatrix(temmat));
            LoadSave loadSave=new LoadSave(NowName);
            GameFrame gameFrame = new GameFrame(600, 450, mapMatrix, orisave, loadSave ,playerManager, NowName,this);
            this.gameFrame = gameFrame;
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
            int[][] temmat=new int[mapMatrix.getHeight()][mapMatrix.getWidth()];
            for(int i=0;i<mapMatrix.getHeight();i++){
                for(int j=0;j<mapMatrix.getWidth();j++){
                    temmat[i][j]=mapMatrix.getId(i,j);
                }
            }
            MapSave orisave=new MapSave(new MapMatrix(temmat));
            LoadSave loadSave=new LoadSave(NowName);
            GameFrame gameFrame = new GameFrame(600, 450, mapMatrix, orisave, loadSave ,playerManager, NowName,this);
            this.gameFrame = gameFrame;
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
            int[][] temmat=new int[mapMatrix.getHeight()][mapMatrix.getWidth()];
            for(int i=0;i<mapMatrix.getHeight();i++){
                for(int j=0;j<mapMatrix.getWidth();j++){
                    temmat[i][j]=mapMatrix.getId(i,j);
                }
            }
            MapSave orisave=new MapSave(new MapMatrix(temmat));
            LoadSave loadSave=new LoadSave(NowName);
            GameFrame gameFrame = new GameFrame(600, 450, mapMatrix, orisave, loadSave ,playerManager, NowName,this);
            this.gameFrame = gameFrame;
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
            int[][] temmat=new int[mapMatrix.getHeight()][mapMatrix.getWidth()];
            for(int i=0;i<mapMatrix.getHeight();i++){
                for(int j=0;j<mapMatrix.getWidth();j++){
                    temmat[i][j]=mapMatrix.getId(i,j);
                }
            }
            MapSave orisave=new MapSave(new MapMatrix(temmat));
            LoadSave loadSave=new LoadSave(NowName);
            GameFrame gameFrame = new GameFrame(600, 450, mapMatrix, orisave, loadSave ,playerManager, NowName,this);
            this.gameFrame = gameFrame;
            gameFrame.setPlayerName(this.NowName);
            gameFrame.setPlayerManager(this.playerManager);
            gameFrame.setLevelFrame(this);
            this.setVisible(false);
            gameFrame.setVisible(true);
        });

        level6Btn.addActionListener(l->{
            MapMatrix mapMatrix =null;
            try
            {
                FileInputStream fileIn = new FileInputStream("data/map/map6.ser");
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
            int[][] temmat=new int[mapMatrix.getHeight()][mapMatrix.getWidth()];
            for(int i=0;i<mapMatrix.getHeight();i++){
                for(int j=0;j<mapMatrix.getWidth();j++){
                    temmat[i][j]=mapMatrix.getId(i,j);
                }
            }
            MapSave orisave=new MapSave(new MapMatrix(temmat));
            LoadSave loadSave=new LoadSave(NowName);
            GameFrame gameFrame = new GameFrame(600, 450, mapMatrix, orisave, loadSave ,playerManager, NowName,this);
            this.gameFrame = gameFrame;
            gameFrame.setPlayerName(this.NowName);
            gameFrame.setPlayerManager(this.playerManager);
            gameFrame.setLevelFrame(this);
            this.setVisible(false);
            gameFrame.setVisible(true);
        });

        level7Btn.addActionListener(l->{

        });

        //todo: complete other level.

        loadBtn.addActionListener(l->{
            if(NowName.equals("Visitor")||playerManager.getloadnum(NowName)==0){
                JDialog dialog = new JDialog(this, "Sorry!", true);  // true: modal dialog
                dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));  // 垂直布局

                dialog.add(new JLabel(String.format("%s do not have any load", NowName)));

                // 添加关闭按钮
                JButton closeButton = new JButton("Close");
                closeButton.addActionListener(ev -> dialog.dispose());  // 关闭对话框
                dialog.add(closeButton);

                dialog.setSize(250, 100);
                dialog.setLocationRelativeTo(this);  // 将弹窗定位在主窗口中心
                dialog.setVisible(true);
            }
            else{
                ReadLoadFrame readLoadFrame=new ReadLoadFrame(200,500,this,playerManager,NowName);
                readLoadFrame.setVisible(true);

            }

        });

        newmapBtn.addActionListener(l->{
            ReadDataFrame readDataFrame=new ReadDataFrame(this,playerManager,NowName);
            readDataFrame.setVisible(true);
        });


        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public void startGame(int[][] temmat){
        MapMatrix mapMatrix=new MapMatrix(temmat);
        MapSave orisave=new MapSave(new MapMatrix(temmat));
        LoadSave loadSave=new LoadSave(NowName);
        GameFrame gameFrame = new GameFrame(600, 450, mapMatrix, orisave, loadSave ,playerManager, NowName,this);
        this.gameFrame = gameFrame;
        gameFrame.setPlayerName(this.NowName);
        gameFrame.setPlayerManager(this.playerManager);
        gameFrame.setLevelFrame(this);
        this.setVisible(false);
        gameFrame.setVisible(true);
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

    public void setGameFrame(GameFrame gameFrame){
        this.gameFrame=gameFrame;
    }

    public void setPlayerManager(PlayerManager playerManager){
        this.playerManager = playerManager;
        if(this.playerManager==null) System.out.println("Player manager is null");
    }

    public void newDialog(){
        JFrame frame = new JFrame("Input");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(100, 100);
        frame.setLocationRelativeTo(null);

        // 创建一个按钮，点击后弹出对话框
        JButton button = new JButton("输入宽高");
        JButton cloBtn = new JButton("Close");

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(button);
        panel.add(Box.createVerticalStrut(10));  // 添加垂直间隔
        panel.add(cloBtn);


        // 按钮的事件监听
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] input = showInputDialog();
                if (input != null) {
                    try {
                        // 将宽度和高度转换为 int 类型
                        int width = Integer.parseInt(input[0]);
                        int height = Integer.parseInt(input[1]);

                        // 打印转换后的宽度和高度
                        System.out.println("宽度: " + width + ", 高度: " + height);

                        // 如果需要进行其他操作，可以继续处理转换后的数据
                        frame.dispose();
                    } catch (NumberFormatException ex) {
                        // 如果输入无法转换为整数，给出提示
                        JOptionPane.showMessageDialog(frame, "请输入有效的数字！", "输入错误", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        cloBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        // 将按钮面板添加到窗口
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }


    public static String[] showInputDialog() {
        // 创建一个 JPanel 用于输入框
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JTextField widthField = new JTextField();
        JTextField heightField = new JTextField();

        panel.add(new JLabel("宽度:"));
        panel.add(widthField);
        panel.add(new JLabel("高度:"));
        panel.add(heightField);

        // 创建确认和取消按钮
        int option = JOptionPane.showOptionDialog(null, panel, "请输入宽度和高度",
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


            /*
            MapMatrix mapMatrix = new MapMatrix(new int[][]{

                    {1, 1, 1, 1, 0, 0, 0, 0, 0},
                    {1, 20, 0, 3, 1, 0, 0, 0, 0},
                    {1, 0, 10, 10, 2, 1, 0, 0, 0},
                    {0, 1, 2, 10, 10, 2, 1, 0, 0},
                    {0, 0, 1, 2, 10, 10, 2, 1, 0},
                    {0, 0, 0, 1, 2, 10, 10, 2, 1},
                    {0, 0, 0, 0, 1, 2, 10, 0, 1},
                    {0, 0, 0, 0, 0, 1, 2, 0, 1},
                    {0, 0, 0, 0, 0, 0, 1, 1, 1}
            });

            try
            {
                FileOutputStream fileOut =
                        new FileOutputStream("data/map/map6.ser");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(mapMatrix);
                out.close();
                fileOut.close();
                System.out.printf("Serialized data is saved in data/map/map6.ser");
            }catch(IOException i)
            {
                i.printStackTrace();
            }
            */