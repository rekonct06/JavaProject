package player;

import java.io.Serializable;

public class PlayerInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String name;
    private final String password;
    private int winCount = 0;

    public PlayerInfo(String name,String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public int getWinCount() {
        return this.winCount;
    }

    public void addWinCount() {
        ++this.winCount;
    }

}

