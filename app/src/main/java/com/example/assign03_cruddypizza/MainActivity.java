package com.example.assign03_cruddypizza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.widget.Toast;


import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {


//    creating button objects
    private Button  smallSizeBtn , medSizeBtn  , largSizeBtn , xlSizeBtn ,
                    addCheeseBtn , subCheeseBtn , addChickenBtn , subChickenBtn,
                    addPepperoniBtn , subPepperoniBtn , addSausageBtn , subSausageBtn ,
                    addRedOnionBtn , subRedOnionBtn , addMushroomBtn , subMushroomBtn ,
                    addPineappleBtn , subPineappleBtn , addVeggiesBtn , subVeggiesBtn , orderBtn;

    private ImageButton searchDbBtn;
    private Switch toggleSwitch;


//    creating text view objects to display topping amounts
    private TextView cheeseAmntTxtView , chickenAmntTxtView , pepperoniAmntTxtView ,
                        sausageAmntTxtView , redOnionAmntTxtView , mushroomAmntTxtView ,
                        pineappleAmntTxtView , veggiesAmntTxtView;


//    creating text view objects to display prompts
    private TextView crustPromptTxtView , chooseToppingsPromptTxtView , toppingsPromptTxtView01 ,
                    toppingTxtView02 ,toppingTxtView03 , toppingTxtView04 , toppingTxtView05 ,
                    toppingTxtView06 , toppingTxtView07 , toppingTxtView08 ,
                    customerPromptTxtView01;

    private EditText customerQ1PlainText , customerQ2PhoneTxt,
            customerQ3PostalAddrTxt;

//     shared preference member variable
    SharedPreferences userLangPreference;
    private static final String LANG_PREF_KEY= "keyLanguage";
    public static String userLangPref = "";


//    for the amount of toppings chosen
    final int MAX_NUM_OF_TOPPINGS = 3;
    int userNumOfToppings = 0;


//    storing user crust size 1 = s, 2 = m , 3 = l , 4 = xl
    String userCrustSize = "";

//    Array for Storing Customer Info
    String[] customerInfo = new String[3];
    String[] selectedToppings = new String[3];

//    creating a dictionary for the toppings and its num of values
    HashMap<String,Integer> toppingsDict = new HashMap<String, Integer>(){{

//      Inserting toppings into dictionary
        put("CHEESE",0);
        put("CHICKEN",0);
        put("PEPPERONI",0);
        put("SAUSAGE",0);
        put("RED ONION",0);
        put("MUSHROOM",0);
        put("PINEAPPLE",0);
        put("VEGGIES",0);
}};


//    For storing the hash key and values when topping add/sub btn clicked
    String key = "";
    TextView view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        calling method to link all buttons and text views
        setUpAndLinkBtns();


//        lang pref toggle listener
        toggleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton button_view, boolean is_checked) {
                //        shared preference
                userLangPreference = getSharedPreferences(LANG_PREF_KEY, MODE_PRIVATE);
                SharedPreferences.Editor editor = userLangPreference.edit();

//                checking if toggle checked
                if (is_checked) {

                    userLangPref = "FRENCH";
                    setLanguage(userLangPref);
                    editor.putString(LANG_PREF_KEY, userLangPref);
                } else {

                    userLangPref = "ENGLISH";
                    setLanguage(userLangPref);
                    editor.putString(LANG_PREF_KEY, userLangPref);
                }

//                committing sharedpref
                editor.commit();
            }

        });

//        saving user language preference
        userLangPreference = getSharedPreferences(LANG_PREF_KEY, MODE_PRIVATE);
        userLangPref = userLangPreference.getString(LANG_PREF_KEY, "ENGLISH");
        setLanguage(userLangPref);

    }

//    method to set app text based off preference
    private void setLanguage(String langPref) {

        if (langPref.equals("ENGLISH")) {

            crustPromptTxtView.setText(R.string.crustPromptTxtView01);
            chooseToppingsPromptTxtView.setText(R.string.toppingsPromptTxtView01);
            toppingsPromptTxtView01.setText(R.string.toppingTxtView01);
            toppingTxtView02.setText(R.string.toppingTxtView02);
            toppingTxtView03.setText(R.string.toppingTxtView03);
            toppingTxtView04.setText(R.string.toppingTxtView04);
            toppingTxtView05.setText(R.string.toppingTxtView05);
            toppingTxtView06.setText(R.string.toppingTxtView06);
            toppingTxtView07.setText(R.string.toppingTxtView07);
            toppingTxtView08.setText(R.string.toppingTxtView08);
            customerPromptTxtView01.setText(R.string.customerPromptTxtView01);
            customerQ1PlainText.setHint(R.string.customerQ1PlainText01);
            customerQ2PhoneTxt.setHint(R.string.customerQ2TxtPhone01);
            customerQ3PostalAddrTxt.setHint(R.string.customerQ3TxtPostalAddress01);
            orderBtn.setText(R.string.orderBtn01);
        } else {

            crustPromptTxtView.setText(R.string.crustPromptTxtView011);
            chooseToppingsPromptTxtView.setText(R.string.toppingsPromptTxtView011);
            toppingsPromptTxtView01.setText(R.string.toppingTxtView011);
            toppingTxtView02.setText(R.string.toppingTxtView022);
            toppingTxtView03.setText(R.string.toppingTxtView033);
            toppingTxtView04.setText(R.string.toppingTxtView044);
            toppingTxtView05.setText(R.string.toppingTxtView055);
            toppingTxtView06.setText(R.string.toppingTxtView066);
            toppingTxtView07.setText(R.string.toppingTxtView077);
            toppingTxtView08.setText(R.string.toppingTxtView088);
            customerPromptTxtView01.setText(R.string.customerPromptTxtView011);
            customerQ1PlainText.setHint(R.string.customerQ1PlainText011);
            customerQ2PhoneTxt.setHint(R.string.customerQ2TxtPhone011);
            customerQ3PostalAddrTxt.setHint(R.string.customerQ3TxtPostalAddress011);
            orderBtn.setText(R.string.orderBtn011);
        }
    }


// ON CLICK LISTENERS

//    on click listener for searching the database and going to next intent
    public View.OnClickListener searchDataBaseListener = new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Intent databaseIntent = new Intent(MainActivity.this, DatabaseActivity.class);
        MainActivity.this.startActivity(databaseIntent);
    }
};


    //    on click listener for the crust size
    public View.OnClickListener crustSizeListener = new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.smallSizeBtn:
                setCrustSize(smallSizeBtn);
                break;
            case R.id.medSizeBtn:
                setCrustSize(medSizeBtn);
                break;
            case R.id.largSizeBtn:
                setCrustSize(largSizeBtn);
                break;
            case R.id.xlSizeBtn:
                setCrustSize(xlSizeBtn);
                break;
            default:
                userCrustSize = "s";
                break;

        }
    }
};



//    on click listener for topping choices
    public View.OnClickListener toppingsChoiceListener = new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.addCheeseBtn:
                key = "CHEESE";
                view = cheeseAmntTxtView;
//                add
                setToppingTxtView(key, view, true);
                break;

            case R.id.subCheeseBtn:
                key = "CHEESE";
                view = cheeseAmntTxtView;
//                sub
                setToppingTxtView(key, view, false);

                break;

            case R.id.addChickenBtn:
                key = "CHICKEN";
                view = chickenAmntTxtView;
//                add
                setToppingTxtView(key, view, true);
                break;

            case R.id.subChickenBtn:
                key = "CHICKEN";
                view = chickenAmntTxtView;
//                sub
                setToppingTxtView(key, view, false);
                break;

            case R.id.addPepperoniBtn:
                key = "PEPPERONI";
                view = pepperoniAmntTxtView;
//                add
                setToppingTxtView(key, view, true);
                break;

            case R.id.subPepperoniBtn:
                key = "PEPPERONI";
                view = pepperoniAmntTxtView;
//                sub
                setToppingTxtView(key, view, false);
                break;

            case R.id.addSausageBtn:
                key = "SAUSAGE";
                view = sausageAmntTxtView;
//                add
                setToppingTxtView(key, view, true);
                break;

            case R.id.subSausageBtn:
                key = "SAUSAGE";
                view = sausageAmntTxtView;
//                sub
                setToppingTxtView(key, view, false);
                break;

            case R.id.addRedOnionBtn:
                key = "RED ONION";
                view = redOnionAmntTxtView;
//                add
                setToppingTxtView(key, view, true);
                break;

            case R.id.subRedOnionBtn:
                key = "RED ONION";
                view = redOnionAmntTxtView;
//                sub
                setToppingTxtView(key, view, false);
                break;

            case R.id.addMushroomBtn:
                key = "MUSHROOM";
                view = mushroomAmntTxtView;
//                add
                setToppingTxtView(key, view, true);
                break;

            case R.id.subMushroomBtn:
                key = "MUSHROOM";
                view = mushroomAmntTxtView;
//                sub
                setToppingTxtView(key, view, false);
                break;

            case R.id.addPineappleBtn:
                key = "PINEAPPLE";
                view = pineappleAmntTxtView;
//                add
                setToppingTxtView(key, view, true);
                break;

            case R.id.subPineappleBtn:
                key = "PINEAPPLE";
                view = pineappleAmntTxtView;
//                sub
                setToppingTxtView(key, view, false);
                break;

            case R.id.addVeggiesBtn:
                key = "VEGGIES";
                view = veggiesAmntTxtView;
//                add
                setToppingTxtView(key, view, true);
                break;

            case R.id.subVeggiesBtn:
                key = "VEGGIES";
                view = veggiesAmntTxtView;
//                sub
                setToppingTxtView(key, view, false);
                break;

            default:
                break;
        }
    }
};




//    on click listener for storing customer name
    public View.OnClickListener customerInfoListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch(v.getId()){

                case(R.id.customerQ1PlainTxt01):
                    String customerName = customerQ1PlainText.getText().toString();
                    customerInfo[0] = customerName;
                    break;

                case(R.id.customerQ2TxtPhone01):
                    String customerNumber = customerQ2PhoneTxt.getText().toString();
                    customerInfo[2] = customerNumber;
                    break;

                case(R.id.customerQ3TxtPostalAddress):
                    String customerAddress = customerQ3PostalAddrTxt.getText().toString();
                    customerInfo[1] = customerAddress;
                    break;
                default:
                    break;

            }
        }
    };



//    on click listener for order button
    public View.OnClickListener orderBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            storeToppingResults(toppingsDict, selectedToppings);
            for(String cus: customerInfo) {
                System.out.println(cus);
            }
            // method to add to database entry
            DBAdapter myDB = new DBAdapter(MainActivity.this);
            myDB.open();
            myDB.insertCustomer(customerInfo[0],customerInfo[1],customerInfo[2],userCrustSize,selectedToppings[0],selectedToppings[1],selectedToppings[2]);
            myDB.close();
            // method to reset the textViews
            // display a toast message that the customer info ws added successfully
        }
    };


//    METHODS

//    method to store hashmap values greater than 0
    public void storeToppingResults(HashMap<String, Integer> toppingDict, String[] selectedToppings) {

        int i = 0;

//        for each to loop through hashMap
        for(Map.Entry<String, Integer> entry : toppingDict.entrySet()) {

            if (entry.getValue() > 0 ) {

                if (entry.getValue() == 1){

                    selectedToppings[i] = entry.getKey();

                }else if (entry.getValue() == 2) {

                    selectedToppings[1] = entry.getKey();
                    selectedToppings[2] = entry.getKey();

                } else if (entry.getValue() == 3) {

                    selectedToppings[0] = entry.getKey();
                    selectedToppings[1] = entry.getKey();
                    selectedToppings[2] = entry.getKey();
                }

                i++;
            }

        }
    }

//    method to set topping text view
    public void setToppingTxtView(String key, TextView view, boolean addition){

//        storing dict topping value
        int toppingAmount = toppingsDict.get(key);

//        storing topping text view amount
        int viewAmnt = Integer.parseInt(view.getText().toString());

//        if the addition is set to True that means add
        if (addition == true) {
            if ( userNumOfToppings >= 3) {
                displayToastTooManyToppingError();
            } else {
                incraseToppingCount();
                viewAmnt++;
                toppingAmount++;
                toppingsDict.put(key,toppingAmount);
                view.setText(Integer.toString(viewAmnt));
            }
        } else {
            if (viewAmnt <= 0 && toppingAmount <= 0) {
                displayToastTooLittleToppingError();
            } else {
                decreaseToppingCount();
                viewAmnt--;
                toppingAmount--;
                toppingsDict.put(key,toppingAmount);
                view.setText(Integer.toString(viewAmnt));
            }
        }
    }




// method to set crust size
    public void setCrustSize(Button crustBtn){

        String buttonTxt = crustBtn.getText().toString();
        userCrustSize = buttonTxt;

    }

// method to get customer info
    public static String[] getCustomerInfo(String[] cusInfo) {
        return cusInfo;
    }

//    method to increase topping count
    public void incraseToppingCount(){


        if (userNumOfToppings >= MAX_NUM_OF_TOPPINGS) {
//            if user tries to select more than 3 toppings will show toast error
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_LONG;
            CharSequence toppingErrorMessage = "3 toppings is the max.";
            Toast toast = Toast.makeText(context, toppingErrorMessage, duration);
            toast.show();
        } else {
            userNumOfToppings++;
        }
    }





//    method to toast if more than 3 toppings is selected
    public void displayToastTooManyToppingError(){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        CharSequence toppingErrorMessage = "3 toppings is the max.";
        Toast toast = Toast.makeText(context, toppingErrorMessage, duration);
        toast.show();
    }





//    method to display tooLittleToppingsError
    public void displayToastTooLittleToppingError(){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        CharSequence toppingErrorMessage = "You must select at least 1 topping.";
        Toast toast = Toast.makeText(context, toppingErrorMessage, duration);
        toast.show();
    }




//    method to decrease topping count
    public void decreaseToppingCount(){

         if (userNumOfToppings <= 0) {
            //            if user tries to select more than 3 toppings will show toast error
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_LONG;
            CharSequence toppingErrorMessage = "You must select at least 1 topping.";
            Toast toast = Toast.makeText(context, toppingErrorMessage, duration);
            toast.show();
        } else {
            userNumOfToppings--;
        }
    }




//    method to link java buttons with xml
    public void setUpAndLinkBtns(){

//        search database button
        searchDbBtn = findViewById(R.id.searchDbBtn);
        searchDbBtn.setOnClickListener(searchDataBaseListener);


//        linking crust size buttons
        smallSizeBtn = findViewById(R.id.smallSizeBtn);
        smallSizeBtn.setOnClickListener(crustSizeListener);

        medSizeBtn = findViewById(R.id.medSizeBtn);
        medSizeBtn.setOnClickListener(crustSizeListener);

        largSizeBtn = findViewById(R.id.largSizeBtn);
        largSizeBtn.setOnClickListener(crustSizeListener);

        xlSizeBtn = findViewById(R.id.xlSizeBtn);
        xlSizeBtn.setOnClickListener(crustSizeListener);

//        linking topping choice buttons
//        cheese
        addCheeseBtn = findViewById(R.id.addCheeseBtn);
        addCheeseBtn.setOnClickListener(toppingsChoiceListener);
        subCheeseBtn = findViewById(R.id.subCheeseBtn);
        subCheeseBtn.setOnClickListener(toppingsChoiceListener);
//        chicken
        addChickenBtn = findViewById(R.id.addChickenBtn);
        addChickenBtn.setOnClickListener(toppingsChoiceListener);
        subChickenBtn = findViewById(R.id.subChickenBtn);
        subChickenBtn.setOnClickListener(toppingsChoiceListener);
//        pepperoni
        addPepperoniBtn = findViewById(R.id.addPepperoniBtn);
        addPepperoniBtn.setOnClickListener(toppingsChoiceListener);
        subPepperoniBtn = findViewById(R.id.subPepperoniBtn);
        subPepperoniBtn.setOnClickListener(toppingsChoiceListener);
//        sausage
        addSausageBtn = findViewById(R.id.addSausageBtn);
        addSausageBtn.setOnClickListener(toppingsChoiceListener);
        subSausageBtn = findViewById(R.id.subSausageBtn);
        subSausageBtn.setOnClickListener(toppingsChoiceListener);
//        red onion
        addRedOnionBtn = findViewById(R.id.addRedOnionBtn);
        addRedOnionBtn.setOnClickListener(toppingsChoiceListener);
        subRedOnionBtn = findViewById(R.id.subRedOnionBtn);
        subRedOnionBtn.setOnClickListener(toppingsChoiceListener);
//        mushroom
        addMushroomBtn = findViewById(R.id.addMushroomBtn);
        addMushroomBtn.setOnClickListener(toppingsChoiceListener);
        subMushroomBtn = findViewById(R.id.subMushroomBtn);
        subMushroomBtn.setOnClickListener(toppingsChoiceListener);
//        pineapple
        addPineappleBtn = findViewById(R.id.addPineappleBtn);
        addPineappleBtn.setOnClickListener(toppingsChoiceListener);
        subPineappleBtn = findViewById(R.id.subPineappleBtn);
        subPineappleBtn.setOnClickListener(toppingsChoiceListener);
//        veggies
        addVeggiesBtn = findViewById(R.id.addVeggiesBtn);
        addVeggiesBtn.setOnClickListener(toppingsChoiceListener);
        subVeggiesBtn = findViewById(R.id.subVeggiesBtn);
        subVeggiesBtn.setOnClickListener(toppingsChoiceListener);
//        order button
        orderBtn = findViewById(R.id.orderBtn01);
        orderBtn.setOnClickListener(orderBtnListener);

//        linking topping amounts textViews
        cheeseAmntTxtView = findViewById(R.id.cheeseAmntTxtView);
        chickenAmntTxtView = findViewById(R.id.chickenAmntTxtView);
        pepperoniAmntTxtView = findViewById(R.id.pepperoniAmntTxtView);
        sausageAmntTxtView = findViewById(R.id.sausageAmntTxtView);
        redOnionAmntTxtView = findViewById(R.id.redOnionAmntTxtView);
        mushroomAmntTxtView = findViewById(R.id.mushroomAmntTxtView);
        pineappleAmntTxtView = findViewById(R.id.pineappleTxtView);
        veggiesAmntTxtView = findViewById(R.id.veggiesAmntTxtView);

//        linking toppings choices textViews
        crustPromptTxtView = findViewById(R.id.crustPromptTxtView01);
        chooseToppingsPromptTxtView = findViewById(R.id.toppingsPromptTxtView01);
        toppingsPromptTxtView01 = findViewById(R.id.toppingTxtView01);
        toppingTxtView02 = findViewById(R.id.toppingTxtView02);
        toppingTxtView03 = findViewById(R.id.toppingTxtView03);
        toppingTxtView04 = findViewById(R.id.toppingTxtView04);
        toppingTxtView05 = findViewById(R.id.toppingTxtView05);
        toppingTxtView06 = findViewById(R.id.toppingTxtView06);
        toppingTxtView07 = findViewById(R.id.toppingTxtView07);
        toppingTxtView08 = findViewById(R.id.toppingTxtView08);

//        linking customer promprt textViews and plain text
        customerPromptTxtView01 = findViewById(R.id.customerPromptTxtView01);

        customerQ1PlainText = findViewById(R.id.customerQ1PlainTxt01);
        customerQ1PlainText.setOnClickListener(customerInfoListener);

        customerQ2PhoneTxt = findViewById(R.id.customerQ2TxtPhone01);
        customerQ2PhoneTxt.setOnClickListener(customerInfoListener);

        customerQ3PostalAddrTxt = findViewById(R.id.customerQ3TxtPostalAddress);
        customerQ3PostalAddrTxt.setOnClickListener(customerInfoListener);

//        switch
        toggleSwitch = findViewById(R.id.toggleSwitch);
    }
}