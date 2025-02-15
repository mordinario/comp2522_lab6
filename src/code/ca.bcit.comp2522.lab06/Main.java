package ca.bcit.comp2522.lab06;

public class Main
{
    public static void main(final String[] args)
    {
        final BookStore<Literature> store;
        store = new BookStore<>("Bookpilled Storemaxxer");
        store.addBook(new Novel("don Quixote", "Miguel de Cervantes", 1605));
        store.addBook(new ComicBook("Spider-Man", 2000));
        store.addBook(new Magazine("National Geographic", 2000));
        store.printBookList(); // Should print titles from different item types
    }
}
