package com.example.belajarcrud_android;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    Database database;
    Button btn_simpan;
    EditText nama, kampus;
    protected Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        database = new Database(this);
        nama = findViewById(R.id.nama);
        kampus = findViewById(R.id.kampus);
        btn_simpan = findViewById(R.id.btn_simpan);

        SQLiteDatabase db = database.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM mahasiswa WHERE nama = '" +
                getIntent().getStringExtra("nama") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            nama.setText(cursor.getString(0).toString());
            kampus.setText(cursor.getString(1).toString());
        }

        //button simpan
        //menulis (writeable)
        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = database.getWritableDatabase();
                db.execSQL("update mahasiswa set nama='" +
                        nama.getText().toString() + "', kampus= '" +
                        kampus.getText().toString() + "' where nama = '" +
                        getIntent().getStringExtra("nama")+"'");
                Toast.makeText(getApplicationContext(), "Data berhasil diupdate", Toast.LENGTH_LONG).show();
                MainActivity.ma.RefreshList();
                finish();
            }
        });

    }
}