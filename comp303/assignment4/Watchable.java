package comp303.assignment4;

/**
 * Represents a video object that can be watched
 */
public interface Watchable {

    /**
     * Plays the video to the user
     * @pre isValid()
     */
    public void watch();

    public String getTitle();

    /**
     * Indicates whether the Watchable element is ready to be played.
     *
     * @return true if the file path points to an existing location that can be read and is not a directory
     */
    public boolean isValid();

}
