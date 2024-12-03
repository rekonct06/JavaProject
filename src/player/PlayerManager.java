package player;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class PlayerManager implements Serializable {
    public static final long serialVersionUID = 1L;
    private int CntPlayer = 0;
    ArrayList <Player> players=null;
    public PlayerManager() {  }

    public int getCntPlayer() {
        return CntPlayer;
    }
    public void addPlayer(Player player) {
        if (this.players == null) {
            this.players = new ArrayList<>();
        }
        players.add(player);
        CntPlayer++;
    }
    public ArrayList <Player> getPlayers() {
        return players;
    }
    public void updateData() {
        try
        {
            FileOutputStream fileOut =
                    new FileOutputStream("data/user/PlayerManager.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in data/user/PlayerManager.ser");
        }catch(IOException i)
        {
            i.printStackTrace();
        }
    }
}
