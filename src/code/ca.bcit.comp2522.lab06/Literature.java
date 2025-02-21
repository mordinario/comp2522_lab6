package ca.bcit.comp2522.lab06;

public abstract class Literature
{
    protected final String title;
    protected final int yearPublished;

    public Literature(final String titleToBe,
                      final int yearPublishedToBe)
    {
        validateTitle(titleToBe);
        this.title = titleToBe;

        validateYear(yearPublishedToBe);
        this.yearPublished = yearPublishedToBe;
    }

    private void validateYear(final int yearPublishedToBe)
    {
        if(yearPublishedToBe > 2025)
        {
            throw new IllegalArgumentException(
                    "Year Published is greater than current year");
        }
    }

    private void validateTitle(final String unvalidatedTitle)
    {
        if(unvalidatedTitle == null)
        {
            throw new IllegalArgumentException("Title cannot be null");
        }
        if(unvalidatedTitle.isBlank())
        {
            throw new IllegalArgumentException("Title cannot be blank");
        }
    }

    abstract String getTitle();
    abstract int    getYearPublished();
}
