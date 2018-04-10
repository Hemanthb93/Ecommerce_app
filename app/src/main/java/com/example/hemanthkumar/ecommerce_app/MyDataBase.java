package com.example.hemanthkumar.ecommerce_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by hemanthkumar on 7/3/18.
 */

public class MyDataBase extends SQLiteOpenHelper {

    //1.Database version
    private static int DATABASE_VERSION = 1;
    //2.Database Name
    private static String Database_Name = "Bunch";
    //3.Table name
    private static String Table_Name = "Products";

    //4.Second Table Name
    private static String Table_Two = "Cartdata";

    private static String Two_title = "titletwo";
    private static String Two_Quantity = "quantitytwo";
    private static String Two_amount = "amounttwo";
    //5.Column names
    private static String Title_Name = "title";
    private static String Description = "description";
    private static String Price = "price";
    private static String Image = "image";
    private static String Quantity = "quantity";
    public Context context;

    //constructor
    public MyDataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //creating table here
        String CREATE_BUNCH_TABLE = "CREATE TABLE "
                + Table_Name + "("
                + Title_Name + " TEXT,"
                + Description + " TEXT,"
                + Price + " INTEGER,"
                + Quantity + " INTEGER,"
                + Image + " TEXT" + ")";

        String CREATE_CART_TABLE = "CREATE TABLE "
                + Table_Two + "("
                + Two_title + " TEXT,"
                + Two_Quantity + " INTEGER,"
                + Two_amount + " INTEGER" + ")";
        db.execSQL(CREATE_CART_TABLE);
        db.execSQL(CREATE_BUNCH_TABLE);


    }

//adding s
    public void insert(String title, String description, int _price, String _image, int quantity) {
        SQLiteDatabase db = this.getReadableDatabase();
        String _Insert = "Insert into " + Table_Name + "(" + Title_Name + "," + Description + "," + Price + "," + Quantity + ","
                + Image + ") values('" + title + "','" + description + "','" + _price + "','" + quantity + "','" + _image + "');";
        /*ContentValues contentValues = new ContentValues();
        contentValues.put(Title_Name, title);
        contentValues.put(Description, description);
        contentValues.put(Price,price);
        db.insertOrThrow(Table_Name, null, contentValues);*/
        db.execSQL(_Insert);
        db.close();

    }

    public void inserttwo(String titletwo, int quantitytwo, int amounttwo) {

        ArrayList<DataTwo> tabletwodata;
        tabletwodata = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String RETRIEVE_STUDENTS_INFO = "SELECT * from " + Table_Two;
        Cursor cursor = db.rawQuery(RETRIEVE_STUDENTS_INFO, null);
        if (cursor.moveToFirst()) {
            Log.d("", "getsomething2: " + cursor.getCount());
            do {
                String title = cursor.getString(cursor.getColumnIndex(Two_title));

                if (title.equals(titletwo)) {
                    update1(quantitytwo, amounttwo, title);
                    cursor.close();
                    db.close();
                    return;
                }
            } while (cursor.moveToNext());
        }


        String insert_two = "Insert into " + Table_Two + "(" + Two_title + "," + Two_Quantity + "," + Two_amount + ") " +
                "values('" + titletwo + "'," + quantitytwo + "," + amounttwo + ");";
        db.execSQL(insert_two);
        db.close();
    }

    public void delete(String titletwo) {
        SQLiteDatabase db = this.getWritableDatabase();
        Toast.makeText(context, "String " + titletwo, Toast.LENGTH_LONG).show();
        db.execSQL("delete from " + Table_Two + " where " + Two_title + " = '" + titletwo + "'");
        db.close();

    }

    public void update(String titletwo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Two_title, titletwo);
        db.update(MyDataBase.Table_Two, contentValues,
                null, null);
        db.close();
    }

    public void update1(int quantitytwo, int amounttwo, String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "update " + Table_Two
                + " set " + Two_Quantity + " = " + quantitytwo + " where " + Two_title + " = '" + title + "'";
        db.execSQL(query);
        db.close();

    }

    public ArrayList<Data> getData() {
        ArrayList<Data> tabledata;
        tabledata = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String RETRIEVE_STUDENTS_INFO = "SELECT * from " + Table_Name;
        Cursor cursor = db.rawQuery(RETRIEVE_STUDENTS_INFO, null);
        if (cursor.moveToFirst()) {
            do {
                String title = cursor.getString(cursor.getColumnIndex(Title_Name));
                String description = cursor.getString(cursor.getColumnIndex(Description));
                int price = cursor.getInt(cursor.getColumnIndex(Price));
                String image = cursor.getString(cursor.getColumnIndex(Image));
                int quantity = cursor.getInt(cursor.getColumnIndex(Quantity));
                Data data = new Data(title, description, image, quantity, price);
                tabledata.add(data);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tabledata;
    }


    public ArrayList<DataTwo> getsecondtabledata() {

        ArrayList<DataTwo> tabletwodata;
        tabletwodata = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String RETRIEVE_STUDENTS_INFO = "SELECT * from " + Table_Two;
        Cursor cursor = db.rawQuery(RETRIEVE_STUDENTS_INFO, null);
        if (cursor.moveToFirst()) {

            do {
                String title = cursor.getString(cursor.getColumnIndex(Two_title));
                int quantity = cursor.getInt(cursor.getColumnIndex(Two_Quantity));
                int price = cursor.getInt(cursor.getColumnIndex(Two_amount));
                DataTwo data = new DataTwo(title, quantity, price);
                tabletwodata.add(data);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tabletwodata;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
