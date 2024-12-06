package player;

import save.LoadSave;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PlayerManager implements Serializable {
    public static final long serialVersionUID = 1L;
    private int CntPlayer = 0;
    ArrayList <Player> players=null;
    public PlayerManager() {
        CntPlayer = 0;
        players=new ArrayList<>();
    }

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

    public void PlayerWin(String WinnerName){
        for(Player iplayer : players) {
            if(iplayer.getName().equals(WinnerName)){
                iplayer.win();
                break;
            }
        }
    }

    public void AddLoadto(String username, LoadSave iload){
        for(Player iplayer : players) {
            if(iplayer.getName().equals(username)){
                iplayer.newload(iload);
                break;
            }
        }
    }

    public int getloadnum(String username){
        for(Player iplayer : players) {
            if(iplayer.getName().equals(username)){
                return iplayer.getCntload();
            }
        }
        return 0;
    }

    public LoadSave getloadofid(String username,int lid){
        for(Player iplayer : players) {
            if(iplayer.getName().equals(username)){
                if(lid<3)return iplayer.getLoads().get(lid);
                else return null;
            }
        }
        return null;
    }

    public void sortPlayersByWins() {
        if (players != null) {
            Collections.sort(players, new Comparator<Player>() {
                @Override
                public int compare(Player p1, Player p2) {
                    return Integer.compare(p2.gethasWin(), p1.gethasWin());
                }
            });
        }
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
