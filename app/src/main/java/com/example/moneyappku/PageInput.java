package com.example.moneyappku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.moneyappku.db.AppDatabase;
import com.example.moneyappku.db.Uang;

import java.util.Calendar;

public class PageInput extends AppCompatActivity {

    DatePickerDialog picker;
    EditText edtTanggal, edtJenis, edtJumlah;
    Button btnSimpan, tmbh_pengeluaran, tmbh_pemasukan;
    String ktgr = "Pemasukan";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_input);

        //edtxt - findview
        edtJenis = findViewById(R.id.inputjenis);
        edtJumlah = findViewById(R.id.inputJumlah);
        edtTanggal = findViewById(R.id.inputTanggal);

        //button
        btnSimpan = findViewById(R.id.btnSimpan);
        tmbh_pemasukan = findViewById(R.id.tambah_pemasukan);
        tmbh_pengeluaran = findViewById(R.id.tambah_pengeluaran);

        tmbh_pemasukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ktgr = "Pemasukan";

                tmbh_pemasukan.setBackground(ContextCompat.getDrawable(PageInput.this, R.color.blueColor));
                tmbh_pemasukan.setTextColor(ContextCompat.getColor(PageInput.this, R.color.whiteColor));
                tmbh_pengeluaran.setBackground(ContextCompat.getDrawable(PageInput.this, R.color.whiteColor));
                tmbh_pengeluaran.setTextColor(ContextCompat.getColor(PageInput.this, R.color.blueColor));
            }
        });

        tmbh_pengeluaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ktgr = "Pengeluaran";

                tmbh_pemasukan.setBackground(ContextCompat.getDrawable(PageInput.this, R.color.whiteColor));
                tmbh_pemasukan.setTextColor(ContextCompat.getColor(PageInput.this, R.color.blueColor));
                tmbh_pengeluaran.setBackground(ContextCompat.getDrawable(PageInput.this, R.color.blueColor));
                tmbh_pengeluaran.setTextColor(ContextCompat.getColor(PageInput.this, R.color.whiteColor));
            }
        });

        edtTanggal.setInputType(InputType.TYPE_NULL);
        edtTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                picker = new DatePickerDialog(PageInput.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        int month = monthOfYear + 1;
                        String formattedMonthOfYear = "" + month;
                        String formattedDayOfMonth = "" + dayOfMonth;

                        if (month < 10) {
                            formattedMonthOfYear = "0" + month;
                        }
                        if (dayOfMonth < 10) {
                            formattedDayOfMonth = "0" + dayOfMonth;
                        }
                        edtTanggal.setText(year + "-" + formattedMonthOfYear + "-" + formattedDayOfMonth);
                    }
                }, year, month, day);
                picker.show();
            }
        });

        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpan(ktgr, edtJenis.getText().toString(), edtJumlah.getText().toString(), edtTanggal.getText().toString());
                db.userDao().getAllTransaksi();
            }
        });


    }

    private void simpan (String kategori, String jenis, String jumlah, String tanggal ){
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        Uang uang = new Uang();

        uang.kategori = kategori;
        uang.jenis = jenis;
        uang.jumlah = jumlah;
        uang.tanggal = tanggal;

        db.userDao().insertTransaksi(uang);
        setResult(RESULT_OK);

        finish();
    }
}