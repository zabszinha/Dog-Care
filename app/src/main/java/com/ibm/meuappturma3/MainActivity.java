package com.ibm.meuappturma3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String msg = "";
    private String email, senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText etEmail, etSenha;

        etEmail = findViewById(R.id.etEmail);
        etSenha = findViewById(R.id.etSenha);
        TextView tv_Cadastro = findViewById(R.id.tv_Cadastro);


        Button botao = findViewById(R.id.btnCadastrar);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = etEmail.getText().toString();
                senha = etSenha.getText().toString();

                SharedPreferences pref = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                String emailShared = pref.getString("email", null);
                String senhaShared = pref.getString("senha", null);


                if(email.equals(emailShared) && senha.equals(senhaShared) ){
                    Intent intent = new Intent(MainActivity.this, ProdutosActivity.class);

                    intent.putExtra("KeyEmail", email);
                    intent.putExtra("KeySenha", senha);

                    startActivity(intent);
                }
                else{
                    dialog();
                }
            }
        });

        tv_Cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        });

    }

    private void enviar(){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    private void dialog(){

        new android.app.AlertDialog.Builder(MainActivity.this)
                .setTitle("ATENÇÃO")
                .setMessage("Você não possui cadastro, deseja cadastrar?")

                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
                        intent.putExtra("KeyEmail", email);
                        intent.putExtra("KeySenha", senha);
                        startActivity(intent);
                    }
                })

                .setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(getApplicationContext(),
                                "Acesso Bloqueado",
                                Toast.LENGTH_LONG).show();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}