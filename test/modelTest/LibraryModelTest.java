package modelTest;

import org.junit.Test;
import src.model.*;

import static org.junit.Assert.*;

public class LibraryModelTest {
    MusicStore musicStore = new MusicStore("albums");
    LibraryModel libraryModel = new LibraryModel(musicStore);

    @Test
    public void testAddandGetSongToLibrary() {
        libraryModel.addSongToLibrary("Daylight", "Coldplay");
        assertEquals("Song - Title: Daylight, Artist: Coldplay, Album: A Rush of Blood to the Head\n",
                libraryModel.getSongsByTitleString("Daylight"));
    }

    @Test
    public void testBadInputGetSongFromLibrary() {
        libraryModel.addSongToLibrary("Daylight", "Coldplay");
        assertEquals("This Song is not in the songs list\n",
                libraryModel.getSongsByTitleString("BLAH"));
    }

    @Test
    public void testAddSongToLibraryWhenNotInStore() {
        assertEquals("This song is not in the Music Store\n", libraryModel.addSongToLibrary("YELLOWBRICKROAD", "IDK"));
    }

    @Test
    // this will be for the 2 lulabys
    public void testAddingTwoSongsSameName() {
        libraryModel.addSongToLibrary("Lullaby", "Leonard Cohen");
        libraryModel.addSongToLibrary("Lullaby", "OneRepublic");

        String ex = "Song - Title: Lullaby, Artist: Leonard Cohen, Album: Old Ideas\n" +
                "Song - Title: Lullaby, Artist: OneRepublic, Album: Waking Up\n";
        assertEquals(ex, libraryModel.getSongsByTitleString("Lullaby"));
    }

    @Test
    // this will be for adding 2 of the same song
    public void testAddingSameSong2Times() {
        libraryModel.addSongToLibrary("Lullaby", "Leonard Cohen");
        assertEquals("This song is already in the list\n", libraryModel.addSongToLibrary("Lullaby", "Leonard Cohen"));
    }

    @Test
    public void testAddingSongRightNameWrongArtist() {
        assertEquals("This song is not in the Music Store\n", libraryModel.addSongToLibrary("After Party", "Adele"));
    }

    @Test
    public void testGetSongsByArtist() {
        libraryModel.addSongToLibrary("I'll Be Waiting", "Adele");
        libraryModel.addSongToLibrary("I Found a Boy", "Adele");
        libraryModel.addSongToLibrary("First Love", "Adele");

        String ex = "Song - Title: I'll Be Waiting, Artist: Adele, Album: 21\n" +
                "Song - Title: I Found a Boy, Artist: Adele, Album: 21\n" +
                "Song - Title: First Love, Artist: Adele, Album: 19\n";
        assertEquals(ex, libraryModel.getSongsByArtistString("Adele"));
    }

    @Test
    public void testGetSongsByArtistEmpty() {
        libraryModel.addSongToLibrary("First Love", "Adele");
        assertEquals("There are no songs by this artist\n", libraryModel.getSongsByArtistString("Kanye"));
    }

    @Test
    public void testGetAlbumsByName() {
        libraryModel.addAlbumToLibrary("Begin Again");
        String ex = "Album: Begin Again, Artist: Norah Jones, Genre: Pop\n" +
                "My Heart Is Full\n" +
                "Begin Again\n" +
                "It Was You\n" +
                "A Song with No Name\n" +
                "Uh Oh\n" +
                "Wintertime\n" +
                "Just a Little Bit\n" +
                "\n";
        assertEquals(ex, libraryModel.getAlbumsByTitleString("Begin Again"));
    }

    @Test
    public void testGetAlbumsByNameEmpty() {
        assertEquals("There are no albums of this name\n", libraryModel.getAlbumsByTitleString("na"));
    }

    @Test
    public void testGetAlbumsByArtist() {
        libraryModel.addAlbumToLibrary("19");
        libraryModel.addAlbumToLibrary("21");
        String ex = "Album: 19, Artist: Adele, Genre: Pop\n" +
                "Daydreamer\n" +
                "Best for Last\n" +
                "Chasing Pavements\n" +
                "Cold Shoulder\n" +
                "Crazy for You\n" +
                "Melt My Heart to Stone\n" +
                "First Love\n" +
                "Right as Rain\n" +
                "Make You Feel My Love\n" +
                "My Same\n" +
                "Tired\n" +
                "Hometown Glory\n" +
                "\n" +
                "Album: 21, Artist: Adele, Genre: Pop\n" +
                "Rolling in the Deep\n" +
                "Rumour Has It\n" +
                "Turning Tables\n" +
                "Don't You Remember\n" +
                "Set Fire to the Rain\n" +
                "He Won't Go\n" +
                "Take It All\n" +
                "I'll Be Waiting\n" +
                "One and Only\n" +
                "Lovesong\n" +
                "Someone Like You\n" +
                "I Found a Boy\n" +
                "\n";
        assertEquals(ex, libraryModel.getAlbumsByArtistString("Adele"));
    }

    @Test
    public void testGetAlbumsByArtistEmpty() {
        assertEquals("There are no albums by this artist\n", libraryModel.getAlbumsByArtistString("na"));
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
        String exFav = "=== Favorites ===\n" +
                "Song - Title: You Ain't Alone, Artist: Alabama Shakes, Album: Boys & Girls\n";

        String exRate = "=== Songs of Rating FIVE ===\n" +
                "Song - Title: You Ain't Alone, Artist: Alabama Shakes, Album: Boys & Girls\n";
        assertEquals(exFav, libraryModel.getFavoritesString());
        assertEquals(exRate, libraryModel.getSongsByRatingString(Rating.FIVE).toString());
    }

    @Test
    public void testAddingSongToFavByRating5() {
        libraryModel.rateSong("The Cave", "Mumford & Sons", Rating.FIVE);
        libraryModel.addSongToFavorites("You Ain't Alone", "Alabama Shakes");
        String ex = "=== Songs of Rating FIVE ===\n" +
                "Song - Title: The Cave, Artist: Mumford & Sons, Album: Sigh No More\n" +
                "Song - Title: You Ain't Alone, Artist: Alabama Shakes, Album: Boys & Girls\n";
        assertEquals(ex, libraryModel.getSongsByRatingString(Rating.FIVE));

    }

    @Test
    public void testNoSongsInFavoriteOrOfRating() {
        assertEquals("There are no songs of this rating\n", libraryModel.getSongsByRatingString(Rating.ONE));
        assertEquals("There are no favorites\n", libraryModel.getFavoritesString());
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
        libraryModel.addSongToLibrary("The Cave", "Mumford & Sons");
        String ex = "=== Songs List ===\n" +
                "Title: It Was You, Artist: Norah Jones, Album: Begin Again\n" +
                "Title: Wintertime, Artist: Norah Jones, Album: Begin Again\n" +
                "Title: A Song with No Name, Artist: Norah Jones, Album: Begin Again\n" +
                "Title: Just a Little Bit, Artist: Norah Jones, Album: Begin Again\n" +
                "Title: Begin Again, Artist: Norah Jones, Album: Begin Again\n" +
                "Title: Uh Oh, Artist: Norah Jones, Album: Begin Again\n" +
                "Title: The Cave, Artist: Mumford & Sons, Album: Sigh No More\n" +
                "Title: My Heart Is Full, Artist: Norah Jones, Album: Begin Again\n";
        assertEquals(ex, libraryModel.listAllSongsString());
    }

    @Test
    public void testListAllAlbumsString() {
        libraryModel.addAlbumToLibrary("21");
        libraryModel.addAlbumToLibrary("19");
        libraryModel.addAlbumToLibrary("Don't Mess With the Dragon");
        String ex = "=== Albums List ===\n" +
                "Album: 19, Artist: Adele\n" +
                "Album: Don't Mess With the Dragon, Artist: Ozomatli\n" +
                "Album: 21, Artist: Adele\n";
        assertEquals(ex, libraryModel.listAllAlbumsString());
    }

    @Test
    public void addingSongToPlaylist() {
        libraryModel.createPlaylist("My Playlist");
        libraryModel.addSongToPlaylist("Hundido En Un Rincon", "Mana", "My Playlist");
        // System.out.print(libraryModel.getPlaylistByNameString("My Playlist"));
        String ex = "=== Playlist: My Playlist ===\n" +
                "Song - Title: Hundido En Un Rincon, Artist: Mana, Album: Cuando Los Angeles Lloran\n";
        assertEquals(ex, libraryModel.getPlaylistByNameString("My Playlist"));
    }

    @Test
    public void testPlaylistDNE() {
        libraryModel.createPlaylist("My Playlist1");
        // System.out.print(libraryModel.getPlaylistByNameString("My"));
        String ex = "There are no playlists by this title\n";
        assertEquals(ex, libraryModel.getPlaylistByNameString("My"));
    }

    @Test
    public void testMultiplePlaylists() {
        libraryModel.createPlaylist("My Playlist1");
        libraryModel.createPlaylist("My Playlist2");
        libraryModel.createPlaylist("My Playlist3");
        // System.out.print(libraryModel.listAllPlaylistsString());
        String ex = "=== Playlists List ===\n" +
                "Playlist: My Playlist1\n" +
                "Playlist: My Playlist2\n" +
                "Playlist: My Playlist3\n";
        assertEquals(ex, libraryModel.listAllPlaylistsString());
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
        String ex = "=== Playlist: My Playlist1 ===\n" +
                "Song - Title: One and Only, Artist: Adele, Album: 21\n";
        assertEquals(ex, libraryModel.getPlaylistByNameString("My Playlist1"));

    }

    @Test
    public void testListAllArtistsString() {
        libraryModel.addAlbumToLibrary("21");
        libraryModel.addAlbumToLibrary("19");
        libraryModel.addAlbumToLibrary("Don't Mess With the Dragon");

        String expected = "=== Artists List ===\n" +
                "Ozomatli\n" +
                "Adele\n";

        assertEquals(expected, libraryModel.listAllArtistsString());
    }

}
