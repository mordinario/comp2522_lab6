package ca.bcit.comp2522.lab06;

import java.util.*;

/**
 * The {@code BookStore} class represents a bookstore that manages
 * a collection of Novel objects. It provides functionality for
 * populating the store with novels, printing and querying novel titles,
 * and performing various operations such as sorting, filtering, and
 * statistical calculations on the collection.
 *
 * <p>This class internally maintains a {@code List} of novels as well as
 * a {@code Map} that associates each novel's title (as the key) with its
 * corresponding {@code Novel} object. The map is used for efficient lookup
 * and manipulation of novels, such as the removal of novels whose titles
 * contain the substring "the" (ignoring case).
 *
 * <p>Public methods are provided to:
 * <ul>
 *   <li>Print all novel titles in uppercase.</li>
 *   <li>Print novel titles that contain a specified
 *       substring (case-insensitive).</li>
 *   <li>Print novel titles in alphabetical order.</li>
 *   <li>Print novels grouped by a specific decade.</li>
 *   <li>Retrieve and print the novel with the longest title.</li>
 *   <li>Determine whether a novel was written in a specific year.</li>
 *   <li>Count how many novels contain a given substring in their
 *       titles.</li>
 *   <li>Calculate the percentage of novels written within a specified
 *       year range.</li>
 *   <li>Retrieve the oldest novel in the bookstore.</li>
 *   <li>Retrieve a list of novels whose title lengths match a given
 *       number.</li>
 * </ul>
 *
 * <p>The class is designed to be used as part of an application that manages
 * or displays literary works, and it is contained in the
 * {@code ca.bcit.comp2522.lab05} package.
 *
 * @author Yang Li
 * @author Marcy Ordinario
 * @version 1.0
 */
public class BookStore<T extends Literature>
{
    private static final int DECADE_DIVIDER = 10;
    private static final int PERCENTAGE_MULT = 100;

    private final String name;
    private final List<T> bookList;
    private final Map<String, T> bookMap;

    /**
     * Constructs a new {@code BookStore} with the specified name.
     *
     * <p>This constructor performs the following steps:
     * <ol>
     *   <li>Validates the provided bookstore name.</li>
     *   <li>Initializes the list of novels and populates it
     *       using {@code populateBooks()}.</li>
     *   <li>Creates a {@code HashMap} ({@code novelsMap}) and maps
     *       each novel's title to its {@code Novel} object.</li>
     *   <li>Prints each title as it is added to the map.</li>
     *   <li>Removes any novel from the map if its title contains the
     *       substring "the" (ignoring case).</li>
     *   <li>Converts the remaining key set to an {@code ArrayList},
     *       sorts it alphabetically, and then prints the corresponding
     *       {@code Novel} objects using their {@code toString()}
     *       method.</li>
     * </ol>
     *
     * @param nameToBe the name to assign to the bookstore;
     *                 must not be null or blank
     */
    public BookStore(final String nameToBe)
    {
        validateName(nameToBe);
        this.name = nameToBe;

        bookList = new ArrayList<>();

        // Initializes HashMap
        bookMap = new HashMap<>();

        for (final T book : bookList)
        {
            // Gets key from each novel
            final String titleKey = book.getTitle();
            bookMap.put(titleKey, book);
        }
    }

    private static void validateName(final String unvalidatedName)
    {
        if (unvalidatedName == null)
        {
            throw new IllegalArgumentException("Name cannot be null");
        }
        if (unvalidatedName.isBlank())
        {
            throw new IllegalArgumentException("Name cannot be blank");
        }
    }

    /**
     * The main entry point for the BookStore application.
     *
     * <p>This method creates an instance of {@code BookStore} with
     *    a predefined name.
     *
     * @param args the command line arguments (not used in this application)
     */
    public static void main(final String[] args)
    {
        final BookStore store;
        store = new BookStore("The Bookstore");
    }


    public void addBook(T item)
    {
        bookList.add(item);
        bookMap.put(item.getTitle(), item);
    }

    public void printBookList()
    {
        for (T item : bookList) {
            System.out.println(item.getTitle());
        }
    }
}
