package com.example.moneyappku;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.moneyappku.db.AppDatabase;

public class PageEdit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_edit);

        //Button
        Button btnHapus = findViewById(R.id.edtHapus);
        Button btnEdit = findViewById(R.id.edtEdit);

        //EditText
        EditText kategori = findViewById(R.id.edtInputKategori);
        EditText jumlah = findViewById(R.id.edtInputHarga);
        EditText jenis = findViewById(R.id.edtInputJenis);
        EditText tanggal = findViewById(R.id.edtInputTanggal);

        int id = 0;
        String inKategori = "data";
        String inJumlah = "data";
        String inJenis = "data";
        String inTanggal = "data";

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("id");
            inKategori = extras.getString("kategori");
            inJenis = extras.getString("jenis");
            inJumlah = extras.getString("jumlah");
            inTanggal = extras.getString("tanggal");

        }


        kategori.setText(inKategori);
        jenis.setText(inJenis);
        tanggal.setText(inTanggal);
        jumlah.setText(inJumlah);
        int finalD = id;

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(jenis.getText().toString(), jumlah.getText().toString(), tanggal.getText().toString(), finalD);

                setResult(RESULT_OK);
                finish();

            }
        });

        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hapus(finalD);
                setResult(RESULT_OK);
                finish();

            }
        });

    }

    private void update(String jenis, String jumlah, String tanggal, int id) {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        db.userDao().update(jenis, jumlah, tanggal, id);
    }

    private void hapus(int id) {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        db.userDao().hapus(id);
    }
}