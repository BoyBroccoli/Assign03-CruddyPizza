package com.example.assign03_cruddypizza;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    Spinner dropDownPizzaSize, dropDownToppingOne, dropDownToppingTwo, dropDownToppingThree;

    private EditText customerQ1PlainText_input ,
            customerQ2PhoneTxt_input,
            customerQ3PostalAddrTxt_input;
    Button update_btn, delete_btn;

    String id, name, address, phoneNumber, pizzaSize, toppingOne, toppingTwo, toppingThree;

    //    string arrays to fill in the drop down menus

    String[] pizzaSizes = {"S","M","L","XL"};

    String[] toppingsEn = {"CHEESE","PEPPERONI",
            "SAUSAGE","CHICKEN",
            "Red Onion","Mushroom",
            "Pineapple","VEGGIES"};

    //    Array for Storing Customer Info
    String[] customerInfo = new String[3];


    String[] pizzaInfo = new String[4];

    //    array addapters for drop downs
    ArrayAdapter<String> adapterPizzaSize,
            adapterToppingsOne,
            adapterToppingsTwo,
            adapterToppingsThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

//        for customer info

        customerQ1PlainText_input = (EditText) findViewById(R.id.addCustomerName2);

        customerQ2PhoneTxt_input = (EditText) findViewById(R.id.addCustomerNumber2);

        customerQ3PostalAddrTxt_input = (EditText) findViewById(R.id.addCustomerAddress2);

//        for the spinners
        dropDownPizzaSize = findViewById(R.id.pizzaSize2);
        dropDownToppingOne = findViewById(R.id.toppingOne2);
        dropDownToppingTwo = findViewById(R.id.toppingTwo2);
        dropDownToppingThree = findViewById(R.id.toppingThree2);
        DBAdapter myDB = new DBAdapter(UpdateActivity.this);
        myDB.open();


//        for populating pizza size spinner
        adapterPizzaSize = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, pizzaSizes);
        dropDownPizzaSize.setAdapter(adapterPizzaSize);

//        on click listener for pizza size
        dropDownPizzaSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pizzaInfo[0] = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                pizzaInfo[0] = "S";
            }
        });

//        for populating toppings spinner
        adapterToppingsOne = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, toppingsEn);
        dropDownToppingOne.setAdapter(adapterToppingsOne);
        //        on click listener for pizza toppingOne
        dropDownToppingOne.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pizzaInfo[1] = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                pizzaInfo[1] = "CHEESE";
            }
        });


        adapterToppingsTwo = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, toppingsEn);
        dropDownToppingTwo.setAdapter(adapterToppingsTwo);
        //        on click listener for pizza toppingTwo
        dropDownToppingTwo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pizzaInfo[2] = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                pizzaInfo[2] = "CHEESE";
            }
        });

        adapterToppingsThree = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, toppingsEn);
        dropDownToppingThree.setAdapter(adapterToppingsThree);
        //        on click listener for pizza toppingThree
        dropDownToppingThree.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pizzaInfo[3] = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                pizzaInfo[3] = "CHEESE";
            }
        });


        customerQ1PlainText_input.setOnClickListener(customerInfoListener);
        customerQ2PhoneTxt_input.setOnClickListener(customerInfoListener);
        customerQ3PostalAddrTxt_input.setOnClickListener(customerInfoListener);

        getAndSetIntentData();

//        for setting action bar title after getAndSetIntentData
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name);
        }



        update_btn = findViewById(R.id.update_btn);
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int cusIdToInt = Integer.parseInt(id);
                myDB.updateCustomer(cusIdToInt,customerInfo[0],customerInfo[2],customerInfo[1],pizzaInfo[0],pizzaInfo[1],pizzaInfo[2],pizzaInfo[3]);
                myDB.close();
            }
        });

        delete_btn = findViewById(R.id.delete_btn);
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                confirmDialog();
            }
        });


    }

    //    on click listener for getting customer info
    public View.OnClickListener customerInfoListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch(v.getId()){

                case(R.id.addCustomerName2):
                    String customerName = customerQ1PlainText_input.getText().toString();
                    customerInfo[0] = customerName;
                    break;

                case(R.id.addCustomerNumber2):
                    String customerNumber = customerQ2PhoneTxt_input.getText().toString();
                    customerInfo[2] = customerNumber;
                    break;

                case(R.id.addCustomerAddress2):
                    String customerAddress = customerQ3PostalAddrTxt_input.getText().toString();
                    customerInfo[1] = customerAddress;
                    break;
                default:
                    break;

            }
        }
    };

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id")
                && getIntent().hasExtra("name")
                && getIntent().hasExtra("address")
                && getIntent().hasExtra("phoneNumber")
                && getIntent().hasExtra("pizzaSize")
                && getIntent().hasExtra("toppingOne")
                && getIntent().hasExtra("toppingTwo")
                && getIntent().hasExtra("toppingThree")) {

//            getting intent Data
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            customerInfo[0] = name;
            address = getIntent().getStringExtra("address");
            customerInfo[1] = address;
            phoneNumber = getIntent().getStringExtra("phoneNumber");
            customerInfo[2] = phoneNumber;
            toppingOne = getIntent().getStringExtra("toppingOne");
            toppingTwo = getIntent().getStringExtra("toppingTwo");
            toppingThree = getIntent().getStringExtra("toppingThree");
            pizzaSize = getIntent().getStringExtra("pizzaSize");

//            setting intent Data
            customerQ1PlainText_input.setText(name);
            customerQ3PostalAddrTxt_input.setText(address);
            customerQ2PhoneTxt_input.setText(phoneNumber);

//            setting intent data for spenner selections
            // for pizza size
            if (pizzaSize != null) {
                int spinnerPosition = adapterPizzaSize.getPosition(pizzaSize);
                dropDownPizzaSize.setSelection(spinnerPosition);
            }
            // for topping one
            if (dropDownToppingOne != null) {
                int spinnerPosition = adapterToppingsOne.getPosition(toppingOne);
                dropDownToppingOne.setSelection(spinnerPosition);
            }
            // for topping two
            if (dropDownToppingTwo != null) {
                int spinnerPosition = adapterToppingsTwo.getPosition(toppingTwo);
                dropDownToppingTwo.setSelection(spinnerPosition);
            }
            // for topping Three
            if (dropDownToppingThree != null) {
                int spinnerPosition = adapterToppingsThree.getPosition(toppingThree);
                dropDownToppingThree.setSelection(spinnerPosition);
            }

        } else {
            Toast.makeText(this, "No Data", Toast.LENGTH_LONG).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?" );
        builder.setMessage("Are you sure you want to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                int cusIdToInt = Integer.parseInt(id);
                DBAdapter myDB = new DBAdapter(UpdateActivity.this);
                myDB.open();
                myDB.deleteCustomer(id);
                myDB.close();
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

}