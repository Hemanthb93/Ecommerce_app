package com.example.hemanthkumar.ecommerce_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    ArrayList<Data> tabledata;
    Context context;
    MyDataBase dataBase;
    MyAdapter adapter;
    TextView total_Amount,totalQuantity;
    public static final String MY_PREFS_NAME = "MyPref";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        total_Amount=(TextView) findViewById(R.id.tv_totalamount);
        totalQuantity=(TextView) findViewById(R.id.tv_totalquantity);
        totalQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,PaymentActivity.class);
                startActivity(intent);

            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        tabledata = new ArrayList<>();
        context = this;
        dataBase = new MyDataBase(context, "DB", null, 1);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (!prefs.getBoolean("firstTime", false)) {
            // <---- run your one time code here
            insert();
            // mark first time has runned.
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", true);
            editor.commit();
        }
        tabledata = dataBase.getData();
        showDetails(tabledata);
    }

    private void showDetails(ArrayList<Data> tabledata) {
        adapter = new MyAdapter(this, tabledata);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void insert() {
        dataBase.insert("TV", "samsung", 1245, "camera", 10);
        dataBase.insert("Mobile", "apple 15GB", 45687, "shoe", 10);
        dataBase.insert("Shoe", "TV", 1254, "head", 10);
        dataBase.insert("HeadPhones", "TV", 41258, "camera", 10);
        dataBase.insert("Camera", "Canon Camera", 51447, "mobile", 10);
        dataBase.insert("T_shirt", "peterengland", 1000, "shoe", 10);
        dataBase.insert("TV", "TV", 2500, "mobile", 10);
    }

    public void getData( int totalquantity, int  totalAmount){


                total_Amount.setText("Totalmount: "+String.valueOf(totalAmount));
                totalQuantity.setText("Total Qty: "+String.valueOf(totalquantity));


    }


}
