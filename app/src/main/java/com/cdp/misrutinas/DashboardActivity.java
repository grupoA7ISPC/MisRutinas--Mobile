package com.cdp.misrutinas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class DashboardActivity extends AppCompatActivity {

    BottomNavigationView nav;
    TextView textUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnItemSelectedListener(this::onNavigationItemSelected);

        textUsername = findViewById(R.id.textUsername);
        Intent intent = getIntent();

        if (intent.hasExtra("username")) {
            String username = intent.getStringExtra("username");
            textUsername.setText("@" + username);
        } else {
            textUsername.setText("de vuelta");
        }
    }

    private boolean onNavigationItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.home) {
            return true;
        } else if (itemId == R.id.finanza) {
            startActivity(new Intent(getApplicationContext(), FinanzasActivity.class));
            return true;
        } else if (itemId == R.id.calendario) {
            startActivity(new Intent(getApplicationContext(), CalendarioActivity.class));
            return true;
        } else if (itemId == R.id.contacto) {
            startActivity(new Intent(getApplicationContext(), ContactoActivity.class));
            return true;
        }
        return false;
    }

    public void btnSocioList(View view){
        Intent intent=new Intent(DashboardActivity.this, ListaSocioActivity.class);
        startActivity(intent);
    }

    public void btnProfeList(View view){
        Intent intent=new Intent(DashboardActivity.this, ListaProfesorActivity.class);
        startActivity(intent);
    }

    public void btnClaseList(View view){
        Intent intent=new Intent(DashboardActivity.this, ListaClaseActivity.class);
        startActivity(intent);
    }
    public void btnLogout(View view){
        Intent intent=new Intent(DashboardActivity.this, MainActivity.class);
        startActivity(intent);
    }
}