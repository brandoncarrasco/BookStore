package Book;

public class BookDaoFactory
{
    private static BookDao bookDao;

    private BookDaoFactory()
    {

    }

    public static BookDao getAccountDao()
    {
        if(bookDao == null)
        {
            bookDao = new BookDaoImpl();
        }

        return bookDao;
    }
}
