//file: UserLibraryPlaylists.java
//author: Ameya Madhugiri
//purpose: To make sure LibraryModel.java is not a god class I am splitting classes up. this will
// handle the stuff from LA1 (Playlists)

package model.userLibModels;

import model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserLibraryPlaylists {
    private Map<String, PlayList> playlistByName; // changed to use a hash map since it makes more sense
    private PlayList mostRecent;
    private PlayList mostFrequent;
    UserLibrarySongs userLibrarySongs;


    public UserLibraryPlaylists(UserLibrarySongs u) {
        userLibrarySongs = u;
        playlistByName = new HashMap<>();
        mostRecent = new PlayList("Most Recent");
        mostFrequent = new PlayList("Most Frequent");
    }

    // this will create a playlist in the music library
    // @pre name != null && playlistsByName.contains(name) == false
    public void createPlaylist(String name) {
        playlistByName.put(name, new PlayList(name));
    }



    // this function allows us to add a song to our playlist within our library
    // @pre title != null && artist != null && playlistName != null
    public String addSongToPlaylist(String title, String artist, String playlistName) {
        PlayList p = playlistByName.get(playlistName);
        if (p == null) {
            return "Playlist Not Found";
        }

        Song s = HelperFunctions.getSongByTitleAndArtist(title, artist);
        if (s == null) {
            return "Song Not Found";
        }

        p.addSong(s);
        return "Song added to Playlist Successfully";
    }

    // this function allows us to remove a song from within a playlist that is in
    // our library
    // @pre title != null && artist != null && playlistName != null
    public void removeSongFromPlaylist(String title, String artist, String playlistName) {
        PlayList p = playlistByName.get(playlistName);
        if (p == null) {
            return;
        }
        Song s = HelperFunctions.getSongByTitleAndArtist(title, artist);
        if (s == null) {
            return;
        }
        p.removeSong(s);
    }

    // adding in playlist function
    // @pre name != null
    public PlayList getPlaylistByName(String name) {
        return playlistByName.get(name);
    }

    // adding in playlist function for string
    // @pre title != null
    public String playlistByNameToString(String title) {
        if (getPlaylistByName(title) == null) {
            return "There are no playlists by this title\n";
        }
        if(getPlaylistByName(title).isEmpty()){
            return "Playlist is Empty";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("=== Playlist: ").append(title).append(" ===\n");
        for (Song s : getPlaylistByName(title).getSongs()) {
            sb.append(s.toString()).append("\n");
        }
        return sb.toString();
    }

    // returns a string off all playlists
    public String allPlaylistsToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Playlists List ===\n");
        for (PlayList playlist : playlistByName.values()) {
            sb.append("Playlist: ");
            sb.append(playlist.getName());
            sb.append("\n");
        }
        return sb.toString();
    }

    //MOST RECENT

    public void addToMostRecent(Song s){
        mostRecent.addSong(s);
        if(mostRecent.size() > 10){
            mostRecent.trim();
        }
    }

    public String mostRecentToString() {

        return "=== Most Recent Songs ===\n" + mostRecent.toString();
    }


    //MOST FREQUENT

    public void updateMostFrequent(){
        mostFrequent = new PlayList("Most Frequent", userLibrarySongs.get10MostPlayed());
    }

    public String mostFrequentToString(){
        updateMostFrequent();
        return "==== Most Frequent Songs ===\n" + mostFrequent.toString();
    }
}
