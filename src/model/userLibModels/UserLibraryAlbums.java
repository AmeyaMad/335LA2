//file: UserLibraryAlbums.java
//author: Ameya Madhugiri
//purpose: To make sure LibraryModel.java is not a god class I am splitting classes up. this will
// handle the stuff from LA1 (Albums)

package model.userLibModels;

import model.*;

import java.util.ArrayList;
import java.util.HashMap;

public class UserLibraryAlbums {
    //declaring both of these hashmaps
    private HashMap<String, ArrayList<Album>> albumsByTitle;
    private HashMap<String, ArrayList<Album>> albumsByArtist;
    private UserLibrarySongs userLibrarySongs;

    //Constructor
    public UserLibraryAlbums(UserLibrarySongs ulSongs) {
        albumsByTitle = new HashMap<>();
        albumsByArtist = new HashMap<>();
        userLibrarySongs = ulSongs;
    }


    //this will take in a song object and add it to both albumsByTitle and albumsByArtist
    //@pre a != null
    public void addAlbum(Album a) {
        //TODO make sure this is in the video
        //After dealing with the if(empty) blah blah last time i checked to see if there was an easier
        //way to place things into the hashmap and found the .computeIfAbsent function

        /*As far as I understand it, it allows you to do something (in this case create a new arraylist)
        if the value for the given key is not there in one line of code I guess the k is needed
        as a placeholder and is the standard, but is never actually used
         */
        albumsByTitle.computeIfAbsent(a.getTitle(), k -> new ArrayList<Album>());
        albumsByTitle.get(a.getTitle()).add(a);

        albumsByArtist.computeIfAbsent(a.getArtist(), k -> new ArrayList<Album>());
        albumsByArtist.get(a.getArtist()).add(a);
    }

    //Returns an ArrayList of all songs with name `title`
    //@pre title != null
    public ArrayList<Album> getAlbumsByTitle(String title) {
        if (!albumsByTitle.containsKey(title)) {
            //return an empty arraylist if does not exist
            return new ArrayList<>();
        }
        return new ArrayList<>(albumsByTitle.get(title));
    }

    //Returns an ArrayList of all songs with name `title`
    //@pre title != null
    public ArrayList<Album> getAlbumsByArtist(String artist) {
        if (!albumsByArtist.containsKey(artist)) {
            //return an empty arraylist if does not exist
            return new ArrayList<>();
        }
        return new ArrayList<>(albumsByArtist.get(artist));
    }

    // string for albums by title
    // @pre title != null
    public String AlbumsByTitleToString(String title) {
        if (getAlbumsByTitle(title) == null) {
            return "There are no albums of this name\n";
        }
        StringBuilder sb = new StringBuilder();
        for (Album a : getAlbumsByTitle(title)) {
            sb.append(a.toString()).append("\n");
        }
        return sb.toString();
    }

    // string for albums by artist
    // @pre artist != null
    public String AlbumsByArtistToString(String artist) {
        if (getAlbumsByArtist(artist) == null) {
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
            return false;   //album is not in music store
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
            userLibrarySongs.addSongToLibrary(s.getTitle(), s.getArtist());
        }
        return true;
    }
}


