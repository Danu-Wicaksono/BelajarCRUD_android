package com.example.belajarcrud_android;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateActivity extends AppCompatActivity {

    Database database;
    Button btn_simpan;
    EditText nama, kampus;
    protected Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        database = new Database(this);
        nama = findViewById(R.id.nama);
        kampus = findViewById(R.id.kampus);
        btn_simpan = findViewById(R.id.btn_simpan);

        //button simpan
        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = database.getWritableDatabase();
                db.execSQL("insert into mahasiswa(nama, kampus) values('" +
                        nama.getText().toString() + "','" +
                        kampus.getText().toString() + "')");
                Toast.makeText(getApplicationContext(), "Data Tersimpan", Toast.LENGTH_LONG).show();
                MainActivity.ma.RefreshList();
                finish();
            }
        });

    }
}