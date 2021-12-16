package com.example.pemrogramanmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private MahasiswaAdapter adapter;
    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MahasiswaAdapter();
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        getMahasiswa();
        FloatingActionButton tambahButton = findViewById(R.id.tambah);
        tambahButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, TambahMahasiswa.class));
            }
        });
    }

    private void getMahasiswa() {
        final ArrayList<Mahasiswa> listMahasiswa = new ArrayList<>();
        String baseUrl = "http://apipemrogramanmobile.medandigitalinnovation.com/mahasiswa/";
        AndroidNetworking.get(baseUrl)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("_kotlinResponse", response.toString());
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject mahasiswa = jsonArray.getJSONObject(i);
                                Mahasiswa itemMahasiswa = new Mahasiswa();
                                itemMahasiswa.setNama(mahasiswa.getString("nama"));
                                itemMahasiswa.setNim(mahasiswa.getString("nim"));
                                itemMahasiswa.setEmail(mahasiswa.getString("email"));
                                itemMahasiswa.setFakultas(mahasiswa.getString("fakultas"));
                                itemMahasiswa.setProdi(mahasiswa.getString("prodi"));
                                itemMahasiswa.setSemester(mahasiswa.getString("semester"));
                                itemMahasiswa.setFakultas(mahasiswa.getString("fakultas"));
                                itemMahasiswa.setStatus(mahasiswa.getString("statusmhs"));
                                itemMahasiswa.setGambar(mahasiswa.getString("foto"));
                                listMahasiswa.add(itemMahasiswa);
                            }
                            adapter.setData(listMahasiswa);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Log.d("onFailure", anError.getMessage());
                    }
                });
    }
}