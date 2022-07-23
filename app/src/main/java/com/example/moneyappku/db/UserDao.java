package com.example.moneyappku.db;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM uang")
    List<Uang> getAllTransaksi();

    @Query("SELECT SUM(jumlah) as jumlah from Uang WHERE kategori = 'Pengeluaran'")
    int getTotalPengeluaran();

    @Query("SELECT SUM(jumlah) as jumlah from Uang WHERE kategori = 'Pemasukan'")
    int getTotalPemasukan();

    @Insert
    void insertTransaksi(Uang...uang);

    @Query("UPDATE uang SET jumlah = :jumlah ,jenis = :jenis , tanggal = :tanggal WHERE id= :id")
    void update(String jenis , String jumlah ,String tanggal, int id);

    @Query("DELETE FROM uang WHERE id = :id")
    void hapus(int id);


}
