package com.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    String[] addresses = {"gildana33@gmail.com"};
    String subject = "Order From Music Shop";
    String emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        setTitle("Your Order");

        Intent receivedOrderIntent = getIntent();
        String userName = receivedOrderIntent.getStringExtra("userNameForIntent");
        String goodsName = receivedOrderIntent.getStringExtra("goodsNameForIntent");
        int quantity = receivedOrderIntent.getIntExtra("quantityForIntent",0);
        double orderPrice = receivedOrderIntent.getDoubleExtra("orderPriceForIntent",0);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView orderTextView = findViewById(R.id.orderTextView);

        emailText = "Customer Name: " + userName + "\n" +
                "Goods name: " + goodsName + "\n" +
                "Quantity: " + quantity + "\n" +
                "Price: " + orderPrice/quantity+"\n" +
                "Order price: " + orderPrice;

        orderTextView.setText(emailText);

    }
    public void submitOrder(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, emailText);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}