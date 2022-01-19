package comp303.assignment4tests;

import comp303.assignment4.*;
import org.junit.jupiter.api.*;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class WhiteBoxTest {

    // NOTE: COVERAGE IS NOT VERY HIGH BECAUSE I ONLY TESTED THE METHODS THAT I EDITED DIRECTLY OR COULD EFFECTIVELY
    //       TEST WITH GOOD INTENTION.

    static Library lib = new Library();

    static File f1 = new File("Desktop/TVShows/TestShow/");


    @Test
    void tvShowTest() {
        TVShow show = new TVShow("Test Show", Language.ENGLISH, PublishingStudio.WarnerBrothers);
        assertTrue(show.getStudio() == PublishingStudio.WarnerBrothers);
        assertTrue(show.getTitle() == "Test Show");
        assertTrue(show.getLanguage() == Language.ENGLISH);

        show.setInfo("Rating", "5 stars");
        assertTrue(show.getInfo("Rating") == "5 stars");

        assertFalse(show.isValid());

        show.createAndAddEpisode(f1, "Episode 1");
        assertTrue(show.getEpisode(1).getTitle() == "Episode 1");
        assertTrue(show.getTotalCount() == 1);

        show.setPrototypeEpisode(show.getEpisode(1));
        show.createEpisode();

        assertTrue(show.getEpisode(1).hasNext());

        assertTrue(show.getEpisode(1).equals(show.getEpisode(2)));
        assertFalse(show.getEpisode(1) == show.getEpisode(2));
        assertTrue(show.getEpisode(1).getClass() == show.getEpisode(2).getClass());

        assertTrue(show.getTotalCount() == 2);
        assertTrue(show.getEpisode(1).getTitle() == show.getEpisode(2).getTitle());
        assertTrue(show.getEpisode(1).getLanguage() == show.getEpisode(2).getLanguage());
        assertTrue(show.getEpisode(1).getStudio() == show.getEpisode(2).getStudio());

    }

    @Test
    void FilterTest() {
        FilterBuilder test = new FilterBuilder();

        WatchListFilter english = new LanguageFilterStrategy(Language.ENGLISH);

        test.addFilter(english);
        assertTrue(test.numberOfFilters() == 1);

        test.removeFilter(english);
        assertTrue(test.numberOfFilters() == 0);

    }
}
