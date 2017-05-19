package sales.com.repository.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.PorterDuff;

import java.util.ArrayList;
import java.util.List;

import sales.com.model.Customer;
import sales.com.repository.CustomerRepository;

/**
 * Created by Gembit Soultan on 4/13/2017.
 */

public class CustomerSQLLiteRepository implements CustomerRepository {

    private DatabaseHelper dh;
    private String table="Customer";
    private String createTable = "CREATE TABLE IF NOT EXISTS " + table + " (id INTEGER  PRIMARY KEY AUTOINCREMENT, firstName varchar(50), " +
            "lastName varchar(50), gender varchar(10), address varchar(200))";
    private String selectAllCustomer = "Select * From " + table;
    private String dropTable = "";

    public CustomerSQLLiteRepository(Context context) {
        dh = new DatabaseHelper(context, this.createTable, this.dropTable);
    }

    @Override
    public List<Customer> findAllCustomer() {
        List<Customer> customers=new ArrayList<Customer>();
        Cursor rs=dh.getDatabase().rawQuery(this.selectAllCustomer, null);
        rs.moveToFirst();

        while(!rs.isAfterLast()) {
            customers.add(convertCursorToCustomer(rs));
            rs.moveToNext();
        }
        if(!rs.isClosed()) { rs.close(); }
        return customers;
    }

    @Override
    public long insert(Customer customer) {
        ContentValues cv=new ContentValues();
        cv.put("firstName", customer.getFirstName());
        cv.put("lastName", customer.getLastName());
        cv.put("gender", customer.getGender());
        cv.put("address", customer.getAddress());
        return dh.getDatabase().insert(this.table, null, cv);
    }

    private Customer convertCursorToCustomer(Cursor rs) {
        Customer c=new Customer();
        c.setId(rs.getInt(rs.getColumnIndex("id")));
        c.setFirstName(rs.getString(rs.getColumnIndex("firstName")));
        c.setLastName(rs.getString(rs.getColumnIndex("lastName")));
        c.setGender(rs.getString(rs.getColumnIndex("gender")));
        c.setAddress(rs.getString(rs.getColumnIndex("address")));
        return c;
    }


}
