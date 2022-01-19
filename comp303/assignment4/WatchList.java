package comp303.assignment4;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Represents a sequence of watchables to watch in FIFO order.
 */
public class WatchList implements Bingeable<Watchable> {

    private final List<Watchable> aList = new LinkedList<>();
    private String aName;
    private int aNext;

    /**
     * Creates a new empty watchlist.
     *
     * @param pName
     *            the name of the list
     * @pre pName!=null;
     */
    public WatchList(String pName) {
        assert pName != null;
        aName = pName;
        aNext = 0;
    }

    public String getName() {
        return aName;
    }

    /**
     * Changes the name of this watchlist.
     *
     * @param pName
     *            the new name
     * @pre pName!=null;
     */
    public void setName(String pName) {
        assert pName != null;
        aName = pName;
    }

    /**
     * Adds a watchable at the end of this watchlist.
     *
     * @param pWatchable
     *            the watchable to add
     * @pre pWatchable!=null;
     */
    public void addWatchable(Watchable pWatchable) {
        assert pWatchable != null;
        aList.add(pWatchable);
    }

    /**
     * Retrieves and removes the next watchable to watch from this watchlist. Watchables are retrieved in FIFO order.
     */
    public Watchable removeNext() {
        return aList.remove(0);
    }

    /**
     * @return the total number of valid watchable elements
     */
    public int getValidCount() {
        int count = 0;
        for (Watchable item : aList) {
            if (item.isValid()) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int getTotalCount() {
        return aList.size();
    }

    @Override
    public int getRemainingCount() {
        return aList.size() - aNext;
    }

    @Override
    public Watchable next() {
        assert getRemainingCount() > 0;
        Watchable next = aList.get(aNext);
        aNext++;
        if (aNext >= aList.size()) {
            aNext = 0;
        }
        return next;
    }

    @Override
    public void reset() {
        aNext = 0;
    }

    @Override
    public Iterator<Watchable> iterator() {
        return Collections.unmodifiableList(aList).iterator();
    }

    public ArrayList<String> getContents() {
        ArrayList<String> list = new ArrayList<String>();
        for(Watchable watch: aList) {
            list.add(watch.getTitle());
        }
        return list;
    }

    public Boolean contains(Watchable w){
        ArrayList<Watchable> list = new ArrayList<Watchable>();
        for(Watchable watchable: aList) {
            if(watchable.equals(w)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Watchable> getClearContents() {
        ArrayList<Watchable> list = new ArrayList<Watchable>();
        for(Watchable watchable: aList) {
            list.add(watchable);
        }
        return list;
    }
}
