package com.example.hemanthkumar.ecommerce_app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by hemanthkumar on 9/3/18.
 */

public class AdapterTwo extends RecyclerView.Adapter<AdapterTwo.MyHolder> {

    ArrayList<DataTwo> tabletwodata;
    Context context;
    String titletwo;
    MyDataBase dataBase;


    public AdapterTwo(Context context, ArrayList<DataTwo> tabletwodata) {
        dataBase = new MyDataBase(context, "DB", null, 1);
        this.tabletwodata = tabletwodata;
        this.context = context;
    }


    @Override
    public AdapterTwo.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.roowtwo, parent, false);
        AdapterTwo.MyHolder viewholder = new MyHolder(view);
        return viewholder;

    }

    @Override
    public void onBindViewHolder(AdapterTwo.MyHolder holder, final int position) {

        holder.title.setText(tabletwodata.get(position).getTitle_two());
        holder.quantity.setText(String.valueOf(tabletwodata.get(position).getQuantity_two()));
        holder.amount.setText(String.valueOf(tabletwodata.get(position).getAmount_two()));

        final int pos = position;
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titletwo = tabletwodata.get(position).getTitle_two();
                delete(titletwo);
                tabletwodata.remove(tabletwodata.get(pos));
                notifyDataSetChanged();
            }
        });

    }


    @Override
    public int getItemCount() {
        return tabletwodata.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView title, quantity, amount;

        ImageButton delete;

        public MyHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_product_name);
            quantity = itemView.findViewById(R.id.tv_quantity);
            amount = itemView.findViewById(R.id.tv_amount);
            delete = itemView.findViewById(R.id.ib_delete);

        }
    }

    private void delete(String titletwo) {
        dataBase.delete(titletwo);
    }
}
