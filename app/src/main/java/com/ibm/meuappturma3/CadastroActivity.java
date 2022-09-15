package com.ibm.meuappturma3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        EditText etNome = findViewById(R.id.editTextUsuario);
        EditText etEmail = findViewById(R.id.editTextEmail);
        EditText etSenha = findViewById(R.id.editTextSenha);
        EditText etSenhaConfirma = findViewById(R.id.editTextSenhaConfirm);

        etEmail.setText(getIntent().getStringExtra("KeyEmail") );
        etSenha.setText(getIntent().getStringExtra("KeySenha") );

       Button btnCadastrar = findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = etEmail.getText().toString();
                String senha = etSenha.getText().toString();
                String senhaConfirma = etSenhaConfirma.getText().toString();
                String nome = etNome.getText().toString();




                if( senha.equals(senhaConfirma) ){

                    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                    SharedPreferences.Editor  myEdit = sharedPreferences.edit();


                    myEdit.putString("nome", nome);
                    myEdit.commit();



                    //Ir pra tela surpresa
                    Intent intent = new Intent(CadastroActivity.this, ProdutosActivity.class);
                    startActivity(intent);

                } else{
                    //erro senhas divergentes
                    alerta("Senhas divergentes, por favor digite a mesma senha");
                }


            }
        });




        /*try {
            //ciar o banco
            SQLiteDatabase bancoDedados = openOrCreateDatabase("app", MODE_PRIVATE,null);

            //criar tabela
            bancoDedados.execSQL("CREATE TABLE IF NOT EXISTS dados (usuario VARCHAR, senha VARCHAR )");

            //inserir dados
            bancoDedados.execSQL("INSERT INTO dados (usuario, senha) VALUES ('dai@ibm.com', '123') ");

        }catch (Exception e){
            e.printStackTrace();
        }*/


    }

    private void alerta(String msg){
        new android.app.AlertDialog.Builder(CadastroActivity.this)
                .setTitle("ATENÇÃO")
                .setMessage(msg)

                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }

                })
                .setIcon(android.R.drawable.ic_delete)
                .show();
    }

}