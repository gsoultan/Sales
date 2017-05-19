package sales.com.ui;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import sales.com.model.Customer;
import sales.com.repository.CustomerRepository;
import sales.com.repository.sqllite.CustomerSQLLiteRepository;

public class CustomerFormActivity extends AppCompatActivity {

    private CustomerRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_form);

        repository = new CustomerSQLLiteRepository(this);

        Button saveBtn = (Button) findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Customer c = new Customer();
                EditText firstNameTxt = (EditText) findViewById(R.id.firstNameTxt);
                c.setFirstName(firstNameTxt.getText().toString());
                EditText lastNameTxt = (EditText) findViewById(R.id.lastNameTxt);
                c.setLastName(lastNameTxt.getText().toString());
                RadioGroup genderRG = (RadioGroup) findViewById(R.id.genderRG);
                genderRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                        switch (checkedId){
                            case R.id.maleRB:
                                c.setGender("Male");
                                break;
                            case R.id.femaleRB:
                                c.setGender("Female");
                                break;
                        }
                    }
                });
                EditText addressTxt = (EditText) findViewById(R.id.addressTxt);
                c.setAddress(addressTxt.getText().toString());
                repository.insert(c);
            }
        });
    }
}
