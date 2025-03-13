//  file: Album.java
//  author: Ameya Madhugiri
//  purpose: This class will represent an album and will hold all the songs within it along with
//              the genre and the artist
//  This class is also immutable
package model;

import java.util.ArrayList;

public class Album {
    // setting up instance vars for the album
    private String title;
    private String artist;
    private String genre;
    private ArrayList<Song> songs;

    /*
     * Constructor to make sure making an album is easy
     * 
     * @pre title != null && artist != null && album != null
     */

    public Album(String title, String artist, String genre, ArrayList<Song> songs) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.songs = new ArrayList<Song>(songs); // making sure there is no escaping references
    }

    // auto generated getters for all fields
    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    public ArrayList<Song> getSongs() {
        return new ArrayList<Song>(songs);
    }

    // over riding to string to make sure it prints information
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder("Album: " + title + ", Artist: " + artist + ", Genre: " + genre + "\n");
        for (Song s : getSongs()) {
            out.append(s.getTitle()).append("\n");
        }
        return out.toString();
    }
}
