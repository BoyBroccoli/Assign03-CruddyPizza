package com.example.assign03_cruddypizza;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

//    add entry button
    private Button addEntryBtn;

//    Variables for AutoComplete TextViews
    Spinner dropDownPizzaSize, dropDownToppingOne, dropDownToppingTwo, dropDownToppingThree;

    private EditText customerQ1PlainText ,
                    customerQ2PhoneTxt,
                    customerQ3PostalAddrTxt;

    //    Array for Storing Customer Info
    String[] customerInfo = new String[3];


    String[] pizzaInfo = new String[4];


//    string arrays to fill in the drop down menus

    String[] pizzaSizes = {"S","M","L","XL"};

    String[] toppingsEn = {"CHEESE","PEPPERONI",
                            "SAUSAGE","CHICKEN",
                            "Red Onion","Mushroom",
                            "Pineapple","VEGGIES"};

    String[] toppingsFr = {"Fromage","Pepperoni",
                            "Saucisse","Poulet",
                            "Oignon Rouge","Champignon",
                            "Ananas","Légumes"};

//    array addapters for drop downs
    ArrayAdapter<String> adapterPizzaSize,
                        adapterToppingsOne,
                        adapterToppingsTwo,
                        adapterToppingsThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        linkButtons();

        DBAdapter myDB = new DBAdapter(AddActivity.this);
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


        customerQ1PlainText.setOnClickListener(customerInfoListener);
        customerQ2PhoneTxt.setOnClickListener(customerInfoListener);
        customerQ3PostalAddrTxt.setOnClickListener(customerInfoListener);


    }



//    on click listener for getting customer info
    public View.OnClickListener customerInfoListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch(v.getId()){

                case(R.id.addCustomerName):
                    String customerName = customerQ1PlainText.getText().toString();
                    customerInfo[0] = customerName;
                    break;

                case(R.id.addCustomerNumber):
                    String customerNumber = customerQ2PhoneTxt.getText().toString();
                    customerInfo[2] = customerNumber;
                    break;

                case(R.id.addCustomerAddress):
                    String customerAddress = customerQ3PostalAddrTxt.getText().toString();
                    customerInfo[1] = customerAddress;
                    break;
                default:
                    break;

            }
        }
    };


//     click listener for add button
    public View.OnClickListener addButtonListenr = new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        //        creating databse helper obj
        DBAdapter myDB = new DBAdapter(AddActivity.this);
        myDB.open();
        myDB.insertCustomer(customerInfo[0],customerInfo[1],customerInfo[2],pizzaInfo[0],pizzaInfo[1],pizzaInfo[2],pizzaInfo[3]);
//        myDB.insertCustomer(pizzaInfo[0],customerInfo[2],customerInfo[1],pizzaInfo[3],customerInfo[0],pizzaInfo[1],pizzaInfo[2]);
        myDB.close();

    }
};

    public void linkButtons(){

//        for add button
        addEntryBtn = findViewById(R.id.add_button);
        addEntryBtn.setOnClickListener(addButtonListenr);
//        for customer info

        customerQ1PlainText = (EditText) findViewById(R.id.addCustomerName);

        customerQ2PhoneTxt = (EditText) findViewById(R.id.addCustomerNumber);

        customerQ3PostalAddrTxt = (EditText) findViewById(R.id.addCustomerAddress);

//        for the spinners
        dropDownPizzaSize = findViewById(R.id.pizzaSize);
        dropDownToppingOne = findViewById(R.id.toppingOne);
        dropDownToppingTwo = findViewById(R.id.toppingTwo);
        dropDownToppingThree = findViewById(R.id.toppingThree);
    }
}