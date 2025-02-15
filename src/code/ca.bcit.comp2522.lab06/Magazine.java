package ca.bcit.comp2522.lab06;

public class Magazine
        extends Literature
{
    public Magazine(final String title,
                     final int yearPublished)
    {
        super(title, yearPublished);
    }

    public String getTitle()
    {
        return super.title;
    }

    public int getYearPublished()
    {
        return super.yearPublished;
    }
}
