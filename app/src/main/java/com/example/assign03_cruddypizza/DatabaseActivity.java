package com.example.assign03_cruddypizza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class DatabaseActivity extends AppCompatActivity {

//    back button
    private ImageButton backBtn;

    private Button showDbResults;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        setUpAndLinkBtns();
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
    public View.OnClickListener showDatabaseListener = new View.OnClickListener() {
    @Override
    public void onClick(View v) {

//        recycler view for showing database results
    }
};

//    linking buttons and views
    public void setUpAndLinkBtns(){

    //        search database button
        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(backBtnListener);

//        show database results button
        showDbResults = findViewById(R.id.showDbBtn);
        showDbResults.setOnClickListener(showDatabaseListener);

    }
}