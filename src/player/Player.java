package player;

import java.io.Serializable;


public abstract class Player implements Serializable {
    public static final long serialVersionUID = 1L;
    private final int id;
    private final String name;
    private final String password;
    protected final PlayerType type;
    private static int hasWin;

    public Player(int id, String name, String password,PlayerType type) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.type = type;
    }

    public void onNotify() {
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public PlayerType getType() {
        return this.type;
    }

    public void win() {
        this.hasWin++;
    }

    public int gethasWin() {
        return this.hasWin;
    }

    public void revive() {
        this.hasWin = 0;
    }

/*    public void surrender() {
        Game.performAction(new SurrenderAction(this));
    }

    protected static Player playerFactory(int id, String name, PlayerType type) {
        Object res;
        if (type == PlayerType.AI) {
            res = AIPlayer.getAIPlayer(id, name);
        } else {
            res = new LocalPlayer(id, name);
        }

        return (Player)res;
    }*/
}
