package ca.bcit.comp2522.lab06;

import java.util.*;

/**
 * The {@code BookStore} class represents a bookstore that manages
 * a collection of {@link Novel} objects. It provides functionality for
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
        bookMap = new HashMap<String, T>();

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

    /**
     * Prints all the novel titles in this bookstore in uppercase.
     *
     * <p>This method iterates through the internal list of novels
     * and converts each title to uppercase before printing it to
     * the standard output.
     */
    public void printAllTitles()
    {
        for (final T book : bookList)
        {
            final String upperCaseNovel;
            upperCaseNovel = book.getTitle().toUpperCase();
            System.out.println(upperCaseNovel);
        }
    }

    /**
     * Prints the titles of all novels that contain the specified
     * substring.
     *
     * <p>The search is case-insensitive. For each novel in the
     * collection, if the lowercase version of the title contains the
     * lowercase version of the provided substring, the title is printed.
     *
     * @param substring the substring to search for within novel titles
     */
    public void printBookTitle(final String substring)
    {
        final String stringToCompare;
        stringToCompare = substring.toLowerCase();

        for (final T book : bookList)
        {
            final String titleToCompare;
            titleToCompare = book.getTitle().toLowerCase();

            if (titleToCompare.contains(stringToCompare))
            {
                System.out.println(book.getTitle());
            }
        }
    }

    /**
     * Prints all the novel titles in this bookstore in alphabetical
     * order.
     *
     * <p>This method sorts the internal list of novels (using the
     * natural ordering defined in the {@link Novel} class) and then
     * prints each title in order.
     */
    public void printTitlesInAlphaOrder()
    {
        final List<T> sortedBooks;
        sortedBooks = bookList;

        Collections.sort(sortedBooks);

        for (final T alphaBook : sortedBooks)
        {
            System.out.println(alphaBook.getTitle());
        }
    }

    /**
     * Prints the titles of all novels that were published within the
     * specified decade.
     *
     * <p>The decade is determined by dividing the publication year
     * by 10. For example, to print novels from the 1950s, pass 1950 as
     * the {@code decadeToPrint}. The comparison is performed by dividing
     * both the novel's publication year and the specified decade by 10.
     *
     * @param decadeToPrint the decade to filter novels by (e.g., 1950 for the 1950s)
     */
    public void printGroupByDecade(final int decadeToPrint)
    {
        for (final T book : bookList)
        {
            final int yearPublished;
            final int decadeToCompareTo;

            yearPublished = book.getYearPublished() / DECADE_DIVIDER;
            decadeToCompareTo = decadeToPrint / DECADE_DIVIDER;

            if (yearPublished == decadeToCompareTo)
            {
                System.out.println(book.getTitle());
            }
        }
    }

    /**
     * Prints the title of the novel with the longest title in this
     * bookstore.
     *
     * <p>This method iterates through the collection of novels,
     * determines the novel with the longest title based on the length
     * of the title string, and then prints that title. If the bookstore
     * is empty, an exception might occur.
     */
    public void getLongest()
    {
        T bookWithLongestTitle;
        // MAYBE consider the edge case where the
        // book store is empty
        bookWithLongestTitle = bookList.getFirst();

        for (final T bookToCompare : bookList)
        {
            final int longestTitleLength;
            final int comparedTitleLength;

            longestTitleLength = bookWithLongestTitle.getTitle().length();
            comparedTitleLength = bookToCompare.getTitle().length();
            if (comparedTitleLength > longestTitleLength)
            {
                bookWithLongestTitle = bookToCompare;
            }
        }

        System.out.println(bookWithLongestTitle.getTitle());
    }

    /**
     * Determines if there is at least one novel written in the
     * specified year.
     *
     * <p>This method iterates through the collection of novels and
     * returns {@code true} as soon as it finds a novel whose
     * publication year matches the provided year.
     *
     * @param yearToSearch the publication year to search for
     * @return {@code true} if a novel was written in the specified year;
     *         {@code false} otherwise
     */
    public boolean isThereABookWrittenBetween(final int yearToSearch)
    {
        for (final T book : bookList)
        {
            final int bookPublicationYear;
            bookPublicationYear = book.getYearPublished();

            if (bookPublicationYear == yearToSearch)
            {
                return true;
            }
        }

        return false;
    }

    /**
     * Counts the number of novels whose titles contain the given
     * substring.
     *
     * <p>The search is case-insensitive. This method iterates through
     * all novels and increments a counter each time the title contains
     * the provided substring.
     *
     * @param substring the substring to search for within novel titles
     * @return the number of novels whose titles contain the specified
     *         substring
     */
    public int howManyBooksContain(final String substring)
    {
        int counter;
        counter = 0;

        for (final T book : bookList)
        {
            final String substringToCompare;
            final String titleToCompare;

            titleToCompare = book.getTitle().toLowerCase();
            substringToCompare = substring.toLowerCase();

            if (titleToCompare.contains(substringToCompare))
            {
                counter++;
            }
        }

        return counter;
    }

    /**
     * Calculates the percentage of novels that were written between
     * the specified years, inclusive.
     *
     * <p>This method iterates through the collection of novels and
     * counts those whose publication years fall between
     * {@code lowerYearBound} and {@code upperYearBound} (inclusive).
     * It then calculates the percentage based on the total number of
     * novels.
     *
     * @param lowerYearBound the lower bound of the publication year
     *                       range (inclusive)
     * @param upperYearBound the upper bound of the publication year
     *                       range (inclusive)
     * @return the percentage (as an integer) of novels written between
     *         the specified year bounds
     */
    public int whichPercentWrittenBetween(final int lowerYearBound,
                                          final int upperYearBound)
    {
        final int bookPercentage;
        final int totalBooks;

        int booksWrittenBetweenYears;

        booksWrittenBetweenYears = 0;
        totalBooks = bookList.size();

        for (final T book : bookList)
        {
            if (book.getYearPublished() >= lowerYearBound
                && book.getYearPublished() <= upperYearBound)
            {
                booksWrittenBetweenYears++;
            }
        }

        bookPercentage = (booksWrittenBetweenYears * PERCENTAGE_MULT)
                          / totalBooks;
        return bookPercentage;
    }

    /**
     * Retrieves the oldest novel in the bookstore.
     *
     * <p>This method iterates through the collection of novels and
     * returns the {@code Novel} object with the smallest publication
     * year (i.e., the oldest novel). If the collection is empty, the
     * behavior is undefined.
     *
     * @return the {@link Novel} object representing the oldest novel
     *         in the bookstore
     */
    public T getOldestBook()
    {
        T oldestBook;
        oldestBook = bookList.getFirst();

        for (final T book : bookList)
        {
            final int oldestPublicationYear;
            final int yearToCompare;

            oldestPublicationYear = oldestBook.getYearPublished();
            yearToCompare = book.getYearPublished();

            if (yearToCompare < oldestPublicationYear)
            {
                oldestBook = book;
            }
        }

        return oldestBook;
    }

    /**
     * Retrieves a list of novels whose titles have the specified length.
     *
     * <p>This method creates a new list containing all novels from
     * the bookstore whose title length (number of characters)
     * exactly matches the provided {@code titleLength}.
     *
     * @param titleLength the desired length of the novel titles to
     *                    match
     * @return an {@code ArrayList} of {@link Novel} objects that have
     *         titles with the specified length
     */
    public List<T> getBooksThisLength(final int titleLength)
    {
        final List<T> booksThisLength;
        booksThisLength = new ArrayList<T>();

        for (final T book : bookList)
        {
            if (book.getTitle().length() == titleLength)
            {
                booksThisLength.add(book);
            }
        }

        return booksThisLength;
    }
}
