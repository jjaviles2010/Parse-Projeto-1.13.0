package com.JLApps.photoshare.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.JLApps.photoshare.R;
import com.JLApps.photoshare.util.ParseErros;

public class CadastroActivity extends AppCompatActivity {
    TextView lblLogin;
    EditText txtName, txtEmail, txtPassword;
    Button btnCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        lblLogin = (TextView)findViewById(R.id.lblLogin);
        btnCadastro = (Button)findViewById(R.id.btnCadastrar);
        txtName = (EditText)findViewById(R.id.txtUser);
        txtEmail = (EditText)findViewById(R.id.txtEmail);
        txtPassword = (EditText)findViewById(R.id.txtPassword);

        lblLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirLoginUsuario();
            }
        });

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CadastrarUsuario();
            }
        });
    }

    private void CadastrarUsuario(){
        //Cria objeto usuario
        ParseUser user = new ParseUser();
        user.setUsername(txtName.getText().toString());
        user.setEmail(txtEmail.getText().toString());
        user.setPassword(txtPassword.getText().toString());

        //salva dados do usuario
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){
                    Toast.makeText(CadastroActivity.this,"Usuario criado com sucesso!",Toast.LENGTH_LONG).show();
                    abrirLoginUsuario();
                }else {
                    ParseErros erro = new ParseErros();
                    Toast.makeText(CadastroActivity.this,"Erro ao criar usuario!" + erro.getErro(e.getCode()),Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    private void abrirLoginUsuario(){
        finish();
    }
}
