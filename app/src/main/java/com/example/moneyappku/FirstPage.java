package com.example.moneyappku;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.moneyappku.db.AppDatabase;
import com.example.moneyappku.db.Uang;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class FirstPage extends AppCompatActivity {
    private List<Uang> transaksi;
    private UangAdapter uangAdapter;
    private UangAdapter.RecyclerViewClickListener listener;

    private NestedScrollView nested_scroll_view;
    FloatingActionButton floatingButton;

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {

                    setOnClickListener();
                    loadRecyclerView();
                    loadAll();
                    getPemasukan();
                    getPengeluaran();
                    getSemuaUang();
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        nested_scroll_view = findViewById(R.id.nested_scrollview);
        floatingButton = findViewById(R.id.fab);

        nested_scroll_view.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > oldScrollY) {
                    floatingButton.hide();
                } else {
                    floatingButton.show();
                }
            }
        });

        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FirstPage.this, PageInput.class);
                activityResultLauncher.launch(i);
            }
        });

        setOnClickListener();
        loadRecyclerView();
        loadAll();
        getPemasukan();
        getPengeluaran();
        getSemuaUang();



    }

    public void loadRecyclerView() {
        RecyclerView rv = findViewById(R.id.rv_Pengeluaran);
        rv.setLayoutManager(new LinearLayoutManager(FirstPage.this));

        DividerItemDecoration div = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rv.addItemDecoration(div);

        uangAdapter = new UangAdapter(transaksi, listener);
        rv.setAdapter(uangAdapter);
    }

    public void loadAll() {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        List<Uang> list = db.userDao().getAllTransaksi();
        uangAdapter.setDaftarTransaksi(list);
    }

    public void getPemasukan() {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        int total = db.userDao().getTotalPemasukan();
        TextView pemasukan = findViewById(R.id.tvPemasukan);
        pemasukan.setText(String.valueOf(total));
    }

    public void getPengeluaran() {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        int total = db.userDao().getTotalPengeluaran();
        TextView pengeluaran = findViewById(R.id.tvPengeluaran);
        pengeluaran.setText(String.valueOf(total));
    }

    public void getSemuaUang() {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        int pengeluaran = db.userDao().getTotalPengeluaran();
        int pemasukan = db.userDao().getTotalPemasukan();

        int totalUang = pemasukan - pengeluaran;
        TextView total = findViewById(R.id.tvTotalUang);
        total.setText(String.valueOf(totalUang));
    }

    private void setOnClickListener() {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        List<Uang> transaksi = db.userDao().getAllTransaksi();

        listener  = new UangAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), PageEdit.class);
                intent.putExtra("id", transaksi.get(position).id);
                intent.putExtra("kategori", transaksi.get(position).kategori);
                intent.putExtra("jenis", transaksi.get(position).jenis);
                intent.putExtra("jumlah", transaksi.get(position).jumlah);
                intent.putExtra("tanggal", transaksi.get(position).tanggal);
                activityResultLauncher.launch(intent);

            }
        };
    }


}