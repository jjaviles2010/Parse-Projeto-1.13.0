package com.JLApps.photoshare.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.JLApps.photoshare.R;
import com.JLApps.photoshare.util.ParseErros;

public class LoginActivity extends AppCompatActivity {
    private TextView lblRegister;
    private EditText txtName, txtPassword;
    private Button btnLogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtName = (EditText)findViewById(R.id.txtName);
        txtPassword = (EditText)findViewById(R.id.txtPassword);
        btnLogar = (Button)findViewById(R.id.btnLogar);

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nmUser = txtName.getText().toString();
                String password = txtPassword.getText().toString();
                LogarUsuario(nmUser,password);
            }
        });

        lblRegister = (TextView)findViewById(R.id.lblCadastrarUsuario);
        lblRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        });

        //ParseUser.logOut();
        VerificarUsuarioLogado();
    }

    private void VerificarUsuarioLogado(){

        if(ParseUser.getCurrentUser() != null){
            AbrirTelaPrincipal();
        }
    }

    private void AbrirTelaPrincipal(){

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();

    }

    private void LogarUsuario(String userName, String userPassword){
        ParseUser.logInInBackground(userName, userPassword, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e==null){
                    Toast.makeText(LoginActivity.this,"Usuario logado!",Toast.LENGTH_LONG).show();
                    AbrirTelaPrincipal();
                }else {
                    Toast.makeText(LoginActivity.this,"Erro ao fazer log in!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
