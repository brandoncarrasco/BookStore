package Book;

import java.sql.*;

public class CustomerDaoImpl implements CustomerDao
{
    Connection connection;
    public CustomerDaoImpl() {this.connection = ConnectionFactory.getConnection();}
    @Override
    public void addCustomer(Customer customer) throws SQLException
    {
        String sql = "insert into customers (name,password) value (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,customer.getName());
        preparedStatement.setString(2,customer.getPin());

        int count = preparedStatement.executeUpdate();

        if (count > 0)
            System.out.println("Customer added");
        else
            System.out.println("Oops! Something went wrong!");
    }

    @Override
    public Boolean getCustomerByPin(String pin) throws SQLException
    {
        String sql = "select * from customers where password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,pin);
        ResultSet resultSet = preparedStatement.executeQuery();


        if (resultSet.next())
        {
            // System.out.println("Customer found");
            return true;
        }
        else
        {
            // System.out.println("Customer not found");
            return false;
        }
    }

    @Override
    public Boolean doesCustomerExist(String owner) throws SQLException
    {
        String sql = "select * from customers where name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,owner);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next())
        {
            System.out.println("Customer found");
            return true;
        }
        else
        {
            System.out.println("Customer not found");
            return false;
        }
    }
}
