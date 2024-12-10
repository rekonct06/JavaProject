package player;

import model.MapMatrix;
import save.LoadSave;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public  class Player implements Serializable {
    public static final long serialVersionUID = 1L;
    private final int id;
    private final String name;
    private final String password;
    protected final PlayerType type;
    private int hasWin;
    private int Cntload;
    private List<LoadSave> loads;
    private int[] levelstate;
    private int[] levelstep;
    private MapMatrix ownmap;

    public Player(int id, String name, String password,PlayerType type) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.type = type;
        this.hasWin = 0;
        this.Cntload = 0;
        this.loads = new ArrayList<LoadSave>();
        this.levelstate = new int[10];
        this.levelstep = new int[10];
        this.ownmap=null;
        for(int i=0;i<=8;i++){
            this.levelstate[i] = 0;
            this.levelstep[i] = 100000;
        }
    }

    public void onNotify() {
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {return this.password;}

    public PlayerType getType() {
        return this.type;
    }

    public void win(int levelid,int step) {
        if(levelid==0)return;
        if(levelstate[levelid]==0){
            levelstate[levelid]=1;
            this.hasWin++;
        }
        if(levelstep[levelid]>step){levelstep[levelid]=step;}
    }

    public int getstep(int levelid){
        if(levelstep==null)return 100000;
        return levelstep[levelid];
    }

    public int getCntload() {
        this.Cntload=0;
        for(LoadSave il:this.loads){
            if(il!=null)this.Cntload++;
        }
        return this.Cntload;
    }

    public int gethasWin() {
        return this.hasWin;
    }

    public void setCntload(int Cntload) {
        this.Cntload = Cntload;
    }

    public void revive() {
        this.hasWin = 0;
    }

    public List<LoadSave> getLoads() {
        return this.loads;
    }
    public void setLoads(List<LoadSave> loads) {
        this.loads = loads;
    }

    public void newload(LoadSave load,int loadid){
        if(this.loads==null)this.loads=new ArrayList<LoadSave>();
        while(this.loads.size()<=loadid){
            this.loads.add(null);
        }
        this.loads.set(loadid,load);
        this.Cntload=0;
        for(LoadSave il:this.loads){
            if(il!=null)this.Cntload++;
        }
    }

    public void setOwnmap(MapMatrix ownmap) {
        this.ownmap = ownmap;
    }
    public MapMatrix getOwnmap() {
        return this.ownmap;
    }
}
