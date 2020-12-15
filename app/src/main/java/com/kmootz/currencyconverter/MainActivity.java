// MainActivity.java - Currency converter converts between USD and EUR
// Created by: Konner Mootz
// Date: 10/25/2020
// Week 7 (Final Exam Part 2)
// Class: CSC 315 Mobile App Development (Instructor: James Tucker)

package com.kmootz.currencyconverter;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button convertBtn = findViewById(R.id.convertBtn);
        Button clearBtn = findViewById(R.id.clearBtn);
        final TextView tvIsEmptyMessage = findViewById(R.id.tvIsEmptyMessage);

        convertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText etUSD = findViewById(R.id.etUSD);
                EditText etEUR = findViewById(R.id.etEUR);
                String USD = etUSD.getText().toString();
                String EUR = etEUR.getText().toString();

                boolean usdIsEmpty = USD.trim().isEmpty();
                boolean eurIsEmpty = EUR.trim().isEmpty();


                if (usdIsEmpty && eurIsEmpty) {
                    Toast.makeText(MainActivity.this, "ERROR!", Toast.LENGTH_SHORT).show();
                    tvIsEmptyMessage.setText(R.string.one_field_must_not_be_empty);
                    etUSD.setError("Both fields cannot be empty");
                    etEUR.setError("Both fields cannot be empty");
                }
                else if (usdIsEmpty) {
                    double valueEUR = Double.parseDouble(etEUR.getText().toString());
                    double convertToUSD = (valueEUR * 1.1494252873563218390804597701149);
                    etUSD.setText(String.format(Locale.ENGLISH, "%.2f", convertToUSD));
                    tvIsEmptyMessage.setText("");
                    etUSD.setError(null);
                    etEUR.setError(null);
                }
                else {
                    double valueUSD = Double.parseDouble(etUSD.getText().toString());
                    double convertToEUR = (valueUSD * 0.87);
                    etEUR.setText(String.format(Locale.ENGLISH,"%.2f", convertToEUR));
                    tvIsEmptyMessage.setText("");
                    etUSD.setError(null);
                    etEUR.setError(null);
                }
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText etUSD = findViewById(R.id.etUSD);
                EditText etEUR = findViewById(R.id.etEUR);
                etUSD.setText("");
                etEUR.setText("");
                tvIsEmptyMessage.setText("");
                etUSD.setError(null);
                etEUR.setError(null);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(MainActivity.this, AboutUsActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}