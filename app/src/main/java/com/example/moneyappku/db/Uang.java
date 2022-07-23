package com.example.moneyappku.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Uang {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "jumlah")
    public String jumlah;

    @ColumnInfo(name = "jenis")
    public String jenis;

    @ColumnInfo(name = "kategori")
    public String kategori;

    @ColumnInfo(name = "tanggal")
    public String tanggal;
}
