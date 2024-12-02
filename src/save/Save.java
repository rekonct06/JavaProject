package save;

import java.io.Serializable;
import java.util.Stack;

public class Save implements Serializable {
    public Class<?> boardClass;
//    public Stack<Action> actionStack;
    public int width;
    public int height;

    /*
    public Save(Class<?> boardClass, Stack<Action> actionStack) {
        this.boardClass = boardClass;
        this.actionStack = actionStack;
        this.width = Game.getWidth();
        this.height = Game.getHeight();
    }
    */
}
