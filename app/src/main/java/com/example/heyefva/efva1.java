package com.example.heyefva;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class efva1 extends AppCompatActivity {


    TextView angkaKounter;
    TextView item1;
    TextView pemesan1;
    TextView alamat1;
    int angka=0;
    int hasilnya;
    TextView hasilakhir;
    Button tblAdd;
    Button tblView;
    DatabaseHelper BantuDb;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_efva1);

        angkaKounter=findViewById(R.id.kounter);
        hasilakhir=findViewById(R.id.hasilHitung);

        BantuDb=new DatabaseHelper(this);
        angkaKounter=(TextView) findViewById(R.id.kounter);
        pemesan1=(TextView) findViewById(R.id.pemesan);
        hasilakhir=(TextView)findViewById(R.id.hasilHitung);
        alamat1=(TextView) findViewById(R.id.alamat);
        item1=(TextView) findViewById(R.id.item);
        tblAdd=(Button)findViewById(R.id.tblAdd);
        tblView=(Button)findViewById(R.id.tblView);
        viewAll();
        tblAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted =  BantuDb.insertData(angkaKounter.getText().toString(),item1.getText().toString(),pemesan1.getText().toString(),alamat1.getText().toString(),hasilakhir.getText().toString());
                if(isInserted == true)
                    Toast.makeText(efva1.this,"Data Terimpan",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(efva1.this,"Data Gagal Tersimpan",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void kembali(View view) {
        Intent intent = new Intent(efva1.this,Home.class);
        startActivity(intent);
    }

    public void viewAll() {
        tblView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res =BantuDb.getAllData();
                        if (res.getCount() == 0) {
                            // show message
                            showMessage("Error", "Noting Found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Jumlah                     : " + res.getString(0) + "\n");//3
                            buffer.append("Nama Barang          : " + res.getString(1) + "\n");//2
                            buffer.append("Nama Pemesan      : " + res.getString(2) + "\n");//2
                            buffer.append("Alamat                      : " + res.getString(3) + "\n");//3
                            buffer.append("Harga                        :" + res.getString(4) + "\n\n");
                            buffer.append("                                                              ");

                        }
                        // show all data
                        showMessage("Pembelian :", buffer.toString());
                    }
                });
    }


    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void tblkounter(View view) {
        angka = angka + 1;
        angkaKounter.setText(Integer.toString(angka));
        hasilnya=angka*170000;
        hasilakhir.setText(Integer.toString(hasilnya));
    }
    public void tblkounter2(View view) {
        angka = angka - 1;
        angkaKounter.setText(Integer.toString(angka));
        hasilnya=angka*170000;
        hasilakhir.setText(Integer.toString(hasilnya));
    }
    public void klikpesan(View view) {
        Toast.makeText(this, "Barang Dipesan !", Toast.LENGTH_SHORT).show();
    }
}