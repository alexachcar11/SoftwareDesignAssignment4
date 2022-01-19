package comp303.assignment4;

import java.util.ArrayList;

/**
 * Class for building an enhanced filter for elements in the Library
 */
public class FilterBuilder {

    ArrayList<WatchListFilter> aFilters = new ArrayList<WatchListFilter>();

    /**
     * Allows the client to add a filter to the enhanced filter builder.
     *
     * @param pFilter
     *
     * @pre pFilter != null
     */
    public void addFilter(WatchListFilter pFilter) {
        assert pFilter != null;
        aFilters.add(pFilter);
    }

    /**
     * Allows the client to remove a filter from the enhanced filter builder.
     *
     * @param pFilter
     *
     * @pre pFilter != null
     */
    public void removeFilter(WatchListFilter pFilter) {
        assert pFilter != null;
        aFilters.remove(pFilter);
    }

    public ArrayList<WatchListFilter> getFilters() {
        return aFilters;
    }

    public int numberOfFilters() {
        return aFilters.size();
    }

    /**
     * Builds a conjunction of filters that are added by the client. Doesn't include a watchable unless it passes
     * all of the filters in aFilters. If one filter is missed, it evaluates to false.
     *
     * @return
     *        WatchListFilter consisting of the requirements indicated by the client
     *
     * @pre aFilters.size() < 0;
     */
    public WatchListFilter buildConjunction() {
        assert aFilters.size() != 0;
        return new WatchListFilter() {
            @Override
            public boolean filter(Movie pMovie) {
                for (WatchListFilter f : aFilters) {
                    if(!f.filter(pMovie)) {
                        return false;
                    }
                }
                return true;
            }

            @Override
            public boolean filter(TVShow pTVShow) {
                for (WatchListFilter f : aFilters) {
                    if(!f.filter(pTVShow)) {
                        return false;
                    }
                }
                return true;
            }

            @Override
            public boolean filter(Episode pEpisode) {
                for (WatchListFilter f : aFilters) {
                    if(!f.filter(pEpisode)) {
                        return false;
                    }
                }
                return true;
            }
        };
    }

    /**
     * Builds a disjunction of filters that are added by the client. Includes a Watchable if it passes any one of the
     * filters inside aFilters. If it doesn't match any of the filters, it evaluates to False.
     *
     * @return
     *        WatchListFilter consisting of the requirements indicated by the client
     *
     * @pre aFilters.size() < 0;
     */
    public WatchListFilter buildDisjunction() {
        assert aFilters.size() != 0;
        return new WatchListFilter() {
            @Override
            public boolean filter(Movie pMovie) {
                for(WatchListFilter f : aFilters) {
                    if (f.filter(pMovie)) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public boolean filter(TVShow pTVShow) {
                for(WatchListFilter f : aFilters) {
                    if (f.filter(pTVShow)) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public boolean filter(Episode pEpisode) {
                for(WatchListFilter f : aFilters) {
                    if (f.filter(pEpisode)) {
                        return true;
                    }
                }
                return false;
            }
        };
    }

}
