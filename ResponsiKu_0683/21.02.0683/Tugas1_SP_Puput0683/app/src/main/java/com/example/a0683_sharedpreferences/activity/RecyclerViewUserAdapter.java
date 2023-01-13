package com.example.a0683_sharedpreferences.activity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a0683_sharedpreferences.R;
import com.example.a0683_sharedpreferences.room.Mahasiswa;

import java.util.List;

public class RecyclerViewUserAdapter extends RecyclerView.Adapter<RecyclerViewUserAdapter.MyViewHolder> {

    private Context mContext;
    private List<Mahasiswa> myList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView tvAlamat;
        public TextView tvKejuruan;
        public TextView tvNama;
        public TextView tvNim;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAlamat = itemView.findViewById(R.id.tvAlamat);
            tvKejuruan = itemView.findViewById(R.id.tvKejuruan);
            tvNama = itemView.findViewById(R.id.tvNama);
            tvNim = itemView.findViewById(R.id.tvNim);

        }
    }

    public RecyclerViewUserAdapter(UserActivity userActivity, List<Mahasiswa> albumList) {
        this.mContext = mContext;
        this.myList = albumList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_mhs, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewUserAdapter.MyViewHolder myViewHolder, int i) {
        final Mahasiswa album = myList.get(i);
        myViewHolder.tvNama.setText(album.getNama());
        myViewHolder.tvNim.setText(album.getNim());
        myViewHolder.tvKejuruan.setText(album.getKejuruan());
        myViewHolder.tvAlamat.setText(album.getAlamat());

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //karena ingin mengambil data dari activity maka bukan menggunakan .this tpi dipanggil context nya
                Intent editIntent = new Intent(mContext, AddUserActivity.class);
                editIntent.putExtra("id", album.getId());
                editIntent.putExtra("nama", album.getNama());
                editIntent.putExtra("nama", album.getNim());
                editIntent.putExtra("nama", album.getKejuruan());
                editIntent.putExtra("nama", album.getAlamat());



            }
        });

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }


}
