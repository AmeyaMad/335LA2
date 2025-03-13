//  file: MusicStore.java
//  Author: Ameya Madhugiri
//  Purpose: The purpose of this class is to be a sort of global library of all known songs
//              (all songs input in albums folder) that we can pull from
package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class MusicStore {
    // Setting up instance variables (will only create one MusicStore obj however)

    // using arraylist as there could be multiple songs with the same title
    private HashMap<String, ArrayList<Song>> songsByTitle;

    // using arraylist as there could be multiple songs by the same artist
    private HashMap<String, ArrayList<Song>> songsByArtist;

    // using arraylist as there could be multiple albums with the same title
    private HashMap<String, ArrayList<Album>> albumsByTitle;

    // using arraylist as there could be multiple albums by the same artist
    private HashMap<String, ArrayList<Album>> albumsByArtist;

    // constructor to create MusicStore that takes in the name of a directory and
    // creates maps so we
    // can look them up easily later on
    // @pre dirName != null
    public MusicStore(String dirName) {
        // initializing our hashmaps
        songsByTitle = new HashMap<>();
        songsByArtist = new HashMap<>();
        albumsByTitle = new HashMap<>();
        albumsByArtist = new HashMap<>();

        // setting up file reading
        File dir = new File(dirName);
        File[] files = dir.listFiles();

        // based on spec we can assume the dir exists and contains things
        for (int i = 0; i < files.length; i++) {
            // dont try to create albums based on the albums.txt file
            if (files[i].getName().equals("albums.txt")) {
                continue;
            }
            // making an ArrayList of Song for each file in dir
            ArrayList<Song> songs = new ArrayList<>();

            // to use FileReader need to use a try catch block
            try (BufferedReader curFile = new BufferedReader(new FileReader("albums/" + files[i].getName()))) {
                String line = curFile.readLine(); // reading first line with album info
                String[] albumInfo = line.split(","); // separating first line so we can use info

                // albumInfo = [album, artist, genre, year (not used)]

                /*
                 * because none of the objects are public and are not being passed in by client
                 * code we do not
                 * need to worry about escaping references
                 */

                // while loop to add every song in an arraylist
                while ((line = curFile.readLine()) != null) {
                    Song tmp = new Song(line, albumInfo[1], albumInfo[0]);
                    songs.add(tmp);

                    // this will add the songs to our songsByTitle map
                    if (songsByTitle.containsKey(line)) {
                        songsByTitle.get(line).add(tmp);
                    } else {
                        ArrayList<Song> tmpSongs = new ArrayList<>();
                        tmpSongs.add(tmp);
                        songsByTitle.put(line, tmpSongs);
                    }

                    // this will add the songs to our songsByArtist map
                    if (songsByArtist.containsKey(albumInfo[1])) {
                        songsByArtist.get(albumInfo[1]).add(tmp);
                    } else {
                        ArrayList<Song> tmpSongs = new ArrayList<>();
                        tmpSongs.add(tmp);
                        songsByArtist.put(albumInfo[1], tmpSongs);
                    }
                }
                // after our while loops is done we have an ArrayList of all the songs, we can
                // create our Album
                Album curAlbum = new Album(albumInfo[0], albumInfo[1], albumInfo[2], songs);

                // we will now add these to our albumBy___ maps

                // this will add the album to our albumsByTitle map
                // looks like in our test there is no albums with the same title, so not covered
                // however good to keep incase
                if (albumsByTitle.containsKey(albumInfo[0])) {
                    albumsByTitle.get(albumInfo[0]).add(curAlbum);
                } else {
                    ArrayList<Album> tmpAlbums = new ArrayList<>();
                    tmpAlbums.add(curAlbum);
                    albumsByTitle.put(albumInfo[0], tmpAlbums);
                }

                // this will add the album to our albumsByArtist map
                if (albumsByArtist.containsKey(albumInfo[1])) {
                    albumsByArtist.get(albumInfo[1]).add(curAlbum);
                } else {
                    ArrayList<Album> tmpAlbums = new ArrayList<>();
                    tmpAlbums.add(curAlbum);
                    albumsByArtist.put(albumInfo[1], tmpAlbums);
                }
                // should never happen since we are promised in the spec that there will be
                // files
            } catch (Exception e) {
                System.err.println(e);
            }
        }

    }

    // now making getter functions for all the maps

    // gets arraylist of song by title
    // @pre title != null
    public ArrayList<Song> getSongsByTitle(String title) {
        if (songsByTitle.containsKey(title)) {
            return new ArrayList<Song>(songsByTitle.get(title));
        } else {
            return null;
        }
    }

    // gets arraylist of song by artist
    // @pre artist != null
    public ArrayList<Song> getSongsByArtist(String artist) {
        if (songsByArtist.containsKey(artist)) {
            return new ArrayList<Song>(songsByArtist.get(artist));
        } else {
            return null;
        }

    }

    // gets arraylist of albums by title
    // @pre title != null
    public ArrayList<Album> getAlbumsByTitle(String title) {
        if (albumsByTitle.containsKey(title)) {
            return new ArrayList<Album>(albumsByTitle.get(title));
        } else {
            return null;
        }
    }

    // gets arraylsit of albums by artist
    // @pre artist != null
    public ArrayList<Album> getAlbumsByArtist(String artist) {
        if (albumsByArtist.containsKey(artist)) {
            return new ArrayList<Album>(albumsByArtist.get(artist));
        } else {
            return null;
        }
    }

    // making a functions that returns string in a clean format bc default
    // ArrayList.toString() stinks!

    // string for songs by title
    // @pre title != null
    public String getSongsByTitleString(String title) {
        if (getSongsByTitle(title) == null) {
            return "This Song is not in the songs list";
        }
        StringBuilder sb = new StringBuilder();
        for (Song s : getSongsByTitle(title)) {
            sb.append(s.toString()).append("\n");
        }
        return sb.toString();
    }

    // string for songs by artist
    // @pre artist != null
    public String getSongsByArtistString(String artist) {
        if (getSongsByArtist(artist) == null) {
            return "There are no songs by this artist";
        }
        StringBuilder sb = new StringBuilder();
        for (Song s : getSongsByArtist(artist)) {
            sb.append(s.toString()).append("\n");
        }
        return sb.toString();
    }

    // string for albums by title
    // @pre title != null
    public String getAlbumsByTitleString(String title) {
        if (getAlbumsByTitle(title) == null) {
            return "There are no albums of this name";
        }
        StringBuilder sb = new StringBuilder();
        for (Album a : getAlbumsByTitle(title)) {
            sb.append(a.toString()).append("\n");
        }
        return sb.toString();
    }

    // string for albums by artist
    // @pre artist != null
    public String getAlbumsByArtistString(String artist) {
        if (getAlbumsByArtist(artist) == null) {
            return "There are no albums by this artist";
        }
        StringBuilder sb = new StringBuilder();
        for (Album a : getAlbumsByArtist(artist)) {
            sb.append(a.toString()).append("\n");
        }
        return sb.toString();
    }
}
