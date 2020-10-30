package com.example.ev02_cristianmolina.activities;

import androidx.appcompat.app.AppCompatActivity;
import com.example.ev02_cristianmolina.R;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class SecurityActivity extends AppCompatActivity {

    private EditText passwordText;
    private TextView cryptoText, ivText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);
        passwordText = (EditText) findViewById(R.id.password_sec);
        cryptoText = (TextView) findViewById(R.id.encrypt_text);
        ivText = (TextView) findViewById(R.id.iv_text);
    }

    public void onClick(View view) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException {
        String password = passwordText.getText().toString();
        if(!password.isEmpty()) {
            byte[] plaintext = password.getBytes();
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            keygen.init(256);
            SecretKey key = keygen.generateKey();
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] ciphertext = cipher.doFinal(plaintext);
            byte[] iv = cipher.getIV();
            String s = new String(ciphertext, StandardCharsets.UTF_8);
            String d = new String(iv, StandardCharsets.UTF_8);
            cryptoText.setText(new StringBuilder("Encriptado: ").append(s));
            ivText.setText(new StringBuilder("Vectorización inicial: ").append(d));
        } else {

        }


    }
}