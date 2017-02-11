package com.example.devesh.place_my_meal;

import android.content.ContentValues;
import android.content.Intent;
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

import com.example.devesh.place_my_meal.Models.MenuOrder;
import com.example.devesh.place_my_meal.Models.OffDatabase;
import com.example.devesh.place_my_meal.Models.Table;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class MenuPage extends AppCompatActivity {

    String main;
    DatabaseReference databaseReference;
    ArrayList<MenuOrder.port> myList;
    ListView listView;
    Adapter adapter;
    SQLiteDatabase myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);

        main = getIntent().getExtras().getString("id");
        myList = new ArrayList<>();


        perform();
    }

    void perform(){


        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://placemymeal.firebaseio.com/menu_items/");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    MenuOrder.port element = dsp.getValue(MenuOrder.port.class);

                    if(main.matches(String.valueOf(element.getcompany_id())))
                    {
                        myList.add((element));
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });

        confirm();
    }

    void confirm(){

        listView = (ListView) findViewById(R.id.listt_view_menu);
        adapter = new Adapter(myList);
        adapter.notifyDataSetChanged();
    }

    public class Adapter extends BaseAdapter{

        ArrayList<MenuOrder.port> mList;

        public Adapter(ArrayList<MenuOrder.port> mList) {
            this.mList = mList;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public MenuOrder.port getItem(int i) {
            return mList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {


            LayoutInflater layoutInflater = getLayoutInflater();
            view = layoutInflater.inflate(R.layout.menu_order_layout,null);

            TextView tv1,tv2;
            Button add,sub;

            tv1= (TextView) view.findViewById(R.id.name);
            tv2 = (TextView) view.findViewById(R.id.price);
            add = (Button) view.findViewById(R.id.add);
            sub = (Button) view.findViewById(R.id.sub);

            final MenuOrder.port object = mList.get(i);

            tv1.setText(object.getName());
            tv2.setText(String.valueOf(object.getPrice()));

            //MenuOrder.port object = mList.get(i);

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    myDatabase = OrderDatabase.getWritableDatabase(getApplicationContext());

                    ContentValues contentValues = new ContentValues();
                    contentValues.put(OffDatabase.Columns.MENU_ID,object.getId());
                    contentValues.put(OffDatabase.Columns.OUTLET_ID,main);
                    contentValues.put(OffDatabase.Columns.NAME,object.getName());
                    contentValues.put(OffDatabase.Columns.PRICE,object.getPrice());
                    contentValues.put(OffDatabase.Columns.QUANTITY,1);

                    myDatabase.insert(OffDatabase.TABLE_NAME,null,contentValues);


                }
            });

            sub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    myDatabase = OrderDatabase.getWritableDatabase(getApplicationContext());

                    myDatabase.delete(OffDatabase.TABLE_NAME,OffDatabase.Columns.NAME+"="+object.getName(),null);

                }
            });

            return  view;
        }
    }
}
