package com.example.crudsqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crudsqlite.usuario.UsuarioDAO;

public class MainActivity extends AppCompatActivity {

    Button botaoEntrar;
    Button botaoSair;

    EditText usuario;
    EditText senha;

    UsuarioDAO usuarioDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoEntrar = findViewById(R.id.btEntrar);
        botaoSair = findViewById(R.id.btSair);

        usuario = findViewById(R.id.usuario);
        senha = findViewById(R.id.senha);

        usuarioDAO =
                new UsuarioDAO(openOrCreateDatabase(usuarioDAO.NOME_BANCO,
                        MODE_PRIVATE, null));


        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (usuarioDAO.autenticarUsuario(usuario.getText().toString(),
                        senha.getText().toString())) {
                    Intent intent = new
                            Intent(MainActivity.this, MenuPrincipal.class);
                    intent.putExtra("usuario", usuario.getText().toString());
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Dados Inválidos",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        botaoSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
    }
}
