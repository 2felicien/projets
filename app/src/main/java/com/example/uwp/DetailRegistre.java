package com.example.uwp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import static com.example.uwp.Cours.PROMOTION_MESSAGE;

public class DetailRegistre extends AppCompatActivity {
    TextView textViewNomEtudiant;
    ListView listeView;
    ArrayAdapter adapter;
    List<String> ListPromotions = Arrays.asList(
            "Séance 1 : 0",
            "Séance 2 : 1",
            "Séance 3 : 0",
            "Séance 4 : 1",
            "Séance 5 : 1",
            "Séance 6 : 1",
            "Séance 7 : 1",
            "Séance 8 : 1",
            "Séance 9 : 1"
    );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_registre);

        listeView = (ListView) findViewById(R.id.liste_view);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ListPromotions);
        listeView.setAdapter(adapter);

        afficher_presence();
    }


    public void afficher_presence(){
        Intent intent = getIntent();
        textViewNomEtudiant = (TextView) findViewById(R.id.textViewNomEtudiant);
        String idGet = intent.getStringExtra(PROMOTION_MESSAGE);
        String Text = PROMOTION_MESSAGE;
        textViewNomEtudiant.setText(Text);

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