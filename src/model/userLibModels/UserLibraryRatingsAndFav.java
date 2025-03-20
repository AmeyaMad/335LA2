//file: UserLibraryPlaylists.java
//author: Ameya Madhugiri
//purpose: To make sure LibraryModel.java is not a god class I am splitting classes up. this will
// handle the stuff from LA1 (Ratings and favorites)

package model.userLibModels;

import model.*;

import java.util.ArrayList;
import java.util.HashMap;

public class UserLibraryRatingsAndFav {
    //list of all favorite songs
    private ArrayList<Song> favoriteSongs;

    //hashmap of all songs by ratings, so we can print them easily
    private HashMap<Rating, ArrayList<Song>> songsByRating;


    public UserLibraryRatingsAndFav() {
        favoriteSongs = new ArrayList<>();
        songsByRating = new HashMap<>();
    }

    // this will be used to add songs to favorite
    // @pre title != null && artist != null
    public String addSongToFavorites(String title, String artist) {
        // using helper function
        Song sWeWant = HelperFunctions.getSongByTitleAndArtist(title, artist);
        if (sWeWant == null) {
            return "There is no song with this name and by this Artist\n";
        }

        // making sure it is not already on our list
        if (favoriteSongs.contains(sWeWant)) {
            return "This song is already in the favorites list\n";
        } else {
            favoriteSongs.add(sWeWant);
        }

        // set rating to 5 if it is not already
        if (songsByRating.containsKey(Rating.FIVE) && songsByRating.get(Rating.FIVE).contains(sWeWant)) {
            return "This song is already rated 5 and in the favorites list.\n";
        }

        rateSong(title, artist, Rating.FIVE);
        // if it hasnt returned by now we know it worked
        return "Successfully added song to the favorites list\n";
    }

    // this will return an arraylist of all favorites
    private ArrayList<Song> getFavorites() {
        if (favoriteSongs.isEmpty()) {
            return null;
        } else {
            return new ArrayList<>(favoriteSongs);
        }
    }

    // this is the more useful function as it returns all the favorite songs in a
    // nice format
    public String getFavoritesToString() {
        if (getFavorites() == null) {
            return "There are no favorites\n";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("=== Favorites ===\n");
        for (Song s : favoriteSongs) {
            sb.append(s.toString()).append("\n");
        }
        return sb.toString();
    }


    // this function will take in details of a song and set its rating,
    // if the rating is 5 it will automatically get placed into the favorites
    // @pre title != null && artist != null && rating != null
    public String rateSong(String title, String artist, Rating rating) {
        Song sWeWant = HelperFunctions.getSongByTitleAndArtist(title, artist);
        if (sWeWant == null) {
            return "There is no song that has this name by this artist\n";
        }
        // first set the rating to input
        sWeWant.setStars(rating);

        // now add to our songs by rating hashmap
        if (songsByRating.containsKey(rating)) {
            songsByRating.get(rating).add(new Song(sWeWant)); // to keep encapsulation
        } else {
            ArrayList<Song> tmp = new ArrayList<>();
            tmp.add(new Song(sWeWant));
            songsByRating.put(rating, tmp); // create a new arraylist with new song obj to add to HashMap
        }

        // if rating == five automatically place in fav
        if (rating == Rating.FIVE && !favoriteSongs.contains(sWeWant)) {
            addSongToFavorites(title, artist);
        }
        return "Successfully rated song\n";
    }

    // This function i dont think is required by the spec but makes sense if setting
    // ratings
    // @pre r != null
    private ArrayList<Song> getSongsByRating(Rating r) {
        if (songsByRating.containsKey(r)) {
            return new ArrayList<Song>(songsByRating.get(r));
        } else {
            return null;
        }
    }

    // this function takes in a rating and returns a nice strng with all songs of
    // that rating
    // @pre r != null
    public String getSongsByRatingString(Rating r) {
        if (getSongsByRating(r) == null) {
            return "There are no songs of this rating\n";
        }
        ArrayList<Song> songs = songsByRating.get(r);
        StringBuilder sb = new StringBuilder();
        sb.append("=== Songs of Rating " + r + " ===\n");
        for (Song s : songs) {
            sb.append(s.toString() + "\n");
        }
        return sb.toString();
    }

}
