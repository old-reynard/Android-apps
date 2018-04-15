package com.example.android.justjava;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;


/**
 * This app displays an order form to order coffee.
 */

public class MainActivity extends AppCompatActivity {

    int quantity = 1;
    boolean hasCreamTopping = false;
    boolean hasChocolateTopping = false;
    String customerName = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText et = (EditText)findViewById(R.id.name_field);
        et.setHint(getString(R.string.name_hint));
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice();
        String priceMessage = createOrderSummary(price);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_ref) + setName());
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

        displayMessage(priceMessage);
    }

    public void setToppings(View view) {
        CheckBox creamCb = findViewById(R.id.toppings_checkbox);
        hasCreamTopping = creamCb.isChecked();
        CheckBox chocolateCb = findViewById(R.id.chocolate_checkbox);
        hasChocolateTopping = chocolateCb.isChecked();
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView)findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private String setName() {
        EditText et = (EditText) findViewById(R.id.name_field);
        if (et.getText().toString().length() > 0)
            customerName = et.getText().toString();
        return customerName;
    }

    /**
     * This method calculates the price with the given quantity of cups.
     */
    private int calculatePrice() {
        int price = 5;
        if (hasCreamTopping) price += 1;
        if (hasChocolateTopping) price += 2;
        return quantity * price;
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity > 0)
            quantity--;
        else {
            Toast.makeText(this, getString(R.string.too_few_coffees_toast), Toast.LENGTH_SHORT).show();
        }
        display(quantity);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if (quantity < 20)
            quantity++;
        else {
            Toast.makeText(this, getString(R.string.too_many_coffees_toast), Toast.LENGTH_SHORT).show();
        }
        display(quantity);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView OrderTextView = (TextView)findViewById(R.id.order_summary_text_view);
        OrderTextView.setText(message);
    }

    public String createOrderSummary(int price) {
        String summary = getString(R.string.name) + " " + setName();
        summary += "\n" + getString(R.string.add_whipped_cream) + " " + (hasCreamTopping? getString(R.string.yas): getString(R.string.noooo));
        summary += "\n" + getString(R.string.add_chocolate) + " " + (hasChocolateTopping? getString(R.string.yas): getString(R.string.noooo));
        summary += "\n" + getString(R.string.Quantity) + ": " + quantity;
        summary += "\n" + getString(R.string.total) + ": $" + price;
        summary += "\n" + getString((R.string.thank_you));

        return summary;
    }
}
