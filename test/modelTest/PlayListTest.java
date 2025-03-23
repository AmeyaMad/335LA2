package modelTest;

import org.junit.Test;
import model.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PlayListTest {
    PlayList pl = new PlayList("RandomPlayList");
    Song hereWeGo = new Song("Here We Go", "Ozomatli", "Don't Mess With the Dragon");
    Song iAint = new Song("I Ain't the Same", "Alabama Shakes", "Boys & Girls");
    ArrayList<Song> songs = new ArrayList<>();

    @Test
    public void testGetName() {
        assertEquals("RandomPlayList", pl.getName());
    }

    @Test
    public void testAddSong() {
        pl.addSong(hereWeGo);
        pl.addSong(iAint);
        String ex = "Title: Here We Go, Artist: Ozomatli, Album: Don't Mess With the Dragon\n" +
                "Title: I Ain't the Same, Artist: Alabama Shakes, Album: Boys & Girls\n";
        assertEquals(ex, pl.toString());
    }

    @Test
    public void testRemoveSong() {
        pl.addSong(hereWeGo);
        pl.addSong(iAint);
        pl.removeSong(hereWeGo);
        String ex = "Title: I Ain't the Same, Artist: Alabama Shakes, Album: Boys & Girls\n";
        assertEquals(ex, pl.toString());
        System.out.print(pl);
    }

    @Test
    public void addingSameSong() {
        pl.addSong(iAint);
        pl.addSong(iAint);
        String ex = "Title: I Ain't the Same, Artist: Alabama Shakes, Album: Boys & Girls\n";
        assertEquals(ex, pl.toString());
    }

    @Test
    public void testAddingAlbums() {
        pl.addAlbum("Begin Again");
        pl.addAlbum("Mission Bell");
        String ex = "Title: My Heart Is Full, Artist: Norah Jones, Album: Begin Again\n" +
                "Title: Begin Again, Artist: Norah Jones, Album: Begin Again\n" +
                "Title: It Was You, Artist: Norah Jones, Album: Begin Again\n" +
                "Title: A Song with No Name, Artist: Norah Jones, Album: Begin Again\n" +
                "Title: Uh Oh, Artist: Norah Jones, Album: Begin Again\n" +
                "Title: Wintertime, Artist: Norah Jones, Album: Begin Again\n" +
                "Title: Just a Little Bit, Artist: Norah Jones, Album: Begin Again\n" +
                "Title: El Camino, Artist: Amos Lee, Album: Mission Bell\n" +
                "Title: Windows Are Rolled Down, Artist: Amos Lee, Album: Mission Bell\n" +
                "Title: Flower, Artist: Amos Lee, Album: Mission Bell\n" +
                "Title: Stay With Me, Artist: Amos Lee, Album: Mission Bell\n" +
                "Title: Out of the Cold, Artist: Amos Lee, Album: Mission Bell\n" +
                "Title: Jesus, Artist: Amos Lee, Album: Mission Bell\n" +
                "Title: Hello Again, Artist: Amos Lee, Album: Mission Bell\n" +
                "Title: Cup of Sorrow, Artist: Amos Lee, Album: Mission Bell\n" +
                "Title: Clear Blue Eyes (feat. Lucinda Williams), Artist: Amos Lee, Album: Mission Bell\n" +
                "Title: Behind Me Now, Artist: Amos Lee, Album: Mission Bell\n";
        assertEquals(ex, pl.toString());
    }

    @Test
    public void testAddingBadAlbum() {
        // System.out.print(pl.addAlbum("Graduation"));
        String ex = "Album does not exist in Music Store";
        assertEquals(ex, pl.addAlbum("Graduation"));
    }

    @Test
    public void testAddingAlbumWhenSongsAlreadyExist() {
        pl.addSong(iAint);
        pl.addAlbum("Boys & Girls");
        // System.out.print(pl.getSongsString());
        String ex = "Title: I Ain't the Same, Artist: Alabama Shakes, Album: Boys & Girls\n" +
                "Title: Hold On, Artist: Alabama Shakes, Album: Boys & Girls\n" +
                "Title: I Found You, Artist: Alabama Shakes, Album: Boys & Girls\n" +
                "Title: Hang Loose, Artist: Alabama Shakes, Album: Boys & Girls\n" +
                "Title: Rise to the Sun, Artist: Alabama Shakes, Album: Boys & Girls\n" +
                "Title: You Ain't Alone, Artist: Alabama Shakes, Album: Boys & Girls\n" +
                "Title: Goin' to the Party, Artist: Alabama Shakes, Album: Boys & Girls\n" +
                "Title: Heartbreaker, Artist: Alabama Shakes, Album: Boys & Girls\n" +
                "Title: Boys & Girls, Artist: Alabama Shakes, Album: Boys & Girls\n" +
                "Title: Be Mine, Artist: Alabama Shakes, Album: Boys & Girls\n" +
                "Title: On Your Way, Artist: Alabama Shakes, Album: Boys & Girls\n" +
                "Title: Heavy Chevy (Bonus Track), Artist: Alabama Shakes, Album: Boys & Girls\n";
        assertEquals(ex, pl.toString());
    }



}
