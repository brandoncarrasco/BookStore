package Book;

import com.mysql.cj.util.StringUtils;

import java.sql.*;
import java.sql.Statement;

public class BookDaoImpl implements BookDao
{
    Connection connection;
    public BookDaoImpl() {this.connection = ConnectionFactory.getConnection();}

    @Override
    public void showListOfAllBook() throws SQLException
    {
        String sql = "select * from books";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        /*
        ResultSetMetaData data = resultSet.getMetaData();
        int columnNumbers = data.getColumnCount();
        // print column label
        for(int i = 1; i <=columnNumbers;i++)
        {
            System.out.print(data.getColumnLabel(i) +"\t\t");
        }
        System.out.println();
        while(resultSet.next())
        {
            for(int i = 1; i <= columnNumbers;i++)
            {
                System.out.print(resultSet.getString(i) + "\t\t");
            }
            System.out.println();
        }*/

        System.out.println();
        while (resultSet.next())
        {
            String title = resultSet.getString("title");
            System.out.printf("Title: %s\n",title);
            String author = resultSet.getString("author");
            System.out.printf("Author: %s\n",author);
            int price = resultSet.getInt("price");
            System.out.printf("Price: %d\n",price);
            String genre = resultSet.getString("genre");
            System.out.printf("Genre: %s\n",genre);

            System.out.println();
        }
    }

    @Override
    public void showListByGenre(String genre) throws SQLException
    {
        String sql = "select * from books where genre = '" + genre +"'" ;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        /*
        ResultSetMetaData data = resultSet.getMetaData();
        int columnNumbers = data.getColumnCount();
        // print column label
        for(int i = 1; i <=columnNumbers;i++)
        {
            System.out.print(data.getColumnLabel(i) +"\t\t");
        }
        System.out.println();
        while(resultSet.next())
        {
            for(int i = 1; i <= columnNumbers;i++)
            {
                System.out.print(resultSet.getString(i) + "\t\t");
            }
            System.out.println();
        }*/
        while (resultSet.next())
        {
            String title = resultSet.getString("title");
            System.out.printf("Title: %s\n",title);
            String author = resultSet.getString("author");
            System.out.printf("Author: %s\n",author);
            int price = resultSet.getInt("price");
            System.out.printf("Price: %d\n",price);
            //String genre = resultSet.getString("genre");
            //System.out.printf("Genre: %s\n",genre);

            System.out.println();
        }
    }

    @Override
    public void showGenres() throws SQLException
    {
        String sql = "select distinct genre from books";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        ResultSetMetaData data = resultSet.getMetaData();
        int columnNumbers = data.getColumnCount();
        // print column label
        for(int i = 1; i <=columnNumbers;i++)
        {
            System.out.println(data.getColumnLabel(i) +"\t\t");
        }
        System.out.println();
        while(resultSet.next())
        {
            for(int i = 1; i <= columnNumbers;i++)
            {
                System.out.println(resultSet.getString(i) + "\t\t");
            }
            System.out.println();
        }
    }

    @Override
    public void showBookInfo(String title) throws SQLException
    {
        String sql = "select description from books where title = '" + title +"'" ;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        resultSet.next();
        System.out.println( "Description: "+resultSet.getString(1));
    }

    @Override
    public Book getBook(String title) throws SQLException
    {
        String name = null;
        String author = null;
        int price = 0;
        String sql = "select title,author,price from books where title = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,title);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next())
        {
            name = resultSet.getString("title");
            author = resultSet.getString("author");
            price = resultSet.getInt("price");
        }
        Book newBook = new Book(name,author,price);

        return newBook;
    }

    @Override
    public void showBookByTitle() throws SQLException
    {
        String sql = "select title from books" ;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        ResultSetMetaData data = resultSet.getMetaData();
        int columnNumbers = data.getColumnCount();

        System.out.println();
        while(resultSet.next())
        {
            for(int i = 1; i <= columnNumbers;i++)
            {
                System.out.print(resultSet.getString(i) + "\t\t");
            }
            System.out.println();
        }
    }
}
