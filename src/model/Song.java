// file: Song.java
// author: Ameya Madhugiri
// purpose: This class is going to represent a song, and will hold information about it
//          this class will also be immutable since we dont want to change details
package model;

import java.util.ArrayList;
import java.util.Comparator;

public class Song {
    // setting up instance variables to hold information about the song
    // could be final but not needed since strings are already immutable
    private String title;
    private String artist;
    private String album;
    private Rating stars;

    /*
     * constructor so we can create song objects easily
     * 
     * @pre title != null && artist != null && album != null
     */
    public Song(String title, String artist, String album) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.stars = Rating.NONE;
    }

    /*
     * constructor to make making copies a lot easier
     * 
     * @pre s != null
     */
    public Song(Song s) {
        this.title = s.title;
        this.artist = s.artist;
        this.album = s.album;
    }

    // auto generated getters for each
    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }


    public Rating getRating() {return stars;}

    public void setStars(Rating stars) {
        this.stars = stars;
    }


    // overriding the toString to make printing easier
    @Override
    public String toString() {
        return "Song - Title: " + title + ", Artist: " + artist + ", Album: " + album;
    }

    // TODO Show this in video to show why coverage is so low

    // Overriding the equals function to make .contains for an arrayList work
    // @pre o != null && o.getClass() == this.getClass()
    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Song song = (Song) o;
        return this.title != null && this.title.equals(song.title) && this.artist != null
                && this.artist.equals(song.artist) && this.album != null && this.album.equals(song.album);
    }

    // overriding hashcode as well to keep contract
    @Override
    public int hashCode() {
        String songConcat = title + artist + album;
        return songConcat.hashCode();
    }


    //Dont fully understand the syntax and everything here, but basing it on the
    //example given by prof
    public static Comparator<Song> titleFirstComparator(){
        return new Comparator<Song>() {
            public int compare(Song o1, Song o2) {
                int comp = o1.getTitle().compareTo(o2.getTitle());
                if (comp == 0) {
                    comp = o1.getArtist().compareTo(o2.getArtist());
                }
                return comp;
            }
        };
    }

    public static Comparator<Song> artistFirstComparator(){
        return new Comparator<Song>() {
            public int compare(Song o1, Song o2) {
                int comp = o1.getArtist().compareTo(o2.getArtist());
                if (comp == 0) {
                    comp = o1.getTitle().compareTo(o2.getTitle());
                }
                return comp;
            }
        };
    }

    public static Comparator<Song> ratingFirstComparator(){
        return new Comparator<Song>() {
            public int compare(Song o1, Song o2) {
                int comp = o1.getRating().compareTo(o2.getRating());
                if (comp == 0) {
                    comp = o1.getTitle().compareTo(o2.getTitle());
                }
                return comp;
            }
        };
    }



}
