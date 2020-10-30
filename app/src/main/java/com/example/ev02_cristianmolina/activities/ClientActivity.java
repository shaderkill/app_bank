package com.example.ev02_cristianmolina.activities;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ev02_cristianmolina.AdminsSQLiteOpenHelper;
import com.example.ev02_cristianmolina.R;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ClientActivity extends AppCompatActivity {

    private EditText salarioText;
    private AutoCompleteTextView nameText;
    private TextView codText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        nameText = (AutoCompleteTextView) findViewById(R.id.text_name);
        codText = (TextView) findViewById(R.id.text_code);
        salarioText = (EditText) findViewById(R.id.text_salary);
        getClients();
    }

    public void addClient(View view) {
        String name = nameText.getText().toString();
        String salaryString = salarioText.getText().toString();
        AdminsSQLiteOpenHelper adminsSQLiteOpenHelper = new AdminsSQLiteOpenHelper(this, "bank_bpm", null, 2);
        SQLiteDatabase database = adminsSQLiteOpenHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT name from clients where name='" + name + "'", null);
        if (!cursor.moveToFirst()) {
            int salary = 0;
            boolean isValid = false;
            try {
                salary = Integer.parseInt(salaryString);
                isValid = true;
            } catch (NumberFormatException e) {
                e.fillInStackTrace();
            }
            if(isValid) {
                ContentValues contentValues = new ContentValues();
                cursor = database.rawQuery("SELECT MAX(id)+1 from clients", null);
                int id = (cursor.moveToFirst() ? cursor.getInt(0) : 0);
                Log.i("Client ID", "Id generada " + id);
                contentValues.put("id", id);
                contentValues.put("name", name);
                contentValues.put("salary", salary);
                database.insert("clients", null, contentValues);
                nameText.setText("");
                codText.setText("");
                salarioText.setText("");
                Toast.makeText(getApplicationContext(), "Cliente agregado!.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Salario ingresado no es válido. Solo se aceptan números.", Toast.LENGTH_SHORT).show();
            }
        }
        getClients();
        database.close();
        cursor.close();
    }

    public void updateClient(View view) {
            String name = nameText.getText().toString();
            String salaryString = salarioText.getText().toString();
            int id = Integer.parseInt(codText.getText().toString());
            AdminsSQLiteOpenHelper adminsSQLiteOpenHelper = new AdminsSQLiteOpenHelper(this, "bank_bpm", null, 2);
            SQLiteDatabase database = adminsSQLiteOpenHelper.getWritableDatabase();
            Cursor cursor = database.rawQuery("SELECT name from clients where id='" + id + "'", null);
            if (cursor.moveToFirst()) {
                int salary = 0;
                boolean isValid = false;
                try {
                    salary = Integer.parseInt(salaryString);
                    isValid = true;
                } catch (NumberFormatException e) {
                    e.fillInStackTrace();
                }
                if(isValid) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("id", id);
                    contentValues.put("name", name);
                    contentValues.put("salary", salary);
                    database.update("clients", contentValues, "id="+id, null);
                    Toast.makeText(getApplicationContext(), "Cliente actualizado!.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Salario ingresado no es válido. Solo se aceptan números.", Toast.LENGTH_SHORT).show();
                }
            } else  {
                Toast.makeText(getApplicationContext(), "No se ha seleccionado un cliente para actualizar.", Toast.LENGTH_SHORT).show();
            }
            database.close();
            cursor.close();
    }

    public void deleteClient(View view) {
        String name = nameText.getText().toString();
        AdminsSQLiteOpenHelper adminsSQLiteOpenHelper = new AdminsSQLiteOpenHelper(this, "bank_bpm", null, 2);
        SQLiteDatabase database = adminsSQLiteOpenHelper.getWritableDatabase();
        database.execSQL("DELETE FROM clients WHERE name='" + name+"'");
        Toast.makeText(getApplicationContext(), "Cliente eliminado!.", Toast.LENGTH_SHORT).show();
        nameText.setText("");
        codText.setText("");
        salarioText.setText("");
        database.close();
        getClients();
    }

    public void getClients() {
        ArrayList<String> clientsList = new ArrayList<>();
        AdminsSQLiteOpenHelper adminsSQLiteOpenHelper = new AdminsSQLiteOpenHelper(this, "bank_bpm", null, 2);
        SQLiteDatabase database = adminsSQLiteOpenHelper.getWritableDatabase();
        Cursor rows = database.rawQuery("SELECT name from clients", null);
        while (rows.moveToNext()) {
            clientsList.add(rows.getString(0));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, clientsList);
        nameText.setAdapter(adapter);
        rows.close();
        database.close();
    }

    public void getClient(View view) {
        String name = nameText.getText().toString();
        AdminsSQLiteOpenHelper adminsSQLiteOpenHelper = new AdminsSQLiteOpenHelper(this, "bank_bpm", null, 2);
        SQLiteDatabase database = adminsSQLiteOpenHelper.getWritableDatabase();
        Cursor rows = database.rawQuery("SELECT id, name, salary from clients where name ='"+name+"'", null);
        if(rows.moveToFirst()) {
            String id = String.valueOf(rows.getInt(0));
            codText.setText(id);
            String salary = String.valueOf(rows.getInt(2));
            salarioText.setText(salary);
        } else {
            Toast.makeText(getApplicationContext(), "Cliente solicitado, no existe.", Toast.LENGTH_SHORT).show();
        }
        rows.close();
        database.close();
    }
}