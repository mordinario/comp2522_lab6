package ca.bcit.comp2522.lab06;

public class ComicBook extends Literature
{
    public ComicBook(final String title,
                     final int publicationDate)
    {
        super(title, publicationDate);
    }

    @Override
    public String getTitle() {
        return super.title;
    }

    @Override
    int getYearPublished() {
        return 0;
    }
}
