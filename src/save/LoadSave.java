package save;

import model.MapMatrix;
import player.PlayerManager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LoadSave implements Serializable {
    public static final long serialVersionUID = 1L;
    private String username;
    private List<MapSave> load ;

    public LoadSave(String username) {
        this.username = username;
        load = new ArrayList<MapSave>();
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public List<MapSave> getLoad() {
        return load;
    }
    public void setLoad(List<MapSave> load) {
        this.load = load;
    }

    public void addmap(MapSave mapSave) {
        load.add(mapSave);
    }

    public int getstep(MapSave mapSave) {
        return load.indexOf(mapSave);
    }

    public int getmapnum(){
        return load.size();
    }

    public void resetwith(MapSave mapSave) {
        load.clear();
        load.add(mapSave);
    }

    public MapSave getimap(int lid){
        if(lid>=load.size()){return null;}
        return load.get(lid);
    }

    public void deletelast(){
        if (!this.load.isEmpty()) {
            this.load.remove(this.load.size() - 1);
        }
    }

    public void saveInFile(PlayerManager playerManager,int loadid) {
        playerManager.AddLoadto(this.username,this,loadid);
        playerManager.updateData();
        /*
        try
        {
            String path = String.format("data/game/%s/%dload.ser",this.username,playerManager.getloadnum(this.username));
            FileOutputStream fileOut =
                    new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in "+path);
        }catch(IOException i)
        {
            i.printStackTrace();
        }
        */
    }

}
