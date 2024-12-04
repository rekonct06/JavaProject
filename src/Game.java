import model.MapMatrix;
import player.PlayerManager;
import view.level.LevelFrame;
import view.login.LoginFrame;

import javax.swing.*;
import java.io.*;

public class Game {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        //    PlayerManager players=null;
            /*
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
             */
            LoginFrame loginFrame = new LoginFrame(280,350);
            loginFrame.setVisible(true);
            LevelFrame levelFrame = new LevelFrame(680,200);
            levelFrame.setVisible(false);
            loginFrame.setLevelFrame(levelFrame);
            levelFrame.setLoginFrame(loginFrame);
        });
    }
}
