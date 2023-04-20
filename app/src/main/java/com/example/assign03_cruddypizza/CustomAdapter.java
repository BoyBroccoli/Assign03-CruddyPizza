package com.example.assign03_cruddypizza;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {


    private Context context;
    Activity activity;
    private ArrayList<String> customerId, name, address,
            phoneNumber,toppingOne,toppingTwo,
            toppingThree, pizzaSize, createdAt, updatedAt;

//    constructor for Adapter
    CustomAdapter(Activity activity, Context context,
                  ArrayList customerId,
                  ArrayList name,
                  ArrayList createdAt,
                  ArrayList address,
                  ArrayList phoneNumber,
                  ArrayList pizzaSize,
                  ArrayList toppingOne,
                  ArrayList toppingTwo,
                  ArrayList toppingThree
                  ) {
        this.activity = activity;
        this.context = context;
        this.customerId = customerId;
        this.name = name;
        this.createdAt = createdAt;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.pizzaSize = pizzaSize;
        this.toppingOne = toppingOne;
        this.toppingTwo = toppingTwo;
        this.toppingThree = toppingThree;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView customerId, name, address,
                phoneNumber,toppingOne,toppingTwo,
                toppingThree, pizzaSize, createdAt, updatedAt;

        ConstraintLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            customerId = itemView.findViewById(R.id.customerId);
            name = itemView.findViewById(R.id.customerName);
            createdAt = itemView.findViewById(R.id.createdAt);
            address = itemView.findViewById(R.id.address);
            phoneNumber = itemView.findViewById(R.id.phoneNum);
            toppingOne = itemView.findViewById(R.id.toppingOne);
            toppingTwo = itemView.findViewById(R.id.toppingTwo);
            toppingThree = itemView.findViewById(R.id.toppingThree);
            pizzaSize = itemView.findViewById(R.id.pizzaSize);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //        setting view layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);

        return new MyViewHolder(view);
    }

//    gets called each time a new row is about to be displayed in RecyclerView
    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {

//        setting the textViews text with whats stored in the arrays.

        holder.customerId.setText(String.valueOf(customerId.get(position)));
        holder.name.setText(String.valueOf(name.get(position)));
        holder.createdAt.setText(String.valueOf(createdAt.get(position)));
        holder.toppingOne.setText(String.valueOf(toppingOne.get(position)));
        holder.toppingTwo.setText(String.valueOf(toppingTwo.get(position)));
        holder.toppingThree.setText(String.valueOf(toppingThree.get(position)));
        holder.address.setText(String.valueOf(address.get(position)));
        holder.phoneNumber.setText(String.valueOf(phoneNumber.get(position)));
        holder.pizzaSize.setText(String.valueOf(pizzaSize.get(position)));


//        on click listener for recycler when clicked, will start the UpdateActivity class.
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                creating an intent to move to updateActivity
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(customerId.get(position)));
                intent.putExtra("name", String.valueOf(name.get(position)));
                intent.putExtra("address", String.valueOf(address.get(position)));
                intent.putExtra("phoneNumber", String.valueOf(phoneNumber.get(position)));
                intent.putExtra("pizzaSize", String.valueOf(pizzaSize.get(position)));
                intent.putExtra("toppingOne", String.valueOf(toppingOne.get(position)));
                intent.putExtra("toppingTwo", String.valueOf(toppingTwo.get(position)));
                intent.putExtra("toppingThree", String.valueOf(toppingThree.get(position)));

//                to refresh the activity with database
                activity.startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return customerId.size();
    }
}
