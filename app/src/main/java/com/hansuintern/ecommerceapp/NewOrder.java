package com.hansuintern.ecommerceapp;

import android.app.Dialog;
import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class NewOrder extends AppCompatActivity {
    EditText customerName;
    EditText itemName;
    EditText itemQuantity;
    EditText itemAmount;
    TextView orderDate;
    EditText customerLocation;
    EditText customerContact;
    EditText customerEmail;
    EditText customerComment;
    DatePicker mDatePicker;

    String customer;
    String item;
    String quantity;
    String amount;
    String date;
    String location;
    String email;
    String comment;
    String contact;
    String payment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);
        customerName = findViewById(R.id.customerName);
        itemName = findViewById(R.id.itemName);
        itemQuantity = findViewById(R.id.itemQuantity);
        itemAmount = findViewById(R.id.orderAmount);
        orderDate = findViewById(R.id.orderDate);
        customerLocation = findViewById(R.id.customerLocation);
        customerContact = findViewById(R.id.customerContact);
        customerEmail = findViewById(R.id.customerEmail);
        customerComment = findViewById(R.id.customerComment);


    }

    public void chooseModeOfPayment(View view) {

        if (view.getId() == R.id.mobileMoney) {
            payment = "Mobile Money";

        }
        if (view.getId() == R.id.creditCard) {
            payment = "Credit Card";
        }


    }
    //Method to initiate Date Picker dialog

    public void createDateDialog(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        View view = LayoutInflater.from(this). inflate(R.layout.date_dialog,null);
        mDatePicker = view.findViewById(R.id.datePicker);
        mDatePicker.init(year,month,day,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.app_name)
                .setView(view)
                .setPositiveButton("Pick Date", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int year = mDatePicker.getYear();
                        int month = mDatePicker.getMonth();
                        int day = mDatePicker.getDayOfMonth();
                     String  date = String.valueOf(year+"-"+month+"-"+day);
                      orderDate.setText(date);
                    }
                });
        Dialog dialog = builder.create();
        dialog.show();
    }
    public void createDialog(View view){
        createDateDialog();
    }


    public void
    makeOrder(View view) {
        customer = customerName.getText().toString();
        item = itemName.getText().toString();
        quantity = itemQuantity.getText().toString();
        amount = itemAmount.getText().toString();
        date = orderDate.getText().toString();
        location = customerLocation.getText().toString();
        email = customerEmail.getText().toString();
        comment =customerComment.getText().toString();
        contact =customerContact.getText().toString();

        if (!customer.isEmpty() && !item.isEmpty() && ! quantity.isEmpty() && !amount.isEmpty() && !date.isEmpty() &&
                !location.isEmpty() && !email.isEmpty() && !comment.isEmpty() && !contact.isEmpty() ){
            Order order = new Order(customer,item,quantity,amount,date,location,email,comment,contact,payment);
            getDB().ecommerceDao().insertOrder(order);


            Toast.makeText(this,"customer" + order.getCustomer(),Toast.LENGTH_SHORT).show();
            finish();
        }else {
            Toast.makeText(this,"please enter all fields",Toast.LENGTH_SHORT).show();

        }
    }
    public EcommerceDatabBase getDB(){
        String databaseName = "ecommerce_db";
        EcommerceDatabBase ecommerceDatabBase = Room.databaseBuilder(getApplicationContext(),EcommerceDatabBase.class,databaseName)
                .allowMainThreadQueries().build();
        return ecommerceDatabBase;
    }
}
