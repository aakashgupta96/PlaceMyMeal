package com.example.devesh.place_my_meal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.devesh.place_my_meal.Models.Company;

import java.util.ArrayList;

public class OrderPage extends AppCompatActivity {


    Adapter adapter;
    ArrayList <Company.Items> myList;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);

        listView = (ListView) findViewById(R.id.list_view);

        myList = new ArrayList<>();


        adapter = new Adapter(myList);

    }


    public class Adapter extends BaseAdapter{

        private ArrayList<Company.Items> mList;



        public Adapter(ArrayList<Company.Items> mList) {
            this.mList = mList;

        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Company.Items getItem(int i) {
            return mList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            LayoutInflater layoutInflater = getLayoutInflater();
            view = layoutInflater.inflate(R.layout.list_item,null);

            ImageView outletImage = (ImageView) view.findViewById(R.id.outlet_image);
            TextView outletName = (TextView) view.findViewById(R.id.outlet_name);

            Company.Items user=getItem(i);

            outletName.setText(user.getName());
            outletName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

//            outletImage.setImageResource();

            return view;
        }

    }
}
