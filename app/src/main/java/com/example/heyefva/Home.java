package com.example.heyefva;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends AppCompatActivity {

    FirebaseAuth firebaseauth;
    TextView tvakun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseauth = FirebaseAuth.getInstance();
        tvakun = findViewById(R.id.tvakun);
    }
    public void efvaa1 (View view) {
        Intent intent = new Intent(Home.this,efva1.class);
        startActivity(intent);
    }
    public void efvaa2 (View view) {
        Intent intent = new Intent(Home.this,efva2.class);
        startActivity(intent);
    }
    public void efvaa3 (View view) {
        Intent intent = new Intent(Home.this,efva3.class);
        startActivity(intent);
    }
    public void efvaa4 (View view) {
        Intent intent = new Intent(Home.this,efva4.class);
        startActivity(intent);
    }
    public void efvaa5 (View view) {
        Intent intent = new Intent(Home.this,efva5.class);
        startActivity(intent);
    }
    public void efvaa6 (View view) {
        Intent intent = new Intent(Home.this,efva6.class);
        startActivity(intent);
    }

    private  void checkuser(){
        FirebaseUser user = firebaseauth.getCurrentUser();
        if(user != null){
            tvakun.setText(user.getEmail());
        }else{
            startActivity(new Intent(Home.this, MainActivity.class));
            finish();
        }
    }

    @Override
    protected void onStart() {
        checkuser();
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_logout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id== R.id.menulogout){
            firebaseauth.signOut();
            checkuser();
        }
        return super.onOptionsItemSelected(item);
    }
}