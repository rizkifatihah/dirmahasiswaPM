package com.example.pemrogramanmobile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.MahasiswaViewHolder> {

    private ArrayList<Mahasiswa> mData = new ArrayList<>();
    private OnItemClickCallback onItemClickCallback;
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }
    public void setData(ArrayList<Mahasiswa> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MahasiswaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_mahasiswa, parent, false);
        return new MahasiswaViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaViewHolder holder, int position) {
        holder.bind(mData.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(mData.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MahasiswaViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNama;
        TextView textViewNim;
        TextView textViewFakultas;
        TextView textViewEmail;
        TextView textViewProdi;
        TextView textViewSemester;
        TextView textViewStatus;
        ImageView imageViewGambar;
        public MahasiswaViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNama = itemView.findViewById(R.id.mahasiswaNama);
            textViewNim = itemView.findViewById(R.id.mahasiswaNIM);
            textViewFakultas = itemView.findViewById(R.id.mahasiswaFakultas);
            textViewEmail = itemView.findViewById(R.id.mahasiswaEmail);
            textViewProdi = itemView.findViewById(R.id.mahasiswaProdi);
            textViewSemester = itemView.findViewById(R.id.mahasiswaSemester);
            textViewStatus = itemView.findViewById(R.id.mahasiswaStatus);
            imageViewGambar = itemView.findViewById(R.id.mahasiswaGambar);
        }

        public void bind(Mahasiswa mahasiswa) {
            Glide.with(itemView.getContext())
                    .load(mahasiswa.getGambar())
                    .apply(new RequestOptions().override(55, 55))
                    .into(imageViewGambar);
            textViewNama.setText(mahasiswa.getNama());
            textViewNim.setText(mahasiswa.getNim());
            textViewFakultas.setText(mahasiswa.getFakultas());
            textViewEmail.setText(mahasiswa.getEmail());
            textViewProdi.setText(mahasiswa.getProdi());
            textViewSemester.setText(mahasiswa.getSemester());
            textViewStatus.setText(mahasiswa.getStatus());
        }
    }

    private class OnItemClickCallback {
        void onItemClicked(Mahasiswa data) {

        }
    }
}
