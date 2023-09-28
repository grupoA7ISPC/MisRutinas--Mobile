package com.cdp.misrutinas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class ContactoActivity extends AppCompatActivity {


    BottomNavigationView nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
        nav = findViewById(R.id.nav);
        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();
                if (itemId == R.id.contacto) {
                    return true;
                } else if (itemId == R.id.finanza) {
                    Toast.makeText(ContactoActivity.this, "Finanza", Toast.LENGTH_LONG).show();
                } else if (itemId == R.id.calendario) {
                    startActivity(new Intent(getApplicationContext(), CalendarioActivity.class));
                    return true;
                } else if (itemId == R.id.home) {
                    startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                    return true;
                }

                return false;
            }
        });
    }
}