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

//    @Test
//    public void testAddingSongTop10() {
//        libraryModel.   //TODO
//    }

    @Test
    public void testPlayingSong(){
        libraryModel.addAlbumToLibrary("21");
        String ex = "===== Playing Song: I'll Be Waiting by Adele ===== \n" +
                "                   <-      ||         ->";
        assertEquals(ex, libraryModel.playSong("I'll Be Waiting", "Adele"));
    }

    @Test
    public void testFrequentSongs(){
        libraryModel.addAlbumToLibrary("21");
        libraryModel.playSong("I'll Be Waiting", "Adele");
        libraryModel.playSong("Rumour Has It", "Adele");
        libraryModel.playSong("Rumour Has It", "Adele");

//        libraryModel.playSong("Rumour Has It", "Adele");
        System.out.print(libraryModel.mostFrequentToString());

    }



}
