//file: LibraryModel.java
// Author: Ameya Madhugiri
// Purpose: The purpose of this class is to represent a users library of music, it is similar to the MusicStore, except
//          what is contained in there is defined by the user

package model;

import model.userLibModels.UserLibraryAlbums;
import model.userLibModels.UserLibraryRatingsAndFav;
import model.userLibModels.UserLibrarySongs;
import model.userLibModels.UserLibraryPlaylists;

import java.util.ArrayList;
import java.util.Collections;

public class LibraryModel {
    // Setting up Instance Variables... will be similar to MusicStore
    // using hashmpas so it is easy to lookup

    private UserLibrarySongs userLibrarySongs;
    private UserLibraryAlbums userLibraryAlbums;
    private UserLibraryPlaylists userLibraryPlaylists;
    private UserLibraryRatingsAndFav userLibraryRatingsAndFav;

    // we need an instance of musicstore
    private MusicStore musicStore;

    // new list of most recently played songs
    private ArrayList<Song> mostRecentSongs;

    private ArrayList<Song> mostFrequentSongs;

    // constructor will create an empty LibraryModel
    public LibraryModel() {
        userLibrarySongs = new UserLibrarySongs();
        userLibraryAlbums = new UserLibraryAlbums(userLibrarySongs);
        userLibraryPlaylists = new UserLibraryPlaylists(userLibrarySongs);
        userLibraryRatingsAndFav = new UserLibraryRatingsAndFav();
    }

    /*
     * ===================================================================
     * ===================================================================
     * 
     * Song section
     * 
     * ===================================================================
     * ===================================================================
     */

    // adding a song to the library
    // @pre title != null && artist != null
    public String addSong(String title, String artist) {
        Song sWeWant = HelperFunctions.getSongByTitleAndArtist(title, artist);
        if (sWeWant == null) {
            return "This song is not in the Music Store\n";
        }

        return userLibrarySongs.addSongToLibrary(sWeWant);
    }

    // prints all songs with the same title
    // @pre title != null
    public String getSongsByTitle(String title) {
        return userLibrarySongs.songsByTitleToString(title);
    }

    // prints all songs from same artist
    // @pre artist != null
    public String getSongsByArtist(String artist) {
        return userLibrarySongs.songsByArtistToString(artist);
    }


    public String listAllSongs() {
        return userLibrarySongs.allSongToString();
    }

    public String listAllArtists() {
        return userLibrarySongs.listAllArtistsToString();
    }

    /*
     * ===================================================================
     * ===================================================================
     * 
     * Album section
     * 
     * ===================================================================
     * ===================================================================
     */

    // for an album: print the album information and a list of the songs in the
    // appropriate order
    // @pre title != null
    public String getAlbumsByTitle(String album) {
        return userLibraryAlbums.albumsByTitleToString(album);
    }

    // prints all albums by an artist
    // @pre artist != null
    public String getAlbumsByArtist(String artist) {
        return userLibraryAlbums.albumsByArtistToString(artist);
    }

    // adds album to library
    // @pre album != null
    public boolean addAlbumToLibrary(String album) {
        return userLibraryAlbums.addAlbumToLibrary(album);
    }

    // returns a string of all the albums formatted nicely
    public String listAllAlbums() {
        return userLibraryAlbums.allAlbumsToString();
    }



    /*
     * ===================================================================
     * ===================================================================
     * 
     * Playlist section
     * 
     * ===================================================================
     * ===================================================================
     */

    // this will create a playlist in the music library
    // @pre name != null
    public void createPlaylist(String name) {
        userLibraryPlaylists.createPlaylist(name);
    }

    // this function allows us to add a song to our playlist within our library
    // @pre title != null && artist != null && playlistName != null
    public String addSongToPlaylist(String title, String artist, String playlistName) {
        return userLibraryPlaylists.addSongToPlaylist(title, artist, playlistName);
    }

    // this function allows us to remove a song from within a playlist that is in
    // our library
    // @pre title != null && artist != null && playlistName != null
    public void removeSongFromPlaylist(String title, String artist, String playlistName) {
        userLibraryPlaylists.removeSongFromPlaylist(title, artist, playlistName);
    }

    // prints out details of playlists based on their name
    // @pre title != null
    public String playlistByToNameString(String title) {
        return userLibraryPlaylists.playlistByNameToString(title);
    }

    // returns a list og all playlists
    public String listAllPlaylists() {
        return userLibraryPlaylists.allPlaylistsToString();
    }

    /*
     * ===================================================================
     * ===================================================================
     * 
     * Favorites and Ratings section
     * 
     * ===================================================================
     * ===================================================================
     */

    // this will be used to add songs to favorite
    // @pre title != null && artist != null
    public String addSongToFavorites(String title, String artist) {
        return userLibraryRatingsAndFav.addSongToFavorites(title, artist);
    }

    // returns all the favorites in a nice format
    public String getFavoritesToString() {
        return userLibraryRatingsAndFav.getFavoritesToString();
    }

    // this function will take in details of a song and set its rating,
    // if the rating is 5 it will automatically get placed into the favorites
    // @pre title != null && artist != null && rating != null
    public String rateSong(String title, String artist, Rating rating) {
        userLibrarySongs.rateSong(title, artist, rating);
        return userLibraryRatingsAndFav.rateSong(title, artist, rating);
    }

    // returns a nice string with all songs of that rating
    // @pre r != null
    public String getSongsByRatingString(Rating r) {
        return userLibraryRatingsAndFav.getSongsByRatingString(r);
    }

    //adds a song to the 10 most recent
    //todo

    /*
     * ===================================================================
     * ===================================================================
     *
     * New Functionality: Playing songs & Automatic Playlists
     *
     * ===================================================================
     * ===================================================================
     */
    //plays a song
    //@pre title != null && artist != null
    public String playSong(String title, String artist) {
       Song sWeWant = HelperFunctions.getSongByTitleAndArtist(title, artist);
       if (sWeWant == null) {
           return "This song is not in the Music Store\n";
       }
       userLibraryPlaylists.addToMostRecent(sWeWant);
       return userLibrarySongs.playSong(title, artist);
    }

    //gets most freq songs
    public String mostFrequentToString() {
        return userLibraryPlaylists.mostFrequentToString();
    }

    public String mostRecentToString() {
        return userLibraryPlaylists.mostRecentToString();
    }

    /*
     * ===================================================================
     * ===================================================================
     *
     * New Functionality: Printing all songs based on user Query
     *
     * ===================================================================
     * ===================================================================
     */

    public String allSongsByComparison(int byWhat) {
        ArrayList<Song> allSongs = userLibrarySongs.getAllSongs();

        switch (byWhat) {
            case 1:
                allSongs.sort(Song.titleFirstComparator());
                break;
            case 2:
                allSongs.sort(Song.artistFirstComparator());
                break;
            case 3:
                allSongs.sort(Song.ratingFirstComparator());
                break;
            default:
                return "Invalid option. Use 1 for title, 2 for artist, 3 for rating.\n";
        }
        PlayList tmp = new PlayList("Tmp", allSongs);
        return tmp.toString();
    }

    /*
     * ===================================================================
     * ===================================================================
     *
     * New Functionality: Removing songs and albums from lib
     *
     * ===================================================================
     * ===================================================================
     */

    // removes a song from an album (used when removing specific songs from library)
    // @pre title != null && artist != null
    public void removeSongFromLibrary(String title, String artist) {
        userLibrarySongs.removeSongFromLibrary(title, artist);
        userLibraryAlbums.removeSongFromAlbums(title, artist);
    }

    //removes an entire album from library
    //@pre title != null
    public void removeAlbumFromLibrary(String title) {
        //todo fill in
        userLibraryAlbums.removeAlbumFromLibrary(title);
    }

    /*
     * ===================================================================
     * ===================================================================
     *
     * New Functionality: Shuffling Library and Playlists
     *
     * ===================================================================
     * ===================================================================
     */

    //returns a shuffled list of all songs, based on spec doesnt seem like we need
    //to have a way to play these or anything, just shuffle them
    // a but confused because it would make sense to be able to "play" a playlist
    //or all songs in the library but that is not specified.
    // TODO mention not sure how to test this in vide
    public String shuffleLibraryToString(){
        ArrayList<Song> songs = userLibrarySongs.getAllSongs();
        Collections.shuffle(songs);
        StringBuilder builder = new StringBuilder();
        for (Song song : songs) {
            builder.append(song).append("\n");
        }
        return builder.toString();
    }

    public String shufflePlaylistToString(String playlistName) {
        PlayList p = userLibraryPlaylists.getPlaylistByName(playlistName);
        p.shuffle();
        return p.toString();
    }
}
