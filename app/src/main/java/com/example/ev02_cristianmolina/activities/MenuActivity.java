package com.example.ev02_cristianmolina.activities;

import com.example.ev02_cristianmolina.R;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    private ViewFlipper viewFlipper;

    private int[] gallery_grid_Images ={R.drawable.a,R.drawable.b,R.drawable.c};

    private ArrayList<String> clientes = new ArrayList<String>();
    private ArrayList<String> creditos = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        viewFlipper = (ViewFlipper) findViewById(R.id.slider);
        for (int gallery_grid_image : gallery_grid_Images) {
            setFlipperImage(gallery_grid_image);
        }
        viewFlipper.setFlipInterval(10000);
        viewFlipper.startFlipping();


        clientes.add("Axel");
        clientes.add("Roxana");
        clientes.add("Betzabe");
        clientes.add("Matias");
        creditos.add("Crédito Hipotecario");
        creditos.add("Crédito Automotriz");
    }

    private void setFlipperImage(int res) {
        ImageView image = new ImageView(getApplicationContext());
        image.setBackgroundResource(res);
        viewFlipper.addView(image);
    }

    /**
     * On click.
     *
     * @param view the view
     */
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.client_btn:
                intent = new Intent(MenuActivity.this, ClientActivity.class);
                break;
            case R.id.payment_btn:
                intent = new Intent(MenuActivity.this, PaymentActivity.class);
                intent.putExtra("clients", clientes);
                intent.putExtra("credits", creditos);
                break;
            case R.id.info_btn:
                intent = new Intent(MenuActivity.this, InformationActivity.class);
                break;
            case R.id.security_btn:
                intent = new Intent(MenuActivity.this, SecurityActivity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}