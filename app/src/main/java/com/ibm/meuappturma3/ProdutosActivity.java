package com.ibm.meuappturma3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class ProdutosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);

        TextView nomeUsuario = findViewById(R.id.tvNomeUsuario);
        SharedPreferences pref = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        String nomeShared = pref.getString("nome", null);
        nomeUsuario.setText(nomeShared);

    }
}