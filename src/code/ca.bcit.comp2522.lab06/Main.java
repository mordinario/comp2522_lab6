package ca.bcit.comp2522.lab06;

public class Main
{
    public static void main(final String[] args)
    {
        final BookStore<Literature> store;
        store = new BookStore<>("The Bookstore");
        store.addBook(new Novel("War and Peace"));
        store.addBook(new ComicBook("Spider-Man"));
        store.addBook(new Magazine("National Geographic"));
        store.printBookList(); // Should print titles from different item types
    }
}
