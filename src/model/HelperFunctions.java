//file: HelperFunctions.java
//Author: Ameya Madhugiri
//Purpose:The purpose of this class is to act in a similar manner as the Math. class
// and to provide helper comments to make sure there is no issues with the duplicated
//code antipattern
package model;

import java.util.ArrayList;

public class HelperFunctions {
    // This looks like what I found online in terms of how to set up a class to be
    // used like Math.abs() or similar
    // I remember it was brought up in class but was not able to find any reference
    // to it

    // private constructor to prevent instantiation
    private HelperFunctions() {
    }

    // this function takes in a title and artist and returns the song from the music
    // store
    // @pre title!= null && artist != null
    public static Song getSongByTitleAndArtist(String title, String artist) {
        // making a music store
        MusicStore ms = new MusicStore("albums");
        // if we are unable to find any songs with that name, return null
        if (ms.getSongsByTitle(title) == null) {
            return null;
        }

        // list of all songs with the name
        ArrayList<Song> songsWithName = ms.getSongsByTitle(title);

        // setting up return obj
        Song sWeWant = null;
        // check each song in the list for the one with the right artist
        for (Song s : songsWithName) {
            if (s.getArtist().equals(artist)) {
                sWeWant = s;
            }
        }
        if (sWeWant == null) {
            return null;
        }
        // return result, if not able to find one still return null
        return new Song(sWeWant);
    }

    // This function takes in a title and returns an album object of the same name
    // @pre title != null
    public static Album getAlbumByTitle(String title) {
        MusicStore ms = new MusicStore("albums");
        if (ms.getAlbumsByTitle(title) == null) {
            return null;
        }
        // uses same idea as function above to return an album
        ArrayList<Album> albums = ms.getAlbumsByTitle(title);
        Album album = null;
        for (Album a : albums) {
            if (a.getTitle().equals(title)) {
                album = a;
            }
        }
        return album; // since albums are immutable dont need ot worry about it
    }

}
