package comp303.assignment4tests;

import comp303.assignment4.*;
import org.junit.jupiter.api.*;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class BlackBoxTest {

    static Library lib = new Library();

    static File f1 = new File("Desktop/TVShows/TheOfficeS1E1.mp4");
    static File f2 = new File("Desktop/TVShows/TheOfficeS1E2.mp4");
    static File f3 = new File("Desktop/TVShows/TheOfficeS1E3.mp4");
    static File f4 = new File("Desktop/TVShows/TheOfficeS1E4.mp4");
    static File f5 = new File("Desktop/TVShows/TheOfficeS1E5.mp4");
    static File f6 = new File("Desktop/TVShows/TheOfficeS1E6.mp4");

    static TVShow theOffice = new TVShow("The Office", Language.ENGLISH, PublishingStudio.NBC);
    static TVShow theMandalorian = new TVShow("The Mandalorian", Language.FRENCH, PublishingStudio.Disney);
    static TVShow superStore = new TVShow("Superstore", Language.FRENCH, PublishingStudio.NBC);
    static TVShow theBigBangTheory = new TVShow("The Big Bang Theory", Language.ENGLISH, PublishingStudio.WarnerBrothers);
    static TVShow friends = new TVShow("Friends", Language.FRENCH, PublishingStudio.WarnerBrothers);
    static TVShow looneyTunes = new TVShow("The Looney Tunes Show", Language.SPANISH, PublishingStudio.WarnerBrothers);

    /**
     * Populating the Library with elements before the tests are run
     */
    @BeforeAll
    static void setUp() {

        theOffice.createAndAddEpisode(f1, "TheOfficeS1E1");
        theOffice.createAndAddEpisode(f2, "TheOfficeS1E2");
        theOffice.createAndAddEpisode(f3, "TheOfficeS1E3");
        theOffice.createAndAddEpisode(f4, "TheOfficeS1E4");
        theOffice.createAndAddEpisode(f5, "TheOfficeS1E5");
        theOffice.createAndAddEpisode(f6, "TheOfficeS1E6");

        theMandalorian.createAndAddEpisode(f1, "TheMandalorianS1E1");
        theMandalorian.createAndAddEpisode(f2, "TheMandalorianS1E2");
        theMandalorian.createAndAddEpisode(f3, "TheMandalorianS1E3");
        theMandalorian.createAndAddEpisode(f4, "TheMandalorianS1E4");
        theMandalorian.createAndAddEpisode(f5, "TheMandalorianS1E5");
        theMandalorian.createAndAddEpisode(f6, "TheMandalorianS1E6");

        superStore.createAndAddEpisode(f1, "SuperstoreS1E1");
        superStore.createAndAddEpisode(f2, "SuperstoreS1E2");
        superStore.createAndAddEpisode(f3, "SuperstoreS1E3");
        superStore.createAndAddEpisode(f4, "SuperstoreS1E4");
        superStore.createAndAddEpisode(f5, "SuperstoreS1E5");
        superStore.createAndAddEpisode(f6, "SuperstoreS1E6");

        theBigBangTheory.createAndAddEpisode(f1, "TheBigBangTheoryS1E1");
        theBigBangTheory.createAndAddEpisode(f2, "TheBigBangTheoryS1E2");
        theBigBangTheory.createAndAddEpisode(f3, "TheBigBangTheoryS1E3");
        theBigBangTheory.createAndAddEpisode(f4, "TheBigBangTheoryS1E4");
        theBigBangTheory.createAndAddEpisode(f5, "TheBigBangTheoryS1E5");
        theBigBangTheory.createAndAddEpisode(f6, "TheBigBangTheoryS1E6");

        friends.createAndAddEpisode(f1, "FriendsS1E1");
        friends.createAndAddEpisode(f2, "FriendsS1E2");
        friends.createAndAddEpisode(f3, "FriendsS1E3");
        friends.createAndAddEpisode(f4, "FriendsS1E4");
        friends.createAndAddEpisode(f5, "FriendsS1E5");
        friends.createAndAddEpisode(f6, "FriendsS1E6");

        looneyTunes.createAndAddEpisode(f1, "LooneyTunesS1E1");
        looneyTunes.createAndAddEpisode(f2, "LooneyTunesS1E2");
        looneyTunes.createAndAddEpisode(f3, "LooneyTunesS1E3");
        looneyTunes.createAndAddEpisode(f4, "LooneyTunesS1E4");
        looneyTunes.createAndAddEpisode(f5, "LooneyTunesS1E5");
        looneyTunes.createAndAddEpisode(f6, "LooneyTunesS1E6");

        lib.addTVShow(theOffice); //NBC ENGLISH
        lib.addTVShow(theMandalorian); //DISNEY FRENCH
        lib.addTVShow(superStore); //NBC FRENCH
        lib.addTVShow(theBigBangTheory); //WARNERBROS ENGLISH
        lib.addTVShow(friends); //WARNERBROS FRENCH
        lib.addTVShow(looneyTunes); //WARNERBROS SPANISH

    }

    @Test
    void conjunctionTest1() {
        WatchList empty = new WatchList("Empty Watchlist");

        WatchListFilter ENG = new LanguageFilterStrategy(Language.ENGLISH);
        WatchListFilter FR = new LanguageFilterStrategy(Language.FRENCH);

        FilterBuilder engOrFr = new FilterBuilder();
        engOrFr.addFilter(ENG);
        engOrFr.addFilter(FR);

        WatchList test = lib.generateWatchList("ConjTest1", engOrFr.buildConjunction());

        assertEquals(empty.getContents(), test.getContents());
    }

    @Test
    void conjunctionTest2() {
        WatchList testList = new WatchList("Contains all English TVShow episodes made by NBC");

        for(Episode e: theOffice) {
            testList.addWatchable(e);
        }

        WatchListFilter NBC = new PublishingStudioFilterStrategy(PublishingStudio.NBC);
        WatchListFilter ENG = new LanguageFilterStrategy(Language.ENGLISH);

        FilterBuilder NBCandEng = new FilterBuilder();
        NBCandEng.addFilter(NBC);
        NBCandEng.addFilter(ENG);

        WatchList test = lib.generateWatchList("ConjTest2", NBCandEng.buildConjunction());

        assertEquals(testList.getContents(), test.getContents());
    }

    @Test
    void conjunctionTest3() {
        WatchList testList = new WatchList("Contains the first episode of spanish TVShow episodes");

        testList.addWatchable(looneyTunes.getEpisode(1));

        WatchListFilter SP = new LanguageFilterStrategy(Language.SPANISH);
        WatchListFilter firstEp = new FirstEpisodeFilterStrategy();

        FilterBuilder firstAndSP = new FilterBuilder();

        firstAndSP.addFilter(SP);
        firstAndSP.addFilter(firstEp);

        WatchList test = lib.generateWatchList("ConjTest3", firstAndSP.buildConjunction() );

        assertEquals(testList.getContents(), test.getContents());
    }

    @Test
    void conjunctionTest4() {
        WatchList testList = new WatchList("Contains the first episode of shows by Disney");

        testList.addWatchable(theMandalorian.getEpisode(1));

        WatchListFilter disney = new PublishingStudioFilterStrategy(PublishingStudio.Disney);
        WatchListFilter firstEp = new FirstEpisodeFilterStrategy();

        FilterBuilder firstAndDis = new FilterBuilder();

        firstAndDis.addFilter(disney);
        firstAndDis.addFilter(firstEp);

        WatchList test = lib.generateWatchList("ConjTest4", firstAndDis.buildConjunction());

        assertEquals(testList.getContents(), test.getContents());

    }

    //     -------------------------------------------------------------------------------------------------

    @Test
    void disjunctionTest1() {
        WatchList testList = new WatchList("Contains the first episode of TVShows and spanish episodes");

        for(Watchable w :looneyTunes) {
            testList.addWatchable(w);
        }
        testList.addWatchable(theMandalorian.getEpisode(1));
        testList.addWatchable(theOffice.getEpisode(1));
        testList.addWatchable(theBigBangTheory.getEpisode(1));
        testList.addWatchable(superStore.getEpisode(1));
        testList.addWatchable(friends.getEpisode(1));

        WatchListFilter first = new FirstEpisodeFilterStrategy();
        WatchListFilter SP = new LanguageFilterStrategy(Language.SPANISH);

        FilterBuilder firstOrSP = new FilterBuilder();

        firstOrSP.addFilter(first);
        firstOrSP.addFilter(SP);

        WatchList test = lib.generateWatchList("DisjTest1", firstOrSP.buildDisjunction());

        assertEquals(testList.getTotalCount(), test.getTotalCount());

    }

    @Test
    void disjunctionTest2() {
        WatchList testList = new WatchList("Contains all english and spanish episodes");
        for(Watchable w : theOffice) {
            testList.addWatchable(w);
        }
        for(Watchable w : theBigBangTheory) {
            testList.addWatchable(w);
        }
        for(Watchable w : looneyTunes) {
            testList.addWatchable(w);
        }

        WatchListFilter EN = new LanguageFilterStrategy(Language.ENGLISH);
        WatchListFilter SP = new LanguageFilterStrategy(Language.SPANISH);

        FilterBuilder ENorSP = new FilterBuilder();

        ENorSP.addFilter(EN);
        ENorSP.addFilter(SP);

        WatchList test = lib.generateWatchList("DisjTest2", ENorSP.buildDisjunction());

        assertEquals(testList.getTotalCount(), test.getTotalCount());
    }

    @Test
    void disjunctionTest3() {
        WatchList testList = new WatchList("Contains all disney episodes and Klingon Episodes");

        for(Watchable w : theMandalorian) {
            testList.addWatchable(w);
        }

        WatchListFilter disney = new PublishingStudioFilterStrategy(PublishingStudio.Disney);
        WatchListFilter KLI = new LanguageFilterStrategy(Language.KLINGON);

        FilterBuilder disOrKLI = new FilterBuilder();

        disOrKLI.addFilter(disney);
        disOrKLI.addFilter(KLI);

        WatchList test = lib.generateWatchList("DisjTest3", disOrKLI.buildDisjunction());

        assertEquals(testList.getTotalCount(), test.getTotalCount());
    }

    @Test
    void disjunctionTest4() {
        WatchList testList = new WatchList("Contains all TVShow episodes by Columbia and episodes in Latin");

        WatchListFilter columbia = new PublishingStudioFilterStrategy(PublishingStudio.Columbia);
        WatchListFilter LAT = new LanguageFilterStrategy(Language.LATIN);

        FilterBuilder colOrLat = new FilterBuilder();

        colOrLat.addFilter(columbia);
        colOrLat.addFilter(LAT);

        WatchList test = lib.generateWatchList("DisjTest4", colOrLat.buildDisjunction());

        assertEquals(testList.getTotalCount(), test.getTotalCount());
    }

    @Test
    void combinationTest1() {
        WatchList testList = new WatchList("");

        testList.addWatchable(theOffice.getEpisode(1));
        testList.addWatchable(theBigBangTheory.getEpisode(1));

        WatchListFilter ENG = new LanguageFilterStrategy(Language.ENGLISH);
        WatchListFilter FR = new LanguageFilterStrategy(Language.FRENCH);
        WatchListFilter first = new FirstEpisodeFilterStrategy();
        WatchListFilter warner = new PublishingStudioFilterStrategy(PublishingStudio.WarnerBrothers);

        FilterBuilder ENGorFR = new FilterBuilder();
        ENGorFR.addFilter(ENG);
        ENGorFR.addFilter(FR);

        FilterBuilder warAndFirst = new FilterBuilder();
        warAndFirst.addFilter(warner);
        warAndFirst.addFilter(first);
        warAndFirst.addFilter(ENGorFR.buildDisjunction());

        WatchList test = lib.generateWatchList("WarnerBros First Episodes in English or French", warAndFirst.buildConjunction());

        assertEquals(test.getTotalCount(), testList.getTotalCount());
    }

    @Test
    void combinationTest2() {
        WatchList testList = new WatchList("");

        WatchListFilter columbia = new PublishingStudioFilterStrategy(PublishingStudio.Columbia);
        WatchListFilter disney = new PublishingStudioFilterStrategy(PublishingStudio.Disney);
        WatchListFilter ENG = new LanguageFilterStrategy(Language.ENGLISH);
        WatchListFilter FR = new LanguageFilterStrategy(Language.FRENCH);

        FilterBuilder ENGorFR = new FilterBuilder();
        ENGorFR.addFilter(ENG);
        ENGorFR.addFilter(FR);

        FilterBuilder colAndDis = new FilterBuilder();
        colAndDis.addFilter(columbia);
        colAndDis.addFilter(disney);

        colAndDis.addFilter(ENGorFR.buildDisjunction());

        WatchList test = lib.generateWatchList("Disney and Columbia in English or French", colAndDis.buildConjunction());

        assertEquals(testList.getContents(), test.getContents());

    }

    @Test
    void combinationTest3() {
        WatchList testList = new WatchList("");

        testList.addWatchable(theOffice.getEpisode(1));

        WatchListFilter first = new FirstEpisodeFilterStrategy();
        WatchListFilter ENG = new LanguageFilterStrategy(Language.ENGLISH);
        WatchListFilter disney = new PublishingStudioFilterStrategy(PublishingStudio.Disney);
        WatchListFilter nbc = new PublishingStudioFilterStrategy(PublishingStudio.NBC);

        FilterBuilder disOrNBC = new FilterBuilder();
        disOrNBC.addFilter(disney);
        disOrNBC.addFilter(nbc);

        FilterBuilder firstAndEng = new FilterBuilder();
        firstAndEng.addFilter(first);
        firstAndEng.addFilter(ENG);
        firstAndEng.addFilter(disOrNBC.buildDisjunction());

        WatchList test = lib.generateWatchList("First and English from Disney or NBC", firstAndEng.buildConjunction());

        assertEquals(test.getTotalCount(), testList.getTotalCount());

    }

    @Test
    void combinationTest4() {
        WatchList testList = new WatchList("");

        WatchListFilter KLI = new LanguageFilterStrategy(Language.KLINGON);
        WatchListFilter LAT = new LanguageFilterStrategy(Language.LATIN);
        WatchListFilter nbc = new PublishingStudioFilterStrategy(PublishingStudio.NBC);
        WatchListFilter dis = new PublishingStudioFilterStrategy(PublishingStudio.Disney);

        FilterBuilder NBCorDis = new FilterBuilder();
        NBCorDis.addFilter(nbc);
        NBCorDis.addFilter(dis);

        FilterBuilder KLIandLAT = new FilterBuilder();
        KLIandLAT.addFilter(KLI);
        KLIandLAT.addFilter(LAT);
        KLIandLAT.addFilter(NBCorDis.buildDisjunction());

        WatchList test = lib.generateWatchList("Klingon and Latin Episodes from NBC or Disney", KLIandLAT.buildConjunction());

        assertEquals(test.getContents(), testList.getContents());

    }

}
