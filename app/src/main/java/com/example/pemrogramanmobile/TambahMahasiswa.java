package com.example.pemrogramanmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

public class TambahMahasiswa extends AppCompatActivity {
    private EditText mahasiswaNama, mahasiswaEmail, mahasiswaProdi, mahasiswaStatus, mahasiswaGambar, mahasiswaSemester, mahasiswaFakultas, mahasiswaNIM;
    String txtNama, txtEmail, txtProdi, txtStatus, txtGambar, txtSemester, txtFakultas, txtNIM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambah_mahasiswa);

        mahasiswaNama = findViewById(R.id.mahasiswaNama);
        mahasiswaEmail = findViewById(R.id.mahasiswaEmail);
        mahasiswaProdi = findViewById(R.id.mahasiswaProdi);
        mahasiswaStatus = findViewById(R.id.mahasiswaStatus);
        mahasiswaGambar = findViewById(R.id.mahasiswaGambar);
        mahasiswaSemester = findViewById(R.id.mahasiswaSemester);
        mahasiswaFakultas = findViewById(R.id.mahasiswaFakultas);
        mahasiswaNIM = findViewById(R.id.mahasiswaNIM);
    }

    public void kembali(View view){
        startActivity(new Intent(TambahMahasiswa.this, MainActivity.class));
    }

    public void simpanData(View view){
        AksiSimpan();
    }

    private void AksiSimpan(){
        txtNama = mahasiswaNama.getText().toString();
        txtEmail = mahasiswaEmail.getText().toString();
        txtProdi = mahasiswaProdi.getText().toString();
        txtStatus = mahasiswaStatus.getText().toString();
        txtGambar = mahasiswaGambar.getText().toString();
        txtSemester = mahasiswaSemester.getText().toString();
        txtFakultas = mahasiswaFakultas.getText().toString();
        txtNIM = mahasiswaNIM.getText().toString();
        if (txtNama.equals("") || txtEmail.equals("") || txtProdi.equals("") || txtStatus.equals("") || txtGambar.equals("") || txtSemester.equals("") || txtFakultas.equals("") || txtNIM.equals(""))
        {
            Toast.makeText(this, "Data tidak boleh kosong", Toast.LENGTH_LONG).show();
        }else{
            AndroidNetworking.post("http://apipemrogramanmobile.medandigitalinnovation.com/mahasiswa/")
                    .addBodyParameter("nim", txtNIM)
                    .addBodyParameter("nama", txtNama)
                    .addBodyParameter("email", txtEmail)
                    .addBodyParameter("fakultas", txtFakultas)
                    .addBodyParameter("prodi", txtProdi)
                    .addBodyParameter("semester", txtSemester)
                    .addBodyParameter("statusmhs", txtStatus)
                    .addBodyParameter("foto", txtGambar)
                    .setTag("test")
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // do anything with response
                            try {
                                if(response.getString("status").equals("1")){
                                    Toast.makeText(getApplicationContext(),"Data Berhasil disimpan",Toast.LENGTH_LONG).show();
                                }else{
                                    Toast.makeText(getApplicationContext(),"Data Gagal disimpan",Toast.LENGTH_LONG).show();
                                }
                            }catch (JSONException e){
                                Toast.makeText(getApplicationContext(),"Data Gagal disimpan" + e,Toast.LENGTH_LONG).show();
                            }
                        }
                        @Override
                        public void onError(ANError error) {
                            // handle error
                            Toast.makeText(getApplicationContext(),"Data Gagal disimpan" + error,Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }
}
