package Book;

import java.sql.SQLException;

public interface CustomerDao
{
    void addCustomer(Customer customer) throws SQLException;
    Boolean getCustomerByPin(String pin) throws SQLException;
    Boolean doesCustomerExist(String owner) throws SQLException;
}
