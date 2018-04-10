package com.example.hemanthkumar.ecommerce_app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by hemanthkumar on 6/3/18.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    ArrayList<Data> tabledata;
    Context activity;
    int totalquantity = 0;
    int totalAmount = 0;
    MyDataBase dataBase;


    public MyAdapter(Context activity, ArrayList<Data> tabledata) {
        this.tabledata = tabledata;
        this.activity = activity;
    }




    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.title.setText(tabledata.get(position).getTitle());
        holder.description.setText(tabledata.get(position).getDescription());
        holder.price.setText(String.valueOf(tabledata.get(position).getPrize()));
        String image = tabledata.get(position).getImage();
        final int quantity = tabledata.get(position).getQuantity();
        holder.imageviewplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numberOfItems = Integer.valueOf(holder.tv_number.getText().toString());
                if (numberOfItems < quantity) {
                    numberOfItems = numberOfItems + 1;
                    holder.tv_number.setText(String.valueOf(numberOfItems));
                    holder.tv_amountpayable.setText("Amount Payable:" + String.valueOf(numberOfItems * tabledata.get(position).getPrize()));
                } else {
                    Toast.makeText(activity, "out of stock ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.imageviewminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numberOfItems = Integer.valueOf(holder.tv_number.getText().toString());
                if (numberOfItems > 0) {
                    numberOfItems = numberOfItems - 1;
                    holder.tv_number.setText(String.valueOf(numberOfItems));
                    holder.tv_amountpayable.setText("Amount Payable:" + String.valueOf(numberOfItems * tabledata.get(position).getPrize()));
                }

            }
        });

        holder.cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numberOfItems = Integer.valueOf(holder.tv_number.getText().toString());
                int prevNumberOfItems = Integer.valueOf(holder.tv_cart.getText().toString());
                holder.tv_cart.setText(String.valueOf(numberOfItems));
                int prize = tabledata.get(position).getPrize();
                totalAmount = totalAmount + numberOfItems * prize - prevNumberOfItems * prize;
                totalquantity = totalquantity + numberOfItems - prevNumberOfItems;
                ((MainActivity) activity).getData(totalquantity, totalAmount);

                String title = tabledata.get(position).getTitle();
                int quantity = numberOfItems;
                int amount = numberOfItems * tabledata.get(position).getPrize();

                if(totalquantity!=0){
                    inserttwo(title, quantity, amount);
                }

            }

            private void update(String title) {

                dataBase = new MyDataBase(activity, "DB", null, 1);
                dataBase.update(title);

            }

            private void inserttwo(String title, int quantity, int amount) {
                dataBase = new MyDataBase(activity, "DB", null, 1);
                dataBase.inserttwo(title, quantity, amount);
            }
        });




        int resId = activity.getResources().getIdentifier(image, "drawable", activity.getPackageName());
        Bitmap bitmap2 = BitmapFactory.decodeResource(activity.getResources(), resId);
        holder.imageView.setImageBitmap(bitmap2);

    }

    @Override
    public int getItemCount() {
        return tabledata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, description, price, tv_number, tv_amountpayable, tv_cart;
        ImageView imageView;
        ImageButton imageviewplus, imageviewminus, cart;

        public ViewHolder(final View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.tv_title);
            description = (TextView) itemView.findViewById(R.id.tv_description);
            price = (TextView) itemView.findViewById(R.id.tv_price);
            imageView = (ImageView) itemView.findViewById(R.id.iv_imageview);
            tv_number = itemView.findViewById(R.id.tv_number);
            tv_amountpayable = itemView.findViewById(R.id.tv_amountpayable);
            cart = itemView.findViewById(R.id.iv_cart);
            tv_cart = itemView.findViewById(R.id.tv_cartitems);
            imageviewplus = itemView.findViewById(R.id.iv_plus);
            imageviewminus = itemView.findViewById(R.id.iv_minus);


        }
    }
}
