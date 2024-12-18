package player;

import model.MapMatrix;
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

    public void PlayerWin(String WinnerName,int levelid,int step){
        for(Player iplayer : players) {
            if(iplayer.getName().equals(WinnerName)){
                iplayer.win(levelid,step);
                break;
            }
        }
    }

    public void addownmap(String username, MapMatrix ownmap){
        for(Player iplayer : players) {
            if(iplayer.getName().equals(username)){
                iplayer.setOwnmap(ownmap);
                return;
            }
        }
    }

    public MapMatrix getOwnmap(String username){
        for(Player iplayer : players) {
            if(iplayer.getName().equals(username)){
                return iplayer.getOwnmap();
            }
        }
        return null;
    }

    public void AddLoadto(String username, LoadSave iload,int loadid){
        for(Player iplayer : players) {
            if(iplayer.getName().equals(username)){
                iplayer.newload(iload,loadid);
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
                if(iplayer.getLoads()==null)return null;
                if(iplayer.getCntload()==0)return null;
                if(lid>=iplayer.getLoads().size()){
                    return null;
                }
                if(lid<3)return iplayer.getLoads().get(lid);
                else return null;
            }
        }
        return null;
    }

    public String getminstep(int levelid){
        if(levelid==0)return null;
        int minstep=100000;
        String minname=null;
        for(Player iplayer : players) {
            if(iplayer.getstep(levelid)==100000)continue;
            if(iplayer.getstep(levelid)<minstep) {
                minstep = iplayer.getstep(levelid);
                minname = iplayer.getName();
            }
        }
        if(minstep==100000)return null;
        return String.format("%s,%d",minname,minstep);
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
