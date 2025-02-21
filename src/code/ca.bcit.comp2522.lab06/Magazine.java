package ca.bcit.comp2522.lab06;

public class Magazine extends Literature {

    public Magazine(final String title,
                    final int publicationDate) {
        super(title, publicationDate);
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    int getYearPublished() {
        return 0;
    }
}
