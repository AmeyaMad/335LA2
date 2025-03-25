package modelTest;

import org.junit.Test;
import model.*;

import static org.junit.Assert.*;

public class LibraryModelTest {
    MusicStore musicStore = new MusicStore("albums");
    LibraryModel libraryModel = new LibraryModel();

    @Test
    public void testAddandGetSongToLibrary() {
        libraryModel.addSong("Daylight", "Coldplay");
        assertEquals("Song - Title: Daylight, Artist: Coldplay, Album: A Rush of Blood to the Head\n",
                libraryModel.getSongsByTitle("Daylight"));
    }

    @Test
    public void testBadInputGetSongFromLibrary() {
        libraryModel.addSong("Daylight", "Coldplay");
        assertEquals("This Song is not in the songs list\n",
                libraryModel.getSongsByTitle("BLAH"));
    }

    @Test
    public void testAddSongToLibraryWhenNotInStore() {
        assertEquals("This song is not in the Music Store\n", libraryModel.addSong("Word", "Random"));
    }

    @Test
    // this will be for the 2 lullaby
    public void testAddingTwoSongsSameName() {
        libraryModel.addSong("Lullaby", "Leonard Cohen");
        libraryModel.addSong("Lullaby", "OneRepublic");

        String ex = """
                Song - Title: Lullaby, Artist: Leonard Cohen, Album: Old Ideas
                Song - Title: Lullaby, Artist: OneRepublic, Album: Waking Up
                """;
        assertEquals(ex, libraryModel.getSongsByTitle("Lullaby"));
    }

    @Test
    // this will be for adding 2 of the same song
    public void testAddingSameSong2Times() {
        libraryModel.addSong("Lullaby", "Leonard Cohen");
        assertEquals("This song is already in the list\n", libraryModel.addSong("Lullaby", "Leonard Cohen"));
    }

    @Test
    public void testAddingSongRightNameWrongArtist() {
        assertEquals("This song is not in the Music Store\n", libraryModel.addSong("After Party", "Adele"));
    }

    @Test
    public void testGetSongsByArtist() {
        libraryModel.addSong("I'll Be Waiting", "Adele");
        libraryModel.addSong("I Found a Boy", "Adele");
        libraryModel.addSong("First Love", "Adele");

        String ex = """
                Song - Title: I'll Be Waiting, Artist: Adele, Album: 21
                Song - Title: I Found a Boy, Artist: Adele, Album: 21
                Song - Title: First Love, Artist: Adele, Album: 19
                """;
        assertEquals(ex, libraryModel.getSongsByArtist("Adele"));
    }

    @Test
    public void testGetSongsByArtistEmpty() {
        libraryModel.addSong("First Love", "Adele");
        assertEquals("There are no songs by this artist\n", libraryModel.getSongsByArtist("Kanye"));
    }

    @Test
    public void testGetAlbumsByName() {
        libraryModel.addAlbumToLibrary("Begin Again");
        String ex = """
                Album: Begin Again, Artist: Norah Jones, Genre: Pop
                My Heart Is Full
                Begin Again
                It Was You
                A Song with No Name
                Uh Oh
                Wintertime
                Just a Little Bit
                """;
        assertEquals(ex, libraryModel.getAlbumsByTitle("Begin Again"));
    }

    @Test
    public void testGetAlbumsByNameEmpty() {
        assertEquals("There are no albums of this name\n", libraryModel.getAlbumsByTitle("na"));
    }

    @Test
    public void testGetAlbumsByArtist() {
        libraryModel.addAlbumToLibrary("19");
        libraryModel.addAlbumToLibrary("21");
        String ex = """
                Album: 19, Artist: Adele, Genre: Pop
                Daydreamer
                Best for Last
                Chasing Pavements
                Cold Shoulder
                Crazy for You
                Melt My Heart to Stone
                First Love
                Right as Rain
                Make You Feel My Love
                My Same
                Tired
                Hometown Glory

                Album: 21, Artist: Adele, Genre: Pop
                Rolling in the Deep
                Rumour Has It
                Turning Tables
                Don't You Remember
                Set Fire to the Rain
                He Won't Go
                Take It All
                I'll Be Waiting
                One and Only
                Lovesong
                Someone Like You
                I Found a Boy

                """;
        assertEquals(ex, libraryModel.getAlbumsByArtist("Adele"));
    }

    @Test
    public void testGetAlbumsByArtistEmpty() {
        assertEquals("There are no albums by this artist\n", libraryModel.getAlbumsByArtist("na"));
    }

    @Test
    public void testAddingAlbumNotInLib() {
        assertFalse(libraryModel.addAlbumToLibrary("Graduation"));
    }

    @Test
    public void testAddingSameAlbum2times() {
        libraryModel.addAlbumToLibrary("Fight for Your Mind");
        assertFalse(libraryModel.addAlbumToLibrary("Fight for Your Mind"));
    }

    @Test
    public void testAddingSongToFavorite() {
        libraryModel.addSongToFavorites("You Ain't Alone", "Alabama Shakes");
        String exFav = """
                === Favorites ===
                Song - Title: You Ain't Alone, Artist: Alabama Shakes, Album: Boys & Girls
                """;

        String exRate = """
                === Songs of Rating FIVE ===
                Song - Title: You Ain't Alone, Artist: Alabama Shakes, Album: Boys & Girls
                """;
        assertEquals(exFav, libraryModel.getFavoritesToString());
        assertEquals(exRate, libraryModel.getSongsByRatingString(Rating.FIVE));
    }

    @Test
    public void testAddingSongToFavByRating5() {
        libraryModel.rateSong("The Cave", "Mumford & Sons", Rating.FIVE);
        libraryModel.addSongToFavorites("You Ain't Alone", "Alabama Shakes");
        String ex = """
                === Songs of Rating FIVE ===
                Song - Title: The Cave, Artist: Mumford & Sons, Album: Sigh No More
                Song - Title: You Ain't Alone, Artist: Alabama Shakes, Album: Boys & Girls
                """;
        assertEquals(ex, libraryModel.getSongsByRatingString(Rating.FIVE));

    }

    @Test
    public void testNoSongsInFavoriteOrOfRating() {
        assertEquals("There are no songs of this rating\n", libraryModel.getSongsByRatingString(Rating.ONE));
        assertEquals("There are no favorites\n", libraryModel.getFavoritesToString());
    }

    @Test
    public void testAddingNonexistentSongToFavoriteAndRating() {
        assertEquals("There is no song with this name and by this Artist\n",
                libraryModel.addSongToFavorites("Flashing Lights", "Kanye West"));
        assertEquals("There is no song that has this name by this artist\n",
                libraryModel.rateSong("All of the Lights", "Kanye West", Rating.FIVE));
    }

    @Test
    public void testAddingSameSongToFavoriteAndRating() {
        libraryModel.addSongToFavorites("The Cave", "Mumford & Sons");
        assertEquals("This song is already in the favorites list\n",
                libraryModel.addSongToFavorites("The Cave", "Mumford & Sons"));
        libraryModel.rateSong("Uh Oh", "Norah Jones", Rating.THREE);
        assertEquals("Successfully rated song\n", libraryModel.rateSong("Uh Oh", "Norah Jones", Rating.THREE));
    }

    @Test
    public void testListAllSongsString() {
        libraryModel.addAlbumToLibrary("Begin Again");
        libraryModel.addSong("The Cave", "Mumford & Sons");
        String ex = """
                === Songs List ===
                Title: It Was You, Artist: Norah Jones, Album: Begin Again
                Title: Wintertime, Artist: Norah Jones, Album: Begin Again
                Title: A Song with No Name, Artist: Norah Jones, Album: Begin Again
                Title: Just a Little Bit, Artist: Norah Jones, Album: Begin Again
                Title: Begin Again, Artist: Norah Jones, Album: Begin Again
                Title: The Cave, Artist: Mumford & Sons, Album: Sigh No More
                Title: Uh Oh, Artist: Norah Jones, Album: Begin Again
                Title: My Heart Is Full, Artist: Norah Jones, Album: Begin Again
                """;
        assertEquals(ex, libraryModel.listAllSongs());
    }

    @Test
    public void testListAllAlbumsString() {
        libraryModel.addAlbumToLibrary("21");
        libraryModel.addAlbumToLibrary("19");
        libraryModel.addAlbumToLibrary("Don't Mess With the Dragon");
        String ex = """
                === Albums List ===
                Album: 19, Artist: Adele
                Album: Don't Mess With the Dragon, Artist: Ozomatli
                Album: 21, Artist: Adele
                """;
        assertEquals(ex, libraryModel.listAllAlbums());
    }

    @Test
    public void addingSongToPlaylist() {
        libraryModel.createPlaylist("My Playlist");
        libraryModel.addSongToPlaylist("Hundido En Un Rincon", "Mana", "My Playlist");
        // System.out.print(libraryModel.getPlaylistByNameString("My Playlist"));
        String ex = """
                === Playlist: My Playlist ===
                Song - Title: Hundido En Un Rincon, Artist: Mana, Album: Cuando Los Angeles Lloran
                """;
        assertEquals(ex, libraryModel.playlistByToNameString("My Playlist"));
    }

    @Test
    public void testPlaylistDNE() {
        libraryModel.createPlaylist("My Playlist1");
        // System.out.print(libraryModel.getPlaylistByNameString("My"));
        String ex = "There are no playlists by this title\n";
        assertEquals(ex, libraryModel.playlistByToNameString("My"));
    }

    @Test
    public void testMultiplePlaylists() {
        libraryModel.createPlaylist("My Playlist1");
        libraryModel.createPlaylist("My Playlist2");
        libraryModel.createPlaylist("My Playlist3");
        // System.out.print(libraryModel.listAllPlaylistsString());
        String ex = """
                === Playlists List ===
                Playlist: My Playlist1
                Playlist: My Playlist3
                Playlist: My Playlist2
                """;
        assertEquals(ex, libraryModel.listAllPlaylists());
    }

    @Test
    public void testAddingSongToPlaylistThatDNE() {
        String ex = "Playlist Not Found";
        assertEquals(ex, libraryModel.addSongToPlaylist("One and Only", "Adele", "DNE PLAYLIST"));
    }

    @Test
    public void testAddingDNESongToPlaylist() {
        libraryModel.createPlaylist("My Playlist1");
        String ex = "Song Not Found";
        assertEquals(ex, libraryModel.addSongToPlaylist("Only One", "Kanye", "My Playlist1"));
    }

    @Test
    public void testRemovingSongFromPlaylist() {
        libraryModel.createPlaylist("My Playlist1");
        libraryModel.addSongToPlaylist("One and Only", "Adele", "My Playlist1");
        libraryModel.addSongToPlaylist("I Found a Boy", "Adele", "My Playlist1");

        libraryModel.removeSongFromPlaylist("I Found a Boy", "Adele", "My Playlist1");
        // System.out.print(libraryModel.getPlaylistByNameString("My Playlist1"));
        String ex = """
                === Playlist: My Playlist1 ===
                Song - Title: One and Only, Artist: Adele, Album: 21
                """;
        assertEquals(ex, libraryModel.playlistByToNameString("My Playlist1"));

    }

    @Test
    public void testListAllArtistsString() {
        libraryModel.addAlbumToLibrary("21");
        libraryModel.addAlbumToLibrary("19");
        libraryModel.addAlbumToLibrary("Don't Mess With the Dragon");

        String expected = """
                === Artists List ===
                Ozomatli
                Adele
                """;

        assertEquals(expected, libraryModel.listAllArtists());
    }

    // @Test
    // public void testAddingSongTop10() {
    // libraryModel. //TODO
    // }

    @Test
    public void testPlayingSong() {
        libraryModel.addAlbumToLibrary("21");
        String ex = "===== Playing Song: I'll Be Waiting by Adele ===== \n" +
                "                   <-      ||         ->";
        assertEquals(ex, libraryModel.playSong("I'll Be Waiting", "Adele"));
    }

    @Test
    public void testFrequentSongs() {
        libraryModel.addAlbumToLibrary("21");
        libraryModel.addAlbumToLibrary("19");

        libraryModel.playSong("I'll Be Waiting", "Adele");
        libraryModel.playSong("I Found a Boy", "Adele");
        libraryModel.playSong("I Found a Boy", "Adele");
        libraryModel.playSong("First Love", "Adele");
        libraryModel.playSong("Rumour Has It", "Adele");
        libraryModel.playSong("I Found a Boy", "Adele");
        libraryModel.playSong("Rumour Has It", "Adele");
        libraryModel.playSong("I'll Be Waiting", "Adele");
        libraryModel.playSong("I Found a Boy", "Adele");
        libraryModel.playSong("I'll Be Waiting", "Adele");
        libraryModel.playSong("I Found a Boy", "Adele");
        libraryModel.playSong("First Love", "Adele");

        String ex = """
                ==== Most Frequent Songs ===
                Title: I Found a Boy, Artist: Adele, Album: 21
                Title: I'll Be Waiting, Artist: Adele, Album: 21
                Title: First Love, Artist: Adele, Album: 19
                Title: Rumour Has It, Artist: Adele, Album: 21
                """;
        assertEquals(ex, libraryModel.mostFrequentToString());
    }

    @Test
    public void testFrequentPlaylistWhenPlayingMoreThan10Songs() {
        libraryModel.addAlbumToLibrary("21");
        libraryModel.addAlbumToLibrary("19");
        libraryModel.addAlbumToLibrary("Don't Mess With the Dragon");

        libraryModel.playSong("City Of Angels", "Ozomatli");
        libraryModel.playSong("After Party", "Ozomatli");
        libraryModel.playSong("Don't Mess With The Dragons", "Ozomatli");
        libraryModel.playSong("La Gallina", "Ozomatli");
        libraryModel.playSong("Magnolia Soul", "Ozomatli");
        libraryModel.playSong("Here We Gos", "Ozomatli");
        libraryModel.playSong("La Temperatura", "Ozomatli");
        libraryModel.playSong("Violeta", "Ozomatli");
        libraryModel.playSong("When I Close My Eyes", "Ozomatli");

        libraryModel.playSong("Someone Like You", "Adele");
        libraryModel.playSong("Lovesong", "Adele");
        libraryModel.playSong("Turning Tables", "Adele");
        libraryModel.playSong("Turning Tables", "Adele");
        libraryModel.playSong("Don't You Remember", "Adele");
        libraryModel.playSong("I Found a Boy", "Adele");
        libraryModel.playSong("Someone Like You", "Adele");
        libraryModel.playSong("Someone Like You", "Adele");
        libraryModel.playSong("Someone Like You", "Adele");
        libraryModel.playSong("Right as Rain", "Adele");
        libraryModel.playSong("First Love", "Adele");

        String ex = """
                ==== Most Frequent Songs ===
                Title: Someone Like You, Artist: Adele, Album: 21
                Title: Turning Tables, Artist: Adele, Album: 21
                Title: First Love, Artist: Adele, Album: 19
                Title: Right as Rain, Artist: Adele, Album: 19
                Title: I Found a Boy, Artist: Adele, Album: 21
                Title: Don't You Remember, Artist: Adele, Album: 21
                Title: Lovesong, Artist: Adele, Album: 21
                Title: When I Close My Eyes, Artist: Ozomatli, Album: Don't Mess With the Dragon
                Title: Violeta, Artist: Ozomatli, Album: Don't Mess With the Dragon
                Title: La Temperatura, Artist: Ozomatli, Album: Don't Mess With the Dragon
                """;
        assertEquals(ex, libraryModel.mostFrequentToString());

    }

    @Test
    public void testMostRecentPlaylistWhenPlayingMoreThan10Songs() {
        libraryModel.addAlbumToLibrary("21");
        libraryModel.addAlbumToLibrary("19");
        libraryModel.addAlbumToLibrary("Don't Mess With the Dragon");

        libraryModel.playSong("City Of Angels", "Ozomatli");
        libraryModel.playSong("After Party", "Ozomatli");
        libraryModel.playSong("Don't Mess With The Dragon", "Ozomatli");
        libraryModel.playSong("La Gallina", "Ozomatli");
        libraryModel.playSong("Magnolia Soul", "Ozomatli");
        libraryModel.playSong("Here We Go", "Ozomatli");
        libraryModel.playSong("La Temperatura", "Ozomatli");
        libraryModel.playSong("Violeta", "Ozomatli");
        libraryModel.playSong("When I Close My Eyes", "Ozomatli");

        libraryModel.playSong("Someone Like You", "Adele");
        libraryModel.playSong("Set Fire to the Rain", "Adele");
        libraryModel.playSong("Turning Tables", "Adele");

        // System.out.print(libraryModel.mostRecentToString());
        String ex = """
                === Most Recent Songs ===
                Title: Turning Tables, Artist: Adele, Album: 21
                Title: Set Fire to the Rain, Artist: Adele, Album: 21
                Title: Someone Like You, Artist: Adele, Album: 21
                Title: When I Close My Eyes, Artist: Ozomatli, Album: Don't Mess With the Dragon
                Title: Violeta, Artist: Ozomatli, Album: Don't Mess With the Dragon
                Title: La Temperatura, Artist: Ozomatli, Album: Don't Mess With the Dragon
                Title: Here We Go, Artist: Ozomatli, Album: Don't Mess With the Dragon
                Title: Magnolia Soul, Artist: Ozomatli, Album: Don't Mess With the Dragon
                Title: La Gallina, Artist: Ozomatli, Album: Don't Mess With the Dragon
                Title: Don't Mess With The Dragon, Artist: Ozomatli, Album: Don't Mess With the Dragon
                """;
        assertEquals(ex, libraryModel.mostRecentToString());

    }

    @Test
    public void testRemovingSongsFromLibrary() {
        libraryModel.addAlbumToLibrary("19");
        libraryModel.addAlbumToLibrary("Boys & Girls");

        libraryModel.removeSongFromLibrary("Cold Shoulder", "Adele");

        String ex = """
                === Songs List ===
                Title: Heavy Chevy (Bonus Track), Artist: Alabama Shakes, Album: Boys & Girls
                Title: Goin' to the Party, Artist: Alabama Shakes, Album: Boys & Girls
                Title: Melt My Heart to Stone, Artist: Adele, Album: 19
                Title: Best for Last, Artist: Adele, Album: 19
                Title: Rise to the Sun, Artist: Alabama Shakes, Album: Boys & Girls
                Title: Right as Rain, Artist: Adele, Album: 19
                Title: Make You Feel My Love, Artist: Adele, Album: 19
                Title: Daydreamer, Artist: Adele, Album: 19
                Title: Hang Loose, Artist: Alabama Shakes, Album: Boys & Girls
                Title: Heartbreaker, Artist: Alabama Shakes, Album: Boys & Girls
                Title: On Your Way, Artist: Alabama Shakes, Album: Boys & Girls
                Title: Hold On, Artist: Alabama Shakes, Album: Boys & Girls
                Title: Be Mine, Artist: Alabama Shakes, Album: Boys & Girls
                Title: Tired, Artist: Adele, Album: 19
                Title: You Ain't Alone, Artist: Alabama Shakes, Album: Boys & Girls
                Title: First Love, Artist: Adele, Album: 19
                Title: I Ain't the Same, Artist: Alabama Shakes, Album: Boys & Girls
                Title: Hometown Glory, Artist: Adele, Album: 19
                Title: Boys & Girls, Artist: Alabama Shakes, Album: Boys & Girls
                Title: I Found You, Artist: Alabama Shakes, Album: Boys & Girls
                Title: Chasing Pavements, Artist: Adele, Album: 19
                Title: My Same, Artist: Adele, Album: 19
                Title: Crazy for You, Artist: Adele, Album: 19
                """;

        assertEquals(ex, libraryModel.listAllSongs());

    }

    @Test
    public void testListingAllSongsByComparison() {
        libraryModel.addAlbumToLibrary("A Rush of Blood to the Head");
        libraryModel.addAlbumToLibrary("Coat of Many Colors");
        libraryModel.addAlbumToLibrary("Don't Mess With the Dragon");

        libraryModel.rateSong("The Scientist", "Coldplay", Rating.FIVE);
        libraryModel.rateSong("Coat of Many Colors", "Dolly Parton", Rating.FOUR);
        libraryModel.rateSong("City Of Angels", "Ozomatli", Rating.THREE);
        libraryModel.rateSong("Here We Go", "Ozomatli", Rating.FIVE);
        libraryModel.rateSong("Amsterdam", "Coldplay", Rating.TWO);
        libraryModel.rateSong("If I Lose My Mind", "Dolly Parton", Rating.FIVE);

        // System.out.print(libraryModel.allSongsByComparison(3));

        String ex1 = """
                Title: A Better Place to Live, Artist: Dolly Parton, Album: Coat of Many Colors
                Title: A Rush of Blood to the Head, Artist: Coldplay, Album: A Rush of Blood to the Head
                Title: A Whisper, Artist: Coldplay, Album: A Rush of Blood to the Head
                Title: After Party, Artist: Ozomatli, Album: Don't Mess With the Dragon
                Title: Amsterdam, Artist: Coldplay, Album: A Rush of Blood to the Head
                Title: City Of Angels, Artist: Ozomatli, Album: Don't Mess With the Dragon
                Title: Clocks, Artist: Coldplay, Album: A Rush of Blood to the Head
                Title: Coat of Many Colors, Artist: Dolly Parton, Album: Coat of Many Colors
                Title: Creo, Artist: Ozomatli, Album: Don't Mess With the Dragon
                Title: Daylight, Artist: Coldplay, Album: A Rush of Blood to the Head
                Title: Don't Mess With The Dragon, Artist: Ozomatli, Album: Don't Mess With the Dragon
                Title: Early Morning Breeze, Artist: Dolly Parton, Album: Coat of Many Colors
                Title: God Put a Smile Upon Your Face, Artist: Coldplay, Album: A Rush of Blood to the Head
                Title: Green Eyes, Artist: Coldplay, Album: A Rush of Blood to the Head
                Title: Here I Am, Artist: Dolly Parton, Album: Coat of Many Colors
                Title: Here We Go, Artist: Ozomatli, Album: Don't Mess With the Dragon
                Title: If I Lose My Mind, Artist: Dolly Parton, Album: Coat of Many Colors
                Title: In My Place, Artist: Coldplay, Album: A Rush of Blood to the Head
                Title: La Gallina, Artist: Ozomatli, Album: Don't Mess With the Dragon
                Title: La Temperatura, Artist: Ozomatli, Album: Don't Mess With the Dragon
                Title: Magnolia Soul, Artist: Ozomatli, Album: Don't Mess With the Dragon
                Title: My Blue Tears, Artist: Dolly Parton, Album: Coat of Many Colors
                Title: Politik, Artist: Coldplay, Album: A Rush of Blood to the Head
                Title: She Never Met a Man (She Didn't Like), Artist: Dolly Parton, Album: Coat of Many Colors
                Title: The Mystery of the Mystery, Artist: Dolly Parton, Album: Coat of Many Colors
                Title: The Scientist, Artist: Coldplay, Album: A Rush of Blood to the Head
                Title: The Way I See You, Artist: Dolly Parton, Album: Coat of Many Colors
                Title: Traveling Man, Artist: Dolly Parton, Album: Coat of Many Colors
                Title: Violeta, Artist: Ozomatli, Album: Don't Mess With the Dragon
                Title: Warning Sign, Artist: Coldplay, Album: A Rush of Blood to the Head
                Title: When I Close My Eyes, Artist: Ozomatli, Album: Don't Mess With the Dragon
                """;

        String ex2 = """
                Title: A Rush of Blood to the Head, Artist: Coldplay, Album: A Rush of Blood to the Head
                Title: A Whisper, Artist: Coldplay, Album: A Rush of Blood to the Head
                Title: Amsterdam, Artist: Coldplay, Album: A Rush of Blood to the Head
                Title: Clocks, Artist: Coldplay, Album: A Rush of Blood to the Head
                Title: Daylight, Artist: Coldplay, Album: A Rush of Blood to the Head
                Title: God Put a Smile Upon Your Face, Artist: Coldplay, Album: A Rush of Blood to the Head
                Title: Green Eyes, Artist: Coldplay, Album: A Rush of Blood to the Head
                Title: In My Place, Artist: Coldplay, Album: A Rush of Blood to the Head
                Title: Politik, Artist: Coldplay, Album: A Rush of Blood to the Head
                Title: The Scientist, Artist: Coldplay, Album: A Rush of Blood to the Head
                Title: Warning Sign, Artist: Coldplay, Album: A Rush of Blood to the Head
                Title: A Better Place to Live, Artist: Dolly Parton, Album: Coat of Many Colors
                Title: Coat of Many Colors, Artist: Dolly Parton, Album: Coat of Many Colors
                Title: Early Morning Breeze, Artist: Dolly Parton, Album: Coat of Many Colors
                Title: Here I Am, Artist: Dolly Parton, Album: Coat of Many Colors
                Title: If I Lose My Mind, Artist: Dolly Parton, Album: Coat of Many Colors
                Title: My Blue Tears, Artist: Dolly Parton, Album: Coat of Many Colors
                Title: She Never Met a Man (She Didn't Like), Artist: Dolly Parton, Album: Coat of Many Colors
                Title: The Mystery of the Mystery, Artist: Dolly Parton, Album: Coat of Many Colors
                Title: The Way I See You, Artist: Dolly Parton, Album: Coat of Many Colors
                Title: Traveling Man, Artist: Dolly Parton, Album: Coat of Many Colors
                Title: After Party, Artist: Ozomatli, Album: Don't Mess With the Dragon
                Title: City Of Angels, Artist: Ozomatli, Album: Don't Mess With the Dragon
                Title: Creo, Artist: Ozomatli, Album: Don't Mess With the Dragon
                Title: Don't Mess With The Dragon, Artist: Ozomatli, Album: Don't Mess With the Dragon
                Title: Here We Go, Artist: Ozomatli, Album: Don't Mess With the Dragon
                Title: La Gallina, Artist: Ozomatli, Album: Don't Mess With the Dragon
                Title: La Temperatura, Artist: Ozomatli, Album: Don't Mess With the Dragon
                Title: Magnolia Soul, Artist: Ozomatli, Album: Don't Mess With the Dragon
                Title: Violeta, Artist: Ozomatli, Album: Don't Mess With the Dragon
                Title: When I Close My Eyes, Artist: Ozomatli, Album: Don't Mess With the Dragon
                """;

        String ex3 = """
                Title: A Better Place to Live, Artist: Dolly Parton, Album: Coat of Many Colors
                Title: A Rush of Blood to the Head, Artist: Coldplay, Album: A Rush of Blood to the Head
                Title: A Whisper, Artist: Coldplay, Album: A Rush of Blood to the Head
                Title: After Party, Artist: Ozomatli, Album: Don't Mess With the Dragon
                Title: Clocks, Artist: Coldplay, Album: A Rush of Blood to the Head
                Title: Creo, Artist: Ozomatli, Album: Don't Mess With the Dragon
                Title: Daylight, Artist: Coldplay, Album: A Rush of Blood to the Head
                Title: Don't Mess With The Dragon, Artist: Ozomatli, Album: Don't Mess With the Dragon
                Title: Early Morning Breeze, Artist: Dolly Parton, Album: Coat of Many Colors
                Title: God Put a Smile Upon Your Face, Artist: Coldplay, Album: A Rush of Blood to the Head
                Title: Green Eyes, Artist: Coldplay, Album: A Rush of Blood to the Head
                Title: Here I Am, Artist: Dolly Parton, Album: Coat of Many Colors
                Title: In My Place, Artist: Coldplay, Album: A Rush of Blood to the Head
                Title: La Gallina, Artist: Ozomatli, Album: Don't Mess With the Dragon
                Title: La Temperatura, Artist: Ozomatli, Album: Don't Mess With the Dragon
                Title: Magnolia Soul, Artist: Ozomatli, Album: Don't Mess With the Dragon
                Title: My Blue Tears, Artist: Dolly Parton, Album: Coat of Many Colors
                Title: Politik, Artist: Coldplay, Album: A Rush of Blood to the Head
                Title: She Never Met a Man (She Didn't Like), Artist: Dolly Parton, Album: Coat of Many Colors
                Title: The Mystery of the Mystery, Artist: Dolly Parton, Album: Coat of Many Colors
                Title: The Way I See You, Artist: Dolly Parton, Album: Coat of Many Colors
                Title: Traveling Man, Artist: Dolly Parton, Album: Coat of Many Colors
                Title: Violeta, Artist: Ozomatli, Album: Don't Mess With the Dragon
                Title: Warning Sign, Artist: Coldplay, Album: A Rush of Blood to the Head
                Title: When I Close My Eyes, Artist: Ozomatli, Album: Don't Mess With the Dragon
                Title: Amsterdam, Artist: Coldplay, Album: A Rush of Blood to the Head
                Title: City Of Angels, Artist: Ozomatli, Album: Don't Mess With the Dragon
                Title: Coat of Many Colors, Artist: Dolly Parton, Album: Coat of Many Colors
                Title: Here We Go, Artist: Ozomatli, Album: Don't Mess With the Dragon
                Title: If I Lose My Mind, Artist: Dolly Parton, Album: Coat of Many Colors
                Title: The Scientist, Artist: Coldplay, Album: A Rush of Blood to the Head
                """;

        assertEquals(ex1, libraryModel.allSongsByComparison(1));
        assertEquals(ex2, libraryModel.allSongsByComparison(2));
        assertEquals(ex3, libraryModel.allSongsByComparison(3));

        assertEquals("Invalid option. Use 1 for title, 2 for artist, 3 for rating.\n", libraryModel.allSongsByComparison(4));
    }

    @Test
    public void testEmptyFreqAndRecentPlaylists() {
        String ex1 = "==== Most Frequent Songs ===\n";
        String ex2 = "=== Most Recent Songs ===\n";
        assertEquals(ex1, libraryModel.mostFrequentToString());
        assertEquals(ex2, libraryModel.mostRecentToString());
//        System.out.print(libraryModel.mostFrequentToString());
//        System.out.print(libraryModel.mostRecentToString());
    }


    @Test
    public void testRemovingAlbumFromLibrary() {
        libraryModel.addAlbumToLibrary("19");
        libraryModel.addAlbumToLibrary("Mission Bell");

        // System.out.print(libraryModel.listAllSongs());
        // System.out.print(libraryModel.listAllAlbums());
        // System.out.print(libraryModel.listAllArtists());

        libraryModel.removeAlbumFromLibrary("19");

        // System.out.print(libraryModel.listAllSongs());
        // System.out.print(libraryModel.listAllAlbums());
        // System.out.print(libraryModel.listAllArtists());

        String ex1 = """
                === Songs List ===
                Title: Flower, Artist: Amos Lee, Album: Mission Bell
                Title: El Camino, Artist: Amos Lee, Album: Mission Bell
                Title: Stay With Me, Artist: Amos Lee, Album: Mission Bell
                Title: Out of the Cold, Artist: Amos Lee, Album: Mission Bell
                Title: Windows Are Rolled Down, Artist: Amos Lee, Album: Mission Bell
                Title: Hello Again, Artist: Amos Lee, Album: Mission Bell
                Title: Jesus, Artist: Amos Lee, Album: Mission Bell
                Title: Clear Blue Eyes (feat. Lucinda Williams), Artist: Amos Lee, Album: Mission Bell
                Title: Behind Me Now, Artist: Amos Lee, Album: Mission Bell
                Title: Cup of Sorrow, Artist: Amos Lee, Album: Mission Bell
                """;

        String ex2 = """
                === Albums List ===
                Album: Mission Bell, Artist: Amos Lee
                """;
        String ex3 = """
                === Artists List ===
                Amos Lee
                """;

        assertEquals(ex1, libraryModel.listAllSongs());
    }

    // Not sure how to unit test these since it has random elements
    @Test
    public void testShufflingAllSongs() {
        libraryModel.addAlbumToLibrary("19");
        libraryModel.addAlbumToLibrary("Coat of Many Colors");

        //libraryModel.shuffleLibraryToString();
    }

    @Test
    public void testShufflingPlaylist() {
        libraryModel.createPlaylist("testPlaylist");
        libraryModel.addSongToPlaylist("She Never Met a Man (She Didn't Like)", "Dolly Parton", "testPlaylist");
        libraryModel.addSongToPlaylist("A Better Place to Live", "Dolly Parton", "testPlaylist");
        libraryModel.addSongToPlaylist("If I Lose My Mind", "Dolly Parton", "testPlaylist");
        libraryModel.addSongToPlaylist("A Whisper", "Coldplay", "testPlaylist");
        libraryModel.addSongToPlaylist("Burn One Down", "Ben Harper", "testPlaylist");
        libraryModel.addSongToPlaylist("Power of the Gospel", "Ben Harper", "testPlaylist");
        libraryModel.addSongToPlaylist("One Road to Freedom", "Ben Harper", "testPlaylist");

        //System.out.println(libraryModel.playlistByToNameString("testPlaylist"));
        //libraryModel.shufflePlaylistToString("testPlaylist");
       // System.out.println(libraryModel.playlistByToNameString("testPlaylist"));
    }

    @Test
    public void testingMoreInfo() {
        libraryModel.addAlbumToLibrary("19");
        libraryModel.addAlbumToLibrary("A Rush of Blood to the Head");

        libraryModel.removeSongFromLibrary("God Put a Smile Upon Your Face", "Coldplay");

        // System.out.print(libraryModel.requestMoreInfo("A Rush of Blood to the
        // Head"));

        String ex1 = """
                === Album Info ===
                Album: A Rush of Blood to the Head, Artist: Coldplay, Genre: Alternative
                Politik
                In My Place
                God Put a Smile Upon Your Face
                The Scientist
                Clocks
                Daylight
                Green Eyes
                Warning Sign
                A Whisper
                A Rush of Blood to the Head
                Amsterdam
                Full Album is not in Library
                """;

        assertEquals(ex1, libraryModel.requestMoreInfo("A Rush of Blood to the Head"));

        libraryModel.addSong("Lullaby", "Leonard Cohen");
        libraryModel.addSong("Lullaby", "OneRepublic");

        String ex2 = """
                === Album Info ===
                Album: Old Ideas, Artist: Leonard Cohen, Genre: Singer/Songwriter
                Going Home
                Amen
                Show Me the Place
                Darkness
                Anyhow
                Crazy to Love You
                Come Healing
                Banjo
                Lullaby
                Different Sides
                Full Album is not in Library
                === Album Info ===
                Album: Waking Up, Artist: OneRepublic, Genre: Rock
                Made for You
                All the Right Moves
                Secrets
                Everybody Loves Me
                Missing Persons 1 & 2
                Good Life
                All This Time
                Fear
                Waking Up
                Marchin On
                Lullaby
                Full Album is not in Library
                """;

        assertEquals(ex2, libraryModel.requestMoreInfo("Lullaby"));

        // System.out.print(libraryModel.requestMoreInfo("Lullaby"));

        String ex3 = """
                === Album Info ===
                Album: 19, Artist: Adele, Genre: Pop
                Daydreamer
                Best for Last
                Chasing Pavements
                Cold Shoulder
                Crazy for You
                Melt My Heart to Stone
                First Love
                Right as Rain
                Make You Feel My Love
                My Same
                Tired
                Hometown Glory
                Full Album is in Library
                """;

        assertEquals(ex3, libraryModel.requestMoreInfo("Tired"));

        // System.out.print(libraryModel.requestMoreInfo("Tired"));

    }

    @Test
    public void testPartialAlbums() {
        libraryModel.addSong("A Whisper", "Coldplay");
        // System.out.print(libraryModel.getAlbumsByTitle("A Rush of Blood to the
        // Head"));

        String ex1 = "Album: A Rush of Blood to the Head, Artist: Coldplay, Genre: Alternative\n" +
                "A Whisper\n";
        assertEquals(ex1, libraryModel.getAlbumsByTitle("A Rush of Blood to the Head"));

    }

    @Test
    public void testAutoPlaylist() {
        libraryModel.addAlbumToLibrary("A Rush of Blood to the Head");
        // System.out.print(libraryModel.listAllPlaylists());

        String ex1 = "=== Playlists List ===\n" +
                "Playlist: Genre: Alternative\n";
        assertEquals(ex1, libraryModel.listAllPlaylists());

        libraryModel.addSong("Melt My Heart to Stone", "Adele");
        libraryModel.addSong("First Love", "Adele");
        libraryModel.addSong("Right as Rain", "Adele");
        libraryModel.addSong("Make You Feel My Love", "Adele");
        libraryModel.addSong("My Same", "Adele");
        libraryModel.addSong("Tired", "Adele");
        libraryModel.addSong("Hometown Glory", "Adele");
        libraryModel.addSong("He Won't Go", "Adele");
        libraryModel.addSong("Take It All", "Adele");
        libraryModel.addSong("I'll Be Waiting", "Adele");
        libraryModel.addSong("One and Only", "Adele");
        libraryModel.addSong("Lovesong", "Adele");
        libraryModel.addSong("Someone Like You", "Adele");
        libraryModel.addSong("I Found a Boy", "Adele");

        // System.out.print(libraryModel.listAllPlaylists());
        String ex2 = "=== Playlists List ===\n" +
                "Playlist: Genre: Alternative\n" +
                "Playlist: Genre: Pop\n";
        assertEquals(ex2, libraryModel.listAllPlaylists());
    }

    @Test
    public void testAutoPlaylist2() {
        libraryModel.addAlbumToLibrary("A Rush of Blood to the Head");
        libraryModel.rateSong("A Rush of Blood to the Head", "Coldplay", Rating.FIVE);
        libraryModel.rateSong("Warning Sign", "Coldplay", Rating.FOUR);

        // System.out.print(libraryModel.listAllPlaylists());
        String ex1 = "=== Playlists List ===\n" +
                "Playlist: Favorite Songs\n" +
                "Playlist: Genre: Alternative\n" +
                "Playlist: Top Rated\n";
        assertEquals(ex1, libraryModel.listAllPlaylists());

        // System.out.print(libraryModel.playlistByToNameString("Favorite Songs"));
        String ex2 = "=== Playlist: Favorite Songs ===\n" +
                "Song - Title: A Rush of Blood to the Head, Artist: Coldplay, Album: A Rush of Blood to the Head\n";
        assertEquals(ex2, libraryModel.playlistByToNameString("Favorite Songs"));

        // System.out.print(libraryModel.playlistByToNameString("Top Rated"));
        String ex3 = "=== Playlist: Top Rated ===\n" +
                "Song - Title: A Rush of Blood to the Head, Artist: Coldplay, Album: A Rush of Blood to the Head\n" +
                "Song - Title: Warning Sign, Artist: Coldplay, Album: A Rush of Blood to the Head\n";
        assertEquals(ex3, libraryModel.playlistByToNameString("Top Rated"));
    }

    @Test
    public void addNAAlbumToLibrary() {
        assertFalse(libraryModel.addAlbumToLibrary("RANDOM"));
    }

    @Test
    public void testGetStringByGenre(){
        libraryModel.addAlbumToLibrary("A Rush of Blood to the Head");
        libraryModel.addAlbumToLibrary("Old Ideas");
        libraryModel.addAlbumToLibrary("Don't Mess With the Dragon");

        //System.out.print(libraryModel.getSongsByGenreString("Rock"));

        String ex =
                "=== Songs in Genre: Rock ===\n" +
                "Song - Title: City Of Angels, Artist: Ozomatli, Album: Don't Mess With the Dragon\n" +
                "Song - Title: After Party, Artist: Ozomatli, Album: Don't Mess With the Dragon\n" +
                "Song - Title: Don't Mess With The Dragon, Artist: Ozomatli, Album: Don't Mess With the Dragon\n" +
                "Song - Title: La Gallina, Artist: Ozomatli, Album: Don't Mess With the Dragon\n" +
                "Song - Title: Magnolia Soul, Artist: Ozomatli, Album: Don't Mess With the Dragon\n" +
                "Song - Title: Here We Go, Artist: Ozomatli, Album: Don't Mess With the Dragon\n" +
                "Song - Title: La Temperatura, Artist: Ozomatli, Album: Don't Mess With the Dragon\n" +
                "Song - Title: Violeta, Artist: Ozomatli, Album: Don't Mess With the Dragon\n" +
                "Song - Title: Creo, Artist: Ozomatli, Album: Don't Mess With the Dragon\n" +
                "Song - Title: When I Close My Eyes, Artist: Ozomatli, Album: Don't Mess With the Dragon\n";
        assertEquals(ex, libraryModel.getSongsByGenreString("Rock"));
        assertEquals("No songs found for genre: RANDOM\n", libraryModel.getSongsByGenreString("RANDOM"));
    }
}
