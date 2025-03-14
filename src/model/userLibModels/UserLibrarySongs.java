//file: UserLibrarySongs.java
//author: Ameya Madhugiri
//purpose: To make sure LibraryModel.java is not a god class I am splitting classes up. this will
// handle the stuff from LA1

package model.userLibModels;

import model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserLibrarySongs {
    //declaring both of these hashmaps
    private HashMap<String, ArrayList<Song>> songsByTitle;
    private HashMap<String, ArrayList<Song>> songsByArtist;


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
}

