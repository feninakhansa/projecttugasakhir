package com.example.heyefva;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class register extends AppCompatActivity {

    EditText emailedit, passedit;
    Button prosesdaftar;
    ProgressDialog pd;
    TextView tvdaftar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailedit = findViewById(R.id.emailedit);
        passedit = findViewById(R.id.passedit);
        prosesdaftar = findViewById(R.id.prosesdaftar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Buat Akun Baru");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        mAuth = FirebaseAuth.getInstance();

        tvdaftar = findViewById(R.id.tvdaftar);

        pd = new ProgressDialog(this);
        pd.setMessage("Daftar...");

        tvdaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(register.this, MainActivity.class));
                finish();
            }
        });


        prosesdaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailedit.getText().toString().trim();
                String password = passedit.getText().toString().trim();

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    emailedit.setError("Masukkan Email Dengan Benar");
                    emailedit.setFocusable(true);
                }
                else if(password.length() < 6){
                    passedit.setError("Password Minimal 6 Karakter");
                    passedit.setFocusable(true);
                }
                else{
                    prosesdaftarakun(email,password);
                }
            }
        });
    }

    private void prosesdaftarakun(String email, String password) {
        pd.show();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            pd.dismiss();
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(register.this, "Register Berhasil", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(register.this, Home.class));
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            pd.dismiss();
                            Toast.makeText(register.this, "Proses Register Gagal",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(register.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}