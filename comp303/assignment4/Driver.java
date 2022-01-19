package comp303.assignment4;

import java.io.File;
import java.util.HashSet;

public class Driver {
    public static void main(String [] args) {
        Library lib1 = new Library();

        File f1 = new File("Desktop/TVShows/TheOfficeS1E1.mp4");
        File f2 = new File("Desktop/TVShows/TheOfficeS1E2.mp4");
        File f3 = new File("Desktop/TVShows/TheOfficeS1E3.mp4");
        File f4 = new File("Desktop/TVShows/TheOfficeS1E4.mp4");
        File f5 = new File("Desktop/TVShows/TheOfficeS1E5.mp4");
        File f6 = new File("Desktop/TVShows/TheOfficeS1E6.mp4");

        TVShow theOffice = new TVShow("The Office", Language.ENGLISH, PublishingStudio.NBC);
        TVShow theMandalorian = new TVShow("The Mandalorian", Language.FRENCH, PublishingStudio.Disney);
        TVShow superStore = new TVShow("Superstore", Language.FRENCH, PublishingStudio.NBC);
        TVShow theBigBangTheory = new TVShow("The Big Bang Theory", Language.ENGLISH, PublishingStudio.WarnerBrothers);
        TVShow friends = new TVShow("Friends", Language.FRENCH, PublishingStudio.WarnerBrothers);
        TVShow looneyTunes = new TVShow("The Looney Tunes Show", Language.SPANISH, PublishingStudio.WarnerBrothers);

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

        lib1.addTVShow(theOffice); //NBC ENGLISH
        lib1.addTVShow(theMandalorian); //DISNEY FRENCH
        lib1.addTVShow(superStore); //NBC FRENCH
        lib1.addTVShow(theBigBangTheory); //WARNERBROS ENGLISH
        lib1.addTVShow(friends); //WARNERBROS FRENCH
        lib1.addTVShow(looneyTunes); //WARNERBROS SPANISH

        WatchListFilter ENG = new LanguageFilterStrategy(Language.ENGLISH);
        WatchListFilter FR = new LanguageFilterStrategy(Language.FRENCH);
        WatchListFilter nbc = new PublishingStudioFilterStrategy(PublishingStudio.NBC);
        WatchListFilter first = new FirstEpisodeFilterStrategy();
        WatchListFilter warner = new PublishingStudioFilterStrategy(PublishingStudio.WarnerBrothers);

        // BUILDING A WATCHLIST OF WARNERBROS FIRST EPISODES THAT ARE IN ENGLISH OR FRENCH

        FilterBuilder ENGorFR = new FilterBuilder();
        ENGorFR.addFilter(ENG);
        ENGorFR.addFilter(FR);

        FilterBuilder warAndFirst = new FilterBuilder();
        warAndFirst.addFilter(warner);
        warAndFirst.addFilter(first);
        warAndFirst.addFilter(ENGorFR.buildDisjunction());

        WatchList DriverTest = lib1.generateWatchList("\nWarnerBros First Episodes in English or French", warAndFirst.buildConjunction());

        System.out.println(DriverTest.getName() + ": ");
        System.out.println(DriverTest.getContents()+ "\n");

        // ARBITRARY PUBLISHING STUDIO

        WatchList NBC = lib1.generateWatchList("Publishing Studio: NBC", nbc);

        System.out.println(NBC.getName() + ": ");
        System.out.println(NBC.getContents()+ "\n");

        // FIRST EPISODE ONLY

        WatchList firsts = lib1.generateWatchList("First Episodes Only", first);

        System.out.println(firsts.getName() + ": ");
        System.out.println(firsts.getContents()+ "\n");

        // EQUALS METHOD FOR THE CLONE

//        friends.setPrototypeEpisode(friends.getEpisode(6));
//        System.out.println(friends.getEpisode(6).equals(friends.getEpisode(6).clone()));
//        System.out.println((friends.getEpisode(6) == (friends.getEpisode(6).clone())));


    }
}
