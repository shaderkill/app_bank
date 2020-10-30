package com.example.ev02_cristianmolina.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ev02_cristianmolina.R;
import com.example.ev02_cristianmolina.models.User;
import com.example.ev02_cristianmolina.taks.LoginTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MainActivity extends AppCompatActivity {

    private EditText userText, passwordText;
    private Button button;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userText = (EditText) findViewById(R.id.user_text);
        passwordText = (EditText) findViewById(R.id.pwd_text);
        button = (Button) findViewById(R.id.login_btn);
        progressBar = (ProgressBar) findViewById(R.id.loading_spinner);
    }

    /**
     * On click.
     *
     * @param view the view
     */
    public void onClick(View view) {
        progressBar.setVisibility(View.VISIBLE);
        userText.setVisibility(View.GONE);
        passwordText.setVisibility(View.GONE);
        button.setVisibility(View.GONE);

        final String user = userText.getText().toString();
        final String password = passwordText.getText().toString();
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {
                try {
                    boolean result = new LoginTask(new User(user, password)).execute().get(2, TimeUnit.SECONDS);
                    if(result) {
                        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                        intent.putExtra("username", user);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Usuario y/o contraseñas no son válidos.", Toast.LENGTH_LONG).show();
                    }
                } catch (ExecutionException | TimeoutException | InterruptedException e) {
                    e.printStackTrace();
                }
                progressBar.setVisibility(View.GONE);
                userText.setVisibility(View.VISIBLE);
                passwordText.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);
            }
        };
        handler.postDelayed(r, 3000);
    }
}