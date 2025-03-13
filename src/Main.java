import model.*;
import view.*;

public class Main {
    public static void main(String[] args) {
        MusicStore ms = new MusicStore("albums");
        LibraryModel lib = new LibraryModel(ms);
        View view = new View(ms, lib); // Create and start the View
        view.start();
    }
}
