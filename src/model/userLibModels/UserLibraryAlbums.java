//file: UserLibraryAlbums.java
//author: Ameya Madhugiri
//purpose: To make sure LibraryModel.java is not a god class I am splitting classes up. this will
// handle the stuff from LA1 (Albums)

package model.userLibModels;

import model.*;

import java.util.ArrayList;
import java.util.HashMap;

public class UserLibraryAlbums {
    // declaring both of these hashmaps
    private HashMap<String, ArrayList<Album>> albumsByTitle;
    private HashMap<String, ArrayList<Album>> albumsByArtist;
    private UserLibrarySongs userLibrarySongs;

    // Constructor
    public UserLibraryAlbums(UserLibrarySongs ulSongs) {
        albumsByTitle = new HashMap<>();
        albumsByArtist = new HashMap<>();
        userLibrarySongs = ulSongs;
    }

    // Returns an ArrayList of all songs with name `title`
    // @pre title != null
    public ArrayList<Album> getAlbumsByTitle(String title) {
        if (!albumsByTitle.containsKey(title)) {
            // return an empty arraylist if does not exist
            return new ArrayList<>();
        }
        return new ArrayList<>(albumsByTitle.get(title));
    }

    // Returns an ArrayList of all songs with name `title`
    // @pre title != null
    public ArrayList<Album> getAlbumsByArtist(String artist) {
        if (!albumsByArtist.containsKey(artist)) {
            // return an empty arraylist if does not exist
            return new ArrayList<>();
        }
        return new ArrayList<>(albumsByArtist.get(artist));
    }

    // string for albums by title
    // @pre title != null
    public String albumsByTitleToString(String title) {
        if (getAlbumsByTitle(title).isEmpty()) {
            return "There are no albums of this name\n";
        }
        StringBuilder sb = new StringBuilder();
        for (Album a : getAlbumsByTitle(title)) {
            sb.append(a.toString());
        }
        return sb.toString();
    }

    // string for albums by artist
    // @pre artist != null
    public String albumsByArtistToString(String artist) {
        if (getAlbumsByArtist(artist).isEmpty()) {
            return "There are no albums by this artist\n";
        }
        StringBuilder sb = new StringBuilder();
        for (Album a : getAlbumsByArtist(artist)) {
            sb.append(a.toString()).append("\n");
        }
        return sb.toString();
    }

    // Logic for adding albums to library
    // @pre title != null
    public boolean addAlbumToLibrary(String title) {
        Album a = HelperFunctions.getAlbumByTitle(title);
        if (a == null) {
            return false; // album is not in music store
        }

        if (albumsByTitle.containsKey(title)) {
            return false; // Album is already in the library
        }

        ArrayList<Album> albums = new ArrayList<>();
        albums.add(a);

        // Add album by title
        albumsByTitle.put(title, albums);

        // Add album to artist-based lookup
        if (albumsByArtist.containsKey(a.getArtist())) {
            albumsByArtist.get(a.getArtist()).add(a);
        } else {
            ArrayList<Album> newAlbumList = new ArrayList<>();
            newAlbumList.add(a);
            albumsByArtist.put(a.getArtist(), newAlbumList);
        }

        for (Song s : a.getSongs()) {
            userLibrarySongs.addSongToLibrary(s);
        }
        return true;
    }

    // will return a string with all Albums in the Library
    public String allAlbumsToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Albums List ===\n");

        for (String albumName : albumsByTitle.keySet()) {
            ArrayList<Album> albums = albumsByTitle.get(albumName); // gets list of albums with this title

            for (Album album : albums) { // add to string for each album
                sb.append("Album: ").append(album.getTitle())
                        .append(", Artist: ").append(album.getArtist())
                        .append("\n");
            }
        }

        return sb.toString();
    }

    // This fucntion will remove songs form albums, since from now on we have
    // partial albums
    // @pre title != null && artist != null
    public void removeSongFromAlbums(String title, String artist) {
        Song sWeWant = HelperFunctions.getSongByTitleAndArtist(title, artist);
        if (sWeWant == null) {
            return;
        }

        for (Album a : albumsByArtist.get(artist)) {
            a.removeSong(sWeWant);
        }

        for (String k : albumsByTitle.keySet()) {
            ArrayList<Album> albums = albumsByTitle.get(k);
            for (Album a : albums) {
                a.removeSong(sWeWant);
            }
        }
    }

    // this removes an entire album from the library
    // @pre title != null
    public void removeAlbumFromLibrary(String title) {
        ArrayList<Album> albums = albumsByTitle.get(title);
        for (Album a : albums) {
            for (Song s : a.getSongs()) {
                userLibrarySongs.removeSongFromLibrary(s.getTitle(), s.getArtist());
            }
        }
        albumsByTitle.remove(title);
    }

    // this adds the partial album to the library whenever a single song is added
    // @pre song != null
    public void addSongToUserAlbum(Song song) {
        String albumTitle = song.getAlbum();
        String artist = song.getArtist();

        // using same computeIfAbsent as other places
        ArrayList<Album> userAlbums = albumsByTitle.computeIfAbsent(albumTitle, k -> new ArrayList<>());
        Album userAlbum = null;

        Album aFromMS = HelperFunctions.getAlbumByTitle(albumTitle);
        String genre = aFromMS.getGenre();

        // since we are doing partial albums now, we can look to see if it is already
        // there
        for (Album a : userAlbums) {
            if (a.getArtist().equals(artist)) {
                userAlbum = a;
                break;
            }
        }

        // otherwise make a new one
        if (userAlbum == null) {
            ArrayList<Song> initSongs = new ArrayList<>();
            initSongs.add(song);
            Album newAlbum = new Album(albumTitle, artist, genre, initSongs);
            userAlbums.add(newAlbum);

            // need to add to albums by artist
            albumsByArtist.computeIfAbsent(artist, k -> new ArrayList<>());
            albumsByArtist.get(artist).add(newAlbum);
        } else {
            // just add a song to the album already there
            if (!userAlbum.getSongs().contains(song)) {
                userAlbum.getSongs().add(song);
            }
        }
    }

}
