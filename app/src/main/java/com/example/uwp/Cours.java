package com.example.uwp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class Cours extends AppCompatActivity {

    ListView ListeView;
    ArrayAdapter adapter;
    List<String> CoursBac1 = Arrays.asList(" ","ANALYSE MATH", "LANGAGE C", "LOGIQUE MATH", "ANGLAIS");
    List<String> CoursBac2 = Arrays.asList(" ","LOGIQUE DE PROGRAMMATION", "PROJET INFORMATIQUE", "PROGRAMMATION WEB 1.0");
    List<String> CoursBac3 = Arrays.asList(" ","PROGRAMMATION WEB 2.0", "PROGRAMMATION ANDROID", "ANGAIS", "C.S.I");

    public static String PROMOTION_MESSAGE = " ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cours);
// Pour le liste View
        ListeView = (ListView) findViewById(R.id.liste_view);

        ListeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(Cours.this, Registre.class);
                intent.putExtra(PROMOTION_MESSAGE, ListeView.getItemAtPosition(i).toString());
                startActivity(intent);
            }
        });

// pour recuperer les donnes de l'activite Promotion
        Intent intent = getIntent();
        String idGet = intent.getStringExtra("Promotion");

        TextView textView = findViewById(R.id.textView);

        if(idGet.equals("PREMIER BACHELIER")){

      //  identier promotion
            PROMOTION_MESSAGE = "PREMIER BACHELIER";

            textView.setText(idGet);
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, CoursBac1);
            ListeView.setAdapter(adapter);
        }
        else if(idGet.equals("DEUXIEME BACHELIER")) {
            //  identier promotion
            PROMOTION_MESSAGE = "DEUXIEME BACHELIER";

            textView.setText(idGet);
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, CoursBac2);
            ListeView.setAdapter(adapter);
        }
        else if(idGet.equals("TROISIEME BACHELIER")){
            //  identier promotion
            PROMOTION_MESSAGE = "TROISIEME BACHELIER";

            textView.setText(idGet);
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, CoursBac3);
            ListeView.setAdapter(adapter);
        }
        else {
            //  identier promotion
            PROMOTION_MESSAGE = " ";

            textView.setText("Erreur");
        }
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