package com.example.uwp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class Promotion extends AppCompatActivity {

    ListView ListeView;
    ArrayAdapter adapter;
    List<String> ListPromotions = Arrays.asList("PREMIER BACHELIER", "DEUXIEME BACHELIER", "TROISIEME BACHELIER");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion);

        ListeView = (ListView) findViewById(R.id.liste_view);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ListPromotions);
        ListeView.setAdapter(adapter);
        ListeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Promotion.this, Cours.class);
                intent.putExtra("Promotion", ListeView.getItemAtPosition(i).toString());
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_uwepo, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(this, "Accueil", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent1);
                return true;
            case R.id.itemApropos:
                Toast.makeText(this, "A propos", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Apropos.class);
                startActivity(intent);
                return true;

            case R.id.itemPromotion:
                Toast.makeText(this, "Liste des promotions", Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(getApplicationContext(), Promotion.class);
                startActivity(intent3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}