package Book;

import java.sql.SQLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Currency;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main
{

    public static void main(String[] args) throws SQLException
    {

        boolean running = true;
        while(running)
        {
            CustomerDao dao = CustomerDaoFactory.getCustomerDao();
            BookDao bookDao = BookDaoFactory.getAccountDao();
            Scanner sc = new Scanner(System.in).useDelimiter("\n");
            ArrayList<Book> books = new ArrayList<>();

            System.out.println("***********************");
            System.out.println("Welcome to the Bookstore!");
            System.out.println("***********************");


            Customer customer = new Customer();

            System.out.println("Do you have a Profile?");
            System.out.println("1 - YES");
            System.out.println("2 - NO");
            int option = sc.nextInt();

            if (option == 1)
            {
                // get customer from dao
                System.out.println("Welcome back!! Please Enter your name..");
                String name = sc.next();
                if(!dao.doesCustomerExist(name))
                {
                    continue;
                }
                customer.setName(name);
                System.out.println("Enter your unique password: ");
                String pin = sc.next();
                customer.setPin(pin);

                // validate pin
                int attempts = 0;
                while(dao.getCustomerByPin(customer.getPin()) == false)
                {
                    if(attempts > 1)
                    {
                        System.out.println("Too many attempts. Logging off.");
                        break;
                    }
                    System.out.println("Wrong password.Try again");
                    pin = sc.next();
                    customer.setPin(pin);
                    attempts++;
                }
                if(attempts > 1) continue;
                // check if customer has at least one account
                /*
                else if(dao.getCustomerNumberOfAccounts(customer.getPin()) == 0)
                {
                    System.out.println("You have no accounts..");
                } */

            }
            else if (option == 2)
            {
                System.out.println("Enter your name: ");
                String name = sc.next();
                System.out.println("Attempting to create your account...");
                customer.setName(name);
                System.out.println("Create your unique password: ");
                String pin = sc.next();
                customer.setPin(pin);

                dao.addCustomer(customer);
            }

            boolean session = true;

            while(session)
            {
                // start customer session
                System.out.println("1 - Show me cool books");
                System.out.println("2 - Select book by genre");
                System.out.println("3 - More info on a book");
                System.out.println("4 - Add book to cart");
                System.out.println("5 - View Cart and Buy book");
                System.out.println("6 - Log Out");
                int menuChoice = sc.nextInt();
                switch(menuChoice)
                {
                    case 1:
                    {
                        // show list of all available books
                        bookDao.showListOfAllBook();
                        System.out.println();
                        break;
                    }
                    case 2:
                    {
                        System.out.println("What genre do you want to look at?");
                        // show list of genres available
                        bookDao.showGenres();
                        String choice = sc.next();
                        bookDao.showListByGenre(choice);
                        // show list of book of certain genre
                        break;
                    }
                    case 3:
                    {

                        bookDao.showListOfAllBook();
                        System.out.println("What book would you like more info on: ");
                        String choice = sc.next();
                        bookDao.showBookInfo(choice);
                        // get more info on book
                        //System.out.println();
                        break;
                    }
                    case 4:
                    {
                        // add book to cart that customer select by name
                        bookDao.showBookByTitle();
                        // show cart and ask customer to buy book.
                        System.out.println("Which book would you like to add to your cart");
                        String book = sc.next();
                        books.add(bookDao.getBook(book));
                        bookDao.insertIntoCart(customer.getName(), bookDao.getBook(book));
                       // System.out.println();

                        break;
                    }
                    case 5:
                    {
                        /*
                        if (books.isEmpty())
                        {
                            System.out.println("You have no books in your cart");
                            break;
                        }
                       for(Book book : books)
                       {
                           System.out.println("Title: " + book.getTitle());
                           System.out.println("Author: " + book.getAuthor());
                           System.out.println("Price: " + book.getPrice());

                           System.out.println();
                           System.out.println();
                       }*/
                        bookDao.getCart(customer.getName());

                       System.out.println("Would you like to checkout? (Yes or No)");
                       String choice = sc.next();
                       if(choice.equals("Yes"))
                           System.out.println("Enjoy your book!");
                       else if (choice.equals("No"))
                           System.out.println("No books for you today");
                       session = false;

                       break;
                    }
                    case 6:
                        System.out.println("Thanks for being a valuable customer");
                        session = false;
                        break;

                    default:
                        System.out.println("Please enter one of the choices in the menu.");
                        break;
                }
            }
        }
    }

    // created this method because I was using the same code for a lot of the input.
    // maybe I can put this code into its own class and overload or override it
    // to be able to take in different types of input.
    public static int input(Scanner sc)
    {
        int num = -1;
        do
        {
            sc.nextLine();
            try
            {

                num = sc.nextInt();
                if (num < 0)
                {
                    System.out.println("Please enter a positive number.");
                }
            }
            catch(InputMismatchException e)
            {
                System.out.println("Please only enter positive numbers and no letters!");
            }
        } while(num <= 0);
        return num;
    }
}

