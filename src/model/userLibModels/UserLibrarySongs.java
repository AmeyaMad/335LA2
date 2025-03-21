//file: UserLibrarySongs.java
//author: Ameya Madhugiri
//purpose: To make sure LibraryModel.java is not a god class I am splitting classes up. this will
// handle the stuff from LA1

package model.userLibModels;

import model.*;

import java.util.ArrayList;
import java.util.HashMap;

public class UserLibrarySongs {
    // declaring both of these hashmaps
    private HashMap<String, ArrayList<Song>> songsByTitle;
    private HashMap<String, ArrayList<Song>> songsByArtist;

    // Constructor
    public UserLibrarySongs() {
        songsByTitle = new HashMap<>();
        songsByArtist = new HashMap<>();
    }

    // this will take in a song object and add it to both songsByTitle and
    // songsByArtist
    // @pre song != null
    public String addSongToLibrary(Song song) {
        // TODO make sure this is in the video
        // After dealing with the if(empty) blah blah last time i checked to see if
        // there was an easier
        // way to place things into the hashmap and found the .computeIfAbsent function

        /*
         * As far as I understand it, it allows you to do something (in this case create
         * a new arraylist)
         * if the value for the given key is not there in one line of code. I guess the
         * k is needed
         * as a placeholder and is the standard, but is never actually used
         */

        String out = "Added song " + song.getTitle() + " to the library\n";
        songsByTitle.computeIfAbsent(song.getTitle(), k -> new ArrayList<>());
        if (!songsByTitle.get(song.getTitle()).contains(song)) {
            songsByTitle.get(song.getTitle()).add(song);
        } else {
            out = "This song is already in the list\n";
        }

        songsByArtist.computeIfAbsent(song.getArtist(), k -> new ArrayList<>());
        if (!songsByArtist.get(song.getArtist()).contains(song)) {
            songsByArtist.get(song.getArtist()).add(song);
        } else {
            out = "This song is already in the list\n";
        }
        return out;
    }

    // will remove a song from user library
    // @pre title != null & artist != null
    public String removeSongFromLibrary(String title, String artist) {
        Song sWeWant = HelperFunctions.getSongByTitleAndArtist(title, artist);
        if (sWeWant == null) {
            return "There is no song that has this name by this artist in the music store\n";
        }

        // remove from songsByTitle
        ArrayList<Song> songs = this.songsByTitle.get(title);
        songs.remove(sWeWant);

        // remove song from songsByArtist
        songs = this.songsByArtist.get(artist);
        songs.remove(sWeWant);

        // TODO implement remove in the albums class to make this shit work

        return "TEMP";
    }

    // Returns an ArrayList of all songs with name `title`
    // @pre title != null
    private ArrayList<Song> getSongsByTitle(String title) {
        if (songsByTitle.get(title) == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(songsByTitle.get(title));
    }

    // Returns an ArrayList of all songs with name `title`
    // @pre title != null
    private ArrayList<Song> getSongsByArtist(String artist) {
        if (songsByArtist.get(artist) == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(songsByArtist.get(artist));
    }

    // string for songs by title
    // @pre title != null
    public String songsByTitleToString(String title) {
        if (getSongsByTitle(title).isEmpty()) {
            return "This Song is not in the songs list\n";
        }
        StringBuilder sb = new StringBuilder();
        for (Song s : getSongsByTitle(title)) {
            sb.append(s.toString()).append("\n");
        }
        return sb.toString();
    }

    // string for songs by artist
    // @pre artist != null
    public String songsByArtistToString(String artist) {
        if (getSongsByArtist(artist).isEmpty()) {
            return "There are no songs by this artist\n";
        }
        StringBuilder sb = new StringBuilder();
        for (Song s : getSongsByArtist(artist)) {
            sb.append(s.toString()).append("\n");
        }
        return sb.toString();
    }

    // need functions to list all songs, albums, and playlists
    // will return a string with all songs in the Library
    public String allSongToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Songs List ===\n");

        for (String title : songsByTitle.keySet()) {
            ArrayList<Song> songs = songsByTitle.get(title); // gets list of songs with this title

            for (Song song : songs) { // add to string for each song
                sb.append("Title: ").append(song.getTitle())
                        .append(", Artist: ").append(song.getArtist())
                        .append(", Album: ").append(song.getAlbum())
                        .append("\n");
            }
        }
        return sb.toString();
    }

    // lists all artists in the songs
    public String listAllArtistsToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Artists List ===\n");

        for (String artist : songsByArtist.keySet()) {
            sb.append(artist).append("\n");
        }

        return sb.toString();
    }
}
