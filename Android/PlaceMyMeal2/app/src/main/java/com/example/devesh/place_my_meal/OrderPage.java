package com.example.devesh.place_my_meal;

import android.database.Cursor;
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
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class OrderPage extends AppCompatActivity {

    ListView myListView;
    ArrayList <MenuItem.Food> myList;
    Integer TotalBill;
    Button Pay;
    Adapter adapter;
    TextView showTotal;
    SQLiteDatabase orderDatabase;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);

        showTotal = (TextView) findViewById(R.id.set_value);
        myList = new ArrayList<>();
        myListView = (ListView) findViewById(R.id.list_view_final);
        Pay = (Button) findViewById(R.id.pay);
        TotalBill = 0;

        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://placemymeal.firebaseio.com/orders/");

        perform();


        Pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    public void perform(){
        orderDatabase = OrderDatabase.getReadableDatabase(getApplicationContext());
        myList.clear();

        DatabaseReference mref = databaseReference.child("ordered_items");

        String proj[]={
                OffDatabase.Columns.MENU_ID,
                OffDatabase.Columns.OUTLET_ID,
                OffDatabase.Columns.NAME,
                OffDatabase.Columns.PRICE,
                OffDatabase.Columns.QUANTITY
        };

        Cursor cursor = orderDatabase.query(OffDatabase.TABLE_NAME,proj,null,null,null,null,null);
int cnt=0;

        while (cursor.moveToNext()){

            Integer id1= cursor.getInt(cursor.getColumnIndexOrThrow(OffDatabase.Columns.MENU_ID));
            Integer id2 = cursor.getInt(cursor.getColumnIndexOrThrow(OffDatabase.Columns.OUTLET_ID));
            Integer pri = cursor.getInt(cursor.getColumnIndexOrThrow(OffDatabase.Columns.PRICE));
            Integer qua = cursor.getInt(cursor.getColumnIndexOrThrow(OffDatabase.Columns.QUANTITY));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(OffDatabase.Columns.NAME));

            TotalBill+=pri;

            mref.push().setValue("menu_item_id",id1);
            mref.push().setValue("quantity",qua);
            mref.push().setValue("order_id",cnt);

            cnt++;

        }

        confirm();

        showTotal.setText(String.valueOf(TotalBill));
    }

    public void confirm(){
        adapter = new Adapter(myList);
        myListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

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
