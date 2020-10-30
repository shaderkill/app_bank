package com.example.ev02_cristianmolina.activities;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ev02_cristianmolina.R;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class PaymentActivity extends AppCompatActivity {

    private Spinner clientSpinner, creditSpinner;
    private TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        clientSpinner = (Spinner) findViewById(R.id.spinner_clients);
        creditSpinner = (Spinner) findViewById(R.id.spinner_credits);

        ArrayList<String> clients = (ArrayList<String>) getIntent().getSerializableExtra("clients");
        ArrayList<String> credits = (ArrayList<String>) getIntent().getSerializableExtra("credits");
        if (clients != null) {
            ArrayAdapter<String> adaptClient = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, clients);
            clientSpinner.setAdapter(adaptClient);
        }
        if (credits != null) {
            ArrayAdapter<String> adaptCredit = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, credits);
            creditSpinner.setAdapter(adaptCredit);
        }

        resultView = (TextView) findViewById(R.id.text_result);
    }

    public void onCalcPrestamo(View view) {
        int base = getClientFee(clientSpinner.getSelectedItem().toString());
        int credit = getCreditAmount(creditSpinner.getSelectedItem().toString());

        int result = base + credit;
        String message = "Prestamo final: " + result;
        resultView.setText(message);
    }

    public void onCalcDeuda(View view) {
        int base = getClientFee(clientSpinner.getSelectedItem().toString());
        int credit = getCreditAmount(creditSpinner.getSelectedItem().toString());
        int cuotas = 0;
        switch(creditSpinner.getSelectedItem().toString()) {
            case "Crédito Hipotecario":
                cuotas = 12;
                break;
            case "Crédito Automotriz":
                cuotas = 8;
                break;
        }

        int result = (base + credit) / cuotas;
        String message = "Deuda final: " + result;
        resultView.setText(message);
    }

    public int getClientFee(String client) {
        int amount = 0;
        switch (client) {
            case "Axel":
                amount = 750000;
                break;
            case "Roxana":
                amount = 900000;
                break;
            case "Betzabe":
                amount = 1150000;
                break;
            case "Matias":
                amount = 1300000;
                break;
        }
        return amount;
    }

    public int getCreditAmount(String credit) {
        int amount = 0;
        switch (credit) {
            case "Crédito Hipotecario":
                amount = 1000000;
                break;
            case "Crédito Automotriz":
                amount = 500000;
                break;
        }
        return amount;
    }
}