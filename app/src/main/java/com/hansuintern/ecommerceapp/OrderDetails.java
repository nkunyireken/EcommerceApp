package com.hansuintern.ecommerceapp;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.Update;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OrderDetails extends AppCompatActivity {

    TextView Customer;
    TextView Item;
    TextView Quantity;
    TextView Amount;
    TextView Date;
    TextView Location;
    TextView Email;
    TextView Comment;
    TextView Contact;
    TextView Payment;
    int itemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        Customer = findViewById(R.id.nameDetails);
        Item = findViewById(R.id.itemDetails);
        Quantity = findViewById(R.id.quantityDetails);
        Amount = findViewById(R.id.amountDetails);
        Payment = findViewById(R.id.paymentDetails);
        Date = findViewById(R.id.dateDetails);
        Location = findViewById(R.id.locationDetails);
        Contact = findViewById(R.id.contactDetails);
        Email = findViewById(R.id.emailDetails);
        Comment = findViewById(R.id.commentDetails);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(intent.EXTRA_TEXT)){
            itemId = intent.getIntExtra(Intent.EXTRA_TEXT,-1);
            Order order = getDB().ecommerceDao().getSingleOrderById(itemId);
            UpdateUI(order);

        }
    }
    public void UpdateUI(Order order){
        Customer.setText(order.getCustomer());
        Item.setText(order.getItem());
        Quantity.setText(order.getQuantity());
        Amount.setText(order.getAmount());
        Payment.setText(order.getPayment());
        Date.setText(order.getDate());
        Location.setText(order.getLocation());
        Contact.setText(order.getContact());
        Email.setText(order.getEmail());
        Comment.setText(order.getComment());


    }
    public EcommerceDatabBase getDB(){
        String databaseName = "ecommerce_db";
        EcommerceDatabBase ecommerceDatabBase = Room.databaseBuilder(getApplicationContext(),EcommerceDatabBase.class,databaseName)
                .allowMainThreadQueries().build();
        return ecommerceDatabBase;
    }
}
