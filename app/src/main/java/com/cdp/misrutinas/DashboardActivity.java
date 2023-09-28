package com.cdp.misrutinas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class DashboardActivity extends AppCompatActivity {

    BottomNavigationView nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        nav = findViewById(R.id.nav);
        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();
                if (itemId == R.id.home) {
                    return true;
                } else if (itemId == R.id.finanza) {
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
        });
    }
}