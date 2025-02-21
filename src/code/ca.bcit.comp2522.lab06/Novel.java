package ca.bcit.comp2522.lab06;

/**
 * Represents a novel with a title, an author, and a publication year.
 * <p>
 * A novel is considered valid if the title and author are non-null,
 * non-empty, and do not exceed {@value #MAX_CHARACTERS} characters.
 * The year published must be between {@value #MIN_YEAR} and
 * {@value #MAX_YEAR}.
 * </p>
 *
 * @author Yang Li
 * @author Marcy Ordinario
 * @version 1.0
 */
public class Novel
        extends Literature
        implements Comparable<Novel>
{
    private static final int MIN_YEAR = 1500;
    private static final int MAX_YEAR = 2025;
    private static final int MAX_CHARACTERS = 50;

    private final String author;

    /**
     * Constructs a new {@code Novel} with the specified title,
     * author, and year published.
     *
     * @param title         the title of the novel;
     *                      must be non-null, non-empty, and at most
     *                      {@value #MAX_CHARACTERS} characters
     * @param author        the author of the novel; must be non-null,
     *                      non-empty, and at most {@value #MAX_CHARACTERS}
     *                      characters
     * @param yearPublished the year the novel was published; must be between
     *                      {@value #MIN_YEAR} and {@value #MAX_YEAR}
     */
    public Novel(final String title,
                 final String author,
                 final int yearPublished)
    {
        super(title, yearPublished);

        validateName(author);
        this.author = author;
    }

    private static void validateName(final String nameToValidate)
    {
        if(nameToValidate == null)
        {
            throw new IllegalArgumentException("Name cannot be null");
        }
        if(nameToValidate.isBlank())
        {
            throw new IllegalArgumentException("Name cannot be blank");
        }
    }

    /**
     * Returns the title of the novel.
     *
     * @return the title of the novel
     */
    protected String getTitle()
    {
        return title;
    }

    /**
     * Returns the author of the novel.
     *
     * @return the author's name
     */
    protected String getAuthorName()
    {
        return author;
    }

    /**
     * Returns the year the novel was published.
     *
     * @return the publication year of the novel
     */
    protected int getYearPublished()
    {
        return yearPublished;
    }

    /**
     * Compares this novel with the specified novel for order
     * based on their titles.
     *
     * @param novelToCompare the novel to be compared
     * @return a negative integer if this Novel's title is
     *         lexicographically less than the compared novel,
     *         a positive integer as this novel's title is
     *         lexicographically greater than the compared novel,
     *         0 if the Novels' titles are equal
     * @throws NullPointerException if the specified novel is {@code null}
     */
    @Override
    public int compareTo(final Novel novelToCompare)
    {
        if (novelToCompare == null)
        {
            throw new NullPointerException();
        }

        if (this == novelToCompare)
        {
            return 0;
        }

        return this.getTitle().compareTo(novelToCompare.getTitle());
    }

    /**
     * Returns a string representation of this {@code Novel} object.
     * <p>
     * The returned string includes the title, author, and publication
     * year of the novel, each on a separate line.
     * </p>
     *
     * @return a formatted string containing the novel's title, author,
     *         and year published
     */
    @Override
    public String toString() {
        final StringBuilder sb;
        sb = new StringBuilder();

        sb.append("Title: ").append(title);
        sb.append('\n');
        sb.append("Author: ").append(author);
        sb.append('\n');
        sb.append("Year Published: ").append(yearPublished);
        sb.append('\n');

        return sb.toString();
    }
}
