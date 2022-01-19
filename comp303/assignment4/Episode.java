package comp303.assignment4;

import java.io.File;
import java.util.*;

/**
 * Represents a single episode, with at least a title, and an episode number. Each episode is identified by its path on
 * the file system.
 */
public class Episode implements Sequenceable<Episode>, Cloneable {

    private File aPath;
    private final TVShow aTVShow;
    private String aTitle;
    private int aEpisodeNumber;
    private Map<String, String> aCast = new HashMap<>();
    private Map<String, String> aTags = new HashMap<>();

    /**
     * Creates an episode from the file path. This method should not be called by a client. Use
     * TVShow.createAndAddEpisode instead.
     *
     * @param pPath
     *            location of the episode on the file system.
     * @param pTVShow
     *            TVShow that this episode is a part of
     * @param pTitle
     *            official title of the episode
     * @param pEpisodeNumber
     *            the episode number that identifies the episode
     * @pre pPath!=null && pTVShow != null && pTitle!=null
     * @throws IllegalArgumentException
     *             if the path doesn't point to a file (e.g., it denotes a directory)
     */
    Episode(File pPath, TVShow pTVShow, String pTitle, int pEpisodeNumber) {
        // Package-private constructor AND notice in the Javadoc to prevent clients from using this constructor.
        // Still, a client could create an Episode directly, and it would only affect the episode object, not the TV
        // show.
        // Alternatively, the Episode class could be nested inside TVShow, with a private constructor.
        assert (pPath != null) && (pTVShow != null) && (pTitle != null);
        if (pPath.exists() && !pPath.isFile()) {
            throw new IllegalArgumentException("The path should point to a file.");
        }
        aPath = pPath; // ok because File is immutable.
        aTVShow = pTVShow;
        aTitle = pTitle;
        aEpisodeNumber = pEpisodeNumber;
    }

    @Override
    public void watch() {
        System.out.println("Now watching " + aTVShow.getTitle() + " [" + aEpisodeNumber + "]: " + aTitle);
    }

    @Override
    public boolean isValid() {
        return aPath.isFile() && aPath.canRead();
    }

    @Override
    public String getTitle() {
        return aTitle;
    }

    public PublishingStudio getStudio() {
        return aTVShow.getStudio();
    }

    public Language getLanguage() {
        return aTVShow.getLanguage();
    }

    /**
     * @return the episode number of the episode
     */
    public int getEpisodeNumber() {
        return aEpisodeNumber;
    }

    public String setCast(String pCharacter, String pActor) {
        if (pActor == null) {
            return aCast.remove(pCharacter);
        }
        else {
            return aCast.put(pCharacter, pActor);
        }
    }

    public String getCast(String pCharacter) {
        return aCast.get(pCharacter);
    }

    public Set<String> getAllCharacters() {
        return Collections.unmodifiableSet(aCast.keySet());
    }

    public String setInfo(String pKey, String pValue) {
        if (pValue == null) {
            return aTags.remove(pKey);
        }
        else {
            return aTags.put(pKey, pValue);
        }
    }

    public String getInfo(String pKey) {
        return aTags.get(pKey);
    }

    public boolean hasInfo(String pKey) {
        return aTags.containsKey(pKey);
    }

    public TVShow getTVShow() {
        return aTVShow;
    }

    @Override
    public boolean hasPrevious() {
        return aEpisodeNumber > 1;
    }

    @Override
    public boolean hasNext() {
        return aEpisodeNumber < aTVShow.getTotalCount();
    }

    @Override
    public Episode getPrevious() {
        return aTVShow.getEpisode(aEpisodeNumber - 1);
    }

    @Override
    public Episode getNext() {
        return aTVShow.getEpisode(aEpisodeNumber + 1);
    }

    /**
     * Creates a clone of the episode. Retains the parent TVShow attribute, but allows the user to change the path,
     * title, and sets the episode number to one above the episode that it is cloning. Note that the episode attribute
     * can be changed in the life-cycle of the episode.
     *
     * @return cloned episode
     *
     * @throws CloneNotSupportedException
     *             if the object can't or shouldn't be cloned.
     */
    @Override
    public Episode clone() {
        try {
            Episode clone = (Episode) super.clone();

            clone.aEpisodeNumber = this.getTVShow().getTotalCount()+1;

            return clone;
        }
        catch (CloneNotSupportedException e)
        {
            assert false;
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Episode episode = (Episode) o;

        return aPath.equals(episode.aPath) &&
                aTVShow.equals(episode.aTVShow) &&
                aTitle.equals(episode.aTitle) &&
                Objects.equals(aCast, episode.aCast) &&
                Objects.equals(aTags, episode.aTags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aPath, aTVShow, aTitle, aEpisodeNumber, aCast, aTags);
    }
}
