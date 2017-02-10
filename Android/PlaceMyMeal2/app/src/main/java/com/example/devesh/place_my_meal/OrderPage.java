package com.example.devesh.place_my_meal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.devesh.place_my_meal.Models.MenuItem;

import java.util.ArrayList;

public class OrderPage extends AppCompatActivity {

    ListView myListView;
    ArrayList <MenuItem.Food> myList;
    Integer TotalBill;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);



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




            return view;
        }
    }

}
