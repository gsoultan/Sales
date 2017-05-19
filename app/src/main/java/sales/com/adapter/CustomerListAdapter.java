package sales.com.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import sales.com.model.Customer;
import sales.com.ui.R;

/**
 * Created by Gembit Soultan on 4/13/2017.
 */

public class CustomerListAdapter extends ArrayAdapter<Customer> {

    private List<Customer> customers;
    private Context mContext;

    public CustomerListAdapter(@NonNull Context context, List<Customer> customers) {
        super(context, R.layout.customer_list_item);
        this.customers = customers;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return this.customers.size();
    }

    @Nullable
    @Override
    public Customer getItem(int position) {
        return this.customers.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = mInflater.inflate(R.layout.customer_list_item, parent,false);

        Customer c = this.getItem(position);

        TextView nameTextView = (TextView) convertView.findViewById(R.id.nameTextView);
        TextView addressTextView = (TextView) convertView.findViewById(R.id.addressTextView);

        nameTextView.setText(c.getFullName());
        addressTextView.setText(c.getAddress());

        return convertView;
    }
}
