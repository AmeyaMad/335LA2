package modelTest;

import org.junit.Test;
import model.MusicStore;

import static org.junit.Assert.*;

public class MusicStoreTest {
    MusicStore musicStore = new MusicStore("albums");

    @Test
    public void testGetSongsByTitleOne() {
        assertEquals("Song - Title: Burn One Down, Artist: Ben Harper, Album: Fight for Your Mind\n",
                musicStore.getSongsByTitleString("Burn One Down"));
    }

    @Test
    public void testGetSongsByTitleTwo() {
        assertEquals(
                "Song - Title: Lullaby, Artist: Leonard Cohen, Album: Old Ideas\nSong - Title: Lullaby, Artist: OneRepublic, Album: Waking Up\n",
                musicStore.getSongsByTitleString("Lullaby"));
    }

    @Test
    public void testGetSongsByArtist() {
        String expected = "Song - Title: Heavy for You, Artist: The Heavy, Album: Sons\n" +
                "Song - Title: The Thief, Artist: The Heavy, Album: Sons\n" +
                "Song - Title: Better as One, Artist: The Heavy, Album: Sons\n" +
                "Song - Title: Fire, Artist: The Heavy, Album: Sons\n" +
                "Song - Title: Fighting for the Same Thing, Artist: The Heavy, Album: Sons\n" +
                "Song - Title: Hurt Interlude, Artist: The Heavy, Album: Sons\n" +
                "Song - Title: Put the Hurt on Me, Artist: The Heavy, Album: Sons\n" +
                "Song - Title: Simple Things, Artist: The Heavy, Album: Sons\n" +
                "Song - Title: A Whole Lot of Love, Artist: The Heavy, Album: Sons\n" +
                "Song - Title: What Don't Kill You, Artist: The Heavy, Album: Sons\n" +
                "Song - Title: Burn Bright, Artist: The Heavy, Album: Sons\n";

        assertEquals(expected, musicStore.getSongsByArtistString("The Heavy"));
    }

    @Test
    public void testGetAlbumsByTitle() {
        String expected = "Album: Fight for Your Mind, Artist: Ben Harper, Genre: Alternative\n" +
                "Oppression\n" +
                "Ground on Down\n" +
                "Another Lonely Day\n" +
                "Gold to Me\n" +
                "Burn One Down\n" +
                "Excuse Me Mr.\n" +
                "People Lead\n" +
                "Fight for Your Mind\n" +
                "Give a Man a Home\n" +
                "By My Side\n" +
                "Power of the Gospel\n" +
                "God Fearing Man\n" +
                "One Road to Freedom\n" +
                "\n";

        assertEquals(expected, musicStore.getAlbumsByTitleString("Fight for Your Mind"));
    }

    @Test
    public void testGetAlbumsByArtist() {
        String expected = "Album: 21, Artist: Adele, Genre: Pop\n" +
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
                "\n" +
                "Album: 19, Artist: Adele, Genre: Pop\n" +
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
                "\n";
        assertEquals(expected, musicStore.getAlbumsByArtistString("Adele"));
    }

    // now lets test the case where the thing does not exist
    @Test
    public void testGetSongsByTitleNull() {
        assertEquals("This Song is not in the songs list", musicStore.getSongsByTitleString("Yellow Brick Road"));
    }

    @Test
    public void testGetSongsByArtistNull() {
        assertEquals("There are no songs by this artist", musicStore.getSongsByArtistString("Kanye West"));
    }

    @Test
    public void testGetAlbumsByTitleNull() {
        assertEquals("There are no albums of this name", musicStore.getAlbumsByTitleString("DNE"));
    }

    @Test
    public void testGetAlbumsByArtistNull() {
        assertEquals("There are no albums by this artist", musicStore.getAlbumsByArtistString("DNE"));
    }

}
