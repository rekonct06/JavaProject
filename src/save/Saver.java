package save;

import java.io.IOException;

public abstract class Saver {
    private int slotNumber = 3;

    public Saver() {
    }

    public abstract Save getLoadedSave();

    public abstract boolean hasLoadedSave();

    public abstract void clearLoadedSave();

    public abstract void load(String var1) throws IOException, ClassNotFoundException, UnmatchedSizeException;

    public abstract void save(String var1) throws IOException;

    public abstract void checkSize(boolean var1);

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public int getSlotNumber() {
        return this.slotNumber;
    }
}
