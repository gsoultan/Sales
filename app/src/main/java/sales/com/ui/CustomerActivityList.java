package sales.com.ui;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import sales.com.adapter.CustomerListAdapter;
import sales.com.model.Customer;
import sales.com.repository.CustomerRepository;
import sales.com.repository.sqllite.CustomerSQLLiteRepository;
import sales.com.repository.sqllite.DatabaseHelper;

public class CustomerActivityList extends AppCompatActivity {

    private CustomerRepository customerRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);

        customerRepository = new CustomerSQLLiteRepository(this);

        ListView customerLV = (ListView) this.findViewById(R.id.customerListView);
        final CustomerListAdapter customerListAdapter = new CustomerListAdapter(this, customerRepository.findAllCustomer());
        customerLV.setAdapter(customerListAdapter);
        customerLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Customer c = (Customer) parent.getAdapter().getItem(position);
                Intent mIntent = new Intent(getApplicationContext(), CustomerInfoActivity.class);
            }
        });

        FloatingActionButton addCustomerBtn = (FloatingActionButton) this.findViewById(R.id.addCustomerBtn);
        addCustomerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(v.getContext().getApplicationContext(), CustomerFormActivity.class);
                startActivity(mIntent);
            }
        });

        customerLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }


}
