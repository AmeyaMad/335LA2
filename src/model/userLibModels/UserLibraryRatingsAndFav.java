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



}
