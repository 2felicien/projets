package com.example.uwp;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    public Boolean MENU = false;
    private EditText Identifiant;
    private EditText Password;
    private Button btn_connexion;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Identifiant = (EditText) findViewById(R.id.editTextIdentifiant);
        Password = (EditText) findViewById(R.id.editTextMdp);
        btn_connexion = (Button) findViewById(R.id.btn_connexion);

        btn_connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _function_connexion(Identifiant.getText().toString(), Password.getText().toString());
            }
        });
    }


    public void _function_connexion(String UserIdentifiant, String UserPassword) {

        if(UserIdentifiant.equals("felix@gmail.com") && UserPassword.equals("1234")){
            Intent intent1 = new Intent(getApplicationContext(), Promotion.class);
            startActivity(intent1);
        }
        else{
            Toast.makeText(this, "Les informations saisi sont invalide.", Toast.LENGTH_SHORT).show();
        }
    }
}