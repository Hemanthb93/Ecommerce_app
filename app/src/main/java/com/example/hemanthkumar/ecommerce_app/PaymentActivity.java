package com.example.hemanthkumar.ecommerce_app;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class PaymentActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyDataBase dataBase;
    Context context;
    ArrayList<DataTwo>tabletwodata;
    AdapterTwo adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        recyclerView=findViewById(R.id.RecyclerViewtwo);
        tabletwodata=new ArrayList<>();
        context=this;
        dataBase = new MyDataBase(context, "DB", null, 1);
        tabletwodata = dataBase.getsecondtabledata();
        showDetails(tabletwodata);
    }

    private void showDetails(ArrayList<DataTwo> tabletwodata) {

        adapter = new AdapterTwo(this, tabletwodata);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
