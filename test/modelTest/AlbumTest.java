package modelTest;

import org.junit.Test;
import src.model.Album;
import src.model.MusicStore;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AlbumTest {
    // creating album to make things easier
    MusicStore musicStore = new MusicStore("albums");
    ArrayList<Album> albums = musicStore.getAlbumsByTitle("A Rush of Blood to the Head");
    Album a = albums.get(0);

    @Test
    public void testGetTitle() {
        assertEquals("A Rush of Blood to the Head", a.getTitle());
    }

    @Test
    public void testGetArtist() {
        assertEquals("Coldplay", a.getArtist());
    }

    @Test
    public void testGetGenre() {
        assertEquals("Alternative", a.getGenre());
    }

    @Test
    public void testToString() {
        String ex = "Album: A Rush of Blood to the Head, Artist: Coldplay, Genre: Alternative\n" +
                "Politik\n" +
                "In My Place\n" +
                "God Put a Smile Upon Your Face\n" +
                "The Scientist\n" +
                "Clocks\n" +
                "Daylight\n" +
                "Green Eyes\n" +
                "Warning Sign\n" +
                "A Whisper\n" +
                "A Rush of Blood to the Head\n" +
                "Amsterdam\n";
        assertEquals(ex, a.toString());
    }
}
