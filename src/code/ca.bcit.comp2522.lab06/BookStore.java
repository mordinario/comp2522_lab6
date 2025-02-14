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
public class BookStore
{
    private static final int DECADE_DIVIDER = 10;
    private static final int PERCENTAGE_MULT = 100;

    private final String name;
    private final List<Novel> novels;
    private final Map<String, Novel> novelsMap;

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

        novels = new ArrayList<>();
        populateBooks();

        // Initializes HashMap
        novelsMap = new HashMap<String, Novel>();

        for (final Novel novel : novels)
        {
            // Gets key from each novel
            final String titleKey = novel.getTitle();
            novelsMap.put(titleKey, novel);
        }

        // Initializes key set and iterator
        final Set<String>      titleSet;
        final List<String>     titleList;
        final Iterator<String> titleIter;

        titleSet  = novelsMap.keySet();
        titleIter = novelsMap.keySet().iterator();

        // Removing any novel with "the" in the title from the map
        while (titleIter.hasNext())
        {
            final String key;
            key = titleIter.next();
            System.out.println(key);

            if (key.toLowerCase().contains("the"))
            {
                titleIter.remove();
            }
        }

        // Convert the key set to an ArrayList
        titleList = new ArrayList<>(titleSet);

        // Sort the list then print using toString()
        Collections.sort(titleList);
        for (String key : titleList)
        {
            final Novel novelToPrint;
            novelToPrint = novelsMap.get(key);
            System.out.println(novelToPrint.toString());
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

    /*
     * Populates this BookStore with Novels.
     * All Novel data is hard-coded into this application; no
     * functionality exists for users to add or remove Novels
     * in this BookStore.
     */
    private void populateBooks()
    {
        novels.add(new Novel("The Adventures of Augie March", "Saul Bellow", 1953));
        novels.add(new Novel("All the King’s Men", "Robert Penn Warren", 1946));
        novels.add(new Novel("American Pastoral", "Philip Roth", 1997));
        novels.add(new Novel("An American Tragedy", "Theodore Dreiser", 1925));
        novels.add(new Novel("Animal Farm", "George Orwell", 1946));
        novels.add(new Novel("Appointment in Samarra", "John O'Hara", 1934));
        novels.add(new Novel("Are You There God? It’s Me, Margaret.", "Judy Blume", 1970));
        novels.add(new Novel("The Assistant", "Bernard Malamud", 1957));
        novels.add(new Novel("At Swim-Two-Birds", "Flann O’Brien", 1938));
        novels.add(new Novel("Atonement", "Ian McEwan", 2002));
        novels.add(new Novel("Beloved", "Toni Morrison", 1987));
        novels.add(new Novel("The Berlin Stories", "Christopher Isherwood", 1946));
        novels.add(new Novel("The Big Sleep", "Raymond Chandler", 1939));
        novels.add(new Novel("The Blind Assassin", "Margaret Atwood", 2000));
        novels.add(new Novel("Blood Meridian", "Cormac McCarthy", 1986));
        novels.add(new Novel("Brideshead Revisited", "Evelyn Waugh", 1946));
        novels.add(new Novel("The Bridge of San Luis Rey", "Thornton Wilder", 1927));
        novels.add(new Novel("Call It Sleep", "Henry Roth", 1935));
        novels.add(new Novel("Catch-22", "Joseph Heller", 1961));
        novels.add(new Novel("The Catcher in the Rye", "J.D. Salinger", 1951));
        novels.add(new Novel("A Clockwork Orange", "Anthony Burgess", 1963));
        novels.add(new Novel("The Confessions of Nat Turner", "William Styron", 1967));
        novels.add(new Novel("The Corrections", "Jonathan Franzen", 2001));
        novels.add(new Novel("The Crying of Lot 49", "Thomas Pynchon", 1966));
        novels.add(new Novel("A Dance to the Music of Time", "Anthony Powell", 1951));
        novels.add(new Novel("The Day of the Locust", "Nathanael West", 1939));
        novels.add(new Novel("Death Comes for the Archbishop", "Willa Cather", 1927));
        novels.add(new Novel("A Death in the Family", "James Agee", 1958));
        novels.add(new Novel("The Death of the Heart", "Elizabeth Bowen", 1958));
        novels.add(new Novel("Deliverance", "James Dickey", 1970));
        novels.add(new Novel("Dog Soldiers", "Robert Stone", 1974));
        novels.add(new Novel("Falconer", "John Cheever", 1977));
        novels.add(new Novel("The French Lieutenant's Woman", "John Fowles", 1969));
        novels.add(new Novel("The Golden Notebook", "Doris Lessing", 1962));
        novels.add(new Novel("Go Tell It on the Mountain", "James Baldwin", 1953));
        novels.add(new Novel("Gone with the Wind", "Margaret Mitchell", 1936));
        novels.add(new Novel("The Grapes of Wrath", "John Steinbeck", 1939));
        novels.add(new Novel("Gravity's Rainbow", "Thomas Pynchon", 1973));
        novels.add(new Novel("The Great Gatsby", "F. Scott Fitzgerald", 1925));
        novels.add(new Novel("A Handful of Dust", "Evelyn Waugh", 1934));
        novels.add(new Novel("The Heart Is a Lonely Hunter", "Carson McCullers", 1940));
        novels.add(new Novel("The Heart of the Matter", "Graham Greene", 1948));
        novels.add(new Novel("Herzog", "Saul Bellow", 1964));
        novels.add(new Novel("Housekeeping", "Marilynne Robinson", 1981));
        novels.add(new Novel("A House for Mr. Biswas", "V.S. Naipaul", 1962));
        novels.add(new Novel("I, Claudius", "Robert Graves", 1934));
        novels.add(new Novel("Infinite Jest", "David Foster Wallace", 1996));
        novels.add(new Novel("Invisible Man", "Ralph Ellison", 1952));
        novels.add(new Novel("Light in August", "William Faulkner", 1932));
        novels.add(new Novel("The Lion, the Witch and the Wardrobe", "C.S. Lewis", 1950));
        novels.add(new Novel("Lolita", "Vladimir Nabokov", 1955));
        novels.add(new Novel("Lord of the Flies", "William Golding", 1954));
        novels.add(new Novel("Snow Crash", "Neal Stephenson", 1992));
        novels.add(new Novel("The Sot-Weed Factor", "John Barth", 1960));
        novels.add(new Novel("The Sound and the Fury", "William Faulkner", 1929));
        novels.add(new Novel("The Sportswriter", "Richard Ford", 1986));
        novels.add(new Novel("The Spy Who Came in from the Cold", "John le Carré", 1964));
        novels.add(new Novel("The Sun Also Rises", "Ernest Hemingway", 1926));
        novels.add(new Novel("Their Eyes Were Watching God", "Zora Neale Hurston", 1937));
        novels.add(new Novel("Things Fall Apart", "Chinua Achebe", 1959));
        novels.add(new Novel("To Kill a Mockingbird", "Harper Lee", 1960));
        novels.add(new Novel("To the Lighthouse", "Virginia Woolf", 1929));
        novels.add(new Novel("Tropic of Cancer", "Henry Miller", 1934));
        novels.add(new Novel("Ubik", "Philip K. Dick", 1969));
        novels.add(new Novel("Under the Net", "Iris Murdoch", 1954));
        novels.add(new Novel("Under the Volcano", "Malcolm Lowry", 1947));
        novels.add(new Novel("Watchmen", "Alan Moore and Dave Gibbons", 1986));
        novels.add(new Novel("White Noise", "Don DeLillo", 1985));
        novels.add(new Novel("White Teeth", "Zadie Smith", 2000));
        novels.add(new Novel("Wide Sargasso Sea", "Jean Rhys", 1966));
        novels.add(new Novel("The Lord of the Rings", "J.R.R. Tolkien", 1954));
        novels.add(new Novel("Loving", "Henry Green", 1945));
        novels.add(new Novel("Lucky Jim", "Kingsley Amis", 1954));
        novels.add(new Novel("The Man Who Loved Children", "Christina Stead", 1940));
        novels.add(new Novel("Midnight's Children", "Salman Rushdie", 1981));
        novels.add(new Novel("Money", "Martin Amis", 1984));
        novels.add(new Novel("The Moviegoer", "Walker Percy", 1961));
        novels.add(new Novel("Mrs. Dalloway", "Virginia Woolf", 1925));
        novels.add(new Novel("Naked Lunch", "William Burroughs", 1959));
        novels.add(new Novel("Native Son", "Richard Wright", 1940));
        novels.add(new Novel("Neuromancer", "William Gibson", 1984));
        novels.add(new Novel("Never Let Me Go", "Kazuo Ishiguro", 2005));
        novels.add(new Novel("1984", "George Orwell", 1948));
        novels.add(new Novel("On the Road", "Jack Kerouac", 1957));
        novels.add(new Novel("One Flew Over the Cuckoo's Nest", "Ken Kesey", 1962));
        novels.add(new Novel("The Painted Bird", "Jerzy Kosinski", 1965));
        novels.add(new Novel("Pale Fire", "Vladimir Nabokov", 1962));
        novels.add(new Novel("A Passage to India", "E.M. Forster", 1924));
        novels.add(new Novel("Play It as It Lays", "Joan Didion", 1970));
        novels.add(new Novel("Portnoy's Complaint", "Philip Roth", 1969));
        novels.add(new Novel("Possession", "A.S. Byatt", 1990));
        novels.add(new Novel("The Power and the Glory", "Graham Greene", 1939));
        novels.add(new Novel("The Prime of Miss Jean Brodie", "Muriel Spark", 1961));
        novels.add(new Novel("Rabbit, Run", "John Updike", 1960));
        novels.add(new Novel("Ragtime", "E.L. Doctorow", 1975));
        novels.add(new Novel("The Recognitions", "William Gaddis", 1955));
        novels.add(new Novel("Red Harvest", "Dashiell Hammett", 1929));
        novels.add(new Novel("Revolutionary Road", "Richard Yates", 1961));
        novels.add(new Novel("The Sheltering Sky", "Paul Bowles", 1949));
        novels.add(new Novel("Slaughterhouse-Five", "Kurt Vonnegut", 1969));
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
        for (final Novel novel : novels)
        {
            final String upperCaseNovel;
            upperCaseNovel = novel.getTitle().toUpperCase();
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

        for (final Novel novel : novels)
        {
            final String titleToCompare;
            titleToCompare = novel.getTitle().toLowerCase();

            if (titleToCompare.contains(stringToCompare))
            {
                System.out.println(novel.getTitle());
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
        final List<Novel> sortedNovels;
        sortedNovels = novels;

        Collections.sort(sortedNovels);

        for (final Novel alphaNovel : sortedNovels)
        {
            System.out.println(alphaNovel.getTitle());
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
        for (final Novel novel : novels)
        {
            final int yearPublished;
            final int decadeToCompareTo;

            yearPublished = novel.getYearPublished() / DECADE_DIVIDER;
            decadeToCompareTo = decadeToPrint / DECADE_DIVIDER;

            if (yearPublished == decadeToCompareTo)
            {
                System.out.println(novel.getTitle());
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
        Novel novelWithLongestTitle;
        // MAYBE consider the edge case where the
        // book store is empty
        novelWithLongestTitle = novels.getFirst();

        for (final Novel novelToCompare : novels)
        {
            final int longestTitleLength;
            final int comparedTitleLength;

            longestTitleLength = novelWithLongestTitle.getTitle().length();
            comparedTitleLength = novelToCompare.getTitle().length();
            if (comparedTitleLength > longestTitleLength)
            {
                novelWithLongestTitle = novelToCompare;
            }
        }

        System.out.println(novelWithLongestTitle.getTitle());
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
        for (final Novel novel : novels)
        {
            final int novelPublicationYear;
            novelPublicationYear = novel.getYearPublished();

            if (novelPublicationYear == yearToSearch)
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

        for (final Novel novel : novels)
        {
            final String substringToCompare;
            final String titleToCompare;
            titleToCompare = novel.getTitle().toLowerCase();
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
        final int novelPercentage;
        final int totalNovels;

        int novelsWrittenBetweenYears;

        novelsWrittenBetweenYears = 0;
        totalNovels = novels.size();

        for (final Novel novel : novels)
        {
            if (novel.getYearPublished() >= lowerYearBound
                && novel.getYearPublished() <= upperYearBound)
            {
                novelsWrittenBetweenYears++;
            }
        }

        novelPercentage = (novelsWrittenBetweenYears * PERCENTAGE_MULT)
                          / totalNovels;
        return novelPercentage;
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
    public Novel getOldestBook()
    {
        Novel oldestNovel;
        oldestNovel = novels.getFirst();

        for (final Novel novel : novels)
        {
            final int oldestPublicationYear;
            final int yearToCompare;

            oldestPublicationYear = oldestNovel.getYearPublished();
            yearToCompare = novel.getYearPublished();

            if (yearToCompare < oldestPublicationYear)
            {
                oldestNovel = novel;
            }
        }

        return oldestNovel;
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
    public List<Novel> getBooksThisLength(final int titleLength)
    {
        final List<Novel> booksThisLength;
        booksThisLength = new ArrayList<Novel>();

        for (final Novel novel : novels)
        {
            if (novel.getTitle().length() == titleLength)
            {
                booksThisLength.add(novel);
            }
        }

        return booksThisLength;
    }
}
