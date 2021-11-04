package Book;

import java.sql.SQLException;

public interface BookDao
{
    void showListOfAllBook() throws SQLException;
    void showListByGenre(String genre) throws SQLException;
    void showGenres() throws SQLException;
    void showBookInfo(String title) throws SQLException;
    Book getBook(String title) throws SQLException;
    void showBookByTitle() throws SQLException;
}
