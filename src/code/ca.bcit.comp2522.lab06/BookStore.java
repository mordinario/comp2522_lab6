package ca.bcit.comp2522.lab06;

import java.util.*;

/**
 * The {@code BookStore} class represents a bookstore that manages
 * a collection of {@link T extends Literature} objects. It provides
 * functionality for adding new items to the {@code BookStore} and
 * printing a complete list of its current held titles
 *
 * <p>The class is designed to be used as part of an application that manages
 * or displays literary works, and it is contained in the
 * {@code ca.bcit.comp2522.lab06} package.
 *
 * @author Yang Li
 * @author Marcy Ordinario
 * @version 1.0
 */
public class BookStore<T extends Literature>
{
    private final String name;
    private final List<T> bookList;
    private final Map<String, T> bookMap;

    // Contains functionality for comparing books
    private class novelStatistics
    {
        public double averageTitleLength()
        {
            int totalLength;
            totalLength = 0;

            for(final T book : bookList)
            {
                totalLength += book.getTitle().length();
            }

            return (double) totalLength / bookList.size();
        }

        public int findCommonPublicationYear()
        {
            final Map<Integer, Integer> yearMap;
            yearMap = new HashMap<Integer, Integer>();
            int mostCommonPublicationYear;

            for(final T book : bookList)
            {
                final int bookYear;
                bookYear = book.getYearPublished();
                if(!yearMap.containsKey(bookYear))
                {
                    yearMap.put(bookYear, 1);
                }
                else
                {
                    yearMap.put(bookYear, yearMap.get(bookYear) + 1);
                }
            }

            mostCommonPublicationYear = 0;

            final Set<Integer> yearSet;
            yearSet = yearMap.keySet();

            for(final int year : yearSet)
            {
                if(year > mostCommonPublicationYear)
                {
                    mostCommonPublicationYear = year;
                }
            }

            return mostCommonPublicationYear;
        }
    }

    /**
     * Constructs a new {@code BookStore} with the specified name.
     *
     * <p>This constructor performs the following steps:
     * <ol>
     *   <li>Validates the provided bookstore name.</li>
     *   <li>Initializes a {@code HashMap} that will contain a map
     *       of book titles as keys and {@code T extends Literature}
     *       objects as values.</li>
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
        bookMap = new HashMap<>();
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

    public String getName()
    {
        return name;
    }
}
