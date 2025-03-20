//file: UserLibraryPlaylists.java
//author: Ameya Madhugiri
//purpose: To make sure LibraryModel.java is not a god class I am splitting classes up. this will
// handle the stuff from LA1 (Playlists)

package model.userLibModels;

import model.*;

import java.util.ArrayList;

public class UserLibraryPlaylists {
    private ArrayList<PlayList> playlistByName; // using an arraylist to store playlists

    public UserLibraryPlaylists() {
        playlistByName = new ArrayList<>();
    }

    // this will create a playlist in the music library
    // @pre name != null
    public void createPlaylist(String name) {
        playlistByName.add(new PlayList(name));
    }

    // this function allows us to add a song to our playlist within our library
    // @pre title != null && artist != null && playlistName != null
    public String addSongToPlaylist(String title, String artist, String playlistName) {
        PlayList p = null;
        for (PlayList playlist : playlistByName) {
            if (playlist.getName().equals(playlistName)) {
                p = playlist;
            }
        }
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
        PlayList p = null;
        for (PlayList playlist : playlistByName) {
            if (playlist.getName().equals(playlistName)) {
                p = playlist;
            }
        }
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
    private PlayList getPlaylistByName(String name) {
        PlayList out = null;
        for (PlayList playlist : playlistByName) {
            if (playlist.getName().equals(name)) {
                out = playlist;
            }
        }
        return out;
    }

    // adding in playlist function for string
    // @pre title != null
    public String playlistByNameToString(String title) {
        if (getPlaylistByName(title) == null) {
            return "There are no playlists by this title\n";
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
        for (PlayList playlist : playlistByName) {
            sb.append("Playlist: ");
            sb.append(playlist.getName());
            sb.append("\n");
        }
        return sb.toString();
    }
}
