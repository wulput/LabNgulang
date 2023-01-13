package com.example.myapplication.activity.mahasiswa;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.room.Mahasiswa;
import com.example.myapplication.util.OnClickAdapterItem;

import java.util.List;


public class RecyclerviewUserAdapter extends RecyclerView.Adapter<RecyclerviewUserAdapter.MyViewHolder> {
    private Context mContext;
    private List<Mahasiswa> myList;
    private OnClickAdapterItem onClickAdapterItem;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvAlamat;
        public TextView tvKejuruan;
        public TextView tvNama;
        public TextView tvNim;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAlamat = itemView.findViewById(R.id.tvAlamat);
            tvKejuruan = itemView.findViewById(R.id.tvKejuruan);
            tvNim = itemView.findViewById(R.id.tvNim);
            tvNama = itemView.findViewById(R.id.tvNama);
        }
    }

    public RecyclerviewUserAdapter(Context mContext, List<Mahasiswa> albumList) {
        this.mContext = mContext;
        this.myList = albumList;
    }


    public void OnClickAdapterItem(OnClickAdapterItem onClickAdapterItem) {
        this.onClickAdapterItem = onClickAdapterItem;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_mahasiswa, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final Mahasiswa album = myList.get(i);
        myViewHolder.tvNama.setText(album.getNama());
        myViewHolder.tvNim.setText(album.getNim());
        myViewHolder.tvKejuruan.setText(album.getKejuruan());
        myViewHolder.tvAlamat.setText(album.getAlamat());


        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, AddUserActivity.class);
                intent.putExtra("status", "edit");
                intent.putExtra("id", album.getId());
                mContext.startActivity(intent);
            }
        });

        myViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (onClickAdapterItem != null) {
                    onClickAdapterItem.clickItem(album.getId(), i);
                }
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }
}