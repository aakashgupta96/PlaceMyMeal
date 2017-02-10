package com.example.devesh.place_my_meal;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.devesh.place_my_meal.Models.MenuItem;
import com.example.devesh.place_my_meal.Models.OffDatabase;

import java.util.ArrayList;

public class OrderPage extends AppCompatActivity {

    ListView myListView;
    ArrayList <MenuItem.Food> myList;
    Integer TotalBill;
    Button Pay;
    Adapter adapter;
    TextView showTotal;
    SQLiteDatabase orderDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);

        showTotal = (TextView) findViewById(R.id.set_value);
        myList = new ArrayList<>();
        myListView = (ListView) findViewById(R.id.list_view_final);
        Pay = (Button) findViewById(R.id.pay);

        perform();

        adapter = new Adapter(myList);
        myListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        Pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    public void perform(){
        orderDatabase = OrderDatabase.getReadableDatabase(getApplicationContext());



        showTotal.setText(String.valueOf(TotalBill));
    }

    public class Adapter extends BaseAdapter{

        ArrayList<MenuItem.Food> mList;

        public Adapter(ArrayList<MenuItem.Food> mList) {
            this.mList = mList;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public MenuItem.Food getItem(int i) {
            return mList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            LayoutInflater layoutInflater = getLayoutInflater();
            layoutInflater.inflate(R.layout.order_layout,null);

            MenuItem.Food obj = mList.get(i);

            TextView t1,t2;

            t1 = (TextView) view.findViewById(R.id.item_name);
            t2 = (TextView) view.findViewById(R.id.item_quantity);

            t1.setText(obj.getName());
            t2.setText(String.valueOf(obj.getQuantity()));


            return view;
        }
    }

}
