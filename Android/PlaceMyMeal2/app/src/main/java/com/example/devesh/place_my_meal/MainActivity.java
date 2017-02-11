package com.example.devesh.place_my_meal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.devesh.place_my_meal.Models.Company;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.nearby.messages.Strategy;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private String mUsername;
    private GoogleApiClient mGoogleApiClient;

    DatabaseReference databaseReference;
    ArrayList <Company.Items> myList;
    Button logout;
    Adapter adapter;
    ListView listView;
    TextView UserName;

    public static final String TAG = "HELLO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();

        if(mFirebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(getApplicationContext(),SignIn.class));
        }

        else{


            myList = new ArrayList<>();





       perform();
        }

    }


    public void perform(){
        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://placemymeal.firebaseio.com/company/");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dsp : dataSnapshot.getChildren()){
                    Log.d(TAG,"helllowhshd");
                    Company.Items object = (Company.Items) dsp.getValue();
                    //Company.Items object = new Company.Items("","");
                    //object=dsp.getValue(Company.Items.class);
                    // Company.Items object = new Company.Items(map.get("id"),map.get("name"));
                    //Log.d(TAG,object.getName()+" "+object.getId());
                    myList.add(object);
                  //  Log.d(TAG , map.get("id")+" "+map.get("name"));

          //          Toast.makeText(getApplication(),myList.size()+" ", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //Toast.makeText(getApplication(),myList.size()+" ", Toast.LENGTH_LONG).show();
        confirm();
       // adapter.notifyDataSetChanged();
    }

    public void confirm(){
        listView= (ListView) findViewById(R.id.list_view_company);
        Log.d(TAG,"hello123456");
        adapter = new Adapter(myList);
        Log.d(TAG,"hello12");
        listView.setAdapter(adapter);
        Log.d(TAG,"hello1234");
        adapter.notifyDataSetChanged();


    }

    public class Adapter extends BaseAdapter{

        ArrayList<Company.Items> mList;

        public Adapter(ArrayList<Company.Items> mList) {
            this.mList = mList;
        }

        public class Holder{
            TextView name;
            ImageView imageView;
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

            Log.d(TAG,"Fuck this shit");
            LayoutInflater layoutInflater = getLayoutInflater();
            Holder holder = new Holder();

            if(view==null){
                view = layoutInflater.inflate(R.layout.list_item, null);
                holder.name= (TextView) view.findViewById(R.id.outlet_name);
                holder.imageView = (ImageView) view.findViewById(R.id.outlet_image);
                view.setTag(holder);
            }
            else{
                holder = (Holder) view.getTag();
            }


            //ImageView imageView = (ImageView) view.findViewById(R.id.outlet_image);
            //TextView textView = (TextView) view.findViewById(R.id.outlet_name);

            final Company.Items obj = mList.get(i);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Bundle args = new Bundle();
                    //args.putString("id",obj.getId());
                    Intent intent = new Intent(getApplicationContext(),OrderPage.class);
                    intent.putExtra("id",obj.getId());
                    startActivity(intent);
                }
            });

            Log.d(TAG,obj.getName()+" ");

            //Log.d(TAG,obj.getName());
            holder.name.setText(obj.getName());

            return view;
        }
    }


    @Override
    public void onBackPressed() {
        mFirebaseAuth.signOut();
        finish();
        startActivity(new Intent(getApplicationContext(),SignIn.class));

    }
}
