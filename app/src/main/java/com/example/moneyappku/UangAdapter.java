package com.example.moneyappku;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneyappku.db.Uang;

import java.util.List;

public class UangAdapter extends RecyclerView.Adapter<UangAdapter.ItemViewHolder> {

    private List<Uang> transaksi;
    private RecyclerViewClickListener listener;

    public UangAdapter(List<Uang> transaksi, RecyclerViewClickListener listener) {
        this.transaksi = transaksi;
        this.listener = listener;
    }

    public void setDaftarTransaksi(List<Uang> transaksi) {
        this.transaksi = transaksi;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_list, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        if (transaksi.get(position).kategori.contains("Pemasukan")) {
            holder.txtjenis.setText(this.transaksi.get(position).jenis);
            holder.txtjumlah.setText(this.transaksi.get(position).jumlah);
            holder.txttanggal.setText(this.transaksi.get(position).tanggal);
            holder.iconTransaksi.setBackgroundResource(R.drawable.ic_up);
        } else {
            holder.txtjenis.setText(this.transaksi.get(position).jenis);
            holder.txtjumlah.setText(this.transaksi.get(position).jumlah);
            holder.txttanggal.setText(this.transaksi.get(position).tanggal);
            holder.iconTransaksi.setBackgroundResource(R.drawable.ic_down);
        }

    }

    @Override
    public int getItemCount() {
        return transaksi.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtjenis, txtjumlah, txttanggal;
        ImageView imgHapus, iconTransaksi;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            txtjenis = itemView.findViewById(R.id.tvJenis);
            txtjumlah = itemView.findViewById(R.id.tvJumlah);
            txttanggal = itemView.findViewById(R.id.tvTanggal);

            iconTransaksi = itemView.findViewById(R.id.img_src);
            imgHapus = itemView.findViewById(R.id.btnHapus);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());

        }
    }

    public interface RecyclerViewClickListener {
        void onClick(View v, int position);
    }
}
