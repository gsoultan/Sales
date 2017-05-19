package sales.com.repository;

/**
 * Created by Gembit Soultan on 4/13/2017.
 */

import java.util.List;

import sales.com.model.Customer;

public interface CustomerRepository {

    public List<Customer> findAllCustomer();
    public long insert(Customer customer);
}
