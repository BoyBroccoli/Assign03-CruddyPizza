package com.example.assign03_cruddypizza;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class DatabaseActivity extends AppCompatActivity {

//    back button
    private ImageButton backBtn;

    private TextView showDbResultsTxtView;

    RecyclerView recyclerView;

//    database object
    DBAdapter myDB;
//    creating arrayList to display the db results
    ArrayList<String> customerId, name, address,
                        phoneNumber,toppingOne,toppingTwo,
                        toppingThree, pizzaSize, createdAt, updatedAt;
    FloatingActionButton add_button;
    CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        setUpAndLinkBtns();

        myDB = new DBAdapter(DatabaseActivity.this);
//        initializing array list
        customerId = new ArrayList<>();
        name = new ArrayList<>();
        address = new ArrayList<>();
        phoneNumber = new ArrayList<>();
        toppingOne = new ArrayList<>();
        toppingTwo = new ArrayList<>();
        toppingThree = new ArrayList<>();
        pizzaSize = new ArrayList<>();
        createdAt = new ArrayList<>();
        updatedAt = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(DatabaseActivity.this,this, customerId, name, createdAt, address,phoneNumber,pizzaSize,toppingOne,toppingTwo,toppingThree);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(DatabaseActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
//            refreshing DatabaseActivity
            recreate();
        }
    }


//    method to display database results
    void storeDataInArrays(){
        Cursor cursor = myDB.getEverythingFromCustomer();
        if(cursor.getCount() == 0) {
            Toast.makeText(this, "There is no data.", Toast.LENGTH_LONG).show();
        } else {
            while (cursor.moveToNext()) {

                customerId.add(cursor.getString(0));
                name.add(cursor.getString(1));
                address.add(cursor.getString(3));
                phoneNumber.add(cursor.getString(2));
                toppingOne.add(cursor.getString(4));
                toppingTwo.add(cursor.getString(5));
                toppingThree.add(cursor.getString(6));
                pizzaSize.add(cursor.getString(7));
                createdAt.add(cursor.getString(8));
                updatedAt.add(cursor.getString(9));

            }
        }
    }

    //    on click listener for back button to go back to order screen
    public View.OnClickListener backBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent orderScreenIntent = new Intent(DatabaseActivity.this, MainActivity.class);
            DatabaseActivity.this.startActivity(orderScreenIntent);
        }
    };


//    on click listener for showDbResults button
//    public View.OnClickListener showDatabaseListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//
//    //        recycler view for showing database results
//        }
//    };

//    on click listener for add_button
    public View.OnClickListener add_buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent (DatabaseActivity.this, AddActivity.class);
            startActivity(intent);
        }
    };

//    linking buttons and views
    public void setUpAndLinkBtns(){

    //        search database button
        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(backBtnListener);

//        show database results button
        showDbResultsTxtView = findViewById(R.id.textView);

//        recyclerviewer
        recyclerView = findViewById(R.id.recyclerView);

//        floating action button
        add_button = findViewById(R.id.floatingActionButton);
        add_button.setOnClickListener(add_buttonClickListener);

    }
}