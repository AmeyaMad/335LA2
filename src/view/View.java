//file: View.java
//Author: Ameya Madhugiri & chatGPT
//purpose: This is the file used to accsess everything I hvae done before this
//it will allow the user to interact with both the Music Store and the User lib
//I will be heavily utilizing AI specifically chatGPT for this portion of the project
package view;

import model.*;

import java.util.Scanner;

/// UNDER HERE IT IS ALMOST ALL AI GEN, WILL ADD MY OWN COMMENTS AS WELL

/*
 * View.java
 *
 * This class provides a simple text-based UI for interacting with the music
 * system.
 * The user can navigate between the Music Store and User Library menus, search
 * for songs/albums,
 * manage playlists, rate songs, and more.
 */
public class View {
    private Scanner scanner; // Scanner object to handle user input
    private MusicStore ms; // MusicStore instance
    private LibraryModel lib;

    /*
     * Constructor for the View class.
     * Initializes the scanner for reading user input.
     */
    public View(MusicStore ms, LibraryModel lib) {
        scanner = new Scanner(System.in);
        this.ms = ms;
        this.lib = lib;
    }

    /*
     * Starts the main menu loop, allowing the user to navigate the system.
     */
    public void start() {
        while (true) {
            // Display main menu options
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. Access Music Store");
            System.out.println("2. Access User Library");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = getValidIntInput(); // Get valid integer input from user

            // Handle user selection
            switch (choice) {
                case 1:
                    showMusicStoreMenu();
                    break;
                case 2:
                    showUserLibraryMenu();
                    break;
                case 3:
                    System.out.println("Exiting... Goodbye!");
                    return; // Exit the program
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            }
        }
    }

    /*
     * Ensures the user inputs a valid integer.
     * If the input is not an integer, it will prompt the user until a valid input
     * is received.
     *
     * @return A valid integer entered by the user.
     */
    private int getValidIntInput() {
        while (!scanner.hasNextInt()) { // Check if input is a valid integer
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // Clear the invalid input
        }
        return scanner.nextInt(); // Return valid integer input
    }

    /// THIS SECTION DEALS WITH OTHER VIEWS, ABOVE IS MAIN MENU AND BELOW IS
    ///
    /// MUSIC STORE & RELATED
    /*
     * Displays the Music Store Menu.
     * The user will be able to search for songs/albums in the store.
     * (Functionality to be implemented in the next step)
     */
    /**
     * Displays the Music Store Menu.
     * The user can search for songs/albums in the store.
     */
    private void showMusicStoreMenu() {
        while (true) {
            System.out.println("\n=== Music Store Menu ===");
            System.out.println("1. Search for Song by Title");
            System.out.println("2. Search for Song by Artist");
            System.out.println("3. Search for Album by Title");
            System.out.println("4. Search for Album by Artist");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = getValidIntInput();

            switch (choice) {
                case 1:
                    searchSongByTitle();
                    break;
                case 2:
                    searchSongByArtist();
                    break;
                case 3:
                    searchAlbumByTitle();
                    break;
                case 4:
                    searchAlbumByArtist();
                    break;
                case 5:
                    return; // Goes back to the Main Menu
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }

    /**
     * Searches for a song in the music store by title.
     */
    private void searchSongByTitle() {
        System.out.print("\nEnter song title: ");
        scanner.nextLine(); // Consume newline
        String title = scanner.nextLine();

        String result = ms.getSongsByTitleString(title); // Using `ms` instead of `musicStore`
        System.out.println(result);
    }

    /**
     * Searches for all songs in the music store by a specific artist.
     */
    private void searchSongByArtist() {
        System.out.print("\nEnter artist name: ");
        scanner.nextLine(); // Consume newline
        String artist = scanner.nextLine();

        String result = ms.getSongsByArtistString(artist);
        System.out.println(result);
    }

    /**
     * Searches for an album in the music store by title.
     */
    private void searchAlbumByTitle() {
        System.out.print("\nEnter album title: ");
        scanner.nextLine(); // Consume newline
        String title = scanner.nextLine();

        String result = ms.getAlbumsByTitleString(title);
        System.out.println(result);
    }

    /**
     * Searches for all albums in the music store by a specific artist.
     */
    private void searchAlbumByArtist() {
        System.out.print("\nEnter artist name: ");
        scanner.nextLine(); // Consume newline
        String artist = scanner.nextLine();

        String result = ms.getAlbumsByArtistString(artist);
        System.out.println(result);
    }

    /// USER LIBRARY & RELATED
    ///
    ///
    ///
    /*
     * Displays the User Library Menu.
     * The user will be able to manage their personal music library, playlists,
     * favorites, etc.
     * (Functionality to be implemented in the next step)
     */
    private void showUserLibraryMenu() {
        while (true) {
            System.out.println("\n=== User Library Menu ===");
            System.out.println("1. Search for Song by Title");
            System.out.println("2. Search for Song by Artist");
            System.out.println("3. Search for Album by Title");
            System.out.println("4. Search for Album by Artist");
            System.out.println("5. Add Song to Library");
            System.out.println("6. Add Album to Library");
            System.out.println("7. List All Songs");
            System.out.println("8. List All Albums");
            System.out.println("9. List All Artists");
            System.out.println("10. List Favorite Songs");
            System.out.println("11. Create & Manage Playlists");
            System.out.println("12. Rate a Song / Mark as Favorite");
            System.out.println("13. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = getValidIntInput();

            switch (choice) {
                case 1:
                    searchLibrarySongByTitle();
                    break;
                case 2:
                    searchLibrarySongByArtist();
                    break;
                case 3:
                    searchLibraryAlbumByTitle();
                    break;
                case 4:
                    searchLibraryAlbumByArtist();
                    break;
                case 5:
                    addSongToLibrary();
                    break;
                case 6:
                    addAlbumToLibrary();
                    break;
                case 7:
                    listAllLibrarySongs();
                    break;
                case 8:
                    listAllLibraryAlbums();
                    break;
                case 9:
                    listAllLibraryArtists();
                    break;
                case 10:
                    listAllFavoriteSongs();
                    break;
                case 11:
                    managePlaylists();
                    break;
                case 12:
                    rateOrFavoriteSong();
                    break;
                case 13:
                    return; // Go back to the main menu
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 13.");
            }
        }
    }

    // SEARCHING

    private void searchLibrarySongByTitle() {
        System.out.print("\nEnter song title: ");
        scanner.nextLine(); // Consume newline
        String title = scanner.nextLine();

        String result = lib.getSongsByTitle(title);
        System.out.println(result);
    }

    private void searchLibrarySongByArtist() {
        System.out.print("\nEnter artist name: ");
        scanner.nextLine(); // Consume newline
        String artist = scanner.nextLine();

        String result = lib.getSongsByArtist(artist);
        System.out.println(result);
    }

    private void searchLibraryAlbumByTitle() {
        System.out.print("\nEnter album title: ");
        scanner.nextLine(); // Consume newline
        String title = scanner.nextLine();

        String result = lib.getAlbumsByTitleString(title);
        System.out.println(result);
    }

    private void searchLibraryAlbumByArtist() {
        System.out.print("\nEnter artist name: ");
        scanner.nextLine(); // Consume newline
        String artist = scanner.nextLine();

        String result = lib.getAlbumsByArtistString(artist);
        System.out.println(result);
    }

    private void searchPlaylistByName() {
        System.out.print("\nEnter playlist name: ");
        scanner.nextLine(); // Consume newline
        String playlistName = scanner.nextLine();

        String result = lib.getPlaylistByNameString(playlistName);
        System.out.println(result);
    }

    // ADDIG
    private void addSongToLibrary() {
        System.out.print("\nEnter song title: ");
        scanner.nextLine(); // Consume newline
        String title = scanner.nextLine();

        System.out.print("Enter artist name: ");
        String artist = scanner.nextLine();

        // Call the LibraryModel function
        String result = lib.addSong(title, artist);

        // Print the result message
        System.out.println(result);
    }

    private void addAlbumToLibrary() {
        System.out.print("\nEnter album title: ");
        scanner.nextLine(); // Consume newline
        String title = scanner.nextLine();

        // Call the LibraryModel function
        boolean success = lib.addAlbumToLibrary(title);

        // Print the result message
        if (success) {
            System.out.println("Success: '" + title + "' has been added to your library!");
        } else {
            System.out.println("Error: The album '" + title + "' is not available in the Music Store.");
        }
    }

    /**
     * Lists all songs in the user's library.
     */
    private void listAllLibrarySongs() {
        System.out.println("\n=== Songs in Library ===");
        System.out.println(lib.listAllSongsString());
    }

    /**
     * Lists all albums in the user's library.
     */
    private void listAllLibraryAlbums() {
        System.out.println("\n=== Albums in Library ===");
        System.out.println(lib.listAllAlbumsString());
    }

    /**
     * Lists all artists in the user's library.
     */
    private void listAllLibraryArtists() {
        System.out.println(lib.listAllArtistsString());
    }

    /**
     * Lists all playlists in the user's library.
     */

    /**
     * Lists all favorite songs in the user's library.
     */
    // TODO
    private void listAllFavoriteSongs() {
        System.out.println("\n=== Favorite Songs ===");
        System.out.println(lib.getFavoritesString());
    }

    /// EMPTY SO I CAN TEST

    /**
     * Allows the user to list songs, albums, artists, playlists, or favorite songs.
     */

    /**
     * Allows the user to create a new playlist and manage existing playlists.
     */
    private void managePlaylists() {
        while (true) {
            System.out.println("\n=== Playlist Management ===");
            System.out.println("1. Create a Playlist");
            System.out.println("2. Add a Song to a Playlist");
            System.out.println("3. Remove a Song from a Playlist");
            System.out.println("4. List All Playlists"); // ✅ New option
            System.out.println("5. View Songs in a Playlist");
            System.out.println("6. Back to User Library Menu");
            System.out.print("Enter your choice: ");

            int choice = getValidIntInput();

            switch (choice) {
                case 1:
                    createPlaylist();
                    break;
                case 2:
                    addSongToPlaylist();
                    break;
                case 3:
                    removeSongFromPlaylist();
                    break;
                case 4:
                    listAllLibraryPlaylists();
                    break; // Calls new function
                case 5:
                    listSongsInPlaylist();
                    break;
                case 6:
                    return; // Goes back to User Library Menu
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }
    }

    /**
     * Creates a new playlist with a user-provided name.
     */
    private void createPlaylist() {
        System.out.print("\nEnter a name for the new playlist: ");
        scanner.nextLine(); // Consume newline
        String playlistName = scanner.nextLine();

        // Call the LibraryModel function to create the playlist
        lib.createPlaylist(playlistName);

        System.out.println("Success: Playlist '" + playlistName + "' has been created!");
    }

    /**
     * Adds a song to an existing playlist.
     */
    private void addSongToPlaylist() {
        System.out.print("\nEnter the name of the playlist: ");
        scanner.nextLine(); // Consume newline
        String playlistName = scanner.nextLine();

        System.out.print("Enter the song title: ");
        String songTitle = scanner.nextLine();

        System.out.print("Enter the artist name: ");
        String artistName = scanner.nextLine();

        // Call the LibraryModel function
        String result = lib.addSongToPlaylist(songTitle, artistName, playlistName);

        // Display the result message
        System.out.println(result);
    }

    /**
     * Removes a song from an existing playlist.
     */
    private void removeSongFromPlaylist() {
        System.out.print("\nEnter the name of the playlist: ");
        scanner.nextLine(); // Consume newline
        String playlistName = scanner.nextLine();

        System.out.print("Enter the song title: ");
        String songTitle = scanner.nextLine();

        System.out.print("Enter the artist name: ");
        String artistName = scanner.nextLine();

        // Call the LibraryModel function
        lib.removeSongFromPlaylist(songTitle, artistName, playlistName);

        System.out.println(
                "Attempted to remove '" + songTitle + "' by " + artistName + " from playlist '" + playlistName + "'.");
    }

    private void listSongsInPlaylist() {
        System.out.print("\nEnter playlist name: ");
        scanner.nextLine(); // Consume newline
        String playlistName = scanner.nextLine();

        String result = lib.getPlaylistByNameString(playlistName);
        System.out.println(result);
    }

    /**
     * Lists all playlists in the user's library.
     */
    private void listAllLibraryPlaylists() {
        System.out.println("\n=== Playlists in Library ===");
        System.out.println(lib.listAllPlaylistsString());
    }

    /**
     * Allows the user to rate a song and mark it as a favorite if rated 5 stars.
     */
    private void rateOrFavoriteSong() {
        while (true) {
            System.out.println("\n=== Rate a Song / Mark as Favorite ===");
            System.out.println("1. Rate a Song");
            System.out.println("2. Mark a Song as Favorite");
            System.out.println("3. Back to User Library Menu");
            System.out.print("Enter your choice: ");

            int choice = getValidIntInput();

            switch (choice) {
                case 1:
                    rateSong();
                    break;
                case 2:
                    markSongAsFavorite();
                    break;
                case 3:
                    return; // Goes back to User Library Menu
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            }
        }
    }

    /**
     * Allows the user to rate a song from 1 to 5 stars.
     */
    private void rateSong() {
        System.out.print("\nEnter song title: ");
        scanner.nextLine(); // Consume newline
        String title = scanner.nextLine();

        System.out.print("Enter artist name: ");
        String artist = scanner.nextLine();

        System.out.print("Enter rating (1-5): ");
        int ratingValue = getValidIntInput();

        // Ensure rating is within range
        if (ratingValue < 1 || ratingValue > 5) {
            System.out.println("Invalid rating. Please enter a number between 1 and 5.");
            return;
        }

        // Convert to Rating enum
        Rating rating = Rating.values()[ratingValue - 1]; // Assuming Rating enum is 0-indexed

        // Call the LibraryModel function
        String result = lib.rateSong(title, artist, rating);

        // Print the result message
        System.out.println(result);
    }

    /**
     * Allows the user to manually mark a song as a favorite.
     */
    private void markSongAsFavorite() {
        System.out.print("\nEnter song title: ");
        scanner.nextLine(); // Consume newline
        String title = scanner.nextLine();

        System.out.print("Enter artist name: ");
        String artist = scanner.nextLine();

        // Call the LibraryModel function
        String result = lib.addSongToFavorites(title, artist);

        // Print the result message
        System.out.println(result);
    }

}
