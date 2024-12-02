package save;

public class UnmatchedSizeException extends Exception {
    public final int expectedWidth;
    public final int expectedHeight;
    public final int realWidth;
    public final int realHeight;

    public UnmatchedSizeException(int expectedWidth, int expectedHeight, int realWidth, int realHeight) {
        super(String.format("Expected: (%d, %d), loaded: (%d, %d). ", expectedWidth, expectedHeight, realWidth, realHeight));
        this.expectedWidth = expectedWidth;
        this.expectedHeight = expectedHeight;
        this.realWidth = realWidth;
        this.realHeight = realHeight;
    }
}
