package Book;

import java.sql.SQLException;

public class CustomerDaoFactory
{
    private static CustomerDao dao;

    private CustomerDaoFactory()
    {

    }

    public static CustomerDao getCustomerDao()
    {
        if(dao == null)
        {
            dao = new CustomerDaoImpl();
        }
        return dao;
    }
}