package com.example.uwp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


import static com.example.uwp.Cours.PROMOTION_MESSAGE;

public class Registre extends AppCompatActivity {

    ListView  listView;
    TextView textView;
    ArrayAdapter adapter;

    //ArrayList<HashMap<String, String>> ListEtudiantsBac1 = new ArrayList<HashMap<String, String>>();
    //List<String> ListEtudiantsBac1 = Arrays.asList(" ","PREMIER BACHELIER", "DEUXIEME BACHELIER", "TROISIEME BACHELIER");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registre);

        listView = (ListView) findViewById(R.id.liste_view);
        textView = (TextView) findViewById(R.id.textView);
        afficher_liste_etudiant();

    }

    //private void chargerListEtudiants() {
    private void getJSON(final String urlWebService) {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                try {
                    loadIntoListView(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void loadIntoListView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        String[] Tabetudiants = new String[jsonArray.length()];

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            Tabetudiants[i] = obj.getString("Nom");

        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Tabetudiants);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Registre.this, DetailRegistre.class);
                intent.putExtra("Promotion", listView.getItemAtPosition(i).toString());
                startActivity(intent);
            }
        });
    }


    public void afficher_liste_etudiant(){

        // pour recuperer les donnes de l'activite Promotion
        Intent intent = getIntent();
        String idGet = intent.getStringExtra(PROMOTION_MESSAGE);


        String Text = PROMOTION_MESSAGE+" [" + idGet+" ] ";
        textView.setText(Text);

        String url = "http://192.168.43.68/uwepo/?p=";

        if(PROMOTION_MESSAGE.equals("PREMIER BACHELIER")){
            //  identifier promotion
            getJSON(url+"etudiant_bac1");
        }
        else if(PROMOTION_MESSAGE.equals("DEUXIEME BACHELIER")) {
            //  identifier promotion
            getJSON(url+"etudiant_bac2");
        }
        else if(PROMOTION_MESSAGE.equals("TROISIEME BACHELIER")){
            //  identifier promotion
             getJSON(url+"etudiant_bac3");
        }
        else {
            //  identifier promotion
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