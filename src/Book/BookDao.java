package Book;

import java.sql.SQLException;
import java.util.List;

public interface BookDao
{
    void showListOfAllBook() throws SQLException;
    void showListByGenre(String genre) throws SQLException;
    void showGenres() throws SQLException;
    void showBookInfo(String title) throws SQLException;
    Book getBook(String title) throws SQLException;
    void showBookByTitle() throws SQLException;
    void insertIntoCart(String owner,Book book) throws SQLException;
    void getCart(String owner) throws SQLException;
}
