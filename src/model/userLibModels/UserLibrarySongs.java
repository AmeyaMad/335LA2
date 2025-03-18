//file: UserLibrarySongs.java
//author: Ameya Madhugiri
//purpose: To make sure LibraryModel.java is not a god class I am splitting classes up. this will
// handle the stuff from LA1

package model.userLibModels;

import model.*;

import java.util.ArrayList;
import java.util.HashMap;

public class UserLibrarySongs {
    //declaring both of these hashmaps
    private HashMap<String, ArrayList<Song>> songsByTitle;
    private HashMap<String, ArrayList<Song>> songsByArtist;

    //Constructor
    public UserLibrarySongs() {
        songsByTitle = new HashMap<>();
        songsByArtist = new HashMap<>();
    }

    //this will take in a song object and add it to both songsByTitle and songsByArtist
    //@pre song != null
    public void addSong(Song song) {
        //TODO make sure this is in the video
        //After dealing with the if(empty) blah blah last time i checked to see if there was an easier
        //way to place things into the hashmap and found the .computeIfAbsent function

        /*As far as I understand it, it allows you to do something (in this case create a new arraylist)
        if the value for the given key is not there in one line of code I guess the k is needed
        as a placeholder and is the standard, but is never actually used
         */
        songsByTitle.computeIfAbsent(song.getTitle(), k -> new ArrayList<Song>());
        songsByTitle.get(song.getTitle()).add(song);

        songsByArtist.computeIfAbsent(song.getArtist(), k -> new ArrayList<Song>());
        songsByArtist.get(song.getArtist()).add(song);
    }

    //Returns an ArrayList of all songs with name `title`
    //@pre title != null
    public ArrayList<Song> getSongsByTitle(String title) {
        return new ArrayList<>(songsByTitle.get(title));
    }

    //Returns an ArrayList of all songs with name `title`
    //@pre title != null
    public ArrayList<Song> getSongsByArtist(String artist) {
        return new ArrayList<>(songsByArtist.get(artist));
    }

    // string for songs by title
    // @pre title != null
    public String songsByTitleToString(String title) {
        if (getSongsByTitle(title) == null) {
            return "This Song is not in the songs list\n";
        }
        StringBuilder sb = new StringBuilder();
        for (Song s : getSongsByTitle(title)) {
            sb.append(s.toString()).append("\n");
        }
        return sb.toString();
    }

    //TODO deal with other issues like we had with the albums one with null pointer execption when reutrning

    // string for songs by artist
    // @pre artist != null
    public String SongsByArtistToString(String artist) {
        if (getSongsByArtist(artist) == null) {
            return "There are no songs by this artist\n";
        }
        StringBuilder sb = new StringBuilder();
        for (Song s : getSongsByArtist(artist)) {
            sb.append(s.toString()).append("\n");
        }
        return sb.toString();
    }

    // now we need new logic for adding and removing songs/albums
    // @pre title != null
    public String addSongToLibrary(String title, String artist) {
        Song sWeWant = HelperFunctions.getSongByTitleAndArtist(title, artist);

        if (sWeWant == null) {
            return "This song is not in the Music Store\n";
        }

        if (songsByTitle.containsKey(title)) {
            for (Song s : songsByTitle.get(title)) {
                if (s.getArtist().equals(artist)) {
                    return "This song is already in the list\n";
                }
            }
            songsByTitle.get(title).add(sWeWant);
        } else {
            ArrayList<Song> newSongs = new ArrayList<Song>();
            newSongs.add(sWeWant);
            songsByTitle.put(title, newSongs);
        }

        if (songsByArtist.containsKey(artist)) {
            songsByArtist.get(artist).add(sWeWant);
        } else {
            ArrayList<Song> tmp = new ArrayList<>();
            tmp.add(sWeWant);
            songsByArtist.put(artist, tmp);
        }
        return "Successfully added song to the library\n";
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
}

